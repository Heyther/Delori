/**
 * Save and load persistent data.
 * @author Winfield Brooks
 * @version 2/11/16
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data {

	private List<Job> myJobs;
	private List<AbstractUser> myUsers;

	String myDataFile = "urbanParkData.ser";

	public Data() {

	}

	/*
	 * Save persistent data to specified file
	 */
	public void saveData(List<Job> theJobs, List<AbstractUser> theUsers) {

		try (OutputStream file = new FileOutputStream(myDataFile);
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput out = new ObjectOutputStream(buffer);) {

			out.writeObject(theJobs);
			out.writeObject(theUsers);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Load persistent data from specified file
	 */
	public void loadData() {
		
		try(InputStream file = new FileInputStream(myDataFile);
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput in = new ObjectInputStream(buffer);) {
			
			myJobs = (List<Job>) in.readObject();
			myUsers = (List<AbstractUser>) in.readObject();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(IOException ex) {
			ex.getStackTrace();
		}
				
	}
	
	public List<Job> getJobs() {
		return myJobs;
	}
	
	public List<AbstractUser> getUsers() {
		return myUsers;
	}
	
	// Temporary testing method
	public static void main(String[] args) {
//		UrbanParkStaffMember staff1 = new UrbanParkStaffMember("John", "Smith", "john@smith.com", UserStatus.UPSMEMBER);
//		Volunteer vol1 = new Volunteer ("Mike", "Jones", "mikejones@who.com", UserStatus.VOLUNTEER);
//		Job job1 = new Job("Feed lions", "2/12/16", "12:00", "2/12/16", "1", "Pat's House", "John Jackson", 
//				"feed the lions some beef", 0, 2, 10);
//		List<AbstractUser> test1 = new ArrayList<AbstractUser>();
//		List<Job> test2 = new ArrayList<Job>();
//		test1.add(staff1);
//		test1.add(vol1);
//		test2.add(job1);
		Data s1 = new Data();
//		s1.saveData(test2, test1);
		s1.loadData();
		System.out.println(s1.myJobs.get(0).toString());	

		
	}
}
