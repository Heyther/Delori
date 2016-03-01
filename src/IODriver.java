import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;


/**
 * Driver(/Controller) class. Controls menus and user types.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/16/2016
 * @version 1.0
 */

public class IODriver {
	
	public static Scanner input;
	static Calendar calendar;
	public static AbstractUser currentUser;
	public static UI_AbstractUser currentUserUI;
	static Data storedData;
	static boolean quitProgram;
	Data jobs, users;
	String response;

	/*
	 * Constructs the driver for the program.
	 */
	public IODriver() throws ClassNotFoundException, IOException {
		// loads all the users and jobs data
		storedData = new Data();
		response = "";
		currentUser = null;
		currentUserUI = null;
		input = new Scanner(System.in);
		quitProgram = false;
		runProgram();
	}

	/*
	 * Prompts user for login and user options.
	 */
	private void runProgram() throws IOException {
		while (!quitProgram) {
			login();
			MenuOptions selection = null;

			while (selection != MenuOptions.EXIT) {
				
				menuBox(currentUserUI.usersHomeMenu());
				System.out.print(">");
				response = input.nextLine();
				clearConsole();
				selection = currentUserUI.usersHomeMenu().get(Integer.parseInt(response));
				nextSelectionDisplay(selection);
				
			}
		}
		System.out.println("\nGoodbye");
	}
	
	/*
	 * Retrieves the next display based on user's selection
	 */
	public void nextSelectionDisplay(MenuOptions theOption) throws IOException {

		switch (theOption) {
			case VIEW_ENROLLED_JOBS:
				if (currentUserUI instanceof UI_Volunteer) {
					clearConsole();
					((UI_Volunteer) currentUserUI).viewEnrolledJobs();
				}
				break;
			case ADD_A_JOB:
				if (currentUserUI instanceof UI_ParkManager) {
					((ParkManager) currentUser).addJob();	
					break;
				}
			case VIEW_JOBS_MANAGED:
				if (currentUserUI instanceof UI_ParkManager) {
					((ParkManager) currentUser).viewJobsManaged();
				}
				break;
			case VIEW_UPCOMING_JOBS:  
				if (currentUser instanceof Volunteer) {
					menuBoxForJobs(storedData.getJobs());
					//((Volunteer) currentUser).signUpOrViewJobDetail();
				} else {
					clearConsole();
					menuBoxForJobs(storedData.getJobs());
				}
				break;
			case EXIT:    
				quitProgram = true;
				break;
	
			case SEARCH_VOL_LASTNAME:
				if (currentUserUI instanceof UI_UrbanParkStaffMember) {
					((UrbanParkStaffMember) currentUser).volunteerSearch();
				}
				break;
			case VIEW_JOB_DETAIL: 
				if (currentUserUI instanceof UI_UrbanParkStaffMember) {
					menuBoxForJobs(storedData.getJobs());
					((UrbanParkStaffMember) currentUser).viewJobDetails();
				} else if (currentUserUI instanceof UI_Volunteer) {
					clearConsole();
					menuBoxForJobs(storedData.getJobs());
					((UI_Volunteer) currentUserUI).viewJobDetails();
				}
				break;
			case SIGN_UP: 
				clearConsole();
				menuBoxForJobs(storedData.getJobs());
				System.out.print("Please, select a job number or enter 0 to go back:\n>");
				response = input.nextLine();
				int index = Integer.parseInt(response);
				if (index != 0) {
					
					((Volunteer) currentUser).signUp(storedData.getJobs().get(index-1));
				} else {
					//do nothing
					clearConsole();
				}
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
//		
//		menuBox(greet);
		int boxWidth = getLongestString(greet) + 3; 
		StringBuilder divider = repeat("=", (int) boxWidth + 9); 
		
		System.out.print(divider + "\n" + MenuOptions.OPTION_LOGIN +"\n" + MenuOptions.OPTION_ENTER_EMAIL+ "\n" +divider + "\n>");
		//System.out.print(">");
		
		
		while (Objects.isNull(currentUser) ) {
			response = input.nextLine();
			currentUser = storedData.getReturningUser(response);
			if (Objects.isNull(currentUser)) {
				System.out.println("Wrong Email...\n Please, try again.\n>");
			}
		}
		currentUser = storedData.getReturningUser(response);
		if (currentUser instanceof Volunteer) {
			currentUserUI = new UI_Volunteer();
		} else if (currentUser instanceof ParkManager) {
			currentUserUI = new UI_ParkManager();
		} else {
			currentUserUI = new UI_UrbanParkStaffMember();
		}
		
		try {
			clearConsole();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Clears the console.
	 */
	public static void clearConsole() throws IOException {
		for(int clear = 0; clear < 6; clear++) {
		    System.out.println("\n") ;
		}
	} 
	
	/*
	 * Repeats a string pattern n times
	 */
	private static StringBuilder repeat(String str, int times) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < times; i++) {
			result.append(str);
		}
		return result;
	}

	/*
	 * Takes a list of menu options and displays them in a box format
	 */
	public void menuBox(ArrayList<MenuOptions> menuOptions) {
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
	public void menuBoxForJobs(ArrayList<Job> menuOptions) {
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


	/*
	 * Starts the program.
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IODriver driver = new IODriver();
	}

}
