package work.javiermantilla.aws.dto;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Clase de respuesta de error
 * @author javier.mantilla SP 6. 
 * @since Ago/2023
 */
public class ErrorResponseDTO implements Serializable {
	private static final long serialVersionUID = -5893336408895833988L;
	
	private static final Logger LOGGER = LogManager.getLogger(ErrorResponseDTO.class);

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date timestamp;
	private int code;

	private String status;

	private String message;

	private String stackTrace;

	private Object data;	

	public ErrorResponseDTO() {
		timestamp = new Date();
	}
	
	public ErrorResponseDTO(int httpStatus, String message) {
		this();

		this.code = httpStatus;
		this.status = HttpStatus.getStatusText(httpStatus);
		this.message = message;
	}

	public ErrorResponseDTO(int httpStatus, String message, String stackTrace) {
		this(httpStatus, message);
		this.stackTrace = stackTrace;
	}

	public ErrorResponseDTO(int httpStatus, String message, String stackTrace, Object data) {
		this(httpStatus, message, stackTrace);
		this.data = data;
	}
	public ErrorResponseDTO(int httpStatus, String message, Throwable error) {
		this();

		this.code = httpStatus;
		this.status = HttpStatus.getStatusText(httpStatus);
		this.message = message;
		
		StringWriter stringWriter = new StringWriter();
	    PrintWriter printWriter = new PrintWriter(stringWriter);
	    error.printStackTrace(printWriter);
	    this.stackTrace= stringWriter.toString();		
		
	}
	
	public ErrorResponseDTO(int httpStatus,Throwable error) {
		this();
		this.code = httpStatus;
		this.status = HttpStatus.getStatusText(httpStatus);
		this.message = error.getMessage();
		
		StringWriter stringWriter = new StringWriter();
	    PrintWriter printWriter = new PrintWriter(stringWriter);
	    error.printStackTrace(printWriter);
	    this.stackTrace= stringWriter.toString();	    
	    LOGGER.error(this.stackTrace);
		
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public Object getData() {
		return data;
	}
	
	

}
