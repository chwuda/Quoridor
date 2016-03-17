package main;

public class Location {

	
    private int row, col;


    /**
     * Construit un object Location a partir du num�ro de la colonne et de la ligne
     * 
     * @param row
     *           Num�ro de la ligne
     * @param col
     *           Num�ro de la colonne
     */
    public Location(int row, int col) {
		this.row = row;
		this.col = col;
    }
    

    /**
     * Obtenir la ligne 
     * @return la ligne 
     */
    public int getRow() {
		return this.row;
    }

    /**
     * D�finir la ligne
     * 
     * @param row
     *            Num�ro de la ligne
     */
    public void setRow(int row) {
		this.row = row;
    }

     /**
     * Obtenir la colonne 
     * @return la colonne 
     */
    public int getCol() {
		return this.col;
    }

    /**
     * D�finir la colonne
     * 
     * @param col
     *            Num�ro de la colonne
     */
    public void setCol(int col) {
		this.col = col;
    }


    public String toString() {
		return "(" + getRow() + ", " + getCol()+")";
    }

    /**
     * V�rifie si deux positions sont �gales
     * 
     * @param obj
     *            object de type qu'on veut comparer
     * @return true 
     *            si les deux positions sont les m�mes
     *
     */
    @Override
    public boolean equals(Object obj) {
    	if (obj == null)
    	    return false;
    	if (obj instanceof Location) {
    	    Location loc = (Location) obj;
    	    return loc.getRow() == getRow() && loc.getCol() == getCol();
    	}
    	return false;
        }

    /**
     * 	
     * 
     * @param loc
     * 
     * @return
     */
    public boolean rowEquals(Location loc){
        return loc.row == this.row;
    }

    /**
     * 
     * 
     * @param loc
     * 
     * @return
     */
    public boolean colEquals(Location loc){
        return loc.col == this.col;
    }
    
    public Location topCell(){
    	if (this.row >0)
    		return new Location(this.row-1, this.col);
    	return null;
    }
    
    public Location botCell(){
    	if (this.row < Board.maxIndex())
    		return new Location(this.row+1, this.col);
    	return null;
    }
    
    public Location leftCell(){
    	if (this.col > 0)
    		return new Location(this.row, this.col-1);
    	return null;
    }
    
    public Location rightCell(){
    	if (this.col < Board.maxIndex())
    		return new Location(this.row, this.col+1);
    	return null;
    }
    
    
    public Location topCell(int n){
    	if (this.row >=n)
    		return new Location(this.row-n, this.col);
    	return null;
    }
    
    public Location botCell(int n){
    	if (this.row < Board.getLength()-n)
    		return new Location(this.row+n, this.col);
    	return null;
    }
    
    public Location leftCell(int n){
    	if (this.col >= n)
    		return new Location(this.row, this.col-n);
    	return null;
    }
    
    public Location rightCell(int n){
    	if (this.col < Board.getLength()-n)
    		return new Location(this.row, this.col+n);
    	return null;
    }
    
    public boolean isInBoard(){
    	return this.row <= Board.maxIndex() && this.row >= 0 && this.col <= Board.maxIndex() && this.col >= 0;
    }
    
    public Location rotate(Location center, int rad){
    	int Vx = center.getCol()-this.getCol();
    	int Vy = center.getRow()-this.getRow();
    	
    	int y = (int) (Vy*Math.cos(rad) - Vx*Math.sin(rad));
    	int x = (int) (Vy*Math.sin(rad) - Vx*Math.cos(rad));
    	
    	if((new Location (y,x)).isInBoard()){
    		return new Location(y,x);
    	}
    	return null;
    }

}
