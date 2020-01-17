/*
 *
 */
package global.coda.hms.models;

// TODO: Auto-generated Javadoc
/**
 * The Class Response.
 *
 * @param <T> the generic type
 */
public class ResponseObject<T> {

	/** The data. */
	private T data;

	/** The status code. */
	private int statusCode;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
