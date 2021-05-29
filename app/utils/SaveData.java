package utils;

import services.PremierLeagueManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveData {

	public static void dataSaveToFile(){		// data save
		try{
			PremierLeagueManager.allClubsRanksUpdate();
			FileOutputStream fileOutputStream = new FileOutputStream("public/Resources/premierLeagueClubsList.txt");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(PremierLeagueManager.premierLeagueClubsList);
			objectOutputStream.writeObject(PremierLeagueManager.listOfMatches);
			objectOutputStream.writeObject(PremierLeagueManager.listOfRandomMatches);

			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
			System.out.println("Data saving is successful!");
		}catch (IOException i){
			i.printStackTrace();
		}
	}
}
