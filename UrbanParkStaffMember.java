import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Console driven i/o Urban Park Staff Member.
 * 
 * @author Winfield Brooks
 * @version 1.1
 *
 */
public class UrbanParkStaffMember implements Serializable {

	public String myFirstName;
	public String myLastName;
	public Scanner scan;
	public static ArrayList<String> myNames;
	public static ArrayList<String> myJobs;

	
	public UrbanParkStaffMember() {
		this("First", "Last");
	}
	
	/*
	 * Constructor 
	 * @param theFirstName first name
	 * @param theLastName last name
	 */
	public UrbanParkStaffMember(String theFirstName, String theLastName) {
		myFirstName = theFirstName;
		myLastName = theLastName;
		scan = new Scanner(System.in);
		myNames = new ArrayList<String>();
		myJobs = new ArrayList<String>();
	}
	
	/*
	 * Console driven menu to select staff member functions.
	 */
	public void staffMenu() {
		int select = -1;
		System.out.println("Urban Park Staff Member: " + myFirstName + " " + myLastName + "\n");
		System.out.println("Select from the following:");
		System.out.println("\t1) Search volunteer by last name.");
		System.out.println("\t2) View job details.");
		System.out.println("Enter item number:");
		try {
			select = scan.nextInt();
		} catch (InputMismatchException e) {
			scan.next();
		}
		System.out.println(select);
		if (select == 1) {
			volunteerSearch();
		} else if (select == 2) {
			viewJobDetails();
		} else {
			System.out.println("Invalid entry. Try again. Menu");
			staffMenu();
		}
	}

	/*
	 * Search for a volunteer by last name.
	 */
	public void volunteerSearch() {
		String name;
		System.out.println("Volunteer Search:");
		System.out.println("Enter volunteers last name:");
		name = scan.next();
		System.out.println(name);
		if (myNames.contains(name)) {
			System.out.println(name);
		} else {
			System.out.println("Volunteer not found.");
			volunteerSearch();
		}
	}

	/*
	 * Select job from job list and view the job details.
	 */
	public void viewJobDetails() {
		int jobNumber = -1;
		System.out.println("View job details:");
		for(int i = 0; i < myJobs.size(); i++) {
			System.out.println(i + 1 + ".) " + myJobs.get(i));
		}
		System.out.println("Select job number:");
		try {
			jobNumber = scan.nextInt();
		} catch (InputMismatchException e) {
			scan.next();
		}
		if (jobNumber <= 0 || jobNumber > myJobs.size()) {
			System.out.println("Invalid entry. Try again. Job deets");
			viewJobDetails();
		} else {
		System.out.println(jobNumber);
		System.out.println(myJobs.get(jobNumber - 1));
		}
	}

	/*
	 * Temporary main for tests
	 */
	public static void main(String[] args) {
		UrbanParkStaffMember staff1 = new UrbanParkStaffMember("Smokey", "Bear");
		myNames.add("Robert");
		myNames.add("Mark");
		myJobs.add("river clean up");
		myJobs.add("feed the gorilla");
		staff1.staffMenu();
	}
}