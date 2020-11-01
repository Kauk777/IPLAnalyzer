
package IPLAnalyzer;

import org.junit.Test;

import com.google.gson.Gson;

import org.junit.Assert;
public class IPLAnalyzerTest {
   
	private static final String IPL_FACTSHEET_MOSTRUNS_FILE_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_FACTSHEET_MOSTWKTS_FILE_PATH="./src/test/resources/IPL2019FactsheetMostWkts.csv";
	
	@Test
	public void given_IplFactsheetMostRuns_ShouldReturnCount() {
		try {
			IPLAnalyzer iplAnalyzer=new IPLAnalyzer();
			int numOfRecords=iplAnalyzer.loadIPLMostRunsCSV(IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			Assert.assertEquals(101,numOfRecords);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		} 
	}
	
	 @Test
	 public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_WithHighestBattingAverage() {
		 try {
			 IPLAnalyzer iplAnalyzer= new IPLAnalyzer();
			 iplAnalyzer.loadIPLMostRunsCSV(IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			 String sortByAverage=iplAnalyzer.iplRunsSortedData("battingAverage",true);
			 IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByAverage, IPLMostRunsCSV[].class);
	         Assert.assertEquals("MS Dhoni",iplRuns[0].player);
		 } catch (IPLAnalyzerException e) {
				e.printStackTrace();
		} 
	 }
}
