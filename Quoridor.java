package main;

public class Quoridor {

    /**
     * On peut utiliser: import java.util.Scanner;
     * Scanner sc = new Scanner(System.in);
     * System.out.println("Enter how many players: (Only 2 valid):")
     * players = sc.nextInt();
     * sc.nextLine();
     */

    /**
     * Nº de jouers
     */
    private static int numPlayers = 4;

    /**
     * tableau des joueurs
     */
    private static Player[] playersArray  = new Player[numPlayers];
    
    private static int currentPlayerId = 0;

	public static void main(String[] args) {
		
		
		initPlayers();
		new GameView("Quoridor", 1000, 850, false);

	}
	/**
	 * Initialise les jouers
	 */
	
	private static void initPlayers(){
		for (int i = 0; i < numPlayers; i++){
			playersArray[i] = new Player(i, numPlayers);
			Board.setCell(playersArray[i].getPlayerLoc(), playersArray[i]);
		}
	}
	
	/**
	 * Je ne sais pas qu'est qu'il fait :x
	 */
	
	public static void nextPlayer(){
			updateAvailableLocations();
			if(getCurrentPlayer().isWinner())
				System.out.println(getCurrentPlayer().getName()+" is the winner!!!!");
			else				
				currentPlayerId = (currentPlayerId + 1) % numPlayers;
	}
	
	
	/**
	 * Afficher le jouer courant. (Object type Player?)
	 */
	public static Player getCurrentPlayer(){
		return playersArray[currentPlayerId];
	}
	
	

	private static void updateAvailableLocations(){

		for (int i = 0; i < numPlayers; i++){
			playersArray[i].setAvailableLocations();
		}
	}
	
	/**
	 * Afficher la ID du jouer courant.
	 */
	
	public static int getCurrentPlayerId(){
		return currentPlayerId;
	}
	
	
	/**
	 * Afficher le número de jouers.
	 */
	public static int getNumPlayers(){
		return numPlayers;
	}

}
