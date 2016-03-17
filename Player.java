package main;

public class Player extends Rules{

    /**
    * Id du joueur
    */
    private int playerId;
    
    /**
     * Nom du joueur
     */
    private String name;
    

    /**
     * La position Location du joueur
     */
    private Location playerLocation;

    /**
     * Nombre de murs qu'il reste au joueur
     */
    private int numWalls;

   

    /**
     * Tableau contenant toutes les les cases qu'un joueur peut atteindre � partir de la position o� il se trouve
     */
    private Location[] availableLocations;

     /**
     * tableau des joueurs
     */


    /**
     * Construit un Player a partir du nombre de joueurs et du num�ro du joueur
     * 
     * @param numPlayers
     *           nombre de joueurs
     * @param index
     *            num�ro du joueur
     */
    public Player(int index, int numPlayers) {
        this.playerId = index;
    	this.setplayerLoc(Settings.INITIAL_LOCATIONS[index]);
    	this.setNumWalls(Settings.TOTAL_WALLS/numPlayers);
    	this.setName("Player "+index);
        this.setAvailableLocations();
    }

    public int getPlayerId(){
    	return this.playerId;
    }
    
    /**
     * Obtenir le nombre de murs qu'il reste au joueur
     * @return le nombre de murs qu'il reste au joueur
     */
    public int getNumWalls() {
	   return numWalls;
    }
    
    public String getName(){
    	return this.name;
    }
    public void setName(String name){
    	this.name = name;
    }

    /**
     * D�finir le nombre de murs qu'il reste au joueur
     * 
     * @param numWalls
     *            le nombre de murs qu'on veut donner au joueur
     */
    public void decrementNumWalls() {
	   this.numWalls--;
    }

    /**
     * Obtenir la position (Location) du joueur qui appartient a ce jouer.
     * 
     * @return L�objet Location (position) du joueur de ce jouer
     */
    public Location getPlayerLoc() {
	   return playerLocation;
    }

    /**
     * D�finir la position du joueur de ce joueur
     * 
     * @param playerLoc
     *            La nouvelle position (Location )
     */
    public void setplayerLoc(Location playerLoc) {
	   this.playerLocation = playerLoc;
    }
    
    public void setAvailableLocations(){
    	this.availableLocations = Rules.availableLocations(this.playerLocation);
    }
    
    public Location[] getAvailableLocations(){
    	return this.availableLocations;
    }
    
    public void setNumWalls(int n){
    	this.numWalls = n;
    }
    
    public String toString(){
    	return this.name;
    }

    /**
     * 
     * 
     * @param loc
     * 
     * @return
     */
    public void movePawnTo(Location loc){
        if (Rules.canMoveTo(this, loc)){
        	
        	Board.setCell(this.playerLocation,null);
        	
            this.playerLocation = loc;
            
            Board.setCell(loc,this);
            this.setAvailableLocations();
  
            Quoridor.nextPlayer();
            
        }
        

    }
    
    
    public void putWall(Location loc){
    	Board.putWall(this, loc);
    	
    	
    }
    
    public boolean isWinner(){
        switch (this.playerId) {
            case 0:
                return this.playerLocation.rowEquals(Settings.TOP);

            case 1:
                return this.playerLocation.rowEquals(Settings.BOT);

            case 2:
                return this.playerLocation.colEquals(Settings.LEFT);

            case 3:
                return this.playerLocation.colEquals(Settings.RIGHT);
        }
		return false;
    }
}
