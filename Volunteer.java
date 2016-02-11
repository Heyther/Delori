import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Volunteer.
 * 
 * @author Heather
 * @date 
 */
@SuppressWarnings("serial")
public class Volunteer extends AbstractUser  {
	
	protected UserStatus role; 
	public ArrayList<Job> jobs;		
	public Scanner scanner = new Scanner(System.in);
	public ArrayList<Job> enrolledJobs;
	
	/*
	 * Constructs a volunteer
	 */
	public Volunteer(String theFname, String theLname, String theEmail, UserStatus theRole) {
		super(theFname, theLname, theEmail);
		jobs = new ArrayList<Job>();
		enrolledJobs = new ArrayList<Job>();
		volunteerMenu();
		role = theRole;
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
	 * Console/menu driven io
	 */
	public void volunteerMenu() {
		
	}

	/*
	 * View joined jobs specific to a volunteer.
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
	 * Sign up for a job.
	 */
	public void signUp(Job theJob) {
		enrolledJobs.add(theJob);
	}

	/*
	 * Displays the menu for a volunteer.
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public StringBuilder userDisplayMenu() {
		StringBuilder greetingAndMenu = new StringBuilder();
		greetingAndMenu.append("Welcome, " + super.fname + " " + super.lname + "!"
				+ "\nPlease select from the options below...");
		return greetingAndMenu;
	}
	

	
}
