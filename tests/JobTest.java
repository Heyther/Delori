import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Tests the Job class
 * 
 * @authors: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public class JobTest {

	Job someJob;
	Volunteer vol1;
	Volunteer vol2;
	Volunteer vol3;
	
	@Before
	public void setUp() throws Exception {
		someJob = new Job("Job Title", "03/01/2016", "12:00 pm", "1", "Some Park", "Some Manager", 
				"Some decription", 1, 1, 1);
		vol1 = new Volunteer("Firstname1", "Lastname1", "email1");
		vol2 = new Volunteer("Firstname2", "Lastname2", "email2");
		vol3 = new Volunteer("Firstname3", "Lastname3", "email3");
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * Test if jobs are equal.
	 */
	@Test
	public void testEqualsJob() {
		Job same = new Job("Job Title", "03/01/2016", "12:00 pm", "1", "Some Park", "Some Manager", 
				"Some decription", 1, 1, 1);
		assertTrue(someJob.equals(same));
	}
	
	/*
	 * Test non-equality between two different jobs.
	 */
	@Test
	public void testNotEqualsJob() {
		Job diff = new Job("Different Job Title", "03/02/2016", "12:00 pm", "1", "Some Park", "Some Manager", 
				"Different decription", 1, 1, 1);
		assertFalse(someJob.equals(diff));
	}

	/*
	 * Checking retrieval of volunteers.
	 */
	@Test
	public void testGetVolunteers() {
		someJob.signUpVolunteer(vol1, 1);
		someJob.signUpVolunteer(vol2, 2);
		someJob.signUpVolunteer(vol3, 3);
		
		assertTrue(someJob.getVolunteers().contains(vol1));
		assertTrue(someJob.getVolunteers().contains(vol2));
		assertTrue(someJob.getVolunteers().contains(vol3));
	}

	/*
	 * Test volunteer sign up on a job.
	 */
	@Test
	public void testSignUpVolunteer() {
		
		//Test sign up for light slot
		assertFalse(someJob.lightVolunteers.contains(vol1));
		int openSlots = someJob.lightSlots;
		someJob.signUpVolunteer(vol1, 1);
		
		assertTrue(someJob.lightVolunteers.contains(vol1));
		assertTrue(someJob.lightSlots == openSlots-1);
		
		//Test sign up for medium slot
		assertFalse(someJob.mediumVolunteers.contains(vol2));
		openSlots = someJob.mediumSlots;
		someJob.signUpVolunteer(vol2, 2);
		
		assertTrue(someJob.mediumVolunteers.contains(vol2));
		assertTrue(someJob.mediumSlots == openSlots-1);
		
		//Test sign up for heavy slot
		assertFalse(someJob.heavyVolunteers.contains(vol3));
		openSlots = someJob.heavySlots;
		someJob.signUpVolunteer(vol3, 3);
				
		assertTrue(someJob.heavyVolunteers.contains(vol3));
		assertTrue(someJob.heavySlots == openSlots-1);
	}

	/*
	 * Tests when volunteer cancels a job.
	 */
	@Test
	public void testCancelVolunteer() {
		//Test cancel light slot
		someJob.signUpVolunteer(vol1, 1);
		someJob.cancelVolunteer(vol1);
		
		assertFalse(someJob.lightVolunteers.contains(vol1));
		
		//Test cancel medium slot
		someJob.signUpVolunteer(vol2, 2);
		someJob.cancelVolunteer(vol2);
		
		assertFalse(someJob.mediumVolunteers.contains(vol2));
		
		//Test cancel heavy slot	
		someJob.signUpVolunteer(vol3, 3);
		someJob.cancelVolunteer(vol3);
		
		assertFalse(someJob.heavyVolunteers.contains(vol3));
	}
}
