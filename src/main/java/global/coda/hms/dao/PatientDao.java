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
import global.coda.hms.models.Patient;
import global.coda.hms.models.User;

/**
 * The Class PatientDao.
 */
public class PatientDao {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(PatientDao.class);

	/**
	 * Read patient dao.
	 *
	 * @param patientId the patient id
	 * @return the patient
	 * @throws SQLException the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SystemException the system exception
	 * @throws Exception the exception
	 */
	public Patient readPatient(int patientId)
	    throws SQLException,
	    ClassNotFoundException,
	    SystemException {
		logger.traceEntry(Integer.toString(patientId));
		List<Patient> allpatients = null;
		Patient patient = null;
		ResultSet resultData;
		Connection databaseConnection = DBConfig.establishConnection();
		PreparedStatement preparedStatementforPatient;
		preparedStatementforPatient = databaseConnection.prepareStatement(
		    QueryConstants.PATIENT_READ);
		preparedStatementforPatient.setInt(1, patientId);
		resultData = preparedStatementforPatient.executeQuery();
		allpatients = getPatientDetails(resultData);
		if (allpatients.size() > 0) {
			patient = allpatients.get(0);
		}
		logger.traceExit(patient);
		return patient;
	}

	/**
	 * Gets the patient details.
	 *
	 * @param resultData the result data
	 * @return the patient details
	 * @throws SQLException the SQL exception
	 */
	public List<Patient> getPatientDetails(ResultSet resultData)
	    throws SQLException {
		logger.traceEntry(resultData.toString());
		List<Patient> allpatients = new ArrayList<Patient>();
		while (resultData.next()) {
			Patient patient = new Patient();
			patient.setPatientId(resultData.getInt(QueryConstants.USER_ID));
			patient.setUsername(resultData.getString(QueryConstants.USER_NAME));
			patient.setPassword(resultData.getString(QueryConstants.USER_PASSWORD));
			patient.setEmail(resultData.getString(QueryConstants.USER_EMAIL));
			patient.setPhonenumber(resultData.getString(
			    QueryConstants.USER_PHONENUMBER));
			patient.setFirstname(resultData.getString(QueryConstants.USER_FIRSTNAME));
			patient.setLastname(resultData.getString(QueryConstants.USER_LASTNAME));
			patient.setAge(resultData.getInt(QueryConstants.USER_AGE));
			patient.setStreet(resultData.getString(QueryConstants.USER_STREET));
			patient.setCity(resultData.getString(QueryConstants.USER_CITY));
			patient.setState(resultData.getString(QueryConstants.USER_STATE));
			patient.setRoleId(resultData.getInt(QueryConstants.USER_ROLE_ID));
			patient.setPatientHeight(resultData.getInt(
			    QueryConstants.PATIENT_HEIGHT));
			patient.setBloodGroup(resultData.getString(
			    QueryConstants.PATIENT_BLOODGROUP));
			patient.setPatientWeight(resultData.getInt(
			    QueryConstants.PATIENT_WEIGHT));
			patient.setIsActive(resultData.getInt(QueryConstants.USER_ISACTIVE));
			patient.setIsactive(resultData.getInt(QueryConstants.PATIENT_ISACTIVE));
			patient.setCreatedOn(resultData.getDate(QueryConstants.USER_CREATEDON));
			patient.setUpdatedOn(resultData.getDate(QueryConstants.USER_UPDATEDON));
			allpatients.add(patient);
		}
		logger.traceExit(allpatients);
		return allpatients;
	}

	/**
	 * Creates the patient dao.
	 *
	 * @param patient the patient
	 * @return the patient
	 * @throws SQLException the SQL exception
	 * @throws SystemException the system exception
	 */
	public Patient createPatient(Patient patient)
	    throws SQLException,
	    SystemException {
		logger.traceEntry(patient.toString());
		int executePatient = 0;
		int executeUser = 0;
		Connection databaseConnection = DBConfig.establishConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement preparedStatementForUser = databaseConnection
			    .prepareStatement(QueryConstants.USER_INSERT,
			        Statement.RETURN_GENERATED_KEYS);
			setUserDetails(preparedStatementForUser, patient);
			executeUser = preparedStatementForUser.executeUpdate();
			if (executeUser == 1) {
				if (setIdForUser(preparedStatementForUser, patient)) {

					PreparedStatement preparedStatementforPatient = databaseConnection
					    .prepareStatement(QueryConstants.PATIENT_INSERT,
					        Statement.RETURN_GENERATED_KEYS);
					setPatientDetails(preparedStatementforPatient, patient);
					executePatient = preparedStatementforPatient.executeUpdate();
					if (executePatient == 1) {
						if (setIdForPatient(preparedStatementforPatient, patient)) {

							databaseConnection.commit();
						} else {
							logger.traceExit(patient);
						}
					}
				}
			} else {
				logger.traceExit(patient);
			}
		} catch (SQLException e) {
			try {
				logger.traceExit(patient);
				databaseConnection.rollback();
			} catch (SQLException e1) {
				logger.traceExit(patient);
			}
			logger.traceExit(patient);
			throw e;
		}
		logger.traceExit(patient);
		return patient;

	}

	/**
	 * Sets the id for patient.
	 *
	 * @param preparedStatementforPatient the prepared statementfor patient
	 * @param patient the patient
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean setIdForPatient(PreparedStatement preparedStatementforPatient,
	    Patient patient)
	    throws SQLException {
		logger.traceEntry(patient.toString());
		boolean result = false;
		ResultSet resultSetId = preparedStatementforPatient.getGeneratedKeys();
		if (resultSetId != null && resultSetId.next()) {
			int userId = resultSetId.getInt(1);
			patient.setPatientId(userId);
			result = true;
		}
		logger.traceExit(result);
		return result;
	}

	/**
	 * Sets the patient details.
	 *
	 * @param preparedStatement the prepared statement
	 * @param patient the patient
	 * @throws SQLException the SQL exception
	 */
	public void setPatientDetails(PreparedStatement preparedStatement,
	    Patient patient)
	    throws SQLException {
		logger.traceEntry(patient.toString());

		preparedStatement.setInt(ApplicationConstants.ONE, patient
		    .getPatientHeight());
		preparedStatement.setString(ApplicationConstants.TWO, patient
		    .getBloodGroup());
		preparedStatement.setInt(ApplicationConstants.THREE, patient
		    .getPatientWeight());
		preparedStatement.setInt(ApplicationConstants.FOUR, patient.getUserId());
		logger.traceExit(patient.toString());
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
	 * Update patient dao.
	 *
	 * @param patient the patient
	 * @return the patient
	 * @throws SystemException System Exception
	 * @throws SQLException Sql Exception
	 * @throws ClassNotFoundException classNotFound
	 * @throws Exception the exception Exception
	 */
	public Patient updatePatient(Patient patient)
	    throws ClassNotFoundException,
	    SQLException,
	    SystemException {
		logger.traceEntry(patient.toString());
		int rowsChangedForPatient = 0, rowsChangedForUser = 0;
		Patient patientToBeUpdated = null;
		patientToBeUpdated = readPatient(patient.getUserId());
		if (patientToBeUpdated != null) {
			Connection databaseConnection = DBConfig.establishConnection();
			databaseConnection.setAutoCommit(false);
			PreparedStatement userPreparedStatement = databaseConnection
			    .prepareStatement(QueryConstants.USER_UPDATE);
			setUserDetails(userPreparedStatement, patient);
			patient.setPatientId(patient.getUserId());
			userPreparedStatement.setInt(ApplicationConstants.ELEVEN, patient
			    .getUserId());
			rowsChangedForUser = userPreparedStatement.executeUpdate();
			if (rowsChangedForUser > 0) {
				PreparedStatement patientPreparedStatement = databaseConnection
				    .prepareStatement(QueryConstants.PATIENT_UPDATE);
				setPatientDetails(patientPreparedStatement, patient);
				patientPreparedStatement.setInt(ApplicationConstants.FOUR, patient
				    .getUserId());
				rowsChangedForPatient = patientPreparedStatement.executeUpdate();
				if (rowsChangedForPatient > 0) {
					databaseConnection.commit();
				}
			}
		}
		logger.traceExit(patient.toString());
		return patient;

	}

	/**
	 * Delete patient dao.
	 *
	 * @param patientId the patient id
	 * @return the string
	 * @throws SQLException the SQL exception
	 * @throws SystemException the system exception
	 */
	public String deletePatient(int patientId)
	    throws SQLException,
	    SystemException {
		logger.traceEntry(Integer.toString(patientId));
		String result = ApplicationConstants.NOT_DELETED;
		int rowsChangedForPatient = 0;
		Connection databaseConnection = DBConfig.establishConnection();
		PreparedStatement patientPreparedStatement;

		patientPreparedStatement = databaseConnection.prepareStatement(
		    QueryConstants.PATIENT_DELETE);
		patientPreparedStatement.setInt(1, patientId);
		// patientPreparedStatement.setInt(2, userId);
		rowsChangedForPatient = patientPreparedStatement.executeUpdate();
		if (rowsChangedForPatient > 0) {
			result = ApplicationConstants.DELETED_SUCCESSFULLY;
		}
		logger.traceExit(result);
		return result;
	}
}
