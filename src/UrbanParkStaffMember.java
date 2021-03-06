import java.util.Objects;

/**
 * Console driven i/o Urban Park Staff Member.
 * 
 * @author: Luciana, Winfield, Heather, Sean
 * @date 3/8/2016
 * @version 2.0
 */
public class UrbanParkStaffMember extends AbstractUser {

	private static final long serialVersionUID = 1483276272402890408L;
	protected UserStatus role;
	
	/*
	 * Constructs an Urban Park Staff Member.
	 */
	public UrbanParkStaffMember(String theFirstName, String theLastName, String theEmail) {
		super(theFirstName, theLastName, theEmail);
		role = UserStatus.UPSMEMBER;
	}


	/*
	 * Retrieves the user's role.
	 * @see AbstractUser#getRole()
	 */
	public UserStatus getRole() {
		return role;
	}
	
	public String getRoleString() {
		return "Urban Park Staff Member";
	}

	/*
	 * Sets the user's role.
	 */
	public void setRole(UserStatus role) {
		this.role = role;
	}
	
    /*
     * Hashcode for equals method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }
	
	/*
	 * Checks if the object is-a Urban Park Staff Member.
	 * @see AbstractUser#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object theObject){
		if (super.equals(theObject)) {
			UrbanParkStaffMember v = (UrbanParkStaffMember) theObject;
			if (getRole().equals(v.getRole())) { return true; };
		}
		return false;
	}
}
