/*
 *
 */
package global.coda.hms.models;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Patient.
 */
public class Patient extends User {

	/** The blood group. */
	private String bloodGroup;

	/** The created on. */
	private Date createdOn;

	/** The isactive. */
	private int isactive;

	/** The patient height. */
	private int patientHeight;

	/** The patient id. */
	private int patientId;

	/** The patient weight. */
	private int patientWeight;

	/** The updated on. */
	private Date updatedOn;

	/**
	 * Gets the blood group.
	 *
	 * @return the blood group
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	@Override
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Gets the isactive.
	 *
	 * @return the isactive
	 */
	public int getIsactive() {
		return isactive;
	}

	/**
	 * Gets the patient height.
	 *
	 * @return the patient height
	 */
	public int getPatientHeight() {
		return patientHeight;
	}

	/**
	 * Gets the patient id.
	 *
	 * @return the patient id
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * Gets the patient weight.
	 *
	 * @return the patient weight
	 */
	public int getPatientWeight() {
		return patientWeight;
	}

	/**
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	@Override
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * Sets the blood group.
	 *
	 * @param bloodGroup the new blood group
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	@Override
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Sets the isactive.
	 *
	 * @param isactive the new isactive
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	/**
	 * Sets the patient height.
	 *
	 * @param patientHeight the new patient height
	 */
	public void setPatientHeight(int patientHeight) {
		this.patientHeight = patientHeight;
	}

	/**
	 * Sets the patient id.
	 *
	 * @param patientId the new patient id
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * Sets the patient weight.
	 *
	 * @param patientWeight the new patient weight
	 */
	public void setPatientWeight(int patientWeight) {
		this.patientWeight = patientWeight;
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the new updated on
	 */
	@Override
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientHeight="
		    + patientHeight + ", patientWeight="
		    + patientWeight + ", bloodGroup=" + bloodGroup + ", isactive="
		    + isactive + ", createdOn=" + createdOn
		    + ", updatedOn=" + updatedOn + ", getUserId()=" + getUserId()
		    + ", getUsername()=" + getUsername()
		    + ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail()
		    + ", getPhonenumber()="
		    + getPhonenumber() + ", getFirstname()=" + getFirstname()
		    + ", getLastname()=" + getLastname()
		    + ", getCity()=" + getCity() + ", getState()=" + getState()
		    + ", getStreet()=" + getStreet()
		    + ", getRoleId()=" + getRoleId() + ", getAge()=" + getAge() + "]";
	}
}
