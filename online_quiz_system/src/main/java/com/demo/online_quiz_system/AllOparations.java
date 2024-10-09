package com.demo.online_quiz_system;

import com.demo.online_quiz_system.service.UserService;

import com.demo.online_quiz_system.service.QuizService;
import com.demo.online_quiz_system.service.*;
import com.demo.online_quiz_system.serviceImpl.*;

import com.demo.online_quiz_system.serviceImpl.UserServiceImpl;
//import com.demo.online_quiz_system.serviceImpl.QuizServiceImpl;
import com.demo.online_quiz_system.entities.*;
import com.demo.online_quiz_system.entities.Question.QuestionType;
import com.demo.online_quiz_system.AllOparations;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import java.sql.Timestamp;
import java.time.Instant;
import com.demo.online_quiz_system.Dao.QuizDao;
import com.demo.online_quiz_system.DaoImpl.QuizDaoImpl;
public class AllOparations {

	static UserService userService = new UserServiceImpl();
	static QuizService quizService = new QuizServiceImpl();
	static AnswersService answerService = new AnswersServiceImpl();
	static ResultService resultService = new ResultServiceImpl();
	static QuestionService questionService = new QuestionServiceImpl();
	static LeaderboardService leaderboardService = new LeaderboardServiceImpl();
	static CategoriesService categoriesService = new CategoriesServiceImpl();

	//static FeedBackService feedBackService = new FeedBackServiceImpl();

	static Scanner sc = new Scanner(System.in);


	// This method checks if a username already exists in the database
	public static String checkUsernameExists() {
		String username;
		while (true) {
			System.out.print("Username (unique): ");
			username = sc.nextLine();

			if (!userService.isUsernameExists(username)) {
				break; // Break the loop if the username is unique
			} else {
				System.out.println("This username is already taken, please enter a different one.");
			}
		}
		return username;
	}

	// User Input method
	public static User userInputs() {
		System.out.println("Please enter the following details for the User:");

		// Check for unique username
		String username = checkUsernameExists(); // This now handles checking if the username is unique

		System.out.print("Password: ");
		String password = sc.nextLine();

		// Use empty sets for createdQuizzes, results, and leaderboardEntries (for now)
		Set<Quiz> createdQuizzes = new HashSet<>();
		Set<Result> results = new HashSet<>();
		Set<Leaderboard> leaderboardEntries = new HashSet<>();

		// Create and return a new User object using the provided constructor
		return new User(username, password, createdQuizzes, results, leaderboardEntries);
	}


	public static Quiz quizInputs() {
	    Scanner sc = new Scanner(System.in); // Initialize the scanner
	    sc.nextLine();  // Clear the buffer to avoid input skipping

	    // Prompt user for the quiz title
	    System.out.print("Enter Quiz Title (not null): ");
	    String title = sc.nextLine();

	    // Validate that the title is not empty
	    while (title == null || title.trim().isEmpty()) {
	        System.out.print("Quiz title cannot be empty. Please enter a valid title: ");
	        title = sc.nextLine();
	    }

	    // Fetching the category
	    Categories category = null;
	    while (category == null) {
	        System.out.print("Enter Category ID for the quiz (must exist): ");
	        int categoryId = sc.nextInt();
	        category = categoriesService.getCategoryById(categoryId); // Fetching the category by ID
	        if (category == null) {
	            System.out.println("Invalid Category ID. Please enter a valid one.");
	        }
	    }

	    // Fetching the user ID
	    User createdBy = null;
	    while (createdBy == null) {
	        System.out.print("Enter User ID of the creator (must exist): ");
	        String userId = Integer.toString(sc.nextInt()); // Assuming user IDs are integers
	        createdBy = userService.getUser(userId);  // Fetch user by ID
	        if (createdBy == null) {
	            System.out.println("User not found with ID: " + userId);
	        } else {
	            System.out.println("User found: " + createdBy);
	        }
	    }

	    // Initialize an empty leaderboard set since this is a new quiz
	    Set<Leaderboard> leaderboardEntries = new HashSet<>();

	    // Returning the new Quiz instance
	    return new Quiz(title, category, createdBy, leaderboardEntries);
	}




	//  Categories Input method
	public static Categories categoryInput() {
		System.out.print("Category Name (unique, max 50 characters): ");
		String name = sc.nextLine();

		// Validate uniqueness and constraints
		while (categoriesService.isCategoryNameExists(name)) {
			System.out.println("This category name is already taken, please enter a different one.");
			System.out.print("Category Name (unique, max 50 characters): ");
			name = sc.nextLine();
		}

		System.out.print("Category Description (optional): ");
		String description = sc.nextLine();

		// Initialize an empty Set of quizzes
		Set<Quiz> quizzes = new HashSet<>();
		// Returning a new Category object with user inputs
		return new Categories(name, description, quizzes);
	}


	//Input Method for Answers
	public static Answers answerInput() {
		// Input for the answer ID
		sc.nextLine();
		System.out.println("Enter Question ID:");
		String questionId = sc.nextLine(); // Input for the question ID
		sc.nextLine();
		System.out.println("Enter Answer Text:");
		String answerText = sc.nextLine(); // Input for the answer text
		System.out.println("Is the answer correct? (true/false):");
		String isCorrect = sc.nextLine();   // Input for whether the answer is correct

		return new Answers(questionId, answerText, isCorrect);
	}

	//Input Method for Leaderboard
	public static Leaderboard leaderboardInput() {
		// Prompt for Quiz ID
		System.out.print("Enter Quiz ID: ");
		int quizId = sc.nextInt();

		// Fetch the Quiz entity based on quizId (assuming this service method exists)
		Quiz quiz = quizService.getQuizById(quizId);
		if (quiz == null) {
			System.out.println("Quiz not found with ID: " + quizId);
			return null;
		}

		// Prompt for User ID
		System.out.print("Enter User ID: ");
		String userId = Integer.toString(sc.nextInt());

		// Fetch the User entity based on userId (assuming this service method exists)
		User user = userService.getUser(userId);
		if (user == null) {
			System.out.println("User not found with ID: " + userId);
			return null;
		}

		// Prompt for rank and score
		System.out.print("Enter your rank: ");
		int rank = sc.nextInt();

		System.out.print("Enter your score: ");
		int score = sc.nextInt();

		// Create and return a new Leaderboard object with the fetched Quiz and User
		Leaderboard leaderboardEntry = new Leaderboard(quiz, user, score, rank);

		System.out.println("Leaderboard entry added successfully!");
		System.out.println();

		return leaderboardEntry;
	}

	// Assuming you have a QuizDao class with a method to get Quiz by ID
	public static Quiz quizInputForQuestion() {
		System.out.println("Enter Quiz ID:");
		int quizId = sc.nextInt();  // Get the quiz ID from user input

		QuizDao quizDao = new QuizDaoImpl();  // Create an instance of QuizDao
		Quiz quiz = quizDao.getQuizById(quizId);  // Retrieve the Quiz object from the database

		if (quiz == null) {
			System.out.println("No quiz found with ID: " + quizId);
		}

		return quiz;  // Return the retrieved Quiz object
	}

	public static Question questionInput(List<Quiz> quizzes) {
	    // Automatically select the first quiz ID from the list (or modify to select based on your logic)
	    int quizId = quizzes.isEmpty() ? -1 : quizzes.get(0).getQuizId(); // Ensure to handle empty list
	    if (quizId == -1) {
	        System.out.println("No quizzes available. Cannot create a question.");
	        return null; // Or handle it as per your requirements
	    }

	    // Proceed to take question text and type as input
	    sc.nextLine(); // Consume newline
	    System.out.println("Automatically assigned Quiz ID: " + quizId);

	    System.out.println("Enter Question Text:");
	    String questionText = sc.nextLine(); // Input for the question text

	    System.out.println("Enter Question Type (e.g., MULTIPLE_CHOICE, TRUE_FALSE, SHORT_ANSWER):");
	    String questionTypeStr = sc.nextLine(); // Input for question type

	    // Validate and set question type
	    Question.QuestionType questionType;
	    try {
	        questionType = Question.QuestionType.valueOf(questionTypeStr.toUpperCase());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Invalid question type. Setting to default type MULTIPLE_CHOICE.");
	        questionType = Question.QuestionType.MULTIPLE_CHOICE; // Default or handle as necessary
	    }

	    // Create a new Question object (Quiz reference can be set as null since it's not directly needed)
	    Question newQuestion = new Question();
	    newQuestion.setQuestionText(questionText);
	    newQuestion.setQuestionType(questionType);

	    // Associate the quiz with the question (if needed, you can keep this or leave it commented out)
	    // newQuestion.setQuiz(new Quiz(quizId)); // If needed to set the quiz reference

	    return newQuestion; // Return the new Question object
	}



	// Input Method for Result
	public static Result resultInput() {
		// Fetching the User
		System.out.print("Enter User ID: ");
		String userId = Integer.toString(sc.nextInt());
		User user = userService.getUser(userId);
		if (user == null) {
			System.out.println("User not found with ID: " + userId);
			return null; // Exit if user not found
		}

		// Fetching the Quiz
		System.out.print("Enter Quiz ID: ");
		int quizId = sc.nextInt();
		Quiz quiz = quizService.getQuizById(quizId);
		if (quiz == null) {
			System.out.println("Quiz not found with ID: " + quizId);
			return null; // Exit if quiz not found
		}

		// Getting the score
		System.out.print("Enter Score: ");
		double score = sc.nextDouble();

		// Create a new Result object with quiz, user, and score
		Result result = new Result(quiz, user, score);

		// Ensure the result object is valid before returning
		if (result != null && result.getQuiz() != null && result.getUser() != null) {
			return result;
		} else {
			System.out.println("Error in creating the result. Please check input data.");
			return null;
		}
	}

	//User Operations method
	public static void userOperations() {
		while (true) {
			System.out.println("Press 1. Add User\nPress 2. Retrieve All Users\n"
					+ "Press 3. Update User\nPress 4. Delete User\nPress 5. To get back to the main menu");
			int input = sc.nextInt();
			sc.nextLine(); // Clear the buffer after nextInt()
			switch (input) {
			case 1:
				// Add User
				User user = userInputs();
				// Save the user in the database
				User savedUser = userService.createUser(user);
				// Display confirmation

				System.out.println("User has been saved successfully: " + savedUser);
				break;

			case 2:
				// Retrieve All Users
				List<User> users = userService.getAllUser();
				if (users.isEmpty()) {
					System.out.println("No users found.");
				} else {
					for (User usr : users) {
						System.out.println(usr);
					}
				}
				break;
			case 3:
				// Update User
				sc.nextLine(); // Clear buffer
				System.out.println("Enter User ID to update:");
				String userId = Integer.toString(sc.nextInt()); // Changed this to int for user ID consistency
				sc.nextLine(); // Clear buffer after reading integer
				User existingUser = userService.getUser(userId); // Fetch the user by ID
				if (existingUser != null) {
					// Fetch current username and other fields
					System.out.println("Current Username: " + existingUser.getUsername());
					System.out.println("Do you want to update the username? (yes/no)");
					String updateUsername = sc.nextLine();

					if (updateUsername.equalsIgnoreCase("yes")) {
						System.out.println("Enter new Username (must be unique):");
						String newUsername = sc.nextLine();
						existingUser.setUsername(newUsername); // Update username
					}

					System.out.println("Enter new Password:");
					String newPassword = sc.nextLine();
					existingUser.setPassword(newPassword); // Update password or other fields as required

					// Assuming userService.updateUser updates the user in the database
					User updatedInfo = userService.updateUser(userId,existingUser); // Call update service method
					System.out.println("User data has been updated successfully: " + updatedInfo);
				} else {
					System.out.println("User not found");
				}
				break;


			case 4:
				// Delete User
				System.out.println("Enter User Id to delete:");
				int deleteUsername = sc.nextInt();
				userService.deleteUser(deleteUsername);
				System.out.println("User data has been deleted Successfully");
				break;

			case 5:
				// Return to main menu (implement mainOps() as needed)
				return;

			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	// Result Operations method
	public static void resultOperations() {
		while (true) {
			System.out.println("Press 1. Add Result\nPress 2. Retrieve All Results\n"
					+ "Press 3. Update Result\nPress 4. Delete Result\nPress 5. To get back to the main menu");
			int input = sc.nextInt();

			switch (input) {
			case 1:
				// Add Result
				Result result = resultInput(); // Fetch user input for result
				if (result != null) {
					Result savedResult = resultService.createResult(result); // Save the result
					System.out.println("Result has been saved successfully: " + savedResult);
				} else {
					System.out.println("Failed to save the result. Check the input data.");
				}
				break;

			case 2:
				// Retrieve All Results
				List<Result> results = resultService.getAllResults();
				if (results.isEmpty()) {
					System.out.println("No results found.");
				} else {
					for (Result res : results) {
						System.out.println(res);
					}
				}
				break;

			case 3:
				// Update Result
				sc.nextLine(); // Clear buffer
				System.out.println("Enter Result ID to update:");
				int resultId = sc.nextInt();
				Result existingResult = resultService.getResultById(resultId);
				if (existingResult != null) {
					Result updatedResult = resultInput(); // Get new input for result
					if (updatedResult != null) {
						Result updatedInfo = resultService.updateResult(resultId, updatedResult); // Update result
						System.out.println("Result data has been updated successfully: " + updatedInfo);
					} else {
						System.out.println("Failed to update result. Invalid input data.");
					}
				} else {
					System.out.println("Result not found.");
				}
				break;

			case 4:
				// Delete Result
				System.out.println("Enter Result ID to delete:");
				int deleteResultId = sc.nextInt();
				resultService.deleteResult(deleteResultId);
				System.out.println("Result has been deleted successfully.");
				break;

			case 5:
				// Return to the main menu (implement mainOps() as needed)
				return;

			default:
				System.out.println("Invalid input. Please select a valid option.");
				break;
			}
		}
	}

	// Quiz Operations method
	public static void quizOperations() {
	    Scanner sc = new Scanner(System.in); // Initialize the scanner
	    while (true) {
	        System.out.println("Press 1. Add Quiz\nPress 2. Retrieve All Quizzes\n"
	                + "Press 3. Update Quiz\nPress 4. Delete Quiz\nPress 5. To get back to the main menu");
	        int input = sc.nextInt();
	        sc.nextLine(); // Clear the buffer

	        switch (input) {
	            case 1:
	                Quiz quiz = quizInputs();
	                if (quiz != null) {
	                    Quiz savedQuiz = quizService.createQuiz(quiz);
	                    System.out.println("Quiz has been saved successfully: " + savedQuiz);
	                } else {
	                    System.out.println("Failed to add quiz due to invalid input.");
	                }
	                break;

	            case 2:
	                List<Quiz> quizzes = quizService.getAllQuizzes();
	                if (quizzes.isEmpty()) {
	                    System.out.println("No quizzes available.");
	                } else {
	                    for (Quiz qz : quizzes) {
	                        System.out.println(qz);
	                    }
	                }
	                break;

	            case 3:
	                System.out.print("Enter Quiz ID to update: ");
	                int quizId = sc.nextInt();
	                sc.nextLine(); // Clear the buffer
	                Quiz existingQuiz = quizService.getQuizById(quizId);
	                if (existingQuiz != null) {
	                    Quiz updatedQuiz = quizInputs();
	                    if (updatedQuiz != null) {
	                        Quiz updatedInfo = quizService.updateQuiz(quizId, updatedQuiz);
	                        System.out.println("Quiz data has been updated successfully: " + updatedInfo);
	                    } else {
	                        System.out.println("Failed to update quiz due to invalid input.");
	                    }
	                } else {
	                    System.out.println("Quiz not found with ID: " + quizId);
	                }
	                break;

	            case 4:
	                System.out.print("Enter Quiz ID to delete: ");
	                int deleteQuizId = sc.nextInt();
	                String message = quizService.deleteQuiz(deleteQuizId);
	                System.out.println(message);
	                break;

	            case 5:
	                // Return to main menu (implement mainOps() as needed)
	                return;

	            default:
	                System.out.println("Invalid option. Please choose a valid operation.");
	        }
	    }
	}


	// Question Operations method
	public static void questionOperations() {
	    // Assuming you have a method to fetch all quizzes
	    List<Quiz> quizzes = quizService.getAllQuizzes(); // Get the list of available quizzes

	    while (true) {
	        System.out.println("Press 1. Add Question\nPress 2. Retrieve All Questions\n"
	                + "Press 3. Update Question\nPress 4. Delete Question\nPress 5. To get back to the main menu");
	        int input = sc.nextInt();

	        switch (input) {
	            case 1:
	                // Call the updated questionInput method with the list of quizzes
	                Question question = questionInput(quizzes);
	                if (question != null) { // Ensure the question is not null
	                    Question savedQuestion = questionService.createQuestion(question);
	                    System.out.println("Question has been saved successfully: " + savedQuestion);
	                }
	                break;

	            case 2:
	                List<Question> questions = questionService.getAllQuestions();
	                for (Question qst : questions) {
	                    System.out.println(qst);
	                }
	                break;

	            case 3:
	                sc.nextLine();
	                System.out.println("Enter Question ID to update:");
	                int questionId = sc.nextInt();
	                Question existingQuestion = questionService.getQuestionById(questionId);
	                if (existingQuestion != null) {
	                    // Call the updated questionInput method for the existing question
	                    Question updatedQuestion = questionInput(quizzes);
	                    if (updatedQuestion != null) { // Check for null before updating
	                        Question updatedInfo = questionService.updateQuestion(questionId, updatedQuestion);
	                        System.out.println("Question data has been updated successfully: " + updatedInfo);
	                    }
	                } else {
	                    System.out.println("Question not found");
	                }
	                break;

	            case 4:
	                System.out.println("Enter Question ID to delete:");
	                int deleteQuestionId = sc.nextInt();  // Input is already an int
	                String message = questionService.deleteQuestion(deleteQuestionId); // Pass it directly
	                System.out.println(message);
	                break;

	            case 5:
	                // Return to main menu (implement mainOps() as needed)
	                return;

	            default:
	                System.out.println("Invalid input. Please try again.");
	        }
	    }
	}

	// Leaderboard Operations method
	public static void leaderboardOperations() {
		while (true) {
			System.out.println("Press 1. Add Leaderboard Entry\nPress 2. Retrieve All Leaderboard Entries\n"
					+ "Press 3. Update Leaderboard Entry\nPress 4. Delete Leaderboard Entry\nPress 5. To get back to the main menu");
			int input = sc.nextInt();

			switch (input) {
			case 1:
				Leaderboard leaderboard = leaderboardInput();
				if (leaderboard != null) { // Ensure leaderboard is not null
					Leaderboard savedLeaderboard = leaderboardService.createLeaderboardEntry(leaderboard);
					System.out.println("Leaderboard entry has been saved successfully: " + savedLeaderboard);
				} else {
					System.out.println("Failed to create leaderboard entry. Please try again.");
				}
				break;

			case 2:
				List<Leaderboard> leaderboardEntries = leaderboardService.getAllLeaderboardEntries();
				System.out.println("Leaderboard Records:");
				for (Leaderboard lb : leaderboardEntries) {
					System.out.println(lb);
				}
				break;

			case 3:
				sc.nextLine();
				System.out.println("Enter Leaderboard ID to update:");
				int leaderboardId = sc.nextInt();
				Leaderboard existingLeaderboard = leaderboardService.getLeaderboardEntryById(leaderboardId);
				if (existingLeaderboard != null) {
					Leaderboard updatedLeaderboard = leaderboardInput();
					if (updatedLeaderboard != null) {
						updatedLeaderboard.setLeaderboardId(leaderboardId); // Keep the original ID
						Leaderboard updatedInfo = leaderboardService.updateLeaderboardEntry(leaderboardId, updatedLeaderboard);
						System.out.println("Leaderboard entry has been updated successfully: " + updatedInfo);
					}
				} else {
					System.out.println("Leaderboard entry not found");
				}
				break;

			case 4:
				System.out.println("Enter Leaderboard ID to delete:");
				int deleteLeaderboardId = sc.nextInt();
				String message = leaderboardService.deleteLeaderboardEntry(deleteLeaderboardId);
				System.out.println(message);
				break;

			case 5:
				// Return to main menu
				return;

			default:
				System.out.println("Invalid input. Please try again.");
			}
		}
	}



	// Category Operations method
	public static void categoriesOperations() {
		while (true) {
			System.out.println("Press 1. Add Category\nPress 2. Retrieve All Categories\n"
					+ "Press 3. Update Category\nPress 4. Delete Category\nPress 5. To get back to the main menu");
			int input = sc.nextInt();
			sc.nextLine(); // Consume newline
			switch (input) {
			case 1:
				Categories category = categoryInput();
				Categories savedCategory = categoriesService.createCategory(category);
				if (savedCategory != null) {
					System.out.println("Category has been saved successfully: " + savedCategory);
				} else {
					System.out.println("Failed to save the category.");
				}
				break;

			case 2:
				List<Categories> categories = categoriesService.getAllCategories();
				for (Categories cat : categories) {
					System.out.println(cat);
				}
				break;

			case 3:
				System.out.print("Enter category ID to update: ");
				int categoryId = sc.nextInt();
				sc.nextLine(); // Consume newline left-over

				System.out.print("Enter new category name: ");
				String name = sc.nextLine();

				System.out.print("Enter new category description: ");
				String description = sc.nextLine();

				// Create an updated Categories object
				Categories updatedCategory = new Categories();
				updatedCategory.setName(name);
				updatedCategory.setDescription(description);

				// Call the update method
				Categories result = categoriesService.updateCategory(categoryId, updatedCategory);

				// Output the result
				if (result != null) {
					System.out.println("Category updated successfully: " + result);
				} else {
					System.out.println("Failed to update category or category not found.");
				}
				break;

			case 4:
				System.out.println("Enter Category ID to delete:");
				int deleteCategoryId = sc.nextInt();
				String message = categoriesService.deleteCategory(deleteCategoryId);
				System.out.println(message);
				break;

			case 5:
				// Return to main menu
				return;
			default:
				System.out.println("Invalid choice. Please try again.");

			}
		}
	}


	// Answer Operations method
	public static void answerOperations() {
		while (true) {
			System.out.println("Press 1. Add Answer\nPress 2. Retrieve All Answers\n"
					+ "Press 3. Update Answer\nPress 4. Delete Answer\nPress 5. To get back to the main menu");
			int input = sc.nextInt();

			switch (input) {
			case 1:
				Answers answer = answerInput();
				Answers savedAnswer = answerService.createAnswer(answer);
				System.out.println("Answer has been saved successfully: " + savedAnswer);
				break;

			case 2:
				List<Answers> answers = answerService.getAllAnswers();
				for (Answers ans : answers) {
					System.out.println(ans);
				}
				break;

			case 3:
				sc.nextLine();
				System.out.println("Enter Answer ID to update:");
				int answerId = sc.nextInt();
				Answers existingAnswer = answerService.getAnswerById(answerId);
				if (existingAnswer != null) {
					Answers updatedAnswer = answerInput();
					Answers updatedInfo = answerService.updateAnswer(answerId, updatedAnswer);
					System.out.println("Answer data has been updated successfully: " + updatedInfo);
				} else {
					System.out.println("Answer not found");
				}
				break;

			case 4:
				System.out.println("Enter Answer ID to delete:");
				int deleteAnswerId = sc.nextInt();
				String message = answerService.deleteAnswer(deleteAnswerId);
				System.out.println(message);
				break;

			case 5:
				// Return to main menu (implement mainOps() as needed)
				return;
			}
		}
	}


}
