package exception;

@SuppressWarnings("serial")
public class DuplicateAirlineException extends RuntimeException {

	public DuplicateAirlineException(String message) {
		super(message);
	}

}
