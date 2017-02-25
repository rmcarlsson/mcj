package se.trantor.mcj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataEntry {

	public DataEntry(double aTemperature, Date aDate, double aSetpoint, double aOutput) {
		temp = aTemperature;
		date = aDate;
		set_point = aSetpoint;
		output = aOutput;
	}

	public double temp;
	public Date date;
	public double set_point;
	public double output;

	public String toString() {
		StringBuilder ret = new StringBuilder();
		SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");

		ret.append(ft.format(date)).append(", ").append(temp).append(", ").append(set_point).append(", ").append(output);
		return ret.toString();
	}
}
