package services;

import entities.FootballClub;
import entities.SportsClub;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class PremierLeagueManagerTest {
	private PremierLeagueManager plm = new PremierLeagueManager();
	private OutputStream OS = new ByteArrayOutputStream();
	private PrintStream PS = new PrintStream(OS);

	@Before
	public void init(){
		SportsClub sportsClub2 = new FootballClub("Chelsea", "America","sd", 23);
		SportsClub sportsClub3 = new FootballClub("Everton", "Mexico","sd", 30);
		SportsClub sportsClub4 = new FootballClub("Manchester United", "Africa","sd", 33);
		plm.addFootballClubToPremierLeague(sportsClub2);
		plm.addFootballClubToPremierLeague(sportsClub3);
		plm.addFootballClubToPremierLeague(sportsClub4);
		plm.displayPremierLeagueTable();
	}
	@Test
	public void testAddFootballClubToPremierLeague() {
		System.setOut(PS);
		SportsClub sportsClub5 = new FootballClub("Aston Villa", "Russia","sa", 31);
		plm.addFootballClubToPremierLeague(sportsClub5);
		assertEquals("* Data added successfully!"+ System.getProperty("line.separator") + "Number of registered clubs			   : " + plm.premierLeagueClubsList.size()+ System.getProperty("line.separator") +"Remaining space for clubs registration : " + (20 - plm.premierLeagueClubsList.size())+ System.getProperty("line.separator") ,OS.toString());
		System.setOut(System.out);
	}

	@Test
	public void testDeleteAClub() {
		System.setOut(PS);
		plm.deleteAClub("Chelsea");
		assertEquals("Chelsea is deleted successfully !"+ System.getProperty("line.separator") ,OS.toString() );
		System.setOut(System.out);
	}

	@Test
	public void testAddPlayedMatchDetails() {
		System.setOut(PS);

		plm.addPlayedMatchDetails("2019-12-30","Leicester City",10,"Manchester United",3);
		assertEquals("Match Data added successful!"+ System.getProperty("line.separator") ,OS.toString() );
		System.setOut(System.out);
	}
}
