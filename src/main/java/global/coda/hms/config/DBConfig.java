/*
 *
 */
package global.coda.hms.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.constants.DatabaseConstants;
import global.coda.hms.exceptions.SystemException;

// TODO: Auto-generated Javadoc
/**
 * DBConfig Class.
 *
 * @author GS
 */
public class DBConfig {

	/** The db connection. */
	private static Connection dbConnection = null;

	/**
	 * Establish connection.
	 *
	 * @return the connection
	 * @throws SystemException the system exception
	 */
	public static Connection establishConnection()
	    throws SystemException {
		final Logger logger = LogManager.getLogger(DBConfig.class);
		logger.traceEntry();
		if (dbConnection == null) {
			logger.traceEntry();
			try {
				Class.forName(DatabaseConstants.DRIVER_CLASS);
				dbConnection = DriverManager.getConnection(DatabaseConstants.URL,
				    DatabaseConstants.USERNAME, DatabaseConstants.PASSWORD);
			} catch (ClassNotFoundException e) {
				logger.traceExit();
				throw new SystemException(e);
			} catch (SQLException e) {
				logger.traceExit();
				throw new SystemException(e);
			}
			logger.traceExit();
		}
		logger.traceExit();
		return dbConnection;
	}

	/**
	 * Close connection.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void closeConnection()
	    throws SQLException {
		dbConnection.close();
	}
}
