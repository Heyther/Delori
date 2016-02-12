import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represents a user.
 * 
 * @author Heather
 * @date
 */
public abstract class AbstractUser implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String fname, lname, email; // maybe necessary?

	/*
	 * Constructs an instance of a user
	 */
	public AbstractUser(final String theFname, final String theLname, final String theEmail) {
		this.fname = theFname;
		this.lname = theLname;
		this.email = theEmail;
	}

	/* 
	 * Displays a menu with options for a user.
	 */
	public abstract StringBuilder userDisplayMenu();
	
	/*
	 * Retrieves the role of the user.
	 */
	public abstract UserStatus getRole();
	
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



	
}
