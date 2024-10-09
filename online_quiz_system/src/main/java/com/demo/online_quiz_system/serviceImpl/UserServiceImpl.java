package com.demo.online_quiz_system.serviceImpl;

import java.util.List;

import com.demo.online_quiz_system.Dao.UserDao;
import com.demo.online_quiz_system.DaoImpl.UserDaoImpl;
import com.demo.online_quiz_system.entities.User;
import com.demo.online_quiz_system.service.UserService;

public class UserServiceImpl implements UserService {

    // Dependency on UserDao
    UserDao userDao = new UserDaoImpl();

    @Override
    public User createUser(User user) {
        // Invoke UserDaoImpl method to save user object
        return userDao.createUser(user);
    }

    @Override
    public List<User> getAllUser() {
        // Invoke UserDaoImpl method to retrieve all users
        return userDao.getAllStudents();
    }

    @Override
    public User getUser(String userID) {
        // Invoke UserDaoImpl method to retrieve a specific user by userID
        return userDao.getUser(userID);
    }

    @Override
    public User updateUser(String userID, User updatedUser) {
        // Invoke UserDaoImpl method to update the user's details
        return userDao.updateUser(userID, updatedUser);
    }

    @Override
    public void deleteUser(int userId) {
        try {
            userDao.deleteUser(userId); // Assuming UserDao has deleteUserById(int userId)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean isUsernameExists(String username) {
        return userDao.isUsernameExists(username); // Delegate to the DAO
    }
}
