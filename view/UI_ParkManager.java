import java.util.ArrayList;
/**
 * Represents a Park Manager's UI.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/28/2016
 * @version 1.0
 */
public class UI_ParkManager extends UI_AbstractUser {

	public UI_ParkManager() {
		
	}

	/*
	 * Park Manager's display menu.
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
		result.add(MenuOptions.VIEW_JOBS_MANAGED);
		result.add(MenuOptions.ADD_A_JOB);
		result.add(MenuOptions.EXIT);
			
		return result;
	}
}
