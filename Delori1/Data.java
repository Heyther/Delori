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
	private List<Job> allJobs;
	private List<AbstractUser> allUsers;

	String myDataFile = "urbanParkData.ser";
	private boolean dataInitialized;

	public Data() throws ClassNotFoundException, IOException  {
		dataInitialized = false;
		allUsers = new ArrayList<AbstractUser>();
		allJobs = new ArrayList<Job>();
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
	
	public List<Job> getJobs() {
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
		ParkManager m = new ParkManager("Mary", "Thompson", "m@gmail.com", "Green Park");
		addUser(m);
		addUser(new ParkManager("Smith", "Smithers", "s@gmail.com", "Evergreen Park"));
		addUser(new UrbanParkStaffMember("Joe", "Fogel", "j@gmail.com"));
		addUser(new UrbanParkStaffMember("Tim", "Sitz", "t@gmail.com"));
		addUser(new Volunteer("Beth", "Krom", "b@gmail.com"));
		addUser(new Volunteer("Liz", "Breton", "l@gmail.com"));
		
		addJob(new Job("Title", "1/1/2016", "1:00pm", "1/2/2016", "1", "Tacoma", "Evergreen Park", "Description:..", 1, 2, 3));
		
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
	
	
	
	
//	// Temporary testing method
//	public static void main(String[] args) {
////		UrbanParkStaffMember staff1 = new UrbanParkStaffMember("John", "Smith", "john@smith.com", UserStatus.UPSMEMBER);
////		Volunteer vol1 = new Volunteer ("Mike", "Jones", "mikejones@who.com", UserStatus.VOLUNTEER);
////		Job job1 = new Job("Feed lions", "2/12/16", "12:00", "2/12/16", "1", "Pat's House", "John Jackson", 
////				"feed the lions some beef", 0, 2, 10);
////		List<AbstractUser> test1 = new ArrayList<AbstractUser>();
////		List<Job> test2 = new ArrayList<Job>();
////		test1.add(staff1);
////		test1.add(vol1);
////		test2.add(job1);
//		Data s1 = new Data();
////		s1.saveData(test2, test1);
//		s1.loadData();
//		System.out.println(s1.allJobs.get(0).toString());	
//
//		
//	}
}
