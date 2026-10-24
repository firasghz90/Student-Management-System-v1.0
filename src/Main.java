import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("=========================");
        System.out.println("Student Management System");
        System.out.println("=========================");

        Scanner scanner = new Scanner(System.in);
        List<Student> students = StudentFileManager.loadStudents();

        boolean running = true;
        int userChoice = 0;
        while (running) {
            try {
                System.out.println("\n1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student ID");
                System.out.println("4. Update Student Age");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.println("=========================");
                System.out.print("Enter your choice: ");
                userChoice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (userChoice) {
                case 1:
                    addStudent(scanner, students);
                    break;

                case 2:
                    viewAllStudents(students);
                    break;

                case 3:
                    searchStudent(scanner, students);
                    break;

                case 4:
                    updateStudent(scanner, students);
                    break;

                case 5:
                    deleteStudent(scanner, students);
                    break;

                case 6:
                    StudentFileManager.saveStudents(students);
                    System.out.println("Goodbye!");
                    running = false;
                    break;
            
                default:
                    System.out.println("Invalid choice. Please choose between 1 - 6");
                    break;
                }
        }
        scanner.close();
    }

    public static void viewAllStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No student registered yet.");
            return;
        }

        System.out.println("List of student: ");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void addStudent(Scanner scanner, List<Student> students) {
        // Student ID
        System.out.print("\nEnter student ID: ");
        int studentID;
        try {
            studentID = Integer.parseInt(scanner.nextLine());
            if (studentID <= 0) {
                System.out.println("Student ID must be positive. Try again.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid student ID. Please enter a number.");
            return;
        }
    
        // Student Name
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        // Student Age
        System.out.print("Enter student age: ");
        int studentAge;
        try {
            studentAge = Integer.parseInt(scanner.nextLine());
            if (studentAge < 1 || studentAge > 120) {
                System.out.println("Invalid age. Age must be between 1 and 120.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Please enter a number.");
            return;
        }

        // Validation
        Student findStudent = findStudentById(students, studentID);
        if (findStudent != null) {
            System.out.println("Student ID already exists. Please try again.");
            return;
        }

        students.add(new Student(studentID, studentName, studentAge));
        StudentFileManager.saveStudents(students);
        System.out.println("Student added successfully!");
    }

    public static void searchStudent(Scanner scanner, List<Student> students) {
        try {
            System.out.print("Enter student ID to search: ");
            int searchID = Integer.parseInt(scanner.nextLine());
            Student foundStudent = findStudentById(students, searchID);
            if (foundStudent != null) {
                System.out.println("Found: " + foundStudent);
            } else {
                System.out.println("Student not found or registered yet.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }

    public static void updateStudent(Scanner scanner, List<Student> students) {
         try {
            System.out.print("Enter student ID to update: ");
            int updateID = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter a new age: ");
            int newAge = Integer.parseInt(scanner.nextLine());

            boolean statusUpdate = updateStudentAge(students, updateID, newAge);
            if (statusUpdate) {
                StudentFileManager.saveStudents(students);
                System.out.println("New Age updated successfully!");
            } else {
                if (findStudentById(students, updateID) == null) {
                    System.out.println("Update failed. Student ID not found.");
                } else {
                     System.out.println("Update failed. Age must be between 1 and 120.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    public static void deleteStudent(Scanner scanner, List<Student> students) {
        try {
            System.out.print("Enter student ID to delete: ");
            int deleteId = Integer.parseInt(scanner.nextLine());

            boolean deletedStudent = deleteStudentById(students, deleteId);
            if (deletedStudent) {
                StudentFileManager.saveStudents(students);
                System.out.println("Student " + deleteId + " deleted successfully!");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
        }
    }


    /* 
     * Function
     */ 
    public static Student findStudentById(List<Student> students, int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static boolean updateStudentAge(List<Student> students, int id, int newAge) {
        Student student = findStudentById(students, id);

        if (student != null) {
            if (newAge < 1 || newAge > 120) {
                return false;
            }

            student.setAge(newAge);
            return true;
        }

        return false;
    }

    public static boolean deleteStudentById(List<Student> students, int id) {
        Student student = findStudentById(students, id);

        if (student != null) {
            students.remove(student);
            return true;
        }

        return false;
    }
}
