package DAO;

import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecruiterDAOImpl implements RecruiterDAO {

    @Override
    public int createRecruiter(RecruiterBean aRecruiter, LoginBean aLogin) {
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";// connection string, jdbc: java database connection
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String insertString;
            //String newFirstName = aUser.getFirstName().replace("'","''");
            //String newLastName = aUser.getLastName().replace("'","''");
            insertString = "INSERT INTO LinkedU.Recruiter VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = DBConn.prepareStatement(insertString);
            
            pstmt.setString(1, aRecruiter.getFirstName());
            pstmt.setString(2, aRecruiter.getLastName());
            pstmt.setString(3, aRecruiter.getUserName());
            pstmt.setString(4, aRecruiter.getUniversity());
            pstmt.setBoolean(5, aRecruiter.isAccountStatus());
            pstmt.setString(6, aRecruiter.getEmail());
            pstmt.setString(7, aRecruiter.getPhoneNumber());
            pstmt.setString(8, aRecruiter.getPhoneNetwork());
            
            rowCount = pstmt.executeUpdate();
            
            insertString = "INSERT INTO LinkedU.Login VALUES (?, ?, ?)";
            
            pstmt = DBConn.prepareStatement(insertString);
            
            pstmt.setString(1, aLogin.getUserName());
            pstmt.setString(2, aLogin.getPasswordHash());
            pstmt.setString(3, aLogin.getAccountType());

            
            pstmt.executeUpdate();
            //System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    @Override
    public ArrayList findAll() {

        ArrayList aCollection = null;
        return aCollection;

    }

     public List<RecruiterBean> loadRecruitersFromDB(String query) throws SQLException {

        List<RecruiterBean> recruiters = new ArrayList<RecruiterBean>();
        DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
        String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
        Connection DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
        RecruiterBean aRecruiter = null;
        try {
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                RecruiterBean rec = new RecruiterBean();
                rec.setFirstName(rs.getString("firstName"));
                rec.setLastName(rs.getString("lastName"));
                rec.setUserName(rs.getString("userName"));
                rec.setUniversity(rs.getString("university"));
                rec.setAccountStatus(rs.getBoolean("accountStatus"));
                rec.setEmail(rs.getString("email"));
                rec.setPhoneNumber(rs.getString("phoneNumber"));
                rec.setPhoneNetwork(rs.getString("phoneNetwork"));
                
                recruiters.add(rec);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return recruiters;
    } 

    @Override
    public RecruiterBean findByName(String firstName, String lastName) {
        String query = "SELECT * FROM LinkedU.Recruiter s WHERE s.firstName = " + firstName + " and s.lastName = " + lastName;
        RecruiterBean aRecruiter = new RecruiterBean();//loadRecruitersFromDB(query);
        return aRecruiter;
    }

    @Override
    public ArrayList findByUserName(String userName){
        ArrayList aRecruiterCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM LinkedU.Recruiter WHERE USERNAME = ?";
            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1, userName); // replace the 1st ? with userID
            ResultSet rs = pstmt.executeQuery();
            
            RecruiterBean recruiter;
            while (rs.next()) {
                
                 //Same ordering as LinkedU.sql
                String fN, lN, uN, u, em, cS, pN, pNe;
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
                recruiter = new RecruiterBean(fN, lN, uN, u, aS, em, pN, pNe);
                
                // add the newly created object to the collection
                aRecruiterCollection.add(recruiter);
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
        return aRecruiterCollection;
    }
}
