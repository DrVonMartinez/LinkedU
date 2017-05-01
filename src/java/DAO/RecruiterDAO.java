/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import Model.*;

/**
 *
 * @author it353s723
 */
public interface RecruiterDAO 
{
    public int createRecruiter(RecruiterBean aRecruiter, LoginBean aLogin);
    public ArrayList findAll();
    public RecruiterBean findByName(String firstName, String lastName); 
    public ArrayList findByUserName(String userName);
}
