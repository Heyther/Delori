/* 
 * 
 * @author: Luciana
 */
public class ParkManager extends User
{
	public String parkName;
	public String parkAddress;
	public String parkCity;
	
	public ParkManager(String theFname, String theLname, String theEmail, String thePark)
	{
		super(theFname, theLname, theEmail);
		this.parkName = thePark;
		this.type = "Park Manager";
	}
	
	public String toString()
	{
		return this.fname + " " + this.lname;
	}
	
	/*
	 * Prompts user for details of the job and creates a new job with the given details
	 */
	public void addJob()
	{
		String startDate, startTime, duration, description;
		int lightSlots = 0, medSlots = 0, heavySlots = 0;
		System.out.println("\nAdd New Job \n");
		
		System.out.println("\nStart date (mm/dd/yy): ");
		startDate = IODriver.input.next();
		/*
		 * if (response < currentDate) { 
		 * System.out.println("That date is invalid. Please enter a different date. /n");
		 * System.out.println("Start date: /n");
		 * }
		 */
		System.out.println("\nStart time (hh:mm am/pm): "); 
		startTime = IODriver.input.next();
		
		System.out.println("\nDuration (1 or 2 days): ");
		duration = IODriver.input.next();
		/*
		while (duration != "1" && duration != "2"){
		   System.out.println("\nInvalid job duration. Please enter 1 or 2. Duration:");
		   duration = IODriver.input.next();
		}
		*/
		
		System.out.println("\nDescription of job: ");
		description = IODriver.input.next();
		
		System.out.println("\nNumber of light slots: ");
		lightSlots = IODriver.input.nextInt();
		
		System.out.println("\nNumber of medium slots: ");
		medSlots = IODriver.input.nextInt();
		
		System.out.println("\nNumber of heavy slots: ");
		heavySlots = IODriver.input.nextInt();
		
		Job newJob = new Job(startDate, startTime, duration, this.parkName, this.toString(), description, lightSlots, medSlots, heavySlots);
		//add to job list
		System.out.println("\nJob Added! Review Job Details:\n");
		System.out.println(newJob.toString());
		
		//Print menu of options 
		jobDetailsMenu(newJob);
		
	}
	
	public void jobDetailsMenu(Job theJob){
		System.out.println("\nPlease type a number: \n "
				+ "1) Edit job \n "
				+ "2) Cancel job\n "
				+ "3) Exit " );
		String response = IODriver.input.next();
		
		switch (response){
		case "1": editJob(theJob);
			break;
		case "2": cancelJob(theJob);
			break;
		case "3": //Do nothing to go back to home menu
			break;
		default: jobDetailsMenu(theJob); //Invalid response. Try again.
			break;
		}
	}
	
	public void cancelJob(Job theJob){
		//find job in job list
		//show details of job
		//ask for confirmation 
		//remove job
	}
	
	public void editJob(Job theJob){
		//find job in job list
		//show details of job
		//while user does not exit
			//ask which detail should be edited
			//ask for new input and change the value of the correct field
			//show details of job again
	}
	
	public void viewJobs(){
		//view list of all the park manager's upcoming jobs
	}

}
