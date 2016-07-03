package se.trantor.mcj;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Temperature {
	
	private String id;
	double temperature;
	private Heater heater;
	private PidController pidController;
	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());
	
	
	public Temperature(String aId)
	{
		id = aId;
		temperature = 50;
	}

	public double GetTemperature()
	{
		int val = heater.getPower();
		double diff = (val/2) - temperature;
		
		temperature += diff/50;
		if (temperature > 101)
			temperature = 101;
		
		logger.log(Level.FINE, "Temperature is {0}", temperature);
		return temperature;
	}

	public void SetHeater(Heater aHeater) {
		heater = aHeater;
	}
	
	public void SetPidController(PidController aPid)
	{
		pidController = aPid;
	}
}

