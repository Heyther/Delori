import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Volunteer class
 * 
 * @authors: Luciana, Winfield, Heather, Sean
 * @date 2/16/2016
 * @version 1.0
 */
public class VolunteerTest {
	
	private Volunteer vol;

	@Before
    public void setUp() {
		vol = new Volunteer("John", "Doe", "dough@gmail.com");
	}


	@Test
	public void testEqualsObject() {
		Volunteer same = new Volunteer("John", "Doe", "dough@gmail.com");
		assertTrue(vol.equals(same));
	}
	
	@Test
	public void testNotEqualsObject() {
		Volunteer diff = new Volunteer("notfirst", "notlast", "notemail");
		assertFalse(vol.equals(diff));
	}

}
