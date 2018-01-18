package guru.springfamework.services;


public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {		
	}
	
	public ResourceNotFoundException (String massage) {
		super(massage);
	}

	public ResourceNotFoundException (String massage, Throwable cause) {
		super(massage, cause);
	}
	
	public ResourceNotFoundException (Throwable cause) {
		super(cause);
	}
	
	public ResourceNotFoundException (String massage, Throwable cause, boolean enableSupersession, boolean writableStackTrace) {
		super(massage, cause, enableSupersession, writableStackTrace);
	}
}
