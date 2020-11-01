package IPLAnalyzer;

public class BestBattingAndBowlingComparator implements java.util.Comparator<IPLDAO>{
	
	@Override
    public int compare(IPLDAO ipldao1, IPLDAO ipldao2) {
        return (int) ((ipldao1.battingAverage-(1d/ipldao1.averageOfBowler))-
                (ipldao2.battingAverage-(1d/ipldao2.averageOfBowler)));
    }

}
