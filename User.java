
public abstract class User {
	protected String fname;
	protected String lname;
	protected String email;
	protected String type;
	
	public User(String theFname, String theLname, String theEmail) {
		fname = theFname;
		lname = theLname;
		email = theEmail;
	}
	
	
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
