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
		//showUser();
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.SEARCH_VOL_LASTNAME);
		result.add(MenuOptions.EXIT);
			
		return result;
	}
	
	/*
	 * Search for a volunteer by last name.
	 */
	public void volunteerSearch() {

		String name;
		System.out.println("Volunteer Search:");
		System.out.print("Enter volunteers last name or '0' to go back:\n>");
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
			//usersHomeMenu();

		} else if(name.equals("0")) {
			//usersHomeMenu();
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

//	@Override
//	public ArrayList<MenuOptions> jobOptionsMenu() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
