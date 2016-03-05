import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VolunteerTest {

	private Volunteer volunteer;
	private Job job;

	@Before
    public void setUp() {
		volunteer = new Volunteer("John", "Doe", "dough@gmail.com");
		job = new Job("Title", "04/04/2016", "12:00 pm", "1", "Tacoma", "Manager", 
															"Description", 1, 1, 1);
	}

	@Test
	public void testEqualsObject() {
		Volunteer same = new Volunteer("John", "Doe", "dough@gmail.com");
		assertTrue(volunteer.equals(same));
	}
	
	@Test
	public void testNotEqualsObject() {
		Volunteer diff = new Volunteer("notfirst", "notlast", "notemail");
		assertFalse(volunteer.equals(diff));
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

//	@Test
//	public void testCancelEnrolledJob() {
//		fail("Not yet implemented");
//	}

	@Test(expected = JobSlotFilledException.class)
	public void testSignUpOnJobSlotFilled() {
		fail("Not yet implemented");
	}
	
	@Test(expected = SignUpOnSameDayException.class)
	public void testSignUpWhenEnrolled() {
		fail("Not yet implemented");
	}

	@Test 
	public void testToString() {
		fail("Not yet implemented");
	}

}
