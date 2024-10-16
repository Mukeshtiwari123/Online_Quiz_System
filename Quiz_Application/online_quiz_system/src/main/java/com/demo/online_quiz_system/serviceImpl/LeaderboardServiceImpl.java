package com.demo.online_quiz_system.serviceImpl;

import java.util.List;
import com.demo.online_quiz_system.Dao.LeaderboardDao; // Assuming you have LeaderboardDao
import com.demo.online_quiz_system.DaoImpl.LeaderboardDaoImpl; // Assuming you have LeaderboardDaoImpl
import com.demo.online_quiz_system.entities.Leaderboard;
import com.demo.online_quiz_system.service.LeaderboardService;

public class LeaderboardServiceImpl implements LeaderboardService {

    // Dependency on LeaderboardDao
    LeaderboardDao leaderboardDao = new LeaderboardDaoImpl();

    @Override
    public Leaderboard createLeaderboardEntry(Leaderboard leaderboard) {
        // Invoke LeaderboardDaoImpl method to save leaderboard object
        return leaderboardDao.createLeaderboardEntry(leaderboard);
    }

    @Override
    public List<Leaderboard> getAllLeaderboardEntries() {
        // Invoke LeaderboardDaoImpl method to retrieve all leaderboard entries
        return leaderboardDao.getAllLeaderboardEntries();
    }

    @Override
    public Leaderboard getLeaderboardEntryById(int leaderboardId) {
        // Invoke LeaderboardDaoImpl method to retrieve a specific leaderboard entry by leaderboardId
        return leaderboardDao.getLeaderboardEntryById(leaderboardId);
    }

    @Override
    public Leaderboard updateLeaderboardEntry(int leaderboardId, Leaderboard updatedLeaderboard) {
        // Invoke LeaderboardDaoImpl method to update the leaderboard entry's details
        return leaderboardDao.updateLeaderboardEntry(leaderboardId, updatedLeaderboard);
    }

    @Override
    public String deleteLeaderboardEntry(int leaderboardId) {
        // Invoke LeaderboardDaoImpl method to delete a leaderboard entry by leaderboardId
        return leaderboardDao.deleteLeaderboardEntry(leaderboardId);
    }
}
