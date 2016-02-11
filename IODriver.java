import java.util.Scanner;

/**
 * Driver class. Controls menus and user types.
 * 
 * @author: Luciana (Modified by Heather)
 * @date
 */

public class IODriver{
	public static Scanner input;
	boolean quit = false;
	
	public IODriver(){
		input = new Scanner(System.in);
		input.useDelimiter("\n");
	}
	
	public void printMenu(AbstractUser currentUser){
		switch (currentUser.getRole()){
		case VOLUNTEER: //volunteerMenu(currentUser);
			break;
		case PARKMANAGER: //parkManagerMenu((ParkManager)currentUser);
			break;
		case UPSMEMBER: //staffMenu(currentUser); 
			break;
		default: 
			break;
		}
	}

	public void volunteerMenu(){
		
	}
	
	public void staffMenu(){
		
	}
	
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
