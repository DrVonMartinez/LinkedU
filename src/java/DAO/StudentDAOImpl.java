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
            String myDB = "jdbc:derby://localhost:1527/LinkedU";// connection string, jdbc: java database connection
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
            pstmt.setString(5, aStudent.getCurrentUniversity());
            pstmt.setString(6, aStudent.getUniversityChoices());
            pstmt.setString(7, aStudent.getMajorChoices());
            pstmt.setString(8, aStudent.getEssay());
            pstmt.setString(9, aStudent.getActivities());
            pstmt.setInt(10, aStudent.getACTScores());
            pstmt.setInt(11, aStudent.getSATScores());
            pstmt.setDouble(12, aStudent.getGPA());
            pstmt.setString(13, aStudent.getGraduationDate());
            pstmt.setBoolean(14, aStudent.isAccountStatus());
            pstmt.setString(15, aStudent.getEmail());
            pstmt.setString(16, aStudent.getCurrentState());
            pstmt.setString(17, aStudent.getPhoneNumber());
            pstmt.setString(18, aStudent.getPhoneNetwork());
            
            rowCount = pstmt.executeUpdate();
            
            insertString = "INSERT INTO LinkedU.Login VALUES (?, ?)";
            
            pstmt = DBConn.prepareStatement(insertString);
            
            pstmt.setString(1, aLogin.getUserName());
            pstmt.setString(2, aLogin.getPasswordHash());
            
            rowCount = pstmt.executeUpdate();
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

    private static StudentBean loadStudentsFromDB(String query) {

        DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
        String myDB = "jdbc:derby://localhost:1527/LinkedU";
        Connection DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

        ArrayList aStudentCollection = new ArrayList();
        StudentBean aStudent = null;
        try {

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String firstName, lastName, userName, highSchool, currentUniversity, universityChoices, majorChoices, essay, activities, email, currentState, phoneNumber, phoneNetwork;
            String graduationDate;
            int ACTScores, SATScores;
            double GPA;
            boolean accountStatus;
            while (rs.next()) {
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                userName = rs.getString("userName");
                highSchool = rs.getString("highSchool");
                currentUniversity = rs.getString("currentUniversity");
                universityChoices = rs.getString("universityChoices");
                majorChoices = rs.getString("majorChoices");
                essay = rs.getString("essay");
                activities = rs.getString("activities");
                ACTScores = rs.getInt("ACTScores");
                SATScores = rs.getInt("SATScores");
                GPA = rs.getDouble("GPA");
                graduationDate = rs.getString("graduationDate");
                accountStatus = rs.getBoolean("accountStatus");
                email = rs.getString("email");
                currentState = rs.getString("currentState");
                phoneNumber = rs.getString("phoneNumber");
                phoneNetwork = rs.getString("phoneNetwork");

                // make a StudentOld object out of the values
                aStudent = new StudentBean(firstName, lastName, userName, highSchool, currentUniversity, universityChoices, majorChoices, essay, activities, ACTScores, SATScores, GPA, graduationDate, accountStatus, email, currentState, phoneNumber, phoneNetwork);

                // add the newly created object to the collection
                aStudentCollection.add(aStudent);
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
        aStudent.setStudents(aStudentCollection);
        return aStudent;
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
            String myDB = "jdbc:derby://localhost:1527/LinkedU";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM LinkedU.Student WHERE USERNAME = ?";
            PreparedStatement pstmt = DBConn.prepareStatement(query);
            pstmt.setString(1, userName); // replace the 1st ? with userID
            ResultSet rs = pstmt.executeQuery();
            
            StudentBean student;
            while (rs.next()) {
                
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
                student = new StudentBean(fN, lN, uN, hS, cU, uC, mC, e, a, ACT, SAT, GPA, gD, aS, em, cS, pN, pNe);
                
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
