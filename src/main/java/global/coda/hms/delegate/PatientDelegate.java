/*
 *
 */
package global.coda.hms.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.helpers.PatientHelper;
import global.coda.hms.models.Patient;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientDelegate.
 */
public class PatientDelegate {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(PatientDelegate.class);

	/** The patient helper. */
	private PatientHelper patientHelper = new PatientHelper();

	/**
	 * Read patient delegate.
	 *
	 * @param patientId the patient id
	 * @return the patient
	 * @throws Exception the exception
	 */
	public Patient readPatient(int patientId)
	    throws Exception {
		logger.traceEntry(Integer.toString(patientId));
		Patient patient = patientHelper.readPatient(patientId);
		logger.traceExit(patient);
		return patient;
	}

	/**
	 * Creates the patient delegate.
	 *
	 * @param patient the patient
	 * @return the patient
	 * @throws Exception exception
	 */
	public Patient createPatient(Patient patient)
	    throws Exception {
		logger.traceEntry(patient.toString());
		Patient patients = patientHelper.createPatient(patient);
		logger.traceExit(patients);
		return patients;
	}

	/**
	 * Update patient delegate.
	 *
	 * @param patient the patient
	 * @return the patient
	 * @throws Exception the exception
	 */
	public Patient updatePatient(Patient patient)
	    throws Exception {
		logger.traceEntry(patient.toString());
		Patient patients = patientHelper.updatePatient(patient);
		logger.traceExit(patients);
		return patients;
	}

	/**
	 * Delete patient delegate.
	 *
	 * @param patientId the patient id
	 * @return the string
	 * @throws Exception exception
	 */
	public String deletePatient(int patientId)
	    throws Exception {
		logger.traceEntry(Integer.toString(patientId));
		String patients = patientHelper.deletePatient(patientId);
		logger.traceExit(patients);
		return patients;
	}

}
