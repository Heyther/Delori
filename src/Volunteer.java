
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
	protected UserStatus role; 
	public ArrayList<Job> jobs;		
	public ArrayList<Job> enrolledJobs;


	/*
	 * Constructs a volunteer
	 */
	public Volunteer(String theFname, String theLname, String theEmail) {
		super(theFname, theLname, theEmail);
		jobs = new ArrayList<Job>();
		enrolledJobs = new ArrayList<Job>();
		//volunteerMenu();
		role = UserStatus.VOLUNTEER;
	}
	
	/*
	 * Retrieve the Volunteer's enrolled jobs.
	 */
	public ArrayList<Job> getEnrolledJobs() {
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
		
		theJob.cancelVolunteer(this);
		
		//Remove the job from the volunteer's list of jobs
		enrolledJobs.remove(theJob);
		//Add job (now without that volunteer signed up) into the main job list
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Sign up for a job. (U6)
	 */
	public void signUp(Job theJob) throws IOException {
		// BR7
		boolean signUp = false;
		for (Job j : enrolledJobs) {
			if (!j.getStartDate().equals(theJob.getStartDate())) signUp = true; 
		}
		if (signUp || enrolledJobs.size() == 0) {
			int response = Integer.parseInt(IODriver.input.nextLine());
			// BR3
			if (theJob.signUpVolunteer(this, response)) {
				//delete the job from the main job list (to be added back in once the volunteer has been added)
				IODriver.storedData.deleteJob(theJob);
				
				
				//Add the job back into the main job list and to the volunteer's own list
				IODriver.storedData.addJob(theJob);
				enrolledJobs.add(theJob);
			} else {
				System.out.println("Sorry, all slots of that category are filled. You cannot sign up.");
			}
		} else {
			System.out.println("Sorry, you cannot sign up for another job with time conflicts.\n");
		}
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
