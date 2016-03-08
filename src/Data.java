import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Save and load persistent data.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public class Data {
	private static final String SERIALIZED_FILE = "./urbanParkData.ser";
	private ArrayList<Job> allJobs;
	private ArrayList<AbstractUser> allUsers;
	Calendar calendar;
	String myDataFile = "urbanParkData.ser";
	private boolean dataInitialized;

	/*
	 * Constructs a new Data class.
	 */
	public Data() throws ClassNotFoundException, IOException  {
		dataInitialized = false;
		allUsers = new ArrayList<AbstractUser>();
		allJobs = new ArrayList<Job>();
		calendar = new Calendar();
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
    public ArrayList<Volunteer> searchVolunteerByLname(String theLName) throws VolunteerNotFoundException{
        ArrayList<Volunteer> volunteers = new ArrayList<Volunteer>();
        for (AbstractUser aUser : allUsers)  {
            if (aUser instanceof Volunteer && ( aUser.getLname().toLowerCase().equals( theLName.toLowerCase() ) )) {
                volunteers.add((Volunteer) aUser);
            }
        }
        if(volunteers.size() == 0) {
            throw new VolunteerNotFoundException();
        } else {
        return volunteers;    
        }
    }

	
	///////////////
	// Job data  
	///////////////
    
	/*
	 * Retrieves all jobs in system.
	 */
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

	///////////////
	// Serialize  
	///////////////
	
	/*
	 * Initialize data for single use to add users. Method controlled by field dataInitialized.
	 */
	public void initializeData() throws IOException {
		ParkManager m = new ParkManager("Mary", "Thompson", "m@gmail.com", "Green Park");
		addUser(m);
		addUser(new ParkManager("Smith", "Smithers", "s@gmail.com", "Evergreen Park"));
		addUser(new UrbanParkStaffMember("Joe", "Fogel", "j@gmail.com"));
		addUser(new UrbanParkStaffMember("Zoey", "Sitz", "z@gmail.com"));
		addUser(new Volunteer("Beth", "Krom", "b@gmail.com"));
		addUser(new Volunteer("Liz", "Breton", "l@gmail.com"));
		addUser(new ParkManager("first", "last", "manageremail", "park"));
		addJob(new Job("Krusty Krab phone clerk", "03/1/2016", "12:00 pm", "1", "KrustyKrab", "Mr. Krabs", 
				"Is this the Krusty Krab? No this is Patrick", 1, 1, 1));
		serializeObject();
	}
	
	
	/*
	 * Load persistent data from specified file
	 */
	public void loadData() throws ClassNotFoundException, IOException {
		// if no file exists (for initial set up purposes)
		if (!dataInitialized) {
			initializeData();
		}
		readObject();
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
