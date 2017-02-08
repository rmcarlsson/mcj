package se.trantor.mcj;

public interface HeaterService extends Runnable {

	int PERIOD = 5000;
	int MAX_POWER = 2200;

	void SetPower(int controlSignal);

	int getPower();

}