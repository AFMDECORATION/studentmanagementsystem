# Student Management System - Java Mini Project

A comprehensive Java desktop application for managing student records with a modern Swing GUI and file-based persistence. Designed for first-year Computer Science Engineering students to demonstrate core Java OOP concepts.

## Project Highlights

- **100% Pure Java** - No external libraries or databases required
- **10+ Java Concepts** - Classes, Objects, Inheritance, Abstract Class, Interface, Method Overloading, Method Overriding, Exception Handling, File Handling, Swing GUI
- **Modern Swing GUI** - Login screen, Dashboard, Forms, Tables, Reports
- **File Persistence** - All data stored in `students.txt`
- **Viva Ready** - Well-commented, beginner-friendly code

## Screenshots / Features

| Module | Description |
|--------|-------------|
| **Login Screen** | Secure authentication (admin/admin) |
| **Dashboard** | 6 color-coded module buttons |
| **Add Student** | Complete registration form with validation |
| **View Students** | Professional JTable with all records |
| **Search Student** | Find by roll number with detailed report |
| **Delete Student** | Safe deletion with confirmation |
| **Marks Entry** | Enter marks for 5 subjects |
| **Grade Report** | Statistics, ranking, grade distribution |

## Java Concepts Demonstrated

| Concept | Implementation |
|---------|---------------|
| **Classes & Objects** | `Person`, `Student`, `ReportGenerator` classes |
| **Constructors** | Multiple overloaded constructors in `Person` and `Student` |
| **Inheritance** | `Student extends Person` |
| **Abstract Class** | `Person` is declared `abstract` with abstract methods |
| **Interface** | `StudentManager` interface with CRUD operations |
| **Method Overloading** | `ReportGenerator.generateReport()` - 3 versions |
| **Method Overriding** | `Student.getRole()`, `Student.toString()`, `Student.equals()` |
| **Exception Handling** | try-catch for NumberFormatException, IOException |
| **File Handling** | Read/write `students.txt` using FileReader/FileWriter |
| **Swing GUI** | JFrame, JPanel, JTable, JOptionPane, CardLayout |

## Project Structure

```
StudentManagementSystem/
|-- Main.java                      # Application entry point
|-- Person.java                    # Abstract base class
|-- Student.java                   # Student class (extends Person)
|-- StudentManager.java            # Interface for operations
|-- StudentManagementGUI.java      # Main GUI application
|-- ReportGenerator.java           # Report generation utility
|-- students.txt                   # Data storage file
|
|-- docs/
|   |-- ProjectReport.pdf          # Comprehensive project report (22 pages)
|   |-- VivaQuestions.pdf          # 40 viva Q&A (11 pages)
|   |-- Flowchart.png              # Program flowchart
|   |-- ClassDiagram.png           # UML class diagram
```

## How to Run

### Prerequisites
- Java JDK 8 or higher installed
- Any Java-supported IDE (VS Code, IntelliJ, Eclipse) or command line

### Option 1: Command Line (Recommended)
```bash
# Navigate to project folder
cd StudentManagementSystem

# Compile all Java files
javac *.java

# Run the application
java Main
```

### Option 2: VS Code
1. Open the `StudentManagementSystem` folder in VS Code
2. Install the "Extension Pack for Java" extension
3. Open `Main.java` and click the "Run" button

### Option 3: GitHub Codespaces
1. Upload the project to a GitHub repository
2. Open the repository in GitHub Codespaces
3. Run `javac *.java && java Main` in the terminal

## Default Login Credentials
- **Username:** `admin`
- **Password:** `admin`

## File Descriptions

### Main.java
Entry point of the application. Sets the system Look and Feel and launches the GUI on the Event Dispatch Thread using `SwingUtilities.invokeLater()`.

### Person.java
Abstract class defining common attributes (ID, name, age, email, phone, address) for all person types. Declares abstract methods `getRole()` and `getFormattedDetails()` that subclasses must implement. Demonstrates encapsulation with private fields and public getters/setters.

### Student.java
Concrete class extending Person. Adds student-specific fields: rollNumber, course, semester, marks array, subjects array, totalMarks, percentage, and grade. Implements grade calculation logic and file serialization/deserialization.

### StudentManager.java
Interface defining the contract for all student management operations including CRUD operations, file I/O, and statistical queries. The GUI class implements this interface.

### StudentManagementGUI.java
Main application class (~600+ lines) implementing StudentManager. Contains all Swing GUI code including login screen, dashboard, forms, tables, and dialogs. Uses CardLayout for screen navigation and handles all user interactions.

### ReportGenerator.java
Utility class demonstrating method overloading with three versions of `generateReport()`. Provides comprehensive grade distribution analysis with text-based bar charts and student ranking.

### students.txt
Data storage file in pipe-delimited format. Sample data with 5 students is pre-loaded. The file is automatically created/updated by the application.

## Grade Calculation Logic

| Percentage | Grade |
|------------|-------|
| >= 90% | A |
| >= 80% | B |
| >= 70% | C |
| >= 60% | D |
| >= 50% | E |
| < 50% | F |

## Sample Output (Grade Report)
```
============================================================
            COMPREHENSIVE GRADE REPORT
============================================================

Total Students    : 5
Average Percentage: 77.80%

---------------- GRADE DISTRIBUTION ------------------------
Grade A:   1 students ( 20.0%) [####]
Grade B:   1 students ( 20.0%) [####]
Grade C:   1 students ( 20.0%) [####]
Grade D:   0 students (  0.0%) [                    ]
Grade E:   1 students ( 20.0%) [####]
Grade F:   1 students ( 20.0%) [####]
```

## Documentation Included

| Document | Pages | Description |
|----------|-------|-------------|
| **ProjectReport.pdf** | 22 | Complete report with all chapters |
| **VivaQuestions.pdf** | 11 | 40 Q&A organized by topic |
| **Flowchart.png** | - | Program execution flow |
| **ClassDiagram.png** | - | UML class relationships |

## Viva Questions Covered

The VivaQuestions.pdf contains 40 questions organized into 8 sections:
1. Basic Java Concepts (Questions 1-4)
2. OOP Concepts (Questions 5-13)
3. Exception Handling (Questions 14-16)
4. File Handling (Questions 17-19)
5. Java Swing GUI (Questions 20-24)
6. Project-Specific (Questions 25-30)
7. Code Explanation (Questions 31-34)
8. Advanced Questions (Questions 35-40)

## For Instructors / Evaluators

This project is suitable for assessing:
- Understanding of OOP principles
- Java syntax and language features
- GUI development skills
- File I/O operations
- Code organization and documentation
- Problem-solving approach

## License

This project is created for educational purposes and is free to use for academic coursework.

---

**Prepared for:** Java Programming Coursework  
**Language:** Java (Standard Edition)  
**GUI Framework:** Java Swing  
**Data Storage:** Text File (students.txt)  
**Target Audience:** First-Year Computer Science Engineering Students
