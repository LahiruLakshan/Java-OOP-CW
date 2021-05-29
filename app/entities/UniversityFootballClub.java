package entities;

public class UniversityFootballClub extends FootballClub {

	private String universityName;

	public UniversityFootballClub(int position,String universityName, String nameOfTheClub, String location, String stadium,int numOfMembers, String matchDate , int numOfPlayedMatches, int won, int drawn, int lost, int numOfGoalsScored, int numOfGoalsReceived, int numOfGoalsDifference, int numOfPoints) {
		super(position,nameOfTheClub, location,stadium, numOfMembers, matchDate, numOfPlayedMatches, won, drawn, lost, numOfGoalsScored,numOfGoalsReceived, numOfGoalsDifference, numOfPoints);
		this.universityName = universityName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
}
