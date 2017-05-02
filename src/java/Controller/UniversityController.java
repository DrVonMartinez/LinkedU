/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UniversityBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    
    
}
