import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract user interface class to hold common view methods for all users.
 * 
 * @author Winfield Brooks
 * @version 2/24/2016
 *
 */
public abstract class UI_AbstractUser implements Serializable {
	
	AbstractUser user;
	
	public UI_AbstractUser() {
		user = IODriver.currentUser;
	}
	
	// Displays a menu with options for a user.
	public abstract ArrayList<MenuOptions> usersHomeMenu();
	
	public void showUser() {
		if (user != null) {
			System.out.println("Signed in as " + user.getFullName() + ", " + user.getRole());
		}
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
			jobDetailsBox(allJobs.get(responseJobNum - 1));
		}

	}
	
	/*
	 * Add a border to the job details.
	 */
	public void jobDetailsBox(Job job) {
		String results = "";
		int boxWidth = job.getDescription().length() + 6; 
		StringBuilder divider = IODriver.repeat("=", (int) boxWidth + 9); 
		
		results += divider + "\n";
		results += job.toString() + "\n";
		results += divider + "\n";
		System.out.println(results);
	}
	
}
