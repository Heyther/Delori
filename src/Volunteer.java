
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Volunteer.
 * 
 * @author Heather (Modified by Luciana)
 * @date 2/16/2016
 */

public class Volunteer extends AbstractUser  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3505720660401145090L;
	protected UserStatus role; 
	public ArrayList<Job> jobs;		

	//public transient Scanner scanner = new Scanner(System.in);
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
	 * Retrieves the user's role.
	 * @see AbstractUser#getRole()
	 */
	public UserStatus getRole() {
		return role;
	}

	/*
	 * Sets the user's role.
	 */
	public void setRole(UserStatus role) {
		this.role = role;
	}

	/*
	 * View joined jobs specific to a volunteer. (U7)
	 */
	public void viewEnrolledJobs() {
		StringBuilder result = new StringBuilder();
		if (enrolledJobs.size() > 0) {
			result.append("Jobs you are enrolled in:\n");
			for (Job j : enrolledJobs) {
				result.append(j.getJobTitle() + "\n");
			}
		} else {
			result.append("You are currently not enrolled in any jobs\n");
		}
			System.out.println(result);
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
		
		System.out.println("Please select a workload: \n 1. Light \n 2. Medium \n 3. Heavy\n\n>");
		
<<<<<<< HEAD
		
		// BR7
		boolean signUp = false;
		for (Job j : enrolledJobs) {
			if (!j.getStartDate().equals(theJob.getStartDate())) signUp = true; 
		}
		if (signUp || enrolledJobs.size() == 0) {
			//delete the job from the main job list (to be added back in once the volunteer has been added)
			IODriver.storedData.deleteJob(theJob);
			int response = Integer.parseInt(IODriver.input.nextLine());
			theJob.signUpVolunteer(this, response);
			
			//Add the job back into the main job list and to the volunteer's own list
			IODriver.storedData.addJob(theJob);

			enrolledJobs.add(theJob);
		} else {
			System.out.println("Sorry, you cannot sign up for another job with time conflicts.\n");
		}
=======
		//delete the job from the main job list (to be added back in once the volunteer has been added)
		IODriver.storedData.deleteJob(theJob);
		
		if (theJob.signUpVolunteer(this, response)) {
			//Add the job to the volunteer's list
			IODriver.storedData.addJob(theJob);
		}
		else {
			System.out.println("Sorry, all slots of that category are filled. You cannot sign up.");
		}
		
		//Add the job back into the main job list
		enrolledJobs.add(theJob);
>>>>>>> 610a4f7006b0660849866fc65c6d6241e32a0c85
	}

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), getRole());
//    }
	
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
	 * Displays the menu for a volunteer.
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
		result.add(MenuOptions.SIGN_UP);
		result.add(MenuOptions.VIEW_ENROLLED_JOBS);
		result.add(MenuOptions.EXIT);
			
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getLname() + ", " + getFname() + "\nEmail: " + getEmail();
	}
	
	
}
