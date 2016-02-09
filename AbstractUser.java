
public class AbstractUser {

	protected String fname, lname, email; // maybe necessary?
	
	/*
	 * Constructs a user for the system
	 */
	public AbstractUser(final String theFname, final String theLname, final String theEmail) {
		fname = theFname;
		lname = theLname;
		email = theEmail;
	}

	// possibly use?
//	public enum User {
//		VOLUNTEER, MANAGER, STAFF;
//	}
	
	
	// Getters and Setters below
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
