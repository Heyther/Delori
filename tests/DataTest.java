import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataTest {

	private Data data;
	
	@Before
	public void setUp() throws Exception {
		//Calendar calendar = new Calendar(data.getUsers(), data.getJobs());
		data = new Data();
		
	} 
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetReturningUser() {
		AbstractUser user = data.getReturningUser("b@gmail.com");
		assertEquals("Krom", user.getLname());
		
	}
	
	@Test
	public void testGetReturningUser2() {
		AbstractUser user = data.getReturningUser("junkEmail@gmail.com");
		assertNull(user);
		
	}
	
	@Test
	public void testAddUser() {
		UrbanParkStaffMember staff = new UrbanParkStaffMember("Tim", "Jones", "tj@gmail.com");
		data.addUser(staff);
		assertTrue(data.getUsers().contains(staff));
		
		
	}
	
	public void testDeletJob() throws IOException {
		Job job = new Job("Title", "02/26/2016", "1:00pm", "1",
				"Tacoma", "Evergreen Park", "Description:..", 1, 2, 3);
		data.addJob(job);
		assertTrue(data.getJobs().contains(job));
		data.deleteJob(job);
		assertFalse(data.getJobs().contains(job));
		
	}
	
	@Test
	public void testSearchVolunteerByLastLname() {
		ArrayList<Volunteer> vol = data.searchVolunteerByLname("Krom");
		assertEquals("Krom", vol.get(0).getLname());
		
	}
	
	@Test
	public void testSearchVolunteerByLastLname2() {
		ArrayList<Volunteer> vol = data.searchVolunteerByLname("Not a name");
		assertEquals(0, vol.size());
		
	}

	@Test
	public void testAddJob() throws IOException {
		Job job = new Job("Title", "02/26/2016", "1:00pm", "1",
				"Tacoma", "Evergreen Park", "Description:..", 1, 2, 3);
		data.addJob(job);
		assertTrue(data.getJobs().contains(job));
		data.deleteJob(job);
	}
}
