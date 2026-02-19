import java.util.*;
import java.util.logging.Logger;

/**
 * StudentGradeSystem - A professional grade management system.
 *
 * This system manages student records and grades using Java Collections.
 * It supports adding students, recording grades, computing averages,
 * and generating formatted reports.
 *
 * @author Group10
 * @version 1.0
 */
public class StudentGradeSystem {

    private static final Logger LOGGER = Logger.getLogger(StudentGradeSystem.class.getName());

    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 100;
    private static final double A_THRESHOLD = 90.0;
    private static final double B_THRESHOLD = 80.0;
    private static final double C_THRESHOLD = 70.0;
    private static final double D_THRESHOLD = 60.0;

    private static final Map<String, List<Integer>> studentGrades = new HashMap<>();

    // ─────────────────────────────────────────────
    // STUDENT MANAGEMENT
    // ─────────────────────────────────────────────

    public static void addStudent(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }

        if (studentGrades.containsKey(name)) {
            throw new IllegalArgumentException("Student '" + name + "' is already registered.");
        }

        studentGrades.put(name, new ArrayList<>());
        LOGGER.info("Student registered: " + name);
    }

    public static void addGrade(String name, int grade) {
        if (!studentGrades.containsKey(name)) {
            throw new IllegalArgumentException(
                    "Student '" + name + "' not found. Please register the student first."
            );
        }

        if (grade < MIN_GRADE || grade > MAX_GRADE) {
            throw new IllegalArgumentException(
                    "Grade must be between " + MIN_GRADE + " and " + MAX_GRADE + ". Received: " + grade
            );
        }

        studentGrades.get(name).add(grade);
        LOGGER.info("Grade " + grade + " added for student: " + name);
    }

    // ─────────────────────────────────────────────
    // GRADE CALCULATION
    // ─────────────────────────────────────────────

    public static double calculateAverage(String name) {
        if (!studentGrades.containsKey(name)) {
            throw new IllegalArgumentException("Student '" + name + "' not found.");
        }

        List<Integer> grades = studentGrades.get(name);

        if (grades.isEmpty()) {
            LOGGER.warning("No grades found for student: " + name);
            return 0.0;
        }

        int total = 0;

        for (int grade : grades) {
            total += grade;
        }

        return (double) total / grades.size();
    }

    public static String getLetterGrade(double average) {
        if (average >= A_THRESHOLD) return "A";
        if (average >= B_THRESHOLD) return "B";
        if (average >= C_THRESHOLD) return "C";
        if (average >= D_THRESHOLD) return "D";
        return "F";
    }

    // ─────────────────────────────────────────────
    // REPORTING
    // ─────────────────────────────────────────────

    public static void printReport() {

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           STUDENT GRADE REPORT           ║");
        System.out.println("╠══════════════════════════════════════════╣");

        List<String> sortedStudents = new ArrayList<>(studentGrades.keySet());
        Collections.sort(sortedStudents);

        for (String student : sortedStudents) {
            double avg = calculateAverage(student);
            String letter = getLetterGrade(avg);
            List<Integer> grades = studentGrades.get(student);

            StringBuilder sb = new StringBuilder();
            sb.append(String.format(
                    "║ %-15s | Avg: %5.2f | Grade: %s | Scores: %s",
                    student, avg, letter, grades.toString()
            ));

            System.out.println(sb);
        }

        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf("║ Total Students: %-25d ║%n", studentGrades.size());
        System.out.println("╚══════════════════════════════════════════╝\n");
    }

    // ─────────────────────────────────────────────
    // MAIN — Entry Point
    // ─────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("Starting Student Grade System...\n");

        // Register students
        addStudent("Apprentice1");
        addStudent("Apprentice2");
        addStudent("Apprentice3");

        // Add grades
        addGrade("Apprentice1", 95);
        addGrade("Apprentice1", 88);
        addGrade("Apprentice1", 92);

        addGrade("Apprentice2", 70);
        addGrade("Apprentice2", 65);
        addGrade("Apprentice2", 78);

        addGrade("Apprentice3", 85);
        addGrade("Apprentice3", 90);
        addGrade("Apprentice3", 80);

        // Demonstrate error handling
        try {
            addGrade("NonExistentStudent", 85);
        } catch (IllegalArgumentException e) {
            System.out.println("[Handled Error] " + e.getMessage());
        }

        try {
            addGrade("Apprentice1", 110);
        } catch (IllegalArgumentException e) {
            System.out.println("[Handled Error] " + e.getMessage());
        }

        // Print report
        printReport();

        System.out.println("System completed successfully.");
    }
}
