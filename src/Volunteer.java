

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a Volunteer.
 * 
 * @author Heather (Modified by Luciana)
 * @date 
 */

public class Volunteer extends AbstractUser  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3505720660401145090L;
	protected UserStatus role; 
	public ArrayList<Job> jobs;		

	public transient Scanner scanner = new Scanner(System.in);
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
	public void viewEnrolledJobs(ArrayList<Job> list) {
		StringBuilder result = new StringBuilder();
		for (Job j : enrolledJobs) {
			result.append(j.getJobTitle() + "\n");
		}
		result.substring(0, result.length());
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
		System.out.println("Please select a workload: \n 1. Light \n 2. Medium \n 3. Heavy");
		int response = IODriver.input.nextInt();
		
		//delete the job from the main job list (to be added back in once the volunteer has been added)
		IODriver.storedData.deleteJob(theJob);
		theJob.signUpVolunteer(this, response);
		
		//Add the job back into the main job list and to the volunteer's own list
		IODriver.storedData.addJob(theJob);
		enrolledJobs.add(theJob);
	}

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
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
	 * Displays the menu for a volunteer.
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
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
