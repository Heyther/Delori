import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Test suite for all tests within this program.
 * 
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
