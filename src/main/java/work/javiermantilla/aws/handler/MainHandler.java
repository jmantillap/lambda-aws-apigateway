package work.javiermantilla.aws.handler;


import java.util.HashMap;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

import work.javiermantilla.aws.dto.ErrorResponseDTO;
import work.javiermantilla.aws.dto.ResponseBodyDTO;
import work.javiermantilla.aws.services.InvocarLambdaServices;
import work.javiermantilla.aws.services.impl.InvocarLambdaServicesImpl;
import work.javiermantilla.aws.util.ConstantsLambda;
import work.javiermantilla.aws.util.MapperConversionUtil;

/**
 * Clase principal de la lambda
 * 
 * @author javier.mantilla SP 6.
 * @since Ago/2023
 */
public class MainHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

	private static final Logger LOGGER = LogManager.getLogger(MainHandler.class);

	private MapperConversionUtil mapperConversionUtil;
	private InvocarLambdaServices invocarLambdaServices;

	public MainHandler() {
		this.loadDependencies();		
	}

	@Override
	public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent input, Context context) {
		int responseCode = HttpStatus.SC_OK;
		LOGGER.info("20230816. Se recibe el evento de APIGatewayV2HTTPEvent.getBody(): {}", input.getBody());
		try {
			ResponseBodyDTO responseBody = this.invocarLambdaServices.invocarLambda(input.getBody());
			String responseBodyJson = mapperConversionUtil.getStringJsonFromDTO(responseBody);
			return new APIGatewayV2HTTPResponse(responseCode, this.getHeaders(), null, null, responseBodyJson, false);
		} catch (Exception e) {
			String bodyErrorJson = mapperConversionUtil
					.getStringJsonFromDTO(new ErrorResponseDTO(HttpStatus.SC_INTERNAL_SERVER_ERROR, e));
			return new APIGatewayV2HTTPResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, this.getHeaders(), null, null,
					bodyErrorJson, false);
		}

	}

	private HashMap<String, String> getHeaders() {
		HashMap<String, String> headers = new HashMap<>();
		headers.put(ConstantsLambda.CONTENT_TYPE, ConstantsLambda.APPLICATION_JSON);
		return headers;
	}

	public void loadDependencies() {
		this.mapperConversionUtil = new MapperConversionUtil();
		this.invocarLambdaServices = new InvocarLambdaServicesImpl();
	}
}
