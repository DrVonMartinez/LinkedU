/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.*;
import Model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javamailapp.JavaMailApp;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
//import java.io.Serializable;

/**
 *
 * @author it353s723
 */
@ManagedBean
@SessionScoped
public class HomeController/* implements Serializable*/{
    
    private GenericUserBean user;
    private StudentBean student;
    private RecruiterBean recruiter;
    private LoginBean login;
    private AppointmentBean appt;
    private boolean loggedIn;
    private ArrayList<String> accountTypes;
    private List<String> phoneCarriers;
            
    public HomeController(){
        user = new GenericUserBean();
        student = new StudentBean();
        recruiter = new RecruiterBean();
        login = new LoginBean();
        appt = new AppointmentBean();
        loggedIn = false;
        accountTypes = new ArrayList<>();
        accountTypes.add("Student");
        accountTypes.add("Recruiter");
    }

    public String isLogged(ComponentSystemEvent event) {

        //add <f:event listener="#{homeController.isLogged}" type="preRenderView" /> tag after <head> tag in html
        String navi = null;
        if (!isLoggedIn()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("login?faces-redirect=true");
        }
        return navi;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public String logout(){
        loggedIn = false;
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "login.xhtml?faces-redirect=true";
    }
    
    public String createRecruiter(){
        RecruiterDAO recruiterDAO = new RecruiterDAOImpl();   
        login.setUserName(recruiter.getUserName());
        login.setAccountType("Recruiter");

        ArrayList existingUser = recruiterDAO.findByUserName(recruiter.getUserName());
        if(!existingUser.isEmpty()){   //User exists in DB already
            FacesMessage message = new FacesMessage("This Username already exists");
            FacesContext.getCurrentInstance().addMessage("recruiterSignupForm:recUserName", message);
            return null;//"signUp.xhtml?faces-redirect=true";
        }
        int status = recruiterDAO.createRecruiter(recruiter, login);
        if (status == 1){
            //JavaMailApp.sendUserCreationEmail(user);
            welcomeSMS(2);
            setLoggedIn(true);
            return "index.xhtml?faces-redirect=true";
        }
        else{
            System.out.println("db status return isnt 1: " + status);
            return null;
        }
    }
    
    public String createStudent(){
        
        StudentDAO studentDAO = new StudentDAOImpl();
        login.setUserName(student.getUserName());
        login.setAccountType("Student");
        ArrayList existingUser = studentDAO.findByUserName(student.getUserName());
        if(!existingUser.isEmpty()){   //User exists in DB already
            FacesMessage message = new FacesMessage("This Username already exists");
            FacesContext.getCurrentInstance().addMessage("studentSignupForm:stuUserName", message);
            //System.out.println("student already exists");
            return null;//"signUp.xhtml?faces-redirect=true";
        }
        int status = studentDAO.createStudent(student, login);
        if (status == 1){
            //JavaMailApp.sendUserCreationEmail(user);
            welcomeSMS(1);
            setLoggedIn(true);
            return "index.xhtml?faces-redirect=true";
        }
        else{
            System.out.println("db status return isnt 1");
            return null;
        }
        
    }
    
    public boolean studentLoggedIn(){
        if(login == null || login.getAccountType() == null) return false;
        return loggedIn && (login.getAccountType().equals("Student"));
    }

    public String loginUser() {
        LoginDAO aLoginDAO = new LoginDAOImpl();
        setUser(aLoginDAO.authenticateUser(getLogin()));
        if (getUser() != null) {
            switch (user.getAccountType()) {
                case "Student":
                    student = new StudentBean((StudentBean)user);
                    login.setAccountType("Student");
                    setLoggedIn(true);
                    return "index.xhtml?faces-redirect=true";
                case "Recruiter":
                    recruiter = new RecruiterBean((RecruiterBean)user);
                    login.setAccountType("Recruiter");
                    setLoggedIn(true);
                    return "index.xhtml?faces-redirect=true";
                case "Admin":
                    setLoggedIn(true);
                    return "admin.xhtml?faces-redirect=true";
                default:
                    return null;
            }
        } else {
            setLoggedIn(false);
            //return to login and add error message
            FacesMessage message = new FacesMessage("Login unsuccessful. Invalid username or password.");
            //                                           vv change this vv                 vv add this to xhtml loginForm
            FacesContext.getCurrentInstance().addMessage("loginForm:userName", message); // <p><h:message for="userName" style="color: red;" /></p>
            return null;
        }
    }
    
    public String scheduleAppt(){
        appt.setStudent(new StudentBean(this.student));
        JavaMailApp.scheduleAppointmentEmail(appt);
        appt = new AppointmentBean();
        return "index.xhtml?faces-redirect=true";
    }

    /**
     * @return the user
     */
    public GenericUserBean getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(GenericUserBean user) {
        this.user = user;
    }

    /**
     * @return the student
     */
    public StudentBean getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(StudentBean student) {
        this.student = student;
    }

    /**
     * @return the recruiter
     */
    public RecruiterBean getRecruiter() {
        return recruiter;
    }

    /**
     * @param recruiter the recruiter to set
     */
    public void setRecruiter(RecruiterBean recruiter) {
        this.recruiter = recruiter;
    }

    
    /**
     * @return the login
     */
    public LoginBean getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(LoginBean login) {
        this.login = login;
    }

    /**
     * @return the accountTypes
     */
    public List<String> getAccountTypes() {
        return accountTypes;
    }

    /**
     * @param accountTypes the accountTypes to set
     */
    public void setAccountTypes(ArrayList<String> accountTypes) {
        this.accountTypes = accountTypes;
    }

    /**
     * @return the phoneCarriers
     */
    public List<String> getPhoneCarriers() {
        phoneCarriers = Model.TextMessageWriter.getCarriers();
        //phoneCarriers = new ArrayList<>();
        return phoneCarriers;
    }

    /**
     * @param phoneCarriers the phoneCarriers to set
     */
    public void setPhoneCarriers(List<String> phoneCarriers) {
        this.phoneCarriers = phoneCarriers;
    }
    
    public ArrayList<String> getUniversities(){
        //Access UniversityDAO to get full list of active universities
        UniversityDAO universityDB = new UniversityDAOImpl();
        ArrayList<UniversityBean> universityData = universityDB.findAll();
        //this for temporary use.
        //ArrayList<String> u = new ArrayList();
        //u.add("University of Illinois");
        //u.add("Illinois State University");
        ArrayList u = new ArrayList();
        for(UniversityBean i: universityData)
        {
            u.add(i.getUniversityName());
        }
        
        return u;
    }
    
    
    private void welcomeSMS(int messageType)
    {
        String cellPhoneNetwork, cellPhoneNumber, name;
        if(messageType == 1)
        {
            cellPhoneNetwork = student.getPhoneNetwork();
            cellPhoneNumber = student.getPhoneNumber();
            name = student.getFirstName() + " " + student.getLastName();
            Model.TextMessageWriter.sendSMS(cellPhoneNetwork, cellPhoneNumber, "Welcome to LinkedU " + name + "!");
            System.out.println("Sent! (" + messageType + ") (to " + cellPhoneNumber + " on " + cellPhoneNetwork + ")");
        }
        else if(messageType == 2)
        {
            cellPhoneNetwork = recruiter.getPhoneNetwork();
            cellPhoneNumber = recruiter.getPhoneNumber();
            name = recruiter.getFirstName() + " " + recruiter.getLastName();
            Model.TextMessageWriter.sendSMS(cellPhoneNetwork, cellPhoneNumber, "Welcome to LinkedU " + name + "!");
            System.out.println("Sent! (" + messageType + ") (to " + cellPhoneNumber + " on " + cellPhoneNetwork + ")");
        }
    }

    /**
     * @return the appt
     */
    public AppointmentBean getAppt() {
        return appt;
    }

    /**
     * @param appt the appt to set
     */
    public void setAppt(AppointmentBean appt) {
        this.appt = appt;
    }
}
