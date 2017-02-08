package se.trantor.mcj;

public interface TemperatureService extends Runnable {

	double GetTemperature();

	void SetHeater(HeaterService aHeater);

}