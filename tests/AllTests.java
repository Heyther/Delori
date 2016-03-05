import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ CalendarTest.class, DataTest.class, JobTest.class,
		ParkManagerTest.class, UrbanParkStaffMemberTest.class,
		VolunteerTest.class })
public class AllTests {

}
