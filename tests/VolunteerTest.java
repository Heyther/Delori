import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class VolunteerTest {

	private Volunteer volunteer_WithNoEnrolledJobs;
	private Job aJob;

	@Rule
	public ExpectedException expectedExc = ExpectedException.none();

	
	@Before
    public void setUp() {
		volunteer_WithNoEnrolledJobs = new Volunteer("John", "Doe", "dough@gmail.com");
		aJob = new Job("Title", "04/04/2016", "12:00pm", "1", "Tacoma", "Manager", 
				"Description", 1, 1, 1);
	}

	/*
	 * Tests if two values are the same
	 */
	@Test
	public void testEqualsObject() {
		Volunteer same = new Volunteer("John", "Doe", "dough@gmail.com");
		assertEquals("objects are not equal", volunteer_WithNoEnrolledJobs, same);
		assertTrue("objects hashcode are not equal", volunteer_WithNoEnrolledJobs.equals(same));
	}
	
	/*
	 * Checks if two objects are not equal
	 */
	@Test
	public void testNotEqualsObject() {
		Volunteer diff = new Volunteer("notFirst", "notLast", "notEmail");
		assertNotSame("objects are equal", volunteer_WithNoEnrolledJobs, diff);
	}


	@Test
	public void testSignUpWithNoEnrolledJobsException() throws NoEnrolledJobsPresentException {
		expectedExc.expect(NoEnrolledJobsPresentException.class);
		expectedExc.expectMessage("not enrolled in any");
		volunteer_WithNoEnrolledJobs.getEnrolledJobs();
	}
	
//	@Test
//	public void testSignUpWithNoEnrolledJobsExceptionWithAJob() throws NoEnrolledJobsPresentException, JobSlotFilledException, SignUpOnSameDayException, IOException {
//		expectedExc.expect(NoEnrolledJobsPresentException.class);
//		expectedExc.expectMessage("not enrolled in any");
//		volunteer_WithNoEnrolledJobs.signUp(aJob);
//		volunteer_WithNoEnrolledJobs.getEnrolledJobs();
//	}
	
	/*
	 * Tests exception thrown when volunteer signs up for
	 * an already filled job slot.
	 */
	@Test(expected = JobSlotFilledException.class)
	public void testSignUpOnJobSlotFilled() throws JobSlotFilledException, IOException, SignUpOnSameDayException {
		Job filledJob = aJob;
		filledJob.signUpVolunteer(volunteer_WithNoEnrolledJobs, 1);
		volunteer_WithNoEnrolledJobs.signUp(filledJob);
		//filledJob.getVolunteers();
	}
	
//	/*
//	 * Tests exception when volunteer signs up for
//	 * a job that conflicts with their enrolled jobs.
//	 */
//	@Test(expected = SignUpOnSameDayException.class)
//	public void testSignUpWhenEnrolledSameDayException() throws IOException, JobSlotFilledException {
////		expectedExc.expect(SignUpOnSameDayException.class);
////		expectedExc.expectMessage("same start day");
//		
//		Volunteer vol = new Volunteer("FirstName", "LastName", "g@gmail.com");
//		Job theJob1 = new Job("Job1", "04/10/2016", "12:00 pm", "1", "Tacoma", "Manager", 
//				"Description", 3, 3, 3);
//
//			
//		try {
//			vol.setWorkloadResponse(1);
//			vol.signUp(theJob1);
//		} catch (SignUpOnSameDayException e) {
//			assertTrue(e.getMessage().contains("same start day"));
//		}
//		
//		//theJob1.signUpVolunteer(vol, 1);
//
//		Job conflictingJob = new Job("ConflictingWithJob1", "04/10/2016", "12:00 pm", "1", "Tacoma", "Manager", 
//																"Description", 3, 3, 3);
//		
//		
//		try {
//			vol.setWorkloadResponse(1);
//			vol.signUp(conflictingJob);
//		} catch (SignUpOnSameDayException e) {
//			assertTrue(e.getMessage().contains("same start day"));
//		}
//		//conflictingJob.signUpVolunteer(vol, 1);
//		
//	}

}
