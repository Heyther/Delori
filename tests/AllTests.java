import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Test suite for all tests within this program.
 * In order to run these tests it requires editing the specifically commented lines
 * inside Calendar. These changes are at line 32 and line 91 of Calendar.java
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ CalendarTest.class, DataTest.class, JobTest.class,
		ParkManagerTest.class, UrbanParkStaffMemberTest.class,
		VolunteerTest.class })
public class AllTests {

}
