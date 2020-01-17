/*
 *
 */
package global.coda.hms.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import global.coda.hms.constants.ApplicationConstants;
import global.coda.hms.exceptions.BusinessException;
import global.coda.hms.models.ResponseObject;

// TODO: Auto-generated Javadoc
/**
 * The Class BusinessExceptionMapper.
 */
@Provider
public class BusinessExceptionMapper extends Exception implements
    ExceptionMapper<BusinessException> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2917800489848608230L;

	/**
	 * To response.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	@Override
	public Response toResponse(BusinessException exception) {
		ResponseObject<String> response = new ResponseObject<String>();
		response.setStatusCode(ApplicationConstants.FOUR_HUNDRED);
		response.setData(exception.getMessage());
		return Response.status(ApplicationConstants.FOUR_HUNDRED).type(
		    MediaType.APPLICATION_JSON).entity(
		        response).build();
	}

}
