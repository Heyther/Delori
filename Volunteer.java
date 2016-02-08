import java.util.ArrayList;
import java.util.Scanner;

/*
 * Represents a Volunteer.
 * 
 * @author Heather
 */
public class Volunteer extends AbstractUser {
	
	public static ArrayList<Job> jobs;		
	public Scanner scanner = new Scanner(System.in);
	public String myFname, myLname;
	public ArrayList<Job> enrolledJobs;
	
	/*
	 * Constructs a volunteer
	 */
	public Volunteer(final String theFname, final String theLname, final String theEmail) {
		super(theFname, theLname, theEmail);
		myFname = theFname;
		myLname = theLname;
		jobs = new ArrayList<Job>();
		enrolledJobs = new ArrayList<Job>();
		volunteerMenu();
	}
	
	/*
	 * Console/menu driven io
	 */
	public void volunteerMenu() {
		StringBuilder greetingAndMenu = new StringBuilder();
		greetingAndMenu.append("Welcome, " + myFname + " " + myLname + "!"
				+ "\nPlease select from the options below...");
		System.out.println(greetingAndMenu);
	}

	/*
	 * View joined jobs specific to a volunteer.
	 */
	public void viewJobsEnrolled(ArrayList<Job> theJobList) {
		StringBuilder result = new StringBuilder();
		for (Job j : enrolledJobs) {
			result.append(j.<job_title> + "\n");
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
