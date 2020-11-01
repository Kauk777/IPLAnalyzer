package IPLAnalyzer;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

import CSVBuilder.*;

public abstract class IPLAdapter {

	public Map<String, IPLDAO> iplRunsMap;

	public IPLAdapter() {
		this.iplRunsMap = new HashMap<>();
	}

	public abstract Map loadIPLData(IPLAnalyzer.Innings innings, String... csvFilePath) throws IPLAnalyzerException;

	public <T>  Map loadIPLData(Class<T> iplClass, String csvFilePath) throws IPLAnalyzerException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuildFactory.createCsvFile();
			Iterator<T> iplCSVIterator = csvBuilder.getCsvIterator(reader, iplClass);
			Iterable<T> csvIterable = () -> iplCSVIterator;
			if (iplClass.getName().equals("IPLAnalyzer.IPLMostRunsCSV")) {
				StreamSupport.stream(csvIterable.spliterator(), false).map(IPLMostRunsCSV.class::cast)
						.forEach(iplRuns -> this.iplRunsMap.put(iplRuns.player, new IPLDAO(iplRuns)));

			}
			if (iplClass.getName().equals("IPLAnalyzer.IPLWickets")) {
                StreamSupport.stream(csvIterable.spliterator(),false)
                        .map(IPLWickets.class::cast)
                        .forEach(iplWickets -> this.iplRunsMap.put(iplWickets.playerName,  new IPLDAO(iplWickets)));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iplRunsMap;

	}
}
