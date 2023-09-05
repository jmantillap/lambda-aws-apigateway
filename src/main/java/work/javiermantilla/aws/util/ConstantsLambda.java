package work.javiermantilla.aws.util;

/**
 * Clase Constantes SICO
 * @author javier.mantilla SP 6. 
 * @since Ago/2023
 */
public class ConstantsLambda {

	private ConstantsLambda() {
		throw new AssertionError();
	}
	
	/*Variables de entorno*/
	public static final String ENV_AWS_ENVIRONMENT = "ENV_AWS_ENVIRONMENT"; // cuando el valor es = 0, revisa el accesskey y secretkey
	public static final String ENV_AWS_LAMBDA_ACCESSKEY = "ENV_AWS_LAMBDA_ACCESSKEY";
	public static final String ENV_AWS_LAMBDA_SECRETKEY = "ENV_AWS_LAMBDA_SECRETKEY";
	public static final String ENV_AWS_LAMBDA_REGION = "ENV_AWS_LAMBDA_REGION";	
	public static final String ENV_AWS_LAMBDA_ARN= "ENV_AWS_LAMBDA_ARN";
	
	public static final String CONTENT_TYPE= "Content-Type";
	public static final String APPLICATION_JSON= "application/json";
	
	
	
}
