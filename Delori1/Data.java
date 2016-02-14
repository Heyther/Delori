import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Save and load persistent data.
 * @author Winfield Brooks (Modified: Heather)
 * @version 2/13/16
 *
 */
public class Data implements Serializable {

	//
	private static final long serialVersionUID = 1L;
	private static final String SERIALIZED_FILE = "urbanParkData.ser";
	private ArrayList<Job> allJobs;
	private ArrayList<AbstractUser> allUsers;

	String myDataFile = "urbanParkData.ser";
	private boolean dataInitialized;

	public Data() throws ClassNotFoundException, IOException {
		dataInitialized = false;
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
	public ArrayList<AbstractUser> getUsers() {
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
	public void addJob(Job theJob) {
		allJobs.add(theJob);
	}
	
	///////////////
	// Serialize  
	///////////////
	
	public void initializeData() throws IOException {
		addUser(new ParkManager("Mary", "Thompson", "m@gmail.com", "Green Park"));
		addUser(new ParkManager("Smith", "Smithers", "s@gmail.com", "Evergreen Park"));
		addUser(new UrbanParkStaffMember("Joe", "Fogel", "j@gmail.com"));
		addUser(new UrbanParkStaffMember("Tim", "Sitz", "t@gmail.com"));
		addUser(new Volunteer("Beth", "Krom", "b@gmail.com"));
		addUser(new Volunteer("Liz", "Breton", "l@gmail.com"));
		
		addJob(new Job("Title", "1/1/2016", "1:00pm", "1/2/2016", "1", "Tacoma", "Evergreen Park", "Description:..", 1, 2, 3));
		serializeObject(getUsers(), getJobs());
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
			allUsers = new ArrayList<AbstractUser>();
			allJobs = new ArrayList<Job>();
			initializeData();
		}
		// load existing system's database
		ArrayList<Object> allData = readObject(); // retrieve saved data
		allUsers.add((AbstractUser) allData.get(0));
		allJobs.add((Job) allData.get(1));
			
	}
	

	/*
	 * De-serialization of data and read from file.
	 */
	public ArrayList<Object> readObject() throws IOException, ClassNotFoundException {
		ArrayList<Object> accuulatedData = new ArrayList<Object>();
		ObjectInputStream aStream = new  ObjectInputStream(new FileInputStream(SERIALIZED_FILE));
		accuulatedData.add(aStream.readObject());
		accuulatedData.add(aStream.readObject());
		aStream.close();
		return accuulatedData;
	}

	/*
	 * Serialization of data and store in file.
	 */
	public void serializeObject(ArrayList<AbstractUser> theAllUsers, ArrayList<Job> theAllJobs) throws IOException {
		ObjectOutputStream aStream = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
		aStream.writeObject(theAllUsers);
		aStream.writeObject(theAllJobs);
		aStream.flush();
		aStream.close();
	}
	
	/*
	 * Main to for single use to instantiate data.
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Data data = new Data();
		data.initializeData();
	}
}
