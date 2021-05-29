package utils;

import entities.FootballClub;
import entities.SportsClub;
import org.junit.Before;
import org.junit.Test;
import services.PremierLeagueManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class SaveDataTest {
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
	public void dataSaveToFile() throws IOException {
		System.setOut(PS);
		SaveData.dataSaveToFile();
		assertEquals("Data saving is successful!"+ System.getProperty("line.separator") ,OS.toString() );
		System.setOut(System.out);

	}
}
