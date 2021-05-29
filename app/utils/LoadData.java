package utils;

import services.PremierLeagueManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoadData {

	public static void dataLoadInFile() {		// data load method
		try{
			FileInputStream fileInputStream = new FileInputStream("public/Resources/premierLeagueClubsList.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			PremierLeagueManager.premierLeagueClubsList = (ArrayList) objectInputStream.readObject();
			PremierLeagueManager.listOfMatches = (ArrayList) objectInputStream.readObject();
			PremierLeagueManager.listOfRandomMatches = (ArrayList) objectInputStream.readObject();

			System.out.println("Data loaded successful!");
			objectInputStream.close();
			fileInputStream.close();
		}catch (IOException | ClassNotFoundException ex){
			System.out.println("Not found data");
		}
	}
}
