/**
 * Represents system exceptions for the Urban Parks program.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/28/2016
 * @version 1.0
 */
public class CustomExceptions extends Exception {

	
	private static final long serialVersionUID = 5274032867943571144L;
	
	public CustomExceptions() {
		super();
	}
	
	public String toString() {
		return getClass().getName();
	}
}	
	
class SignUpException extends CustomExceptions {
	
	private static final long serialVersionUID = 7981376760024255950L;

	public SignUpException() {
		super();
	}
	public String toString() {
		return getClass().getName() + ": You are currently not enrolled in any jobs. Please, sign up.";
	}
}

class InvalidUserInput extends CustomExceptions {
	
	private static final long serialVersionUID = 8091074594871466790L;
	
	public InvalidUserInput() {
		super();
	}
	public String toString() {
		return getClass().getName() + ": You are currently not enrolled in any jobs. Please, sign up.";
	}
}
	

