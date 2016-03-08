import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Save and load persistent data.
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/2/2016
 * @version 1.0
 */
public class Data {
	private static final String SERIALIZED_FILE = "./urbanParkData.ser";
	private ArrayList<Job> allJobs;
	private ArrayList<AbstractUser> allUsers;
	Calendar calendar;
	String myDataFile = "urbanParkData.ser";
	private boolean dataInitialized;

	/**
	 * Constructs a new Data class.
	 */
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
		//m.addJob("Pet Lions", "03/24/2016", "1:00pm", "1", "Description:..", 1, 2, 3); // pre-add jobs
		//m.addJob("Feed Ducks", "03/10/2016", "1:00pm", "1", "Description1:..", 1, 2, 3);
		
		addUser(m);
		addUser(new ParkManager("Smith", "Smithers", "s@gmail.com", "Evergreen Park"));
		addUser(new UrbanParkStaffMember("Joe", "Fogel", "j@gmail.com"));
		addUser(new UrbanParkStaffMember("Zoey", "Sitz", "z@gmail.com"));
		addUser(new Volunteer("Beth", "Krom", "b@gmail.com"));
		addUser(new Volunteer("Liz", "Breton", "l@gmail.com"));
		
		addUser(new ParkManager("first", "last", "manageremail", "park"));

		//addJob(new Job("Pet Lions", "02/21/2016", "1:00pm", "1", "Tacoma", "Evergreen Park", "Description:..", 1, 2, 3));
		//addJob(new Job("Feed Ducks", "02/24/2016", "1:00pm", "1", "Seattle", "GreenLake", "Description1:..", 1, 2, 3));
		//addJob(new Job("Clean trash", "02/21/2016", "1:00pm", "1", "Tacoma", "Evergreen Park", "Clean trash in the parking lot", 1, 2, 3));
		//addJob(new Job("Paint", "02/24/2016", "1:00pm", "1", "Seattle", "GreenLake", "Paint the wall by the soccer fields.", 1, 2, 3));

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
