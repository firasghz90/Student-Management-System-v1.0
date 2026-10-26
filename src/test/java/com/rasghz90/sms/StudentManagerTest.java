package com.rasghz90.sms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentManagerTest {
    
    private List<Student> students;
    private StudentManager studentManager;

    @BeforeEach 
    void setup() {
        students = new ArrayList<>();
        students.add(new Student(101, "Alice", 20));
        students.add(new Student(102, "Bob", 23));

        studentManager = new StudentManager(students);
    }

    @Test 
    void shouldFindExistingStudent() {
        Student student = studentManager.findStudentById(101);

        assertNotNull(student);
        assertEquals("Alice", student.getName());
        assertEquals(20, student.getAge());
    }

    @Test 
    void shouldReturnNullForMissingStudent() {
        Student missingStudent = studentManager.findStudentById(103);

        assertNull(missingStudent);
    }

    @Test 
    void shouldUpdateExistingStudentAge() {
        boolean updateStudentAge = studentManager.updateStudentAge(101, 23);

        assertTrue(updateStudentAge);
        assertEquals(23, studentManager.findStudentById(101).getAge());
    }

    @Test 
    void shouldNotUpdateMissingStudent() {
        boolean updateStudentAge = studentManager.updateStudentAge(103, 24);

        assertFalse(updateStudentAge);
        assertEquals(2, students.size());
    }

    @Test 
    void shouldRejectInvalidAgeAndKeepOriginalAge() {
        boolean updateStudentAge = studentManager.updateStudentAge(101, 0);

        assertFalse(updateStudentAge);
        assertEquals(20, studentManager.findStudentById(101).getAge());
    }

    @Test 
    void shouldDeleteExistingStudent() {
        boolean deletedStudent = studentManager.deleteStudentById(101);

        assertTrue(deletedStudent);
        assertNull(studentManager.findStudentById(101));
        assertEquals(1, students.size());
    }

    @Test 
    void shouldNotDeleteMissingStudent() {
        boolean deletedStudent = studentManager.deleteStudentById(999);

        assertFalse(deletedStudent);
        assertEquals(2, students.size());
    }
}
