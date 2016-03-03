import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Volunteer's UI.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/28/2016
 * @version 1.0
 */
public class UI_Volunteer extends UI_AbstractUser {

	private static final long serialVersionUID = -7797674674958830828L;
	public Volunteer user;

	public UI_Volunteer() {
		// sets specific user to their UI  (this could be done in control)
		user = (Volunteer) IODriver.currentUser; // <- is this okay to have here 
												 //    instead of in IODriver?
	}
	
	/*
	 * View joined jobs specific to a volunteer. (U7)
	 */
	public void viewEnrolledJobs() {
		StringBuilder result = new StringBuilder();
		try {
			if (user.getEnrolledJobs().size() > 0) {
				result.append("Jobs you are enrolled in:\n");
				for (Job j : user.getEnrolledJobs()) {
					result.append(j.getJobTitle() + "\n");
				}
			}
		} catch (NoEnrolledJobsPresentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(result);
	}
	
	public void signUpView() throws IOException {
		try {
			System.out.print("Please, select a job number or enter 0 to go back:\n>");
			int responseIndex = Integer.parseInt(IODriver.input.nextLine());
			
			if (responseIndex != 0) {
				System.out.println(MenuOptions.SELECT_WORKLOAD);
				int responseWorkloadIndex = Integer.parseInt(IODriver.input.nextLine());
				user.setWorkloadResponse(responseWorkloadIndex);
				((Volunteer) user).signUp(IODriver.storedData.getJobs().get(responseWorkloadIndex-1));
			} else {
				//do nothing
				IODriver.clearConsole();
			}
		} catch (JobSlotFilledException | SignUpOnSameDayException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	/*
	 * Displays the menu for a volunteer.
	 * @see AbstractUser#userDisplayMenu()
	 */
	@Override
	public ArrayList<MenuOptions> usersHomeMenu() {
		//showUser();
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.OPTION_WELCOME);
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.SIGN_UP);
		result.add(MenuOptions.VIEW_ENROLLED_JOBS);
		result.add(MenuOptions.EXIT);

		return result;
	}
//
//	@Override
//	public ArrayList<MenuOptions> jobOptionsMenu() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//

	
}
