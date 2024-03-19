import java.sql.*;
/**
 *  The StudentDataBase program manipulates and manages a population of students
 * in a DataBase using CRUD methods (Create, Read Update Delete.
 * It allows for viewing all students in the database, adding a new student, updating a student's email
 * and removing a specific student from the database.
 * @author Firas EL-Ezzi 101239531
 * @version March 18 2024
*/

public class StudentDatabase {
    private Connection connection;

    // connection parameters
    // Replace the strings with your own server details

    // Replace compa3 with your database name
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/compa3";

    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    /**
     * Database constructor
     */
    public StudentDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates the table for the database to hold the student info
     */
    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (" + "student_id SERIAL PRIMARY KEY," + "first_name TEXT NOT NULL," + "last_name TEXT NOT NULL," + "email TEXT NOT NULL UNIQUE," + "enrollment_date DATE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns all students in the database with a specific format
     */
    public void getAllStudents() {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            //Loop through all the columns while printing them
            while (resultSet.next()) {
                System.out.println("Student ID: " + resultSet.getInt("student_id") + ", First Name: " + resultSet.getString("first_name") + ", Last Name: " + resultSet.getString("last_name") + ", Email: " + resultSet.getString("email") + ", Enrollment Date: " + resultSet.getDate("enrollment_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    /**
     * adds a specific student into the database
     * @param firstName
     * @param lastName
     * @param email
     * @param enrollmentDate
     */
    public void addStudent(String firstName, String lastName, String email, String enrollmentDate) {

        try {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setDate(4, Date.valueOf(enrollmentDate));
            pstmt.executeUpdate();
            System.out.println("----------------Adding Student: "+ firstName + lastName + email + enrollmentDate + "-----------------------\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates the email of a specified student by ID
     * @param studentId
     * @param newEmail
     */
    public void updateStudentEmail(int studentId, String newEmail) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE students SET email = ? WHERE student_id = ?");
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, studentId);
            pstmt.executeUpdate();
            System.out.println("----------------Updating Student Email: "+ studentId+ " " + newEmail +"-----------------------\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a student with a specified ID
     * @param studentId
     */
    public void deleteStudent(int studentId) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM students WHERE student_id = ?");
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
            System.out.println("----------------Removing Student: "+ studentId +"-----------------------\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main that runs and initiates all functions.
     * @param args
     */
    public static void main(String[] args) {
        // Create database, table and display current students.
        // Make sure to populate data using pgAdmin or add students using addStudent before displaying.
        StudentDatabase database = new StudentDatabase();
        database.createTable();
        database.getAllStudents();

        System.out.println("\nAdding a new student:");
        database.addStudent("Firas", "El-Ezzi", "firas@example.com", "2024-01-02");
        database.getAllStudents();

        int updateID = 1;
        System.out.println("\nUpdating email of student: " + updateID);
        database.updateStudentEmail(updateID, "NewEmail@example.com");
        database.getAllStudents();

        int studentID = 1 ;
        database.deleteStudent(studentID);
        database.getAllStudents();

        database.closeConnection();
    }
}
