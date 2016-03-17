package main;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class CustomButton extends JButton implements MouseListener{
	private Dimension size = new Dimension(180,180);
	private ImageIcon icon;
	private boolean isActive = true;
	
	private boolean hover = false;
	private boolean clic = false;
	
	private static int i = 0;
	
	private String targetPanel;
	
	private int colorIndex;
	String text;
	
	
	
	
	public CustomButton(String text, ImageIcon icon, boolean isActive){
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		if(text != null)
			this.text = text;
		if(icon != null)
			this.icon = icon;
		this.colorIndex = i;
		i = (i+1)%4;
		
		this.isActive = isActive;
		addMouseListener(this);
		
	}
	
	public CustomButton(String text, ImageIcon icon){
		this(text,icon,true);
	}
	
	public CustomButton(String text){
		
		this(text,null,true);
	}
	
	
	public CustomButton(ImageIcon icon ){
		this(null, icon,true);
	}
	
	public CustomButton(String text, boolean isActive){
		
		this(text,null,isActive);
	}
	
	
	public CustomButton(ImageIcon icon, boolean isActive ){
		this(null, icon,isActive);
	}
	
	
	public void setTargetPanel(String name){
		this.targetPanel = name;
	}
	
	public String getTargetPanel(){
		return this.targetPanel;
	}
	
	@Override
	public void paintComponent(Graphics g1){
		super.paintComponent(g1);
		
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		Color bgColor = Settings.PAWN_COLORS[this.colorIndex];
		Color txtColor = new Color(255, 255, 255);
		if(!isActive){
			bgColor = Settings.PREVIEW_COLORS[this.colorIndex];
		}
		
		
		
		
		//on dessine le boutton
		g.setColor(bgColor);
		g.fillRect(0, 0, size.width, size.height);
		g.setColor(Settings.BR_COLOR);
		g.drawRect(0, 0, size.width-1, size.height-1);
		
		if(hover){
			g.setColor(new Color(0,0,0,30));
			g.fillRect(0, 0, size.width, size.height);
		}
		if(clic){
			g.setColor(new Color(0,0,0,30));
			g.fillRect(0, 0, size.width, size.height);
		}
		//on dessine l'icone
		if(icon != null){
			icon.paintIcon(this, g, (size.width-48)/2, 35);
		}
				
		g.setColor(txtColor);
		// on dessine le textergb(46, 204, 113)
		if (Settings.getFont() != null){
			g.setFont(Settings.getFont());
		}
		int textWidth = g.getFontMetrics().stringWidth(this.text);
		g.drawString(this.text, (size.width-textWidth)/2, 120);

	}
	
	@Override
	public Dimension getPreferredSize(){
		return size;
	}
	
	@Override
	public Dimension getMaximumSize(){
		return size;
	}
	
	@Override
	public Dimension getMinimumSize(){
		return size;
	}
	
	public void setButtonText(String text){
		this.text = text;
	}
		
	@Override
	public void mouseClicked(MouseEvent e) { 
		if(this.targetPanel != null){
			GameView parentFrame = (GameView) SwingUtilities.getWindowAncestor(this);
			parentFrame.swapView(this.targetPanel);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(isActive)
			this.hover = true;
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(isActive)
			this.hover = false;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(isActive)
			this.clic = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(isActive)
			this.clic = false;
		
	}


}
