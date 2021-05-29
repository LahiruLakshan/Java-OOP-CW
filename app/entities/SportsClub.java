package entities;

import java.io.Serializable;

public class SportsClub implements Serializable {

	private String nameOfTheClub;
	private String location;
	private int numOfMembers;
	private String stadium;

	public SportsClub(String nameOfTheClub,String stadium){

		this.nameOfTheClub = nameOfTheClub;
		this.stadium = stadium;
	}
	public SportsClub(String nameOfTheClub, String location, String stadium, int numOfMembers) {
		this.nameOfTheClub = nameOfTheClub;
		this.location = location;
		this.stadium = stadium;
		this.numOfMembers = numOfMembers;
	}

	public String getNameOfTheClub() {
		return nameOfTheClub;
	}

	public void setNameOfTheClub(String nameOfTheClub) {
		this.nameOfTheClub = nameOfTheClub;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public int getNumOfMembers() {
		return numOfMembers;
	}

	public void setNumOfMembers(int numOfMembers) {
		this.numOfMembers = numOfMembers;
	}
}
