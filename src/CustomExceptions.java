/**
 * Represents system exceptions for the Urban Parks program.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/28/2016
 * @version 1.0
 */
public class CustomExceptions extends Exception {

	
	private static final long serialVersionUID = 5274032867943571144L;
	
	public CustomExceptions(String theString) {
		super(theString);
	}
	
	public String toString() {
		return getClass().getName();
	}
}	
	
class SignUpOnSameDayException extends CustomExceptions {
	
	private static final long serialVersionUID = 7981376760024255950L;

	public SignUpOnSameDayException() {
		super("You are not allowed to signUp for more than" 
				+ "1 job with the same start day.");
	}
	public String toString() {
		return getClass().getName();
	}
}

class NoEnrolledJobsPresentException extends CustomExceptions {
	
	private static final long serialVersionUID = -5758432044218810823L;
	
	public NoEnrolledJobsPresentException() {
		super("You are currently not enrolled in any jobs." 
				+ " Please, sign up.");
	}
	public String toString() {
		return getClass().getName();
	}
}

class JobSlotFilledException extends CustomExceptions {

	private static final long serialVersionUID = 3743560178947307530L;
	
	public JobSlotFilledException() {
		super(": Sorry, all slots of that category are filled. "
				+ "Please, sign up for a different slot or job.");
	}
	
	public String toString() {
		return getClass().getName();
	}
}

class InvalidUserInput extends CustomExceptions {
	
	private static final long serialVersionUID = 8091074594871466790L;
	
	public InvalidUserInput() {
		super("Invalid user input.");
	}
	public String toString() {
		return getClass().getName();
	}
}


	
