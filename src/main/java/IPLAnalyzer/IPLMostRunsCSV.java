package IPLAnalyzer;

import java.util.Comparator;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV  {
	
	

	@CsvBindByName(column = "POS", required = true)
	public int position;
	
	@CsvBindByName(column = "PLAYER", required = true)
	public String player;
	
	@CsvBindByName(column = "MAT", required = true)
	public int matches;
	
	@CsvBindByName(column = "Inns", required = true)
	public int innings;
	
	@CsvBindByName(column = "NO", required = true)
	public int notOut;
	
	@CsvBindByName(column = "Runs", required = true)
	public int runs;
	
	@CsvBindByName(column = "HS", required = true)
	public String highestScore;
	
	@CsvBindByName(column = "Avg", required = true)
	public double battingAverage;
	
	@CsvBindByName(column = "BF", required = true)
	public int ballFaced;
	
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

	@Override
	public String toString() {
		return "IPLMostRunsCSV [position=" + position + ", player=" + player + ", matches=" + matches + ", innings="
				+ innings + ", notOut=" + notOut + ", runs=" + runs + ", highestScore=" + highestScore
				+ ", battingAverage=" + battingAverage + ", ballFaced=" + ballFaced + ", strikeRtae=" + strikeRtae
				+ ", hundreds=" + hundreds + ", fifties=" + fifties + ", fours=" + fours + ", sixes=" + sixes + "]";
	}
	
	
	
	/*@Override
	public int compareTo(IPLMostRunsCSV iplData) {
		return this.avg.compareTo(iplData.avg);
	}
	
	class IPLAnalyserComparator implements Comparator<IPLMostRunsCSV> {
		public int compare(IPLMostRunsCSV obj1, IPLMostRunsCSV obj2) {
			return obj1.avg.compareTo(obj2.avg);
		}
		
	}*/

}
