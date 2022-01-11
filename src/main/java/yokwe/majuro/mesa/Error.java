package yokwe.majuro.mesa;

@SuppressWarnings("serial")
public class Error extends RuntimeException {
	public Error() {
		super();
	}
	public Error(String message) {
		super(message);
	}
	public Error(String message, Throwable throwable) {
		super(message, throwable);
	}
}
