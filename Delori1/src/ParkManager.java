
/* 
 * 
 * @author: Luciana
 * @date
 */
@SuppressWarnings("serial")
public class ParkManager extends AbstractUser 
{
	protected UserStatus role;
	public String parkName;
	public String parkAddress;
	public String parkCity;
	
	public ParkManager(String theFname, String theLname, String theEmail, String thePark, UserStatus theRole)
	{
		super(theFname, theLname, theEmail);
		this.parkName = thePark;
		role = theRole;
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
		IODriver.calendar.addJobToList(newJob);
		
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
		case "3": viewEnrolledVolunteers();
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
		//find job in job list
		//show details of job
		//ask for confirmation 
		//remove job
	}
	
	/*
	 * Edit the details of a job
	 * U3
	 */
	public void editJob(Job theJob) {
		//find job in job list
		//show details of job
		//while user does not exit
			//ask which detail should be edited
			//ask for new input and change the value of the correct field
			//show details of job again
	}
	
	/*
	 * View all the park manager's upcoming jobs
	 * U8
	 */
	public void viewJobs() {
		//view list of all the park manager's upcoming jobs
	}
	
	/*
	 * View the volunteers signed up for my job
	 * U9
	 */
	public void viewEnrolledVolunteers(){
		
	}

	@Override
	public StringBuilder userDisplayMenu() {
		// TODO Auto-generated method stub
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

}
