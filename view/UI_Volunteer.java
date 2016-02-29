import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Volunteer's UI.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/28/2016
 * @version 1.0
 */
public class UI_Volunteer extends UI_AbstractUser {

	public Volunteer user;

	public UI_Volunteer() {
		// sets specific user to their UI  (this could be done in control)
		user = (Volunteer) IODriver.currentUser; // <- is this okay to have here 
												 //    instead of in IODriver?
	}
	
	/*
	 * View joined jobs specific to a volunteer. (U7)
	 */
	public void viewEnrolledJobs() {
		StringBuilder result = new StringBuilder();
		
		if (user.getEnrolledJobs().size() > 0) {
			result.append("Jobs you are enrolled in:\n");
			for (Job j : user.getEnrolledJobs()) {
				result.append(j.getJobTitle() + "\n");
			}
		} else {
			// turn below into exception...
			result.append("You are currently not enrolled in any jobs. Please enter 3 to sign up.\n");
		}
			System.out.println(result);
	}
	
	public void signUp() {
		System.out.println(MenuOptions.SELECT_WORKLOAD);
		
	}
	
	
	/*
	 * Select job from job list and view the job details.
	 */
	public void viewJobDetails() {
		ArrayList<Job> allJobs = (ArrayList<Job>) IODriver.storedData.getJobs();
		//IODriver.menuBoxForJobs(IODriver.storedData.getJobs());
		System.out.println("View job details:");
		System.out.print("Select job number, or enter 0 to go back:\n>");
		int responseJobNum = Integer.parseInt(IODriver.input.nextLine());
		if (responseJobNum < 0 || responseJobNum > allJobs.size()) {
			// turn below into exception?...
			//System.out.println(CustomExceptions InvalidUserInput);
			System.out.println("Invalid entry, try again.\n");
			viewJobDetails();
		} else if (responseJobNum == 0) { // do nothing to go back to main menu
			try {
				IODriver.clearConsole();
			} catch (IOException e) {
				// do nothing
			}
		}else {
			System.out.println(allJobs.get(responseJobNum - 1).toString());
		}

	}
	
	/*
	 * Displays the menu for a volunteer.
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.SIGN_UP);
		result.add(MenuOptions.VIEW_ENROLLED_JOBS);
		result.add(MenuOptions.EXIT);

		return result;
	}


	
}
