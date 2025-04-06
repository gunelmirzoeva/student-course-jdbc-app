                        Student-Course JDBC App
            📖 Overview
            This project is a simple Java application that demonstrates JDBC concepts using PostgreSQL. It includes functionality to:
            
            Connect to a PostgreSQL database
            
            Insert student records into a table
            
            Retrieve and display all student records
            
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
            
            Execute the main.TestConnection class to test the connection and retrieve student data.
            
            📋 Dependencies
            Include the PostgreSQL JDBC Driver in your pom.xml:
            
            xml
            <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.3</version>
            </dependency>
            🚀 Features
            Connects to PostgreSQL and performs basic CRUD operations.
            
            Demonstrates JDBC concepts like Connection, PreparedStatement, and ResultSet.
            
            📝 Notes
            Ensure PostgreSQL is running before executing the application.
            
            Customize the database credentials as per your local setup.
            
            💡 Future Enhancements
            Add more tables to represent courses and student-course relationships.
            
            Incorporate exception handling and logging for better error management.
            
            Explore integration with an ORM framework like Hibernate.
            
