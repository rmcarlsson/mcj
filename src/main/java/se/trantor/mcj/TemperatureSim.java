package se.trantor.mcj;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;

public class TemperatureSim implements TemperatureService {

	double temperature;
	private HeaterService heater;
	private static final Logger logger = Logger.getLogger(TemperatureSim.class.getName());
	private Date lastSampleTime = new Date();


	public TemperatureSim()
	{
		temperature = 60;
		heater = HeaterSingleton.getInstance();
		logger.log(Level.INFO, "Spawning temperature (simulated)");
	}

	public double GetTemperature()
	{		

		int val = heater.getPower();
		// Assume 11 L of water => 4.16 J/gK. 
		// 
		// 5 seconds => (5 * power) / (11000 * 4,16) => deltaT
		Date now = new Date();	
		long deltaTime = now.getTime() - lastSampleTime.getTime();
		lastSampleTime = now;
		
		
		double deltaTemp = ((deltaTime/1000) * (val-70)) / (11000 * 4.16); // kilograms and milliseconds... 
		temperature = deltaTemp + temperature;

		
		
		if (temperature > 101)
			temperature = 101;

		logger.log(Level.INFO, "Temperature is {0}", temperature);
		return temperature;
	}

	public void SetHeater(HeaterService aHeater) {
		heater = aHeater;
	}


	@Override
	public void run() {



		 
	}
}

