
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Volunteer.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public class Volunteer extends AbstractUser  {
	
	private static final long serialVersionUID = 3505720660401145090L;
	private UserStatus role; 	
	protected ArrayList<Job> enrolledJobs;
	private int workloadResponse;

	/*
	 * Constructs a volunteer.
	 */
	public Volunteer(String theFname, String theLname, String theEmail) {
		super(theFname, theLname, theEmail);
		enrolledJobs = new ArrayList<Job>();
		role = UserStatus.VOLUNTEER;
		workloadResponse = 0;
	}
	
	/*
	 * Retrieve the Volunteer's enrolled jobs.
	 */
	public ArrayList<Job> getEnrolledJobs() throws NoEnrolledJobsPresentException  {
		if (enrolledJobs.isEmpty()) {
			throw new NoEnrolledJobsPresentException();
			
		}
		return enrolledJobs;
	}

	/*
	 * Sets the user's role.
	 */
	public void setRole(UserStatus role) {
		this.role = role;
	}

	/*
	 * Retrieves the user's role.
	 */
	public UserStatus getRole() {
		return role;
	}
	
	/*
	 * Retrieves the user's role.
	 * @see AbstractUser#getRoleString()
	 */
	public String getRoleString() {
		return "Volunteer";
	}
	
	/*
	 * Sign up a volunteer for a job. 
	 * (U6: As a Volunteer I want to volunteer for a job.)
	 */
	public void signUp(Job theJob) throws IOException, JobSlotFilledException, SignUpOnSameDayException {
		// (BR7: A Volunteer may not sign up for two jobs on the same day.)
		boolean signUp = false;
		for (Job j : enrolledJobs) { 
			if (!j.getStartDate().equals(theJob.getStartDate())) signUp = true; 
		}
		
		if (signUp || enrolledJobs.size() == 0) {
			/* (BR3: A Volunteer may not sign up for a work category on a job if the maximum 
			    number of Volunteers for that work category has already been reached.) */
			if (theJob.signUpVolunteer(this, getWorkloadResponse())) {
				IODriver.storedData.deleteJob(theJob); // delete old job from main list
				enrolledJobs.add(theJob); // add newly enrolled job to volunteer's list
				IODriver.storedData.addJob(theJob); // add job with volunteer back into main list
			} else {
				throw new JobSlotFilledException();
			}
		} else {
			throw new SignUpOnSameDayException();
		}
	}
	
	/*
	 * Sets user's workload response.
	 */
	public void setWorkloadResponse(int theWorkloadResponse) {
		workloadResponse = theWorkloadResponse;
	}
	
	/*
	 * Get the specified workload response.
	 */
	public int getWorkloadResponse() {
		return workloadResponse;
	}
	
	/*
	 * Checks if the object is-a volunteer.
	 * @see AbstractUser#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object theObject){
		if (super.equals(theObject)) {
			Volunteer v = (Volunteer) theObject;
			if (getRole().equals(v.getRole())) { return true; };
		}
		return false;
	}
	
	/*
	 * Displays last name, first name and email.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getLname() + ", " + getFname() + "\nEmail: " + getEmail();
	}
	
    /*
     * Hashcode for equals method
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }
	
}
