package se.trantor.mcj;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.trantor.grpcproto.BrewStatusReply;
import se.trantor.mcj.MashStepControl.MashControlStateE;

public class MashStepControl implements Runnable {

	public enum MashControlStateE {
		INIT, WAIT_FOR_GRAINS, MASHING, HEATING, MASH_DONE, HEATING_TO_STRIKE_WATER, BOILING, DONE;
	}
	private ArrayList<MashStep> mashStepProfile;
	private int stepIx;

	private PidController pidController;
	private Temperature temperature;

	private int period;

	private boolean running;

	private MashControlStateE state;
	private boolean grainsAdded;
	private MashStep currMashStep ;
	private Date stepStartTime;

	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());


	public MashStepControl(ArrayList<MashStep> aMashProfile)
	{
		state = MashControlStateE.INIT;
		grainsAdded = false;
		stepIx = 0;
		mashStepProfile = aMashProfile;
	}

	public void SetGrainsAdded(boolean aGrainAddedIndication)
	{
		grainsAdded = true;
	}

	private void handle_done() {
		running = false;
		logger.log(Level.INFO, "Mashing done");
	}

	void handle_heating() {
		if (pidController.isControlStable()) {
			state = MashControlStateE.MASHING;
			logger.log(Level.INFO,
					MessageFormat.format("Heating done to {0} C. Will stay for {1} minutes.",
							currMashStep.Temperature, currMashStep.Time));
		}
	}

	private void handle_mashing() {
		Date now = new Date();

		if (stepStartTime == null)
			stepStartTime = new Date();

		long td = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - stepStartTime.getTime());

		if (td >= currMashStep.Time) {
			stepStartTime = new Date();

			stepIx++;
			if(mashStepProfile.size() > stepIx)
			{
				currMashStep = mashStepProfile.get(stepIx);
				pidController.SetSetPoint(currMashStep.Temperature);
				logger.log(Level.INFO, "Starting next step. Heating to {0} C", currMashStep.Temperature);

				// <########## STATE transition INIT -> WAIT_FOR_GRAINS ##########>
				state = MashControlStateE.HEATING;


			} else {
				state = MashControlStateE.MASH_DONE;
				logger.log(Level.INFO, "Mash done\n");
				pidController.SetSetPoint(0);
			}
		}
	}

	private void handle_heating_strike_water() {

		if (pidController.isControlStable()) {
			pidController.SetSetPoint(currMashStep.Temperature);
			logger.log(Level.INFO, "Strike water temperature reached, targeting first mash step at {0} C. Please add grains.",
					currMashStep.Temperature);
			grainsAdded = false;

			// <########## STATE transition INIT -> WAIT_FOR_GRAINS ##########>
			state = MashControlStateE.WAIT_FOR_GRAINS;

		}
	}

	public void handle_wait_for_grains() {

		if (grainsAdded) {
			logger.log(Level.INFO, "Starting first mash step at {0} C\n", currMashStep.Temperature);

			// <########## STATE transition WAIT_FOR_GRAINS -> MASHING ##########>
			state = MashControlStateE.MASHING;
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
		case MASHING:
			handle_mashing();
			break;
		case MASH_DONE:
			handle_done();
			break;
		default:
			assert(true);
			break;
		}
	}

	private void handle_init() {
		currMashStep = mashStepProfile.get(0);
		logger.log(Level.INFO,
				"Heating to strike water temperature at {0} for the first step", currMashStep.Temperature);

		pidController.SetSetPoint(currMashStep.Temperature);

		// <########## STATE transition INIT -> HEATING_TO_STRIKE_WATER ##########>
		state = MashControlStateE.HEATING_TO_STRIKE_WATER;

	}


	public void run() {
		try {

			Heater heater = HeaterSingleton.getInstance();
			pidController = new PidController(1, 2, 3);

			temperature = TemperatureSingleton.getInstance();
			temperature.SetHeater(heater);
			temperature.SetPidController(pidController);
			running = true;

			while (running && !Thread.currentThread().isInterrupted()) 
			{

				Thread.sleep(Heater.PERIOD);
				this.Exec();

				int controlSignal = pidController.Exec(temperature.GetTemperature());
				heater.SetPower(controlSignal);

			} 
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.log(Level.INFO, "Mashing aborted. Shutting down");
			running = false;
		}

		pidController.SetSetPoint(0);
		HeaterSingleton.getInstance().SetPower(0);
	}


	public ArrayList<MashStep> GetCurrentMashProfileStatus() {
		ArrayList<MashStep> ret = new ArrayList<MashStep>();
		for (int i=stepIx; i < mashStepProfile.size(); i++)
		{	
			MashStep s = new MashStep();
			s.Temperature = mashStepProfile.get(i).Temperature;
			s.Time = mashStepProfile.get(i).Time;
			if ((i==stepIx) && (stepStartTime != null)) {
				Date now = new Date();
				long td = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - stepStartTime.getTime());
				s.Time -= td;  
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
