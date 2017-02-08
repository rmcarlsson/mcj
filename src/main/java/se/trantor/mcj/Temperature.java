package se.trantor.mcj;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;

public class Temperature implements TemperatureService {

	double temperature;
	private HeaterService heater;
	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());
	private W1Device device = null;

	public Temperature()
	{
		temperature = 60;
		heater = HeaterSingleton.getInstance();
	}

	/* (non-Javadoc)
	 * @see se.trantor.mcj.TemperatureService#GetTemperature()
	 */
	@Override
	public double GetTemperature()
	{

		int val = heater.getPower();
		double diff = (val/20) - temperature;

		temperature += diff/50;
		if (temperature > 101)
			temperature = 101;

		logger.log(Level.FINE, "Temperature is {0}", temperature);
		return temperature;
	}

	/* (non-Javadoc)
	 * @see se.trantor.mcj.TemperatureService#SetHeater(se.trantor.mcj.Heater)
	 */
	@Override
	public void SetHeater(HeaterService aHeater) {
		heater = aHeater;
	}


	@Override
	public void run() {
		logger.log(Level.INFO, "Spawning temperature");

		if (device == null)
		{
			W1Master master = new W1Master();
			List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
			for (W1Device device : w1Devices) 
			{
				logger.log(Level.INFO, "Found a 1-Wire temperature sensor, ID: {0}", device.getId());	            //returns the ID of the Sensor and the  full text of the virtual file
				this.device = device; 
			}
		}

		
		try {
			while (!Thread.currentThread().isInterrupted())
			{

				logger.log(Level.INFO, "Temperature is {0}", ((TemperatureSensor) device).getTemperature());
				Thread.sleep(TimeUnit.SECONDS.toMillis(5));
			}
		 } catch (InterruptedException ex) {}
		 
	}
}

