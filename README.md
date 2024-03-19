# Firas El-Ezzi
# COMP3005 Assignment 3
# Database Interaction with PostgreSQL and Application Programming



## Overview

The StudentDataBase program manipulates and manages a population of students in a DataBase using CRUD methods (Create, Read Update Delete. It allows for viewing all students in the database, adding a new student, updating a student's email and removing a specific student from the database. 

## YouTube Preview
Copy paste the link into your browser if link doesnt work.
https://youtu.be/qjRD00LibSs

## Requirements before running

- Java JDK Installed
- PostgreSQL database server running
- PostgreSQL JDBC Driver (included in the project)
- pgAdmin

## Installation

1. Download or copy project from repository
2. Make sure PostgreSQL is running
3. Configure the PostgreSQL database connection in parameters of the contructor by changing the values in the attributes (DB_URL, USER, PASSWORD)
4. Test code by initiating commands in main as per seen in the YouTube video above

## Usage
1. Compile the Java program.
2. Populate initial data using pgAdmin
3. In main method, initiate the following commands:
   - View all students.
   - Add a new student.
   - Update student email.
   - Delete a student.

## Example

```java
public static void main(String[] args) {
        StudentDatabase database = new StudentDatabase();
        database.createTable();


        // Example usage of CRUD operations
        System.out.println("All students:");
        System.out.println("-----------------------------------------------------");
        database.getAllStudents();


        //System.out.println("\nAdding a new student:");
        //database.addStudent("Firas", "El-Ezzi", "firas@example.com", "2024-01-02");
        //database.getAllStudents();

        //int updateID = 13;
        //System.out.println("\nUpdating email of student: " + updateID);
        //database.updateStudentEmail(updateID, "firasnew.new@example.com");
        //database.getAllStudents();

        //int studentID =4 ;
        //System.out.println("\nDeleting student:" + studentID);
        //database.deleteStudent(studentID);
        //database.getAllStudents();

        database.closeConnection();
    }
```
