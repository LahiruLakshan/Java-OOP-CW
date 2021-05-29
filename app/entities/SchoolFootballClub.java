package entities;

public class SchoolFootballClub extends FootballClub {

	private String schoolName;

	public SchoolFootballClub(int position, String schoolName, String nameOfTheClub, String location, String stadium,int numOfMembers, String matchDate, int numOfPlayedMatches, int won, int drawn, int lost, int numOfGoalsScored, int numOfGoalsReceived, int numOfGoalsDifference, int numOfPoints ) {
		super(position,nameOfTheClub, location,stadium, numOfMembers, matchDate, numOfPlayedMatches, won, drawn, lost, numOfGoalsScored, numOfGoalsReceived, numOfGoalsDifference, numOfPoints);
		this.schoolName = schoolName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
