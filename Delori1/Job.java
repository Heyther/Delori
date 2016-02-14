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
		public Job(String jobTitle, String start, String time, String endDate, String dur, String loc, String parkMan, 
				String descript, int light, int med, int heavy){
			IODriver.input = new Scanner(System.in);
			this.jobTitle = jobTitle;
		    this.startDate = start;
		    this.startTime = time;
		    this.endDate = endDate;
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
			                 + "Date: "+this.startDate+"\n"
			                 + "Time: "+ this.startTime +"\n"
			                 +"Duration: "+ this.duration + " day(s)" +"\n"
			                 +"Park Manager: "+this.parkManager+ "\n" 
			                 +"Location: " +this.location+"\n"         
			                 +"Description: "+this.description+"\n"
			                 +"Light slots: "+this.lightSlots+"\n"
			                 + "Medium slots: "+this.mediumSlots+"\n" 
			                 + "Heavy slots: "+this.heavySlots;
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
		
		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
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
		public void signUpVolunteer(Volunteer theVolunteer, String workload){
			switch (workload){
			case "light":
				lightVolunteers.add(theVolunteer);
				lightSlots--;
				break;
			case "medium":
				mediumVolunteers.add(theVolunteer);
				mediumSlots--;
				break;
			case "heavy":
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
		public void cancelVolunteer(Volunteer theVolunteer, String workload){
			switch (workload){
			case "light":
				lightVolunteers.remove(theVolunteer);
				lightSlots++;
				break;
			case "medium":
				mediumVolunteers.remove(theVolunteer);
				mediumSlots++;
				break;
			case "heavy":
				heavyVolunteers.remove(theVolunteer);
				heavySlots++;
				break;
			default: 
				break;
			}
		}
		
		
		//Once we have everyone's user classes in I will make it switch auto based on
		//what class name the current user is, for now they just run without user arg
		
		//i.e. something like JobList.getCurrentUser.getClass().getName();
		// for now they are just seperate menus but it will be an easy fix.
		/**
		 * An i/o menu for the park manager to view particular details of job
		 * Or edit the details of a given job
		 */
		public void viewParkManagerJobMenu() {
			System.out.println(("Job details:\n"+this.toString()+
					"\nPlease select one of the following options\n"+""
							+ "(1)Edit Job\n(2)Back\n(3)Exit\n"));
			int input = IODriver.input.nextInt();
			
			switch(input){
				case 1:
					editJobMenu();
					break;
				case 2:
					System.out.println("You went back");
					//viewParkManagerJobMenu();
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Not valid char");
					viewParkManagerJobMenu();
					break;
			}
			
		}
		/**
		 * An i/o menu for the volunteer to view particular details of job.
		 * As well as attempt to volunteer for the position.
		 */
		public void viewVolunteerJobMenu() {
			System.out.println(("Job details:\n"+this.toString()+
					"\nPlease select one of the following options\n"+""
							+ "(1)Volunteer\n(2)Back\n(3)Exit\n"));
			int input = IODriver.input.nextInt();
			
			switch(input){
				case 1:
					//job will add volunteer if not on blackball list
					System.out.println("Yay you were added");
					viewVolunteerJobMenu();
					break;
				case 2:
					System.out.println("You went back");
					viewVolunteerJobMenu();
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Not valid char");
					viewVolunteerJobMenu();
					break;
			}
			
		}
		 /** An i/o menu for the urban parks staff member to view particular details of job
		 ** Or edit the details of a given job
		 */
		public void viewUrbanParkStaffMemberJobMenu() {
			System.out.println(("Job details:\n"+this.toString()+
					"\nPlease select one of the following options\n"+""
							+ "(1)Edit Job\n(2)Back\n(3)Exit\n"));
			int input = IODriver.input.nextInt();
			
			switch(input){
				case 1:
					editJobMenu();
					break;
				case 2:
					System.out.println("You went back");
					
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Not valid input");
					viewUrbanParkStaffMemberJobMenu();
					break;
			}
			
		}
		/**
		 * A Menu for selecting each of the fields that need to be edited during console i/o.
		 * Allowing a park manager or urban parks staff member to edit an already pending job.
		 */
		public void editJobMenu(){
			String inputS = "";
			int inputSelect = 0;
			int inputI;
			
			System.out.println("Welcome to job editing menu!"
					+ " Please select of one the following"
					+ " options:\n"
					+ "(1)Job Title\n"
					+ "(2)Start Date\n"
					+ "(3)Start Time\n"
				    + "(4)Duration\n"
					+ "(5)Location\n"
					+ "(6)Park Manager\n"
					+ "(7)Description\n"
					+ "(8)Edit Light Slots\n"
					+ "(9)Edit Medium Slots\n"
					+ "(10)Edit Heavy Slots\n"
					+ "(11)Back\n"
					);
			
			//inputSelect = scan.nextInt();
		
			try {
				inputSelect = IODriver.input.nextInt();
			} catch (InputMismatchException e) {
					IODriver.input.next();
			}
			IODriver.input.nextLine();
			switch(inputSelect){
			    case 1:
			        System.out.print("Job Title:\n");
					inputS = IODriver.input.next();
					IODriver.input.nextLine();			
					this.setJobTitle(inputS);
					break;
				case 2:
					System.out.print("Start Date:\n");
					inputS = IODriver.input.next();
					IODriver.input.nextLine();			
					this.setStartDate(inputS);
					break;
				case 3:
					System.out.print("Start Time:\n");
					inputS = IODriver.input.next();
					IODriver.input.nextLine();			
					this.setStartTime(inputS);
					break;
				case 4:
					System.out.print("Duration:\n");
					inputS = IODriver.input.next();
					IODriver.input.nextLine();			
					this.setDuration(inputS);
					break;
				case 5:
					System.out.print("Location:\n");
					inputS = IODriver.input.nextLine();
					this.setLocation(inputS);
					break;
				case 6:
				    System.out.print("Park Manager:\n");
					inputS = IODriver.input.nextLine();
					this.setParkManager(inputS);
					break;
				case 7:
					System.out.print("Description:\n");
					inputS = IODriver.input.nextLine();
					this.setDescription(inputS);
					break;
				case 8:
					System.out.print("Light Slots:\n");
					inputI = IODriver.input.nextInt();
					IODriver.input.nextLine();			
					this.setLightSlots(inputI);
					break;
				case 9:
					System.out.print("Medium Slots:\n");
					inputI = IODriver.input.nextInt();
					IODriver.input.nextLine();			
					this.setMediumSlots(inputI);
					break;
				case 10:
					System.out.print("Heavy Slots:\n");
					inputI = IODriver.input.nextInt();
					IODriver.input.nextLine();			
					this.setHeavySlots(inputI);
					break;
				case 11:
					viewParkManagerJobMenu();
					break;
				default:
					editJobMenu();
					break;	
				}
				editJobMenu();
			}
		
		/*
		 * Returns a brief summary of job.
		 */
		public String jobSummary() {
			return this.getJobTitle() + ", " + this.getLocation() + ", " + this.getStartDate();
		}
	    
}
