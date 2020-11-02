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
	public int hundreds;
	public int fifties;
	public double averageOfBowler;
	public double strikeRatesOfBowler;
	public double economyOfBowler;
	public int bowlersWith4Wickets;
	public int bowlersWith5Wickets;
	public int wicketsTaken;

	public IPLDAO(IPLDAO iplCSV) {

	}

	public IPLDAO(IPLMostRunsCSV data) {
		player = data.player;
		matches = data.matches;
		runs = data.runs;
		battingAverage = data.battingAverage;
		strikeRate = data.strikeRate;
		fours = data.fours;
		sixes = data.sixes;
		ballFaced = data.ballFaced;
		hundreds=data.hundreds;
		fifties=data.fifties;
	}
	
	public IPLDAO(IPLWickets data) {
        player = data.playerName;
        averageOfBowler = data.avgOfBowler;
        strikeRatesOfBowler = data.strikeRateOfBowler;
        economyOfBowler = data.economyOfBowler;
        bowlersWith4Wickets = data.wickets4Taken;
        bowlersWith5Wickets = data.wickets5Taken;
        wicketsTaken = data.wicketsTaken;
    }

	
	
	public IPLDAO(String abc, int i, double v) {
		
	}

	public Object getData(IPLAnalyzer.Innings innings) {
		if (innings.equals(IPLAnalyzer.Innings.BATTING))
			return new IPLMostRunsCSV(player, matches, battingAverage, runs, strikeRate, fours, sixes,hundreds,fifties);
		if (innings.equals(IPLAnalyzer.Innings.BOWLING))
            return new IPLWickets(player, averageOfBowler, strikeRatesOfBowler, economyOfBowler,
                    bowlersWith4Wickets, bowlersWith5Wickets, wicketsTaken);
		return null;
	}

	@Override
	public String toString() {
		return "IPLDAO [player=" + player + ", matches=" + matches + ", runs=" + runs + ", battingAverage="
				+ battingAverage + ", strikeRate=" + strikeRate + ", fours=" + fours + ", sixes=" + sixes
				+ ", ballFaced=" + ballFaced + ", averageOfBowler=" + averageOfBowler + ", strikeRatesOfBowler="
				+ strikeRatesOfBowler + ", economyOfBowler=" + economyOfBowler + ", bowlersWith4Wickets="
				+ bowlersWith4Wickets + ", bowlersWith5Wickets=" + bowlersWith5Wickets + ", wicketsTaken="
				+ wicketsTaken + "]";
	}
	

}
