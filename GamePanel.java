package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public  class GamePanel extends JPanel implements MouseInputListener{
	
	private int spanWidth = (Settings.defaultWindowHeight-54)/(4*Board.getNumTiles() - 1);
	private int pawnWidth = 2*spanWidth;
	private int tileWidth = 3*spanWidth;
	private int wallHeight = 7*spanWidth;
	private int pawnHighliterWidth = this.pawnWidth+12;
	private int margin = 35;
	
	private Location preview;
	public GamePanel(){
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);


		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
//		for(int i = 0;i < Quoridor.getNumPlayers();i++){
//			switch (i){
//				case 0:
//					g.setColor(Settings.PAWN_COLORS[0]);
//					g.fillRect(margin, margin-spanWidth-7, 9*tileWidth+8*spanWidth+1, 23);
//					break;
//				case 1:
//					g.setColor(Settings.PAWN_COLORS[1]);
//					g.fillRect(margin , margin + margin-spanWidth + 9*tileWidth+8*spanWidth-5, 9*tileWidth+8*spanWidth+1, 23);
//					break;
//				case 2:
//					g.setColor(Settings.PAWN_COLORS[2]);
//					g.fillRect(margin-spanWidth-7, margin, 23, 9*tileWidth+8*spanWidth+1);
//					break;
//				case 3:
//					g.setColor(Settings.PAWN_COLORS[3]);
//					g.fillRect(margin-spanWidth +margin + 9*tileWidth+8*spanWidth-5, margin, 23, 9*tileWidth+8*spanWidth+1);
//					break;
//			}
//		}
		this.drawBoard(g);
		if (preview != null){
			this.doPreviewAction(preview, g);
		}
	}
	
	
	public void drawVerticalWall(Graphics g, Location loc, Color color){
		int x = margin + this.tileWidth +(this.tileWidth+this.spanWidth)*(loc.getCol()/2);
		int y = margin + (this.tileWidth + this.spanWidth)*(loc.getRow()/2);
		g.setColor(color);
		g.fillRect(x+1, y,  this.spanWidth-1, wallHeight+1);
		g.setColor(Settings.BR_COLOR);
		g.drawRect(x+1, y,  this.spanWidth-2, wallHeight);
	}
	
	public void drawHorizontalWall(Graphics g, Location loc, Color color){
		int y =  margin + (this.tileWidth +(this.tileWidth+this.spanWidth)*(loc.getRow()/2));
		int x =  margin + (this.tileWidth+this.spanWidth)*(loc.getCol()/2);
		g.setColor(color);
		g.fillRect(x, y+1,  wallHeight+1,  this.spanWidth-1);
		g.setColor(Settings.BR_COLOR);
		g.drawRect(x, y+1,  wallHeight,  this.spanWidth-2);
	}
	
	public void previewHorizontalWall(Graphics g, Location loc){
		this.drawHorizontalWall(g, loc, Settings.PREVIEW_COLORS[Quoridor.getCurrentPlayerId()]);
	}
	
	public void previewVerticalWall(Graphics g, Location loc){
		this.drawVerticalWall(g, loc, Settings.PREVIEW_COLORS[Quoridor.getCurrentPlayerId()]);;
	}
	
	public void drawTile(Graphics g, Location loc){
		
		int x = margin + loc.getCol() * (this.spanWidth+this.tileWidth) /2;
		int y = margin + loc.getRow() * (this.spanWidth+this.tileWidth) /2;
		g.setColor(Settings.TILE_COLOR);
		g.fillRect(x, y,  tileWidth,  tileWidth);
		g.setColor(Settings.BR_COLOR);
		g.drawRect(x, y,  tileWidth,  tileWidth);
	}
	
	public void drawTiles(Graphics g){
		for(int i = 0; i < Board.getLength(); i+=2){
			for(int j = 0; j < Board.getLength(); j+=2){
				Location loc = new Location(i, j);
				this.drawTile(g,loc);
				if(Board.getObjectOnCell(loc) instanceof Player){
					int playerId = ((Player) Board.getObjectOnCell(loc)).getPlayerId();
					this.drawPawn(g, loc, playerId, Settings.PAWN_COLORS[playerId], true);
				}
			}
			
		}
	}
	
	public void drawPawn(Graphics g1, Location loc, int playerId, Color color, boolean isHaveBorder){
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (playerId == Quoridor.getCurrentPlayerId() && isHaveBorder)
			this.pawnHighlighter(g, loc, playerId);
		int x = margin + loc.getCol() * (this.spanWidth+this.tileWidth) /2 + (int) (this.spanWidth/2);
		int y = margin + loc.getRow() * (this.spanWidth+this.tileWidth) /2 + (int) (this.spanWidth/2);
		g.setColor(color);
		g.fillOval(x, y,  this.pawnWidth,  this.pawnWidth);
		
		if(isHaveBorder){
			g.setColor(Settings.BR_COLOR);
			g.drawOval(x, y,  this.pawnWidth-1,  this.pawnWidth-1);
		}
			
	}
	
	public void pwanPreview(Graphics g, Location loc){
		int playerId = Quoridor.getCurrentPlayerId();
		this.drawPawn(g, loc, playerId, Settings.PREVIEW_COLORS[playerId], false);

	}
	
	public void pawnHighlighter(Graphics g1, Location loc, int playerId){
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		int x = margin + loc.getCol() * (this.spanWidth+this.tileWidth) /2 + (int) (this.spanWidth/2)-6;
		int y = margin + loc.getRow() * (this.spanWidth+this.tileWidth) /2 + (int) (this.spanWidth/2)-6;
		g.setColor(Settings.PREVIEW_COLORS[playerId]);
		g.fillOval(x, y,  this.pawnHighliterWidth,  this.pawnHighliterWidth);
		g.setColor(new Color(0,0,0,25));
		g.drawOval(x, y,  this.pawnHighliterWidth-1,  this.pawnHighliterWidth-1);
	}
	
	public void drawBoard(Graphics g){
		this.drawTiles(g);
		
		this.drawWalls(g);
	}
	
	
	public void drawWalls(Graphics g){
		Object[][] board = Board.getBoard();

		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				
				Location loc = new Location(i,j);
				
				//si c'est un mur et qu'il est actif et est la "t�te"
				if( Board.isWall(loc) && ((Wall) board[i][j]).isActive() && ((Wall) board[i][j]).isHead()){
					
					// si c'est un mur vertical
					if((i & 1)==0){
						// on dessine un mur vertical
						this.drawVerticalWall(g, loc, Settings.WALL_COLOR);
						
					//sinon il est horizontal
					}else{
						this.drawHorizontalWall(g, loc, Settings.WALL_COLOR);
						//on dessine un mur horizontal
					}
				}

				
			}
			
		}
	}
	
	
	public void doPreviewAction(Location loc, Graphics g){
		if((loc.getCol() & 1) == 0 && (loc.getRow() & 1) == 0)
			this.movePreview(loc, g);
		else if(Quoridor.getCurrentPlayer().getNumWalls() != 0)
			this.wallPreview(loc, g);
	}
	
	public void wallPreview(Location loc, Graphics g){
		if (loc.isInBoard() && Rules.wallCanGoTo(loc)){
			if((loc.getRow() & 1)==0){
				this.previewVerticalWall(g, loc);
			}else{
				this.previewHorizontalWall(g, loc);
			}
			
		}
	}
	
	public void movePreview(Location loc, Graphics g){
		if(Rules.canMoveTo(Quoridor.getCurrentPlayer(),loc))
			this.pwanPreview(g,loc);
			
	}
	

	
	public Location eventToLoc(MouseEvent e){
		int x = e.getX()-margin;
		int y = e.getY()-margin;
		
		//Coordonn�es du pattern
		int patternX =  (x / (4*spanWidth));
		int patternY =  (y / (4*spanWidth));
		
		//Coordonn�es relatives au pattern
		int relX =  (x-patternX*(4*spanWidth)); 
		int relY =  (y-patternY*(4*spanWidth));
		
		int tempX = 2*patternX;
		int tempY = 2*patternY;
		
		int col =(relX > tileWidth)?tempX+1:tempX; 
		int row =(relY > tileWidth)?tempY+1:tempY;
		
		return new Location(row ,col);
		
	}
	
	public void mouseMoved(MouseEvent e){
		this.preview = this.eventToLoc(e);
		repaint();

	}
	
	
	public void mouseClicked(MouseEvent e){
		Location loc = this.eventToLoc(e);
		if((loc.getCol() & 1) == 0 && (loc.getRow() & 1) == 0)
			Quoridor.getCurrentPlayer().movePawnTo(loc);
		else
			Quoridor.getCurrentPlayer().putWall(loc);
		this.repaint();

	}

	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.repaint();
		
	}

	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
