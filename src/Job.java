import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * A job class, that can be used in console i/o.
 * It contains 3 different view "menus" depending on the user type.
 * 
 * @author Sean Hoyt (Modified by Luciana, Heather)
 * @date 
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
		

		/**
		 * Job constructor
		 * @param jobTitle Title description of job.
		 * @param start Start date of job.
		 * @param startTime Start time of job.
		 * @param dur Duration of job.
		 * @param loc Location of job.
		 * @param parkMan Park manager in charge of job.
		 * @param descript Description of job.
		 * @param light The number of light positions available in job.
		 * @param med The number of medium positions available in job.
		 * @param heavy The number of heavy positions available in job.
		 */
		public Job(String jobTitle, String start, String time, String dur, String loc, String parkMan, 
				String descript, int light, int med, int heavy){
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
		

		@Override
		public String toString(){
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
		
		public boolean equals(Job other){
			if (this.jobTitle.equals(other.jobTitle) && this.startDate.equals(other.startDate) 
					&& this.startTime.equals(other.startTime) && this.location.equals(other.location) 
					&& this.duration.equals(other.duration) && this.location.equals(other.location) && 
					this.lightSlots == other.lightSlots && this.mediumSlots == other.mediumSlots && this.heavySlots == other.heavySlots){
				return true;
			}
			else
				return false;
		}
		
		/**
		*Getter for job title.
		*/
		public String getJobTitle(){
		    return jobTitle;
		}
		/**
		*Setter for job title.
		*@param theJobTitle
		*/
        public void setJobTitle(String theJobTitle){
            this.jobTitle = theJobTitle;
        }		
        
		/**
		 * Getter for start date.
		 * @return Returns startDate.
		 */
		public String getStartDate() {
			return startDate;
		}
		/**
		 * Setter for start date.
		 */
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		/**
		 * Getter for start time.
		 * @return Returns startDate.
		 */
		public String getStartTime() {
			return startDate;
		}
		/**
		 * Setter for start time.
		 */
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		/**
		 * Getter for duration.
		 * @return Returns String endDate.
		 */
		public String getDuration() {
			return duration;
		}
		/**
		 * Setter for duration
		 * @param duration.
		 */
		public void setDuration(String duration) {
			this.duration = duration;
		}
		/**
		 * Getter for location.
		 * @return String location.
		 */
		public String getLocation() {
			return location;
		}
		/**
		 * Setter for a location. 
		 * @param location New location for job.
		 */
		public void setLocation(String location) {
			this.location = location;
		}
		/**
		 * Getter for a description.
		 * 
		 * @return String description of a job.
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * Setter for description.
		 * @param description New description for job.
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		**Getter for parkManager
		/*@return parkManager 
		*/
		public String getParkManager() {
			return parkManager;
		}
		/**
		 * Setter for parkManager.
		 * @param description New description for job.
		 */
		public void setParkManager(String parkManager) {
			this.parkManager = parkManager;
		}
		/**
		 * Getter for lightSlots.
		 * @return Number of lightSlots.
		 */
		public int getLightSlots() {
			return lightSlots;
		}
		/**
		 * Setter for LighSlots.
		 * @param lightSlots New number of light slots.
		 */
		public void setLightSlots(int lightSlots) {
			this.lightSlots = lightSlots;
		}
		/**
		 * Getter for medium slots.
		 * @return int number of mediumSlots.
		 */
		public int getMediumSlots() {
			return mediumSlots;
		}
		/**
		 * Setter for medium slots.
		 * @param mediumSlots New number of medium slots.
		 */
		public void setMediumSlots(int mediumSlots) {
			this.mediumSlots = mediumSlots;
		}
		/**
		 * Getter for heavy slots.
		 * @return int number of heavy slots.
		 */
		public int getHeavySlots() {
			return heavySlots;
		}
		/**
		 * Setter for heavy slots.
		 * @param heavySlots New number of heavySlots.
		 */
		public void setHeavySlots(int heavySlots) {
			this.heavySlots = heavySlots;
		}
		/**
		 * Getter for total slots.
		 * @return total number of slots. 
		 */
		public int getTotalSlots() {
			return totalSlots;
		}
		/**
		 * Setter for total slots.
		 * @param totalSlots new number total slots.
		 */
		public void setTotalSlots(int totalSlots) {
			this.totalSlots = totalSlots;
		}
		
		/*
		 * Returns an array list of all Volunteer objects by combining the lists of 
		 * light, medium, and heavy workload volunteers
		 */
		public ArrayList<Volunteer> getVolunteers()
		{
			ArrayList<Volunteer> allVolunteers = new ArrayList<Volunteer>();
			allVolunteers.addAll(lightVolunteers);
			allVolunteers.addAll(mediumVolunteers);
			allVolunteers.addAll(heavyVolunteers);
			return allVolunteers;
		}
		
		/*
		 * Prints names of all volunteers who have signed up for this job to the console
		 */
		public void printVolunteers()
		{
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
		public void signUpVolunteer(Volunteer theVolunteer, int workload){
			switch (workload){
			case LIGHT:
				lightVolunteers.add(theVolunteer);
				lightSlots--;
				break;
			case MEDIUM:
				mediumVolunteers.add(theVolunteer);
				mediumSlots--;
				break;
			case HEAVY:
				heavyVolunteers.add(theVolunteer);
				heavySlots--;
				break;
			default: 
				break;
			}
		}
		
		/*
		 * Accepts a volunteer and a workload and deletes the volunteer from the appropriate list of volunteers
		 * Increments number of available slots of that workload
		 */
		public void cancelVolunteer(Volunteer theVolunteer){
			if (lightVolunteers.contains(theVolunteer)){
				lightVolunteers.remove(theVolunteer);
				lightSlots++;
			}
			else if (mediumVolunteers.contains(theVolunteer)){
				mediumVolunteers.remove(theVolunteer);
				mediumSlots++;
			}
			else if (heavyVolunteers.contains(theVolunteer)){
				heavyVolunteers.remove(theVolunteer);
				heavySlots++;
			}
		}
		
		/*
		 * Returns a brief summary of job.
		 */
		public String jobSummary() {
			return this.getJobTitle() + ", " + this.getLocation() + ", " + this.getStartDate();
		}
	    
}
