import java.util.ArrayList;
import java.util.Objects;

import org.omg.Messaging.SyncScopeHelper;

/* 
 * 
 * @author: Luciana
 * @date
 */

public class ParkManager extends AbstractUser 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3323557037867953268L;
	protected UserStatus role;
	public String parkName;
	public String parkAddress;
	public String parkCity;
	public ArrayList<Job> jobsManaging;
	
	public ParkManager(String theFname, String theLname, String theEmail, String thePark)
	{
		super(theFname, theLname, theEmail);
		this.parkName = thePark;
		role = UserStatus.PARKMANAGER;
		jobsManaging = new ArrayList<Job>();
	}
	
	public String toString()
	{
		return this.fname + " " + this.lname;
	}
	
	/*
	 * Prompts user for details of the job and creates a new job with the given details
	 * U1
	 */
	public void addJob()
	{
		String title, startDate, startTime, duration, description;
		int lightSlots = 0, medSlots = 0, heavySlots = 0;
		System.out.println("\nAdd New Job \n");
		
		System.out.println("Job Title: ");
		title = IODriver.input.next();
		
		System.out.println("Start date (mm/dd/yy): ");
		startDate = IODriver.input.next();
		/*
		 * if (response < currentDate) { 
		 * System.out.println("That date is invalid. Please enter a different date. /n");
		 * System.out.println("Start date: /n");
		 * }
		 */
		System.out.println("Start time (hh:mm am/pm): "); 
		startTime = IODriver.input.next();
		
		System.out.println("Duration (1 or 2 days): ");
		duration = IODriver.input.next();
		/*
		while (duration != "1" && duration != "2"){
		   System.out.println("\nInvalid job duration. Please enter 1 or 2. Duration:");
		   duration = IODriver.input.next();
		}
		*/
		
		System.out.println("Description of job: ");
		description = IODriver.input.next();
		
		System.out.println("Number of light slots: ");
		lightSlots = IODriver.input.nextInt();
		
		System.out.println("Number of medium slots: ");
		medSlots = IODriver.input.nextInt();
		
		System.out.println("\nNumber of heavy slots: ");
		heavySlots = IODriver.input.nextInt();
		
		//Create new Job object and add to job list
		Job newJob = new Job(title, startDate, startTime, "Stand-in end date", duration, this.parkName, this.getFullName(), description, lightSlots, medSlots, heavySlots);
		//IODriver.calendar.addJobToList(newJob);
		this.jobsManaging.add(newJob);
		
		//Show confirmation
		System.out.println("\nJob Added! Review Job Details:\n");
		System.out.println(newJob.toString());
		
		//Print menu of options 
		jobDetailsMenu(newJob);
		
	}
	
	public void jobDetailsMenu(Job theJob) {
		System.out.println("\nPlease type a number: \n "
				+ "1) Edit job \n "
				+ "2) Cancel job\n " 
				+ "3) View signed-up volunteers\n "
				+ "4) Exit " );
		String response = IODriver.input.next();
		
		switch (response){
		case "1": editJob(theJob);
			break;
		case "2": cancelJob(theJob);
			break;
		case "3": viewEnrolledVolunteers(theJob);
			break;
		case "4"://Do nothing to go back to home menu
			break;
		default: jobDetailsMenu(theJob); //Invalid response. Try again.
			break;
		}
	}
	
	/*
	 * Cancel a job (Delete it from the list of jobs)
	 * U2
	 */
	public void cancelJob(Job theJob) {
		System.out.println("Are you sure you want to cancel this job?");
		System.out.println(" 1) Yes, cancel the job. \n 2) No, keep the job \n");
		String response = IODriver.input.next();
		switch (response){
		case "1": 
			this.jobsManaging.remove(theJob); //delete job from park manager's personal job list
			//delete job from main job list
			break;
		case "2": 
			jobDetailsMenu(theJob); //Print options again
			break;
		default: 
			System.out.println("Invalid input. Please type 1 or 2. ");
			break;
		}
	}
	
	/*
	 * Edit the details of a job
	 * U3
	 */
	public void editJob(Job theJob) {
		System.out.println("Which detail would you like to edit?");
		System.out.println(" 1) Job Title: " + "\n"
					         +" 2) Date " + "\n"
					         +" 3) Time " + "\n"
					         +" 4) Duration " + "\n"        
					         +" 5) Description "+"\n"
					         +" 6) Light slots "+"\n"
					         +" 7) Medium slots "+"\n" 
					         +" 8) Heavy slots "+"\n"
					         +" 9) Done editing ");
		String response = IODriver.input.next();
		String newValue;
		switch (response){
		case "1":
			System.out.println("Enter new Job Title: ");
			theJob.setJobTitle(IODriver.input.next());
			System.out.println("Job Title has been changed");
			editJob(theJob);
			break;
		case "2":
			System.out.println("Enter new Date: ");
			theJob.setStartDate(IODriver.input.next());
			System.out.println("Start Date has been changed");
			editJob(theJob);
			break;
		case "3":
			System.out.println("Enter new Time: ");
			theJob.setStartTime(IODriver.input.next());
			System.out.println("Time has been changed");
			editJob(theJob);
			break;
		case "4":
			System.out.println("Enter new Duration: ");
			theJob.setDuration(IODriver.input.next());
			System.out.println("Duration has been changed");
			editJob(theJob);
			break;
		case "5":
			System.out.println("Enter new Description: ");
			theJob.setDescription(IODriver.input.next());
			System.out.println("Description has been changed");
			editJob(theJob);
			break;
		case "6":
			System.out.println("Enter new number of Light Slots: ");
			theJob.setLightSlots(IODriver.input.nextInt());
			System.out.println("Number of Light Slots has been changed");
			editJob(theJob);
			break;
		case "7":
			System.out.println("Enter new number of Medium Slots: ");
			theJob.setMediumSlots(IODriver.input.nextInt());
			System.out.println("Number of Medium Slots has been changed");
			editJob(theJob);
			break;
		case "8":
			System.out.println("Enter new number of Heavy Slots: ");
			theJob.setHeavySlots(IODriver.input.nextInt());
			System.out.println("Number of Heavy Slots has been changed");
			editJob(theJob);
			break;
		case "9": 
			System.out.println("Review Job Details: \n");
			System.out.println(theJob.toString());
			jobDetailsMenu(theJob);
			break;
		default:
			System.out.println("Invalid input. Please type a number 1-9.");
			editJob(theJob);
			break;	
		}
	}
	
	/*
	 * View all the park manager's upcoming jobs
	 * U8
	 */
	public void viewJobsManaged() {
		//view list of all the park manager's upcoming jobs
		int i;
		for (i = 0; i < this.jobsManaging.size(); i++){
			System.out.println(i+1);
			System.out.println(this.jobsManaging.get(i));
		}
	}
	
	/*
	 * View the volunteers signed up for my job
	 * As is, this method only calls another method so is redundant
	 * U9
	 */
	public void viewEnrolledVolunteers(Job theJob){
		theJob.printVolunteers();
	}

	/*
	 * Park Manager's display menu.
	 * 
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public StringBuilder usersHomeMenu() {
//		//System.out.println(manager.toString() + " [Park Manager]");
//		System.out.println("Please type a number. \n"
//				+ "1) View my upcoming jobs \n"
//				+ "2) Add new job \n"
//				+ "3) Edit or cancel a job \n"
//				+ "4) Exit ");
//		String response = this.input.next();
//		
//		switch (response){
//		//case "1": manager.viewJobs();
//			//break;
//		case "2": manager.addJob();
//			break;
//		case "3": 
//			break;
//		case "4": quit = true;
//			break; 
//		default: parkManagerMenu(manager);
//			break;
//		}
		return null;
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
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }
	
	/*
	 * Checks if the object is-a Park Manager.
	 * @see AbstractUser#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object theObject){
		if (super.equals(theObject)) {
			ParkManager v = (ParkManager) theObject;
			if (getRole().equals(v.getRole())) { return true; };
		}
		return false;
	}
}
