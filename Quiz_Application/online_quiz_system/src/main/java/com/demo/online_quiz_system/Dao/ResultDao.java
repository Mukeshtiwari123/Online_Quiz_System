package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Result;

public interface ResultDao {
	// Save a result
    public Result saveResult(Result result);

    // Fetch a result by ID
    public Result getResultById(int id);

    // Fetch all results
    public List<Result> getAllResults();

    // Fetch results by user ID
    public List<Result> getResultsByUserId(int userId);

    // Update a result
    public void updateResult(Result result);

    // Delete a result by ID
    public void deleteResult(int id);
}


