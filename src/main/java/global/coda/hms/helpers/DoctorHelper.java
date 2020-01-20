/*
 *
 */
package global.coda.hms.helpers;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.constants.ApplicationConstants;
import global.coda.hms.dao.DoctorDao;
import global.coda.hms.exceptions.BusinessException;
import global.coda.hms.exceptions.DoctorNotFound;
import global.coda.hms.exceptions.SystemException;
import global.coda.hms.models.Doctor;
import global.coda.hms.models.DoctorPatientList;
import global.coda.hms.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorHelper.
 */
public class DoctorHelper {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(DoctorHelper.class);

	/** The doctor dao. */
	private DoctorDao doctorDao = new DoctorDao();

	/**
	 * Read doctor helper.
	 *
	 * @param doctorId the doctor id
	 * @return the doctor
	 * @throws SystemException the system exception
	 * @throws BusinessException the business exception
	 * @throws Exception the exception
	 */
	public Doctor readDoctor(int doctorId)
	    throws SystemException,
	    BusinessException,
	    Exception {
		logger.traceEntry(Integer.toString(doctorId));
		Doctor doctor;
		try {
			doctor = doctorDao.readDoctor(doctorId);
			if (doctor == null) {
				throw new DoctorNotFound(ApplicationConstants.DOCTOR_NOT_FOUND);
			}
			logger.traceExit(doctor);
			return doctor;
		} catch (SQLException e) {
			throw new SystemException(e);
		} catch (DoctorNotFound e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Creates the doctor helper.
	 *
	 * @param doctor the doctor
	 * @return the doctor
	 * @throws SystemException the system exception
	 * @throws Exception the exception
	 */
	public Doctor createDoctor(Doctor doctor)
	    throws SystemException,
	    Exception {
		logger.traceEntry(doctor.toString());
		try {
			Doctor doctors = doctorDao.createDoctor(doctor);
			logger.traceExit(doctor);
			return doctors;
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Update doctor helper.
	 *
	 * @param doctor the doctor
	 * @return the doctor
	 * @throws SystemException the system exception
	 * @throws BusinessException the business exception
	 * @throws Exception the exception
	 */
	public Doctor updateDoctor(Doctor doctor)
	    throws SystemException,
	    BusinessException,
	    Exception {
		logger.traceEntry(doctor.toString());
		try {
			Doctor doctors = doctorDao.updateDoctor(doctor);
			if (doctors == null) {
				throw new DoctorNotFound(ApplicationConstants.DOCTOR_NOT_FOUND);
			}
			logger.traceExit(doctors);
			return doctors;
		} catch (SQLException e) {
			throw new SystemException(e);
		} catch (DoctorNotFound e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Delete doctor helper.
	 *
	 * @param doctorId the doctor id
	 * @return the string
	 * @throws SystemException the system exception
	 * @throws Exception the exception
	 */
	public String deleteDoctor(int doctorId)
	    throws SystemException,
	    Exception {
		logger.traceEntry(Integer.toString(doctorId));
		try {
			String doctors = doctorDao.deleteDoctor(doctorId);
			logger.traceExit(doctors);
			return doctors;
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Track my patient doctor helper.
	 *
	 * @param doctorId the doctor id
	 * @return the list
	 * @throws SystemException the system exception
	 * @throws BusinessException the business exception
	 * @throws Exception the exception
	 */
	public List<User> trackMyPatient(int doctorId)
	    throws SystemException,
	    BusinessException,
	    Exception {
		logger.traceEntry(Integer.toString(doctorId));
		try {
			List<User> list = doctorDao.trackMyPatientInDoctor(doctorId);
			if (list == null) {
				throw new DoctorNotFound(ApplicationConstants.DOCTOR_NOT_FOUND);
			}
			logger.traceExit(list);
			return list;
		} catch (SQLException e) {
			throw new SystemException(e);
		} catch (DoctorNotFound e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * List doctor patients.
	 *
	 * @return the list
	 * @throws SystemException the system exception
	 * @throws BusinessException the business exception
	 * @throws Exception the exception
	 */
	public List<DoctorPatientList> listDoctorPatients()
	    throws SystemException,
	    BusinessException,
	    Exception {
		logger.traceEntry();
		try {
			List<DoctorPatientList> doctorPatientList = doctorDao.listDoctorPatient();
			if (doctorPatientList == null) {
				throw new DoctorNotFound(ApplicationConstants.DOCTOR_NOT_FOUND);
			}
			return doctorPatientList;
		} catch (SQLException e) {
			throw new SystemException(e);
		} catch (DoctorNotFound e) {
			throw new BusinessException(e);
		}
	}

}
