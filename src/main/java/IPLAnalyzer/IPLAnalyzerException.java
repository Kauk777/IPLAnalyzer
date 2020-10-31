package IPLAnalyzer;

public class IPLAnalyzerException extends Exception {
	enum ExceptionType {
		IPL_FILE_PROBLEM, UNABLE_TO_PARSE, IPL_DELIMITER_HEADER_PROBLEM, NO_IPL_DATA
	}

	ExceptionType type;

	public IPLAnalyzerException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

}
