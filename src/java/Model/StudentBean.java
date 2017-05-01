package Model;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import DAO.StudentDAOImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name = "StudentBean")
@SessionScoped
public class StudentBean extends GenericUserBean implements Serializable
{

    private String firstName;
    private String lastName;
    private String userName;
    private String highSchool;
    private String majorChoices;
    private String essay;
    private String activities;
    private int ACTScores;
    private int SATScores;
    private double GPA;
    private String graduationDate;
    private boolean accountStatus;
    private String email;
    private String currentState;
    private String phoneNumber;
    private String phoneNetwork;
    
    private List<StudentBean> students;

    public StudentBean() 
    {
    }

    public StudentBean(StudentBean sb){
        this.firstName = sb.firstName;
        this.lastName = sb.lastName;
        this.userName = sb.userName;
        this.highSchool = sb.highSchool;
        this.majorChoices = sb.majorChoices;
        this.essay = sb.essay;
        this.activities = sb.activities;
        this.ACTScores = sb.ACTScores;
        this.SATScores = sb.SATScores;
        this.GPA = sb.GPA;
        this.graduationDate = sb.graduationDate;
        this.accountStatus = sb.accountStatus;
        this.email = sb.email;
        this.currentState = sb.currentState;
        this.phoneNumber = sb.phoneNumber;
        this.phoneNetwork = sb.phoneNetwork;
        this.students = sb.students;
    }
    
    public StudentBean(String firstName, String lastName, String userName, String highSchool, String majorChoices, String essay, String activities, int ACTScores, int SATScores, double GPA, String graduationDate, boolean accountStatus, String email, String currentState, String phoneNumber, String phoneNetwork, ArrayList students) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.highSchool = highSchool;
        this.majorChoices = majorChoices;
        this.essay = essay;
        this.activities = activities;
        this.ACTScores = ACTScores;
        this.SATScores = SATScores;
        this.GPA = GPA;
        this.graduationDate = graduationDate;
        this.accountStatus = accountStatus;
        this.email = email;
        this.currentState = currentState;
        this.phoneNumber = phoneNumber;
        this.phoneNetwork = phoneNetwork;
        this.students = students;
    }
    
    public StudentBean(String firstName, String lastName, String userName, String highSchool, String majorChoices, String essay, String activities, int ACTScores, int SATScores, double GPA, String graduationDate, boolean accountStatus, String email, String currentState, String phoneNumber, String phoneNetwork) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.highSchool = highSchool;
        this.majorChoices = majorChoices;
        this.essay = essay;
        this.activities = activities;
        this.ACTScores = ACTScores;
        this.SATScores = SATScores;
        this.GPA = GPA;
        this.graduationDate = graduationDate;
        this.accountStatus = accountStatus;
        this.email = email;
        this.currentState = currentState;
        this.phoneNumber = phoneNumber;
        this.phoneNetwork = phoneNetwork;
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

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getMajorChoices() {
        return majorChoices;
    }

    public void setMajorChoices(String majorChoices) {
        this.majorChoices = majorChoices;
    }

    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public int getACTScores() {
        return ACTScores;
    }

    public void setACTScores(int ACTScores) {
        this.ACTScores = ACTScores;
    }

    public int getSATScores() {
        return SATScores;
    }

    public void setSATScores(int SATScores) {
        this.SATScores = SATScores;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
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

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.contains("-"))
        {
            String[] brokenNumber = phoneNumber.split("-");
            phoneNumber = "";
            for(String i :brokenNumber)
                phoneNumber += i;
        }
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNetwork() {
        return phoneNetwork;
    }

    public void setPhoneNetwork(String phoneNetwork) {
        this.phoneNetwork = phoneNetwork;
    }

    public ArrayList getStudents() {
        if (students == null) {
            StudentBean aStu = (new StudentDAOImpl()).findByName(firstName, lastName);
            this.userName = aStu.userName;
            this.highSchool = aStu.highSchool;
            this.majorChoices = aStu.majorChoices;
            this.essay = aStu.essay;
            this.activities = aStu.activities;
            this.ACTScores = aStu.ACTScores;
            this.SATScores = aStu.SATScores;
            this.GPA = aStu.GPA;
            this.graduationDate = aStu.graduationDate;
            this.accountStatus = aStu.accountStatus;
            this.email = aStu.email;
            this.currentState = aStu.currentState;
            this.phoneNumber = aStu.phoneNumber;
            this.phoneNetwork = aStu.phoneNetwork;
            this.students = aStu.students;

        }
        return (ArrayList) students;
    }

    public void setStudents(ArrayList students) {
        this.students = students;
    }
    
    public void search() throws SQLException {
        String query = "SELECT * FROM LINKEDU.STUDENT s " + "WHERE s.lastName LIKE '%" + lastName + "%'";
        students = (new StudentDAOImpl()).loadStudentsFromDB(query);
    }

}
