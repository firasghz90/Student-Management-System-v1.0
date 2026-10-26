package com.rasghz90.sms;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StudentFileManagerTest {

    @TempDir 
    Path tempDir;

    @Test 
    void shouldSaveAndLoadStudents() {
        Path filePath = tempDir.resolve("students.txt");
        StudentFileManager fileManager = new StudentFileManager(filePath.toString());

        List<Student> students = List.of(
            new Student(101, "Alice", 20),
            new Student(102, "Bob", 24)
        );

        fileManager.save(students);

        List<Student> loadStudents = fileManager.load();

        assertEquals(2, loadStudents.size());
        assertEquals(101, loadStudents.get(0).getId());
        assertEquals("Alice", loadStudents.get(0).getName());
        assertEquals(20, loadStudents.get(0).getAge());
    
        assertEquals(102, loadStudents.get(1).getId());
        assertEquals("Bob", loadStudents.get(1).getName());
        assertEquals(24, loadStudents.get(1).getAge());
    }

    @Test 
    void shouldReturnEmptyListWhenFileDoesNotExist() {
        Path filePath = tempDir.resolve("missing.txt");
        StudentFileManager fileManager = new StudentFileManager(filePath.toString());

        List<Student> students = fileManager.load();

        assertNotNull(students);
        assertTrue(students.isEmpty());
    }

    @Test 
    void shouldSkipMalformedRecords() throws IOException {
        Path filePath = tempDir.resolve("malformed.text");

        Files.writeString(filePath, 
            "101,Alice, 20\n" +
            "this-is-not-valid\n" +
            "102,Bob,22, extra\n" +
            "103,Carol,19\n");

        StudentFileManager fileManager = new StudentFileManager(filePath.toString());
        List<Student> students = fileManager.load();

        assertEquals(2, students.size());
        assertEquals(101, students.get(0).getId());
        assertEquals(103, students.get(1).getId());
    }


    @Test 
    void shouldSkipInvalidFieldValues() throws IOException {
        Path filePath = tempDir.resolve("invalid-values.txt");

        Files.writeString(filePath, 
            "0,Alice,20\n" +
            "102,     ,22\n" +
            "103,Carol,0\n" +
            "104,David,121\n" +
            "105,Eve,twenty\n" + 
            "106,Frank,25");

        StudentFileManager fileManager = new StudentFileManager(filePath.toString());
        List<Student> students = fileManager.load();

        assertEquals(1, students.size());
        assertEquals(106, students.get(0).getId());
        assertEquals("Frank", students.get(0).getName());
    }

    @Test 
    void shouldSkipDuplicateStudentIds() throws IOException {
        Path filePath = tempDir.resolve("duplicates.txt");

        Files.writeString(filePath, 
            "101,Alice,20\n" +
            "101,Another Alice, 25\n" +
            "102,Bob,22\n");

        StudentFileManager fileManager = new StudentFileManager(filePath.toString());
        List<Student> students = fileManager.load();

        assertEquals(2, students.size());
        assertEquals("Alice", students.get(0).getName());
        assertEquals("Bob", students.get(1).getName());
    }

    @Test 
    void shouldRejectEmptyFilePath() {
        assertThrows(IllegalArgumentException.class, () -> new StudentFileManager(" "));
    }

    @Test
    void shouldHandleSaveFailureWithoutThrowingException() throws IOException {
        Path directoryInsteadOfFile = Files.createDirectories(tempDir.resolve("directory"));

        StudentFileManager fileManager = new StudentFileManager(directoryInsteadOfFile.toString());
        List<Student> students = List.of(new Student(101, "Alice", 20));

        assertDoesNotThrow(() -> fileManager.save(students));
    }

    @Test 
    void shouldHandleLoadFailureWithoutThrowingException() throws IOException {
        Path directoryInsteadOfFile = Files.createDirectories(tempDir.resolve("another-directory"));

        StudentFileManager fileManager = new StudentFileManager(directoryInsteadOfFile.toString());
        List<Student> students = assertDoesNotThrow(() -> fileManager.load());

        assertNotNull(students);
        assertTrue(students.isEmpty());
    }
    
}
