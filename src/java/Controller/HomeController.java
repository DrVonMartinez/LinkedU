/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.*;
import Model.*;
import java.util.ArrayList;
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
    
    protected GenericUserBean user;
    protected LoginBean login;
    protected boolean loggedIn;
    
    public HomeController(){
        user = new GenericUserBean();
        login = new LoginBean();
        loggedIn = false;
    }
    
    public String isLogged(ComponentSystemEvent event){
        
        //add <f:event listener="#{homeController.isLogged}" type="preRenderView" /> tag after <head> tag in html
        
        String navi = null;
        if (!loggedIn) {
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
        
        if(user.getAccountType().equalsIgnoreCase("student")){
            
        } else if(user.getAccountType().equalsIgnoreCase("recruiter")){
            
        } else if(user.getAccountType().equalsIgnoreCase("admin")){
            
        }
        
        UserDAO aUserDAO = new UserDAOImpl();    // Creating a new object each time.
        login.setUserName(user.getUserName());
        ArrayList existingUser = aUserDAO.findByUserID(user.getUserID());
        if(!existingUser.isEmpty()){   //User exists in DB already
            //System.out.println("user exisits in db. cannot create");
            FacesMessage message = new FacesMessage("This User ID already exists");
            FacesContext.getCurrentInstance().addMessage("signupForm:userID", message);
            return null;//"signUp.xhtml?faces-redirect=true";
        }
        int status = aUserDAO.createUser(user, login); // Doing anything with the object after this?
        if (status == 1){
            JavaMailApp.sendUserCreationEmail(user);
            loggedIn = true;
            return "echo.xhtml?faces-redirect=true"; // navigate to "echo.xhtml"
        }
        else
            return null; 
    }
    
    public String loginUser(){
        LoginDAO aLoginDAO = new LoginDAOImpl();
        user = aLoginDAO.authenticateUser(login);
        if(user != null){
            loggedIn = true;
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
            loggedIn = false;
            //return to login and add error message
            FacesMessage message = new FacesMessage("Login unsuccessful. Invalid username or password.");
            //                                           vv change this vv                 vv add this to xhtml loginForm
            FacesContext.getCurrentInstance().addMessage("loginForm:userName", message); // <p><h:message for="userName" style="color: red;" /></p>
            return null;
        }
    }
}
