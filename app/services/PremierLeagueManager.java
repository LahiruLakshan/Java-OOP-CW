package services;

import entities.FootballClub;
import entities.SportsClub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PremierLeagueManager implements LeagueManager{

	public static List<SportsClub> premierLeagueClubsList = new ArrayList<>();
	public static List<SportsClub> listOfMatches = new ArrayList<>();
	public static List<SportsClub> listOfRandomMatches = new ArrayList<>();



	@Override
	public void addFootballClubToPremierLeague(SportsClub footballClub) {
		if (premierLeagueClubsList.size() < 20){
			premierLeagueClubsList.add(footballClub);
			System.out.println("* Data added successfully!");
			System.out.println("Number of registered clubs			   : " + premierLeagueClubsList.size());
			System.out.println("Remaining space for clubs registration : " + (20 - premierLeagueClubsList.size()));

			premierLeagueClubsList.sort(Collections.reverseOrder());
			for (SportsClub club:premierLeagueClubsList){
				SportsClub clubPosition = new FootballClub(premierLeagueClubsList.indexOf(club) + 1,club.getNameOfTheClub(),club.getLocation(),club.getStadium(),club.getNumOfMembers(),
						((FootballClub) club).getMatchDate(),((FootballClub) club).getNumOfPlayedMatches(),((FootballClub) club).getWon(),((FootballClub) club).getDrawn(),
						((FootballClub) club).getLost(), ((FootballClub) club).getNumOfGoalsScored(), ((FootballClub) club).getNumOfGoalsReceived(),
						((FootballClub) club).getNumOfGoalsDifference(), ((FootballClub) club).getNumOfPoints());
				premierLeagueClubsList.set(premierLeagueClubsList.indexOf(club),clubPosition);
			}
		}else {
			System.out.println("Can't create a club for the Premier League!");
		}
	}

	@Override
	public void deleteAClub(String clubName) {		// selected club delete
		if (!clubName.equalsIgnoreCase("NotFound")){
			for (SportsClub club : premierLeagueClubsList) {
				if (club.getNameOfTheClub().equalsIgnoreCase(clubName)) {
					premierLeagueClubsList.remove(club);
					break;
				}
			}
			System.out.println(clubName+" is deleted successfully !");
		}else {
			System.out.println(clubName + " club is not exist ");
		}

	}

	@Override
	public void displayVariousStatistics(String clubName) {		// selected club details display in this method
		allClubsRanksUpdate();
		System.out.println("----------------------------------------------- " + clubName + " Club Details -----------------------------------------------\n");
		String tableFormat = "| %-8s | %-25s | %-15s | %-6s | %-3s | %-5s | %-4s | %-4s | %-4s | %-4s | %-6s |%n";
		System.out.println("\tGF = \"Goals For\"\n\tGA = \"Goals Against\"\n\tGD = \"Goals Difference\"");
		System.out.format("+----------+---------------------------+-----------------+--------+-----+-------+------+------+------+------+--------+	%n");
		System.out.format("| Position | Club Name                 | Location        | Played | Won | Drawn | Lost | GF   | GA   | GD   | Points |	%n");
		System.out.format("+----------+---------------------------+-----------------+--------+-----+-------+------+------+------+------+--------+	%n");

		boolean check = false;
		premierLeagueClubsList.sort(Collections.reverseOrder());
		for (SportsClub sportsClub: premierLeagueClubsList) {
			if (sportsClub.getNameOfTheClub().equalsIgnoreCase(clubName)){
				String GF = ((FootballClub) sportsClub).getNumOfGoalsDifference() > 0 ? "+"+((FootballClub) sportsClub).getNumOfGoalsDifference(): String.valueOf(((FootballClub) sportsClub).getNumOfGoalsDifference());
				check = ((FootballClub) sportsClub).getNumOfPlayedMatches() > 0;
				System.out.format(tableFormat,((FootballClub) sportsClub).getPosition(), sportsClub.getNameOfTheClub(), sportsClub.getLocation(), ((FootballClub) sportsClub).getNumOfPlayedMatches(),
						((FootballClub) sportsClub).getWon(), ((FootballClub) sportsClub).getDrawn(), ((FootballClub) sportsClub).getLost(),
						((FootballClub) sportsClub).getNumOfGoalsScored(), ((FootballClub) sportsClub).getNumOfGoalsReceived(), GF, ((FootballClub) sportsClub).getNumOfPoints());
			}
		}
		System.out.format("+----------+---------------------------+-----------------+--------+-----+-------+------+------+------+------+--------+%n");
		int count = 0;
		int homeTeamScore;
		int awayTeamScore;
		String homeTeamResult;
		String awayTeamResult;
		if (check){
			System.out.println("----------------------------------------------- Matches played by "+clubName+" Club -----------------------------------------------\n");
			String matchFormat = "|   %-6s | %-10s | %-25s | %-25s | %-7s | %-25s |%n";
			System.out.format("+----------+------------+---------------------------+---------------------------+---------+---------------------------+%n");
			System.out.format("| Match No | Date       | Stadium                   | Home Team                 | Result  | Away Team                 |%n");
			System.out.format("+----------+------------+---------------------------+---------------------------+---------+---------------------------+%n");
			for (SportsClub sportsClub : listOfMatches){
				if (sportsClub.getNameOfTheClub().equalsIgnoreCase(clubName) || ((FootballClub) sportsClub).getOtherTeamName().equalsIgnoreCase(clubName)){
					count++;
					homeTeamScore = ((FootballClub) sportsClub).getNumOfGoalsScored();
					awayTeamScore = ((FootballClub) sportsClub).getNumOfGoalsReceived();
					if (homeTeamScore > awayTeamScore) {
						homeTeamResult = " (WON)";
						awayTeamResult = " (LOST)";
					} else if (homeTeamScore == awayTeamScore) {
						homeTeamResult = " (DRAWN)";
						awayTeamResult = " (DRAWN)";
					} else {
						homeTeamResult = " (LOST)";
						awayTeamResult = " (WON)";
					}

					System.out.format(matchFormat, count, ((FootballClub) sportsClub).getMatchDate(), ((FootballClub) sportsClub).getStadium(), sportsClub.getNameOfTheClub() + homeTeamResult, String.format("%02d", homeTeamScore) + " : " + String.format("%02d", awayTeamScore), ((FootballClub) sportsClub).getOtherTeamName() + awayTeamResult);

				}
			}
			System.out.format("+----------+------------+---------------------------+---------------------------+---------+---------------------------+%n");
		}
	}

	@Override
	public void displayPremierLeagueTable() {		// position table and matches table display in this method
		allClubsRanksUpdate();
		if (!premierLeagueClubsList.isEmpty()){
			System.out.println("\tGF = \"Goals For\"\n\tGA = \"Goals Against\"\n\tGD = \"Goals Difference\"");
			String tableFormat = "| %-8s | %-25s | %-15s | %-6s | %-3s | %-5s | %-4s | %-4s | %-4s | %-4s | %-6s |%n";
			System.out.format("+----------+---------------------------+-----------------+--------+-----+-------+------+------+------+------+--------+	%n");
			System.out.format("| Position | Club Name                 | Location        | Played | Won | Drawn | Lost | GF   | GA   | GD   | Points |	%n");
			System.out.format("+----------+---------------------------+-----------------+--------+-----+-------+------+------+------+------+--------+	%n");

			premierLeagueClubsList.sort(Collections.reverseOrder());
			for (SportsClub sportsClub : premierLeagueClubsList) {
				String GF = ((FootballClub) sportsClub).getNumOfGoalsDifference() > 0 ? "+" + ((FootballClub) sportsClub).getNumOfGoalsDifference() : String.valueOf(((FootballClub) sportsClub).getNumOfGoalsDifference());

				System.out.format(tableFormat, ((FootballClub) sportsClub).getPosition(), sportsClub.getNameOfTheClub(), sportsClub.getLocation(),
						((FootballClub) sportsClub).getNumOfPlayedMatches(), ((FootballClub) sportsClub).getWon(), ((FootballClub) sportsClub).getDrawn(), ((FootballClub) sportsClub).getLost(),
						((FootballClub) sportsClub).getNumOfGoalsScored(), ((FootballClub) sportsClub).getNumOfGoalsReceived(), GF, ((FootballClub) sportsClub).getNumOfPoints());
			}
			System.out.format("+----------+---------------------------+-----------------+--------+-----+-------+------+------+------+------+--------+%n");

			if (!listOfMatches.isEmpty()) {
				int homeTeamScore;
				int awayTeamScore;
				String homeTeamResult;
				String awayTeamResult;

				System.out.println("----------------------------------------------- Details of all matches played -----------------------------------------------\n");
				String matchFormat = "|   %-6s | %-10s | %-25s | %-25s | %-7s | %-25s |%n";
				System.out.format("+----------+------------+---------------------------+---------------------------+---------+---------------------------+%n");
				System.out.format("| Match No | Date       | Stadium                   | Home Team                 | Result  | Away Team                 |%n");
				System.out.format("+----------+------------+---------------------------+---------------------------+---------+---------------------------+%n");
				for (SportsClub sportsClub : listOfMatches) {
					homeTeamScore = ((FootballClub) sportsClub).getNumOfGoalsScored();
					awayTeamScore = ((FootballClub) sportsClub).getNumOfGoalsReceived();
					if (homeTeamScore > awayTeamScore) {
						homeTeamResult = " (WON)";
						awayTeamResult = " (LOST)";
					} else if (homeTeamScore == awayTeamScore) {
						homeTeamResult = " (DRAWN)";
						awayTeamResult = " (DRAWN)";
					} else {
						homeTeamResult = " (LOST)";
						awayTeamResult = " (WON)";
					}

					System.out.format(matchFormat, listOfMatches.indexOf(sportsClub) + 1, ((FootballClub) sportsClub).getMatchDate(), ((FootballClub) sportsClub).getStadium(), sportsClub.getNameOfTheClub() + homeTeamResult, String.format("%02d", homeTeamScore) + " : " + String.format("%02d", awayTeamScore), ((FootballClub) sportsClub).getOtherTeamName() + awayTeamResult);

				}

				System.out.format("+----------+------------+---------------------------+---------------------------+---------+---------------------------+%n");
			}
		}else {
			System.out.println("Not clubs in register");
		}
	}

	@Override
	public void addPlayedMatchDetails(String date, String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) {
		SportsClub clubUpdate;
		String stadium = null;

		for (SportsClub sportsClub:premierLeagueClubsList){
			if (sportsClub.getNameOfTheClub().equalsIgnoreCase(homeTeamName) || sportsClub.getNameOfTheClub().equalsIgnoreCase(awayTeamName)){
				int match = ((FootballClub) sportsClub).getNumOfPlayedMatches() + 1;
				int won = ((FootballClub) sportsClub).getWon();
				int drawn = ((FootballClub) sportsClub).getDrawn();
				int lost = ((FootballClub) sportsClub).getLost();
				int points = ((FootballClub) sportsClub).getNumOfPoints();

				if (sportsClub.getNameOfTheClub().contains(homeTeamName)){
					stadium = sportsClub.getStadium();
					int goalsScore = ((FootballClub) sportsClub).getNumOfGoalsScored() + homeTeamScore;
					int goalsReceived = ((FootballClub) sportsClub).getNumOfGoalsReceived() + awayTeamScore;
					int goalsDifference = goalsScore - goalsReceived;
					if (homeTeamScore > awayTeamScore) {
						won += 1;
						points += 3;
					} else if (homeTeamScore == awayTeamScore) {
						drawn += 1;
						points += 1;
					} else {
						lost += 1;
					}
					clubUpdate = new FootballClub(premierLeagueClubsList.indexOf(sportsClub),homeTeamName,sportsClub.getLocation(),sportsClub.getStadium(),sportsClub.getNumOfMembers(),date,match,won,drawn,lost,goalsScore,goalsReceived,goalsDifference,points);
					premierLeagueClubsList.set(premierLeagueClubsList.indexOf(sportsClub),clubUpdate);
				}else if (sportsClub.getNameOfTheClub().contains(awayTeamName)){
					int goalsScore = ((FootballClub) sportsClub).getNumOfGoalsScored() + awayTeamScore;
					int goalsReceived = ((FootballClub) sportsClub).getNumOfGoalsReceived() + homeTeamScore;
					int goalsDifference = goalsScore - goalsReceived;

					if (awayTeamScore > homeTeamScore) {
						won += 1;
						points += 3;
					} else if (homeTeamScore == awayTeamScore) {
						drawn += 1;
						points += 1;
					} else {
						lost += 1;
					}
					clubUpdate = new FootballClub(premierLeagueClubsList.indexOf(sportsClub),awayTeamName,sportsClub.getLocation(),sportsClub.getStadium(),sportsClub.getNumOfMembers(),date,match,won,drawn,lost,goalsScore,goalsReceived,goalsDifference,points);
					premierLeagueClubsList.set(premierLeagueClubsList.indexOf(sportsClub),clubUpdate);
				}
			}
		}
		clubUpdate = new FootballClub(date,stadium,homeTeamName,homeTeamScore,awayTeamName,awayTeamScore);
		listOfMatches.add(clubUpdate);
		System.out.println("Match Data added successful!");
	}
	public static void allClubsRanksUpdate(){		// all clubs position update in this method
		premierLeagueClubsList.sort(Collections.reverseOrder());
		for (SportsClub club:premierLeagueClubsList){
			SportsClub clubPosition = new FootballClub(premierLeagueClubsList.indexOf(club) + 1,club.getNameOfTheClub(), club.getLocation(),club.getStadium(),club.getNumOfMembers(),((FootballClub) club).getMatchDate(),((FootballClub) club).getNumOfPlayedMatches(), ((FootballClub) club).getWon(),((FootballClub) club).getDrawn(), ((FootballClub) club).getLost(), ((FootballClub) club).getNumOfGoalsScored(), ((FootballClub) club).getNumOfGoalsReceived(), ((FootballClub) club).getNumOfGoalsDifference(), ((FootballClub) club).getNumOfPoints());
			premierLeagueClubsList.set(premierLeagueClubsList.indexOf(club),clubPosition);
		}
	}
}
