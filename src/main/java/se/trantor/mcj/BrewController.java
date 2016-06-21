package se.trantor.mcj;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.trantor.mcj.MashStepControl.MashControlStateE;


public class BrewController implements Runnable{

	private Logger logger = Logger.getLogger(BoilController.class.getName());
	private ArrayList<MashStep> mashProfile = new ArrayList<MashStep>();
	private ArrayList<HopAddition> hopAdditions = new ArrayList<HopAddition>();
	private int boilTime;
	private MashStepControl msc;
	private boolean spargeDone;
	private BoilController bc;

	private enum stateE { MASHING, BOILING, DONE; }

	private stateE state = stateE.MASHING; 

	public BrewController(ArrayList<MashStep> aMashProfile, 
			ArrayList<HopAddition> aHopAdditionList, 
			int aBoilTime )
	{
		boilTime = aBoilTime;
		hopAdditions = aHopAdditionList;
		mashProfile = aMashProfile;
	}


	@Override
	public void run() {
		state = stateE.MASHING;
		msc = new MashStepControl(mashProfile);
		Thread tMc = new Thread(msc);
		tMc.start();		
		try {
			tMc.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			tMc.interrupt();
			return;
		}

		waitForSpargeDoneNotification();

		if(Thread.currentThread().isInterrupted())
		{
			state = stateE.DONE;
			return;
		}

		state = stateE.BOILING;
		bc = new BoilController(hopAdditions, boilTime);
		Thread tBc = new Thread(bc);
		tBc.start();

		try {
			tBc.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			tBc.interrupt();
		}
		state = stateE.DONE;

	}

	public BrewStatus getStatus()
	{	
		BrewStatus ret = new BrewStatus();

		Temperature t = TemperatureSingleton.getInstance();
		ret.currentMashTemp = t.GetTemperature();

		ret.state = GetState();

		return ret;

	}

	private synchronized void waitForSpargeDoneNotification() {
		while (!spargeDone)
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				return;

			}
		}
	}

	public synchronized void SpargeDoneNotify()
	{
		logger.log(Level.INFO, "Sparge done notification");
		spargeDone = true;
		notify();
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
