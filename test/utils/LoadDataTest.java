package utils;

import entities.FootballClub;
import entities.SportsClub;
import org.junit.Before;
import org.junit.Test;
import services.PremierLeagueManager;
import services.UtilProcess;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class LoadDataTest {

	private OutputStream OS = new ByteArrayOutputStream();
	private PrintStream PS = new PrintStream(OS);

	@Before
	public void init() {
		SportsClub sportsClub1 = new FootballClub("Chelsea", "America", "sd", 23);
		UtilProcess.listOfAllMatchesUpdate.add(sportsClub1);



	}

	@Test
	public void dataLoadInFile() {
		System.setOut(PS);
		LoadData.dataLoadInFile();
		assertEquals("Data loaded successful!"+ System.getProperty("line.separator") ,OS.toString() );
		System.setOut(System.out);
	}
}
