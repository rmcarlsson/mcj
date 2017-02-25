package se.trantor.mcj;

public interface DataLoggerService {
	
	void AddDataSet(double aTemperature, double aSetpoint, double aOutput);
	void DumpData(String aPathAndFile);
	
}
