package se.trantor.mcj;

public class TemperatureSingleton {

	private static final TemperatureService instance = new Temperature();
	private static Thread thrd = null;
	
	public static TemperatureService getInstance()
	{
		if (thrd  == null)
		{
			thrd = new Thread(instance);
			thrd.start();
		}
		
		return instance;
	}
	
}
