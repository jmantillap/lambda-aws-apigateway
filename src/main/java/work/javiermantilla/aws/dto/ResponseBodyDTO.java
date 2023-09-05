package work.javiermantilla.aws.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase de respuesta de la solicitud al frontend
 * @author javier.mantilla SP 6. 
 * @since Ago/2023
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message"})
public class ResponseBodyDTO implements Serializable {

	private static final long serialVersionUID = -3313743002573752695L;
	private String code;
	private String message;
	

	public ResponseBodyDTO() {
		super();
	}
	
	
	public ResponseBodyDTO(String code,String message) {
		super();
		this.message = message;
		this.code=code;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	
	
}
