package services;

import entities.FootballClub;
import entities.SportsClub;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class DateCompare implements Comparator<SportsClub> {
	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public int compare(SportsClub club1, SportsClub club2) {	// date comparator

		LocalDate localDate1 = LocalDate.parse(((FootballClub) club1).getMatchDate(),format);
		LocalDate localDate2 = LocalDate.parse(((FootballClub) club2).getMatchDate(),format);

		return localDate1.compareTo(localDate2);

	}
}
