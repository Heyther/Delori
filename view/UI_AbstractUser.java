import java.util.ArrayList;


public abstract class UI_AbstractUser extends CustomExceptions {
	
	public UI_AbstractUser() {
		
	}
	
	// Displays a menu with options for a user.
	public abstract ArrayList<MenuOptions> usersHomeMenu();

	
}
