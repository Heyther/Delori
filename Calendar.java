import java.util.ArrayList;


/**
 * Represents a calendar full of jobs.
 * 
 * @author Heather
 * @date
 */
public class Calendar {

	public int totalPendingJobs;
	public ArrayList<Job> jobList;
	
	/*
	 * Constructs a calendar
	 */
	public Calendar() {
		totalPendingJobs = 0;
		jobList = new ArrayList<Job>();
		
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
			jobList.add(theJob);
			totalPendingJobs++;
		//}
	}
	
	public void deleteFromJobList(Job theJob){
		jobList.remove(theJob);
		totalPendingJobs--;
	}
	
	/*
	 * We can either directly access the field or perhaps have this method...
	 * we should decide soon.
	 */
	public ArrayList<Job> getListOfPendingJobs() {
		return jobList;
	}
	
	
}
