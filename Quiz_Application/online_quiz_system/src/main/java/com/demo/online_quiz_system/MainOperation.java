package com.demo.online_quiz_system;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainOperation {

    static Scanner sc = new Scanner(System.in);

    // Establishing a connection to the database
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/quiz_system_db"; // replace with your DB URL
        String user = "root"; // replace with your DB username
        String password = "123456"; // replace with your DB password
        return DriverManager.getConnection(url, user, password);
    }
    
 // Simulate user database with a map (for registration and login validation)
    static Map<String, String> userDatabase = new HashMap<>();

    // Predefined users (simulate existing database entries)
    static {
        userDatabase.put("admin", "admin123");
        userDatabase.put("Shreyash", "shr123");
        userDatabase.put("manish", "man123");
    }

    public static void mainOps() {
        System.out.println("====================================");
        System.out.println("Welcome to the Online Quiz System!");
        System.out.println("====================================");

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Login");
            System.out.println("2. New User (Register)");
            System.out.print("Enter your choice (1 or 2): ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }

    // User Login
    public static void login() {
        System.out.println("\n====================================");
        System.out.println("User Login");
        System.out.println("====================================");

        System.out.print("Enter your username: ");
        String username = sc.nextLine();

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        try (Connection con = getConnection()) {
            // Check if username exists in the database
            String checkQuery = "SELECT * FROM user WHERE username = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Username exists, check if the password matches
                String dbPassword = rs.getString("password");
                if (dbPassword.equals(password)) {
                    System.out.println("\nLogin successful! Welcome, " + username + "!");
                    if (username.equals("admin")) {
                        showAdminMenu();
                    } else {
                        showUserMenu();
                    }
                } else {
                    System.out.println("\nIncorrect password. Please try again.");
                }
            } else {
                // Username does not exist, ask user to register
                System.out.println("\nUsername not found. Registering new user...");

                // Store the user in the database since they are logging in for the first time
                String insertQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
                PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();

                System.out.println("Registration complete! You are now logged in as " + username + ".");
                showUserMenu();  // Non-admin menu by default
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // User Registration
    public static void register() {
        System.out.println("\n====================================");
        System.out.println("New User Registration");
        System.out.println("====================================");

        System.out.print("Enter your desired username: ");
        String username = sc.nextLine();

        System.out.print("Enter your desired password: ");
        String password = sc.nextLine();

        try (Connection con = getConnection()) {
            // Check if username already exists in the database
            String checkQuery = "SELECT * FROM user WHERE username = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Username exists, ask for a unique one
                System.out.println("Username already exists. Please choose a different one.");
            } else {
                // Temporarily register the user but not store in the database yet
                userDatabase.put(username, password);
                System.out.println("Registration successful! Please log in with your new credentials.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Admin Menu
    public static void showAdminMenu() {
        while (true) {
            System.out.println("\n====================================");
            System.out.println("Admin Operations Menu");
            System.out.println("====================================");
            System.out.println("Press 1: User Details");
            System.out.println("Press 2: Question Details");
            System.out.println("Press 3: Categories Details");
            System.out.println("Press 4: Play Quiz");
            System.out.println("Press 5: Quit");

            int input = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (input) {
                case 1:
                    AllOparations.userOperations();
                    break;
                case 2:
                    // Uncomment this line when implemented
                    // AllOparations.questionOperations();
                    break;
                case 3:
                    AllOparations.categoriesOperations();
                    break;
                case 4:
                    AllOparations.playQuiz();
                    break;
                case 5:
                    System.out.println("Exiting admin operations.");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Non-admin (Regular User) Menu
    public static void showUserMenu() {
        while (true) {
            System.out.println("\n====================================");
            System.out.println("User Menu");
            System.out.println("====================================");
            System.out.println("Press 1: User Details");
            System.out.println("Press 2: Play Quiz");
            System.out.println("Press 3: Quit");

            int input = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (input) {
                case 1:
                    AllOparations.userOperations();
                    break;
                case 2:
                    AllOparations.playQuiz();
                    break;
                case 3:
                    System.out.println("Exiting user menu.");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void main(String[] args) {
        mainOps();
    }
}
