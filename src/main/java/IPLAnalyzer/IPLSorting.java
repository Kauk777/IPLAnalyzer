package IPLAnalyzer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IPLSorting {
	
	static Map<SortingFields, Comparator<IPLDAO>> iplMap = new HashMap<>();
	
	 public IPLSorting() {
		 
	 }
	 
	 public Comparator<IPLDAO> setSortByFields(SortingFields sortByFields) {
		 this.iplMap.put(SortingFields.MAX_4s_AND_6s, Comparator.comparing(census -> (census.fours *4+census
	                .sixes *6), Comparator.reverseOrder()));
		 Comparator<IPLDAO> comparator = iplMap.get(sortByFields);
	        return comparator; 
	 }
}
