import java.io.IOException;
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
	


	/*
	 * Constructs an Urban Park Staff Member.
	 */
	public UrbanParkStaffMember(String theFirstName, String theLastName, String theEmail) {
		super(theFirstName, theLastName, theEmail);
		role = UserStatus.UPSMEMBER;
		
	}

	/*
	 * Console driven menu to select staff member functions.
	 */
	public void staffMenu() {
		int select = -1;
		System.out.println("Urban Park Staff Member: " + this.getFname() + " "
				+ this.getLname() + "\n");
		System.out.println("Select from the following:");
		System.out.println("\t1) Search volunteer by last name.");
		System.out.println("\t2) View job details.");
		System.out.println("\t3) Exit.");
		System.out.println("Enter item number:");
		try {
			select = IODriver.input.nextInt();
		} catch (InputMismatchException e) {
			IODriver.input.next();
		}
		System.out.println(select);
		switch (select) {
		case 1: volunteerSearch();
			break;
		case 2: viewJobDetails();
			break;
		case 3 : ;
			break;
		default :
			System.out.println("Invalid entry. Try again. Menu");
			staffMenu();
			break;
		}
	}

	/*
	 * Search for a volunteer by last name.
	 */
	public void volunteerSearch() {
		String name;
		System.out.println("Volunteer Search:");
		System.out.println("Enter volunteers last name or 'b' to go back:");
		name = IODriver.input.next();
		ArrayList<Volunteer> volunteers = IODriver.storedData.searchVolunteerByLname(name);
		System.out.println(name);
		if(volunteers.size() > 0) {
			for(Volunteer vol: volunteers ) {
				System.out.println(vol.toString());
			}
			staffMenu();

		} else if(name.equals("b")) {
			staffMenu();
			
		}else {
			System.out.println("Volunteer not found, try again.");
			volunteerSearch();
		}
	}

	/*
	 * Select job from job list and view the job details.
	 */
	public void viewJobDetails() {
		ArrayList<Job> jobs = (ArrayList<Job>) IODriver.storedData.getJobs();
		int jobNumber = 0;
		System.out.println("View job details:");
		for (int i = 0; i < jobs.size(); i++) {
			System.out.println(i + 1 + ".) " + jobs.get(i).getJobTitle());
		}
		System.out.println("Select job number:");
		try {
			jobNumber = IODriver.input.nextInt();
		} catch (InputMismatchException e) {
			IODriver.input.next();
		}
		if (jobNumber <= 0 || jobNumber > jobs.size()) {
			System.out.println("Invalid entry, try again.");
			viewJobDetails();
		} else {
			System.out.println(jobs.get(jobNumber - 1).toString());
			staffMenu();
		}
		
	}

	
	/*
	 * Displays the menu for a UP staff member.
	 * 
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public StringBuilder usersHomeMenu() {
		staffMenu();
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