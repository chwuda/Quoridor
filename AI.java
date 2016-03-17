package main;

import java.util.HashMap;
import java.util.Map;

public class AI extends Player{
	
	public AI(int index, int numPlayers) {
		super(index, numPlayers);
		this.setName("Computer "+index);
	}
	
//	public int getPathLenghtDiffWidth(){
//		Player opponent = getMainOpponent();
//		return this.pathLength - opponent.pathLength;
//	}
//	
//	public Map<String,Object> simulateWall(Location loc){
//		Boad board = MainBoard.clone();
//		//TODO on simule un mur à la position et on calucle la diff de chemmin
//		
//		Map<String, Location> res =  new HashMap<String, Location>();
//		res.put("diff", new Integer(diff));
//		res.put("loc", loc);
//		return res;
//	}
//	
//	public void play(){
//		if(this.getPathLenghtDiffWidth(this) <= 0){
//			this.movePawnTo(this.path);
//		}else{
//			this.putWall(this.getBestWallPosition());
//		}
//	}
//	
//	public int getMainOpponent(){
//		//TODO 
//	}
//	
//	public Location getBestWallPosition(){
//		Location loc;
//		int diff;
//		for (int i = 0; i < longeur du tableau;i++){
//			for(int j = 0; j < largeur du tableau;j++){
//				if((i & 1) != 0 || (j & 1)!=0){
//					Map<String, Location> res =this.simulateWall(new Location(i,j));
//					if(res.get("diff") < diff){
//						diff = res.get("diff");
//						loc = res.get("loc");
//					}
//						
//					
//				}
//			}
//		}
//		return loc;
//	}
//

}
