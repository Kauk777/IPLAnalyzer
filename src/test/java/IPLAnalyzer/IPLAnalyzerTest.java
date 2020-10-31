
package IPLAnalyzer;

import org.junit.Test;
import CSVBuilder.*;
import org.junit.Assert;

public class IPLAnalyzerTest {
   
	private static final String IPL_FACTSHEET_MOSTRUNS_FILE_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_FACTSHEET_MOSTWKTS_FILE_PATH="./src/test/resources/IPL2019FactsheetMostWkts.csv";
	
	@Test
	public void given_IplFactsheetMostRuns_ShouldReturnCount() {
		try {
			IPLAnalyzer iplAnalyzer=new IPLAnalyzer();
			int numOfRecords=iplAnalyzer.loadIPLMostRunsCSV(IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			Assert.assertEquals(100,numOfRecords);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		} 
	}
}
