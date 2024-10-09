package com.demo.online_quiz_system.serviceImpl;

import java.util.List;
import com.demo.online_quiz_system.Dao.ResultDao; // Assuming you have ResultDao
import com.demo.online_quiz_system.DaoImpl.ResultDaoImpl; // Assuming you have ResultDaoImpl
import com.demo.online_quiz_system.entities.Result;
import com.demo.online_quiz_system.service.ResultService;

public class ResultServiceImpl implements ResultService {

    // Dependency on ResultDao
    ResultDao resultDao = new ResultDaoImpl();

    @Override
    public Result createResult(Result result) {
        // Invoke ResultDaoImpl method to save result object
        return resultDao.createResult(result);
    }

    @Override
    public List<Result> getAllResults() {
        // Invoke ResultDaoImpl method to retrieve all results
        return resultDao.getAllResults();
    }

    @Override
    public Result getResultById(int resultId) {
        // Invoke ResultDaoImpl method to retrieve a specific result by resultId
        return resultDao.getResultById(resultId);
    }

    @Override
    public Result updateResult(int resultId, Result updatedResult) {
        // Invoke ResultDaoImpl method to update the result's details
        return resultDao.updateResult(resultId, updatedResult);
    }

    @Override
    public void deleteResult(int resultId) {
        // Invoke ResultDaoImpl method to delete a result by resultId
        resultDao.deleteResult(resultId);
    }
}
