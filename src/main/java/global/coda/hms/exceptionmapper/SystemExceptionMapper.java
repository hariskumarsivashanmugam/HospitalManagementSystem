/*
 *
 */
package global.coda.hms.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import global.coda.hms.constants.ApplicationConstants;
import global.coda.hms.exceptions.SystemException;
import global.coda.hms.models.ResponseObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemExceptionMapper.
 */
@Provider
public class SystemExceptionMapper extends Exception implements ExceptionMapper<
    SystemException> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2849943181098489635L;

	/**
	 * To response.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	@Override
	public Response toResponse(SystemException exception) {
		ResponseObject<String> response = new ResponseObject<String>();
		response.setStatusCode(ApplicationConstants.FIVE_HUNDRED);
		response.setData("Oops!. Something Wrong");
		return Response.status(ApplicationConstants.FIVE_HUNDRED).type(
		    MediaType.APPLICATION_JSON).entity(
		        response).build();
	}

}
