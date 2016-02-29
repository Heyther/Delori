import java.util.ArrayList;


public class UI_UrbanParkStaffMember extends UI_AbstractUser {

	public UI_UrbanParkStaffMember() {
		
	}
	
	/*
	 * Displays the menu for a UP staff member.
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
}
