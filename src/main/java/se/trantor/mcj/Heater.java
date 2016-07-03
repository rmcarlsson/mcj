package se.trantor.mcj;

import java.text.MessageFormat;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Heater implements Runnable {

	private int power = 0;

	private static final Logger logger = Logger.getLogger(Heater.class.getName());
	public static final int PERIOD = 5000;
	public static final int MAX_POWER = 2200;


	public void SetPower(int controlSignal) {
		power = controlSignal;
		logger.log(Level.FINE, "Set power to {0}", controlSignal);

	}

	public int getPower() {
		return power;
	}

	public void run() {

		logger.log(Level.INFO, "Spawning heater");
		//gpio_write_val (0);
		try {

			while (true)
			{
				long on_time = Heater.PERIOD;
				
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
					//gpio_write_val (1);
				}

				if (off_time > 0) 
				{
					//gpio_write_val (0);
					Thread.sleep(off_time);
				}
			}
		} 
		catch (InterruptedException e) 
		{
			//gpio_write_val(0);
		}	
	}
}
