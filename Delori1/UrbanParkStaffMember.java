import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 * Console driven i/o Urban Park Staff Member.
 * 
 * @author Winfield Brooks
 * @version 1.1
 *
 */

public class UrbanParkStaffMember extends AbstractUser {

<<<<<<< HEAD
	private Data data;
	protected UserStatus role;
	public static ArrayList<Volunteer> myNames;
	public static ArrayList<Job> myJobs;
=======
	private static final long serialVersionUID = 1483276272402890408L;
	protected UserStatus role;
	public String fname;
	public String lname;
	public transient Scanner scan;
	public static ArrayList<String> myNames;
	public static ArrayList<String> myJobs;
>>>>>>> hdbr4


	/*
	 * Constructs an Urban Park Staff Member.
	 */
	public UrbanParkStaffMember(String theFirstName, String theLastName, String theEmail) {
		super(theFirstName, theLastName, theEmail);
		//myNames = new ArrayList<String>();
		//myJobs = new ArrayList<String>();
		role = UserStatus.UPSMEMBER;
//			try {
//				data = new Data();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
		
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
			select = IODriver.input.nextInt();
		} catch (InputMismatchException e) {
			IODriver.input.next();
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
		name = IODriver.input.next();
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
		myJobs = data.getJobs();
		int jobNumber = -1;
		System.out.println("View job details:");
		for (int i = 0; i < myJobs.size(); i++) {
			System.out.println(i + 1 + ".) " + myJobs.get(i).getJobTitle());
		}
		System.out.println("Select job number:");
		try {
			jobNumber = IODriver.input.nextInt();
		} catch (InputMismatchException e) {
			IODriver.input.next();
		}
		if (jobNumber <= 0 || jobNumber > myJobs.size()) {
			System.out.println("Invalid entry. Try again. Job deets");
			viewJobDetails();
		} else {
			System.out.println(jobNumber);
			System.out.println(myJobs.get(jobNumber - 1).toString());
		}
	}

	/*
	 * Temporary main for tests
	 */
	public static void main(String[] args) {
		UrbanParkStaffMember staff1 = new UrbanParkStaffMember("Smokey",
				"Bear", "looks@trees.com");
		
		staff1.staffMenu();
	}

	/*
	 * Displays the menu for a UP staff member.
	 * 
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public StringBuilder usersHomeMenu() {
		// TODO Auto-generated method stub
		return null;
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