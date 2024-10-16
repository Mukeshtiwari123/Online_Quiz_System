package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Leaderboard;
import java.util.List;

public interface LeaderboardService {
    Leaderboard createLeaderboardEntry(Leaderboard leaderboard);
    List<Leaderboard> getAllLeaderboardEntries();
    Leaderboard getLeaderboardEntryById(int leaderboardId);
    Leaderboard updateLeaderboardEntry(int leaderboardId, Leaderboard updatedLeaderboard);
    String deleteLeaderboardEntry(int leaderboardId);
}
