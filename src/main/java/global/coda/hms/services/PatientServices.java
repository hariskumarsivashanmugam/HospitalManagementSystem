/*
 *
 */
package global.coda.hms.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.constants.ApplicationConstants;
import global.coda.hms.delegate.PatientDelegate;
import global.coda.hms.models.Patient;
import global.coda.hms.models.ResponseObject;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientServices.
 */
/**
 * @author GS
 */
@Path("/patients")
public class PatientServices {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(PatientServices.class);

	/** The patient delegate. */
	private PatientDelegate patientDelegate = new PatientDelegate();

	/** The patient response. */
	private ResponseObject<Patient> patientResponse = new ResponseObject<
	    Patient>();

	/** The string response. */
	private ResponseObject<String> stringResponse = new ResponseObject<String>();

	/**
	 * Read patient.
	 *
	 * @param patientId the patient id
	 * @return the response object
	 * @throws Exception the exception
	 * @throws NumberFormatException the number format exception
	 */
	@GET
	@Path("readPatient/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseObject<Patient> readPatient(@PathParam("id") int patientId)
	    throws Exception {
		logger.traceEntry(Integer.toString(patientId));
		Patient patient = patientDelegate.readPatient(patientId);
		patientResponse.setStatusCode(ApplicationConstants.STATUS_200);
		patientResponse.setData(patient);
		logger.traceExit(patientResponse);
		return patientResponse;
	}

	/**
	 * Creates the patient.
	 *
	 * @param patient the patient
	 * @return the response object
	 * @throws Exception the exception
	 */
	@POST
	@Path("createPatient")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObject<Patient> createPatient(Patient patient)
	    throws Exception {
		logger.traceEntry(patient.toString());
		patientResponse.setStatusCode(ApplicationConstants.STATUS_200);
		patientResponse.setData(patientDelegate.createPatient(patient));
		logger.traceExit(patientResponse);
		return patientResponse;
	}

	/**
	 * Update patient.
	 *
	 * @param patient the patient
	 * @return the response object
	 * @throws Exception the exception
	 */
	@PUT
	@Path("updatePatient")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObject<Patient> updatePatient(Patient patient)
	    throws Exception {
		logger.traceEntry(patient.toString());
		patientResponse.setStatusCode(ApplicationConstants.STATUS_200);
		patientResponse.setData(patientDelegate.updatePatient(patient));
		logger.traceExit(patientResponse);
		return patientResponse;

	}

	/**
	 * Delete patient.
	 *
	 * @param patientId the patient id
	 * @return the response object
	 * @throws Exception the exception
	 * @throws NumberFormatException the number format exception
	 */
	@DELETE
	@Path("deletePatient/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObject<String> deletePatient(@PathParam("id") int patientId)
	    throws Exception {
		logger.traceEntry(Integer.toString(patientId));
		stringResponse.setStatusCode(ApplicationConstants.STATUS_200);
		stringResponse.setData(patientDelegate.deletePatient(patientId));
		logger.traceExit(stringResponse);
		return stringResponse;
	}
}
