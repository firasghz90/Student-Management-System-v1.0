import java.util.List;

public class MainTemp {
    public static void main(String[] args) {
        
        List<Student> students = StudentFileManager.loadStudents();
        System.out.println("\nLoaded students.");
        
        for (Student student : students) {
            System.out.println(student);
        }


    }
}
