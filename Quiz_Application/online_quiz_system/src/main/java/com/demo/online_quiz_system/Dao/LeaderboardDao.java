package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Leaderboard;

public interface LeaderboardDao {
    Leaderboard createLeaderboardEntry(Leaderboard leaderboard);	
    List<Leaderboard> getAllLeaderboardEntries();
    Leaderboard getLeaderboardEntryById(int leaderboardId);
    Leaderboard updateLeaderboardEntry(int leaderboardId, Leaderboard updatedLeaderboard);
    String deleteLeaderboardEntry(int leaderboardId);
}
