import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Represents a Park Manager.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 2/16/2016
 * @version 2.0
 */

public class ParkManager extends AbstractUser 
{

	private static final long serialVersionUID = 3323557037867953268L;
	protected UserStatus role;
	private String parkName;
	private ArrayList<Job> jobsManaging;
	
	/*
	 * Constructs a Park Manager.
	 */
	public ParkManager(String theFname, String theLname, String theEmail, String thePark)
	{
		super(theFname, theLname, theEmail);
		this.parkName = thePark;
		role = UserStatus.PARKMANAGER;
		jobsManaging = new ArrayList<Job>();
	}
	
	/*
	 * Displays the user's last name, first name, and email.
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return getLname() + ", " + getFname() + "\nEmail: " + getEmail();
	}
	
	/*
	 * Creates a new job with the details that have been set by the UI 
	 * and adds it to the main job list and the park manager's job list
	 * Returns the Job if one was created, null otherwise
	 * U1
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
	 * Displays options on a job.
	 */
	/*
	public void jobDetailsMenu(Job theJob) throws IOException {
		System.out.println("\nPlease type a number: \n "
				+ "1) Edit job \n "
				+ "2) Cancel job\n " 
				+ "3) View signed-up volunteers\n "
				+ "4) Exit " );
		String response = IODriver.input.nextLine();
		
		switch (response){
		case "1": editJob(theJob);
			break;
		case "2": cancelJob(theJob);
			break;
		case "3": viewEnrolledVolunteers(theJob);
			break;
		case "4": IODriver.clearConsole();
			break;
		default: 
			
			jobDetailsMenu(theJob); //Invalid response. Try again.
			break;
		}
	}
	*/
	
	/*
	 * Cancel a job (Delete it from the list of jobs)
	 * U2
	 */
	public void cancelJob(Job theJob) throws IOException {
		this.jobsManaging.remove(theJob); //delete job from park manager's personal job list
		IODriver.storedData.deleteJob(theJob);//delete job from main job list
	}
	
	public void editJobTitle(Job theJob, String newTitle) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setJobTitle(newTitle);
		IODriver.storedData.addJob(theJob);
		this.jobsManaging.add(theJob);
	}
	
	public void editJobDate(Job theJob, String newDate) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setStartDate(newDate);
		IODriver.storedData.addJob(theJob);
		this.jobsManaging.add(theJob);
	}
	
	public void editJobTime(Job theJob, String newTime) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setStartTime(newTime);
		IODriver.storedData.addJob(theJob);
		this.jobsManaging.add(theJob);
	}
	
	public void editJobDuration(Job theJob, String newDuration) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setDuration(newDuration);
		IODriver.storedData.addJob(theJob);
		this.jobsManaging.add(theJob);
	}
	
	public void editJobDescription(Job theJob, String newDescription) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setDescription(newDescription);
		IODriver.storedData.addJob(theJob);
		this.jobsManaging.add(theJob);
	}
	
	public void editJobLightSlots(Job theJob, Integer newLightSlots) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setLightSlots(newLightSlots);
		IODriver.storedData.addJob(theJob);
		this.jobsManaging.add(theJob);
	}
	
	public void editJobMediumSlots(Job theJob, Integer newMediumSlots) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setMediumSlots(newMediumSlots);
		IODriver.storedData.addJob(theJob);
		this.jobsManaging.add(theJob);
	}
	
	public void editJobHeavySlots(Job theJob, Integer newHeavySlots) throws IOException{
		IODriver.storedData.deleteJob(theJob);
		this.jobsManaging.remove(theJob);
		theJob.setHeavySlots(newHeavySlots);
		IODriver.storedData.addJob(theJob);
		this.jobsManaging.add(theJob);
	}
	
	public ArrayList<Job> getJobsManaging(){
		return this.jobsManaging;
	}
	
	/*
	 * View all the park manager's upcoming jobs
	 * U8
	 */
	/*
	public void viewJobsManaged() throws IOException {
		if (this.jobsManaging.size() > 0) {
			//view list of all the park manager's upcoming jobs
			int i;
			for (i = 0; i < this.jobsManaging.size(); i++){
				System.out.print((i+1) + ". ");
				System.out.println(this.jobsManaging.get(i).jobSummary());
			}
			System.out.print("\nType a number to select a job or type 0 to go back:\n>");
			int jobNumber = Integer.parseInt(IODriver.input.nextLine());
			//Keep prompting until good input in received
			while (jobNumber > jobsManaging.size()) {
				System.out.println("Invalid input. Please try again.");
				jobNumber = Integer.parseInt(IODriver.input.nextLine());
			}
			//If a job number was typed, print job details and options (If 0 was typed, do nothing)
			if (jobNumber != 0){
				
				System.out.println(jobsManaging.get(jobNumber-1));
				jobDetailsMenu(jobsManaging.get(jobNumber-1));
			} else {
				IODriver.clearConsole();
			}
		} else {
			System.out.println("You currently have no jobs up.");
		}
	}
	 */

	
	/*
	 * Retrieves the user's role.
	 * @see AbstractUser#getRole()
	 */
	public UserStatus getRole() {
		return role;
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
