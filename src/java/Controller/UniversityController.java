/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UniversityDAO;
import DAO.UniversityDAOImpl;
import Model.UniversityBean;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author it353s728
 */
@ManagedBean
@SessionScoped
public class UniversityController {
    UniversityBean university;
    /**
     * Creates a new instance of UniversityController
     */
    public UniversityController() {
    }

    public UniversityBean getUniversity() {
        return university;
    }

    public void setUniversity(UniversityBean university) {
        this.university = university;
    }
    
    public String applyNow()
    {
        return university.getApplicationLink();
    }
    
    public String visitUs()
    {
        return university.getWebsiteLink();
    }
    
    public String requestUniversityInfo (ComponentSystemEvent event)
    {
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        UniversityDAO universityDB = new UniversityDAOImpl();

        //String reformatted = req.getParameter("university");
        try{
        university = universityDB.findByName(req.getParameter("university").replaceAll("%20", " "));
        }
        catch(NullPointerException npe)
        {
            university = null;
        }
        //System.out.println(university.getIdealACT());
        if(university == null)
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("index?faces-redirect=true");
        }
        return null;
    }
    
    public String findHighlighted(ComponentSystemEvent event)
    {
        UniversityDAO universityDB = new UniversityDAOImpl();
        university = universityDB.findHighlighted();
        System.out.println(university.getPicture());
        return null;
    }
    
    public String navigateTo(String name)
    { 
         return "university.xhtml?university="+name;
    }
}
