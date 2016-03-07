import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Volunteer's UI.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public class UI_Volunteer extends UI_AbstractUser {

	private static final long serialVersionUID = -7797674674958830828L;
	public Volunteer user;

	/*
	 * Constructs volunteer UI methods
	 */
	public UI_Volunteer() {
		user = (Volunteer) IODriver.currentUser; // sets specific user to their UI
	}
	
	/*
	 * View joined jobs specific to a volunteer. 
	 * (U7: As a Volunteer I want to view the jobs I am signed up for)
	 */
	public void viewEnrolledJobs() {
		StringBuilder result = new StringBuilder();
		try {
			if (user.getEnrolledJobs().size() > 0) {
				result.append("Jobs you are enrolled in:\n");
				for (Job j : user.getEnrolledJobs()) {
					result.append(j.jobSummary() + "\n");
				}
			}
		} catch (NoEnrolledJobsPresentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(result);
	}
	
	public void signUpView() throws IOException {
		try {
			int jobIndex = selectJobNumber(IODriver.storedData.getJobs().size());
			if (jobIndex != 0) {
				System.out.println(MenuOptions.SELECT_WORKLOAD);
				int responseWorkloadIndex = Integer.parseInt(IODriver.input.nextLine());
				user.setWorkloadResponse(responseWorkloadIndex);
				((Volunteer) user).signUp(IODriver.storedData.getJobs().get(jobIndex));
				System.out.println("You've signed up for " + IODriver.storedData.getJobs().get(jobIndex).jobTitle );
			} else {
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
		ArrayList<MenuOptions> result = new ArrayList<MenuOptions>();
		result.add(MenuOptions.VIEW_UPCOMING_JOBS);
		result.add(MenuOptions.VIEW_JOB_DETAIL);
		result.add(MenuOptions.SIGN_UP);
		result.add(MenuOptions.VIEW_ENROLLED_JOBS);
		result.add(MenuOptions.LOGOUT);

		return result;
	}	
}
