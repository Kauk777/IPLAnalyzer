package IPLAnalyzer;

public class AllRounderComparator implements java.util.Comparator<IPLDAO> {
	@Override
    public int compare(IPLDAO ipldao1, IPLDAO ipldao2) {
        return ((ipldao1.runs*(ipldao1.wicketsTaken))-
                (ipldao2.runs*(ipldao2.wicketsTaken)));
    }

}
