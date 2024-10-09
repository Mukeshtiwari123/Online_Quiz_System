package com.demo.online_quiz_system.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import com.demo.online_quiz_system.Dao.LeaderboardDao;
import com.demo.online_quiz_system.entities.Leaderboard;

public class LeaderboardDaoImpl implements LeaderboardDao {
    private List<Leaderboard> leaderboardEntries = new ArrayList<>();

    @Override
    public Leaderboard createLeaderboardEntry(Leaderboard leaderboard) {
        leaderboardEntries.add(leaderboard);
        return leaderboard;
    }

    @Override
    public List<Leaderboard> getAllLeaderboardEntries() {
        return new ArrayList<>(leaderboardEntries);
    }

    @Override
    public Leaderboard getLeaderboardEntryById(int leaderboardId) {
        return leaderboardEntries.stream().filter(entry -> entry.getLeaderboardId() == leaderboardId).findFirst().orElse(null);
    }

    @Override
    public Leaderboard updateLeaderboardEntry(int leaderboardId, Leaderboard updatedLeaderboard) {
        for (int i = 0; i < leaderboardEntries.size(); i++) {
            if (leaderboardEntries.get(i).getLeaderboardId() == leaderboardId) {
                leaderboardEntries.set(i, updatedLeaderboard);
                return updatedLeaderboard;
            }
        }
        return null;
    }

    @Override
    public String deleteLeaderboardEntry(int leaderboardId) {
        leaderboardEntries.removeIf(entry -> entry.getLeaderboardId() == leaderboardId);
        return "Leaderboard entry deleted successfully!";
    }
}
