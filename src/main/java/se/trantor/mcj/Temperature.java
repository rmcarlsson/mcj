package se.trantor.mcj;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;

public class Temperature {
	
	private String id;
	double temperature;
	private Heater heater;
	private PidController pidController;
	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());
	private W1Device device = null;
	
	public Temperature(String aId)
	{
		id = aId;
		temperature = 50;
	}

	public double GetTemperature()
	{
		
		if (device == null)
		{
			W1Master master = new W1Master();
			List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
			for (W1Device device : w1Devices) {
				logger.log(Level.INFO, "Found a 1-Wire temperature sensor, ID: {0}", device.getId());	            //returns the ID of the Sensor and the  full text of the virtual file
				this.device = device; 
			}
		}
		

        logger.log(Level.INFO, "Temperature is {0}", ((TemperatureSensor) device).getTemperature());
		
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

