

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a Volunteer.
 * 
 * @author Heather
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
//
//	/*
//	 * Console/menu driven io
//	 */
//	public void volunteerMenu() {
//		
//	}

	/*
	 * View joined jobs specific to a volunteer. (U7)
	 */
	public void viewEnrolledJobs(ArrayList<Job> theJobList) {
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
	public void cancelEnrolledJob(Job theJob) {
		enrolledJobs.remove(theJob);
	}
	
	
	/*
	 * Sign up for a job. (U6)
	 */
	public void signUp(Job theJob) {
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
	public ArrayList<String> usersHomeMenu() {
		StringBuilder greetingAndMenu = new StringBuilder();
		greetingAndMenu.append("");
		
		ArrayList<String> result = new ArrayList<String>();
		result.add("Welcome, " + super.fname + " " + super.lname + "!"
					+ " Please select from the options below...");
		result.add("1. " + MenuOptions.OPTION_ONE);
		result.add("2. " + MenuOptions.OPTION_TWO);
		result.add("3. " + MenuOptions.OPTION_SEVEN);
			
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
