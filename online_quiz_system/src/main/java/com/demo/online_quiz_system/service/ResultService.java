package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Result;
import java.util.List;

public interface ResultService {
    Result createResult(Result result);
    List<Result> getAllResults();
    Result getResultById(int resultId);
    Result updateResult(int resultId, Result updatedResult);
    void deleteResult(int resultId);
}
