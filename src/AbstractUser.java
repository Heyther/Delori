import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a user.
 * 
 * @authors: Luciana, Winfield, Heather, Sean
 * @date 2/16/2016
 * @version 1.0
 */
public abstract class AbstractUser implements Serializable {


	private static final long serialVersionUID = 1322725593346883536L;
	protected String fname, lname, email;
	protected UserStatus role;
	
	/*
	 * Constructs an instance of a user
	 */
	public AbstractUser(final String theFname, final String theLname, final String theEmail) {
		this.fname = theFname;
		this.lname = theLname;
		this.email = theEmail;
	}

	/*
	 * Retrieves user's first name.
	 */
	public String getFname() {
		return fname;
	}

	/*
	 * Sets user's first name.
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/*
	 * Retrieves user's last name.
	 */
	public String getLname() {
		return lname;
	}

	/*
	 * Sets user's last name.
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/*
	 * Retrieves user's full name as one string.
	 */
	public String getFullName() {
		return fname + " " +lname;
	}

	/*
	 * Retrieves user's email.
	 */
	public String getEmail() {
		return email;
	}

	/*
	 * Sets user's email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserStatus getRole() {
		return role;
	}

	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fname, lname, email);
	}

	/*
	 * Checks if the object is an abstractUser.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object theObject) {
		if (this == theObject) return true;
		if (theObject == null) return false;
		if (getClass() != theObject.getClass()) return false;
		
		AbstractUser other = (AbstractUser) theObject;
		if (fname.equals(other.fname) && lname.equals(other.lname)
				&& email.equals(other.email)) {
			return true;
		}
		return false;
		
	}

}
