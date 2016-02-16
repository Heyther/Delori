
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Volunteer.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/16/2016
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
			result.append("You are currently not enrolled in any jobs. Please enter 3 to sign up.\n");
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
	 * Select job from job list and view the job details.
	 */
	public void viewJobDetails() {
		ArrayList<Job> allJobs = (ArrayList<Job>) IODriver.storedData.getJobs();
		//IODriver.menuBoxForJobs(IODriver.storedData.getJobs());
		System.out.println("View job details:");
		System.out.print("Select job number, or enter 0 to go back:\n>");
		int responseJobNum = Integer.parseInt(IODriver.input.nextLine());
		if (responseJobNum < 0 || responseJobNum > allJobs.size()) {
			System.out.println("Invalid entry, try again.\n");
			viewJobDetails();
		} else if (responseJobNum == 0) { // do nothing to go back to main menu
			try {
				IODriver.clearConsole();
			} catch (IOException e) {
				// do nothing
			}
		}else {
			System.out.println(allJobs.get(responseJobNum - 1).toString());
		}

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
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.SIGN_UP);
		result.add(MenuOptions.VIEW_ENROLLED_JOBS);
		result.add(MenuOptions.EXIT);

		return result;
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
