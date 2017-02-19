package se.trantor.mcj;

public class HeaterSingleton  {
	
	private static Thread thrd;

	private static final HeaterService instance = new Heater();
	
	public static HeaterService getInstance()
	{
		if (thrd == null)
		{
			thrd = new Thread(instance);
			thrd.start();
			
		}
		return instance;
	}
	
}

