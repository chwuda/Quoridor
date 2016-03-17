package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Rules{

	/**
	 * Retourne un tableau contenant toutes les positions ou un jouer peut aller � partir d'une position r�f�rence
	 * Cette fonction de prend en compte que les murs. Elle ne s'occupe pas de savoir si la case est occup�e ou pas.
	 * 
	 * @param loc
	 * 		la position r�f�rence
	 * @return
	 * 		les positions dans les-quelles le joueur peut se rendre
	 * 		
	 */
	
	private static Map<String, Location> availableNeighbours(Location loc){
		
		
		Map<String, Location> neighbours = new HashMap<String, Location>(); 
	
		
		if (loc.topCell() != null && ((Wall) Board.getObjectOnCell(loc.topCell())).isInactive())
		    neighbours.put("up", loc.topCell(2)); // case au dessus
		
		if (loc.rightCell() != null && ((Wall) Board.getObjectOnCell(loc.rightCell())).isInactive()) 
		    neighbours.put("right", loc.rightCell(2)); //case � droite 
		
		if (loc.botCell() != null && ((Wall) Board.getObjectOnCell(loc.botCell())).isInactive())
		    neighbours.put("down",loc.botCell(2));  //case en dessous
		
		if (loc.leftCell() != null && ((Wall) Board.getObjectOnCell(loc.leftCell())).isInactive()) 
		    neighbours.put("left", loc.leftCell(2)); //case � gauche

		return neighbours;
	}
	
	/**
	 * Permet de savoir si la position � gauche d'une position r�f�rence est accessible � partir de cette position
	 * 
	 * @param loc
	 * 		position r�f�rence
	 * @return true
	 * 		si la position � gauche de la r�f�rence est accessible � partir de la r�f�rence
	 */
	public static boolean isLeftCellAvailable(Location loc){
		
		Map<String, Location> neighbours = availableNeighbours(loc);
		
		Location value = neighbours.get("left");
		return value != null;
	}
	
	/**
	 * Permet de savoir si la position au dessus d'une position r�f�rence est accessible � partir de cette position
	 * 
	 * @param loc
	 * 		position r�f�rence
	 * @return true
	 * 		si la position au dessus de la r�f�rence est accessible � partir de loc
	 */
	public static boolean isTopCellAvailable(Location loc){
		
		Map<String, Location> neighbours = availableNeighbours(loc);
		
		Location value = neighbours.get("up");
		return value != null;
	}
	
	/**
	 * Permet de savoir si la position � droite d'une position r�f�rence est accessible � partir de cette position
	 * 
	 * @param loc
	 * 		position r�f�rence
	 * @return true
	 * 		si la position � droite de la r�f�rence est accessible � partir de la r�f�rence
	 */
	public static boolean isRightCellAvailable(Location loc){
		
		Map<String, Location> neighbours = availableNeighbours(loc);
		
		Location value = neighbours.get("right");
		return value != null;
	}
	
	/**
	 * Permet de savoir si la position en dessous d'une position r�f�rence est accessible � partir de cette position
	 * 
	 * @param loc
	 * 		position r�f�rence
	 * @return true
	 * 		si la position en dessous de la r�f�rence est accessible � partir de la r�f�rence
	 */
	public static boolean isBotCellAvailable(Location loc){
		
		Map<String, Location> neighbours = availableNeighbours(loc);
		
		Location value = neighbours.get("down");
		return value != null;
	}
	
	
	/**
	 * Permet d'obetenir toutes les positions accessibles � partir d'une position r�f�rence
	 * Elle tient en compte les sauts,..
	 * 
	 * @param loc
	 * 		la position r�f�rence
	 * @return
	 * 		un tableau contenant toutes les positions accessibles � partir de  la r�f�rence
	 */
	public static Location[] availableLocations(Location loc){
		
		List<Location> availableCells = new ArrayList<Location>();
		
		 
		Map<String, Location> neighbours = availableNeighbours(loc);
		

        for (Map.Entry<String, Location> entry: neighbours.entrySet()){
        	
            String key = entry.getKey();
            loc = entry.getValue();

            int col = loc.getCol();
            int row = loc.getRow();
            
            switch (key) {
                case "up" :

                    if (Board.getObjectOnCell(loc) != null) {
 
                        if(loc.topCell() == null || ((Wall) Board.getObjectOnCell(loc.topCell())).isActive()){
 
                            if (isRightCellAvailable(loc)) 
                                availableCells.add(new Location(row,col+2)); //case � droite
                            if (isLeftCellAvailable(loc)) 
                            	availableCells.add(new Location(row,col-2)); //case � gauche
                        }else{

                        	if(Board.getObjectOnCell(loc.topCell(2)) == null)
                        		availableCells.add(loc.topCell(2));
                        }
                    }else{
                    	
                    	availableCells.add(loc);
                    }
                    break;
                case "right" :
                	
                	if (Board.getObjectOnCell(loc) != null) {

                        if(loc.rightCell() == null || ((Wall) Board.getObjectOnCell(loc.rightCell())).isActive()){

                            if (isTopCellAvailable(loc)) 
                                availableCells.add(new Location(row-2,col)); //case � droite
                            if (isBotCellAvailable(loc)) 
                            	availableCells.add(new Location(row+2,col)); //case � gauche
                        }else{
                        	if(Board.getObjectOnCell(loc.rightCell(2)) == null)
                        		availableCells.add(loc.rightCell(2));
                        }
                    }else{

                    	availableCells.add(loc);
                    }

                    break;
                case "down" :

                	if (Board.getObjectOnCell(loc) != null) {

                        if(loc.botCell() == null || ((Wall) Board.getObjectOnCell(loc.botCell())).isActive()){

                            if (isRightCellAvailable(loc)) 
                                availableCells.add(new Location(row,col+2)); //case � droite
                            if (isLeftCellAvailable(loc)) 
                            	availableCells.add(new Location(row,col-2)); //case � gauche
                        }else{
                        	if(Board.getObjectOnCell(loc.botCell(2)) == null)
                        		availableCells.add(loc.botCell(2));
                        }
                    }else{
                    	availableCells.add(loc);
                    }
                	
                    break;
                case "left" :

                	if (Board.getObjectOnCell(loc) != null) {
   
                        if(loc.leftCell() == null || ((Wall) Board.getObjectOnCell(loc.leftCell())).isActive()){
 
                            if (isTopCellAvailable(loc)) 
                                availableCells.add(new Location(row-2,col)); //case � droite
                            if (isBotCellAvailable(loc)) 
                            	availableCells.add(new Location(row+2,col)); //case � gauche
                        }else{
                        	if(Board.getObjectOnCell(loc.leftCell(2)) == null)
                        		availableCells.add(loc.leftCell(2));
                        }
                    }else{
                    	
                    	availableCells.add(loc);
                    }
                    break;

            }
            
            
        }

        return availableCells.toArray(new Location[availableCells.size()]);
		 
		 
	}
	
	/**
	 * Permet de d�terminer si un mur peut �tre pos� � une certaine position
	 * 
	 * @param loc
	 * 		position o� on veut poser un mur
	 * @return
	 * 		true si un mur peur �tre pos� � la position loc
	 */
	public static boolean wallCanGoTo(Location loc){
		
		// TODO V�rifier si un mur ne bloque pas compl�tement le chemin
		if (loc.getRow() >= Board.maxIndex() || loc.getCol() >= Board.maxIndex())
			return false;
		if ((loc.getCol() & 1) !=0 && (loc.getRow() & 1) != 0) 
			return false;
		if ((loc.getCol() & 1) ==0 && (loc.getRow() & 1) == 0) 
			return false;
		if ((loc.getRow() & 1) ==0) // alors c'est un mur vertical
			return  ((Wall) Board.getObjectOnCell(loc)).isInactive() && ((Wall) Board.getObjectOnCell(loc.botCell())).isInactive() && ((Wall) Board.getObjectOnCell(loc.botCell(2))).isInactive();
		else
			return ((Wall) Board.getObjectOnCell(loc)).isInactive() && ((Wall) Board.getObjectOnCell(loc.rightCell())).isInactive() && ((Wall) Board.getObjectOnCell(loc.rightCell(2))).isInactive();
	}

	/**
	 * Permet de v�rifier si un joueur peut poser un mur � une certaine position
	 * @param player
	 * 		le joueur qui veut poser un mur
	 * @param loc
	 * 		la position � laquelle le joueur veut poser un mur
	 * @return
	 * 		true si le joueur peut poser une mur � la position loc
	 */
	public static boolean canPutWall(Player player, Location loc){

		if (player.getNumWalls() == 0) return false;
		
		return wallCanGoTo(loc);
		
	}
	/**
	 * Permet de v�rifier si un joueur peut se d�placer � une certaine position
	 * @param player
	 * 		le joueur qui veut se d�placer
	 * @param loc
	 * 		la position � laquelle le joueur veut se d�placer
	 * @return
	 * 		true si le joueur peut se d�placer � la position loc
	 */
    public static boolean canMoveTo(Player player,Location loc) {

        return Arrays.asList(player.getAvailableLocations()).contains(loc);
    }

	
}
