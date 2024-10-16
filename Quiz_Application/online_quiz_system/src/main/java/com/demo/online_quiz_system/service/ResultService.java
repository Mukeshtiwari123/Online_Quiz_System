package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Result;
import java.util.List;

public interface ResultService {

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

//	public Result createResult(Result result);
}

