import java.io.IOException;
import java.util.ArrayList;
/**
 * Represents a Park Manager's UI.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/28/2016
 * @version 1.0
 */
public class UI_ParkManager extends UI_AbstractUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6557601314417133384L;
	public ParkManager user; 
	
	public UI_ParkManager() {
		user = (ParkManager) IODriver.currentUser;
	}

	/*
	 * Park Manager's display menu.
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
		result.add(MenuOptions.VIEW_JOBS_MANAGED);
		result.add(MenuOptions.ADD_A_JOB);
		result.add(MenuOptions.EXIT);
			
		return result;
	}
	
	/*
	 * Park Manager's menu of actions to take once a job has been selected
	 */
	@Override
	public ArrayList<MenuOptions> jobOptionsMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.JOB_OPTIONS);
		result.add(MenuOptions.EDIT_JOB);
		result.add(MenuOptions.CANCEL_JOB);
		result.add(MenuOptions.VIEW_ENROLLED_VOLUNTEERS);
		result.add(MenuOptions.EXIT);

		return result;
	}
	
	/*
	 * Prompts user for details of the job and calls ParkManager's addJob method
	 * U1
	 */
	public void createJob() throws IOException
	{
		String title, startDate, startTime, duration, description;
		int lightSlots = 0, medSlots = 0, heavySlots = 0;
		System.out.println("\nAdd New Job \n");
		
		System.out.println("Job Title: ");
		title = IODriver.input.nextLine();
		
		System.out.println("Start date (mm/dd/yyyy): ");
		startDate = IODriver.input.nextLine();

		System.out.println("Start time (hh:mm am/pm): "); 
		startTime = IODriver.input.nextLine();
		
		System.out.println("Duration (1 or 2 days): ");
		duration = IODriver.input.nextLine();
		
		System.out.println("Description of job: ");
		description = IODriver.input.nextLine();
		
		System.out.println("Number of light slots: ");
		lightSlots = Integer.parseInt(IODriver.input.nextLine());
		
		System.out.println("Number of medium slots: ");
		medSlots = Integer.parseInt(IODriver.input.nextLine());
		
		System.out.println("Number of heavy slots: ");
		heavySlots = Integer.parseInt(IODriver.input.nextLine());
		
		this.user.addJob(title, startDate, startTime, duration, description, lightSlots, medSlots, heavySlots);
	}
	
	/*
	 * Confirms that the user really wants to cancel the job then calls ParkManager's cancelJob method
	 */
	public void cancelJob(Job theJob) throws IOException {
		System.out.println("Are you sure you want to cancel this job?");
		System.out.println(" 1) Yes, cancel the job. \n 2) No, keep the job \n");
		String response = IODriver.input.nextLine();
		switch (response){
		case "1": 
			this.user.cancelJob(theJob);
			break;
		case "2": 
			//jobDetailsMenu(theJob); //Go back to job details menu
			break;
		default: 
			System.out.println("Invalid input. Please type 1 or 2. ");
			cancelJob(theJob);
			break;
		}
	}
	
	
	/*
	 * Ask user what field to edit, get new value for that field,
	 * then send the new value to the appropriate edit method of the ParkManager class
	 * U3
	 */
	public void editJob(Job theJob) throws IOException {
		
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
		String response = IODriver.input.nextLine();
		
		//For the field the user selected to edit, prompt for the new value and change the appropriate field in the job
		//call the method again so that the user may edit another field
		switch (response){
		case "1":
			System.out.println("Enter new Job Title: ");
			this.user.editJobTitle(theJob, IODriver.input.nextLine());
			//Have editJobTitle return boolean to see if edit was successful
			System.out.println("Job Title has been changed");
			editJob(theJob);
			break;
		case "2":
			System.out.println("Enter new Date: ");
			this.user.editJobDate(theJob, IODriver.input.nextLine());
			System.out.println("Start Date has been changed");
			editJob(theJob);
			break;
		case "3":
			System.out.println("Enter new Time: ");
			this.user.editJobTime(theJob, IODriver.input.nextLine());
			System.out.println("Time has been changed");
			editJob(theJob);
			break;
		case "4":
			System.out.println("Enter new Duration: ");
			this.user.editJobDuration(theJob, IODriver.input.nextLine());
			System.out.println("Duration has been changed");
			editJob(theJob);
			break;
		case "5":
			System.out.println("Enter new Description: ");
			this.user.editJobDescription(theJob, IODriver.input.nextLine());
			System.out.println("Description has been changed");
			editJob(theJob);
			break;
		case "6":
			System.out.println("Enter new number of Light Slots: ");
			this.user.editJobLightSlots(theJob, Integer.parseInt(IODriver.input.nextLine()));
			System.out.println("Number of Light Slots has been changed");
			editJob(theJob);
			break;
		case "7":
			System.out.println("Enter new number of Medium Slots: ");
			this.user.editJobMediumSlots(theJob, Integer.parseInt(IODriver.input.nextLine()));
			System.out.println("Number of Medium Slots has been changed");
			editJob(theJob);
			break;
		case "8":
			System.out.println("Enter new number of Heavy Slots: ");
			this.user.editJobHeavySlots(theJob, Integer.parseInt(IODriver.input.nextLine()));
			System.out.println("Number of Heavy Slots has been changed");
			editJob(theJob);
			break;
		case "9": 
			System.out.println("Review Job Details: \n");
			System.out.println(theJob.toString());
			break;
		default:
			System.out.println("Invalid input. Please type a number 1-9.");
			editJob(theJob);
			break;	
		}
	}
	
	/*
	 * Show all the park manager's upcoming jobs
	 * U8
	 */
	public void viewJobsManaged() throws IOException {
		if (this.user.getJobsManaging().size() > 0) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < this.user.getJobsManaging().size(); i++) {
				str.append(i+1);
				str.append(". ");
				str.append(this.user.getJobsManaging().get(i).jobSummary());
				str.append("\n");
			}
			System.out.println(str.toString());
		}
		else
			System.out.println("You do not have any upcoming jobs in your park.");
	}
	
	/*
	 * View the volunteers signed up for my job
	 * U9
	 */
	public void viewEnrolledVolunteers(Job theJob) {
		theJob.printVolunteers();
	}
}