import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Represents a Volunteer.
 * 
 * @author Heather
 */
public class Volunteer extends AbstractUser implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	public static ArrayList<Job> jobs;		
	public Scanner scanner = new Scanner(System.in);
	public ArrayList<Job> enrolledJobs;
	
	/*
	 * Constructs a volunteer
	 */
	public Volunteer(String theFname, String theLname, String theEmail) {
		super(theFname, theLname, theEmail);
		jobs = new ArrayList<Job>();
		enrolledJobs = new ArrayList<Job>();
		volunteerMenu();
	}
	
	/*
	 * Console/menu driven io
	 */
	public void volunteerMenu() {
		StringBuilder greetingAndMenu = new StringBuilder();
		greetingAndMenu.append("Welcome, " + super.fname + " " + super.lname + "!"
				+ "\nPlease select from the options below...");
		System.out.println(greetingAndMenu);
	}

	/*
	 * View joined jobs specific to a volunteer.
	 */
	public void viewEnrolledJobs(ArrayList<Job> theJobList) {
		StringBuilder result = new StringBuilder();
		for (Job j : enrolledJobs) {
			result.append(j.getTitle() + "\n");	// add title field in Job class
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
	
}
