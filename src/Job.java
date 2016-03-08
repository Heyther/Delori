import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * A job class, that can be used in console i/o.
 * It contains 3 different view "menus" depending on the user type.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public class Job implements Serializable {
	static final int LIGHT = 1;
	static final int MEDIUM = 2;
	static final int HEAVY = 3;
	private static final long serialVersionUID = -8923477868319938597L;
	String jobTitle;
	String startDate;
	String startTime;
	String endDate;
	String duration;
	String location;
	String parkManager;
	String description;
	int lightSlots;
	int mediumSlots;
	int heavySlots;
	int totalSlots;
	ArrayList<Volunteer> lightVolunteers;
	ArrayList<Volunteer> mediumVolunteers;
	ArrayList<Volunteer> heavyVolunteers;


	/*
	 * Job constructor
	 */
	public Job(String jobTitle, String start, String time, String dur, String loc, String parkMan, 
			String descript, int light, int med, int heavy) {
		IODriver.input = new Scanner(System.in);
		this.jobTitle = jobTitle;
		this.startDate = start;
		this.startTime = time;
		this.duration = dur;
		this.location = loc;
		this.parkManager = parkMan;
		this.description = descript;
		this.lightSlots = light;
		this.mediumSlots = med;
		this.heavySlots = heavy;
		this.totalSlots = light + med + heavy;

		lightVolunteers = new ArrayList<Volunteer>();
		mediumVolunteers = new ArrayList<Volunteer>();
		heavyVolunteers = new ArrayList<Volunteer>();   
	}


	/*
	 * Displays a job and all its details.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String toString = "Job Title: "+this.jobTitle+"\n"
				+"Date: "+this.startDate+"\n"
				+"Time: "+ this.startTime +"\n"
				+"Duration: "+ this.duration + " day(s)" +"\n"
				+"Park Manager: "+this.parkManager+ "\n" 
				+"Location: " +this.location+"\n"         
				+"Description: "+this.description+"\n"
				+"Light slots: "+this.lightSlots+"\n"
				+"Medium slots: "+this.mediumSlots+"\n" 
				+"Heavy slots: "+this.heavySlots;
		return toString;
	}

	/*
	 * Checks if the job is the same job.
	 */
	public boolean equals(Job other) {
		if (this.jobTitle.equals(other.jobTitle) && this.startDate.equals(other.startDate) 
				&& this.startTime.equals(other.startTime) && this.location.equals(other.location) 
				&& this.duration.equals(other.duration) && this.location.equals(other.location) && 
				this.lightSlots == other.lightSlots && this.mediumSlots == other.mediumSlots && this.heavySlots == other.heavySlots){
			return true;
		}
		else
			return false;
	}

	/*
	 * Getter for job title.
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/*
	 * Setter for job title.
	 */
	public void setJobTitle(String theJobTitle) {
		this.jobTitle = theJobTitle;
	}		

	/*
	 * Getter for start date.
	 */
	public String getStartDate() {
		return startDate;
	}
	/*
	 * Setter for start date.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/*
	 * Getter for start time.
	 */
	public String getStartTime() {
		return startTime;
	}
	/*
	 * Setter for start time.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/*
	 * Getter for duration.
	 */
	public String getDuration() {
		return duration;
	}
	/*
	 * Setter for duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/*
	 * Getter for location.
	 */
	public String getLocation() {
		return location;
	}
	/*
	 * Setter for a location. 
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/*
	 * Getter for a description.
	 */
	public String getDescription() {
		return description;
	}
	/*
	 * Setter for description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/*
	 **Getter for parkManager
	 */
	public String getParkManager() {
		return parkManager;
	}
	/*
	 * Setter for parkManager.
	 */
	public void setParkManager(String parkManager) {
		this.parkManager = parkManager;
	}
	/*
	 * Getter for lightSlots.
	 */
	public int getLightSlots() {
		return lightSlots;
	}
	/*
	 * Setter for LighSlots.
	 */
	public void setLightSlots(int lightSlots) {
		this.lightSlots = lightSlots;
	}
	/*
	 * Getter for medium slots.
	 */
	public int getMediumSlots() {
		return mediumSlots;
	}
	/*
	 * Setter for medium slots.
	 */
	public void setMediumSlots(int mediumSlots) {
		this.mediumSlots = mediumSlots;
	}
	/*
	 * Getter for heavy slots.
	 */
	public int getHeavySlots() {
		return heavySlots;
	}
	/*
	 * Setter for heavy slots.
	 */
	public void setHeavySlots(int heavySlots) {
		this.heavySlots = heavySlots;
	}
	/*
	 * Getter for total slots.
	 */
	public int getTotalSlots() {
		return totalSlots;
	}
	/*
	 * Setter for total slots.
	 */
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}

	/*
	 * Returns an array list of all Volunteer objects by combining the lists of 
	 * light, medium, and heavy workload volunteers
	 */
	public ArrayList<Volunteer> getVolunteers() {
		ArrayList<Volunteer> allVolunteers = new ArrayList<Volunteer>();
		allVolunteers.addAll(lightVolunteers);
		allVolunteers.addAll(mediumVolunteers);
		allVolunteers.addAll(heavyVolunteers);
		return allVolunteers;
	}

	/*
	 * Prints names of all volunteers who have signed up for this job to the console
	 */
	public void printVolunteers() {
		int i;
		System.out.println("Light Workload:");
		for (i = 0; i < lightVolunteers.size(); i++){
			System.out.println(lightVolunteers.get(i).getFullName());
		}

		System.out.println("Medium Workload:");
		for (i = 0; i < mediumVolunteers.size(); i++){
			System.out.println(mediumVolunteers.get(i).getFullName());
		}

		System.out.println("Heavy Workload:");
		for (i = 0; i < heavyVolunteers.size(); i++){
			System.out.println(heavyVolunteers.get(i).getFullName());
		}
	}

	/*
	 * Accepts a Volunteer and a workload and adds the volunteer to the appropriate list of volunteers
	 * Decrements number of available slots of that workload
	 */
	public boolean signUpVolunteer(Volunteer theVolunteer, int workload){
		switch (workload){
		case LIGHT:
			if (lightVolunteers.size() < lightSlots) {
				lightVolunteers.add(theVolunteer);
				totalSlots--;
				return true;
			}
			else return false;
		case MEDIUM:
			if (mediumVolunteers.size() < mediumSlots) {
				mediumVolunteers.add(theVolunteer);
				totalSlots--;
				return true;
			}
			else return false;
		case HEAVY:
			if (heavyVolunteers.size() < heavySlots) {
				heavyVolunteers.add(theVolunteer);
				totalSlots--;
				return true;
			}
			else return false;
		default: 
			break;
		}
		return false;
	}

	/*
	 * Accepts a volunteer and a workload and deletes the volunteer from the appropriate list of volunteers
	 * Increments number of available slots of that workload
	 */
	public void cancelVolunteer(Volunteer theVolunteer){
		if (lightVolunteers.contains(theVolunteer)){
			lightVolunteers.remove(theVolunteer);
			totalSlots++;
		}
		else if (mediumVolunteers.contains(theVolunteer)){
			mediumVolunteers.remove(theVolunteer);
			totalSlots++;
		}
		else if (heavyVolunteers.contains(theVolunteer)){
			heavyVolunteers.remove(theVolunteer);
			totalSlots++;
		}
	}

	/*
	 * Returns a brief summary of job.
	 */
	public String jobSummary() {
		return this.getJobTitle() + ", " + this.getLocation() + ", " + this.getStartDate() + ", " + this.getStartTime();
	}

	/*
	 * Check if job can be edited.
	 * @return true if no volunteers have signed up for the job.
	 */
	public boolean canEditJob() throws CanNotEditJobException {

		if(lightVolunteers.isEmpty() && mediumVolunteers.isEmpty() && heavyVolunteers.isEmpty()) {
			return true;
		}
		else {
			throw new CanNotEditJobException();
		}
	}
}
