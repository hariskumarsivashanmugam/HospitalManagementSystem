/*
 *
 */
package global.coda.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.config.DBConfig;
import global.coda.hms.constants.ApplicationConstants;
import global.coda.hms.constants.QueryConstants;
import global.coda.hms.exceptions.SystemException;
import global.coda.hms.models.Doctor;
import global.coda.hms.models.DoctorPatientList;
import global.coda.hms.models.User;

/**
 * The Class DoctorDao.
 */
public class DoctorDao {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(PatientDao.class);

	/**
	 * Read doctor dao.
	 *
	 * @param doctorId the doctor id
	 * @return the doctor
	 * @throws SQLException the SQL exception
	 * @throws SystemException the system exception
	 */
	public Doctor readDoctorDao(int doctorId)
	    throws SQLException,
	    SystemException {
		logger.traceEntry(Integer.toString(doctorId));
		List<Doctor> alldoctors = null;
		Doctor doctor = null;
		ResultSet resultData;
		Connection databaseConnection = DBConfig.establishConnection();
		PreparedStatement preparedStatementforDoctor = databaseConnection
		    .prepareStatement(QueryConstants.DOCTOR_READ);
		preparedStatementforDoctor.setInt(1, doctorId);
		resultData = preparedStatementforDoctor.executeQuery();
		alldoctors = getDoctorDetails(resultData);
		doctor = alldoctors.get(0);
		logger.traceExit(doctor.toString());
		return doctor;
	}

	/**
	 * Gets the doctor details.
	 *
	 * @param resultData the result data
	 * @return the doctor details
	 * @throws SQLException the SQL exception
	 */
	private List<Doctor> getDoctorDetails(ResultSet resultData)
	    throws SQLException {
		logger.traceEntry(resultData.toString());
		List<Doctor> alldoctors = new ArrayList<Doctor>();
		while (resultData.next()) {
			Doctor doctor = new Doctor();
			doctor.setDoctorId(resultData.getInt(QueryConstants.USER_ID));
			doctor.setUsername(resultData.getString(QueryConstants.USER_NAME));
			doctor.setPassword(resultData.getString(QueryConstants.USER_PASSWORD));
			doctor.setEmail(resultData.getString(QueryConstants.USER_EMAIL));
			doctor.setPhonenumber(resultData.getString(
			    QueryConstants.USER_PHONENUMBER));
			doctor.setFirstname(resultData.getString(QueryConstants.USER_FIRSTNAME));
			doctor.setLastname(resultData.getString(QueryConstants.USER_LASTNAME));
			doctor.setAge(resultData.getInt(QueryConstants.USER_AGE));
			doctor.setStreet(resultData.getString(QueryConstants.USER_STREET));
			doctor.setCity(resultData.getString(QueryConstants.USER_CITY));
			doctor.setState(resultData.getString(QueryConstants.USER_STATE));
			doctor.setRoleId(resultData.getInt(QueryConstants.USER_ROLE_ID));
			doctor.setDoctorexperience(resultData.getInt(
			    QueryConstants.DOCTOR_EXPERIENCE));
			doctor.setDoctorspeciality(resultData.getString(
			    QueryConstants.DOCTOR_SPECIALITY));
			doctor.setIsactive(resultData.getInt(QueryConstants.DOCTOR_ISACTIVE));
			doctor.setIsActive(resultData.getInt(QueryConstants.USER_ISACTIVE));
			doctor.setCreatedOn(resultData.getDate(QueryConstants.USER_CREATEDON));
			doctor.setUpdatedOn(resultData.getDate(QueryConstants.USER_UPDATEDON));
			alldoctors.add(doctor);
		}
//		if (alldoctors.size() < 1) {
//			logger.traceExit(alldoctors.toString());
//			throw new null(ApplicationConstants.DOCTOR_NOT_FOUND);
//		}
		logger.traceExit(alldoctors.toString());
		return alldoctors;
	}

	/**
	 * Creates the doctor dao.
	 *
	 * @param doctor the doctor
	 * @return the doctor
	 * @throws SQLException the SQL exception
	 * @throws SystemException the system exception
	 */
	public Doctor createDoctorDao(Doctor doctor)
	    throws SQLException,
	    SystemException {
		logger.traceEntry(doctor.toString());
		int rowsChangedForDoctor = 0;
		int rowsChangedForUser = 0;
		Connection databaseConnection = DBConfig.establishConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement preparedStatementForUser = databaseConnection
			    .prepareStatement(QueryConstants.USER_INSERT,
			        Statement.RETURN_GENERATED_KEYS);
			setUserDetails(preparedStatementForUser, doctor);
			rowsChangedForUser = preparedStatementForUser.executeUpdate();
			if (rowsChangedForUser == 1) {
				if (setIdForUser(preparedStatementForUser, doctor)) {

					PreparedStatement preparedStatementForDoctor = databaseConnection
					    .prepareStatement(QueryConstants.DOCTOR_INSERT,
					        Statement.RETURN_GENERATED_KEYS);
					setDoctorDetails(preparedStatementForDoctor, doctor);
					rowsChangedForDoctor = preparedStatementForDoctor.executeUpdate();
					if (rowsChangedForDoctor == 1) {
						if (setIdForDoctor(preparedStatementForDoctor, doctor)) {

							databaseConnection.commit();
						} else {
							logger.traceExit();
						}
					}
				}
			} else {
				logger.traceExit();
			}
		} catch (SQLException e) {
			logger.traceExit();
			databaseConnection.rollback();
			throw e;
		}
		logger.traceExit(doctor.toString());
		return doctor;
	}

	/**
	 * Sets the doctor details.
	 *
	 * @param preparedStatementForDoctor the prepared statement for doctor
	 * @param doctor the doctor
	 * @throws SQLException the SQL exception
	 */
	private void setDoctorDetails(PreparedStatement preparedStatementForDoctor,
	    Doctor doctor)
	    throws SQLException {
		logger.traceEntry(doctor.toString());
		preparedStatementForDoctor.setInt(ApplicationConstants.ONE, doctor
		    .getDoctorexperience());
		preparedStatementForDoctor.setString(ApplicationConstants.TWO, doctor
		    .getDoctorspeciality());
		preparedStatementForDoctor.setInt(ApplicationConstants.THREE, doctor
		    .getUserId());
		logger.traceExit(doctor.toString());
	}

	/**
	 * Sets the id for doctor.
	 *
	 * @param preparedStatementForDoctor the prepared statement for doctor
	 * @param doctor the doctor
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	private boolean setIdForDoctor(PreparedStatement preparedStatementForDoctor,
	    Doctor doctor)
	    throws SQLException {
		logger.traceEntry(doctor.toString());
		boolean result = false;
		ResultSet resultSetId = preparedStatementForDoctor.getGeneratedKeys();
		if (resultSetId != null && resultSetId.next()) {
			int userId = resultSetId.getInt(1);
			doctor.setDoctorId(userId);
			result = true;
		}
		logger.traceExit(result);
		return result;
	}

	/**
	 * Sets the id for user.
	 *
	 * @param userPreparedStatement the user prepared statement
	 * @param user the user
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean setIdForUser(PreparedStatement userPreparedStatement,
	    User user)
	    throws SQLException {
		logger.traceEntry(user.toString());
		boolean result = false;
		ResultSet resultSetId = userPreparedStatement.getGeneratedKeys();
		if (resultSetId != null && resultSetId.next()) {
			int userId = resultSetId.getInt(1);
			user.setUserId(userId);
			result = true;
		}
		logger.traceExit(result);
		return result;
	}

	/**
	 * Sets the user details.
	 *
	 * @param userPreparedStatement the user prepared statement
	 * @param user the user
	 * @throws SQLException the SQL exception
	 */
	public void setUserDetails(PreparedStatement userPreparedStatement, User user)
	    throws SQLException {
		logger.traceEntry(user.toString());
		userPreparedStatement.setString(ApplicationConstants.ONE, user
		    .getUsername());
		userPreparedStatement.setString(ApplicationConstants.TWO, user
		    .getPassword());
		userPreparedStatement.setString(ApplicationConstants.THREE, user
		    .getEmail());
		userPreparedStatement.setString(ApplicationConstants.FOUR, user
		    .getPhonenumber());
		userPreparedStatement.setString(ApplicationConstants.FIVE, user
		    .getFirstname());
		userPreparedStatement.setString(ApplicationConstants.SIX, user
		    .getLastname());
		userPreparedStatement.setInt(ApplicationConstants.SEVEN, user.getAge());
		userPreparedStatement.setString(ApplicationConstants.EIGHT, user.getCity());
		userPreparedStatement.setString(ApplicationConstants.NINE, user.getState());
		userPreparedStatement.setString(ApplicationConstants.TEN, user.getStreet());
		userPreparedStatement.setInt(ApplicationConstants.ELEVEN, user.getRoleId());
		logger.traceExit(user.toString());
	}

	/**
	 * Update doctor dao.
	 *
	 * @param doctor the doctor
	 * @return the doctor
	 * @throws SQLException the SQL exception
	 * @throws SystemException the system exception
	 */
	public Doctor updateDoctorDao(Doctor doctor)
	    throws SQLException,
	    SystemException {
		logger.traceEntry(doctor.toString());
		int rowsChangedForDoctor = 0, rowsChangedForUser = 0;
		Doctor checkDoctor = null;
		checkDoctor = readDoctorDao(doctor.getUserId());
		if (checkDoctor != null) {
			Connection databaseConnection = DBConfig.establishConnection();
			try {
				databaseConnection.setAutoCommit(false);
				PreparedStatement userpreparedStatement = databaseConnection
				    .prepareStatement(QueryConstants.USER_UPDATE);
				setUserDetails(userpreparedStatement, doctor);
				doctor.setDoctorId(doctor.getUserId());
				userpreparedStatement.setInt(ApplicationConstants.ELEVEN, doctor
				    .getUserId());
				rowsChangedForUser = userpreparedStatement.executeUpdate();
				if (rowsChangedForUser > 0) {
					PreparedStatement doctorPreparedStatement = databaseConnection
					    .prepareStatement(QueryConstants.DOCTOR_UPDATE);
					setDoctorDetails(doctorPreparedStatement, doctor);
					doctorPreparedStatement.setInt(ApplicationConstants.THREE, doctor
					    .getUserId());
					rowsChangedForDoctor = doctorPreparedStatement.executeUpdate();
					if (rowsChangedForDoctor > 0) {
						databaseConnection.commit();
					}
				}
			} catch (Exception e) {
				logger.traceExit();
				throw e;
			}
		} else {
			logger.traceExit();
			return null;
		}
		logger.traceExit(doctor.toString());
		return doctor;
	}

	/**
	 * Delete doctor dao.
	 *
	 * @param doctorId the doctor id
	 * @return the string
	 * @throws SQLException the SQL exception
	 * @throws SystemException the system exception
	 */
	public String deleteDoctorDao(int doctorId)
	    throws SQLException,
	    SystemException {
		logger.traceEntry(Integer.toString(doctorId));
		String result = "Not Deleted";
		int rowsChangedForDoctor = 0;
		Connection databaseConnection = DBConfig.establishConnection();
		PreparedStatement doctorPreparedStatement = databaseConnection
		    .prepareStatement(QueryConstants.DOCTOR_DELETE);
		doctorPreparedStatement.setInt(1, doctorId);
		// doctorPreparedStatement.setInt(2, userId);
		rowsChangedForDoctor = doctorPreparedStatement.executeUpdate();
		if (rowsChangedForDoctor > 0) {
			result = "Successsfully Deleted";
		}
		logger.traceExit(result);
		return result;
	}

	/**
	 * Track my patient doctor dao.
	 *
	 * @param doctorId the doctor id
	 * @return the list
	 * @throws SQLException the SQL exception
	 * @throws SystemException the system exception
	 */
	public List<User> trackMyPatientDoctorDao(int doctorId)
	    throws SQLException,
	    SystemException {
		logger.traceEntry(Integer.toString(doctorId));
		List<User> allpatient = null;
		ResultSet resultData;
		Connection databaseConnection = DBConfig.establishConnection();
		PreparedStatement preparedStatementforDoctor = databaseConnection
		    .prepareStatement(QueryConstants.DOCTOR_PATIENT_ONE_TO_MANY);
		preparedStatementforDoctor.setInt(1, doctorId);
		resultData = preparedStatementforDoctor.executeQuery();
		allpatient = trackMyPatientDetails(resultData);
		logger.traceExit(allpatient.toString());
		return allpatient;
	}

	/**
	 * Track my patient details.
	 *
	 * @param resultData the result data
	 * @return the list
	 * @throws SQLException the SQL exception
	 */
	private List<User> trackMyPatientDetails(ResultSet resultData)
	    throws SQLException {
		logger.traceEntry(resultData.toString());
		List<User> allpatient = new ArrayList<User>();
		while (resultData.next()) {
			User user = new User();
			user.setFirstname(resultData.getString(QueryConstants.USER_FIRSTNAME));
			allpatient.add(user);
		}
		if (allpatient.size() < 1) {
			logger.traceExit();
			return null;
		}
		logger.traceExit(allpatient.toString());
		return allpatient;
	}

	/**
	 * List doctor patient.
	 *
	 * @return the list
	 * @throws SQLException the SQL exception
	 * @throws SystemException the system exception
	 */
	public List<DoctorPatientList> listDoctorPatient()
	    throws SQLException,
	    SystemException {
		logger.traceEntry();
		List<DoctorPatientList> doctorPatientList = new ArrayList<
		    DoctorPatientList>();
		List<Integer> arrayList = new ArrayList<Integer>();
		List<DoctorPatientList> allpatient = new ArrayList<DoctorPatientList>();
		ResultSet resultData;
		Connection databaseConnection = DBConfig.establishConnection();
		PreparedStatement preparedStatement = databaseConnection.prepareStatement(
		    QueryConstants.DISTINCTDOCTORS);
		resultData = preparedStatement.executeQuery();
		while (resultData.next()) {
			arrayList.add(resultData.getInt("fk_doctor_id"));
		}
		for (int iterator : arrayList) {
			DoctorPatientList doctorpatient = new DoctorPatientList();
			doctorpatient.setDoctorId(iterator);
			doctorpatient.setData(trackMyPatientDoctorDao(iterator));
			doctorPatientList.add(doctorpatient);
		}
		logger.traceExit(allpatient.toString());
		return allpatient;
	}

}
