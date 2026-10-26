# Student Management System

A console-based Student Management System built with Java 17. This project demonstrates object-oriented programming, CRUD operations, input validation, file-based persistence, and automated testing with JUnit 5.

## Features

- **Create students** with validated student details.
- **View students** stored in the application.
- **Update student records**.
- **Delete student records**.
- **Input validation** for student IDs, names, and ages.
- **File-based persistence** to retain student records between application restarts.
- **Error handling** for invalid data and file operations.
- **Automated tests** using JUnit 5 and Maven.

## Technology Stack

- Java 17
- Apache Maven
- JUnit 5
- Java File I/O
- Git and GitHub

## Prerequisites

Install the following before running the application:

- Java Development Kit (JDK) 17 or later
- Apache Maven 3.6 or later
- Git (optional, for cloning the repository)

Verify your installations:

```bash
java -version
mvn -version
```

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/firasghz90/Student-Management-System-v1.0.git
cd Student-Management-System-v1.0
```

### 2. Build the project

```bash
mvn clean package
```

### 3. Run the application

The application uses Maven's compiled classes. From the project root, run:

```bash
java -cp target/classes com.rasghz90.sms.Main
```

Follow the console menu to manage student records.

## Running Tests

Execute the automated test suite with:

```bash
mvn clean test
```

The tests cover student validation, student management operations, and file persistence and error-handling scenarios.

## Project Structure

```text
Student-Management-System-v1.0/
├── pom.xml
├── README.md
├── .gitignore
├── students.txt
└── src/
    ├── main/
    │   └── java/com/rasghz90/sms/
    │       ├── Main.java
    │       ├── Student.java
    │       ├── StudentManager.java
    │       └── StudentFileManager.java
    └── test/
        └── java/com/rasghz90/sms/
            ├── StudentTest.java
            ├── StudentManagerTest.java
            └── StudentFileManagerTest.java
```

### Main Classes

- **`Main`** — provides the console interface and user interaction.
- **`Student`** — represents a student and validates student details.
- **`StudentManager`** — manages student records and CRUD operations.
- **`StudentFileManager`** — loads and saves student records using file I/O.

## Data Persistence

Student records are stored in a local text file named `students.txt`. This allows records to persist across application restarts.

The repository includes fictional sample records for demonstration. Back up important data before testing operations that modify or delete records.

## Learning Outcomes

This project demonstrates practical experience with:

- Java fundamentals and object-oriented programming
- Encapsulation and separation of responsibilities
- Collections and CRUD operations
- Input validation and exception handling
- File I/O and persistence
- Unit testing with JUnit 5
- Maven build and test workflows
- Git version control

## Future Improvements

Potential future enhancements include:

- Database integration with JDBC
- A graphical user interface or web interface
- Search and sorting functionality
- Improved configuration for the data-file location
- Continuous integration with GitHub Actions

## License

No license has been specified yet. Contact the repository owner before reusing or redistributing this project.
