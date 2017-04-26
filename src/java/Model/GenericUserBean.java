/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author it353s723
 */
public class GenericUserBean {
    
    private String accountType;
    
    public GenericUserBean(){
        accountType = null;
    }
    
    public GenericUserBean(String type){
        accountType = type;
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
