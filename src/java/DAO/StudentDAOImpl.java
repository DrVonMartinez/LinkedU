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

public class StudentDAOImpl implements StudentDAO {

    @Override
    public int createStudent(StudentBean aStudent, LoginBean aLogin) {
        
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
            insertString = "INSERT INTO LinkedU.Student VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = DBConn.prepareStatement(insertString);
            
            pstmt.setString(1, aStudent.getFirstName());
            pstmt.setString(2, aStudent.getLastName());
            pstmt.setString(3, aStudent.getUserName());
            pstmt.setString(4, aStudent.getHighSchool());
            pstmt.setString(5, aStudent.getMajorChoices());
            pstmt.setString(6, aStudent.getEssay());
            pstmt.setString(7, aStudent.getActivities());
            pstmt.setInt(8, aStudent.getACTScores());
            pstmt.setInt(9, aStudent.getSATScores());
            pstmt.setDouble(10, aStudent.getGPA());
            pstmt.setString(11, aStudent.getGraduationDate());
            pstmt.setBoolean(12, aStudent.isAccountStatus());
            pstmt.setString(13, aStudent.getEmail());
            pstmt.setString(14, aStudent.getCurrentState());
            pstmt.setString(15, aStudent.getPhoneNumber());
            pstmt.setString(16, aStudent.getPhoneNetwork());
            
            rowCount = pstmt.executeUpdate();
            
            insertString = "INSERT INTO LinkedU.Login VALUES (?, ?, ?)";
            
            pstmt = DBConn.prepareStatement(insertString);
            
            pstmt.setString(1, aLogin.getUserName());
            pstmt.setString(2, aLogin.getPasswordHash());
            pstmt.setString(2, aLogin.getAccountType());

            
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

     public List<StudentBean> loadStudentsFromDB(String query) throws SQLException {

        List<StudentBean> students = new ArrayList<StudentBean>();
        DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
        String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
        Connection DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
        StudentBean aStudent = null;
        try {
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                StudentBean stu = new StudentBean();
                stu.setFirstName(rs.getString("firstName"));
                stu.setLastName(rs.getString("lastName"));
                stu.setUserName(rs.getString("userName"));
                stu.setHighSchool(rs.getString("highSchool"));
                stu.setMajorChoices(rs.getString("majorChoices"));
                stu.setEssay(rs.getString("essay"));
                stu.setActivities(rs.getString("activities"));
                stu.setACTScores(rs.getInt("ACTScores"));
                stu.setSATScores(rs.getInt("SATScores"));
                stu.setGPA(rs.getDouble("GPA"));
                stu.setGraduationDate(rs.getString("graduationDate"));
                stu.setAccountStatus(rs.getBoolean("accountStatus"));
                stu.setEmail(rs.getString("email"));
                stu.setCurrentState(rs.getString("currentState"));
                stu.setPhoneNumber(rs.getString("phoneNumber"));
                stu.setPhoneNetwork(rs.getString("phoneNetwork"));
                
                students.add(stu);
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
        return students;
    } 

    @Override
    public StudentBean findByName(String firstName, String lastName) {
        String query = "SELECT * FROM LinkedU.Student s WHERE s.firstName = " + firstName + " and s.lastName = " + lastName;
        StudentBean aStudent = new StudentBean();//loadStudentsFromDB(query);
        return aStudent;
    }

    @Override
    public ArrayList findByUserName(String userName){
        ArrayList aStudentCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://gfish2.it.ilstu.edu:1527/mwcoope_Sp2017_LinkedU";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM LinkedU.Student WHERE USERNAME = ?";
            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1, userName); // replace the 1st ? with userID
            ResultSet rs = pstmt.executeQuery();
            
            StudentBean student;
            while (rs.next()) {
                
                 //Same ordering as LinkedU.sql
                String fN, lN, uN, hS, mC, e, a, gD, em, cS, pN, pNe;
                int ACT, SAT;
                double GPA;
                boolean aS;

                fN = rs.getString("firstName");
                lN = rs.getString("lastName");
                uN = rs.getString("userName");
                hS = rs.getString("highSchool");
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
                student = new StudentBean(fN, lN, uN, hS, mC, e, a, ACT, SAT, GPA, gD, aS, em, cS, pN, pNe);
                
                // add the newly created object to the collection
                aStudentCollection.add(student);
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
        return aStudentCollection;
    }
}
