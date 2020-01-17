/*
 *
 */
package global.coda.hms.helpers;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.constants.ApplicationConstants;
import global.coda.hms.dao.PatientDao;
import global.coda.hms.exceptions.BusinessException;
import global.coda.hms.exceptions.PatientNotFound;
import global.coda.hms.exceptions.SystemException;
import global.coda.hms.models.Patient;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientHelper.
 */
public class PatientHelper {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(PatientHelper.class);

	/** The patient dao. */
	private PatientDao patientDao = new PatientDao();

	/**
	 * Read patient helper.
	 *
	 * @param patientId the patient id
	 * @return the patient
	 * @throws Exception the exception
	 */
	public Patient readPatient(int patientId)
	    throws Exception {
		logger.traceEntry(Integer.toString(patientId));
		Patient patient;
		try {
			patient = patientDao.readPatientDao(patientId);
			if (patient == null) {
				throw new PatientNotFound(ApplicationConstants.PATIENT_NOT_FOUND);
			}
			logger.traceExit(patient);
			return patient;
		} catch (ClassNotFoundException e) {
			throw new SystemException(e);
		} catch (SQLException e) {
			throw new SystemException(e);
		} catch (PatientNotFound e) {
			throw new BusinessException(e);
		}

	}

	/**
	 * Creates the patient helper.
	 *
	 * @param patient the patient
	 * @return the patient
	 * @throws SystemException the system exception
	 * @throws Exception the exception
	 */
	public Patient createPatient(Patient patient)
	    throws SystemException,
	    Exception {
		logger.traceEntry(patient.toString());
		Patient patients;
		try {
			patients = patientDao.createPatientDao(patient);
			logger.traceExit(patients);
			return patients;
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Update patient helper.
	 *
	 * @param patient the patient
	 * @return the patient
	 * @throws Exception the exception
	 */
	public Patient updatePatient(Patient patient)
	    throws Exception {
		logger.traceEntry(patient.toString());
		Patient patients;
		try {
			patients = patientDao.updatePatientDao(patient);
			if (patients == null) {
				throw new PatientNotFound(ApplicationConstants.PATIENT_NOT_FOUND);
			}
			logger.traceExit(patients);
			return patients;
		} catch (ClassNotFoundException e) {
			throw new SystemException(e);
		} catch (SQLException e) {
			throw new SystemException(e);
		} catch (PatientNotFound e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Delete patient helper.
	 *
	 * @param patientId the patient id
	 * @return the string
	 * @throws SystemException the system exception
	 * @throws Exception the exception
	 */
	public String deletePatient(int patientId)
	    throws SystemException,
	    Exception {
		logger.traceEntry(Integer.toString(patientId));
		try {
			String patients = patientDao.deletePatientDao(patientId);
			logger.traceExit(patients);
			return patients;
		} catch (SQLException e) {
			throw new SystemException(e);
		}

	}

}
