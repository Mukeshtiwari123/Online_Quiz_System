package com.demo.online_quiz_system.Dao;

import java.util.List;

import com.demo.online_quiz_system.entities.User;

public interface UserDao {

	User createUser(User user);	
	List<User> getAllStudents();
	User getUser(String UserID);//for single student
	User updateUser(String UserID,User updatedUser);
	void deleteUser(int UserID);
	boolean isUsernameExists(String username);

}
