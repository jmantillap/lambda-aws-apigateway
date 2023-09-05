package work.javiermantilla.aws.exception;

/**
 * Codigos de error controlado.
 * @author javier.mantilla SP 6. 
 * @since Ago/2023
 */
public enum ErrorCode {

	/////////////////////////////////
	// Tech Exceptions Codes
	/////////////////////////////////
	TCH_NOT_DEFINED_ENV_VARIABLE("TCH_NOT_DEFINED_ENV_VARIABLE", "Variable de entorno no definida  "),
	TCH_EXCEPTION_OBJECT_MAPPING("TCH_EXCEPTION_OBJECT_MAPPING", "Ocurrió un error técnico al mapear los datos "),
	TCH_ERROR_DATABASE("TCH_ERROR_DATABASE", "Error de Base de datos ."),		
	;

	private String code;

	private String message;

	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}


	public String code() {
		return code;
	}


	public String getMessage() {

		return message;
	}

}

