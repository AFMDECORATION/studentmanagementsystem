/**
 * StudentManagementGUI.java
 * Main GUI class implementing StudentManager interface.
 * Demonstrates: Interface Implementation, Swing GUI, File Handling, Exception Handling
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementGUI extends JFrame implements StudentManager {

    // ============ DATA STORAGE ============
    private ArrayList<Student> students;
    private static final String DATA_FILE = "students.txt";

    // ============ GUI COMPONENTS - LOGIN PANEL ============
    private JPanel loginPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    // ============ GUI COMPONENTS - DASHBOARD ============
    private JPanel dashboardPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    // ============ GUI COMPONENTS - ADD STUDENT ============
    private JTextField txtId, txtName, txtAge, txtEmail, txtPhone, txtAddress;
    private JTextField txtRollNumber, txtCourse, txtSemester;

    // ============ GUI COMPONENTS - VIEW STUDENTS ============
    private JTable studentTable;
    private DefaultTableModel tableModel;

    // ============ GUI COMPONENTS - SEARCH ============
    private JTextField txtSearchRoll;
    private JTextArea txtSearchResult;

    // ============ GUI COMPONENTS - DELETE ============
    private JTextField txtDeleteRoll;

    // ============ GUI COMPONENTS - MARKS ENTRY ============
    private JTextField txtMarksRoll;
    private JTextField[] txtSubjectMarks;
    private JLabel lblMarksName;

    // ============ GUI COMPONENTS - GRADE REPORT ============
    private JTextArea txtGradeReport;

    // ============ COLORS AND STYLING ============
    private Color primaryColor = new Color(51, 102, 153);      // Steel Blue
    private Color secondaryColor = new Color(240, 248, 255);   // Alice Blue
    private Color accentColor = new Color(70, 130, 180);       // Steel Blue lighter
    private Color successColor = new Color(46, 139, 87);       // Sea Green
    private Color dangerColor = new Color(178, 34, 34);        // Fire Brick

    // ============ CONSTRUCTOR ============
    public StudentManagementGUI() {
        // Initialize data
        students = new ArrayList<>();

        // Load existing data
        loadFromFile();

        // Setup main frame
        setTitle("Student Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set application icon (using default if custom not available)
        setIconImage(Toolkit.getDefaultToolkit().getImage(""));

        // Initialize CardLayout for panel switching
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Create all panels
        createLoginPanel();
        createDashboardPanel();
        createAddStudentPanel();
        createViewStudentsPanel();
        createSearchPanel();
        createDeletePanel();
        createMarksEntryPanel();
        createGradeReportPanel();

        // Add panels to card layout
        contentPanel.add(loginPanel, "LOGIN");
        contentPanel.add(dashboardPanel, "DASHBOARD");

        // Add to frame
        add(contentPanel);

        // Show login first
        cardLayout.show(contentPanel, "LOGIN");
    }

    // ============ LOGIN PANEL ============
    private void createLoginPanel() {
        loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(secondaryColor);

        // Center panel for login form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(secondaryColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));

        // Title
        JLabel lblTitle = new JLabel("Student Management System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(primaryColor);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Login to Continue");
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSubtitle.setForeground(Color.DARK_GRAY);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        formPanel.setMaximumSize(new Dimension(400, 250));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 14));
        txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setText("admin");

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblUser, gbc);
        gbc.gridx = 1;
        formPanel.add(txtUsername, gbc);

        // Password
        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 14));
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setText("admin");
        txtPassword.setEchoChar('*');

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblPass, gbc);
        gbc.gridx = 1;
        formPanel.add(txtPassword, gbc);

        // Login button
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(primaryColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setPreferredSize(new Dimension(200, 40));

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        formPanel.add(btnLogin, gbc);

        // Add action listener for login
        btnLogin.addActionListener(e -> performLogin());
        txtPassword.addActionListener(e -> performLogin());

        // Assemble center panel
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(lblSubtitle);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(formPanel);

        // Footer
        JLabel lblFooter = new JLabel("Default: admin / admin");
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        lblFooter.setForeground(Color.GRAY);
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(lblFooter);

        loginPanel.add(centerPanel, BorderLayout.CENTER);
    }

    // Login validation
    private void performLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        // Simple authentication (for demo purposes)
        if (username.equals("admin") && password.equals("admin")) {
            cardLayout.show(contentPanel, "DASHBOARD");
            setTitle("Student Management System - Dashboard");
        } else {
            showError("Invalid username or password!\nTry: admin / admin");
        }
    }

    // ============ DASHBOARD PANEL ============
    private void createDashboardPanel() {
        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(secondaryColor);

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setPreferredSize(new Dimension(1000, 80));
        headerPanel.setLayout(new BorderLayout());

        JLabel lblHeader = new JLabel("  Student Management Dashboard", SwingConstants.LEFT);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        lblHeader.setForeground(Color.WHITE);
        headerPanel.add(lblHeader, BorderLayout.WEST);

        // Logout button
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogout.setBackground(dangerColor);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                saveToFile();
                cardLayout.show(contentPanel, "LOGIN");
                txtPassword.setText("");
            }
        });
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setOpaque(false);
        logoutPanel.add(btnLogout);
        headerPanel.add(logoutPanel, BorderLayout.EAST);

        dashboardPanel.add(headerPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        buttonPanel.setBackground(secondaryColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));

        // Create menu buttons
        buttonPanel.add(createMenuButton("Add Student", "\u2795", successColor, e -> showAddStudent()));
        buttonPanel.add(createMenuButton("View Students", "\ud83d\udccb", accentColor, e -> showViewStudents()));
        buttonPanel.add(createMenuButton("Search Student", "\ud83d\udd0d", accentColor, e -> showSearchStudent()));
        buttonPanel.add(createMenuButton("Delete Student", "\u2796", dangerColor, e -> showDeleteStudent()));
        buttonPanel.add(createMenuButton("Enter Marks", "\ud83d\udcdd", new Color(255, 140, 0), e -> showMarksEntry()));
        buttonPanel.add(createMenuButton("Grade Report", "\ud83d\udcca", new Color(106, 90, 205), e -> showGradeReport()));

        dashboardPanel.add(buttonPanel, BorderLayout.CENTER);

        // Footer with stats
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        footerPanel.setBackground(primaryColor);
        footerPanel.setPreferredSize(new Dimension(1000, 50));

        JLabel lblTotal = new JLabel("Total Students: " + students.size());
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotal.setForeground(Color.WHITE);

        footerPanel.add(lblTotal);
        dashboardPanel.add(footerPanel, BorderLayout.SOUTH);
    }

    // Helper to create menu buttons
    private JButton createMenuButton(String text, String emoji, Color color, ActionListener action) {
        JButton btn = new JButton("<html><center>" + emoji + "<br><br>" + text + "</center></html>");
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(250, 150));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        btn.addActionListener(action);
        return btn;
    }

    // ============ ADD STUDENT PANEL ============
    private void createAddStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(secondaryColor);

        // Header
        JPanel header = createPanelHeader("Add New Student", e -> cardLayout.show(contentPanel, "DASHBOARD"));
        panel.add(header, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor),
            BorderFactory.createEmptyBorder(25, 40, 25, 40)
        ));

        // Initialize fields
        txtId = new JTextField(20);
        txtName = new JTextField(20);
        txtAge = new JTextField(20);
        txtEmail = new JTextField(20);
        txtPhone = new JTextField(20);
        txtAddress = new JTextField(20);
        txtRollNumber = new JTextField(20);
        txtCourse = new JTextField(20);
        txtSemester = new JTextField(20);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Add form fields
        addFormField(formPanel, "Student ID:", txtId, labelFont, fieldFont);
        addFormField(formPanel, "Full Name:", txtName, labelFont, fieldFont);
        addFormField(formPanel, "Age:", txtAge, labelFont, fieldFont);
        addFormField(formPanel, "Email:", txtEmail, labelFont, fieldFont);
        addFormField(formPanel, "Phone:", txtPhone, labelFont, fieldFont);
        addFormField(formPanel, "Address:", txtAddress, labelFont, fieldFont);
        addFormField(formPanel, "Roll Number:", txtRollNumber, labelFont, fieldFont);
        addFormField(formPanel, "Course:", txtCourse, labelFont, fieldFont);
        addFormField(formPanel, "Semester:", txtSemester, labelFont, fieldFont);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(secondaryColor);

        JButton btnSave = new JButton("Save Student");
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setBackground(successColor);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> saveNewStudent());

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.setBackground(Color.GRAY);
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.addActionListener(e -> clearAddForm());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnClear);

        // Center everything
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(secondaryColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(centerPanel, BorderLayout.CENTER);
        contentPanel.add(panel, "ADD_STUDENT");
    }

    private void addFormField(JPanel panel, String label, JTextField field, Font labelFont, Font fieldFont) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(labelFont);
        field.setFont(fieldFont);
        panel.add(lbl);
        panel.add(field);
    }

    private void saveNewStudent() {
        try {
            // Validate inputs
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String ageStr = txtAge.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();
            String rollNumber = txtRollNumber.getText().trim();
            String course = txtCourse.getText().trim();
            String semStr = txtSemester.getText().trim();

            // Validation checks
            if (id.isEmpty() || name.isEmpty() || rollNumber.isEmpty()) {
                showWarning("Student ID, Name, and Roll Number are required!");
                return;
            }

            // Check if roll number already exists
            if (searchByRollNumber(rollNumber) != null) {
                showWarning("A student with this Roll Number already exists!");
                return;
            }

            int age = 0;
            try {
                age = Integer.parseInt(ageStr);
                if (age < 0 || age > 120) {
                    showWarning("Please enter a valid age (0-120)!");
                    return;
                }
            } catch (NumberFormatException ex) {
                showWarning("Age must be a valid number!");
                return;
            }

            int semester = 1;
            try {
                semester = Integer.parseInt(semStr);
                if (semester < 1 || semester > 10) {
                    showWarning("Semester must be between 1 and 10!");
                    return;
                }
            } catch (NumberFormatException ex) {
                semester = 1;
            }

            // Create and add student
            Student student = new Student(id, name, age, email, phone, address, rollNumber, course, semester);
            if (addStudent(student)) {
                showInfo("Student added successfully!\n\nName: " + name + "\nRoll No: " + rollNumber);
                clearAddForm();
            } else {
                showError("Failed to add student!");
            }

        } catch (Exception ex) {
            showError("Error: " + ex.getMessage());
        }
    }

    private void clearAddForm() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtRollNumber.setText("");
        txtCourse.setText("");
        txtSemester.setText("");
    }

    // ============ VIEW STUDENTS PANEL ============
    private void createViewStudentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(secondaryColor);

        JPanel header = createPanelHeader("View All Students", e -> cardLayout.show(contentPanel, "DASHBOARD"));
        panel.add(header, BorderLayout.NORTH);

        // Table
        String[] columns = {"S.No", "Roll No", "Name", "Age", "Course", "Semester", "Percentage", "Grade"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Read-only table
            }
        };
        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 13));
        studentTable.setRowHeight(25);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        studentTable.getTableHeader().setBackground(primaryColor);
        studentTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(primaryColor));

        panel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(panel, "VIEW_STUDENTS");
    }

    // ============ SEARCH PANEL ============
    private void createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(secondaryColor);

        JPanel header = createPanelHeader("Search Student", e -> cardLayout.show(contentPanel, "DASHBOARD"));
        panel.add(header, BorderLayout.NORTH);

        // Search form
        JPanel searchForm = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        searchForm.setBackground(secondaryColor);

        JLabel lblSearch = new JLabel("Enter Roll Number:");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 14));
        txtSearchRoll = new JTextField(20);
        txtSearchRoll.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
        btnSearch.setBackground(accentColor);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(e -> performSearch());

        searchForm.add(lblSearch);
        searchForm.add(txtSearchRoll);
        searchForm.add(btnSearch);

        // Result area
        txtSearchResult = new JTextArea(15, 60);
        txtSearchResult.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtSearchResult.setEditable(false);
        txtSearchResult.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane resultScroll = new JScrollPane(txtSearchResult);
        resultScroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(primaryColor), "Search Result",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14), primaryColor
        ));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(secondaryColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        centerPanel.add(searchForm, BorderLayout.NORTH);
        centerPanel.add(resultScroll, BorderLayout.CENTER);

        panel.add(centerPanel, BorderLayout.CENTER);
        contentPanel.add(panel, "SEARCH");
    }

    private void performSearch() {
        String rollNo = txtSearchRoll.getText().trim();
        if (rollNo.isEmpty()) {
            showWarning("Please enter a roll number!");
            return;
        }

        Student student = searchByRollNumber(rollNo);
        if (student != null) {
            txtSearchResult.setText(student.getFormattedDetails());
        } else {
            txtSearchResult.setText("No student found with Roll Number: " + rollNo);
        }
    }

    // ============ DELETE PANEL ============
    private void createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(secondaryColor);

        JPanel header = createPanelHeader("Delete Student", e -> cardLayout.show(contentPanel, "DASHBOARD"));
        panel.add(header, BorderLayout.NORTH);

        // Delete form
        JPanel deleteForm = new JPanel();
        deleteForm.setLayout(new BoxLayout(deleteForm, BoxLayout.Y_AXIS));
        deleteForm.setBackground(secondaryColor);
        deleteForm.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        JLabel lblDelete = new JLabel("Enter Roll Number to Delete:");
        lblDelete.setFont(new Font("Arial", Font.BOLD, 16));
        lblDelete.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtDeleteRoll = new JTextField(20);
        txtDeleteRoll.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDeleteRoll.setMaximumSize(new Dimension(400, 35));
        txtDeleteRoll.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnDelete = new JButton("Delete Student");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 16));
        btnDelete.setBackground(dangerColor);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setMaximumSize(new Dimension(200, 40));
        btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelete.addActionListener(e -> performDelete());

        // Warning label
        JLabel lblWarning = new JLabel("Warning: This action cannot be undone!");
        lblWarning.setFont(new Font("Arial", Font.ITALIC, 12));
        lblWarning.setForeground(dangerColor);
        lblWarning.setAlignmentX(Component.CENTER_ALIGNMENT);

        deleteForm.add(lblDelete);
        deleteForm.add(Box.createVerticalStrut(15));
        deleteForm.add(txtDeleteRoll);
        deleteForm.add(Box.createVerticalStrut(20));
        deleteForm.add(btnDelete);
        deleteForm.add(Box.createVerticalStrut(10));
        deleteForm.add(lblWarning);

        panel.add(deleteForm, BorderLayout.CENTER);
        contentPanel.add(panel, "DELETE");
    }

    private void performDelete() {
        String rollNo = txtDeleteRoll.getText().trim();
        if (rollNo.isEmpty()) {
            showWarning("Please enter a roll number!");
            return;
        }

        Student student = searchByRollNumber(rollNo);
        if (student == null) {
            showWarning("No student found with Roll Number: " + rollNo);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this student?\n\n" +
            "Name: " + student.getName() + "\nRoll No: " + student.getRollNumber(),
            "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            if (deleteStudent(rollNo)) {
                showInfo("Student deleted successfully!");
                txtDeleteRoll.setText("");
            } else {
                showError("Failed to delete student!");
            }
        }
    }

    // ============ MARKS ENTRY PANEL ============
    private void createMarksEntryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(secondaryColor);

        JPanel header = createPanelHeader("Enter Marks", e -> cardLayout.show(contentPanel, "DASHBOARD"));
        panel.add(header, BorderLayout.NORTH);

        // Top panel - Roll number input
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        topPanel.setBackground(secondaryColor);

        JLabel lblRoll = new JLabel("Roll Number:");
        lblRoll.setFont(new Font("Arial", Font.BOLD, 14));
        txtMarksRoll = new JTextField(15);
        txtMarksRoll.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnFind = new JButton("Find Student");
        btnFind.setFont(new Font("Arial", Font.BOLD, 14));
        btnFind.setBackground(accentColor);
        btnFind.setForeground(Color.WHITE);
        btnFind.setFocusPainted(false);
        btnFind.addActionListener(e -> findStudentForMarks());

        lblMarksName = new JLabel(" ");
        lblMarksName.setFont(new Font("Arial", Font.BOLD, 16));
        lblMarksName.setForeground(primaryColor);

        topPanel.add(lblRoll);
        topPanel.add(txtMarksRoll);
        topPanel.add(btnFind);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(lblMarksName);

        // Marks entry form
        JPanel marksPanel = new JPanel(new GridLayout(5, 2, 15, 10));
        marksPanel.setBackground(Color.WHITE);
        marksPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor),
            BorderFactory.createEmptyBorder(25, 50, 25, 50)
        ));

        String[] subjectNames = {"Mathematics", "Physics", "Chemistry", "Programming", "English"};
        txtSubjectMarks = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            JLabel lblSubj = new JLabel(subjectNames[i] + ":");
            lblSubj.setFont(new Font("Arial", Font.BOLD, 14));
            txtSubjectMarks[i] = new JTextField(10);
            txtSubjectMarks[i].setFont(new Font("Arial", Font.PLAIN, 14));
            txtSubjectMarks[i].setEnabled(false);
            marksPanel.add(lblSubj);
            marksPanel.add(txtSubjectMarks[i]);
        }

        // Button panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setBackground(secondaryColor);

        JButton btnSaveMarks = new JButton("Save Marks");
        btnSaveMarks.setFont(new Font("Arial", Font.BOLD, 14));
        btnSaveMarks.setBackground(successColor);
        btnSaveMarks.setForeground(Color.WHITE);
        btnSaveMarks.setFocusPainted(false);
        btnSaveMarks.setEnabled(false);
        btnSaveMarks.addActionListener(e -> saveMarks());

        JButton btnClearMarks = new JButton("Clear");
        btnClearMarks.setFont(new Font("Arial", Font.BOLD, 14));
        btnClearMarks.setBackground(Color.GRAY);
        btnClearMarks.setForeground(Color.WHITE);
        btnClearMarks.addActionListener(e -> {
            for (JTextField txt : txtSubjectMarks) {
                txt.setText("");
            }
        });

        btnPanel.add(btnSaveMarks);
        btnPanel.add(btnClearMarks);

        // Store reference to save button for enabling/disabling
        marksPanel.putClientProperty("saveBtn", btnSaveMarks);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(secondaryColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 100));
        centerPanel.add(marksPanel, BorderLayout.CENTER);
        centerPanel.add(btnPanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        contentPanel.add(panel, "MARKS");
    }

    private void findStudentForMarks() {
        String rollNo = txtMarksRoll.getText().trim();
        if (rollNo.isEmpty()) {
            showWarning("Please enter a roll number!");
            return;
        }

        Student student = searchByRollNumber(rollNo);
        if (student != null) {
            lblMarksName.setText("Student: " + student.getName());
            for (JTextField txt : txtSubjectMarks) {
                txt.setEnabled(true);
            }
            // Enable save button
            Component[] components = ((JPanel)((BorderLayout)((JPanel)contentPanel.getComponent(6))
                .getLayout()).getLayoutComponent(BorderLayout.CENTER)).getComponents();
        } else {
            lblMarksName.setText("Student not found!");
            for (JTextField txt : txtSubjectMarks) {
                txt.setEnabled(false);
            }
            showWarning("No student found with Roll Number: " + rollNo);
        }
    }

    private void saveMarks() {
        String rollNo = txtMarksRoll.getText().trim();
        double[] marks = new double[5];

        try {
            for (int i = 0; i < 5; i++) {
                String val = txtSubjectMarks[i].getText().trim();
                if (val.isEmpty()) {
                    showWarning("Please enter marks for all subjects!");
                    return;
                }
                marks[i] = Double.parseDouble(val);
                if (marks[i] < 0 || marks[i] > 100) {
                    showWarning("Marks must be between 0 and 100!");
                    return;
                }
            }

            if (updateMarks(rollNo, marks)) {
                Student student = searchByRollNumber(rollNo);
                student.calculateGrade();
                saveToFile();

                showInfo("Marks saved successfully!\n\n" +
                    "Total: " + student.getTotalMarks() + "\n" +
                    "Percentage: " + String.format("%.2f", student.getPercentage()) + "%\n" +
                    "Grade: " + student.getGrade());

                txtMarksRoll.setText("");
                lblMarksName.setText(" ");
                for (JTextField txt : txtSubjectMarks) {
                    txt.setText("");
                    txt.setEnabled(false);
                }
            }
        } catch (NumberFormatException ex) {
            showError("Please enter valid numeric marks!");
        }
    }

    // ============ GRADE REPORT PANEL ============
    private void createGradeReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(secondaryColor);

        JPanel header = createPanelHeader("Grade Report", e -> cardLayout.show(contentPanel, "DASHBOARD"));
        panel.add(header, BorderLayout.NORTH);

        txtGradeReport = new JTextArea();
        txtGradeReport.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtGradeReport.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtGradeReport);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(panel, "GRADE_REPORT");
    }

    // ============ HELPER METHODS ============
    private JPanel createPanelHeader(String title, ActionListener backAction) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(primaryColor);
        header.setPreferredSize(new Dimension(1000, 60));

        JButton btnBack = new JButton(" Back ");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(primaryColor);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnBack.addActionListener(backAction);

        JLabel lblTitle = new JLabel("  " + title, SwingConstants.LEFT);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        header.add(btnBack, BorderLayout.WEST);
        header.add(lblTitle, BorderLayout.CENTER);

        return header;
    }

    private void showAddStudent() {
        cardLayout.show(contentPanel, "ADD_STUDENT");
    }

    private void showViewStudents() {
        refreshStudentTable();
        cardLayout.show(contentPanel, "VIEW_STUDENTS");
    }

    private void showSearchStudent() {
        txtSearchResult.setText("");
        txtSearchRoll.setText("");
        cardLayout.show(contentPanel, "SEARCH");
    }

    private void showDeleteStudent() {
        txtDeleteRoll.setText("");
        cardLayout.show(contentPanel, "DELETE");
    }

    private void showMarksEntry() {
        txtMarksRoll.setText("");
        lblMarksName.setText(" ");
        for (JTextField txt : txtSubjectMarks) {
            txt.setText("");
            txt.setEnabled(false);
        }
        cardLayout.show(contentPanel, "MARKS");
    }

    private void showGradeReport() {
        generateGradeReport();
        cardLayout.show(contentPanel, "GRADE_REPORT");
    }

    private void refreshStudentTable() {
        tableModel.setRowCount(0);
        List<Student> allStudents = getAllStudents();
        int sno = 1;
        for (Student s : allStudents) {
            s.calculateGrade();
            tableModel.addRow(new Object[]{
                sno++,
                s.getRollNumber(),
                s.getName(),
                s.getAge(),
                s.getCourse(),
                s.getSemester(),
                String.format("%.2f%%", s.getPercentage()),
                s.getGrade()
            });
        }
    }

    private void generateGradeReport() {
        ReportGenerator generator = new ReportGenerator();
        txtGradeReport.setText(generator.generateFullReport(students));
    }

    // ============ DIALOG HELPERS ============
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // ============ INTERFACE IMPLEMENTATION ============

    @Override
    public boolean addStudent(Student student) {
        if (student == null) return false;
        students.add(student);
        saveToFile();
        return true;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return copy to protect encapsulation
    }

    @Override
    public Student searchByRollNumber(String rollNumber) {
        if (rollNumber == null || rollNumber.trim().isEmpty()) return null;
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber.trim())) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) return result;
        String searchLower = name.toLowerCase();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(searchLower)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public boolean deleteStudent(String rollNumber) {
        Student student = searchByRollNumber(rollNumber);
        if (student != null) {
            students.remove(student);
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMarks(String rollNumber, double[] marks) {
        Student student = searchByRollNumber(rollNumber);
        if (student != null && marks != null) {
            double[] studentMarks = student.getMarks();
            int len = Math.min(marks.length, studentMarks.length);
            for (int i = 0; i < len; i++) {
                student.setSubjectMark(i, marks[i]);
            }
            student.calculateGrade();
            return true;
        }
        return false;
    }

    @Override
    public boolean saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            writer.println("# Student Management System Data File");
            writer.println("# Format: TYPE|ID|Name|Age|Email|Phone|Address|RollNo|Course|Semester|Marks|Subjects");
            writer.println("# Total Students: " + students.size());
            writer.println("========================================");

            for (Student s : students) {
                writer.println(s.toFileString());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean loadFromFile() {
        students.clear();
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return true; // No data file yet, start fresh
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("=")) {
                    continue; // Skip comments and separators
                }
                if (line.startsWith("STUDENT")) {
                    try {
                        Student student = Student.fromFileString(line);
                        students.add(student);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error parsing line: " + e.getMessage());
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return false;
        }
    }

    @Override
    public int getTotalStudentCount() {
        return students.size();
    }

    @Override
    public int getStudentCountByCourse(String course) {
        int count = 0;
        for (Student s : students) {
            if (s.getCourse().equalsIgnoreCase(course)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public double getAveragePercentage() {
        if (students.isEmpty()) return 0;
        double total = 0;
        for (Student s : students) {
            total += s.getPercentage();
        }
        return total / students.size();
    }

    @Override
    public List<Student> getStudentsByGrade(char grade) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getGrade() == grade) {
                result.add(s);
            }
        }
        return result;
    }
}
