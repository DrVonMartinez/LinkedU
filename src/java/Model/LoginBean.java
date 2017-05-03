/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author it353s723
 */
@ManagedBean
@SessionScoped
public class LoginBean extends GenericUserBean{
    
    private String userName;
    private String passwordHash;
    private String accountType;
    public static final String SALT = "Octane";

    public LoginBean(){
        userName = null;
        passwordHash = null;
        accountType = null;
    }
    
    public LoginBean(String userName, String password, String accountType){
        this.userName = userName;
        passwordHash = generateHash(SALT + password);
        this.accountType = accountType;
    }
    
    public LoginBean(String userName, String password){
        this.userName = userName;
        passwordHash = generateHash(SALT + password);
        accountType = null;
    }
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash the passwordHash to set
     */
    public void setPasswordHash(String password) {
        passwordHash = generateHash(SALT + password);
    }
    
    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
                MessageDigest sha = MessageDigest.getInstance("SHA-1");
                byte[] hashedBytes = sha.digest(input.getBytes());
                char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                'a', 'b', 'c', 'd', 'e', 'f' };
                for (int idx = 0; idx < hashedBytes.length; ++idx) {
                        byte b = hashedBytes[idx];
                        hash.append(digits[(b & 0xf0) >> 4]);
                        hash.append(digits[b & 0x0f]);
                }
        } catch (NoSuchAlgorithmException e) {
                // handle error here.
        }

        return hash.toString();
    }

    /**
     * @return the accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
