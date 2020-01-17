/*
 *
 */
package global.coda.hms.services;

import java.util.List;

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
import global.coda.hms.delegate.DoctorDelegate;
import global.coda.hms.models.Doctor;
import global.coda.hms.models.DoctorPatientList;
import global.coda.hms.models.ResponseObject;
import global.coda.hms.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorServices.
 */
@Path("/doctors")
public class DoctorServices {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(DoctorServices.class);

	/** The doctor delegate. */
	private DoctorDelegate doctorDelegate = new DoctorDelegate();

	/** The doctor response. */
	private ResponseObject<Doctor> doctorResponse = new ResponseObject<Doctor>();

	/** The string response. */
	private ResponseObject<String> stringResponse = new ResponseObject<String>();

	/** The list response. */
	private ResponseObject<List<User>> listResponse = new ResponseObject<List<
	    User>>();

	/** The response. */
	private ResponseObject<List<DoctorPatientList>> response = new ResponseObject<
	    List<
	        DoctorPatientList>>();

	/**
	 * Read doctor.
	 *
	 * @param doctorId the doctor id
	 * @return the response object
	 * @throws Exception the exception
	 */
	@GET
	@Path("readDoctor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseObject<Doctor> readDoctor(@PathParam("id") String doctorId)
	    throws Exception {
		logger.traceEntry(doctorId);
		Doctor doctor = doctorDelegate.readDoctor(Integer.parseInt(
		    doctorId));
		doctorResponse.setStatusCode(ApplicationConstants.STATUS_200);
		doctorResponse.setData(doctor);
		logger.traceExit(doctorResponse);
		return doctorResponse;
	}

	/**
	 * Creates the doctor.
	 *
	 * @param doctor the doctor
	 * @return the response object
	 * @throws Exception the exception
	 */
	@POST
	@Path("createDoctor")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObject<Doctor> createDoctor(Doctor doctor)
	    throws Exception {
		logger.traceEntry(doctor.toString());
		doctorResponse.setStatusCode(ApplicationConstants.STATUS_200);
		doctorResponse.setData(doctorDelegate.createDoctor(doctor));
		logger.traceExit(doctorResponse);
		return doctorResponse;
	}

	/**
	 * Update doctor.
	 *
	 * @param doctor the doctor
	 * @return the response object
	 * @throws Exception the exception
	 */
	@PUT
	@Path("updateDoctor")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObject<Doctor> updateDoctor(Doctor doctor)
	    throws Exception {
		logger.traceEntry(doctor.toString());
		doctorResponse.setStatusCode(ApplicationConstants.STATUS_200);
		doctorResponse.setData(doctorDelegate.updateDoctor(doctor));
		logger.traceExit(doctorResponse);
		return doctorResponse;

	}

	/**
	 * Delete patient.
	 *
	 * @param doctorId the doctor id
	 * @return the response object
	 * @throws Exception the exception
	 */
	@DELETE
	@Path("deleteDoctor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObject<String> deletePatient(@PathParam("id") String doctorId)
	    throws Exception {
		logger.traceEntry(doctorId);
		stringResponse.setStatusCode(ApplicationConstants.STATUS_200);
		stringResponse.setData(doctorDelegate.deleteDoctor(Integer.parseInt(
		    doctorId)));
		logger.traceExit(stringResponse);
		return stringResponse;
	}

	/**
	 * Track patient.
	 *
	 * @param doctorId the doctor id
	 * @return the response object
	 * @throws Exception the exception
	 */
	@GET
	@Path("doctor/{id}/patient")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseObject<List<User>> trackPatient(
	    @PathParam("id") String doctorId)
	    throws Exception {
		logger.traceEntry(doctorId);
		listResponse.setStatusCode(ApplicationConstants.STATUS_200);
		listResponse.setData(doctorDelegate.trackMyPatient(Integer
		    .parseInt(doctorId)));
		logger.traceExit(listResponse);
		return listResponse;
	}

	/**
	 * List doctor patients.
	 *
	 * @return the response object
	 * @throws Exception the exception
	 */
	@GET
	@Path("doctor/patient")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseObject<List<DoctorPatientList>> listDoctorPatients()
	    throws Exception {
		logger.traceEntry();
		response.setStatusCode(ApplicationConstants.STATUS_200);
		response.setData(doctorDelegate.listDoctorPatients());
		logger.traceExit();
		return response;
	}

}
