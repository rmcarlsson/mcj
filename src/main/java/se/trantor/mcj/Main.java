package se.trantor.mcj;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class Main {
	
	private static final Logger logger = Logger.getLogger(McNgMain.class.getName());

	public static void main(String[] args) {
		
	    final McNgMain server = new McNgMain();
	    try {
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    Thread networkDiscoveryThread = new Thread(NetworkDiscoveryThread.getInstance());  
	    networkDiscoveryThread.start();  
	    

    
	    
	    try {
			server.blockUntilShutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
