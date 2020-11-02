
package IPLAnalyzer;

import org.junit.Test;

import com.google.gson.Gson;

import org.junit.Assert;

public class IPLAnalyzerTest {

	private static final String IPL_FACTSHEET_MOSTRUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_FACTSHEET_MOSTWKTS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
	private static final String IPL_BATTING_AVERAGE_FILE_PATH = "./src/test/resources/IPL2019BattingAverages.json";
	private static final String IPL_STRIKE_RATE_FILE_PATH = "./src/test/resources/IPL2019StrikeRate.json";

	@Test
	public void given_IplFactsheetMostRuns_ShouldReturnCount() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
			int numOfRecords = iplAnalyzer.loadIPLMostRunsCSV(IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			Assert.assertEquals(101, numOfRecords);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_WithHighestBattingAverage() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
			iplAnalyzer.loadIPLMostRunsCSV(IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortByAverage = iplAnalyzer.iplRunsSortedData("battingAverage", true);
			iplAnalyzer.writingJsonFile(sortByAverage, IPL_BATTING_AVERAGE_FILE_PATH);
			IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByAverage, IPLMostRunsCSV[].class);
			Assert.assertEquals("MS Dhoni", iplRuns[0].player);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_WithHighestStrikeRate() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer();
			iplAnalyzer.loadIPLMostRunsCSV(IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortByStrikeRate = iplAnalyzer.iplRunsSortedData("strikeRate", true);
			iplAnalyzer.writingJsonFile(sortByStrikeRate, IPL_STRIKE_RATE_FILE_PATH);
			IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByStrikeRate, IPLMostRunsCSV[].class);
			Assert.assertEquals("Ishant Sharma", iplRuns[0].player);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_WithMaximumFoursAndSixes() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BATTING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BATTING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BATTING, IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortByFoursAndSix = iplAnalyzer.sortIPLDataFields(SortingFields.MAX_4s_AND_6s);
			IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByFoursAndSix, IPLMostRunsCSV[].class);
			Assert.assertEquals("Andre Russell", iplRuns[0].player);
		} catch (IPLAnalyzerException e) {
			// e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_WithMaximumFoursAndSixesWithBestStrikeRate() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BATTING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BATTING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BATTING, IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortByFoursAndSix = iplAnalyzer.sortIPLDataFields(SortingFields.MAX_4s_AND_6s_WITH_BEST_STRIKING_RATE);
			IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByFoursAndSix, IPLMostRunsCSV[].class);
			Assert.assertEquals("Ishant Sharma", iplRuns[0].player);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_BattingAverageWithBestStrikeRate() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BATTING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BATTING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BATTING, IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortByFoursAndSix = iplAnalyzer.sortIPLDataFields(SortingFields.BEST_AVERAGE_WITH_STRIKE_RATE);
			IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByFoursAndSix, IPLMostRunsCSV[].class);
			Assert.assertEquals("MS Dhoni", iplRuns[0].player);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_BattingAverageWithMaxRuns() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BATTING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BATTING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BATTING, IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortByFoursAndSix = iplAnalyzer.sortIPLDataFields(SortingFields.MAX_RUNS_WITH_BEST_AVERAGE);
			IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByFoursAndSix, IPLMostRunsCSV[].class);
			Assert.assertEquals("David Warner ", iplRuns[0].player);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostWicketsCSV_ShouldReturnHighestThePlayer_BowlingAverage() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BOWLING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BOWLING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BOWLING, IPL_FACTSHEET_MOSTWKTS_FILE_PATH);
			String sortData = iplAnalyzer.sortIPLDataFields(SortingFields.BOWLING_AVERAGE);
			IPLWickets[] iplWickets = new Gson().fromJson(sortData, IPLWickets[].class);
			Assert.assertEquals("Suresh Raina", iplWickets[0].playerName);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostWicketsCSV_ShouldReturnHighestThePlayer_MostStrikeRate() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BOWLING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BOWLING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BOWLING, IPL_FACTSHEET_MOSTWKTS_FILE_PATH);
			String sortData = iplAnalyzer.sortIPLDataFields(SortingFields.TOP_BOWLING_STRIKING_RATES);
			IPLWickets[] iplWickets = new Gson().fromJson(sortData, IPLWickets[].class);
			Assert.assertEquals("Suresh Raina", iplWickets[0].playerName);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostWicketsCSV_ShouldReturnHighestThePlayer_Economy() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BOWLING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BOWLING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BOWLING, IPL_FACTSHEET_MOSTWKTS_FILE_PATH);
			String sortData = iplAnalyzer.sortIPLDataFields(SortingFields.TOP_BOWLING_ECONOMY_RATES);
			IPLWickets[] iplWickets = new Gson().fromJson(sortData, IPLWickets[].class);
			Assert.assertEquals("Shivam Dube", iplWickets[0].playerName);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostWicketsCSV_ShouldReturnHighestThePlayer_BestStrikeRateWith5wand4w() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BOWLING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BOWLING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BOWLING, IPL_FACTSHEET_MOSTWKTS_FILE_PATH);
			String sortData = iplAnalyzer.sortIPLDataFields(SortingFields.TOP_BOWLING_STRIKING_RATES_WITH_MOST_4Ws_AND_5Ws);
			IPLWickets[] iplWickets = new Gson().fromJson(sortData, IPLWickets[].class);
			Assert.assertEquals("Lasith Malinga", iplWickets[0].playerName);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostWicketsCSV_ShouldReturnHighestThePlayer_BestStrikeRateWithAverage() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BOWLING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BOWLING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BOWLING, IPL_FACTSHEET_MOSTWKTS_FILE_PATH);
			String sortData = iplAnalyzer.sortIPLDataFields(SortingFields.GREAT_AVERAGE_WITH_BEST_STRIKING_RATE);
			IPLWickets[] iplWickets = new Gson().fromJson(sortData, IPLWickets[].class);
			Assert.assertEquals("Andre Russell", iplWickets[0].playerName);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostWicketsCSV_ShouldReturnHighestThePlayer_MostWicktesWithAverage() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BOWLING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BOWLING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BOWLING, IPL_FACTSHEET_MOSTWKTS_FILE_PATH);
			String sortData = iplAnalyzer.sortIPLDataFields(SortingFields.GREAT_AVERAGE_WITH_MOST_WICKETS);
			IPLWickets[] iplWickets = new Gson().fromJson(sortData, IPLWickets[].class);
			Assert.assertEquals("Andre Russell", iplWickets[0].playerName);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostWicketsCSV_ShouldReturnHighestThePlayer_BestBowlingAndBattingAverage() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BATTING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BATTING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BATTING, IPL_FACTSHEET_MOSTWKTS_FILE_PATH, IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortData = iplAnalyzer.sortIPLDataFields(SortingFields.BEST_BATTING_BOWLING_AVERAGE);
			IPLWickets[] iplWickets = new Gson().fromJson(sortData, IPLWickets[].class);
			Assert.assertEquals("Andre Russell", iplWickets[0].playerName);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostWicketsCSV_ShouldReturnHighestThePlayer_BestAllRounder() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BATTING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BATTING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BATTING, IPL_FACTSHEET_MOSTWKTS_FILE_PATH, IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortData = iplAnalyzer.sortIPLDataFields(SortingFields.ALL_ROUNDER);
			IPLWickets[] iplWickets = new Gson().fromJson(sortData, IPLWickets[].class);
			Assert.assertEquals("Hardik Pandya", iplWickets[0].playerName);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_BattingAverageWithMaxHundreds() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BATTING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BATTING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BATTING, IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortByFoursAndSix = iplAnalyzer.sortIPLDataFields(SortingFields.BEST_AVERAGE_WITH_HUNDREDS);
			IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByFoursAndSix, IPLMostRunsCSV[].class);
			Assert.assertEquals("MS Dhoni", iplRuns[0].player);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIPLMostRunsCSV_ShouldReturnHighestThePlayer_BattingAverageWithZeroHundredsAndFifites() {
		try {
			IPLAnalyzer iplAnalyzer = new IPLAnalyzer(IPLAnalyzer.Innings.BATTING);
			iplAnalyzer.setIPLAdapter(iplAnalyzer.getAdapterObject(IPLAnalyzer.Innings.BATTING));
			iplAnalyzer.loadIPLData(IPLAnalyzer.Innings.BATTING, IPL_FACTSHEET_MOSTRUNS_FILE_PATH);
			String sortByFoursAndSix = iplAnalyzer.sortIPLDataFields(SortingFields.BEST_AVERAGE_WITH_ZERO_HUNDREDS_FIFTIES);
			IPLMostRunsCSV[] iplRuns = new Gson().fromJson(sortByFoursAndSix, IPLMostRunsCSV[].class);
			Assert.assertEquals("MS Dhoni", iplRuns[0].player);
		} catch (IPLAnalyzerException e) {
			e.printStackTrace();
		}
	}
	
}
