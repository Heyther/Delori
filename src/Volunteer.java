
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Volunteer.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/28/2016
 * @version 1.0
 */

public class Volunteer extends AbstractUser  {
	
	private static final long serialVersionUID = 3505720660401145090L;
	private UserStatus role; 	
	private ArrayList<Job> enrolledJobs;
	private int workloadResponse;

	/*
	 * Constructs a volunteer
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
	 * Cancel an enrolled job.
	 */
	public void cancelEnrolledJob(Job theJob) throws IOException {
		//Delete the job from the main job list (to be added back in after the volunteer is deleted from it)
		IODriver.storedData.deleteJob(theJob);
		theJob.cancelVolunteer(this); // remove volunteer from job
		enrolledJobs.remove(theJob); // remove enrolled job
		IODriver.storedData.addJob(theJob); //add job without volunteer to main job list
	}
	
	/*
	 * Sign up for a job. (U6)
	 */
	public void signUp(Job theJob) throws IOException, JobSlotFilledException, SignUpOnSameDayException {
		
		// BR7: A Volunteer may not sign up for two jobs on the same day.
		boolean signUp = false;
		for (Job j : enrolledJobs) {
			if (!j.getStartDate().equals(theJob.getStartDate())) signUp = true; 
		}
		
		if (signUp || enrolledJobs.size() == 0) {
			
			// BR3: A Volunteer may not sign up for a work category on a job if the maximum number of Volunteers for
			// that work category has already been reached. (handled in jobs)
			if (theJob.signUpVolunteer(this, getWorkloadResponse())) {
				//delete the job from the main job list (to be added back in once the volunteer has been added)
				IODriver.storedData.deleteJob(theJob);
				
				//Add the job back into the main job list and to the volunteer's own list
				IODriver.storedData.addJob(theJob);
				enrolledJobs.add(theJob);
			} else {
				throw new JobSlotFilledException();
			}
		} else {
			throw new SignUpOnSameDayException();
		}
	}
	
	// odd-ball methods in obtaining user input from 
	// view without having view code within model.
	public void setWorkloadResponse(int theWorkloadResponse) {
		workloadResponse = theWorkloadResponse;
	}
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
     * Hashcode for equals
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }
	
}
