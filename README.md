# Online_Quiz_System

This is a console-based project designed to manage and conduct online quizzes. The project involves user management, quiz creation, and result analysis. It is built using Java, Hibernate for ORM (Object-Relational Mapping), and MySQL as the database.



**Abstract**

The Online Quiz System allows users to participate in quizzes, while administrators (admin) can create and manage quizzes. The system tracks results and displays them on leaderboards. It uses Java Hibernate for seamless interaction between Java objects and the database, ensuring persistent storage of users, quizzes, results, and leaderboard entries.

**Main Entities**

**1. User**: Represents a participant or an administrator who can log in, take quizzes, and view results.

**2. Quiz**: Represents the quiz details such as questions, created by a user (admin).

**3. Result**: Represents the results of the users after completing a quiz.

**4. Leaderboard**: Displays the users and their scores based on their quiz performances.






**Key Features**

**1. User Management:** Create, read, update, and delete users.

**2. Quiz Management:** Admin can create and delete quizzes.

**3. Result Analysis:** Store and display quiz results for users.

**4. Leaderboard:** Track and show the highest scores and top-performing users.





**Getting Started**


**Prerequisites**

Before you begin, ensure you have the following installed on your system:

**1. Java** (Version 8 or higher)

**2. MySQL** (Set up the database as per the schema)

**3. Hibernate** (Included in the project via Maven dependencies)

**4. Maven** (for building the project)

**5. Git** (optional, if you want to clone the repository)


**Steps to Download and Run the Project**


**1. Clone the Repository**

You can clone this repository using Git by running the following command in your terminal:


**git clone https://github.com/yourusername/online_quiz_system.git**


**2. Database Setup**

1. Create a **MySQL** database named **online_quiz_system**.
 
2. Use the provided SQL schema and run it on your MySQL server.

3. Configure the database connection in the **hibernate.cfg.xml** file.


Example configuration for **hibernate.cfg.xml**:

<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/**online_quiz_system**</property>

  <property name="hibernate.connection.username">**your_username**</property>

   <property name="hibernate.connection.password">**your_password**</property>



**3. Build the Project**

If you are using Maven, navigate to the project directory and run the following command to download dependencies and build the project:


**mvn clean install**


**4. Run the Project**

After building, you can run the project using your Java IDE (Eclipse/IntelliJ) or directly from the terminal using:


**java -jar target/online_quiz_system.jar**


**How to Use the System**

**User Operations**

**1. Admin** can create quizzes, delete users, and view the leaderboard.

**2. Users** can log in, participate in quizzes, and view their quiz results.



**Technologies Used**


**1. Java**: Core language for the application.

**2. Hibernate**: For ORM and database management.

**3. MySQL**: As the database.

**4. Maven**: For dependency management and project building.



**Contributing**

If you'd like to contribute to this project, please fork the repository and create a pull request with your improvements or bug fixes.

