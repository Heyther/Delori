import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Tests the ParkManager class.
 * 
 * @authors: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 2.0
 */
public class ParkManagerTest { 
	
	ParkManager manager;

	@Before
	public void setUp() throws Exception {
		//manager = new ParkManager("first", "last", "email", "park");
		@SuppressWarnings("unused")
		IODriver driver = new IODriver(false);
		manager = (ParkManager)IODriver.storedData.getReturningUser("manageremail");
		//IODriver.storedData.addUser(manager);
	}

	@After
	public void tearDown() throws Exception {
		for (int i = 0; i < manager.jobsManaging.size(); i++) {
			manager.cancelJob(manager.jobsManaging.get(i));
		}
	}

	/*
	 * Tests if two equal ParkManagers are considered equal
	 */
	@Test
	public void testEqualsObjectSame() {
		ParkManager same = new ParkManager("first", "last", "manageremail", "park");
		assertTrue(manager.equals(same));
	}
	
	/*
	 * Tests if two not equal ParkManagers are considered equal, every parameter is different 
	 */
	@Test
	public void testEqualsObjectDifferent() {
		ParkManager different = new ParkManager("Different first", "Different last", "Different email", "Different park");
		assertFalse(manager.equals(different));
	}
	
	/*
	 * Tests if two not equal objects are considered equal, only one parameter is different
	 */
	@Test
	public void testEqualsObjectSimilar() {
		ParkManager similar = new ParkManager("first", "last", "different email", "park");
		assertFalse(manager.equals(similar));
	}
	
	/*
	 * Tests if a job can be added when the ParkManager currently has no jobs
	 */
	@Test
	public void testAddJobNoJobsInList() throws IOException, NoManagedJobsException {
		Job newJob = manager.addJob("Title", "04/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		
		assertTrue(manager.getJobsManaging().contains(newJob) && IODriver.storedData.getJobs().contains(newJob));
	}
	
	/*
	 * Tests if a job can be added when the ParkManager currently has two jobs 
	 */
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
	
	/*
	 * Tests if a valid job can be added
	 */
	@Test
	public void testAddJobValid() throws IOException, NoManagedJobsException {
		Job newJob = manager.addJob("Title", "04/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		
		assertTrue(manager.getJobsManaging().contains(newJob) && IODriver.storedData.getJobs().contains(newJob));
	}
	
	/*
	 * Tests if an invalid job can be added
	 */
	@Test
	public void testAddJobInvalid() throws IOException {
		@SuppressWarnings("unused")
		Job validJob = manager.addJob("Title", "04/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		Job invalidJob = manager.addJob("Title", "02/04/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		
		try {
			assertFalse(manager.getJobsManaging().contains(invalidJob) || IODriver.storedData.getJobs().contains(invalidJob));
		} catch (NoManagedJobsException e) {
			
		}
	}

	/*
	 * Tests if a job can be canceled when it is the only job in the list
	 */
	@Test 
	public void testCancelJobOneJobInList() throws IOException, NoManagedJobsException {
		Job toBeCanceled = manager.addJob("Title", "03/16/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		manager.cancelJob(toBeCanceled);
		
		boolean exceptionCaught = false;
		try {
			manager.getJobsManaging();
		} catch (NoManagedJobsException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught && !IODriver.storedData.getJobs().contains(toBeCanceled));
	}
	
	/*
	 * Tests if a job can be canceled when there is another job in the list
	 */
	@Test
	public void testCancelJobTwoJobsInList() throws IOException, NoManagedJobsException {
		Job toBeCanceled1 = manager.addJob("Title", "03/16/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		@SuppressWarnings("unused")
		Job toBeCanceled2 = manager.addJob("Other Title", "03/19/2016", "12:00 pm", "1", "Other Description", 1, 1, 1);
		manager.cancelJob(toBeCanceled1);
		assertFalse(manager.getJobsManaging().contains(toBeCanceled1) || IODriver.storedData.getJobs().contains(toBeCanceled1));
	}

	/*
	 * Tests if a job's title can be edited
	 */
	@Test
	public void testEditJobTitle() throws IOException {
		
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobTitle(toBeEdited, "New Title");
		assertTrue(toBeEdited.getJobTitle().equals("New Title"));
	}

	/*
	 * Tests if a job's start date can be edited
	 */
	@Test
	public void testEditJobDate() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobDate(toBeEdited, "04/29/2016");
		assertTrue(toBeEdited.getStartDate().equals("04/29/2016"));
	}

	/*
	 * Tests if a job's start time can be edited
	 */
	@Test
	public void testEditJobTime() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobTime(toBeEdited, "1:00 pm");
		assertTrue(toBeEdited.getStartTime().equals("1:00 pm"));
	}

	/*
	 * Tests if a job's duration can be edited
	 */
	@Test
	public void testEditJobDuration() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobDuration(toBeEdited, "2");
		assertTrue(toBeEdited.getDuration().equals("2"));
	}

	/*
	 * Tests if a job's description can be edited
	 */
	@Test
	public void testEditJobDescription() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobDescription(toBeEdited, "New Description");
		assertTrue(toBeEdited.getDescription().equals("New Description"));
	}

	/*
	 * Tests if a job's number of light slots can be edited
	 */
	@Test
	public void testEditJobLightSlots() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobLightSlots(toBeEdited, 2);
		assertTrue(toBeEdited.getLightSlots() == 2);
	}

	/*
	 * Tests if a job's number of medium slots can be edited
	 */
	@Test
	public void testEditJobMediumSlots() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobMediumSlots(toBeEdited, 2);
		assertTrue(toBeEdited.getMediumSlots() == 2);
	}

	/*
	 * Tests if a job's number of heavy slots can be edited
	 */
	@Test
	public void testEditJobHeavySlots() throws IOException {
		Job toBeEdited = manager.addJob("Original Title", "04/30/2016", "12:00 pm", "1", "Original Description", 1, 1, 1);
		manager.editJobHeavySlots(toBeEdited, 2);
		assertTrue(toBeEdited.getHeavySlots() == 2);
	}

	/*
	 * Tests if the list of the park manager's jobs is properly returned when there are jobs in the list
	 */
	@Test
	public void testGetJobsManagingSomeJobs() throws NoManagedJobsException, IOException {
		@SuppressWarnings("unused")
		Job job1 = manager.addJob("Title", "03/16/2016", "12:00 pm", "1", "Description", 1, 1, 1);
		@SuppressWarnings("unused")
		Job job2 = manager.addJob("Other Title", "03/19/2016", "12:00 pm", "1", "Other Description", 1, 1, 1);
		
		ArrayList<Job> jobsManagingCopy = manager.getJobsManaging();
		
		for (int i = 0; i < jobsManagingCopy.size(); i++) {
			assertEquals(jobsManagingCopy.get(i), manager.jobsManaging.get(i));
		}
	}
	
	/*
	 * Tests if a NoManagedJobsException is thrown when getJobsManaging is called and there are no jobs in the list
	 */
	@Test
	public void testGetJobsManagingNoJobs() throws IOException {
		boolean exceptionCaught = false;
		try {
			manager.getJobsManaging();
		} catch (NoManagedJobsException e) {
			exceptionCaught = true;
			assertTrue(exceptionCaught);
		}
	}
}
