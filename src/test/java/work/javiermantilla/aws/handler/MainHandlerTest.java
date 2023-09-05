package work.javiermantilla.aws.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.amazonaws.services.lambda.runtime.tests.annotations.Event;

import work.javiermantilla.aws.exception.TechnicalException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@TestInstance(Lifecycle.PER_CLASS)
class MainHandlerTest {

	private static final Logger LOGGER = LogManager.getLogger(MainHandlerTest.class);

	private MainHandler handler;
	
	private static Context context;
	
	
	@BeforeAll
	public void init() throws TechnicalException {		
		context = new TestContextLambda();
	}

	/**
	 * Rest real de pruebas para validar funcionalidades
	 * @param event evento
	 */
	
	@ParameterizedTest
	@Event(value = "events/apigateway-v2.json", type = APIGatewayV2HTTPEvent.class)
	@DisplayName("Prueba respuesta exitosa")
	void handleRequestTest(APIGatewayV2HTTPEvent event) {		
		handler= new MainHandler();
		String body="{\"email\":\"eliana.bravovesgaLambda@gmail.com\",\"idPlataforma\":250}";
		event.setBody(body);		
		APIGatewayV2HTTPResponse response = handler.handleRequest(event, context);
		LOGGER.info("CODIGO : response.getStatusCode() : {}",response.getStatusCode());
		LOGGER.info("response: {}",response.getBody());
		assertEquals(200, response.getStatusCode());
	}

}
