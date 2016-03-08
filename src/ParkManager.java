import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Park Manager.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */

public class ParkManager extends AbstractUser {

	private static final long serialVersionUID = 3323557037867953268L;
	protected UserStatus role;
	private String parkName;
	public ArrayList<Job> jobsManaging; //Made public for testing
	
	/*
	 * Constructs a Park Manager.
	 */
	public ParkManager(String theFname, String theLname, String theEmail, String thePark) {
		super(theFname, theLname, theEmail);
		this.parkName = thePark;
		role = UserStatus.PARKMANAGER;
		jobsManaging = new ArrayList<Job>();
	}
	
	/*
	 * Creates a new job with the details that have been set by the UI 
	 * and adds it to the main job list and the park manager's job list
	 * Returns the Job if one was created, null otherwise
	 * (U1: As a Park Manager I want to submit a new job.)
	 */
	public Job addJob(String jobTitle, String start, String time, String dur, 
			String descript, int light, int med, int heavy) throws IOException {
		
		//Create new Job object and add to main job list and park manager's job list
		Job newJob = new Job(jobTitle, start, time, dur, this.parkName, this.getFullName(), descript, light, med, heavy);
		
		if(IODriver.storedData.calendar.verifyJob(newJob)) {
			this.jobsManaging.add(newJob);
			IODriver.storedData.addJob(newJob);
			return newJob;
		}
		else {
			return null;
		}
	}
	
	/*
	 * Cancel a job (Delete it from the lists of jobs)
	 * (U2: As a Park Manager I want to delete a job.)
	 */
	public void cancelJob(Job theJob) throws IOException {
		this.jobsManaging.remove(theJob); //delete job from park manager's personal job list
		IODriver.storedData.deleteJob(theJob);//delete job from main job list
	}
	
	/*
	 * Edit a job's title
	 */
	public void editJobTitle(Job theJob, String newTitle) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setJobTitle(newTitle);
		jobsManaging.add(theJob);
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Edit a job's start date
	 */
	public void editJobDate(Job theJob, String newDate) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setStartDate(newDate);
		this.jobsManaging.add(theJob);
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Edit a job's start time
	 */
	public void editJobTime(Job theJob, String newTime) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setStartTime(newTime);
		this.jobsManaging.add(theJob);
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Edit a job's duration
	 */
	public void editJobDuration(Job theJob, String newDuration) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setDuration(newDuration);
		this.jobsManaging.add(theJob);
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Edit a job's description
	 */
	public void editJobDescription(Job theJob, String newDescription) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setDescription(newDescription);
		this.jobsManaging.add(theJob);
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Edit a job's number of slots that volunteers can sign up for with light workload
	 */
	public void editJobLightSlots(Job theJob, Integer newLightSlots) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setLightSlots(newLightSlots);
		this.jobsManaging.add(theJob);
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Edit a job's number of slots that volunteers can sign up for with medium workload
	 */
	public void editJobMediumSlots(Job theJob, Integer newMediumSlots) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setMediumSlots(newMediumSlots);
		this.jobsManaging.add(theJob);
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Edit a job's number of slots that volunteers can sign up for with heavy workload
	 */
	public void editJobHeavySlots(Job theJob, Integer newHeavySlots) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setHeavySlots(newHeavySlots);
		this.jobsManaging.add(theJob);
		IODriver.storedData.addJob(theJob);
	}
	
	/*
	 * Returns the list of jobs managed by this manager if there are any. 
	 * If there are no jobs, throws NoManagedJobsException
	 */
	public ArrayList<Job> getJobsManaging() throws NoManagedJobsException {
		if (this.jobsManaging.size() == 0) {
			throw new NoManagedJobsException();
		}
		else {
			return this.jobsManaging;
		}
	}
	
	/*
	 * Displays the user's last name, first name, and email.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getLname() + ", " + getFname() + "\nEmail: " + getEmail();
	}
	/*
	 * Returns this park manager's park name as a String
	 */
	public String getParkName() {
		return this.parkName;
	}
	
	/*
	 * Retrieves the user's role.
	 * @see AbstractUser#getRole()
	 */
	public UserStatus getRole() {
		return role;
	}
	
	/*
	 * Gets the String representation of the user's role.
	 */
	public String getRoleString() {
		return "Park Manager";
	}

	/*
	 * Sets the user's role.
	 */
	public void setRole(UserStatus role) {
		this.role = role;
	}

    /*
     * Hashcode for equals
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }
	
	/*
	 * Checks if the object is-a Park Manager.
	 * @see AbstractUser#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object theObject){
		if (super.equals(theObject)) {
			ParkManager v = (ParkManager) theObject;
			if (getRole().equals(v.getRole())) { return true; };
		}
		return false;
	}
}