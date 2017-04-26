package Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author it353s723
 */
@ManagedBean(name = "Recruiter")
@SessionScoped
public class RecruiterBean extends GenericUserBean{

    private String firstName;
    private String lastName;
    private String userName;
    private String university;
    private boolean accountStatus;
    private String email;
    private String phoneNumber;
    
    public RecruiterBean(){
    }
    public RecruiterBean(String firstName, String lastName, String userName, String university, boolean accountStatus, String email, String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.university = university;
        this.accountStatus = accountStatus;
        this.email = email;
        this.phoneNumber = phoneNumber;
                
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUniversity()
    {
        return university;
    }
    public void setUniversity(String university){
        this.university = university;
    }
    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
