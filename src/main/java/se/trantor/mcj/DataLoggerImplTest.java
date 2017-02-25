package se.trantor.mcj;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataLoggerImplTest {

	@Test
	public void testAddDataSet() {
		DataLoggerService dut = new DataLoggerImpl();
		
		double t = 0;
		double s = 0;
		for (int i = 0; i < 100; i++)
		{
			s = i;
			t = i;
			dut.AddDataSet(t, s,0);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		dut.DumpData("log.txt");
		

	}

	@Test
	public void testDumpData() {
		fail("Not yet implemented");
	}

}
