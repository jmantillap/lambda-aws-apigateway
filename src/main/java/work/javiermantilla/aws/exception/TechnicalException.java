package work.javiermantilla.aws.exception;

/**
 * Clase de Error
 * @author javier.mantilla SP 6. 
 * @since Ago/2023
 */
public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = -6635960527844483637L;

	

	public TechnicalException() {
		super();
	
	}
	
	public TechnicalException(String message) {
		super(message);	
	}
	
	public TechnicalException(String message, Throwable throwable) {
		super(message, throwable);
	
	}	
	
}
