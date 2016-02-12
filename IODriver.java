import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Driver class. Controls menus and user types.
 * 
 * @author: Luciana (Modified by Heather)
 * @date
 */

public class IODriver {
	public static Scanner input;
	boolean quit = false;

	public IODriver() {
		input = new Scanner(System.in);
		input.useDelimiter("\n");
	}

	public void printMenu(AbstractUser currentUser) {
		switch (currentUser.getRole()) {
		case VOLUNTEER: // volunteerMenu(currentUser);
			break;
		case PARKMANAGER: // parkManagerMenu((ParkManager)currentUser);
			break;
		case UPSMEMBER: // staffMenu(currentUser);
			break;
		default:
			break;
		}
	}
	
	public StringBuilder repeat(String str, int times) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < times; i++) {
		    result.append(str);
		}
		return result;
    }
	
	/*
	 * Takes a list of menu options and displays them in a box format
	 */
	public void menuBox(ArrayList<String> menuOptions) {
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
	
	public int getLongestString(ArrayList<String> menuOptions) {
	      int maxLength = 0;
	      for (String s : menuOptions) {
	          if (s.length() > maxLength) {
	              maxLength = s.length();
	          }
	      }
	      System.out.println(maxLength);
	      return maxLength;
	  }
	

	public void greetingAndLogin() throws IOException {
		System.out.print("Welcome to Urban Parks!\n"
				+ "Please, enter your email.\n>");
		String response = input.next();
		// identify user by email through serialization
		
//		ArrayList<String> r = new ArrayList<String>();
//		r.add("Title...");
//		r.add("Option 1..");
//		r.add("Option 2...........");
//		r.add("Option 3");
//		menuBox(r);
		
	}

	public void staffMenu() {

	}

	public void parkManagerMenu(ParkManager manager) {
		System.out.println(manager.toString() + " [Park Manager]");
		System.out.print("Please type a number. \n"
				+ "1) View my upcoming jobs \n" + "2) Add new job \n"
				+ "3) Edit or cancel a job \n" + "4) Exit\n>");
		String response = input.next();

		switch (response) {
		case "1":
			manager.viewJobs();
			break;
		case "2":
			manager.addJob();
			break;
		case "3":
			break;
		case "4":
			quit = true;
			break;
		default:
			parkManagerMenu(manager);
			break;
		}
	}

	public void jobDetailsMenu(AbstractUser currentUser) {
		switch (currentUser.getRole()) {
		case VOLUNTEER: // volunteerMenu((Volunteer)currentUser);
			break;
		case PARKMANAGER: // parkManagerMenu((ParkManager)currentUser);
			break;
		case UPSMEMBER: // staffMenu((UrbanParkStaffMember)currentUser);
			break;
		default:
			break;
		}
	}

	/*
	 * Custom deserialization is needed.
	 */
	private void readObject(ObjectInputStream aStream) throws IOException,
			ClassNotFoundException {
		aStream.defaultReadObject();
		// manually deserialize and init superclass
		// String winningParty = (String)aStream.readObject();
		// init(winningParty);
	}

	/*
	 * Custom serialization is needed.
	 */
	private void writeObject(ObjectOutputStream aStream) throws IOException {
		aStream.defaultWriteObject();
		// manually serialize superclass
		// aStream.writeObject(getWinningParty());
	}

	public static void main(String[] args) throws IOException {
		IODriver io = new IODriver();
		io.greetingAndLogin();
		
		ParkManager testParkMan = new ParkManager("Mary", "Thompson",
				"mthom@gmail.com", "Green Park", UserStatus.PARKMANAGER);

		// Keep running menu until user chooses to exit
//		while (!io.quit) {
//			io.printMenu(testParkMan);
//			//io.parkManagerMenu(testParkMan);
//		}
		System.out.println("\nGoodbye");
	}

}
