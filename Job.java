import java.io.*;
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
		transient Scanner scan;
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
			scan = new Scanner(System.in);
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
	    }
		

		@Override
		public String toString(){
			String toString = "Job Title:"+this.jobTitle
			                 + "Date:"+this.startDate+"\n"
			                 + "Time:"+ this.startTime +"\n"
			                 +"Duration:"+ this.duration + "day(s)" +"\n"
			                 +"Park Manager:"+this.parkManager+ "\n" 
			                 +"Location:" +this.location+"\n"         
			                 +"Description:"+this.description+"\n"
			                 +"Light slots:" +this.lightSlots+"\n"
			                 + "Medium slots:" +this.mediumSlots+"\n" 
			                 + "Heavy slots:" +this.heavySlots;
			return toString;
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
			int input = scan.nextInt();
			
			switch(input){
				case 1:
					editJobMenu();
					break;
				case 2:
					System.out.println("You went back");
					viewParkManagerJobMenu();
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
			int input = scan.nextInt();
			
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
			int input = scan.nextInt();
			
			
			switch(input){
				case 1:
					editJobMenu();
					break;
				case 2:
					System.out.println("You went back");
					viewUrbanParkStaffMemberJobMenu();
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
				inputSelect = scan.nextInt();
			} catch (InputMismatchException e) {
					scan.next();
			}
			scan.nextLine();
			switch(inputSelect){
			    case 1:
			        System.out.print("Job Title:\n");
					inputS = scan.next();
					scan.nextLine();			
					this.setJobTitle(inputS);
					break;
				case 2:
					System.out.print("Start Date:\n");
					inputS = scan.next();
					scan.nextLine();			
					this.setStartDate(inputS);
					break;
				case 3:
					System.out.print("Start Time:\n");
					inputS = scan.next();
					scan.nextLine();			
					this.setStartTime(inputS);
					break;
				case 4:
					System.out.print("Duration:\n");
					inputS = scan.next();
					scan.nextLine();			
					this.setDuration(inputS);
					break;
				case 5:
					System.out.print("Location:\n");
					inputS = scan.nextLine();
					this.setLocation(inputS);
					break;
				case 6:
				    System.out.print("Park Manager:\n");
					inputS = scan.nextLine();
					this.setParkManager(inputS);
					break;
				case 7:
					System.out.print("Description:\n");
					inputS = scan.nextLine();
					this.setDescription(inputS);
					break;
				case 8:
					System.out.print("Light Slots:\n");
					inputI = scan.nextInt();
					scan.nextLine();			
					this.setLightSlots(inputI);
					break;
				case 9:
					System.out.print("Medium Slots:\n");
					inputI = scan.nextInt();
					scan.nextLine();			
					this.setMediumSlots(inputI);
					break;
				case 10:
					System.out.print("Heavy Slots:\n");
					inputI = scan.nextInt();
					scan.nextLine();			
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
		
	    
}
