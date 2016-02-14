



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VolunteerTest {
	
	private Volunteer vol;
	
	
	/*
	 * 
	 */
	@Before
    public void setUp() {
		vol = new Volunteer("John", "Doe", "dough@gmail.com");
	}


    /*
     * Test Volunteer Constructor
     */
	@Test
	public void testVolunteer() {
		Volunteer vol1 = new Volunteer("John", "Doe", "dough@gmail.com");
		assertEquals("Not a volunteer. ", "John", vol1.getFname());
	}


//	
//    /*
//     * Test enrolled jobs
//     */
//	@Test
//	public void testViewJobsEnrolled() {
//		Volunteer vol1 = new Volunteer("John", "Doe", "dough@gmail.com");
//		assertEquals("Not a volunteer. ", 0, vol1);
//	}
//	
//    /*
//     * 
//     */
//	@Test
//	public void testSignUp() {
//		fail("Not yet implemented");
//	}
//	
//	
//    /*
//     * 
//     */
//	@Test
//	public void testCancelEnrolledJob() {
//		fail("Not yet implemented");
//	}

}
