import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.omg.Messaging.SyncScopeHelper;

/* 
 * 
 * @author: Luciana (Modified by Heather)
 * @date 2/16/16
 */

public class ParkManager extends AbstractUser 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3323557037867953268L;
	protected UserStatus role;
	private String parkName;
	private ArrayList<Job> jobsManaging;
	
	public ParkManager(String theFname, String theLname, String theEmail, String thePark)
	{
		super(theFname, theLname, theEmail);
		this.parkName = thePark;
		role = UserStatus.PARKMANAGER;
		jobsManaging = new ArrayList<Job>();
	}
	
	public String toString()
	{
		return getLname() + ", " + getFname() + "\nEmail: " + getEmail();
	}
	
	/*
	 * Prompts user for details of the job and creates a new job with the given details
	 * U1
	 */
	public void addJob() throws IOException
	{
		String title, startDate, startTime, duration, description;
		int lightSlots = 0, medSlots = 0, heavySlots = 0;
		System.out.println("\nAdd New Job \n");
		
		System.out.println("Job Title: ");
		title = IODriver.input.nextLine();
		
		System.out.println("Start date (mm/dd/yyyy): ");
		startDate = IODriver.input.nextLine();
		/*
		 * if (response < currentDate) { 
		 * System.out.println("That date is invalid. Please enter a different date. /n");
		 * System.out.println("Start date: /n");
		 * }
		 */
		System.out.println("Start time (hh:mm am/pm): "); 
		startTime = IODriver.input.nextLine();
		
		System.out.println("Duration (1 or 2 days): ");
		duration = IODriver.input.nextLine();
		/*
		while (duration != "1" && duration != "2"){
		   System.out.println("\nInvalid job duration. Please enter 1 or 2. Duration:");
		   duration = IODriver.input.next();
		}
		*/
		
		System.out.println("Description of job: ");
		description = IODriver.input.nextLine();
		
		System.out.println("Number of light slots: ");
		lightSlots = Integer.parseInt(IODriver.input.nextLine());
		
		System.out.println("Number of medium slots: ");
		medSlots = Integer.parseInt(IODriver.input.nextLine());
		
		System.out.println("\nNumber of heavy slots: ");
		heavySlots = Integer.parseInt(IODriver.input.nextLine());
		
		//Create new Job object and add to main job list and park manager's job list
		Job newJob = new Job(title, startDate, startTime, duration, this.parkName, this.getFullName(), description, lightSlots, medSlots, heavySlots);
		//IODriver.storedData.addJob(newJob);
		//this.jobsManaging.add(newJob);
		
		if(IODriver.storedData.calendar.verifyJob(newJob)) {
			this.jobsManaging.add(newJob);
			IODriver.storedData.addJob(newJob);
			//Show confirmation
			System.out.println("\nJob Added! Review Job Details:\n");
			System.out.println(newJob.toString());
			//Print menu of options 
			jobDetailsMenu(newJob);
		}
		else {
			System.out.println("Unable to add job. Too many jobs scheduled selected week.");

		}
		
		//Print menu of options 
		//jobDetailsMenu(newJob);
		
	}
	
	public void jobDetailsMenu(Job theJob) throws IOException {
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
	public void cancelJob(Job theJob) throws IOException {
		System.out.println("Are you sure you want to cancel this job?");
		System.out.println(" 1) Yes, cancel the job. \n 2) No, keep the job \n");
		String response = IODriver.input.next();
		switch (response){
		case "1": 
			this.jobsManaging.remove(theJob); //delete job from park manager's personal job list
			IODriver.storedData.deleteJob(theJob);//delete job from main job list
			break;
		case "2": 
			jobDetailsMenu(theJob); //Go back to job details menu
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
	public void editJob(Job theJob) throws IOException {
		//Delete the job from the main job list (the edited one will be added instead)
		IODriver.storedData.deleteJob(theJob);
		
		System.out.println("Which detail would you like to edit?");
		System.out.println(" 1) Job Title " + "\n"
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
		
		//For the field the user selected to edit, prompt for the new value and change the appropriate field in the job
		//call the method again so that the user may edit another field
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
			IODriver.storedData.addJob(theJob);
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
	public void viewJobsManaged() throws IOException {
		if (this.jobsManaging.size() > 0) {
			//view list of all the park manager's upcoming jobs
			int i;
			for (i = 0; i < this.jobsManaging.size(); i++){
				System.out.print((i+1) + ". ");
				System.out.println(this.jobsManaging.get(i).jobSummary());
			}
			System.out.println("\nType a number to select a job or type 0 to go back: ");
			int jobNumber = IODriver.input.nextInt();
			//Keep prompting until good input in received
			while (jobNumber > jobsManaging.size()) {
				System.out.println("Invalid input. Please try again.");
				jobNumber = IODriver.input.nextInt();
			}
			//If a job number was typed, print job details and options (If 0 was typed, do nothing)
			if (jobNumber != 0){
				System.out.println(jobsManaging.get(jobNumber-1));
				jobDetailsMenu(jobsManaging.get(jobNumber-1));
			}
		} else {
			System.out.println("You currently have no jobs up.");
		}
	}
	
	/*
	 * View the volunteers signed up for my job
	 * Only prints the volunteers, does not allow the park manager to select the volunteers in any way
	 * U9
	 */
	public void viewEnrolledVolunteers(Job theJob) throws IOException{
		theJob.printVolunteers();
		jobDetailsMenu(theJob);
	}

	/*
	 * Park Manager's display menu.
	 * 
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
		result.add(MenuOptions.ADD_A_JOB);
		result.add(MenuOptions.EXIT);
			
		return result;

//				+ "1) View my upcoming jobs \n"
//				+ "2) Add new job \n"
//				+ "3) Edit or cancel a job \n"
//				+ "4) Exit ");
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
