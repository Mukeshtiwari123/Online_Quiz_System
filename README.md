# Online_Quiz_System

The **Online Quiz System** is a comprehensive application designed to manage and conduct quizzes for both admin and regular users. Developed using **Java** for backend functionality and **MySQL** for the database, this system offers robust user management, quiz operations, and a user-friendly interface.

The application is divided into two main user roles:

**1. Admin:** The admin has full control over managing users, quiz questions, and categories.


**2. Regular User:** Regular users can log in, view their details, and participate in quizzes.



**Key Features and Operations:**


**1. User Management:**

   **i. User Login:** The system checks if the username exists in the database. If the username exists, it verifies the password. If the username is new, it stores the user’s credentials in the database and logs them in.

   
   **ii. User Registration:** New users can register by providing a unique username and password. If the username already exists, the system prompts the user to choose a different name.

   
**2. Admin Operations:**

   **i. User Details:** The admin can view all registered user details.


   **ii. Question Management** (future enhancement): Admins can manage quiz questions and their associated details.


   **iii. Category Management:** Admins can manage different quiz categories.


   **iv. Play Quiz:** Admins can participate in quizzes to test the system's performance.

   
**3. Regular User Operations:**

   **i. User Details:** Users can view their personal information.

   
   **ii. Play Quiz:** Users can participate in available quizzes.



**4. Database Integration:**

   **i.** User credentials are stored in a **MySQL** database.

   
   **ii.** The database checks for unique usernames to prevent duplicate entries and ensures secure login.

   
The project is designed to provide a seamless quiz experience with clear separation between admin and user operations, as well as efficient user authentication.



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

