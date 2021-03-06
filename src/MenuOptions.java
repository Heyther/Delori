/**
 * Represents the different common options for UI.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 2.0
 */
public enum MenuOptions {

	// User selection options
	LOGIN("Login"),
	LOGOUT("Logout"),
	EXIT("Exit"),
	ADD_A_JOB("Add a job"),
	VIEW_ALL_VOL("View all volunteers"),
	VIEW_UPCOMING_JOBS("View upcoming jobs"),
	SEARCH_VOL_LASTNAME("Search volunteer by last name"),
	VIEW_JOB_DETAIL("View job details"),
	VIEW_JOBS_MANAGED("View managed jobs"),
	SELECT_JOB_NUMBER("Select a job number to see more options"),
	JOB_OPTIONS("Job Options"),
	HOME("Back to home menu"),
	
		
	// Volunteer specific options
	SIGN_UP("Sign up for a job"),
	VIEW_ENROLLED_JOBS("View enrolled jobs"),
	SELECT_WORKLOAD("Please select a workload: \n 1. Light \n 2. Medium \n 3. Heavy"),
	
	//Park Manager specific options
	EDIT_JOB("Edit job details"),
	CANCEL_JOB("Cancel job"),
	VIEW_ENROLLED_VOLUNTEERS("View signed-up volunteers"),
	
	// Staff specific options
	VOLUNTEER_SEARCH_TITLE("Volunteer Search"),
	VOLUNTEER_SEARCH_PROMPT("Enter volunteer's last name or '0' to go back:"),
	
	// Greetings or Titles
	OPTION_LOGIN("Login to Urban Parks!"),
	OPTION_WELCOME("Welcome to Urban Parks!"),
	OPTION_ENTER_EMAIL("Please enter your email."),
	LOGGING_OUT("Sucessfully logged out...")
    ;
	
	private final String text;

	
	// Constructor for enums
	private MenuOptions(String text) {
		this.text = text;
	}
	
	/* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
