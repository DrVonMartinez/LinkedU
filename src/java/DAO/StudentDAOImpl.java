package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import model.StudentBean;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public int createStudent(StudentBean aStudent) {
        return 0;
    }

    @Override
    public ArrayList findAll() {

        ArrayList aCollection = null;
        return aCollection;

    }

    private static StudentBean loadStudentsFromDB(String query) {

        DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
        String myDB = "jdbc:derby://localhost:1527/INSERTDATABSENAMEHERE";
        Connection DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

        ArrayList aStudentCollection = new ArrayList();
        StudentBean aStudent = null;
        try {

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String firstName, lastName, userName, highSchool, currentUniversity, universityChoices, majorChoices, essay, activities, email, currentState, phoneNumber;
            Date graduationDate;
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
                graduationDate = rs.getDate("graduationDate");
                accountStatus = rs.getBoolean("accountStatus");
                email = rs.getString("email");
                currentState = rs.getString("currentState");
                phoneNumber = rs.getString("phoneNumber");

                // make a StudentOld object out of the values
                aStudent = new StudentBean(firstName, lastName, userName, highSchool, currentUniversity, universityChoices, majorChoices, essay, activities, ACTScores, SATScores, GPA, graduationDate, accountStatus, email, currentState, phoneNumber, null);

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
        StudentBean aStudent = loadStudentsFromDB(query);
        return aStudent;
    }

    
}
