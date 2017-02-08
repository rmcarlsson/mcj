package se.trantor.mcj;

import java.text.MessageFormat;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class HeaterSim implements HeaterService {

	private int power = 0;

	private static final Logger logger = Logger.getLogger(HeaterSim.class.getName());
	/* (non-Javadoc)
	 * @see se.trantor.mcj.HeaterService#SetPower(int)
	 */
	@Override
	public void SetPower(int controlSignal) {
		power = controlSignal;
		logger.log(Level.FINE, "Set power to {0}", controlSignal);

	}

	/* (non-Javadoc)
	 * @see se.trantor.mcj.HeaterService#getPower()
	 */
	@Override
	public int getPower() {
		return power;
	}

	public void run() {

		logger.log(Level.INFO, "Spawning heater");
		
		try {

			while (true)
			{
				long on_time = HeaterService.PERIOD;
				
				if (power == 0)
					on_time = 0;
				if (power < (int)Math.abs((double)MAX_POWER * 0.95))
					on_time = (power * PERIOD) / MAX_POWER;
				else
					on_time = PERIOD;

				logger.log(Level.FINE, MessageFormat.format("Power is {0}, on time is {1}", power, on_time));
				
				long off_time = PERIOD - on_time;

				if (on_time > 0)
				{
					Thread.sleep(on_time);
					
				}

				if (off_time > 0) 
				{
					Thread.sleep(off_time);
				}
			}
		} 
		catch (InterruptedException e) 
		{
//
		}	
	}
}
