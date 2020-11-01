package IPLAnalyzer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IPLSorting {

	static Map<SortingFields, Comparator<IPLDAO>> iplMap = new HashMap<>();

	public IPLSorting() {

	}

	public Comparator<IPLDAO> setSortByFields(SortingFields sortByFields) {
		this.iplMap.put(SortingFields.MAX_4s_AND_6s,
				Comparator.comparing(census -> (census.fours * 4 + census.sixes * 6), Comparator.reverseOrder()));
		this.iplMap.put(SortingFields.MAX_4s_AND_6s_WITH_BEST_STRIKING_RATE, Comparator.comparing(
				census -> ((census.fours * 4 + census.sixes * 6)) / (census.ballFaced), Comparator.reverseOrder()));
		Comparator<IPLDAO> comparatorForBattingAverage = Comparator.comparing(compare -> compare.battingAverage);
		this.iplMap.put(SortingFields.BEST_AVERAGE_WITH_STRIKE_RATE,
				comparatorForBattingAverage.thenComparing(compare -> compare.strikeRate).reversed());
		Comparator<IPLDAO> comparatorForRuns = Comparator.comparing(compare -> compare.runs);
		this.iplMap.put(SortingFields.MAX_RUNS_WITH_BEST_AVERAGE,
				comparatorForRuns.thenComparing(compare -> compare.battingAverage).reversed());
		this.iplMap.put(SortingFields.BOWLING_AVERAGE, Comparator.comparing(census -> census.averageOfBowler));
		this.iplMap.put(SortingFields.TOP_BOWLING_STRIKING_RATES, Comparator.comparing(census ->
        census.strikeRatesOfBowler));
		this.iplMap.put(SortingFields.TOP_BOWLING_ECONOMY_RATES, Comparator.comparing(census ->
        census.economyOfBowler));
		Comparator<IPLDAO> comparatorForBowler = Comparator.comparing(census -> census.bowlersWith4Wickets
                +census.bowlersWith5Wickets);
        this.iplMap.put(SortingFields.TOP_BOWLING_STRIKING_RATES_WITH_MOST_4Ws_AND_5Ws,
                comparatorForBowler.thenComparing(compare -> compare.strikeRatesOfBowler).reversed());
        Comparator<IPLDAO> comparatorForBestAverageBowler = Comparator.comparing(compare -> compare.averageOfBowler);
        this.iplMap.put(SortingFields.GREAT_AVERAGE_WITH_BEST_STRIKING_RATE,
                comparatorForBestAverageBowler.thenComparing(compare -> compare.strikeRatesOfBowler));
        this.iplMap.put(SortingFields.GREAT_AVERAGE_WITH_MOST_WICKETS,
                comparatorForBestAverageBowler.thenComparing(compare -> compare.wicketsTaken));
        this.iplMap.put(SortingFields.BEST_BATTING_BOWLING_AVERAGE,
                new BestBattingAndBowlingComparator().reversed());
		Comparator<IPLDAO> comparator = iplMap.get(sortByFields);
		return comparator;
	}
}
