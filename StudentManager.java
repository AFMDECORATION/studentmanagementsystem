/**
 * StudentManager.java
 * This is an Interface that defines all operations for student management.
 * It demonstrates: Interface, Abstraction, Method declaration without implementation
 */

import java.util.List;

public interface StudentManager {

    // ============ CRUD OPERATIONS ============

    // Add a new student - returns true if successful
    boolean addStudent(Student student);

    // View all students - returns list of all students
    List<Student> getAllStudents();

    // Search student by roll number - returns Student or null
    Student searchByRollNumber(String rollNumber);

    // Search students by name - returns list of matching students
    // This is Method Overloading - multiple search methods with different parameters
    List<Student> searchByName(String name);

    // Delete student by roll number - returns true if deleted
    boolean deleteStudent(String rollNumber);

    // Update student marks
    boolean updateMarks(String rollNumber, double[] marks);

    // ============ FILE OPERATIONS ============

    // Save all students to file
    boolean saveToFile();

    // Load students from file
    boolean loadFromFile();

    // ============ STATISTICS ============

    // Get total number of students
    int getTotalStudentCount();

    // Get count of students by course
    int getStudentCountByCourse(String course);

    // Get average percentage of all students
    double getAveragePercentage();

    // Get students by grade
    List<Student> getStudentsByGrade(char grade);
}
