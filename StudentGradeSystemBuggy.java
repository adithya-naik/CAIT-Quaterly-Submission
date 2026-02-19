import java.util.*;

// BUG-01: Missing public Access Modifier on Class
class StudentGradeSystemBuggy {

    // BUG-02: Raw HashMap Without Generic Types
    static HashMap studentGrades = new HashMap();

    // BUG-03: No Null/Blank Validation in addStudent()
    static void addStudent(String name) {
        studentGrades.put(name, new ArrayList<>());
    }

    // BUG-04: NullPointerException When Adding Grade for Unregistered Student
    // BUG-05: No Grade Range Validation (Accepts Negative / Over-100 Grades)
    static void addGrade(String name, int grade) {

        // No check if student exists (may return null)
        List grades = (List) studentGrades.get(name);

        // No validation for grade range (0-100)
        grades.add(grade);  // May throw NullPointerException
    }

    // BUG-06: Integer Division Loses Decimal Precision
    // BUG-07: ArithmeticException on Empty Grade List
    static double calculateAverage(String name) {

        List grades = (List) studentGrades.get(name);

        int total = 0;

        for (Object g : grades) {
            total += (int) g;
        }

        // Integer division + divide by zero if list empty
        return total / grades.size();
    }

    // BUG-08: Wrong Grade Boundary in getLetterGrade() — A Grade Threshold
    // BUG-09: Missing 80–89 Range — Logic Gap in Grade Classification
    static String getLetterGrade(double average) {

        if (average > 90) return "A";   // Should be >= 90

        if (average > 70) return "B";   // Should be 80 threshold

        if (average > 60) return "C";

        if (average > 50) return "D";

        return "F";
    }

    // BUG-10: No Javadoc or Code Documentation
    static void printReport() {

        System.out.println("=== Student Grade Report ===");

        Set students = studentGrades.keySet();

        for (Object student : students) {

            String name = (String) student;

            double avg = calculateAverage(name);

            System.out.println(
                "Student: " + name +
                " | Average: " + avg +
                " | Grade: " + getLetterGrade(avg)
            );
        }
    }

    public static void main(String[] args) {

        addStudent("Apprentice1");
        addStudent("Apprentice2");

        addGrade("Apprentice1", 95);
        addGrade("Apprentice1", 88);
        addGrade("Apprentice1", 92);

        addGrade("Apprentice2", 70);
        addGrade("Apprentice2", 65);
        addGrade("Apprentice2", 78);

        // Triggers BUG-04 (NullPointerException)
        addGrade("Apprentice3", 85);

        printReport();
    }
}
