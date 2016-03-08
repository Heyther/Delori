import java.io.IOException;
import java.util.ArrayList;


public class UI_UrbanParkStaffMember extends UI_AbstractUser {

	
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
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.SEARCH_VOL_LASTNAME);
		result.add(MenuOptions.LOGOUT);
			
		return result;
	}
	
	/*
     * Search for a volunteer by last name.  Throws VolunteerNotFoundException is no
     * volunteer has specified last name.
     */
    public void volunteerSearch() throws IOException {
        String name;
        ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
        ArrayList<String> title = new ArrayList<String>();
        title.add(MenuOptions.VOLUNTEER_SEARCH_TITLE.toString());
        title.add(showUser());
        result.add(MenuOptions.VOLUNTEER_SEARCH_PROMPT);
        IODriver.menuBoxNotNumbered(title, result);
        System.out.print(">");
        name = IODriver.input.nextLine();
        if (name.equals("0")) {
            IODriver.clearConsole();
        } else {
            ArrayList<Volunteer> volunteers;
            try {
                volunteers = IODriver.storedData.searchVolunteerByLname(name);
                if (volunteers.size() > 0) {
                    IODriver.clearConsole();
                    for (Volunteer vol : volunteers) {
                        System.out.println(vol.toString() + "\n");
                    }
                }
            } catch (VolunteerNotFoundException e) {
                System.out.println(e.getMessage());
                volunteerSearch();
            }
        }
    }
}
