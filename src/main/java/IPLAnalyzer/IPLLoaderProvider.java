package IPLAnalyzer;

public class IPLLoaderProvider {
	
	public static IPLAdapter getClassAdapterObject(IPLAnalyzer.Innings innings) {
        if (innings.equals(innings.BATTING)) {
            return new IPLRunsAdapter();
        }
        return null;
    }

}
