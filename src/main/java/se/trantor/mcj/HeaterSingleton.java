package se.trantor.mcj;

public class HeaterSingleton {
	
	private static Thread thrd;

	private static final Heater instance = new Heater();
	
	public static Heater getInstance()
	{
		if (thrd == null)
		{
			thrd = new Thread(instance);
			thrd.start();
			
		}
		return instance;
	}
	
}

