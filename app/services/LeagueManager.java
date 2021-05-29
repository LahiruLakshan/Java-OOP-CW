package services;

import entities.SportsClub;

public interface LeagueManager {
	void addFootballClubToPremierLeague(SportsClub footballClub);
	void deleteAClub(String clubName);
	void displayVariousStatistics(String clubName);
	void displayPremierLeagueTable();
	void addPlayedMatchDetails(String date, String homeTeam, int homeTeamScore, String awayTeamName, int awayTeamGoals);
}
