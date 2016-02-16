
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 * Console driven i/o Urban Park Staff Member.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/16/2016
 * @version 1.0
 */

public class UrbanParkStaffMember extends AbstractUser {


	private static final long serialVersionUID = 1483276272402890408L;
	protected UserStatus role;
	


	/*
	 * Constructs an Urban Park Staff Member.
	 */
	public UrbanParkStaffMember(String theFirstName, String theLastName, String theEmail) {
		super(theFirstName, theLastName, theEmail);
		role = UserStatus.UPSMEMBER;
	}


	/*
	 * Search for a volunteer by last name.
	 */
	public void volunteerSearch() {

		String name;
		System.out.println("Volunteer Search:");
		System.out.print("Enter volunteers last name or 'b' to go back:\n>");
		name = IODriver.input.nextLine();
		ArrayList<Volunteer> volunteers = IODriver.storedData.searchVolunteerByLname(name);
		if(volunteers.size() > 0) {
			try {
				IODriver.clearConsole();
			} catch (IOException e) {
				// do nothing
			}
			for(Volunteer vol: volunteers ) {
				System.out.println(vol.toString() + "\n");
			}
			usersHomeMenu();

		} else if(name.equals("b")) {
			usersHomeMenu();
			try {
				IODriver.clearConsole();
			} catch (IOException e) {
				// do nothing
			}
		}else {
			try {
				IODriver.clearConsole();
			} catch (IOException e) {
				// do nothing
			}
			System.out.println("Volunteer not found, try again.\n");
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
		System.out.print("Select job number:\n>");
		try {
			jobNumber = Integer.parseInt(IODriver.input.nextLine());
		} catch (InputMismatchException e) {
		}
		if (jobNumber <= 0 || jobNumber > jobs.size()) {
			System.out.println("Invalid entry, try again.\n");
			viewJobDetails();
		} else {
			System.out.println(jobs.get(jobNumber - 1).toString());
			
		}
	}
	
	/*
	/*
	 * Displays the menu for a UP staff member.
	 * 
	 * @see AbstractUser#userDisplayMenu()
	 */

	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.SEARCH_VOL_LASTNAME);
		result.add(MenuOptions.EXIT);
			
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
     * Hashcode for equals method.
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