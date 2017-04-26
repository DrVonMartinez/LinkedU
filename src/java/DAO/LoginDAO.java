/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;

/**
 *
 * @author it353s723
 */
public interface LoginDAO {
    
    public GenericUserBean authenticateUser(LoginBean aLogin);
    
}
