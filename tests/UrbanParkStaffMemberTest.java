import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UrbanParkStaffMemberTest {

	UrbanParkStaffMember staff;
	
	@Before
	public void setUp() throws Exception {
		staff = new UrbanParkStaffMember("first", "last", "email");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsObject() {
		
		UrbanParkStaffMember same = new UrbanParkStaffMember("first", "last", "email");
		assertTrue(staff.equals(same));
	}
	
	@Test
	public void testNotEqualsObject() {
		UrbanParkStaffMember diff = new UrbanParkStaffMember("notfirst", "notlast", "notemail");
		assertFalse(staff.equals(diff));
	}
}


