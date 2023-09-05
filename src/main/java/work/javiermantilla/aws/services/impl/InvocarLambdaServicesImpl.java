package work.javiermantilla.aws.services.impl;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

import work.javiermantilla.aws.dto.ResponseBodyDTO;
import work.javiermantilla.aws.exception.TechnicalException;
import work.javiermantilla.aws.services.InvocarLambdaServices;
import work.javiermantilla.aws.util.ConstantsLambda;
import work.javiermantilla.aws.util.EnvironmentVariablesUtil;

public class InvocarLambdaServicesImpl implements InvocarLambdaServices {

	private static final Logger LOGGER = LogManager.getLogger(InvocarLambdaServicesImpl.class);

	@Override
	public ResponseBodyDTO invocarLambda(String payload) throws TechnicalException {
		LOGGER.info("payload: {}", payload);
		String enviroment = EnvironmentVariablesUtil.getVariableValue(ConstantsLambda.ENV_AWS_ENVIRONMENT);
		AWSLambda client = AWSLambdaClient.builder().withRegion(ConstantsLambda.ENV_AWS_LAMBDA_REGION).build();
		if (enviroment != null && enviroment.equalsIgnoreCase("0")) {
			client = AWSLambdaClient.builder().withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
					EnvironmentVariablesUtil.getEnvironmentVariableValue(ConstantsLambda.ENV_AWS_LAMBDA_ACCESSKEY),
					EnvironmentVariablesUtil.getEnvironmentVariableValue(ConstantsLambda.ENV_AWS_LAMBDA_SECRETKEY))))
					.withRegion(
							EnvironmentVariablesUtil.getEnvironmentVariableValue(ConstantsLambda.ENV_AWS_LAMBDA_REGION))
					.build();
		}
		InvokeRequest request = new InvokeRequest();
		request.withFunctionName(
				EnvironmentVariablesUtil.getEnvironmentVariableValue(ConstantsLambda.ENV_AWS_LAMBDA_ARN))
				.withPayload(payload);
		InvokeResult invoke = client.invoke(request);
		LOGGER.info("Resultado de la invocacion: {}", invoke);
		String converted = "{error}";
		try {
			converted = new String(invoke.getPayload().array(), StandardCharsets.UTF_8);
			LOGGER.info("Resultado JSON convertida: {}", converted);
		} catch (Exception e) {
			LOGGER.error("Error al convertir el response de la lambda {}", invoke.getPayload(), e);
		}
		return new ResponseBodyDTO("200", converted);
	}

}
