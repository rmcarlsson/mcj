package se.trantor.mcj;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.trantor.mcj.MashStepControl.MashControlStateE;


public class BrewController implements Runnable{

	private Logger logger = Logger.getLogger(BoilController.class.getName());
	private ArrayList<MashStep> mashProfile = new ArrayList<MashStep>();
	private ArrayList<HopAddition> hopAdditions = new ArrayList<HopAddition>();
	private int boilTime;
	private double mashWaterVolume;
	private double grainbillWeight;
	private MashStepControl msc;
	private boolean spargeDone;
	private BoilController bc;

	private enum stateE { MASHING, BOILING, DONE; }

	private stateE state = stateE.MASHING;
	private int totalProcessTime = 0; 

	public BrewController(ArrayList<MashStep> aMashProfile, 
			ArrayList<HopAddition> aHopAdditionList, 
			int aBoilTime,
			double aMashWaterVolume, double aGrainbillWeight)
	{
		boilTime = aBoilTime;
		hopAdditions = aHopAdditionList;
		mashProfile = aMashProfile;
		mashWaterVolume = aMashWaterVolume;
		grainbillWeight = aGrainbillWeight;
	}


	public void run() {
		Thread tMc = null;
		Thread tBc = null;
		totalProcessTime  = calculateTotalBrewProcessTime();
		try {
			if (!mashProfile.isEmpty())
			{
				state = stateE.MASHING;
			
				msc = new MashStepControl(mashProfile, new PidController(mashWaterVolume, grainbillWeight));
				tMc = new Thread(msc);
				tMc.start();		
				tMc.join();

				waitForSpargeDoneNotification();
			}
			
			state = stateE.BOILING;
			bc = new BoilController(hopAdditions, boilTime);
			tBc = new Thread(bc);
			tBc.start();
			tBc.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			if (tBc != null)
				tBc.interrupt();
			if (tMc != null)
				tMc.interrupt();
			state = stateE.DONE;
		}
		state = stateE.DONE;

	}

	private int calculateTotalBrewProcessTime() {
		int ret = 0;
		
		for (int i=0; i < mashProfile.size(); i++)
		{	
			ret += mashProfile.get(i).StepTime;
		}
		
		ret += boilTime;
		
		// TODO Auto-generated method stub
		return ret;
	}


	public BrewStatus getStatus()
	{	
		int totalTimeRemaining = 0;
		BrewStatus ret = new BrewStatus();

		Temperature t = TemperatureSingleton.getInstance();
		ret.currentMashTemp = t.GetTemperature();
		if (state == stateE.BOILING)
			ret.boilTime = bc.getBoilTime();
		else
			ret.boilTime = boilTime;

		if (state == stateE.MASHING)
		{
			ret.currentMashProfile = msc.GetCurrentMashProfileStatus();
			for (int i=0; i < ret.currentMashProfile.size(); i++)
			{	
				totalTimeRemaining += ret.currentMashProfile.get(i).StepTime;
			}
		}	
		totalTimeRemaining += ret.boilTime;
		
		ret.state = GetState();
		if (ret.state != MashControlStateE.DONE )
		{
			ret.progress = ((totalProcessTime - totalTimeRemaining) * 100) / totalProcessTime;
		}
		else
			ret.progress = 0;
		
		return ret;

	}

	private synchronized void waitForSpargeDoneNotification() {
		while (!spargeDone)
		{
			try {
				this.wait();
			} catch (InterruptedException e) {}
		}
		logger.log(Level.INFO, "Sparge done notification");
	}

	public synchronized void SpargeDoneNotify()
	{
		spargeDone = true;
		notify();
	}
	
	public synchronized void WortChillerSanitiedDoneNotify()
	{
		if (bc != null)
		{
			logger.log(Level.INFO, "Wort chiller sanitized done notification");
			bc.SetWortChillerSanitized();
		}
	}

	public ArrayList<MashStep> GetCurrentMashProfile() {
		// TODO Auto-generated method stub
		return msc.GetCurrentMashProfileStatus();
	}


	public double GetMashTemperature() {
		// TODO Auto-generated method stub
		return msc.GetMashTemperature();
	}


	public double GetSetpoint() {
		// TODO Auto-generated method stub
		return msc.GetSetpoint();
	}


	public MashControlStateE GetState() {
		// TODO Auto-generated method stub

		if (state == stateE.MASHING)
		{
			return msc.GetState();
		}

		if (state == stateE.BOILING)
		{
			return bc.GetState();
		}
		else
			return MashControlStateE.DONE;

	}


	public void SetGrainsAdded(boolean b) {
		// TODO Auto-generated method stub
		msc.SetGrainsAdded(b);
	}


}
