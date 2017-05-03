/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IT353S728
 */
public class MajorDAOImpl implements MajorDAO {

    @Override
    public ArrayList findMajors(String majorList) {
       ArrayList<String> aMajorCollection = new ArrayList();
        Connection DBConn = null;
        String[] listOfMajors = majorList.split(",");
        for(String i :listOfMajors)
        {
            try{
                DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
                String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
                DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
                String query = "SELECT * FROM LinkedU.Major m WHERE m.ID = ?";
                PreparedStatement pstmt = DBConn.prepareStatement(query);
                pstmt.setString(1,i);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next())
                {
                    String majorName = rs.getString("MajorName");
                    
                    aMajorCollection.add(majorName);
                }
                rs.close();
                pstmt.close();
            }  
            catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
        }
        try{
            DBConn.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return aMajorCollection;
    }
    
}
