package IPLAnalyzer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;

import CSVBuilder.*;

public class IPLAnalyzer {
	
	private Innings inning;
	
	enum Innings {
		BATTING, BOWLING
	}
	
	
	Map<String,IPLDAO> iplMap;
	IPLSorting iplSort;
	List<IPLMostRunsCSV> iplCSVList=null;
	private IPLAdapter iplAdapter;

	public IPLAnalyzer(Innings inning) {
		//this.inning=inning;
		iplSort=new IPLSorting();
	}

	public IPLAnalyzer() {
		
	}
	
	public void setIPLAdapter(IPLAdapter adapter) {
        this.iplAdapter =adapter;
    }
	
	 public int loadIPLData(Innings innings, String... csvFilePath) throws IPLAnalyzerException {
		 this.inning = innings;
	     this.iplMap = this.iplAdapter.loadIPLData(innings, csvFilePath);
	     return this.iplMap.size();
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
	
	
	public void writingJsonFile(String gson, String filePath) {
		try(FileWriter writer=new FileWriter(filePath);) {
			writer.write(gson);
		} catch(IOException e) {
			e.printStackTrace();
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
	
	 

	public String sortIPLDataFields(SortingFields fields) {
		Comparator<IPLDAO> iplComparator = iplSort.setSortByFields(fields);
		ArrayList getDTO = this.iplMap.values().stream()
                .sorted(iplComparator)
                .collect(Collectors.toCollection(ArrayList::new));
		String sortIplToJson = new Gson().toJson(getDTO);
        return sortIplToJson;
	}

    public static IPLAdapter getAdapterObject(Innings innings) {
		return IPLLoaderProvider.getClassAdapterObject(innings);
	}
	
}
