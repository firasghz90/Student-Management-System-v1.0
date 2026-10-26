package com.rasghz90.sms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;

public class StudentTest {
    
    @Test 
    void shouldCreateStudentWithCorrectDetails() {
        Student student = new Student(101, "Alice", 20);

        assertEquals(101, student.getId());
        assertEquals("Alice", student.getName());
        assertEquals(20, student.getAge());

    }

    @Test 
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Student(0, "Alice", 20));
    }

    @Test 
    void shouldRejectNameContainingCommas() {
        assertThrows(IllegalArgumentException.class, () -> new Student(101, "Alice,", 20));
    }

    @Test 
    void shouldRejectNonPositiveId() {
        assertThrows(IllegalArgumentException.class, () -> new Student(-10, "Alice", 20));
    }

    @Test 
    void shouldRejectAgeBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () -> new Student(101, "Alice", 0));
    }

    @Test 
    void shouldRejectAgeAboveMinimum() {
        assertThrows(IllegalArgumentException.class, () -> new Student(101, "Alice", 121));
    }

    @Test 
    void shouldAcceptMinimumAllowedAge() {
        Student student = new Student(101, "Alice", 1); 

        assertEquals(1, student.getAge());
    }

    @Test 
    void shouldAcceptMaximumAllowedAge() {
        Student student = new Student(101, "Alice", 120);

        assertEquals(120, student.getAge());
    }
}
