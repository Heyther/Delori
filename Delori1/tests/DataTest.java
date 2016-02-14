import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DataTest {

	private Data data;
	
	@Before
	public void setUp() throws Exception {
		
		data = new Data();
		
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
	public void testAddJob() {
		assertEquals(2, data.getJobs().size());
		Job job = new Job("Title", "1/1/2016", "1:00pm", "1/2/2016", "1",
				"Tacoma", "Evergreen Park", "Description:..", 1, 2, 3);
		data.addJob(job);
		assertEquals(3, data.getJobs().size());
	}

}
