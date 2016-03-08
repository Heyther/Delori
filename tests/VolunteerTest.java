import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Testings Volunteer class.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 2.0
 */
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
	 * Tests if two values are the same.
	 */
	@Test
	public void testEqualsObject() {
		Volunteer same = new Volunteer("John", "Doe", "dough@gmail.com");
		assertEquals("objects are not equal", volunteer_WithNoEnrolledJobs, same);
		assertTrue("objects hashcode are not equal", volunteer_WithNoEnrolledJobs.equals(same));
	}
	
	/*
	 * Checks if two objects are not equal.
	 */
	@Test
	public void testNotEqualsObject() {
		Volunteer diff = new Volunteer("notFirst", "notLast", "notEmail");
		assertNotSame("objects are equal", volunteer_WithNoEnrolledJobs, diff);
	}

	/*
	 * Tests the exception NoEnrolledJobsPresentException
	 * when a volunteer tries to view their enrolled jobs.
	 */
	@Test
	public void testSignUpWithNoEnrolledJobsException() throws NoEnrolledJobsPresentException {
		expectedExc.expect(NoEnrolledJobsPresentException.class);
		expectedExc.expectMessage("not enrolled in any");
		volunteer_WithNoEnrolledJobs.getEnrolledJobs();
	}
	
	/*
	 * Tests exception thrown when volunteer signs up for
	 * an already filled job slot.
	 */
	@Test(expected = JobSlotFilledException.class)
	public void testSignUpOnJobSlotFilled() throws JobSlotFilledException, IOException, SignUpOnSameDayException {
		Job filledJob = aJob;
		filledJob.signUpVolunteer(volunteer_WithNoEnrolledJobs, 1);
		volunteer_WithNoEnrolledJobs.signUp(filledJob);
	}
}
