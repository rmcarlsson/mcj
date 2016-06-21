package se.trantor.mcj;

import java.util.ArrayList;

import se.trantor.mcj.MashStepControl.MashControlStateE;



public class BrewStatus {
	public double currentMashTemp = 0;
	public int currentMashSetPoint = 0;
	public ArrayList<MashStep> currentMashProfile = new ArrayList<MashStep>(); 
	public ArrayList<HopAddition> currentHopAdditions = new ArrayList<HopAddition>();
	public int boilTime = 0;
	public MashControlStateE state;

}
