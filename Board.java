package main;


public class Board {
	
	/**
	 * le nombre de cases par ligne
	 */
	private static int numTiles = Settings.boardHeight;

	/**
	* Grille du jeu
	*/
	private static Object[][] boardArray = new Object[2*numTiles-1][2*numTiles-1];
	
	/**
	 * )i & 1) regarder le premier byte, il regarde la parité. Si =1 -> impair, sinon pair
	 */
	static{
		for (int i = 0; i < boardArray.length; i++){
			for (int j = 0; j < boardArray.length; j++ ){
				if((i & 1) != 0 || (j & 1) != 0){
					
					boardArray[i][j] = new Wall();
					
				
				}
			}
		}
		

	}

	
	public static Object[][] getBoard(){
		return boardArray;
	}
	
	/**
	 * Retourne L'object qui se trouve à la position une certaine position sur la grille
	 * 
	 * @param loc
	 * 		la position 
	 * @return
	 * 		retourne l'objet à la position loc
	 */
	public static Object getObjectOnCell(Location loc){
		return boardArray[loc.getRow()][loc.getCol()];
	}
	
	
	
	/**
	 * Pose un mur horizontal à une certaine position
	 * 
	 * @param loc
	 * 		la position où le mur doit être posé
	 */
	private static void putHorizontalWall(Location loc){
		((Wall) getObjectOnCell(loc)).setActive();
		((Wall) getObjectOnCell(loc)).setHead();
		((Wall) getObjectOnCell(loc.rightCell())).setActive();
		((Wall) getObjectOnCell(loc.rightCell(2))).setActive();
	}
	
	/**
	 * Pose un mur vertical à un certaine position loc
	 * @param loc
	 * 		la position où le mur doit être posé 
	 */
	private static void putVerticalWall(Location loc){
		((Wall) getObjectOnCell(loc)).setActive();
		((Wall) getObjectOnCell(loc)).setHead();
		((Wall) getObjectOnCell(loc.botCell())).setActive();
		((Wall) getObjectOnCell(loc.botCell(2))).setActive();
	}

	/**
	 * Permet à un joueur de poser un mur à une certaine position
	 * @param player
	 * 		le joueur concerné
	 * @param loc
	 * 		position où le joueur veut poser un mur
	 */
	public static void putWall(Player player, Location loc){
		if (Rules.canPutWall(player, loc)){
			
			if ((loc.getRow() & 1) ==0) 
				// alors c'est un mur vertical
				putVerticalWall(loc);
			else
				//c'est un mur horizontal
				putHorizontalWall(loc);
			
			player.decrementNumWalls();

			Quoridor.nextPlayer();

		}
	}

	/**
	 * Permet de savoir si l'object qui se trouve sur un certaine position est un mur
	 * 
	 * @param loc
	 * 		la position concernée
	 * @return
	 * 		true si l'object qui se trouve à la position loc est un mur
	 */
	public static boolean isWall(Location loc){
		return getObjectOnCell(loc) instanceof Wall;
	}
	
	/**
	 * Permet d'obtenir le plus grand index du tableau
	 * @return
	 * 		le plus grand index du tableau
	 */
	public static int maxIndex(){
		return boardArray.length-1;
	}
	
	/**
	 * 
	 * @return
	 */
	public static int halfLine(){
		return maxIndex()/2;
	}
	
	/**
	 * Permet de définir l'objet qui se trouve à une certaine position
	 * @param loc
	 * 		position concernée
	 * @param obj
	 * 		l'object qu'on veut mettre à la position loc
	 */
	public static void setCell(Location loc, Object obj){
		boardArray[loc.getRow()][loc.getCol()] = obj;
	}
	
	
	public static int getNumTiles(){
		return numTiles;
	}
	
	public static int getLength(){
		return boardArray.length;
	}

}
