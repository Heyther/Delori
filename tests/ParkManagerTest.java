import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkManagerTest {
	
	ParkManager manager;
	Job job1;

	@Before
	public void setUp() throws Exception {
		manager = new ParkManager("first", "last", "email", "park");
		job1 = new Job("Job Title", "03/05/2016", "12:00 pm", "1", manager.getParkName(), manager.getFullName(), 
				"Some decription", 1, 1, 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRoleString() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		//Test with two equal objects
		//Two not equal objects
		//One object and one null
		fail("Not yet implemented");
	}

	@Test
	public void testParkManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddJobValid() throws IOException, NoManagedJobsException {
		Job newJob = manager.addJob("Title", "04/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		
		assertTrue(manager.getJobsManaging().contains(newJob) && IODriver.storedData.getJobs().contains(newJob));
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddJobInvalidJob() throws IOException, NoManagedJobsException {
		Job invalidJob = manager.addJob("Title", "02/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		
		assertFalse(manager.getJobsManaging().contains(invalidJob) || IODriver.storedData.getJobs().contains(invalidJob));
	}
	
	@Test
	public void testAddJobScheduleConflict() throws IOException, NoManagedJobsException {
		Job jobOnDay = manager.addJob("Title", "03/15/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		Job jobOnSameDay = manager.addJob("Other Title", "03/15/2016", "2:00 pm", "1", "Other Description", 1, 1, 1);
		
		assertFalse(manager.getJobsManaging().contains(jobOnSameDay) || IODriver.storedData.getJobs().contains(jobOnSameDay));
	}

	@Test
	public void testCancelJob() throws IOException, NoManagedJobsException {
		Job toBeDeleted = manager.addJob("Title", "03/16/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		manager.cancelJob(toBeDeleted);
		assertFalse(manager.getJobsManaging().contains(toBeDeleted) || IODriver.storedData.getJobs().contains(toBeDeleted));
	}
	
	@Test
	public void testCancelJobNonexistantJob() throws IOException, NoManagedJobsException {
		Job toBeDeleted = manager.addJob("Title", "03/16/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		manager.cancelJob(toBeDeleted);
		assertFalse(manager.getJobsManaging().contains(toBeDeleted) || IODriver.storedData.getJobs().contains(toBeDeleted));
	}

	@Test
	public void testEditJobTitle() throws IOException {
		
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobTitle(toBeEdited, "New Title");
		assertTrue(toBeEdited.getJobTitle().equals("New Title"));
	}

	@Test
	public void testEditJobDate() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobDate(toBeEdited, "04/29/2016");
		assertTrue(toBeEdited.getStartDate().equals("04/29/2016"));
	}

	@Test
	public void testEditJobTime() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobTime(toBeEdited, "1:00 pm");
		assertTrue(toBeEdited.getStartTime().equals("1:00 pm"));
	}

	@Test
	public void testEditJobDuration() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobDuration(toBeEdited, "2");
		assertTrue(toBeEdited.getDuration().equals("2"));
	}

	@Test
	public void testEditJobDescription() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobDescription(toBeEdited, "New Description");
		assertTrue(toBeEdited.getDescription().equals("New Description"));
	}

	@Test
	public void testEditJobLightSlots() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobLightSlots(toBeEdited, 2);
		assertTrue(toBeEdited.getLightSlots() == 2);
	}

	@Test
	public void testEditJobMediumSlots() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobMediumSlots(toBeEdited, 2);
		assertTrue(toBeEdited.getMediumSlots() == 2);
	}

	@Test
	public void testEditJobHeavySlots() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobHeavySlots(toBeEdited, 2);
		assertTrue(toBeEdited.getHeavySlots() == 2);
	}

	@Test
	public void testGetJobsManaging() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbstractUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFname() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFname() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLname() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLname() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFullName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

}
