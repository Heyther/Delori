import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ParkManager class.
 * 
 * @authors: Luciana, Winfield, Heather, Sean
 * @date 2/16/2016
 * @version 1.0
 */
public class ParkManagerTest {

	ParkManager manager;
	
	@Before
	public void setUp() throws Exception {
		manager = new ParkManager("first", "last", "email", "park");
	}

	@Test
	public void testEqualsObject() {
		
		ParkManager same = new ParkManager("first", "last", "email", "park");
		assertTrue(manager.equals(same));
	}
	
	@Test
	public void testNotEqualsObject() {
		ParkManager diff = new ParkManager("notfirst", "notlast", "notemail", "notpark");
		assertFalse(manager.equals(diff));
	}
}
