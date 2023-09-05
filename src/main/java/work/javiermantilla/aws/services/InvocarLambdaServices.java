package work.javiermantilla.aws.services;

import work.javiermantilla.aws.dto.ResponseBodyDTO;
import work.javiermantilla.aws.exception.TechnicalException;

public interface InvocarLambdaServices {

	ResponseBodyDTO invocarLambda(String payload) throws TechnicalException ;
}
