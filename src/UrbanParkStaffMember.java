import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Console driven i/o Urban Park Staff Member
 * 
 * @author Winfield Brooks
 * @version 1.1
 *
 */
public class UrbanParkStaffMember {

	public String firstName;
	public String lastName;
	public Scanner scan;
	public static ArrayList<String> names;
	public static ArrayList<String> jobs;

	
	public UrbanParkStaffMember() {
		this("First", "Last");
	}
	
	public UrbanParkStaffMember(String first, String last) {
		firstName = first;
		lastName = last;
		scan = new Scanner(System.in);
		names = new ArrayList<String>();
		jobs = new ArrayList<String>();
	}
	
	
	public void staffMenu() {
		int select = -1;
		System.out.println("Urban Park Staff Member: " + firstName + " " + lastName + "\n");
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

	public void volunteerSearch() {
		String name;
		System.out.println("Volunteer Search:");
		System.out.println("Enter volunteers last name:");
		name = scan.next();
		System.out.println(name);
		if (names.contains(name)) {
			System.out.println(name);
		} else {
			System.out.println("Volunteer not found.");
			volunteerSearch();
		}
	}

	public void viewJobDetails() {
		int jobNumber = -1;
		System.out.println("View job details:");
		for(int i = 0; i < jobs.size(); i++) {
			System.out.println(i + 1 + ".) " + jobs.get(i));
		}
		System.out.println("Select job number:");
		try {
			jobNumber = scan.nextInt();
		} catch (InputMismatchException e) {
			scan.next();
		}
		if (jobNumber <= 0 || jobNumber > jobs.size()) {
			System.out.println("Invalid entry. Try again. Job deets");
			viewJobDetails();
		} else {
		System.out.println(jobNumber);
		System.out.println(jobs.get(jobNumber - 1));
		}
	}

	public static void main(String[] args) {
		UrbanParkStaffMember staff1 = new UrbanParkStaffMember("Smokey", "Bear");
		names.add("Robert");
		names.add("Mark");
		jobs.add("river clean up");
		jobs.add("feed the gorilla");
		staff1.staffMenu();
	}
}