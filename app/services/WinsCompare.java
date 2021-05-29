package services;

import entities.FootballClub;
import entities.SportsClub;

import java.util.Comparator;

public class WinsCompare implements Comparator<SportsClub> {	// number of wins compare

	@Override
	public int compare(SportsClub sportsClub1, SportsClub sportsClub2) {
		int win01 = ((FootballClub) sportsClub1).getWon();
		int win02 = ((FootballClub) sportsClub2).getWon();

		return Integer.compare(win02, win01);
	}
}
