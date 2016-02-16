import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Save and load persistent data.
 * @author Winfield Brooks (Modified: Heather)
 * @version 2/13/16
 *
 */
public class Data {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3014553440474186296L;
	private static final String SERIALIZED_FILE = "./urbanParkData.ser";
	private ArrayList<Job> allJobs;
	private ArrayList<AbstractUser> allUsers;
	Calendar calendar;
	String myDataFile = "urbanParkData.ser";
	private boolean dataInitialized;


	public Data() throws ClassNotFoundException, IOException  {
		dataInitialized = true;
		allUsers = new ArrayList<AbstractUser>();
		allJobs = new ArrayList<Job>();
		calendar = new Calendar(allUsers, allJobs);
		loadData();
	}

	
	///////////////
	// User data  
	///////////////
	
	/*
	 * Retrieves returning user.
	 */
	public AbstractUser getReturningUser(String theUserEmail) {
		AbstractUser result = null;
		for (AbstractUser aUser: allUsers) { 
			if (aUser.getEmail().toLowerCase().equals(theUserEmail.toLowerCase())) result = aUser;
		};
		return result;
	}
	
	/*
	 * Adds a user to the system.
	 */
	public void addUser(AbstractUser theUser){
		allUsers.add(theUser);
	}
	
	/*
	 * Retrieve all users in file.
	 */
	public List<AbstractUser> getUsers() {
		return allUsers;
	}
	
	/* 
	 * Search by last name on all users who are volunteers. U10
	 */
	public ArrayList<Volunteer> searchVolunteerByLname(String theLName){
		ArrayList<Volunteer> volunteers = new ArrayList<Volunteer>();
		for (AbstractUser aUser : allUsers)  {
			if (aUser instanceof Volunteer && ( aUser.getLname().toLowerCase().equals( theLName.toLowerCase() ) )) {
				volunteers.add((Volunteer) aUser);
			}
		}
		return volunteers;		
	}
	

	
	///////////////
	// Job data  
	///////////////
	
	public ArrayList<Job> getJobs() {
		return allJobs;
	}
	
	/*
	 * Adds a job to the system.
	 */
	public void addJob(Job theJob) throws IOException {
		allJobs.add(theJob);
		serializeObject();
	}
	
	/*
	 * Deletes a job from the system
	 */
	public void deleteJob(Job theJob) throws IOException {
		allJobs.remove(theJob);
		calendar.totalPendingJobs--;
		serializeObject();
	}
	
	/*
	 * Displays all upcoming jobs
	 */
//	public ArrayList<Job> getUpcomingJobs() {
//		return 
//	}

	
	///////////////
	// Serialize  
	///////////////
	
	public void initializeData() throws IOException {
		ParkManager m = new ParkManager("Mary", "Thompson", "m@gmail.com", "Green Park");
		addUser(m);
		addUser(new ParkManager("Smith", "Smithers", "s@gmail.com", "Evergreen Park"));
		addUser(new UrbanParkStaffMember("Joe", "Fogel", "j@gmail.com"));
		addUser(new UrbanParkStaffMember("Zoey", "Sitz", "z@gmail.com"));
		addUser(new Volunteer("Beth", "Krom", "b@gmail.com"));
		addUser(new Volunteer("Liz", "Breton", "l@gmail.com"));
		
		
		addJob(new Job("Title", "02/21/2016", "1:00pm", "1", "Tacoma", "Evergreen Park", "Description:..", 1, 2, 3));
		addJob(new Job("Job1", "02/24/2016", "1:00pm", "1", "Seattle", "GreenLake", "Description1:..", 1, 2, 3));

		
		System.out.println(m.getFname());
		System.out.println(m.getEmail());
		
		serializeObject();
	}
	
	/*
	 * Save persistent data to specified file
	 */
//	public void saveData(List<Job> theJobs, List<AbstractUser> theUsers) {
//
//		try (OutputStream file = new FileOutputStream(myDataFile);
//				ObjectOutputStream  buffer = new BufferedOutputStream(file);
//				ObjectOutput out = new ObjectOutputStream(buffer);) {
//
//			out.writeObject(theJobs);
//			out.writeObject(theUsers);
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
	
	/*
	 * Load persistent data from specified file
	 */
	public void loadData() throws ClassNotFoundException, IOException {
		// if no file exists (for initial set up purposes)
		if (!dataInitialized) {
			initializeData();
		}
		readObject();
		
//		// load existing system's database
//		ArrayList<Object> allData = readObject(); // retrieve saved data
//		allUsers.addAll((ArrayList<AbstractUser>) allData.get(0));
//		allJobs.add((Job) allData.get(1));
			
	}
	

	/*
	 * De-serialization of data and read from file.
	 */
	@SuppressWarnings("unchecked")
	public void readObject() throws IOException, ClassNotFoundException {
		ObjectInputStream aStream = new  ObjectInputStream(new FileInputStream(SERIALIZED_FILE));
		
		allUsers = (ArrayList<AbstractUser>) aStream.readObject();
		allJobs = (ArrayList<Job>) aStream.readObject();
		aStream.close();
	}

	/*
	 * Serialization of data and store in file.
	 */
	public void serializeObject() throws IOException {
		ObjectOutputStream aStream = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
		aStream.writeObject(allUsers);
		aStream.writeObject(allJobs);
		aStream.flush();
		aStream.close();
	}
	
}
