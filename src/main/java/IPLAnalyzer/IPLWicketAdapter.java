package IPLAnalyzer;

import java.util.Map;


public class IPLWicketAdapter extends IPLAdapter {

	@Override
	public Map loadIPLData(IPLAnalyzer.Innings innings, String... csvFilePath) throws IPLAnalyzerException {
		return this.loadIPLData(IPLWickets.class,csvFilePath[0]);
	}

}
