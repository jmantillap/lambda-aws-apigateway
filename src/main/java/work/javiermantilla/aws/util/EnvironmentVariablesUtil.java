package work.javiermantilla.aws.util;

import java.util.Optional;

import work.javiermantilla.aws.exception.ErrorCode;
import work.javiermantilla.aws.exception.TechnicalException;

/**
 * Clase Para obtener los valores de variables de entorno
 * @author javier.mantilla SP 6. 
 * @since Ago/2023
 */
public class EnvironmentVariablesUtil {		
	
	private EnvironmentVariablesUtil() {		
	}	
	/**
	 * Metodo para obtener la variable de entorno
	 * @param nombre nombre de la variable
	 * @return valor de la variable
	 * @throws TechnicalException error
	 * @author javier.mantilla
	 * @since Ago/2023. Sprint 6 HU # 121258
	 */
	public static String getEnvironmentVariableValue(String name) throws TechnicalException {		
		return Optional.ofNullable(getVariableValue(name))
				.orElseThrow(() -> new TechnicalException(
						ErrorCode.TCH_NOT_DEFINED_ENV_VARIABLE.getMessage() + ": " + name));
	}	
	
	
	public static final String getVariableValue(String name){		
		return System.getenv(name);
	}
		

}
