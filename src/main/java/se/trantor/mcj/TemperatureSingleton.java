package se.trantor.mcj;

public class TemperatureSingleton {

	public static final String id = "01.7756a67c"; 
	private static final Temperature instance = new Temperature(id);
	
	public static Temperature getInstance()
	{
		return instance;
	}
	
}
