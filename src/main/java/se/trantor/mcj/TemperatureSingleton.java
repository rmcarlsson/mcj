package se.trantor.mcj;

public class TemperatureSingleton {

	private static final Temperature instance = new Temperature();
	private static Thread thrd = null;
	
	public static Temperature getInstance()
	{
		if (thrd  == null)
		{
			thrd = new Thread(instance);
			thrd.start();
		}
		
		return instance;
	}
	
}
