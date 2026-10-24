import java.util.List;

public class StudentManager {
    
    private final List<Student> students;

    public StudentManager(List<Student> students) {
        this.students = students;
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean updateStudentAge(int id, int newAge) {
        Student student = findStudentById(id);

        if (student != null) {
            if (newAge < 1 || newAge > 120) {
                return false;
            }

            student.setAge(newAge);
            return true;
        }

        return false;
    }

    public boolean deleteStudentById(int id) {
        Student student = findStudentById(id);

        if (student != null) {
            students.remove(student);
            return true;
        }

        return false;
    }
}
