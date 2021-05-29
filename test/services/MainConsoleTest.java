package services;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainConsoleTest {
	@Test
	public void testMatchDuplicateCheck() {
		assertFalse(MainConsole.matchDuplicateCheck("Manchester United","Manchester United"));
		assertTrue(MainConsole.matchDuplicateCheck("Leicester City", "Chelsea"));
	}

	@Test
	public void testStringsChecker() {
		assertTrue(MainConsole.dateChecker("2020-12-12"));
		assertFalse(MainConsole.dateChecker("2020/12/12"));
		assertFalse(MainConsole.dateChecker("test"));
		assertFalse(MainConsole.dateChecker("12-12-2020"));
	}

	@Test
	public void testDateChecker() {
		assertTrue(MainConsole.stringsChecker("Manchester"));
		assertFalse(MainConsole.stringsChecker("2020/12/12"));
		assertFalse(MainConsole.stringsChecker("07123321123"));
		assertFalse(MainConsole.stringsChecker("12-12-2020"));
	}
}
