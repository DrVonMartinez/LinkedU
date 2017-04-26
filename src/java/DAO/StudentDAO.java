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
public interface StudentDAO 
{
    public int createStudent(StudentBean aStudent, LoginBean aLogin);
    public ArrayList findAll();
    public StudentBean findByName(String firstName, String lastName); 
    public ArrayList findByUserName(String userName);
}
