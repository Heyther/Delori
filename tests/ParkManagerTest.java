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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObjectSame() {
		ParkManager same = new ParkManager("first", "last", "email", "park");
		assertTrue(manager.equals(same));
	}
	
	@Test
	public void testEqualsObjectDifferent() {
		//Test with two equal objects
		ParkManager different = new ParkManager("Different first", "Different last", "Different email", "Different park");
		assertFalse(manager.equals(different));
	}
	
	@Test
	public void testEqualsObjectSimilar() {
		ParkManager similar = new ParkManager("first", "last", "different email", "park");
		assertFalse(manager.equals(similar));
	}
	
	@Test
	public void testAddJobNoJobsInList() throws IOException, NoManagedJobsException {
		Job newJob = manager.addJob("Title", "04/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		
		assertTrue(manager.getJobsManaging().contains(newJob) && IODriver.storedData.getJobs().contains(newJob));
	}
	
	@Test
	public void testAddJobTwoJobsInList() throws IOException, NoManagedJobsException {
		Job job1 = new Job ("Title 1", "04/02/2016", "12:00 pm", "1", manager.getParkName(), manager.getFullName(), "Description 1", 1, 1, 1);  
		Job job2 = new Job ("Title 2", "04/03/2016", "12:00 pm", "1", manager.getParkName(), manager.getFullName(), "Description 2", 1, 1, 1); 
		
		manager.jobsManaging.add(job1);
		IODriver.storedData.addJob(job1);
		manager.jobsManaging.add(job2);
		IODriver.storedData.addJob(job2);
		
		Job newJob = manager.addJob("Title", "04/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		
		assertTrue(manager.getJobsManaging().contains(newJob) && IODriver.storedData.getJobs().contains(newJob));
	}
	
	
	@Test
	public void testAddJobValid() throws IOException, NoManagedJobsException {
		Job newJob = manager.addJob("Title", "04/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		
		assertTrue(manager.getJobsManaging().contains(newJob) && IODriver.storedData.getJobs().contains(newJob));
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddJobInvalid() throws IOException, NoManagedJobsException {
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
	public void testCancelJobOneJobInList() throws IOException, NoManagedJobsException {
		Job toBeCanceled = manager.addJob("Title", "03/16/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		manager.cancelJob(toBeCanceled);
		assertFalse(manager.getJobsManaging().contains(toBeCanceled) || IODriver.storedData.getJobs().contains(toBeCanceled));
	}
	
	@Test
	public void testCancelJobTwoJobsInList() throws IOException, NoManagedJobsException {
		Job toBeCanceled1 = manager.addJob("Title", "03/16/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		Job toBeCanceled2 = manager.addJob("Other Title", "03/19/2016", "12:00 pm", "1", "Other Description", 1, 1, 1);
		manager.cancelJob(toBeCanceled1);
		assertFalse(manager.getJobsManaging().contains(toBeCanceled1) || IODriver.storedData.getJobs().contains(toBeCanceled1));
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

}
