/*
 *
 */
package global.coda.hms.delegate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.helpers.DoctorHelper;
import global.coda.hms.models.Doctor;
import global.coda.hms.models.DoctorPatientList;
import global.coda.hms.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorDelegate.
 */
public class DoctorDelegate {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(DoctorDelegate.class);

	/** The doctor helper. */
	private DoctorHelper doctorHelper = new DoctorHelper();

	/**
	 * Read doctor delegate.
	 *
	 * @param doctorId the doctor id
	 * @return the doctor
	 * @throws Exception exception
	 */
	public Doctor readDoctor(int doctorId)
	    throws Exception {
		logger.traceEntry(Integer.toString(doctorId));
		Doctor doctor = doctorHelper.readDoctor(doctorId);
		logger.traceExit(doctor);
		return doctor;
	}

	/**
	 * Creates the doctor delegate.
	 *
	 * @param doctor the doctor
	 * @return the doctor
	 * @throws Exception exception
	 */
	public Doctor createDoctor(Doctor doctor)
	    throws Exception {
		logger.traceEntry(doctor.toString());
		Doctor doctors = doctorHelper.createDoctor(doctor);
		logger.traceExit(doctors);
		return doctors;
	}

	/**
	 * Update doctor delegate.
	 *
	 * @param doctor the doctor
	 * @return the doctor
	 * @throws Exception exception
	 */
	public Doctor updateDoctor(Doctor doctor)
	    throws Exception {
		logger.traceEntry(doctor.toString());
		Doctor doctors = doctorHelper.updateDoctor(doctor);
		logger.traceExit(doctors);
		return doctors;
	}

	/**
	 * Delete doctor delegate.
	 *
	 * @param doctorId the doctor id
	 * @return the string
	 * @throws Exception exception
	 */
	public String deleteDoctor(int doctorId)
	    throws Exception {
		logger.traceEntry(Integer.toString(doctorId));
		String doctors = doctorHelper.deleteDoctor(doctorId);
		logger.traceExit(doctors);
		return doctors;
	}

	/**
	 * Track my patient doctor delegate.
	 *
	 * @param doctorId the doctor id
	 * @return the list
	 * @throws Exception exception
	 */
	public List<User> trackMyPatient(int doctorId)
	    throws Exception {
		logger.traceEntry(Integer.toString(doctorId));
		List<User> doctors = doctorHelper.trackMyPatient(doctorId);
		logger.traceExit(doctors);
		return doctors;
	}

	/**
	 * List doctor patients.
	 *
	 * @return the list
	 * @throws Exception exception
	 */
	public List<DoctorPatientList> listDoctorPatients()
	    throws Exception {
		logger.traceEntry();
		List<DoctorPatientList> doctors = doctorHelper.listDoctorPatients();
		logger.traceExit();
		return doctors;
	}
}
