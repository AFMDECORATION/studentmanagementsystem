/**
 * ReportGenerator.java
 * This class generates various types of reports for the Student Management System.
 * It demonstrates: Method Overloading, String manipulation, Formatting
 */

import java.util.List;

public class ReportGenerator {

    // ============ METHOD OVERLOADING DEMONSTRATIONS ============

    /**
     * Generate a simple report - 1 parameter version
     * This is the simplest form of report
     */
    public String generateReport(List<Student> students) {
        StringBuilder report = new StringBuilder();
        report.append("============================================================\n");
        report.append("              STUDENT MANAGEMENT REPORT                      \n");
        report.append("============================================================\n\n");
        report.append("Total Students: ").append(students.size()).append("\n\n");

        if (students.isEmpty()) {
            report.append("No students found in the system.\n");
            return report.toString();
        }

        report.append(String.format("%-5s %-12s %-20s %-10s %-8s\n",
            "S.No", "Roll No", "Name", "Course", "Grade"));
        report.append("------------------------------------------------------------\n");

        int sno = 1;
        for (Student s : students) {
            report.append(String.format("%-5d %-12s %-20s %-10s %-8c\n",
                sno++,
                s.getRollNumber(),
                truncateString(s.getName(), 20),
                truncateString(s.getCourse(), 10),
                s.getGrade()));
        }

        report.append("\n============================================================\n");
        return report.toString();
    }

    /**
     * Generate report with title - 2 parameters version (Method Overloading)
     * Allows custom report title
     */
    public String generateReport(List<Student> students, String title) {
        StringBuilder report = new StringBuilder();
        report.append("============================================================\n");
        report.append("     ").append(centerString(title.toUpperCase(), 47)).append("\n");
        report.append("============================================================\n\n");
        report.append("Total Students: ").append(students.size()).append("\n\n");

        if (students.isEmpty()) {
            report.append("No students found.\n");
            return report.toString();
        }

        report.append(String.format("%-5s %-12s %-20s %-10s %-8s %-10s\n",
            "S.No", "Roll No", "Name", "Course", "Grade", "Percentage"));
        report.append("------------------------------------------------------------\n");

        int sno = 1;
        for (Student s : students) {
            report.append(String.format("%-5d %-12s %-20s %-10s %-8c %-10.2f%%\n",
                sno++,
                s.getRollNumber(),
                truncateString(s.getName(), 20),
                truncateString(s.getCourse(), 10),
                s.getGrade(),
                s.getPercentage()));
        }

        report.append("\n============================================================\n");
        return report.toString();
    }

    /**
     * Generate report with title and course filter - 3 parameters version (Method Overloading)
     * Allows filtering by course
     */
    public String generateReport(List<Student> students, String title, String courseFilter) {
        StringBuilder report = new StringBuilder();
        report.append("============================================================\n");
        report.append("     ").append(centerString(title.toUpperCase(), 47)).append("\n");
        report.append("           Course Filter: ").append(courseFilter).append("\n");
        report.append("============================================================\n\n");

        // Filter students by course
        int count = 0;
        for (Student s : students) {
            if (s.getCourse().equalsIgnoreCase(courseFilter)) {
                count++;
            }
        }

        report.append("Matching Students: ").append(count).append("\n\n");

        if (count == 0) {
            report.append("No students found for course: ").append(courseFilter).append("\n");
            report.append("\n============================================================\n");
            return report.toString();
        }

        report.append(String.format("%-5s %-12s %-20s %-8s %-10s %-10s\n",
            "S.No", "Roll No", "Name", "Grade", "Percentage", "Status"));
        report.append("------------------------------------------------------------\n");

        int sno = 1;
        double totalPercentage = 0;
        for (Student s : students) {
            if (s.getCourse().equalsIgnoreCase(courseFilter)) {
                String status = s.getGrade() == 'F' ? "Failed" : "Passed";
                report.append(String.format("%-5d %-12s %-20s %-8c %-10.2f%% %-10s\n",
                    sno++,
                    s.getRollNumber(),
                    truncateString(s.getName(), 20),
                    s.getGrade(),
                    s.getPercentage(),
                    status));
                totalPercentage += s.getPercentage();
            }
        }

        report.append("\n------------------------------------------------------------\n");
        report.append(String.format("Average Percentage: %.2f%%\n", totalPercentage / count));
        report.append("============================================================\n");
        return report.toString();
    }

    /**
     * Generate a comprehensive full report with statistics
     */
    public String generateFullReport(List<Student> students) {
        if (students.isEmpty()) {
            return "No students in the system.\nPlease add students first.";
        }

        StringBuilder report = new StringBuilder();
        report.append("============================================================\n");
        report.append("            COMPREHENSIVE GRADE REPORT                       \n");
        report.append("============================================================\n\n");

        // Overall Statistics
        report.append("---------------- OVERALL STATISTICS ------------------------\n");
        report.append("Total Students    : ").append(students.size()).append("\n");

        double totalPercentage = 0;
        int[] gradeCount = new int[6]; // A, B, C, D, E, F
        char[] grades = {'A', 'B', 'C', 'D', 'E', 'F'};

        for (Student s : students) {
            totalPercentage += s.getPercentage();
            for (int i = 0; i < grades.length; i++) {
                if (s.getGrade() == grades[i]) {
                    gradeCount[i]++;
                    break;
                }
            }
        }

        report.append(String.format("Average Percentage: %.2f%%\n", totalPercentage / students.size()));
        report.append("\n---------------- GRADE DISTRIBUTION ------------------------\n");

        for (int i = 0; i < grades.length; i++) {
            double percentage = (gradeCount[i] * 100.0) / students.size();
            report.append(String.format("Grade %c: %3d students (%5.1f%%) %s\n",
                grades[i], gradeCount[i], percentage, drawBar((int) percentage)));
        }

        report.append("\n---------------- DETAILED STUDENT REPORT -------------------\n\n");

        // Sort students by percentage (simple bubble sort for demonstration)
        Student[] sorted = students.toArray(new Student[0]);
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - i - 1; j++) {
                if (sorted[j].getPercentage() < sorted[j + 1].getPercentage()) {
                    Student temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }

        report.append(String.format("%-5s %-12s %-20s %-8s %-10s %-8s\n",
            "Rank", "Roll No", "Name", "Grade", "Percentage", "Status"));
        report.append("------------------------------------------------------------\n");

        int rank = 1;
        for (Student s : sorted) {
            String status = s.getGrade() == 'F' ? "FAIL" : "PASS";
            report.append(String.format("%-5d %-12s %-20s %-8c %-10.2f%% %-8s\n",
                rank++,
                s.getRollNumber(),
                truncateString(s.getName(), 20),
                s.getGrade(),
                s.getPercentage(),
                status));
        }

        report.append("\n============================================================\n");
        report.append("Report Generated Successfully\n");
        report.append("============================================================\n");

        return report.toString();
    }

    // ============ HELPER METHODS ============

    /**
     * Truncate string to specified length
     */
    private String truncateString(String str, int length) {
        if (str == null) return "";
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }

    /**
     * Center string in given width
     */
    private String centerString(String str, int width) {
        if (str.length() >= width) return str;
        int padding = (width - str.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(str);
        while (sb.length() < width) sb.append(" ");
        return sb.toString();
    }

    /**
     * Draw a simple bar graph using characters
     */
    private String drawBar(int percentage) {
        int barLength = percentage / 5; // Scale to max 20 chars
        if (barLength < 1 && percentage > 0) barLength = 1;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 20; i++) {
            if (i < barLength) {
                bar.append("#");
            } else {
                bar.append(" ");
            }
        }
        bar.append("]");
        return bar.toString();
    }
}
