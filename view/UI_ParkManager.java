import java.io.IOException;
import java.util.ArrayList;
/**
 * Represents a Park Manager's UI.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public class UI_ParkManager extends UI_AbstractUser {

	private static final long serialVersionUID = 6557601314417133384L;
	public ParkManager user; 
	
	/*
	 * Constructor
	 */
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
		//result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.VIEW_JOBS_MANAGED);
		result.add(MenuOptions.ADD_A_JOB);
		result.add(MenuOptions.LOGOUT);
			
		return result;
	}
	
	//Show the menu of job options and return the option selected
	public MenuOptions showJobOptionsMenu() {
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("Job Options");
		ArrayList<MenuOptions> menu = new ArrayList<MenuOptions>();
		menu.add(MenuOptions.VIEW_JOB_DETAIL);
		menu.add(MenuOptions.EDIT_JOB);
		menu.add(MenuOptions.CANCEL_JOB);
		menu.add(MenuOptions.VIEW_ENROLLED_VOLUNTEERS);
		menu.add(MenuOptions.HOME);
		IODriver.numberedMenuBox(titles, menu);
		MenuOptions response = menu.get(Integer.parseInt(IODriver.input.nextLine()) - 1);
		return response;
	}
	
	//Options for what to do after a job has been selected
	public void jobOptions(Job theJob) throws IOException {
		MenuOptions selection = showJobOptionsMenu();
		switch (selection) {
		case VIEW_JOB_DETAIL:
			jobDetailsBox(theJob);
			break;
		case EDIT_JOB:
			try {
				theJob.canEditJob();
				editJob(theJob);
			} catch(CanNotEditJobException e) {
				System.out.println(e.getMessage());
			}
			break;
		case CANCEL_JOB:
			try {
				theJob.canEditJob();
				cancelJob(theJob);
			} catch(CanNotEditJobException e) {
				System.out.println(e.getMessage());
			}			
			return;
		case VIEW_ENROLLED_VOLUNTEERS:
			viewEnrolledVolunteers(theJob);
			break;
		case HOME:
			return;
		default:
			jobOptions(theJob);
			break;
		}
	}
	
	
	/*
	 * Prompts user for details of the job and calls ParkManager's addJob method
	 * (U1: As a Park Manager I want to submit a new job.)
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
		
		Job newJob = this.user.addJob(title, startDate, startTime, duration, description, lightSlots, medSlots, heavySlots);
		
		//Check if job actually was add
		System.out.println("Job Added! \nReview job details:");
		System.out.println(newJob.toString());
		//Options for what to do with this job
		jobOptions(newJob);
		
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
	 * (U3: As a Park Manager I want to edit the details of a job.)
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
			break;
		case "2":
			System.out.println("Enter new Date: ");
			this.user.editJobDate(theJob, IODriver.input.nextLine());
			System.out.println("Start Date has been changed");
			break;
		case "3":
			System.out.println("Enter new Time: ");
			this.user.editJobTime(theJob, IODriver.input.nextLine());
			System.out.println("Time has been changed");
			break;
		case "4":
			System.out.println("Enter new Duration: ");
			this.user.editJobDuration(theJob, IODriver.input.nextLine());
			System.out.println("Duration has been changed");
			break;
		case "5":
			System.out.println("Enter new Description: ");
			this.user.editJobDescription(theJob, IODriver.input.nextLine());
			System.out.println("Description has been changed");
			break;
		case "6":
			System.out.println("Enter new number of Light Slots: ");
			this.user.editJobLightSlots(theJob, Integer.parseInt(IODriver.input.nextLine()));
			System.out.println("Number of Light Slots has been changed");
			break;
		case "7":
			System.out.println("Enter new number of Medium Slots: ");
			this.user.editJobMediumSlots(theJob, Integer.parseInt(IODriver.input.nextLine()));
			System.out.println("Number of Medium Slots has been changed");
			break;
		case "8":
			System.out.println("Enter new number of Heavy Slots: ");
			this.user.editJobHeavySlots(theJob, Integer.parseInt(IODriver.input.nextLine()));
			System.out.println("Number of Heavy Slots has been changed");
			break;
		case "9": 
			System.out.println("Review Job Details: \n");
			System.out.println(theJob.toString());
			return;
		default:
			System.out.println("Invalid input. Please type a number 1-9.");
			break;	
		}
		editJob(theJob);
	}
	
	/*
	 * Show all the park manager's upcoming jobs
	 * (U8: As a Park Manager I want to view a summary of all upcoming jobs in the parks that I manage.)
	 */
	public void viewJobsManaged() throws IOException {
		try {
			IODriver.menuBoxForJobs(this.user.getJobsManaging());
			int jobNumber = selectJobNumber(this.user.getJobsManaging().size());
			if (jobNumber > 0) {
				Job selectedJob = this.user.getJobsManaging().get(jobNumber - 1);
				jobOptions(selectedJob);
			}
			else {
				IODriver.clearConsole();
			}
		}
		catch (NoManagedJobsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * View the volunteers signed up for my job
	 * (U9: As a Park Manager I want to view the Volunteers for a job in the parks that I manage)
	 */
	public void viewEnrolledVolunteers(Job theJob) {
		theJob.printVolunteers();
	}
}
