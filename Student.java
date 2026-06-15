/**
 * Student.java
 * This class represents a Student, extending the abstract Person class.
 * It demonstrates: Inheritance, Method Overriding, Constructors, Encapsulation
 */

import java.util.Arrays;

public class Student extends Person {
    // Student-specific fields
    private String rollNumber;
    private String course;
    private int semester;
    private double[] marks;      // Array to store marks for multiple subjects
    private String[] subjects;    // Array to store subject names
    private double totalMarks;
    private double percentage;
    private char grade;

    // Default Constructor
    public Student() {
        // Call parent default constructor using super()
        super();
        this.rollNumber = "";
        this.course = "";
        this.semester = 1;
        this.marks = new double[5];    // Default 5 subjects
        this.subjects = new String[]{"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5"};
        this.totalMarks = 0;
        this.percentage = 0;
        this.grade = 'F';
    }

    // Parameterized Constructor with basic info
    public Student(String id, String name, int age, String rollNumber, String course, int semester) {
        // Call parent parameterized constructor using super()
        super(id, name, age);
        this.rollNumber = rollNumber;
        this.course = course;
        this.semester = semester;
        this.marks = new double[5];
        this.subjects = new String[]{"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5"};
        this.totalMarks = 0;
        this.percentage = 0;
        this.grade = 'F';
    }

    // Parameterized Constructor with all details - Constructor Overloading
    public Student(String id, String name, int age, String email, String phone, String address,
                   String rollNumber, String course, int semester) {
        // Call parent full constructor using super()
        super(id, name, age, email, phone, address);
        this.rollNumber = rollNumber;
        this.course = course;
        this.semester = semester;
        this.marks = new double[5];
        this.subjects = new String[]{"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5"};
        this.totalMarks = 0;
        this.percentage = 0;
        this.grade = 'F';
    }

    // Constructor with marks - Constructor Overloading
    public Student(String id, String name, int age, String email, String phone, String address,
                   String rollNumber, String course, int semester, double[] marks, String[] subjects) {
        super(id, name, age, email, phone, address);
        this.rollNumber = rollNumber;
        this.course = course;
        this.semester = semester;
        this.marks = marks;
        this.subjects = subjects;
        calculateGrade();  // Auto-calculate grade when marks are provided
    }

    // Getter methods for Student-specific fields
    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double[] getMarks() {
        return marks;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public double getPercentage() {
        return percentage;
    }

    public char getGrade() {
        return grade;
    }

    // Method to set marks for a specific subject
    public void setSubjectMark(int index, double mark) {
        if (index >= 0 && index < marks.length) {
            marks[index] = mark;
        }
    }

    // Method to set subject name
    public void setSubjectName(int index, String name) {
        if (index >= 0 && index < subjects.length) {
            subjects[index] = name;
        }
    }

    // Method to calculate grade based on marks - demonstrates business logic
    public void calculateGrade() {
        totalMarks = 0;
        int validSubjects = 0;
        
        for (double mark : marks) {
            if (mark >= 0) {
                totalMarks += mark;
                validSubjects++;
            }
        }

        if (validSubjects > 0) {
            percentage = totalMarks / validSubjects;
        } else {
            percentage = 0;
        }

        // Grade calculation based on percentage
        if (percentage >= 90) {
            grade = 'A';
        } else if (percentage >= 80) {
            grade = 'B';
        } else if (percentage >= 70) {
            grade = 'C';
        } else if (percentage >= 60) {
            grade = 'D';
        } else if (percentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }
    }

    // Abstract method implementation - Method Overriding (from Person)
    @Override
    public String getRole() {
        return "Student";
    }

    // Abstract method implementation - Method Overriding (from Person)
    @Override
    public String getFormattedDetails() {
        calculateGrade(); // Ensure grade is up to date
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("          STUDENT DETAILS              \n");
        sb.append("========================================\n");
        sb.append("Roll Number: ").append(rollNumber).append("\n");
        sb.append("Name: ").append(getName()).append("\n");
        sb.append("Age: ").append(getAge()).append("\n");
        sb.append("Course: ").append(course).append("\n");
        sb.append("Semester: ").append(semester).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("Phone: ").append(getPhone()).append("\n");
        sb.append("Address: ").append(getAddress()).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("                MARKS                   \n");
        sb.append("----------------------------------------\n");
        for (int i = 0; i < subjects.length; i++) {
            sb.append(String.format("%-15s: %.2f\n", subjects[i], marks[i]));
        }
        sb.append("----------------------------------------\n");
        sb.append(String.format("Total Marks    : %.2f\n", totalMarks));
        sb.append(String.format("Percentage     : %.2f%%\n", percentage));
        sb.append(String.format("Grade          : %c\n", grade));
        sb.append("========================================");
        return sb.toString();
    }

    // Override equals() method - Method Overriding from Object class
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return rollNumber.equals(student.rollNumber);
    }

    // Override toString() - Method Overriding from Person
    @Override
    public String toString() {
        return rollNumber + " - " + getName() + " (" + course + ", Sem " + semester + ")";
    }

    // Convert Student to file storage format (CSV)
    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append("STUDENT").append("|");
        sb.append(getId()).append("|");
        sb.append(getName()).append("|");
        sb.append(getAge()).append("|");
        sb.append(getEmail()).append("|");
        sb.append(getPhone()).append("|");
        sb.append(getAddress()).append("|");
        sb.append(rollNumber).append("|");
        sb.append(course).append("|");
        sb.append(semester).append("|");
        // Append marks
        for (int i = 0; i < marks.length; i++) {
            sb.append(marks[i]);
            if (i < marks.length - 1) sb.append(",");
        }
        sb.append("|");
        // Append subject names
        for (int i = 0; i < subjects.length; i++) {
            sb.append(subjects[i]);
            if (i < subjects.length - 1) sb.append(",");
        }
        return sb.toString();
    }

    // Static method to create Student from file string
    public static Student fromFileString(String line) throws IllegalArgumentException {
        String[] parts = line.split("\\|");
        if (parts.length < 11) {
            throw new IllegalArgumentException("Invalid data format");
        }

        Student student = new Student();
        student.setId(parts[1]);
        student.setName(parts[2]);
        try {
            student.setAge(Integer.parseInt(parts[3]));
        } catch (NumberFormatException e) {
            student.setAge(0);
        }
        student.setEmail(parts[4]);
        student.setPhone(parts[5]);
        student.setAddress(parts[6]);
        student.setRollNumber(parts[7]);
        student.setCourse(parts[8]);
        try {
            student.setSemester(Integer.parseInt(parts[9]));
        } catch (NumberFormatException e) {
            student.setSemester(1);
        }

        // Parse marks
        String[] marksStr = parts[10].split(",");
        double[] marks = new double[marksStr.length];
        for (int i = 0; i < marksStr.length; i++) {
            try {
                marks[i] = Double.parseDouble(marksStr[i]);
            } catch (NumberFormatException e) {
                marks[i] = 0;
            }
        }
        student.marks = marks;

        // Parse subjects
        if (parts.length > 11) {
            student.subjects = parts[11].split(",");
        }

        student.calculateGrade();
        return student;
    }
}
