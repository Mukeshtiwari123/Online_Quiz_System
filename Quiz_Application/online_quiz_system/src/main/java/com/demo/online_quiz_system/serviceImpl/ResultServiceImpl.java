package com.demo.online_quiz_system.serviceImpl;

import java.util.List;
import com.demo.online_quiz_system.Dao.ResultDao; // Assuming you have ResultDao
import com.demo.online_quiz_system.DaoImpl.ResultDaoImpl; // Assuming you have ResultDaoImpl
import com.demo.online_quiz_system.entities.Result;
import com.demo.online_quiz_system.service.ResultService;

public class ResultServiceImpl implements ResultService {

	// Dependency on ResultDao
	//ResultDao resultDao = new ResultDaoImpl();
	private ResultDao resultDao = new ResultDaoImpl();

	@Override
	public Result saveResult(Result result) {
		return resultDao.saveResult(result);
	}

	@Override
	public Result getResultById(int id) {
		return resultDao.getResultById(id);
	}

	@Override
	public List<Result> getAllResults() {
		return resultDao.getAllResults();
	}

	@Override
	public List<Result> getResultsByUserId(int userId) {
		return resultDao.getResultsByUserId(userId);
	}

	@Override
	public void updateResult(Result result) {
		resultDao.updateResult(result);
	}

	@Override
	public void deleteResult(int id) {
		resultDao.deleteResult(id);
	}
}
