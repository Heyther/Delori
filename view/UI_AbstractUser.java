import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract user interface class to hold common view methods for all users.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public abstract class UI_AbstractUser implements Serializable {
	
	private static final long serialVersionUID = 6133973165093948247L;
	AbstractUser user;
	
	/*
	 * Construct view with reference to current logged in user.
	 */
	public UI_AbstractUser() {
		user = IODriver.currentUser;
	}
	
	// Displays a home menu with options for a user.
	public abstract ArrayList<MenuOptions> usersHomeMenu();
	
	/*
	 * A string representation of the user's full name and role.
	 */
	public String showUser() {
		if (user != null) {
			return user.getFullName() + ", " + user.getRoleString();
		} else 
			return null;
	}
	
	/*
	 * Select job number from a printed menu of jobs.
	 */
	public int selectJobNumber(int listSize) {

		System.out.println("Select job number, or enter 0 to go back:");
		System.out.print(">");
		String response = IODriver.input.nextLine();
		int selection = Integer.parseInt(response);
		
		while (selection < 0 || selection > listSize) {
			System.out.println("Invalid input. Please type a number 1-" + listSize);
			System.out.print(">");
			response = IODriver.input.nextLine();
			selection = Integer.parseInt(response);
		}
		
		return selection;
	}
	
	/*
	 * Select job from job list and view the job details.
	 */
	public void viewJobDetails() throws IOException {
		ArrayList<Job> allJobs = (ArrayList<Job>) IODriver.storedData.getJobs();
		System.out.print("Select a job number to view its details (or enter 0 to go back):\n>");
		int responseJobNum = Integer.parseInt(IODriver.input.nextLine());
		if (responseJobNum < 0 || responseJobNum > allJobs.size()) {
			System.out.println("Invalid entry, try again.\n");
			viewJobDetails();
		} else if (responseJobNum == 0) { // do nothing to go back to main menu
				IODriver.clearConsole();
		}else {
			IODriver.clearConsole();
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
