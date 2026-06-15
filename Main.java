/**
 * Main.java
 * Entry point for the Student Management System.
 * Demonstrates: Object creation, Method calls, Program flow
 */

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        // Set Look and Feel to system default for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // If system L&F not available, use default
            System.out.println("Note: Using default look and feel");
        }

        // Launch the application using Swing's Event Dispatch Thread
        // This is the recommended way to start Swing applications
        SwingUtilities.invokeLater(() -> {
            // Create instance of the GUI (Demonstrates Object creation)
            StudentManagementGUI gui = new StudentManagementGUI();

            // Make the window visible
            gui.setVisible(true);

            // Print welcome message to console
            System.out.println("========================================");
            System.out.println("   Student Management System Started   ");
            System.out.println("========================================");
            System.out.println("Login Credentials:");
            System.out.println("  Username: admin");
            System.out.println("  Password: admin");
            System.out.println("========================================");
        });
    }
}
