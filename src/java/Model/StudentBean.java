package model;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import DAO.StudentDAOImpl;
import java.util.Date;

@ManagedBean(name = "Student")
@SessionScoped
public class StudentBean 
{

    private String firstName;
    private String lastName;
    private String userName;
    private String highSchool;
    private String currentUniversity;
    private String universityChoices;
    private String majorChoices;
    private String essay;
    private String activities;
    private int ACTScores;
    private int SATScores;
    private double GPA;
    private Date graduationDate;
    private boolean accountStatus;
    private String email;
    private String currentState;
    private String phoneNumber;

    private ArrayList students;

    public StudentBean() 
    {
    }

    public StudentBean(String firstName, String lastName, String userName, String highSchool, String currentUniversity, String universityChoices, String majorChoices, String essay, String activities, int ACTScores, int SATScores, double GPA, Date graduationDate, boolean accountStatus, String email, String currentState, String phoneNumber, ArrayList students) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.highSchool = highSchool;
        this.currentUniversity = currentUniversity;
        this.universityChoices = universityChoices;
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
        this.students = students;
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

    public String getCurrentUniversity() {
        return currentUniversity;
    }

    public void setCurrentUniversity(String currentUniversity) {
        this.currentUniversity = currentUniversity;
    }

    public String getUniversityChoices() {
        return universityChoices;
    }

    public void setUniversityChoices(String universityChoices) {
        this.universityChoices = universityChoices;
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

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public boolean getAccountStatus() {
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
        this.phoneNumber = phoneNumber;
    }

    public ArrayList getStudents() {
        if (students == null) {
            StudentBean aStu = (new StudentDAOImpl()).findByName(firstName, lastName);
            this.userName = aStu.userName;
            this.highSchool = aStu.highSchool;
            this.currentUniversity = aStu.currentUniversity;
            this.universityChoices = aStu.universityChoices;
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
            this.students = aStu.students;

        }
        return students;
    }

    public void setStudents(ArrayList students) {
        this.students = students;
    }

}
