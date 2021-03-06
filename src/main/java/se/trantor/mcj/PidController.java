package se.trantor.mcj;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PidController {

	private double setPoint;
	private double kp = 84.8828;
	private double ki = 0.038627586;
	private double kd = 41968.58689;

	private static final double HC_W = 4.189;
	private static final double HC_G = 1.592;

	private static final double K = 6.7;
	private static final double M = -273;
	
	private double grainbillWeight;
	private double mashWaterVolume;

	double l_p, l_i, l_d = 0;

	int lastTime;
	double ITerm, lastInput;

	long SampleTime;
	double outMin, outMax;
	boolean inAuto;
	private stateE state;
	private double setpointStep;
	private double tempOff;
	private DataLoggerService data_logger;

	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());

	private enum stateE {
		UNSTABLE, STABLE
	};

	private static final double STABLE_CONTROL_HYST = 1;

	public PidController(double aMashWaterVolume, double aGrainbillWeight) {
		inAuto = false;

		SetOutputLimits(0, HeaterService.MAX_POWER); // default output limit
												// corresponds to
		// the arduino pwm limits

		SampleTime = 5000; // default Controller Sample Time is 0.1 seconds

		SetTunings(kp, ki, kd);

		state = stateE.UNSTABLE;

		mashWaterVolume = aMashWaterVolume;
		grainbillWeight = aGrainbillWeight;
		
		setpointStep = 0;
		tempOff = 0;
		logger.setLevel(Level.ALL);
		
		data_logger = new DataLoggerImpl();
	}

	public void SetSetPoint(double aSetPoint, Boolean isDerivateDisabled) {
		logger.log(Level.INFO, "New setpoint {0}", aSetPoint);
		setPoint = aSetPoint;
		
		setpointStep = 0;
		tempOff = 0;
		state = stateE.UNSTABLE;
		
		resetPid(aSetPoint);
		
		if (isDerivateDisabled)
			kd = 0;
		
		logger.log(Level.INFO, "Controller changed from STABLE to UNSTABLE");
	}
	
	public void SetSetPoint(double aCurrValue, double aSetPoint, int aHeatOverTime) {
		
		assert aSetPoint > aCurrValue;
		assert aHeatOverTime > 0;
		
		// Initiate integrator
		double dT = (aSetPoint - aCurrValue) / (aHeatOverTime * 60);
		ITerm = dT * (HC_W * mashWaterVolume * 1000 + HC_G * grainbillWeight);


		l_i = ITerm += K * aCurrValue + M;
		
		kd = 0;

		// Calculate set point time steps
		setpointStep = dT * HeaterService.PERIOD / 1000;

		logger.log(Level.INFO, MessageFormat.format("New setpoint. Moving from {0} to {1} over {2} minutes", aCurrValue, aSetPoint, aHeatOverTime));
		logger.log(Level.INFO, "Initiating integrator to {0} watts", ITerm);
	
		setPoint = aSetPoint;
		tempOff = (aSetPoint - aCurrValue);

		logger.log(Level.INFO, "Controller is now STABLE");
		state = stateE.STABLE;
	}

	public double GetError() {
		return l_p;
	}

	public int Exec(double aInput) {

		if (tempOff > 0)
			tempOff -= setpointStep;
		else
		{
			kd = 41968.58689;
			tempOff = 0;
		}
			
		
		double _setPoint = setPoint - tempOff;
		

		/* Compute all the working error variables */
		double input = aInput;
		double error = _setPoint - input;

		ITerm += (ki * error);
		if (ITerm > outMax)
			ITerm = outMax;
		else if (ITerm < outMin)
			ITerm = outMin;
		double dInput = (input - lastInput);

		/* Compute PID Output */
		double output = kp * error + ITerm - kd * dInput;
		if ((Math.abs((kp * error) - l_p) > 2) || (Math.abs(ITerm - l_i) > 1) || (Math.abs((kd * dInput) - l_d) > 5)) {
			l_p = (kp * error);
			l_i = ITerm;
			l_d = (kd * dInput);
		}
		

		// Handle saturation and binary control
		if ((output < outMin)|| ((_setPoint - aInput) < 0))
			output = outMin;
		else if ((output > outMax) || ((_setPoint - aInput) > STABLE_CONTROL_HYST))
			output = outMax;

			
		
		if ((Math.abs(_setPoint - aInput) < STABLE_CONTROL_HYST) && (state == stateE.UNSTABLE))
		{
			state = stateE.STABLE;
			logger.log(Level.INFO, "Controller changed from UNSTABLE to STABLE");
		}
		
		lastInput = input;
		
		data_logger.AddDataSet(aInput, _setPoint, output);
		return (int) Math.round(output);

	}

	
	private void resetPid(double aInput) {

		ITerm = l_i = K * aInput + M;

		l_d = 0;
		lastInput = aInput;
		l_p = (setPoint - aInput) * kp;
		
		kd = 41968.58689;
	
		state = stateE.UNSTABLE;

		logger.log(Level.INFO, "Reseting PID, integrator set to {0} watts", ITerm);
		logger.log(Level.INFO, "Controller changed is now UNSTABLE");
	}

	

	private void SetOutputLimits(double Min, double Max) {
		if (Min >= Max)
			return;

		outMin = Min;
		outMax = Max;

		if (ITerm > outMax)
			ITerm = outMax;
		else if (ITerm < outMin)
			ITerm = outMin;
	}

	private void SetTunings(double Kp, double Ki, double Kd) {
		if (Kp < 0 || Ki < 0 || Kd < 0)
			return;

		double SampleTimeInSec = ((double) SampleTime) / 1000;
		kp = Kp;
		ki = Ki * SampleTimeInSec;
		kd = Kd / SampleTimeInSec;

	}

	public void SetSampleTime(int aNewSampleTime) {
		if (aNewSampleTime > 0) {
			double ratio = (double) aNewSampleTime / (double) SampleTime;
			ki *= ratio;
			kd /= ratio;
			SampleTime = (long) aNewSampleTime;
		}
	}

	public boolean isControlStable() {
		return (state == stateE.STABLE);
	}

	public void DumpLogger(String string) {
		
		data_logger.DumpData(string);
		
	}

}
