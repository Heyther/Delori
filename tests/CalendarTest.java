import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Sean
 * In order to run these tests it requires editing the specifically commented lines
 * inside Calendar. As I didn't want to mess with our serializable data whilst testing.
 * These changes are at line 30 and line 114 of Calendar.java
 *
 */
public class CalendarTest {
	ArrayList<Job> jobs;
	ArrayList<AbstractUser> users;
	Job job1;
	Job job2;
	Job job3;
	Job job4;
	Job job5;
	Job job6;
	Job job7;
	Job job8;
	Job job9;
	Calendar cally;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		jobs = new ArrayList<Job>();
		users = new ArrayList<AbstractUser>();
		cally = new Calendar(users, jobs);
		job1 = new Job("Krusty Krab phone clerk", "03/17/2016", "12:00 pm", "1", "KrustyKrab", "Mr. Krabs", 
				"Is this the Krusty Krab? No this is Patrick", 1, 1, 1);
		job2 = new Job("Walrus Tamer", "03/17/2016", "12:00 pm", "1", "your house", "some Walrus", 
				"He's coming", 1, 1, 1);
		job3 = new Job("Garbage Kid", "03/17/2016", "12:00 pm", "1", "Gonyea", "Me", 
				"Pick up that trash!", 1, 1, 1);
		job3 = new Job("Recylcling kid", "03/18/2016", "12:00 pm", "1", "Gonyea", "Me", 
				"Pick up that trash buddy!", 1, 1, 1);
		job4 = new Job("Lawn clippings kid", "03/19/2016", "12:00 pm", "1", "Gonyea", "Me", 
				"You know what to do!", 1, 1, 1);
		job5 = new Job("Refuse cleaner", "03/20/2016", "12:00 pm", "1", "Gonyea", "Me", 
				"Pick up that trash buddy!", 1, 1, 1);
		job6 = new Job("Garbage boy", "03/20/2016", "12:00 pm", "1", "Gonyea", "Me", 
				"Pick up that trash!", 1, 1, 1);
		job7 = new Job("Garbage boy", "01/20/2016", "12:00 pm", "1", "Gonyea", "Me", 
				"Pick up that trash!", 1, 1, 1);
		job8 = new Job("Field sloth", "08/20/2016", "12:00 pm", "1", "Gonyea", "Me", 
				"Moooove slowwwwwly", 1, 1, 1);
		job9 = new Job("Incinerate yardwaste", "04/19/2016", "12:00 pm", "3", "Gonyea", "Me", 
				"Who needs a trash can?", 1, 1, 1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Calendar#verifyJob(Job)}.
	 */
	@Test
	public void testVerifyJobCurrentWorkWeek() {
		//Business Rule 2
		assertTrue(cally.verifyJob(job1));
		jobs.add(job1);
		assertTrue(cally.verifyJob(job2));
		jobs.add(job2);
		assertTrue(cally.verifyJob(job3));
		jobs.add(job3);
		assertTrue(cally.verifyJob(job4));
		jobs.add(job4);
		assertTrue(cally.verifyJob(job5));
		jobs.add(job5);
		assertFalse(cally.verifyJob(job6));
		
	}
	/**
	 * Test method for {@link Calendar#verifyJob(Job)}.
	 */
	@Test
	public void testVerifyJobTotalPending() {
		//Business Rule 1
		int i =0;
		for(i =0; i < 30; i++){
			jobs.add(job1);
		}
		assertFalse(cally.verifyJob(job2));
	}
	/**
	 * Test method for {@link Calendar#verifyJob(Job)}.
	 */
	@Test
	public void testVerifyPastJob() {
		//Business Rule 5/6
		assertFalse(cally.verifyJob(job7));
	}
	/**
	 * Test method for {@link Calendar#verifyJob(Job)}.
	 */
	@Test
	public void testVerifyFutureJob() {
		//Business Rule 5/6
		assertFalse(cally.verifyJob(job8));
	}
	/**
	 * Test method for {@link Calendar#verifyJob(Job)}.
	 */
	@Test
	public void testVerifyDuration() {
		//Business Rule 4
		assertFalse(cally.verifyJob(job9));
	}


	/**
	 * Test method for {@link Calendar#calculateWeekPendingJobs(java.util.Date)}.
	 * @throws ParseException 
	 */
	@Test
	public void testCalculateWeekPendingJobs() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date jobDate = sdf.parse(job1.startDate);
		assertEquals(0,cally.calculateWeekPendingJobs(jobDate));
		jobs.add(job1);
		jobDate = sdf.parse(job2.startDate);
		assertEquals(1,cally.calculateWeekPendingJobs(jobDate));
		jobs.add(job2);
		jobDate = sdf.parse(job5.startDate);
		assertEquals(2, cally.calculateWeekPendingJobs(jobDate));
		jobs.add(job5);
		jobDate = sdf.parse(job6.startDate);
		assertEquals(3, cally.calculateWeekPendingJobs(jobDate));
		jobs.add(job6);
		jobDate = sdf.parse(job4.startDate);
		assertEquals(4, cally.calculateWeekPendingJobs(jobDate));
		jobs.add(job4);
		jobDate = sdf.parse(job3.startDate);
		assertEquals(5, cally.calculateWeekPendingJobs(jobDate));
		
	}

}
