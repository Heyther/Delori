import java.util.ArrayList;
import java.util.List;


/**
 * Represents a calendar full of jobs.
 * 
 * @author Heather
 * @date
 */
public class Calendar {

	public int totalPendingJobs;
	public List<Job> recordOfJobs;
	public List<AbstractUser> recordOfUsers;
	
	/*
	 * Constructs a calendar
	 */
	public Calendar(List<AbstractUser> theRecordOfUsers, List<Job> theRecordOfJobs) {
		totalPendingJobs = 0;
		recordOfJobs = theRecordOfJobs;
		recordOfUsers = theRecordOfUsers;
	}
	
	/*
	 * Add job to list of pending jobs
	 */
	public void addJobToList(Job theJob) {
		String[] start = theJob.getStartDate().split("/");
		String[] end = theJob.getEndDate().split("/");
		// check if job is not over 2-days duration
		//if (start[2].equals(end[2]) & Integer.parseInt(start[0]) >= Integer.parseInt(end[0]) 
										//& Integer.parseInt(end[0]) - Integer.parseInt(start[0]) < 3 ) {
			recordOfJobs.add(theJob);
			totalPendingJobs++;
		//}
	}
	
	public void deleteFromJobList(Job theJob){
		recordOfJobs.remove(theJob);
		totalPendingJobs--;
	}
	
	/*
	 * We can either directly access the field or perhaps have this method...
	 * we should decide soon.
	 */
	public List<Job> getListOfPendingJobs() {
		return recordOfJobs;
	}
	
	
}
