/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.*;
import Model.*;
import java.util.ArrayList;
import java.util.List;
import javamailapp.JavaMailApp;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author it353s723
 */
@ManagedBean
@SessionScoped
public class HomeController {
    
    private GenericUserBean user;
    private StudentBean student;
    private RecruiterBean recruiter;
    private LoginBean login;
    private boolean loggedIn;
    private ArrayList<String> accountTypes;
    
    public HomeController(){
        user = new GenericUserBean();
        login = new LoginBean();
        loggedIn = false;
        accountTypes = new ArrayList<>();
        accountTypes.add("Student");
        accountTypes.add("Recruiter");
    }
    
    public String isLogged(ComponentSystemEvent event){
        
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
    
    public String createUser(){
        
        if(getUser().getAccountType().equalsIgnoreCase("student")){
            StudentDAO studentDAO = new StudentDAOImpl();    // Creating a new object each time.
            getLogin().setUserName(getStudent().getUserName());
            setUser(new StudentBean(getStudent()));
            ArrayList existingUser = studentDAO.findByUserName(getStudent().getUserName());
            if(!existingUser.isEmpty()){   //User exists in DB already
                FacesMessage message = new FacesMessage("This Username already exists");
                FacesContext.getCurrentInstance().addMessage("signupForm:userName", message);
                return null;//"signUp.xhtml?faces-redirect=true";
            }
            int status = studentDAO.createStudent(getStudent(), getLogin());
            if (status == 1){
                //JavaMailApp.sendUserCreationEmail(user);
                setLoggedIn(true);
                return "index.xhtml?faces-redirect=true";
            }
            else{
                return null;
            }
        } else if(getUser().getAccountType().equalsIgnoreCase("recruiter")){
            //
            /*StudentDAO studentDAO = new StudentDAOImpl();    // Creating a new object each time.
            login.setUserName(student.getUserName());
            user = new StudentBean(student);
            ArrayList existingUser = studentDAO.findByUserName(student.getUserName());
            if(!existingUser.isEmpty()){   //User exists in DB already
                FacesMessage message = new FacesMessage("This Username already exists");
                FacesContext.getCurrentInstance().addMessage("signupForm:userName", message);
                return null;//"signUp.xhtml?faces-redirect=true";
            }
            int status = studentDAO.createStudent(student, login);
            if (status == 1){
                //JavaMailApp.sendUserCreationEmail(user);
                loggedIn = true;
                return "index.xhtml?faces-redirect=true";
            }
            else{
                return null;
            }*/
        } else if(getUser().getAccountType().equalsIgnoreCase("admin")){
            
        }
        return null;    //remove this later
    }
    
    public String loginUser(){
        LoginDAO aLoginDAO = new LoginDAOImpl();
        setUser(aLoginDAO.authenticateUser(getLogin()));
        if(getUser() != null){
            setLoggedIn(true);
            return "index.xhtml?faces-redirect=true";
            
            //Use this to redirect to different pages after login
            /*if(user instanceof StudentBean){
                //redirect to student page
            } else if(user instanceof RecruiterBean){
                //redirect to recruiter page
            } else if(user instanceof AdminBean){
                //redirect to admin page
            }
            return "index.xhtml?faces-redirect=true";*/
        } else{
            setLoggedIn(false);
            //return to login and add error message
            FacesMessage message = new FacesMessage("Login unsuccessful. Invalid username or password.");
            //                                           vv change this vv                 vv add this to xhtml loginForm
            FacesContext.getCurrentInstance().addMessage("loginForm:userName", message); // <p><h:message for="userName" style="color: red;" /></p>
            return null;
        }
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
    public ArrayList<String> getAccountTypes() {
        return accountTypes;
    }

    /**
     * @param accountTypes the accountTypes to set
     */
    public void setAccountTypes(ArrayList<String> accountTypes) {
        this.accountTypes = accountTypes;
    }
    
    
}
