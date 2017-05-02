/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.UniversityBean;
import java.util.ArrayList;

/**
 *
 * @author it353s728
 */
public interface UniversityDAO {
    public int createUniverstiy(UniversityBean aUniversity);
    public ArrayList findAll();
    public UniversityBean findByName(String universityName); 
    public ArrayList findByState(String state);
    public UniversityBean findHighlighted();
}
