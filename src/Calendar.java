import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.GregorianCalendar;



/**
 * Represents a calendar full of jobs.
 * 
 * @authors: Luciana, Winfield, Heather, Sean
 * @date 2/16/2016
 * @version 1.0
 */
public class Calendar {
	public int totalPendingJobs;
	Data data;
	public List<Job> recordOfJobs;
	public List<AbstractUser> recordOfUsers;
	
	/*
	 * This method might not be needed anymore, 
	 * but will sit here and feel the shame
	 * buhahaha this zombie was revived by Sean <.<
	 */
	public Calendar(List<AbstractUser> theRecordOfUsers, List<Job> theRecordOfJobs) {
		totalPendingJobs = 0;
	}

	/*
	 * Checks if the job is legitimate.
	 */
	public boolean verifyJob(Job theJob) {
		//Job checkedJob = null;
		boolean canAdd = false;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		//String[] start = theJob.getStartDate().split("/");
		//String[] end = theJob.getEndDate().split("/");
		GregorianCalendar curDay = (GregorianCalendar) GregorianCalendar.getInstance();
		Date myDate = curDay.getTime();
		Date jobStartDate = null;
		Date jobEndDate = null;
		try {
			jobStartDate = sdf.parse(theJob.startDate);


		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		curDay.add(java.util.Calendar.MONTH, 3);
		Date maxJobDate = curDay.getTime();
		// check if job is not over 2-days duration
		//if (start[2].equals(end[2]) & Integer.parseInt(start[0]) >= Integer.parseInt(end[0]) 
										//& Integer.parseInt(end[0]) - Integer.parseInt(start[0]) < 3 ) {
		//business rules 1 & 4
		if(totalPendingJobs < 30 && Integer.parseInt(theJob.duration) <= 2){
			try {
				//business rule 2
				if(jobStartDate != null){
					
				if(calculateWeekPendingJobs(jobStartDate) < 5){
					//business rules 5 & 6
					System.out.println("in validate"+calculateWeekPendingJobs(sdf.parse(theJob.startDate)));
					if(jobStartDate.before(maxJobDate) && (jobStartDate.after(myDate))){
					
							totalPendingJobs++;
							canAdd = true;
						
					}
					
				}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
		}
		//}
		return canAdd;
	}
	
//	public void deleteFromJobList(Job theJob){
//		recordOfJobs.remove(theJob);
//		totalPendingJobs--;  // this line is now taken care of in Data's deleteJob
//	}
	
//	public List<Job> getListOfPendingJobs() {
//		return recordOfJobs;
//	}
	
	/*
	 * Checks that there is only 5 jobs at a time for any given week.
	 */
	public int calculateWeekPendingJobs(Date theDate){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		ListIterator<Job> jobItr = (ListIterator<Job>) IODriver.storedData.getJobs().listIterator();
		//For unit testing  Calendar only
		//ListIterator<Job> jobItr = (ListIterator<Job>)recordOfJobs.listIterator();
		int weekTotal =0;
		GregorianCalendar curDay = (GregorianCalendar) GregorianCalendar.getInstance();
		curDay.setTime(theDate);
		int [] pastDays = new int[7];
		int [] week = new int[7];
		int i =0;
		int temp = GregorianCalendar.DAY_OF_WEEK;
		
		for(i =0; i < 7; i++){
			curDay.add(temp,  i-3);
			pastDays[i] = curDay.get(GregorianCalendar.DAY_OF_WEEK);
			week[i] = curDay.get(GregorianCalendar.WEEK_OF_MONTH);
			curDay.setTime(theDate);
		}
		curDay.setTime(theDate);
		while(jobItr.hasNext()){
			try {
				Date tempDate = sdf.parse(jobItr.next().startDate);
				curDay.setTime(tempDate);
				for(i =0; i < 7; i++){
					if(pastDays[i] == curDay.get(GregorianCalendar.DAY_OF_WEEK)
							&& week[i] == curDay.get(GregorianCalendar.WEEK_OF_MONTH)){
						weekTotal++;		
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return weekTotal;
	}
}
