import java.util.Scanner;

/**
 * Driver class. Controls menus and user types.
 * 
 * @author: Luciana (Modified by Heather)
 * @date
 */

public class IODriver{
	public static Scanner input;
	public static Calendar calendar;
	boolean quit;
	
	public IODriver(){
		input = new Scanner(System.in);
		input.useDelimiter("\n");
		
		calendar = new Calendar();
		quit = false;
	}
	
	/*
	 * Asks the user to enter an email address and returns the user object 
	 * associated with that email
	 * If the email is not in the system, it prompts the user again
	 */
	public void login(){
		System.out.println("Enter your email address: ");
		//Find user in user list with email input.next()
		//If no such user exists, ask for email again
		//System.out.println("No account was found with that email address. Please try again. ");
		//System.out.println("Enter your email address: ");
		//Return the User object
	}
	
	/*
	 * Calls the appropriate menu function depending on what type of user it gets passed
	 */
	public void printMenu(AbstractUser currentUser){
		switch (currentUser.getRole()){
		case VOLUNTEER: //volunteerMenu(currentUser);
			break;
		case PARKMANAGER: parkManagerMenu((ParkManager)currentUser);
			break;
		case UPSMEMBER: //staffMenu(currentUser); 
			break;
		default: 
			break;
		}
	}

	/*
	 * Prints the menu for volunteers
	 */
	public void volunteerMenu(){
		
	}
	
	/*
	 * Prints the menu for staff members
	 */
	public void staffMenu(){
		
	}
	
	/*
	 * Prints the menu for park managers
	 */
	public void parkManagerMenu(ParkManager manager){
		System.out.println(manager.toString() + " [Park Manager]");
		System.out.println("Please type a number. \n"
				+ "1) View my upcoming jobs \n"
				+ "2) Add new job \n"
				+ "3) Edit or cancel a job \n"
				+ "4) Exit ");
		String response = this.input.next();
		
		switch (response){
		case "1": manager.viewJobs();
			break;
		case "2": manager.addJob();
			break;
		case "3": 
			break;
		case "4": quit = true;
			break; 
		default: parkManagerMenu(manager);
			break;
		}
	}
	
	/*
	 * Prints the menu for volunteers
	 
	public void jobDetailsMenu(AbstractUser currentUser){
		switch (currentUser.getRole()){
		case VOLUNTEER: //volunteerMenu((Volunteer)currentUser);
			break;
		case PARKMANAGER: //parkManagerMenu((ParkManager)currentUser);
			break;
		case UPSMEMBER: //staffMenu((UrbanParkStaffMember)currentUser);
			break;
		default: 
			break;
		}
	}
	*/
	
	public static void main(String[] args){
		IODriver io = new IODriver();
		System.out.println("Welcome to Urban Parks! \n");
		
		ParkManager testParkMan = new ParkManager("Mary", "Thompson", "mthom@gmail.com", "Green Park", UserStatus.PARKMANAGER);
		
		//Keep running menu until user chooses to exit
		while(!io.quit){ 
			io.printMenu(testParkMan);
		}
		System.out.println("\nGoodbye");
	}
}
