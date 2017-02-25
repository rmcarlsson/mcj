package se.trantor.mcj;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class DataLoggerImpl implements DataLoggerService {
	

	private ArrayList<DataEntry> data_list;
	
	public DataLoggerImpl()
	{
		data_list = new ArrayList<DataEntry>();
	}

	
	@Override
	public void AddDataSet(double aTemperature, double aSetpoint, double aOutput) {
		data_list.add(new DataEntry(aTemperature, new Date(), aSetpoint, aOutput));
	}

	@Override
	public void DumpData(String aPathAndFile) {
		BufferedWriter out = null;
		try  
		{
		    FileWriter fstream = new FileWriter(aPathAndFile, false); //true tells to append data.
		    out = new BufferedWriter(fstream);
		    for(DataEntry d :  data_list)
		    {
		    	out.write(d.toString());
		    	out.newLine();
		    }
		}
		catch (IOException e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
		finally
		{
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}

	}

}
