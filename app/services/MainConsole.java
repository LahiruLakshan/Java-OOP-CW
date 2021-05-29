package services;

import entities.FootballClub;
import entities.SportsClub;
import utils.LoadData;
import utils.SaveData;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;


public class MainConsole {
	private static final LeagueManager leagueManager = new PremierLeagueManager();
	private static boolean validationCheck;
	private static boolean decisionCheck;
	private static String clubName;
	private static String clubLocation;
	private static int clubMembers;
	private static String date;
	private static String stadiumName;
	private static String homeTeamName;
	private static int score;

	public static void main(String[] args) {
		LoadData.dataLoadInFile();// file is loaded the data to arraylist
		System.out.println("******************************************************** Welcome To Premier League Championship Manager ********************************************************");

		do {
			try{
				Scanner input = new Scanner(System.in);			//Premier League Main Menu Options
				System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: Menu Options ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
				System.out.println("\t\t1. Create a new football club and add it in the premier league. ");
				System.out.println("\t\t2. Delete a club from the premier league.");
				System.out.println("\t\t3. Display the various statistics for a selected club.");
				System.out.println("\t\t4. Display the Premier League Table.");
				System.out.println("\t\t5. Add a played match.");
				System.out.println("\t\t6. Data Save.");
				System.out.println("\t\t7. Open a Graphical User Interface.");
				System.out.println("\t\t8. Exit the Programme.");
				System.out.print("Select the number you want(1 to 8) : ");
				String select = input.next();

				switch (select) {

					case "1":
						System.out.println("\n------------------------------------------------------ Adding a new football club to the Premier League -----------------------------------------------------");
						addANewFootballClub();	// add a new club to premier league
						break;

					case "2":
						System.out.println("\n-------------------------------------------------- Delete a club from the league --------------------------------------------------");
						deleteClub();		// delete a club in premier league
						break;

					case "3":
						System.out.println("\n-------------------------------------------------- Details of a selected club --------------------------------------------------");
						selectedClubDetails();		// display the selected club details
						break;

					case "4":
						System.out.println("\n-------------------------------------------------- Premier League Table --------------------------------------------------");
						leagueManager.displayPremierLeagueTable();		// display the ranking table with matches table
						break;

					case "5":
						System.out.println("\n-------------------------------------------------- Details of a match played --------------------------------------------------");
						matchDetails();		// Enter the match details in the premier league system
						break;

					case "6":
						SaveData.dataSaveToFile();// Data stored from the arraylist to file
						break;

					case "7":
						Desktop.getDesktop().browse(new URI("http://localhost:4200"));	//open gui(Angular - play framework)
						break;

					case "8":
						System.out.println("\n************************************************************* End the Programme *************************************************************");
						decisionCheck = true;// exit the programme
						break;

					default:
						System.out.println("Invalid Input!");
				}
			} catch (URISyntaxException | IOException e) {
				e.printStackTrace();
			} finally {
				if (!decisionCheck){
					String endDecision;
					do {
						Scanner input = new Scanner(System.in);
						System.out.print("\n\tE - Exit the Programme\n\tC - Continue the programme\nChoose the decision you want : ");
						endDecision = input.nextLine();
						if (endDecision.equalsIgnoreCase("e")) {
							System.out.println("\n******************************************************** End the Programme ********************************************************");
							decisionCheck = true;
						} else if (endDecision.equalsIgnoreCase("c")) {
							decisionCheck = false;
						} else {
							System.out.println("~ I can't understand your choose!");
						}
					} while (!(endDecision.equalsIgnoreCase("e") || endDecision.equalsIgnoreCase("c")));
				}
			}

		}while (!decisionCheck);
	}

	private static void addANewFootballClub() {		// Check the list size to see if a new club can be added

		if (PremierLeagueManager.premierLeagueClubsList.size() < 20){
			inputClubName();
			inputClubLocation();
			inputSponsoredStadium();
			inputClubMembers();
			SportsClub footballClub = new FootballClub(clubName,clubLocation,stadiumName,clubMembers);
			leagueManager.addFootballClubToPremierLeague(footballClub);
		}else {
			System.out.println("\t~ Can't create a club for the Premier League! \n\t>> The 20 teams that must participate in the Premier League are complete.");
		}
	}



	private static void inputClubName() {		// Enter a name to add a new club to the Premier League
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("# Enter the name of the club          : ");
			clubName = input.nextLine();
			validationCheck = stringsChecker(clubName);
			if (validationCheck){
				for (SportsClub club : PremierLeagueManager.premierLeagueClubsList) {
					if (club.getNameOfTheClub().equalsIgnoreCase(clubName)) {
						System.out.println("\t>> This Club name is Already Register in League!");
						validationCheck = false;
						break;
					} else {
						validationCheck = true;
					}
				}
			}
		}while (!validationCheck);
	}

	private static void inputClubLocation() {		// Enter a location name to add a new club to the Premier League
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("# Enter the name of the club location : ");
			clubLocation = input.nextLine();
			validationCheck = stringsChecker(clubLocation);
		}while (!validationCheck);
	}
	private static void inputSponsoredStadium() {		// Enter a stadium name to add a new club to the Premier League
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("# Enter the name of the sponsored stadium : ");
			stadiumName = input.nextLine();
			validationCheck = stringsChecker(clubLocation);
		}while (!validationCheck);
	}
	private static void inputClubMembers() {		// Enter a stadium name to add a new club to the Premier League
		do {
			try{
				Scanner input = new Scanner(System.in);
				System.out.print("# Enter the Number of team members    : ");
				clubMembers = input.nextInt();
				validationCheck = (clubMembers >= 11);
				System.out.println(validationCheck ? "": "\t>> Each group must have 11 or more members!");
			}catch (RuntimeException e){
				System.out.println("\t~ Please enter integer input!");
				validationCheck = false;
			}
		}while (!validationCheck);
	}

	private static void deleteClub() {		// delete a club
		if (!PremierLeagueManager.premierLeagueClubsList.isEmpty()){		// Check the list empty or not for delete a club

				Scanner input = new Scanner(System.in);
				System.out.print("# Enter the name of the club : ");
				clubName = input.nextLine();
				validationCheck = stringsChecker(clubName);
				if (validationCheck){
					for (SportsClub club : PremierLeagueManager.premierLeagueClubsList) {
						if (club.getNameOfTheClub().equalsIgnoreCase(clubName)) {
							leagueManager.deleteAClub(clubName);
							validationCheck = true;
							break;
						} else {
							validationCheck = false;
						}
					}
					if (!validationCheck){
						System.out.print(clubName+" ");
						leagueManager.deleteAClub("NotFound");		// delete input is wrong
					}
				}
		}else {
			System.out.println("No clubs to delete from the league.");
		}
	}
	private static void matchDetails(){		// enter a play match details

		int rankedArraySize = PremierLeagueManager.premierLeagueClubsList.size();
		if ((rankedArraySize - 1)*rankedArraySize > PremierLeagueManager.listOfMatches.size()){
			inputMatchDate();
			boolean duplicateCheck;
			String awayTeamName;
			do{
				homeTeamName = inputClubName("Home");
				awayTeamName = inputClubName("Away");

				duplicateCheck = matchDuplicateCheck(homeTeamName, awayTeamName);		// check away team and home team
				if (!duplicateCheck){
					System.out.println("\t~ The match between the "+homeTeamName+"(Home Team) and "+ awayTeamName +"(Away Team) teams is already over.");
				}
			}while (!duplicateCheck);
			int homeTeamScore = inputScore("Home");
			int awayTeamScore = inputScore("Away");
			leagueManager.addPlayedMatchDetails(date, homeTeamName, homeTeamScore, awayTeamName, awayTeamScore);

		}else if (rankedArraySize < 2){
			System.out.println("\t~ There must be at least 2 teams to play one match!");
		}else {
			System.out.println("\t~ Season End!!");
		}
	}



	private static void inputMatchDate() {		// input a date for the match details
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("# Enter the match date in the following format (YYYY-MM-DD) : ");
			date = input.nextLine();
			validationCheck = dateChecker(date);
		}while (!validationCheck);

	}

	private static String inputClubName(String team) {		//input the clubs name for the match details
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("# Enter the name of the "+ team +" Team : ");
			clubName = input.nextLine();
			validationCheck = stringsChecker(clubName);
			if(validationCheck){
				for (SportsClub club : PremierLeagueManager.premierLeagueClubsList) {
					if (club.getNameOfTheClub().equalsIgnoreCase(clubName)) {
						validationCheck = true;
						break;
					} else {
						validationCheck = false;
					}
				}
				if (!validationCheck){
					System.out.println("\t~ There is no team in the league called \"" + clubName+"\"");
				}
				if (validationCheck && team.equalsIgnoreCase("Away") && homeTeamName.equalsIgnoreCase(clubName)){
					System.out.println("\t~ Please enter the name of the 2nd group again!\n\t* The names of the first team and the second team cannot be the same in a match.");
					validationCheck = false;
				}
			}
		}while (!validationCheck);
		return clubName;
	}

	private static int inputScore(String teamScore) {		//input a clubs score for the match details

		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("# Enter the number of goal score in the "+teamScore+" team : ");
				score = input.nextInt();
				validationCheck = (score >= 0);
				if (!validationCheck){
					System.out.println("\t~ Please enter valid input!");
				}			}catch (RuntimeException e){
				System.out.println("\t~ Please enter integer input!");
				validationCheck = false;
			}

		}while (!validationCheck);
		return score;
	}

	private static void selectedClubDetails() {		// input the club name for display club details

		if (!PremierLeagueManager.premierLeagueClubsList.isEmpty()){
			do {
				Scanner input = new Scanner(System.in);
				System.out.print("1. Enter the name of the Team : ");
				clubName = input.nextLine();
				validationCheck = stringsChecker(clubName);
				for (SportsClub club : PremierLeagueManager.premierLeagueClubsList) {
					if (club.getNameOfTheClub().equalsIgnoreCase(clubName)) {
						validationCheck = true;
						break;
					} else {
						validationCheck = false;
					}
				}
				if (!validationCheck) {
					System.out.println("\t~ There is no team in the league called \"" + clubName + "\"");
				}
			} while (!validationCheck);
			leagueManager.displayVariousStatistics(clubName);
		}else {
			System.out.println("\t~ There are currently no teams in the Premier League");
		}
	}

	public static boolean matchDuplicateCheck(String homeTeam, String awayTeam) {
		if (!(homeTeam.equalsIgnoreCase(awayTeam))){
			for (SportsClub club:PremierLeagueManager.listOfMatches){
				if (club.getNameOfTheClub().equalsIgnoreCase(homeTeam) && ((FootballClub) club).getOtherTeamName().equalsIgnoreCase(awayTeam)){
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}



	public static boolean stringsChecker(String value) {    //string validation checker

		if (!(value == null || value.trim().isEmpty())){
			if (value.matches("^[ A-Za-z]+$")){		//https://stackoverflow.com/questions/24191040/checking-to-see-if-a-string-is-letters-spaces-only/24191088
				return true;
			}else {
				System.out.println("\t~ Please enter a valid input!(Only Strings)");
				return false;
			}
		}else {
			System.out.println("\t~ Please enter an input!");
			return false;
		}
	}

	public static boolean dateChecker(String date) {		// date validation checker
		if (!(date == null || date.trim().isEmpty())){		//https://www.regexlib.com/Search.aspx?k=yyyy-mm
			if ((date.matches("^((((19[0-9][0-9])|(2[0-9][0-9][0-9]))([-])(0[13578]|10|12)([-])(0[1-9]|[12][0-9]|3[01]))|(((19[0-9][0-9])|(2[0-9][0-9][0-9]))([-])(0[469]|11)([-])([0][1-9]|[12][0-9]|30))|(((19[0-9][0-9])|(2[0-9][0-9][0-9]))([-])(02)([-])(0[1-9]|1[0-9]|2[0-8]))|(([02468][048]00)([-])(02)([-])(29))|(([13579][26]00)([-])(02)([-])(29))|(([0-9][0-9][0][48])([-])(02)([-])(29))|(([0-9][0-9][2468][048])([-])(02)([-])(29))|(([0-9][0-9][13579][26])([-])(02)([-])(29)))$"))){
				return true;
			}else {
				System.out.println("\t~ Date Error!");
				return false;
			}
		}else {
			System.out.println("\t~ Please enter valid time!");
			return false;
		}
	}
}
