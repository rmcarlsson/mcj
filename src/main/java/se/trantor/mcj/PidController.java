package se.trantor.mcj;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PidController {

	private double setPoint;
	private double kp;
	private double ki;
	private double kd;

	double l_p, l_i, l_d = 0;

	
	int lastTime;
	double ITerm, lastInput;

	long SampleTime;
	double outMin, outMax;
	boolean inAuto;
	private stateE state;
	
	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());
	private enum stateE { BINARY_MODE, PID_MODE };
	private static final double STABLE_CONTROL_HYST = 2;
	


	public PidController(double aKp, double aKi, double aKd) {
		inAuto = false;

		SetOutputLimits(0, Heater.MAX_POWER); // default output limit corresponds to
		// the arduino pwm limits

		SampleTime = 5000; // default Controller Sample Time is 0.1 seconds

		SetTunings(aKp, aKi, aKd);
		
		state = stateE.BINARY_MODE;
	}

	public void SetSetPoint(double aSetPoint)
	{
		logger.log(Level.INFO, "New setpoint {0}", aSetPoint);
		setPoint = aSetPoint;
		
			logger.log(Level.INFO, "Activating binary controller");
			state = stateE.BINARY_MODE;
	}
	
	public double GetError()
	{
		return l_p;
	}
	
	private int ExecPid(double aInput)
	{
		  /*Compute all the working error variables*/
		  double input = aInput;
		  double error = setPoint - input;
		  
		  ITerm += (ki * error);
		  if (ITerm > outMax)
		    ITerm = outMax;
		  else if (ITerm < outMin)
		    ITerm = outMin;
		  double dInput = (input - lastInput);

		  /*Compute PID Output*/
		  double output = kp * error + ITerm - kd * dInput;
		  if ((Math.abs ((kp * error) - l_p) > 2) || (Math.abs (ITerm - l_i) > 1)
		      || (Math.abs ((kd * dInput) - l_d) > 5))
		    {
		      l_p = (kp * error);
		      l_i = ITerm;
		      l_d = (kd * dInput);
		    }

		  if (output > outMax)
		    output = outMax;
		  else if (output < outMin)
		    output = outMin;

		  lastInput = input;

		  return (int)Math.round(output);
		
	}
	
	private int ExecBinaryMode(double aInput)
	{
		if ((setPoint-aInput) > STABLE_CONTROL_HYST)
			return (int)Math.round(outMax);
		if ((setPoint-aInput) < -STABLE_CONTROL_HYST)
			return (int)Math.round(outMin);
		else
		{
			logger.log(Level.INFO, "Activating PID controller");
			resetPid(aInput);
			state = stateE.PID_MODE;
			return (int)Math.round(outMin);
		}		
	}
	
	private void resetPid(double aInput) {
		l_d = 0;
		lastInput = aInput;
		ITerm = l_i = 120;
		l_p = (setPoint - aInput) * kp; 
	}

	public int Exec(double aInput) {
		int ret = 0;
		switch (state)
		{
		case BINARY_MODE:
			ret = ExecBinaryMode(aInput);
			break;
		case PID_MODE:
			ret = ExecPid(aInput);
			break;
		default:
			break;
		}
		return ret;
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

	public void SetSampleTime (int aNewSampleTime)
	{
	  if (aNewSampleTime > 0)
	    {
	      double ratio = (double) aNewSampleTime / (double) SampleTime;
	      ki *= ratio;
	      kd /= ratio;
	      SampleTime = (long) aNewSampleTime;
	    }
	}
	
	public boolean isControlStable() {
		return ((Math.abs(lastInput - setPoint) < STABLE_CONTROL_HYST) && (state == stateE.PID_MODE));
	}

}
