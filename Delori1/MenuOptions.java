
public enum MenuOptions {
	OPTION_ONE("View upcoming jobs"),
	OPTION_TWO("View enrolled jobs"),
	OPTION_THREE("View upcoming jobs"),
	OPTION_FOUR("Add a job"),
	OPTION_FIVE("View all volunteers"),
	OPTION_SIX("View upcoming jobs"),
	OPTION_SEVEN("Exit"),
	OPTION_EIGHT("Search volunteer by last name"),
	OPTION_NINE("View job details"),
	OPTION_TEN(""),
	OPTION_ELEVEN(""),
	OPTION_TWELVE(""),
	OPTION_THIRTEEN(""),
	OPTION_FOURTEEN(""),
	OPTION_FIFTEEN(""),
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
