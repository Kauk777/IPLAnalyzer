package IPLAnalyzer;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {
	
	@CsvBindByName(column = "POS", required = true)
	public int position;
	
	@CsvBindByName(column = "PLAYER", required = true)
	public String player;
	
	@CsvBindByName(column = "MAT", required = true)
	public int matches;
	
	@CsvBindByName(column = "Inns", required = true)
	public int innings;
	
	@CsvBindByName(column = "NO", required = true)
	public int no;
	
	@CsvBindByName(column = "Runs", required = true)
	public int runs;
	
	@CsvBindByName(column = "HS", required = true)
	public int highestScore;
	
	@CsvBindByName(column = "Avg", required = true)
	public double avg;
	
	@CsvBindByName(column = "BF", required = true)
	public int bf;
	
	@CsvBindByName(column = "SR", required = true)
	public double strikeRtae;
	
	@CsvBindByName(column = "100", required = true)
	public int hundreds;
	
	@CsvBindByName(column = "50", required = true)
	public int fifties;
	
	@CsvBindByName(column = "4s", required = true)
	public int fours;
	
	@CsvBindByName(column = "6s", required = true)
	public int sixes;

}
