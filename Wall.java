package main;

public class Wall {
	/**
	* 
	*/
	private boolean isActive = false;
	
	private boolean isHead = false;


	
	public boolean isHead(){
		return this.isHead;
	}
	
	public void setHead(){
		this.isHead = true;
	}
	
	/**
	 * 
	 * 
	 * @return true
	 * 			si ce mur est actif
	 */
	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * 
	 * 
	 * @return true
	 * 			si ce mur est inactif
	 * 
	 */
	public boolean isInactive(){
		return !(this.isActive);
	}

	/**
	 * 
	 */
	public void setActive(){
		this.isActive = true;
	}
	
	public String toString(){
		String s = (this.isActive)?"Actif":"Inactif";
		return "Mur"+s;
	}
	
	
}
