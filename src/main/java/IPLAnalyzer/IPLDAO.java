package IPLAnalyzer;

public class IPLDAO {
	
	public String player;
	public int matches;
	public int runs;
	public double battingAverage;
	public double strikeRate;
	public int fours;
	public int sixes;
	public int ballFaced;
	
	public IPLDAO(IPLDAO iplCSV) {
		
	}
	
	public IPLDAO(IPLMostRunsCSV data) {
		player=data.player;
		matches=data.matches;
		runs=data.runs;
		battingAverage=data.battingAverage;
		strikeRate=data.strikeRate;
		fours=data.fours;
		sixes=data.sixes;
		ballFaced=data.ballFaced;
	}
	
	@Override
	public String toString() {
		return "IPLDAO [player=" + player + ", matches=" + matches + ", runs=" + runs + ", battingAverage="
				+ battingAverage + ", strikeRate=" + strikeRate + ", fours=" + fours + ", sixes=" + sixes
				+ ", ballFaced=" + ballFaced + "]";
	}

	public Object getData(IPLAnalyzer.Innings innings) {
		if(innings.equals(IPLAnalyzer.Innings.BATTING))
			return new IPLMostRunsCSV(player,matches,battingAverage,runs,strikeRate,fours,sixes);
		return null;
	}

}
