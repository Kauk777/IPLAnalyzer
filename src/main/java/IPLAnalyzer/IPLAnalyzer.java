package IPLAnalyzer;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;

import CSVBuilder.*;

public class IPLAnalyzer {
	
	enum Innings {
		BATTING, BOWLING
	}
	
	Innings inning;
	
	List<IPLMostRunsCSV> iplCSVList=null;

	public IPLAnalyzer(Innings inning) {
		this.inning=inning;
	}

	public IPLAnalyzer() {
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int loadIPLMostRunsCSV(String csvFilePath) throws IPLAnalyzerException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder=CSVBuildFactory.createCsvFile();
		    iplCSVList = csvBuilder.getCsvList(reader,IPLMostRunsCSV.class);
			return iplCSVList.size();
		} catch (IOException e) {
			throw new IPLAnalyzerException(e.getMessage(),IPLAnalyzerException.ExceptionType.IPL_FILE_PROBLEM);	
		} catch(Exception e) {
			throw new IPLAnalyzerException(e.getMessage(),IPLAnalyzerException.ExceptionType.IPL_DELIMITER_HEADER_PROBLEM);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String iplRunsSortedData(String fieldName, boolean reverse) throws IPLAnalyzerException {
		if(iplCSVList.size()==0 || iplCSVList==null)
			throw new IPLAnalyzerException("No IPL Runs Data",IPLAnalyzerException.ExceptionType.NO_IPL_DATA);
		Comparator myComparator=sortByField(fieldName);
		if(reverse)
			Collections.sort(iplCSVList,myComparator.reversed());
		else
			Collections.sort(iplCSVList,myComparator);
		String sortedIPLRunsData=new Gson().toJson(iplCSVList);
		return sortedIPLRunsData;
		
	}

	private Comparator<IPLMostRunsCSV> sortByField(String fieldName) {
		Comparator<IPLMostRunsCSV> comparator=new Comparator<IPLMostRunsCSV>() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public int compare(IPLMostRunsCSV obj1, IPLMostRunsCSV obj2) {
			try {
				Field field=IPLMostRunsCSV.class.getDeclaredField(fieldName);
				field.setAccessible(true);
				Comparable field1=(Comparable) field.get(obj1);
				Comparable field2=(Comparable) field.get(obj2);
				return field1.compareTo(field2);
			} catch (Exception e) {
				// if filedName is not proper then return 0
				e.printStackTrace();
				return 0;
			}
		}
		
	  };
		
		return comparator;
	}
	
}
