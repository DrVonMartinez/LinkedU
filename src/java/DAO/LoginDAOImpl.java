/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import Controller.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author it353s723
 */
public class LoginDAOImpl implements LoginDAO{
    
    @Override
    public GenericUserBean authenticateUser(LoginBean aLogin){
        GenericUserBean user = new GenericUserBean();
        String accountType = "";
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/LinkedU";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
        
            String query = "SELECT * FROM LinkedU.Login WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement pstmt = DBConn.prepareStatement(query);
            //System.out.println(aLogin.getUserID() + "   --   " + aLogin.getPasswordHash());
            pstmt.setString(1, aLogin.getUserName());
            pstmt.setString(2, aLogin.getPasswordHash());

            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) { //login successful
                
                accountType = rs.getString("AccountType");
                
                if(accountType.equalsIgnoreCase("student")){
                    //user = new StudentBean();
                    query = "SELECT * FROM LinkedU.Student WHERE USERNAME = ?";
                    pstmt = DBConn.prepareStatement(query);
                    pstmt.setString(1, aLogin.getUserName()); // replace the 1st ? with userID
                    rs = pstmt.executeQuery();

                    if(rs.next()){
                        
                        //Same ordering as LinkedU.sql
                        String fN, lN, uN, hS, cU, uC, mC, e, a, gD, em, cS, pN, pNe;
                        int ACT, SAT;
                        double GPA;
                        boolean aS;
                        
                        fN = rs.getString("firstName");
                        lN = rs.getString("lastName");
                        uN = rs.getString("userName");
                        hS = rs.getString("highSchool");
                        cU = rs.getString("currentUniversity");
                        uC = rs.getString("universityChoices");
                        mC = rs.getString("majorChoices");
                        e = rs.getString("essays");
                        a = rs.getString("activities");
                        gD = rs.getString("graduationDate");
                        em = rs.getString("email");
                        cS = rs.getString("currentState");
                        pN = rs.getString("phoneNumber");
                        pNe = rs.getString("phoneNetwork");
                        ACT = rs.getInt("ACTScores");
                        SAT = rs.getInt("SATScores");
                        GPA = rs.getDouble("GPA");
                        aS = rs.getBoolean("accountStatus");

                        // make a StudentBean object out of the values
                        user = new StudentBean(fN, lN, uN, hS, cU, uC, mC, e, a, ACT, SAT, GPA, gD, aS, em, cS, pN, pNe, null);
                    } else{
                        user = null;
                    }
                    
                } else if(accountType.equalsIgnoreCase("recruiter")){
                    query = "SELECT * FROM LinkedU.Recruiter WHERE USERNAME = ?";
                    pstmt = DBConn.prepareStatement(query);
                    pstmt.setString(1, aLogin.getUserName()); // replace the 1st ? with userID
                    rs = pstmt.executeQuery();

                    if(rs.next()){
                        
                        //Same ordering as LinkedU.sql
                        String fN, lN, uN, u, em, pN, pNe;
                        boolean aS;
                        
                        fN = rs.getString("firstName");
                        lN = rs.getString("lastName");
                        uN = rs.getString("userName");
                        u = rs.getString("university");
                        em = rs.getString("email");
                        pN = rs.getString("phoneNumber");
                        pNe = rs.getString("phoneNetwork");
                        aS = rs.getBoolean("accountStatus");

                        // make a RecruiterBean object out of the values
                        user = new RecruiterBean(fN, lN, uN, u, aS, em, pN, pNe);
                    } else{
                        user = null;
                    }
                    
                } else if(accountType.equalsIgnoreCase("admin")){
                    user = new AdminBean();
                }
                
            } else{ //login unsuccessful
                user = null;
            }
            rs.close();
            pstmt.close();
            
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }
}
