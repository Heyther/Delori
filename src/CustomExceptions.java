/**
 * Represents system exceptions for the Urban Parks program.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
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
				+ " 1 job with the same start day.");
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

class VolunteerNotFoundException extends CustomExceptions {

	private static final long serialVersionUID = 1L;

	public VolunteerNotFoundException() {
		super("No volunteer with that last name exists.  Try again.");
	} 
	public String toString() {
		return getClass().getName();
	}
	
}

class InvalidJob extends CustomExceptions {

	private static final long serialVersionUID = -4664167119716193265L;
	
	public InvalidJob() {
		super("One of the fields entered is invalid. The job could not be created.");
	}
	public String toString() {
		return getClass().getName();
	}
}

class JobScheduleConflict extends CustomExceptions {

	private static final long serialVersionUID = 6181125847932663311L;
	
	public JobScheduleConflict() {
		super("A job cannot be added on that day.");
	}
	public String toString() {
		return getClass().getName();
	}
}

class NoManagedJobsException extends CustomExceptions {

	private static final long serialVersionUID = 6719553768306932668L;
	
	public NoManagedJobsException() {
		super("You do not have any upcoming jobs in your park");
	}
	public String toString() {
		return getClass().getName();
	}
}

class CanNotEditJobException extends CustomExceptions {
	
	private static final long serialVersionUID = 1L;

	public CanNotEditJobException() {
		super("This job has volunteers signed up for it and cannot be edited.");
	}
	public String toString() {
		return getClass().getName();
	}
}
	
	

