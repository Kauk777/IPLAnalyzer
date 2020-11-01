package IPLAnalyzer;

import java.util.Map;

import IPLAnalyzer.IPLAnalyzer.Innings;

public class IPLRunsAdapter extends IPLAdapter {

	@Override
	public Map loadIPLData(Innings innings, String... csvFilePath) throws IPLAnalyzerException {
		try {
            Map<String, IPLDAO> censusStateMap = super.loadIPLData(IPLMostRunsCSV.class, csvFilePath[0]);
            return censusStateMap;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IPLAnalyzerException(e.getMessage(), IPLAnalyzerException.ExceptionType.IPL_FILE_PROBLEM);
        }
	}
	
}
