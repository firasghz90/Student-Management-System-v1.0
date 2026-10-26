package com.rasghz90.sms;

public class Student {
    
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        setId(id);
        setName(name);
        setAge(age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        if (name.contains(",")) {
            throw new IllegalArgumentException("Student name cannot contain commas.");
        }

        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120.");
        }

        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age;
    }
}
