package com.demo.online_quiz_system;

import java.util.Scanner;
import com.demo.online_quiz_system.AllOparations;
import static com.demo.online_quiz_system.AllOparations.userInputs;
import static com.demo.online_quiz_system.AllOparations.quizInputs;


public class MainOperation {

	static Scanner sc=new Scanner(System.in);

	public static void mainOps()       //method
	{
		System.out.println("	Welcome..!!!	\n");
		while(true) {
			System.out.println("Press 1: Answers Details\n"
					+ "Press 2: Categories Deatils \n"
				//	+ "Press 3: Show Leaderboad\n"
					+ "Press 3: Question Details \n"
					+ "Press 4: Quiz Details\n"
					+"Press 5: Result Details\n"
					+"Press 6: User Details\n"
					+ "Press 7: Quit");
			int input=sc.nextInt();

			switch(input)
			{
			case 1:
				AllOparations.answerOperations();
				System.out.println("=======================================");
				break;
			case 2:
				AllOparations.categoriesOperations();
				System.out.println("=======================================");
				break;

//			case 3:
//				AllOparations.leaderboardOperations();
//				System.out.println("=======================================");
//				break;
				
			case 3:
				AllOparations.questionOperations();
				System.out.println("=======================================");
				break;

			case 4:
				AllOparations.quizOperations();
				System.out.println("=======================================");
				break;
			case 5:
				AllOparations.resultOperations();
				System.out.println("=======================================");
				break;
			case 6:
				AllOparations.userOperations();
				System.out.println("=======================================");
				break;
				
			case 7:
				System.out.println("Exiting the application.");
				System.exit(0);

			default:
				System.out.println("Invalid choice, please try again.");
			}
		}
	}

	public static void main(String[] args) 
	{
		mainOps();
	}

}