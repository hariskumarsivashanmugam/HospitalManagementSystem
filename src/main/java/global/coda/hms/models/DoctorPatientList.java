/*
 *
 */
package global.coda.hms.models;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorPatientList.
 */
public class DoctorPatientList {

	/** The doctor id. */
	private int doctorId;

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "DoctorPatientList [doctorId=" + doctorId + ", data=" + data + "]";
	}

	/**
	 * Gets the doctor id.
	 *
	 * @return the doctor id
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * Sets the doctor id.
	 *
	 * @param doctorId the new doctor id
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<User> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(List<User> data) {
		this.data = data;
	}

	/** The data. */
	private List<User> data;

}
