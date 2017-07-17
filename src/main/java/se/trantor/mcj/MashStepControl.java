package se.trantor.mcj;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MashStepControl implements Runnable {

	public enum MashControlStateE {
		INIT, WAIT_FOR_GRAINS, STEP_MASHING, HEATING, MASH_DONE, HEATING_TO_STRIKE_WATER, BOILING, BOIL_DONE, DONE, HEAT_OVER_MASHING;
	}

	private ArrayList<MashStep> mashStepProfile;
	private int stepIx;

	private PidController pidController;
	private TemperatureService temperature;

	private boolean running;

	private MashControlStateE state;
	private boolean grainsAdded;
	private MashStep currMashStep;

	private Date stepStartTime;
	double mashTemp;

	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());

	public MashStepControl(ArrayList<MashStep> aMashProfile, PidController aPidController) {
		change_state(MashControlStateE.INIT);
		grainsAdded = false;
		stepIx = 0;
		mashStepProfile = aMashProfile;
		pidController = aPidController;

		logger.setLevel(Level.FINE);
	}

	public void SetGrainsAdded(boolean aGrainAddedIndication) {
		grainsAdded = true;
	}

	private void handle_done() {
		running = false;
		logger.log(Level.INFO, "Mashing done");
	}

	void handle_heating() {
		if (pidController.isControlStable()) {
			change_state(MashControlStateE.STEP_MASHING);
			logger.log(Level.INFO, MessageFormat.format("Heating done to {0} C. Will stay for {1} minutes.",
					currMashStep.Temperature, currMashStep.StepTime));
		}
	}

	private void handle_heat_over_mashing() {
		Date now = new Date();

		long td = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - stepStartTime.getTime());
		if (td < 0)
			td = 0;

		// The step in the mashing is done. What next?
		if (td >= currMashStep.HeatOverTime) {

			// <########## STATE transition HEAT_OVER_MASHING -> STEP_MASHING
			// ##########>
			change_state(MashControlStateE.STEP_MASHING);
			// Transition action
			logger.log(Level.FINE, "Update setpoint to {0} C for next mash step.", currMashStep.Temperature);

			// Transition action
			pidController.SetSetPoint(currMashStep.Temperature, false);
		}
	}

	private void handle_step_mashing() {
		Date now = new Date();

		long td = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - stepStartTime.getTime());
		if (td < 0)
			td = 0;

		// The step in the mashing is done. What next?
		if (td >= currMashStep.StepTime) {

			stepIx++;
			if (mashStepProfile.size() > stepIx) {

				mashTemp = currMashStep.Temperature;

				currMashStep = mashStepProfile.get(stepIx);

				logger.log(Level.INFO,
						MessageFormat.format(
								"Loading next mash step heat to {0} C over {1} minutes. Stay on rest for {2} minutes, ",
								currMashStep.Temperature, currMashStep.HeatOverTime, currMashStep.StepTime));

				if (currMashStep.HeatOverTime != 0) {

					// <########## STATE transition STEP_MASHING ->
					// HEAT_OVER_MASHING ##########>
					change_state(MashControlStateE.HEAT_OVER_MASHING);

					// Transition action
					logger.log(Level.FINE, "Update setpoint to {0} C to hold heat over temperature.", mashTemp);
					pidController.SetSetPoint(mashTemp, currMashStep.Temperature, currMashStep.HeatOverTime);

				} else {
					// <########## STATE transition STEP_MASHING -> HEATING
					// ##########>
					change_state(MashControlStateE.HEATING);

					pidController.SetSetPoint(currMashStep.Temperature, false);
					logger.log(Level.INFO, "Starting next step. Heating to {0} C", currMashStep.Temperature);
				}

			} else {
				change_state(MashControlStateE.MASH_DONE);

				logger.log(Level.INFO, "Mash done\n");
				pidController.SetSetPoint(0, false);
			}
		}
	}

	private void handle_heating_strike_water() {

		if (pidController.isControlStable()) {
			pidController.SetSetPoint(currMashStep.Temperature, true);
			logger.log(Level.INFO,
					"Strike water temperature reached, targeting first mash step at {0} C. Please add grains.",
					currMashStep.Temperature);
			grainsAdded = false;

			// <########## STATE transition INIT -> WAIT_FOR_GRAINS ##########>
			change_state(MashControlStateE.WAIT_FOR_GRAINS);

		}
	}

	private void change_state(MashControlStateE aNewState) {
		state = aNewState;
		stepStartTime = new Date();

	}

	public void handle_wait_for_grains() {

		if (grainsAdded) {
			logger.log(Level.INFO, "Starting first mash step at {0} C\n", currMashStep.Temperature);

			// <########## STATE transition WAIT_FOR_GRAINS -> STEP_MASHING
			// ##########>
			change_state(MashControlStateE.STEP_MASHING);

		}

	}

	private void Exec() {

		switch (state) {
		case INIT:
			handle_init();
			break;
		case HEATING_TO_STRIKE_WATER:
			handle_heating_strike_water();
			break;
		case WAIT_FOR_GRAINS:
			handle_wait_for_grains();
			break;
		case HEATING:
			handle_heating();
			break;
		case HEAT_OVER_MASHING:
			handle_heat_over_mashing();
			break;
		case STEP_MASHING:
			handle_step_mashing();
			break;
		case MASH_DONE:
			handle_done();
			break;
		default:
			assert (true);
			break;
		}
	}

	private void handle_init() {
		currMashStep = mashStepProfile.get(0);
		logger.log(Level.INFO, "Heating to strike water temperature at {0} for the first step",
				currMashStep.Temperature);

		pidController.SetSetPoint(currMashStep.Temperature, false);

		// <########## STATE transition INIT -> HEATING_TO_STRIKE_WATER
		// ##########>
		change_state(MashControlStateE.HEATING_TO_STRIKE_WATER);

	}

	public void run() {
		try {

			HeaterService heater = HeaterSingleton.getInstance();

			temperature = TemperatureSingleton.getInstance();
			running = true;

			while (running && !Thread.currentThread().isInterrupted()) {

				Thread.sleep(HeaterService.PERIOD);
				this.Exec();

				int controlSignal = pidController.Exec(temperature.GetTemperature());
				heater.SetPower(controlSignal);

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.log(Level.INFO, "Mashing aborted. Shutting down");
			running = false;
		}

		pidController.SetSetPoint(0, false);
		pidController.DumpLogger("mcj_data.log");
		HeaterSingleton.getInstance().SetPower(0);
	}

	public ArrayList<MashStep> GetCurrentMashProfileStatus() {
		ArrayList<MashStep> ret = new ArrayList<MashStep>();
		for (int i = stepIx; i < mashStepProfile.size(); i++) {
			MashStep s = new MashStep();
			
			if (state == MashControlStateE.HEAT_OVER_MASHING) {
				int sTime = currMashStep.HeatOverTime; 
			}
			
				
			s.Temperature = mashStepProfile.get(i).Temperature;
			s.StepTime = mashStepProfile.get(i).StepTime;
			s.HeatOverTime = mashStepProfile.get(i).HeatOverTime;
			if ((i == stepIx) && (stepStartTime != null)) {
				Date now = new Date();
				long td = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - stepStartTime.getTime());
				if (state == MashControlStateE.HEAT_OVER_MASHING) {
					s.HeatOverTime -= td;
				}
				else
				{
					s.HeatOverTime = 0;
					s.StepTime -= td;
				}
			}
			ret.add(s);
		}

		return ret;
	}

	public double GetMashTemperature() {
		// TODO Auto-generated method stub
		return temperature.GetTemperature();
	}

	public int GetSetpoint() {
		if (currMashStep != null)
			return currMashStep.Temperature;
		else
			return 0;
	}

	public MashControlStateE GetState() {
		return state;
	}

}
