package com.rasghz90.sms;

public class StudentValidationTest {
    public static void main(String[] args) {
        Student student = new Student(201, "  Alice  ", 20);

        System.out.println(student);

        try {
            student.setAge(-1);
            System.out.println("ERROR: Invalid age was accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid age rejected successfully.");
        }

        try {
            new Student(202, "   ", 20);
            System.out.println("ERROR: Empty name was accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("Empty name rejected successfully.");
        }

        try {
            new Student(203, "Smith, John", 20);
            System.out.println("ERROR: Comma in name was accepted.");
        } catch (IllegalArgumentException e) {
            System.out.println("Comma in name rejected successfully.");
        }
    }
}
