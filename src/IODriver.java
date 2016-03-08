import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

/**
 * Driver(/Controller) class. Controls menus and user types.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public class IODriver {
	
	public static Scanner input;
	static Calendar calendar;
	public static AbstractUser currentUser;
	public static UI_AbstractUser currentUserUI;
	static Data storedData;
	static boolean quitProgram;
	private static int BOXWIDTHPADDING_THREE = 3;
	private static int BOXWIDTHPADDING_NINE = 9;
	private static int N_LINES = 15;
	Data jobs, users;
	String response;
	MenuOptions selection;
	protected ArrayList<String> titleList;
	
	/*
	 * Constructs the driver for the program.
	 */
	public IODriver(boolean run) throws ClassNotFoundException, IOException, ParseException {
		storedData = new Data(); // loads all the users and jobs data
		response = "";
		currentUser = null;
		currentUserUI = null;
		input = new Scanner(System.in);
		quitProgram = false;
		selection = null;
		titleList = new ArrayList<String>();
		titleList.add(MenuOptions.OPTION_WELCOME.toString());
		if (run) {
			runProgram();
		}
	}

	/*
	 * Prompts user for login and user options.
	 */
	private void runProgram() throws IOException, ParseException {
		login();
		storedData.calendar.removePastJobs();
		while (selection != MenuOptions.EXIT) {
			titleList.add(currentUserUI.showUser());
			while (selection != MenuOptions.LOGOUT) {
				numberedMenuBox(titleList, currentUserUI.usersHomeMenu()); 
				System.out.print(">");
				response = input.nextLine();
				clearConsole();
				selection = currentUserUI.usersHomeMenu().get(Integer.parseInt(response) - 1);
				nextSelectionDisplay(selection);
			}
			// remove personal login status
			titleList.remove(titleList.indexOf( currentUserUI.showUser() ));
			logout();
		}
		System.out.println("\nGoodbye");
	}
	
	/*
	 * Retrieves the next display based on user's selection
	 */
	public void nextSelectionDisplay(MenuOptions theOption) throws IOException {

		switch (theOption) {
			case VIEW_ENROLLED_JOBS:
				clearConsole();
				((UI_Volunteer) currentUserUI).viewEnrolledJobs();
				break;
			case ADD_A_JOB:
				((UI_ParkManager) currentUserUI).createJob();
				break;
			case VIEW_JOBS_MANAGED:
				((UI_ParkManager) currentUserUI).viewJobsManaged();
				break;
			case VIEW_UPCOMING_JOBS:
				clearConsole();
				menuBoxForJobs(storedData.getJobs());
				break;
			case LOGIN:
				login();
				break;
			case LOGOUT:
				break;
			case EXIT:
				quitProgram = true;
				break;
			case SEARCH_VOL_LASTNAME:
				((UI_UrbanParkStaffMember) currentUserUI).volunteerSearch();
				break;
			case VIEW_JOB_DETAIL: 
				clearConsole();
				menuBoxForJobs(storedData.getJobs());
				currentUserUI.viewJobDetails();
				break;
			case SIGN_UP: 
				clearConsole();
				menuBoxForJobs(storedData.getJobs());
				((UI_Volunteer) currentUserUI).signUpView();
				break;
	
			default: System.out.println("");
		}
	}
	
	/*
	 * Asks the user to enter an email address and sets the user object 
	 * associated with that email. If the email is not in the system, 
	 * prompts the user for email again.
	 */
	public void login(){		
		ArrayList<MenuOptions> greet = new ArrayList<MenuOptions>();
		greet.add(MenuOptions.OPTION_LOGIN);
		greet.add(MenuOptions.OPTION_ENTER_EMAIL);
		
		int boxWidth = getLongestStringLength(greet) + BOXWIDTHPADDING_THREE; 
		StringBuilder divider = repeat("=", (int) boxWidth + BOXWIDTHPADDING_NINE); 
		
		System.out.print(divider + "\n" + MenuOptions.OPTION_LOGIN +"\n" + MenuOptions.OPTION_ENTER_EMAIL+ "\n" +divider + "\n>");
		
		while (Objects.isNull(currentUser) ) {
			response = input.nextLine();
			currentUser = storedData.getReturningUser(response);
			if (Objects.isNull(currentUser)) {
				System.out.println("Wrong Email...\n Please, try again.\n>");
			}
		}
		
		// sets current user's UI
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
			// Do nothing
		}
	}
	
	/*
	 * Logs off a user.
	 */
	public void logout() throws IOException {
		ArrayList<String> logoutTitle = new ArrayList<String>();
		logoutTitle.add(MenuOptions.LOGGING_OUT.toString());
		ArrayList<MenuOptions> logout = new ArrayList<MenuOptions>();
		logout.add(MenuOptions.LOGIN);
		logout.add(MenuOptions.EXIT);
		
		numberedMenuBox(logoutTitle, logout);
		System.out.print(">");
		int resp = Integer.parseInt(input.nextLine()) - 1;
		selection = logout.get(resp);
		clearConsole();
		currentUser = null;
		nextSelectionDisplay(selection);
	}

	/*
	 * Clears the console.
	 */
	public static void clearConsole() throws IOException {
		for (int clear = 0; clear < N_LINES; clear++) {
		    System.out.println("\n") ;
		}
	} 
	
	/*
	 * Repeats a string pattern n times
	 */
	protected static StringBuilder repeat(String str, int times) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < times; i++) {
			result.append(str);
		}
		return result;
	}

	/*
	 * Takes a list of strings and menu options and displays them in a numbered box format.
	 */
	public static void numberedMenuBox(ArrayList<String> theTitles, ArrayList<MenuOptions> menuOptions) {
		String results = "";
		int longestTitle = getLongestStringLength(theTitles);
		int longestMenuOption = getLongestStringLength(menuOptions) + BOXWIDTHPADDING_THREE;
		int boxWidth = Math.max(longestTitle, longestMenuOption); 
		StringBuilder divider = repeat("=", (int) boxWidth + BOXWIDTHPADDING_NINE);
		
		// divider with titles below
		results += divider + "\n";
		for (int i = 0; i < theTitles.size(); i++) {
			results += String.format("%-5s%s", "", theTitles.get(i) + "\n");
		}
		// divider with display of options
		results += divider + "\n";
		for (int i = 0; i < menuOptions.size(); i++) {
			results += String.format("%-5s%s", "", i + 1 + ". " + menuOptions.get(i) + "\n");
		}
		results += divider + "\n";
		System.out.println(results);
	}

	/*
	 * Takes all of the jobs within the system and displays them in a box format.
	 */
	public static void menuBoxForJobs(ArrayList<Job> theJobs) {
		String results = "";
		int boxWidth = getLongestStringLength(theJobs) + BOXWIDTHPADDING_THREE; 
		StringBuilder divider = repeat("=", (int) boxWidth + BOXWIDTHPADDING_NINE); 
		
		results += divider + "\nList of Jobs:\n";
		for (int i = 0; i < theJobs.size(); i++) {
			results += String.format("%-5s%s", "", i + 1 + ". " + (theJobs.get(i)).jobSummary() + "\n");
		}
		results += divider + "\n";
		System.out.println(results);
	}
	
	/*
	 * Takes a list of menu options and displays them in a box format.
	 */
	public static void menuBoxNotNumbered(ArrayList<String> theTitles, ArrayList<MenuOptions> menuOptions) {
		String results = "";
		int longestTitle = getLongestStringLength(theTitles);
		int longestMenuOption = getLongestStringLength(menuOptions) + BOXWIDTHPADDING_THREE;
		int boxWidth = Math.max(longestTitle, longestMenuOption); 
		StringBuilder divider = repeat("=", (int) boxWidth + BOXWIDTHPADDING_NINE);
		
		// divider with titles below
		results += divider + "\n";
		for (int i = 0; i < theTitles.size(); i++) {
			results += String.format("%-5s%s", "", theTitles.get(i) + "\n");
		}
		// divider with display of options
		results += divider + "\n";
		for (int i = 0; i < menuOptions.size(); i++) {
			results += String.format("%-5s%s", "", menuOptions.get(i) + "\n");
		}
		results += divider + "\n";
		System.out.println(results);
	}
	
	/*
	 * Finds the longest string length.
	 */
	private static <T> int getLongestStringLength(ArrayList<T> genericStringList) {
		int maxLength = 0;
		for (T s : genericStringList) {
			if (s instanceof Job) {
				if (((Job) s).jobSummary().length() > maxLength) {
					maxLength = ((Job) s).jobSummary().length();
				}
			} else {
				if (s.toString().length() > maxLength) {
					maxLength = s.toString().length();
				}
			}
		}
		return maxLength;
	}

	/*
	 * Starts the program.
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
		@SuppressWarnings("unused")
		IODriver driver = new IODriver(true);
	}

}
