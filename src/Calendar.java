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
 * @author Heather modified by Sean 
 * @date 3/8/16
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
		//recordOfJobs = theRecordOfJobs;
	}
	
	/*
	 * Constructs a calendar
	 */
	public Calendar() {
		totalPendingJobs = 0;
	}


	/**
	 * Checks through various business rules to see if job is able to be added to current list of jobs.
	 * 
	 * @param theJob
	 * @return Returns true if job is able to added to current list, else false.
	 */
	public boolean verifyJob(Job theJob) {
		boolean canAdd = false;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		GregorianCalendar curDay = (GregorianCalendar) GregorianCalendar.getInstance();
		Date myDate = curDay.getTime();
		Date jobStartDate = null;
		try {
			jobStartDate = sdf.parse(theJob.startDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		curDay.add(java.util.Calendar.MONTH, 3);
		Date maxJobDate = curDay.getTime();
		
		if(checkTotalPendingJobs() && checkJobDuration(theJob)){
				//business rule 2

				if(jobStartDate != null){				
					if(canAddInWorkWeek(jobStartDate)){
					//business rule 5
						if(checkDate(jobStartDate,maxJobDate, myDate)){					
								totalPendingJobs++;
								canAdd = true;					
						}
					
					}
				}		
		}
		return canAdd;
	}
	
	
	/**
	 * A job may not be added if the total number of pending jobs during that week 
	 * (3 days on either side of the job days) is currently 5.In other words, during any 
	 * consecutive 7 day period there can be no more than 5 jobs.
	 * @param theDate The starting date to calculate number of pending jobs.
	 * @return Returns the current number of pending jobs in a given work week.
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
		//checks which days of week are in current work week and fills arrays.
		for(i =0; i < 7; i++){
			curDay.add(temp,  i-3);
			pastDays[i] = curDay.get(GregorianCalendar.DAY_OF_WEEK);
			week[i] = curDay.get(GregorianCalendar.WEEK_OF_MONTH);
			curDay.setTime(theDate);
		}
		curDay.setTime(theDate);
		//compares each element of job list and if date is in current work week, increments week total.
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
	/**

	 * A job may not be added if the total number of pending jobs is currently 30.
	 *(Business rule 1)
	 * @return Returns True if total is less than 30 else false.
	 */
	public boolean checkTotalPendingJobs(){
		boolean canAdd = false;
		try{
			if(totalPendingJobs < 30){
			canAdd = true;
			}else {
				throw new TooManyPendingJobsException();
			}
			
		}catch(TooManyPendingJobsException j){
			System.err.println(j.getMessage());
		}
		return canAdd;
	}
	/**
	 * A job may not be scheduled that lasts more than two days(Business rule 4).
	 * @param theJob Returns True if job duration is less than or equal to 2 days else false.
	 * @return
	 */
	public boolean checkJobDuration(Job theJob){
		boolean canAdd = false;
		try{
			int duration =Integer.parseInt(theJob.duration); 
			if( duration > 0 && duration<= 2){
				canAdd = true;
			}else {
				throw new JobExceedsMaxDurationException();
			}
		}catch(JobExceedsMaxDurationException j){
			System.err.println(j.getMessage());
		}
		
		return canAdd;
	}
	/**
	 * A job may not be added that is in the past 
	 * or more than three months in the future(Business rule 5).
	 * @param jobStartDate Date job starts.
	 * @param maxJobDate Last date able to be scheduled.
	 * @param theDate Current date.
	 * @return Returns true if job is before end date and not in the past, else false.
	 */
	public boolean checkDate(Date jobStartDate, Date maxJobDate, Date theDate){
		boolean canAdd = false;
		try{
			if(jobStartDate.before(maxJobDate) && jobStartDate.after(theDate)){
				canAdd = true;
			}else {
				throw new JobDateExceedsBoundsException();
			}
		}catch(JobDateExceedsBoundsException j){
			System.err.println(j.getMessage());
		}
		return canAdd;
	}
	/**
	 *  A job may not be added if the total number of pending jobs during that week 
	 * (3 days on either side of the job days) is currently 5.In other words, during any 
	 * consecutive 7 day period there can be no more than 5 jobs.
	 * Boolean test to see if total number in work week is less than 5
	 * @param theDate the job start date
	 * @return Returns true if the current work week has less than 5 jobs else false.
	 */
	public boolean canAddInWorkWeek(Date theDate){
		boolean canAdd = false;
		try{
		if(calculateWeekPendingJobs(theDate)< 5){
			canAdd = true;
		}else {
			throw new TooManyPendingJobsInWorkWeekException();
		}
		}catch(TooManyPendingJobsInWorkWeekException t){
			System.err.println(t.getMessage());
		}
		return canAdd;
	}
	
	/*
	 * Set the totalPending job field to number of upcoming jobs
	 */
	public void setTotalPendingJobs() {
		totalPendingJobs = 0;
		GregorianCalendar curDay = (GregorianCalendar) GregorianCalendar.getInstance();
		for(Job j : IODriver.storedData.getJobs()) {
			if(curDay.before(j.getStartDate())) {
				totalPendingJobs++;
			}
		}
	}
}

