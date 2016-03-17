 package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Settings {
	
	/**
	 * Hauteur de la fênetre
	 * Largeur de la fênetre
	 * Nombre de cases. (ou tiles)
	 */
	
	public static int defaultWindowHeight = 850;
	public static int defaultWindowWidth = 1000;
	public static int boardHeight = 9;
	
    public static final int TOTAL_WALLS = 20;
	
    /**
     * La positon initiale en haut
     */
    public static final Location TOP = new Location(0, Board.halfLine());

     /**
     * la position initiale en bas
     */
    public static final Location BOT = new Location(Board.maxIndex(), Board.halfLine());

    /**
     * la position initiale à gauche
     */
    public static final Location LEFT = new Location(Board.halfLine(), 0);

    /**
     *la position initiale à droite
     */
    public static final Location RIGHT = new Location(Board.halfLine(), Board.maxIndex());


    /**
     * Position initial en fonction du numéro du jouer
     */
    public static final Location[] INITIAL_LOCATIONS = { BOT, TOP, RIGHT, LEFT };
    

	//public static char difficulty;
	
	//public static char mode;
	
	/**
	 * Couleur de chaque case.
	 * Couleur de des bordures.
	 * Couleur de chaque mur.
	 * Couleur de chaque pion.
	 */
	public static final Color TILE_COLOR = new Color(230,230,230);
	public static final Color BR_COLOR = new Color(0,0,0,50);
	public static final Color WALL_COLOR = new Color(127, 140, 141);
	public static final Color[] PAWN_COLORS = {
		new Color(46,204,113),//emerald
		new Color(243, 156, 18), //orange
		new Color(231,76,60),//alizarine
		new Color(52, 152, 219) //peter river
			
	};
	public static final Color[] PREVIEW_COLORS = {
		new Color(46,204,113, 90),
		new Color(243, 156, 18, 90),
		new Color(231, 76, 60, 90),
		new Color(52, 152, 219, 90) 
	};
	
	private static Font FONT;
	static{
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/fonts/justSquareUltraLight.ttf"))).deriveFont(Font.BOLD, 24);
			FONT = f;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Font getFont(){
		return FONT;
	}
	
	public static Font getTitleFont(){
		return FONT.deriveFont(Font.PLAIN, 150);
	}
			

}
