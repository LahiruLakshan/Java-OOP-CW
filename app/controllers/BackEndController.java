package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UtilProcess;


public class BackEndController extends Controller {

	public Result rankingTableDisplay() {		// for ranking table
		UtilProcess.loadData();
		return ok(Json.toJson(UtilProcess.premierLeagueClubsListUpdate));

	}
	public Result matchTableDisplay() {		// for match table
		UtilProcess.loadData();
		return ok(Json.toJson(UtilProcess.listOfAllMatchesUpdate));
	}

	public Result randomMatches() {		// for random match table
		UtilProcess.loadData();
		return ok(Json.toJson(UtilProcess.listOfRandomMatchesUpdate));
	}

	public Result randomPlayMatch() {		// for random match play
		UtilProcess.randomMatch();
		return ok(Json.toJson(UtilProcess.listOfRandomMatchesUpdate));
	}

	public Result pointSort() {		// for point sort
		UtilProcess.pointSort();
		return ok(Json.toJson(UtilProcess.premierLeagueClubsListUpdate));
	}

	public Result goalsScoredSort() {		// for goals sort
		UtilProcess.goalsScoredSort();
		return ok(Json.toJson(UtilProcess.premierLeagueClubsListUpdate));
	}

	public Result winsSort() {		// for wins sort
		UtilProcess.winsSort();
		return ok(Json.toJson(UtilProcess.premierLeagueClubsListUpdate));
	}

	public Result dateSort() {		//for date sort
		UtilProcess.dateSort();
		return ok(Json.toJson(UtilProcess.listOfAllMatchesUpdate));
	}

	public Result dateSortRandomTable() {		// for random table date sort
		UtilProcess.dateSortRandomTable();
		return ok(Json.toJson(UtilProcess.listOfRandomMatchesUpdate));
	}
}
