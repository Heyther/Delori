
public enum MenuOptions {

	
	// User selection options
	VIEW_ENROLLED_JOBS("View enrolled jobs"),
	ADD_A_JOB("Add a job"),
	VIEW_ALL_VOL("View all volunteers"),
	VIEW_UPCOMING_JOBS("View upcoming jobs"),
	EXIT("Exit"),
	SEARCH_VOL_LASTNAME("Search volunteer by last name"),
	VIEW_JOB_DETAIL("View job details"),
	SIGN_UP("Sign up for a job"),
	//CANCEL_ENROLLED(""),
	
		
	// Greetings or Titles
	OPTION_LOGIN("Login to Urban Parks!"),
	OPTION_WELCOME("Welcome to Urban Parks!"),
	OPTION_ENTER_EMAIL("Please, enter your email.")
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
