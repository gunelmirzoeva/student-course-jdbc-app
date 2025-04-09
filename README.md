                        Student-Course JDBC App
            📖 Overview
            This project is a simple Java application that demonstrates JDBC concepts using PostgreSQL. 
            Now includes a many-to-many relationship between students and courses.

            It currently includes functionality to:

            Connect to a PostgreSQL database
            Perform CRUD operations (Create, Read, Update, Delete) for students via the DAO pattern
            Managing enrollments with JOIN queries and many-to-many mappings
            
            📂 Project Structure
            src/
            ├── config/        # Database configuration
            ├── dao/           # Data access objects
            ├── entity/        # Entities representing database tables
            ├── main/          # Main classes for running the application

            🛠️ Requirements
            Java: JDK 8 or higher
            
            PostgreSQL: Version 12 or higher
            
            Maven: For dependency management
            
            🔧 Setup Instructions
            Install PostgreSQL:
            
            Create a database student_course_db.
            
            Create a table students with the following SQL:
            
            sql
            CREATE TABLE students (
            id SERIAL PRIMARY KEY,
            name VARCHAR(100),
            age INT
            );

            CREATE TABLE courses (
            id SERIAL PRIMARY KEY,
            name VARCHAR(100) NOT NULL
            );
            
            CREATE TABLE student_courses (
            student_id INT REFERENCES students(id) ON DELETE CASCADE,
            course_id INT REFERENCES courses(id) ON DELETE CASCADE,
            PRIMARY KEY (student_id, course_id)
            );

            Configure Database:
            
            Update the DatabaseConfig class with your PostgreSQL credentials:
            
            java
            public class DatabaseConfig {
            public static final String URL = "jdbc:postgresql://localhost:5432/student_course_db";
            public static final String USER = "your_user";
            public static final String PASSWORD = "your_password";
            }

            Build the Project:
            
            Run mvn clean install to build the project and resolve dependencies.
            
            Run the Application:
            
            Execute the main.TestConnection class to test the connection and retrieve, create, update, 
            or delete student and course data..
            
            📋 Dependencies
            Include the PostgreSQL JDBC Driver in your pom.xml:
            
            xml
            <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.5</version>
            </dependency>
            🚀 Features
            Implements the DAO (Data Access Object) pattern for cleanly separating database logic

            Provides basic CRUD operations for students
            
            Demonstrates JDBC concepts like Connection, PreparedStatement, and ResultSet

            Manages enrollments using JOIN queries
            
            📝 Notes
            Ensure PostgreSQL is running before executing the application.
            
            Customize database credentials in the DatabaseConfig class as per your local setup
            
            💡 Future Enhancements

            Prevent duplicate enrollments using unique constraints

            Add CLI menus for easier enrollment management
            
            Explore integration with ORM frameworks like Hibernate
            
