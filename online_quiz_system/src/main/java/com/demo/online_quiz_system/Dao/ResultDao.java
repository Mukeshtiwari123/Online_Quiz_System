package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Result;

public interface ResultDao {
    Result createResult(Result result);	
    List<Result> getAllResults();
    Result getResultById(int resultId);
    Result updateResult(int resultId, Result updatedResult);
    String deleteResult(int resultId);
}


