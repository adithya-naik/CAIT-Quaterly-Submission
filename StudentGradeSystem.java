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
 * FIXED BUG-10: Added proper Javadoc documentation.
 */
public class StudentGradeSystem {   // FIXED BUG-01: Added public access modifier to class

    private static final Logger LOGGER =
            Logger.getLogger(StudentGradeSystem.class.getName());

    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 100;

    private static final double A_THRESHOLD = 90.0;
    private static final double B_THRESHOLD = 80.0;
    private static final double C_THRESHOLD = 70.0;
    private static final double D_THRESHOLD = 60.0;

    // FIXED BUG-02: Replaced raw HashMap with generic type-safe Map
    private static final Map<String, List<Integer>> studentGrades = new HashMap<>();

    // ─────────────────────────────────────────────
    // STUDENT MANAGEMENT
    // ─────────────────────────────────────────────

    public static void addStudent(String name) {

        // FIXED BUG-03: Added null and blank validation
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

        // FIXED BUG-04: Prevent NullPointerException by checking if student exists
        if (!studentGrades.containsKey(name)) {
            throw new IllegalArgumentException(
                    "Student '" + name + "' not found. Please register the student first."
            );
        }

        // FIXED BUG-05: Added grade range validation (0–100 only)
        if (grade < MIN_GRADE || grade > MAX_GRADE) {
            throw new IllegalArgumentException(
                    "Grade must be between " + MIN_GRADE + " and " + MAX_GRADE + "."
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

        // FIXED BUG-07: Prevent ArithmeticException when grade list is empty
        if (grades.isEmpty()) {
            LOGGER.warning("No grades found for student: " + name);
            return 0.0;
        }

        int total = 0;

        for (int grade : grades) {
            total += grade;
        }

        // FIXED BUG-06: Cast to double to avoid integer division precision loss
        return (double) total / grades.size();
    }

    public static String getLetterGrade(double average) {

        // FIXED BUG-08: Corrected A grade boundary (>= 90)
        // FIXED BUG-09: Corrected grading logic gaps (80–89, 70–79, etc.)

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
    // MAIN METHOD
    // ─────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("Starting Student Grade System...\n");

        addStudent("Apprentice1");
        addStudent("Apprentice2");
        addStudent("Apprentice3");

        addGrade("Apprentice1", 95);
        addGrade("Apprentice1", 88);
        addGrade("Apprentice1", 92);

        addGrade("Apprentice2", 70);
        addGrade("Apprentice2", 65);
        addGrade("Apprentice2", 78);

        addGrade("Apprentice3", 85);
        addGrade("Apprentice3", 90);
        addGrade("Apprentice3", 80);

        printReport();

        System.out.println("System completed successfully.");
    }
}
