/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.UniversityBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author it353s728
 */
public class UniversityDAOImpl implements UniversityDAO{

    @Override
    public int createUniverstiy(UniversityBean aUniversity) {
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
            insertString = "INSERT INTO LinkedU.University VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pstmt = DBConn.prepareStatement(insertString);
            
            pstmt.setString(1, aUniversity.getUniversityName());
            pstmt.setString(2, aUniversity.getUSState());
            pstmt.setString(3, aUniversity.getPicture());
            pstmt.setString(4, aUniversity.getBio());
            pstmt.setDouble(5, aUniversity.getIdealGPA());
            pstmt.setInt(6, aUniversity.getIdealACT());
            pstmt.setInt(7, aUniversity.getIdealSAT());
            pstmt.setString(8, aUniversity.getWebsiteLink());
            pstmt.setString(9, aUniversity.getApplicationLink());
            pstmt.setBoolean(10, aUniversity.isHighlighted());
            pstmt.setString(11, aUniversity.getNotableMajors());
            pstmt.setInt(12, aUniversity.getTimeSubscribed());
            
            rowCount = pstmt.executeUpdate();           
            
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
        ArrayList aUniversityCollection = new ArrayList();
        Connection DBConn = null;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            String query = "SELECT * FROM LinkedU.University;";
            PreparedStatement pstmt = DBConn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            UniversityBean university;
            while(rs.next())
            {
                String uN, USS, pi,bi, wL,aL, nM;
                double iGPA;
                int id,iACT,iSAT, tS;
                boolean h;
                id=rs.getInt("id");
                uN = rs.getString("universityName");
                USS = rs.getString("USState");
                pi = rs.getString("picture");
                bi = rs.getString("bio");
                iGPA = rs.getDouble("idealGPA");
                iACT = rs.getInt("idealACT");
                iSAT = rs.getInt("idealSAT");
                wL = rs.getString("websiteLink");
                aL = rs.getString("applicationLink");
                h = rs.getBoolean("highlighted");
                nM = rs.getString("notableMajors");
                tS = rs.getInt("timeSubscribed");
                university = new UniversityBean(id,uN,USS,pi,bi,iGPA,iACT,iSAT,wL,aL,h,nM,tS);
                
                aUniversityCollection.add(university);
            }
            rs.close();
            pstmt.close();
        }  
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        try{
            DBConn.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return aUniversityCollection;
    }

    @Override
    public UniversityBean findByName(String universityName) {
        UniversityBean aUniversity = null;
        Connection DBConn = null;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            String query = "SELECT * FROM LinkedU.University u WHERE u.universityName = ?;";
            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1, universityName);
            ResultSet rs = pstmt.executeQuery();
            String uN, USS, pi,bi, wL,aL, nM;
            double iGPA;
            int id,iACT,iSAT, tS;
            boolean h;
            id=rs.getInt("id");
            uN = rs.getString("universityName");
            USS = rs.getString("USState");
            pi = rs.getString("picture");
            bi = rs.getString("bio");
            iGPA = rs.getDouble("idealGPA");
            iACT = rs.getInt("idealACT");
            iSAT = rs.getInt("idealSAT");
            wL = rs.getString("websiteLink");
            aL = rs.getString("applicationLink");
            h = rs.getBoolean("highlighted");
            nM = rs.getString("notableMajors");
            tS = rs.getInt("timeSubscribed");
            aUniversity = new UniversityBean(id,uN,USS,pi,bi,iGPA,iACT,iSAT,wL,aL,h,nM,tS);
            rs.close();
            pstmt.close();
        }  
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        try{
            DBConn.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return aUniversity;
    }

    @Override
    public ArrayList findByState(String state) {
       ArrayList aUniversityCollection = new ArrayList();
        Connection DBConn = null;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            String query = "SELECT * FROM LinkedU.University u WHERE u.USState = ?;";
            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1,state);
            ResultSet rs = pstmt.executeQuery();
            UniversityBean university;
            while(rs.next())
            {
                String uN, USS, pi,bi, wL,aL, nM;
                double iGPA;
                int id,iACT,iSAT, tS;
                boolean h;
                id=rs.getInt("id");
                uN = rs.getString("universityName");
                USS = rs.getString("USState");
                pi = rs.getString("picture");
                bi = rs.getString("bio");
                iGPA = rs.getDouble("idealGPA");
                iACT = rs.getInt("idealACT");
                iSAT = rs.getInt("idealSAT");
                wL = rs.getString("websiteLink");
                aL = rs.getString("applicationLink");
                h = rs.getBoolean("highlighted");
                nM = rs.getString("notableMajors");
                tS = rs.getInt("timeSubscribed");
                university = new UniversityBean(id,uN,USS,pi,bi,iGPA,iACT,iSAT,wL,aL,h,nM,tS);
                
                aUniversityCollection.add(university);
            }
            rs.close();
            pstmt.close();
        }  
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        try{
            DBConn.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return aUniversityCollection;
    }

    @Override
    public UniversityBean findHighlighted() {
        UniversityBean aUniversity = null;
        Connection DBConn = null;
        try{
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            String query = "SELECT * FROM LinkedU.University u WHERE u.highlighted = TRUE;";
            PreparedStatement pstmt = DBConn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            String uN, USS, pi,bi, wL,aL, nM;
            double iGPA;
            int id,iACT,iSAT, tS;
            boolean h;
            id=rs.getInt("id");
            uN = rs.getString("universityName");
            USS = rs.getString("USState");
            pi = rs.getString("picture");
            bi = rs.getString("bio");
            iGPA = rs.getDouble("idealGPA");
            iACT = rs.getInt("idealACT");
            iSAT = rs.getInt("idealSAT");
            wL = rs.getString("websiteLink");
            aL = rs.getString("applicationLink");
            h = rs.getBoolean("highlighted");
            nM = rs.getString("notableMajors");
            tS = rs.getInt("timeSubscribed");
            aUniversity = new UniversityBean(id,uN,USS,pi,bi,iGPA,iACT,iSAT,wL,aL,h,nM,tS);
            rs.close();
            pstmt.close();
        }  
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        try{
            DBConn.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return aUniversity;
    }
    
}
