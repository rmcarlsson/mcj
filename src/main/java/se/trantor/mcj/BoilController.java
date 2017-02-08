package se.trantor.mcj;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.trantor.mcj.MashStepControl.MashControlStateE;

public class BoilController implements Runnable {


	private Logger logger = Logger.getLogger(BoilController.class.getName());
	private int boilTime;
	private ArrayList<HopAddition> hopAdditions = new ArrayList<HopAddition>();
	private int hopAddition = 0;
	private Date boilStarted;
	private MashControlStateE state;
	private boolean isWortChillSanitized = false;

	public BoilController(ArrayList<HopAddition> aHopAdditions, int aBoilTime) {
		boilTime = aBoilTime;
		hopAdditions = aHopAdditions;
		state = MashControlStateE.HEATING;
	}

	private synchronized void waitForWortChillerSanitizedDoneNotification() {
		while (!isWortChillSanitized)
		{
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		logger.log(Level.INFO, "Wort chiller sanitized done notification");
	}

	public synchronized void SetWortChillerSanitized()
	{
		isWortChillSanitized = true;
		notify();
	}
	
	public int getBoilTime()
	{	
		Date now = new Date();
		int ret;

		if (boilStarted != null)
		{
			long td = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - boilStarted.getTime());
			ret = (int)(boilTime - td);
		}
		else
		{
			ret = boilTime;
		}
		return ret;

	}

	public void run() {
		try {
			
			HeaterService heater = HeaterSingleton.getInstance();
			TemperatureService temperature = TemperatureSingleton.getInstance();
			temperature.SetHeater(heater);

			logger.log(Level.INFO, "Increasing power to max to reach boil.");
			heater.SetPower(HeaterService.MAX_POWER);

			while (temperature.GetTemperature() < 99)
				Thread.sleep(TimeUnit.MINUTES.toMillis(1));			

			logger.log(Level.INFO, "Reached boil, will boil for {0} minutes", boilTime);
			boilStarted = new Date();
			state = MashControlStateE.BOILING;
			Thread.sleep(TimeUnit.MINUTES.toMillis(boilTime));


			logger.log(Level.INFO, "Boil done. Waiting for chiller to be sanitized");
			state = MashControlStateE.BOIL_DONE;
			waitForWortChillerSanitizedDoneNotification();

			
			logger.log(Level.INFO, "Wort chiller to be sanitized, will power off");
			heater.SetPower(0);
			state = MashControlStateE.DONE;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			abort();
			return;
		}
	}

	private void abort()
	{
		logger.log(Level.INFO, "Boil aborted. Shuting down.");
		HeaterSingleton.getInstance().SetPower(0);
		state = MashControlStateE.DONE;
	}
	
	public MashControlStateE GetState() {
		return state;
	}




}
