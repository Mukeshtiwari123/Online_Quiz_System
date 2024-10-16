package com.demo.online_quiz_system.service;

import java.util.List;

import com.demo.online_quiz_system.entities.User;

public interface UserService {
	User createUser(User user);	
	List<User> getAllUser();
	User getUser(String studentID);
	User updateUser(String UserID,User updatedUser);
	void deleteUser(int userId); 
	boolean isUsernameExists(String username);

}
