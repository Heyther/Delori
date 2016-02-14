import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Console driven i/o Urban Park Staff Member.
 * 
 * @author Winfield Brooks
 * @version 1.1
 *
 */

public class UrbanParkStaffMember extends AbstractUser {

	private static final long serialVersionUID = 1483276272402890408L;
	protected UserStatus role;
	public String fname;
	public String lname;
	public transient Scanner scan;
	public static ArrayList<String> myNames;
	public static ArrayList<String> myJobs;


	/*
	 * Constructs an Urban Park Staff Member.
	 */
	public UrbanParkStaffMember(String theFirstName, String theLastName, String theEmail) {
		super(theFirstName, theLastName, theEmail);
		scan = new Scanner(System.in);
		myNames = new ArrayList<String>();
		myJobs = new ArrayList<String>();
		role = UserStatus.UPSMEMBER;
	}

	/*
	 * Console driven menu to select staff member functions.
	 */
	public void staffMenu() {
		int select = -1;
		System.out.println("Urban Park Staff Member: " + fname + " "
				+ lname + "\n");
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
		for (int i = 0; i < myJobs.size(); i++) {
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

//	/*
//	 * Temporary main for tests
//	 */
//	public static void main(String[] args) {
//		UrbanParkStaffMember staff1 = new UrbanParkStaffMember("Smokey",
//				"Bear", "looks@trees.com");
//		myNames.add("Robert");
//		myNames.add("Mark");
//		myJobs.add("river clean up");
//		myJobs.add("feed the gorilla");
//		staff1.staffMenu();
//	}

	/*
	 * Displays the menu for a UP staff member.
	 * 
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<String> usersHomeMenu() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("Welcome, " + super.fname + " " + super.lname + "!"
					+ " Please select from the options below...");
		result.add("1. " + MenuOptions.OPTION_ONE);
		result.add("2. " + MenuOptions.OPTION_FOUR);
		result.add("3. " + MenuOptions.OPTION_FIVE);
		result.add("4. " + MenuOptions.OPTION_SEVEN);
			
		return result;
	}

	/*
	 * Retrieves the user's role.
	 * 
	 * @see AbstractUser#getRole()
	 */
	public UserStatus getRole() {
		return role;
	}

	/*
	 * Sets the user's role.
	 */
	public void setRole(UserStatus role) {
		this.role = role;
	}
	
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }
	
	/*
	 * Checks if the object is-a Urban Park Staff Member.
	 * @see AbstractUser#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object theObject){
		if (super.equals(theObject)) {
			UrbanParkStaffMember v = (UrbanParkStaffMember) theObject;
			if (getRole().equals(v.getRole())) { return true; };
		}
		return false;
	}

}