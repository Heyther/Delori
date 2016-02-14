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
	Calendar calendar;
	AbstractUser currentUser;
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
		calendar = new Calendar(storedData.getUsers(), storedData.getJobs());
		response = "";
		currentUser = null;
		input = new Scanner(System.in);
		input.useDelimiter("\n");
		quitProgram = false;
		runProgram();
	}

	private void runProgram() {
		while (!quitProgram) {
			login();
			MenuOptions selection = null;
			while (selection != MenuOptions.OPTION_SEVEN) {
				menuBox(currentUser.usersHomeMenu());
				response = input.nextLine();
			}
			
		}
		System.out.println("\nGoodbye");
	}

	
	/*
	 * Asks the user to enter an email address and returns the user object 
	 * associated with that email
	 * If the email is not in the system, it prompts the user again
	 */
	public void login(){		
		ArrayList<String> greet = new ArrayList<String>() {{
			add("Welcome to Urban Parks!");
			add("Please, enter your email.");
		}};
		menuBox(greet);
		System.out.print(">");
		
		
		while (Objects.isNull( currentUser) ) {
			response = input.nextLine();
			currentUser = storedData.getReturningUser(response);
			if (Objects.isNull(currentUser));
		}
//		System.out.println(response);
		currentUser = storedData.getReturningUser(response);
	}

	/*
	 * Calls the appropriate menu function depending on what type of user it gets passed
	 */
	public void printMenu(AbstractUser currentUser) {
		currentUser.usersHomeMenu();
	}
	
	public void upcomingJobsMenu(AbstractUser currentUser) {
		//allJobs
		//menuBox();
	}


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
	private void menuBox(ArrayList<String> menuOptions) {
		String results = "";
		int boxWidth = getLongestString(menuOptions) + 3; 
		StringBuilder divider = repeat("=", (int) boxWidth + 9); 

		results += divider + "\n";
		for (int i = 0; i < menuOptions.size(); i++) {
			if (boxWidth == menuOptions.get(i).length()) {
				if (i == 1) { results += divider + "\n"; };
				results += String.format("%-5s %-"+ boxWidth + "s" + "%s", "|", menuOptions.get(i), "|\n");
			} else {
				if (i == 1) { results += divider + "\n"; };
				String stringLengthDifference = Integer.toString((boxWidth - menuOptions.get(i).length()) + 4);
				results += String.format("%-5s %s" + "%"+stringLengthDifference + "s", "|", menuOptions.get(i),"|\n");
			}
		}
		results += divider + "\n";
		System.out.println(results);
	}

	private int getLongestString(ArrayList<String> menuOptions) {
		int maxLength = 0;
		for (String s : menuOptions) {
			if (s.length() > maxLength) {
				maxLength = s.length();
			}
		}
		System.out.println(maxLength);
		return maxLength;
	}


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IODriver driver = new IODriver();
	}

}
