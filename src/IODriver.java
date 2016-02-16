
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


/**
 * Driver class. Controls menus and user types.
 * 
 * @author: Luciana (Modified by Heather)
 * @date 2/13/2016
 */

public class IODriver {
	
	public static Scanner input;
	static Calendar calendar;
	public static AbstractUser currentUser;
	static Data storedData;
	static boolean quitProgram;
	Data jobs, users;
	String response;
	//private Volunteer currentIdentifiedUser;

	/*
	 * Constructs the driver for the program.
	 */
	public IODriver() throws ClassNotFoundException, IOException {
		//calendar = new Calendar();
		// loads all the users and jobs data
		storedData = new Data();
		//calendar = new Calendar(storedData.getUsers(), storedData.getJobs());
		
		response = "";
		currentUser = null;
		input = new Scanner(System.in);
		//input.useDelimiter("\n");
		quitProgram = false;
		//MenuOptions init = null;
		runProgram();
	}

	
	private void runProgram() throws IOException {
		while (!quitProgram) {
			login();
			MenuOptions selection = null;
			//menuBox(currentUser.usersHomeMenu());
			//System.out.print(">");
			//response = input.nextLine();
			//selection = currentUser.usersHomeMenu().get(Integer.parseInt(response));
			while (selection != MenuOptions.EXIT) {
				menuBox(currentUser.usersHomeMenu());
				System.out.print(">");
				response = input.nextLine();
				selection = currentUser.usersHomeMenu().get(Integer.parseInt(response));
				nextSelectionDisplay(selection);
				input.nextLine();
			}
			
		}
		System.out.println("\nGoodbye");
	}
	
	/*
	 * Retrieves the next display based on user's selection
	 */
	public void nextSelectionDisplay(MenuOptions theOption) throws IOException {

//		if (currentUser instanceof Volunteer) {
//			currentUser = (Volunteer) currentUser;
//		} else if (currentUser instanceof ParkManager) {
//			currentUser = (ParkManager) currentUser;
//		} else {
//			currentUser = (UrbanParkStaffMember) currentUser;
//		}
		
		switch (theOption) {
			case VIEW_ENROLLED_JOBS:
				if (currentUser instanceof Volunteer) {
					((Volunteer) currentUser).viewEnrolledJobs();
				}
				break;
			case ADD_A_JOB:
				if (currentUser.getRole().equals(UserStatus.PARKMANAGER)) {
					((ParkManager) currentUser).addJob();	
					break;
				}
			case VIEW_ALL_VOL:
			case VIEW_UPCOMING_JOBS:  
				if (currentUser.getRole().equals(UserStatus.PARKMANAGER)) {
					((ParkManager) currentUser).viewJobsManaged();
				} else if (currentUser instanceof Volunteer) {
					menuBoxForJobs(storedData.getJobs());
				}
				break;
	
			case EXIT:    
				quitProgram = true;
				break;
	
			case SEARCH_VOL_LASTNAME:
				if (currentUser.getRole().equals(UserStatus.UPSMEMBER)) {
					((UrbanParkStaffMember) currentUser).volunteerSearch();
				}
				break;
			case VIEW_JOB_DETAIL: 
				if (currentUser.getRole().equals(UserStatus.UPSMEMBER)) {
					((UrbanParkStaffMember) currentUser).viewJobDetails();
				}
				break;
			case SIGN_UP: 
				System.out.print("Please, select a job number:\n>");
				response = input.nextLine();
				int index = Integer.parseInt(response);
				((Volunteer) currentUser).signUp(storedData.getJobs().get(index-1));
				break;
	
			default: System.out.println("");
		}
	}
	
	/*
	 * Asks the user to enter an email address and returns the user object 
	 * associated with that email
	 * If the email is not in the system, it prompts the user again
	 */
	public void login(){		
		ArrayList<MenuOptions> greet = new ArrayList<MenuOptions>();
			greet.add(MenuOptions.OPTION_LOGIN);
			greet.add(MenuOptions.OPTION_ENTER_EMAIL);
		
		menuBox(greet);
		System.out.print(">");
		
		
		while (Objects.isNull(currentUser) ) {
			response = input.nextLine();
			currentUser = storedData.getReturningUser(response);
			if (Objects.isNull(currentUser)) {
				System.out.println("Wrong Email...\n Please, try again.\n>");
			}
		}
		currentUser = storedData.getReturningUser(response);
	}

	
	public void upcomingJobsMenu(AbstractUser currentUser) {
		//allJobs
		//menuBox();
	}


	/*
	 * Repeats a string pattern n times
	 */
	private StringBuilder repeat(String str, int times) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < times; i++) {
			result.append(str);
		}
		return result;
	}

	/*
	 * Takes a list of menu options and displays them in a box format
	 */
	private void menuBox(ArrayList<MenuOptions> menuOptions) {
		String results = "";
		int boxWidth = getLongestString(menuOptions) + 3; 
		StringBuilder divider = repeat("=", (int) boxWidth + 9); 

		results += divider + "\n";
		for (int i = 0; i < menuOptions.size(); i++) {
			if (boxWidth == menuOptions.get(i).toString().length()) {
				if (i == 1) { results += divider + "\n"; };
				if (i > 0) { results += String.format("%-5s %-"+ boxWidth + "s" + "%s", "|", i + ". " + menuOptions.get(i), "|\n"); }
				else { results += String.format("%-5s %-"+ boxWidth + "s" + "%s", "|", menuOptions.get(i), "|\n"); }
				
			} else {
				if (i == 1) { results += divider + "\n"; };
				String stringLengthDifference = Integer.toString((boxWidth - menuOptions.get(i).toString().length()) + 4);
				if (i > 0) { results += String.format("%-5s %s" + "%"+stringLengthDifference + "s", "|", i + ". " + menuOptions.get(i),"|\n"); }
				else { results += String.format("%-5s %s" + "%"+stringLengthDifference + "s", "|", menuOptions.get(i),"|\n"); }
			}
		}
		results += divider + "\n";
		System.out.println(results);
	}

	/*
	 * Takes all of the jobs within the system and displays them in a box format
	 */
	private void menuBoxForJobs(ArrayList<Job> menuOptions) {
		String results = "";
		int boxWidth = getLongestStringInJobs(menuOptions) + 3; 
		StringBuilder divider = repeat("=", (int) boxWidth + 9); 
		
		results += divider + "\n";
		results += "List of Jobs:\n";
		for (int i = 0; i < menuOptions.size(); i++) {
			//if (boxWidth == menuOptions.get(i).jobSummary().toString().length()) {
				//if (i == 1) { results += divider + "\n"; };
				if (i > 0) { results += String.format("%-5s %-"+ boxWidth + "s" + "%s", "", i + 1 + ". " + (menuOptions.get(i)).jobSummary(), "\n"); }
				else { results += String.format("%-5s %-"+ boxWidth + "s" + "%s", "", i + 1 + ". " + (menuOptions.get(i)).jobSummary(), "\n"); }
				
//			} else {
//				//if (i == 1) { results += divider + "\n"; };
//				String stringLengthDifference = Integer.toString((boxWidth - menuOptions.get(i).jobSummary().toString().length()) + 4);
//				if (i > 0) { results += String.format("%-5s %s" + "%"+stringLengthDifference + "s", "", i + ". " + (menuOptions.get(i)).jobSummary(),"\n"); }
//				else { results += String.format("%-5s %s" + "%"+stringLengthDifference + "s", "", i + ". " + (menuOptions.get(i)).jobSummary(),"\n"); }
//			}
		}
		results += divider + "\n";
		System.out.println(results);
	}
	
	/*
	 * Finds the longest MenuOptions length
	 */
	private int getLongestString(ArrayList<MenuOptions> menuOptions) {
		int maxLength = 0;
		for (MenuOptions s : menuOptions) {
			if (s.toString().length() > maxLength) {
				maxLength = s.toString().length();
			}
		}
		return maxLength;
	}
	
	/*
	 * Finds the longest Job Description length
	 */
	private int getLongestStringInJobs(ArrayList<Job> menuOptions) {
		int maxLength = 0;
		for (Job s : menuOptions) {
			if (s.jobSummary().length() > maxLength) {
				maxLength = s.jobSummary().length();
			}
		}
		return maxLength;
	}


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IODriver driver = new IODriver();
	}

}
