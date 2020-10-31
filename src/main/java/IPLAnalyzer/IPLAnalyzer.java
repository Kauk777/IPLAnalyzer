package IPLAnalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import CSVBuilder.*;

public class IPLAnalyzer {
	
	List<IPLMostRunsCSV> iplCSVList=null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int loadIPLMostRunsCSV(String csvFilePath) throws IPLAnalyzerException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder=CSVBuildFactory.createCsvFile();
			iplCSVList = csvBuilder.getCsvList(reader,IPLMostRunsCSV.class);
		} catch (IOException e) {
			throw new IPLAnalyzerException(e.getMessage(),IPLAnalyzerException.ExceptionType.IPL_FILE_PROBLEM);	
		} catch(RuntimeException e) {
			throw new IPLAnalyzerException(e.getMessage(),IPLAnalyzerException.ExceptionType.IPL_DELIMITER_HEADER_PROBLEM);
		} catch (CSVBuilderException e) {
			e.printStackTrace();
		}
		return iplCSVList.size();
	}
}
