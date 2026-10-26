package com.rasghz90.sms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentFileManager {
    
    private static final String FILE_NAME = "students.txt";
    private final String fileName;

    // Default constructor for existing application
    public StudentFileManager() {
        this(FILE_NAME);
    }

    // Contructor for selecting a file, espesially in unit test
    public StudentFileManager(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be empty.");
        }
        this.fileName = fileName;
    }

    // Existing static method: keeps Main.java compatible
    public static void saveStudents(List<Student> students) {
        new StudentFileManager().save(students);
    }

    // Existing static method: keeps Main.java compatible
    public static List<Student> loadStudents() {
        return new StudentFileManager().load();
    }

    // Instance method that saves to the configuration file
    public void save(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
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

    // Instance method that loads from configuration file
    public List<Student> load() {
        List<Student> students = new ArrayList<>();
        Set<Integer> seenIds = new HashSet<>();

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("No saved records found. Starting fresh.");
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] data = line.split(",", -1);

                if (data.length != 3) {
                    System.out.println("Skipping invalid record at line: " + lineNumber);
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1];
                    int age = Integer.parseInt(data[2].trim());

                    if (id <= 0 || name.trim().isEmpty() || age < 1 || age > 120) {
                        System.out.println("Skipping record with invalid field values at line " + lineNumber);
                        continue;
                    }

                    if (!seenIds.add(id)) {
                        System.out.println("Skipping duplicate student ID at line " + lineNumber);
                        continue;
                    }

                    students.add(new Student(id, name, age));

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid numeric data at line " + lineNumber);
                }
            }
            System.out.println("Student records loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading student records: " + e.getMessage());
        }

        return students;
    }
 }
