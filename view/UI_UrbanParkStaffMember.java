import java.io.IOException;
import java.util.ArrayList;


public class UI_UrbanParkStaffMember extends UI_AbstractUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5078784213187752662L;

	public UI_UrbanParkStaffMember() {
		
	}
	
	/*
	 * Displays the menu for a UP staff member.
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		//result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.SEARCH_VOL_LASTNAME);
		result.add(MenuOptions.LOGOUT);
			
		return result;
	}
	
	/*
	 * Search for a volunteer by last name.
	 */
	public void volunteerSearch() throws IOException {

		String name;
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.VOLUNTEER_SEARCH_TITLE);
		result.add(MenuOptions.VOLUNTEER_SEARCH_PROMPT);
		IODriver.menuBoxNotNumbered(result);
		System.out.print(">");
		name = IODriver.input.nextLine();
		ArrayList<Volunteer> volunteers = IODriver.storedData.searchVolunteerByLname(name);
		if(volunteers.size() > 0) {
				IODriver.clearConsole();
			for(Volunteer vol: volunteers ) {
				System.out.println(vol.toString() + "\n");
			}

		} else if(name.equals("0")) {
				IODriver.clearConsole();
		}else {
				IODriver.clearConsole();
			System.out.println("No volunteer with last name " + name + " exists. Try again.\n");
			volunteerSearch();
		}
	}

//	@Override
//	public ArrayList<MenuOptions> jobOptionsMenu() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
