import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentFileManager {
    
    private static final String FILE_NAME = "student.txt";

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Student student : students) {
                writer.write(
                    student.getId() + "," +
                    student.getName() + "," +
                    student.getAge()
                );
                writer.newLine();
            }
            System.out.println("Student records saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving student records: " + e.getMessage());
        }
    }

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);

                Student student = new Student(id, name, age);
                students.add(student);
            }
            System.out.println("Student records loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading student records: " + e.getMessage());
        }

        return students;
    }
}
