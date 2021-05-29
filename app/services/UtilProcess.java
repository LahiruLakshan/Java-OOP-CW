package services;

import entities.FootballClub;
import entities.SportsClub;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UtilProcess {

	public static List<SportsClub> premierLeagueClubsListUpdate = new ArrayList<>();	// ranking table array
	public static List<SportsClub> listOfAllMatchesUpdate = new ArrayList<>();		// all matches data store in this arraylist
	public static List<SportsClub> listOfRandomMatchesUpdate = new ArrayList<>();	// random matches data store in this arraylist

	public static void loadData(){		// load file
		try{
			FileInputStream fileInputStream = new FileInputStream("public/Resources/premierLeagueClubsList.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			premierLeagueClubsListUpdate = (ArrayList) objectInputStream.readObject();
			listOfAllMatchesUpdate = (ArrayList) objectInputStream.readObject();
			listOfRandomMatchesUpdate = (ArrayList) objectInputStream.readObject();

			System.out.println("Data loaded successful!");
			objectInputStream.close();
			fileInputStream.close();
		}catch (IOException | ClassNotFoundException ex){
			System.out.println("Not found data");
		}
	}

	public static void saveData() {		// save file
		try{
			FileOutputStream fileOutputStream = new FileOutputStream("public/Resources/premierLeagueClubsList.txt");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(premierLeagueClubsListUpdate);
			objectOutputStream.writeObject(listOfAllMatchesUpdate);
			objectOutputStream.writeObject(listOfRandomMatchesUpdate);

			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
			System.out.println("Data saving is successful!");
		}catch (IOException i){
			i.printStackTrace();
		}
	}

	public static void pointSort(){		//points sort method
		loadData();
		premierLeagueClubsListUpdate.sort(Collections.reverseOrder());
	}

	public static void goalsScoredSort(){	//goals sort method
		loadData();
		premierLeagueClubsListUpdate.sort(new GoalScoreCompare());
	}

	public static void winsSort(){	// wins sort method
		loadData();
		premierLeagueClubsListUpdate.sort(new WinsCompare());
	}

	public static void dateSort(){	//date sort method
		loadData();
		listOfAllMatchesUpdate.sort(new DateCompare());
	}

	public static void dateSortRandomTable(){	// random table matches date sort
		loadData();
		listOfRandomMatchesUpdate.sort(new DateCompare());
	}

	public static void randomMatch(){		// random play match method
		loadData();
		SportsClub homeTeam;
		SportsClub awayTeam;
		boolean duplicateCheck = false;
		Random rand = new Random();
		int rankedArraySize = premierLeagueClubsListUpdate.size();
		if ((rankedArraySize - 1)*rankedArraySize > listOfAllMatchesUpdate.size()){	// each team can play only two rounds of matches
			do {
				Random randIndex = new Random();
				homeTeam = premierLeagueClubsListUpdate.get(randIndex.nextInt(premierLeagueClubsListUpdate.size()));
				awayTeam = premierLeagueClubsListUpdate.get(randIndex.nextInt(premierLeagueClubsListUpdate.size()));

				for (SportsClub club: listOfAllMatchesUpdate){		// validation - check weather the same name has selected
					if (club.getNameOfTheClub().equalsIgnoreCase(homeTeam.getNameOfTheClub())
							&& ((FootballClub) club).getOtherTeamName().equalsIgnoreCase(awayTeam.getNameOfTheClub())
							|| homeTeam.getNameOfTheClub().equalsIgnoreCase(awayTeam.getNameOfTheClub())){
						duplicateCheck = true;
						break;
					}else {
						duplicateCheck = false;
					}
				}
			} while (duplicateCheck);
			System.out.println(homeTeam.getNameOfTheClub() + " & " + awayTeam.getNameOfTheClub());
			int homeRandScore = rand.nextInt(15);		// home team random score
			int awayRandScore = rand.nextInt(15);		// away team random score

			LocalDate localDateStart = LocalDate.of(2020, 7, 1);
			long start = localDateStart.toEpochDay();

			LocalDate localDateEnd = LocalDate.of(2021, 5, 1);
			long end = localDateEnd.toEpochDay();

			long randomDate = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();		// random match date
			addPlayedMatchDetails(String.valueOf(LocalDate.ofEpochDay(randomDate)),homeTeam.getNameOfTheClub(),homeRandScore, awayTeam.getNameOfTheClub(),awayRandScore);
			saveData();
		}else if (rankedArraySize < 2){
			System.out.println("\t~ There must be at least 2 teams to play one match!");
		}else {
			System.out.println("\t~ Season End!!");
		}
	}
						// a random match details calculate and club data update
	public static void addPlayedMatchDetails(String date, String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) {
		SportsClub clubUpdate;
		String stadium = null;

		for (SportsClub sportsClub:premierLeagueClubsListUpdate){
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
					clubUpdate = new FootballClub(premierLeagueClubsListUpdate.indexOf(sportsClub),homeTeamName,sportsClub.getLocation(),sportsClub.getStadium(),sportsClub.getNumOfMembers(),date,match,won,drawn,lost,goalsScore,goalsReceived,goalsDifference,points);
					premierLeagueClubsListUpdate.set(premierLeagueClubsListUpdate.indexOf(sportsClub),clubUpdate);
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
					clubUpdate = new FootballClub(premierLeagueClubsListUpdate.indexOf(sportsClub),awayTeamName,sportsClub.getLocation(),sportsClub.getStadium(),sportsClub.getNumOfMembers(),date,match,won,drawn,lost,goalsScore,goalsReceived,goalsDifference,points);
					premierLeagueClubsListUpdate.set(premierLeagueClubsListUpdate.indexOf(sportsClub),clubUpdate);
				}
			}
		}
		clubUpdate = new FootballClub(date,stadium,homeTeamName,homeTeamScore,awayTeamName,awayTeamScore);
		listOfAllMatchesUpdate.add(clubUpdate);
		listOfRandomMatchesUpdate.add(0,clubUpdate);

		premierLeagueClubsListUpdate.sort(Collections.reverseOrder());
		for (SportsClub club:premierLeagueClubsListUpdate){
			SportsClub clubPosition = new FootballClub(premierLeagueClubsListUpdate.indexOf(club) + 1,club.getNameOfTheClub(), club.getLocation(),club.getStadium(),club.getNumOfMembers(),((FootballClub) club).getMatchDate(),((FootballClub) club).getNumOfPlayedMatches(), ((FootballClub) club).getWon(),((FootballClub) club).getDrawn(), ((FootballClub) club).getLost(), ((FootballClub) club).getNumOfGoalsScored(), ((FootballClub) club).getNumOfGoalsReceived(), ((FootballClub) club).getNumOfGoalsDifference(), ((FootballClub) club).getNumOfPoints());
			premierLeagueClubsListUpdate.set(premierLeagueClubsListUpdate.indexOf(club),clubPosition);
		}
	}
}
