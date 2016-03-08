
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Data class
 *
 * @authors: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 1.0
 */
public class DataTest {

    private Data data;
    
    @Before
    public void setUp() throws Exception {
        data = new Data();
        
    }

    /*
     * Checks for an existing user.
     */
    @Test
    public void testGetReturningUserRealUser() {
        AbstractUser user = data.getReturningUser("b@gmail.com");
        assertEquals("Krom", user.getLname());
    }
    
    /*
     * Check for a non-existing user.
     */
    @Test
    public void testGetReturningUserNotRealUser() {
        AbstractUser user = data.getReturningUser("junkEmail@gmail.com");
        assertNull(user);
    }
    
    /*
     * Checks for adding a new user into the system.
     */
    @Test
    public void testAddUser() {
        UrbanParkStaffMember staff = new UrbanParkStaffMember("Tim", "Jones", "tj@gmail.com");
        data.addUser(staff);
        assertTrue(data.getUsers().contains(staff));
    }
    
    /*
     * Checks job deletion from the system.
     */
    public void testDeletJob() throws IOException {
        Job job = new Job("Title", "03/20/2016", "1:00pm", "1",
                "Tacoma", "Evergreen Park", "Description:..", 1, 2, 3);
        data.addJob(job);
        assertTrue(data.getJobs().contains(job));
        data.deleteJob(job);
        assertFalse(data.getJobs().contains(job));
    }
    
    /*
     * Checks searching a volunteer by their last name
     * within the system.
     */
    @Test
    public void testSearchVolunteerByLastLnameRealUser() throws VolunteerNotFoundException {
        ArrayList<Volunteer> vol = data.searchVolunteerByLname("Krom");
        assertEquals("Krom", vol.get(0).getLname());
        
    }
    
    /*
     * Tests the exception VolunteerNotFoundException
     * if a volunteer is not found in the system.
     */
    @Test(expected = VolunteerNotFoundException.class)
    public void testSearchVolunteerByLastLnameNotRealUser() throws VolunteerNotFoundException {
        @SuppressWarnings("unused")
		ArrayList<Volunteer> vol = data.searchVolunteerByLname("Not a name");
    }

    /*
     * Tests if a job is added to the system. 
     */
    @Test
    public void testAddJob() throws IOException {
        Job job = new Job("Title", "02/26/2016", "1:00pm", "1",
                "Tacoma", "Evergreen Park", "Description:..", 1, 2, 3);
        data.addJob(job);
        assertTrue(data.getJobs().contains(job));
        data.deleteJob(job);
    }
}



