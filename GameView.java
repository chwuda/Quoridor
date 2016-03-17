package main;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JFrame{
	
	private CardLayout cardLayout = new CardLayout();
	private JPanel mainPanel = new JPanel();
	
	public GameView(String title, int width, int height, boolean resizable){
			
		this.setTitle(title);
		
		this.setSize(width, height);
		
		this.setLocationRelativeTo(null);
		
		this.setResizable(resizable);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	
		this.add(mainPanel);
		
		MenuPanel menu = new MenuPanel();
		GamePanel game = new GamePanel();
		
		mainPanel.setLayout(cardLayout);
		mainPanel.add(menu, "menu");
		mainPanel.add(game, "game");
		
		
		
	}
	public void swapView(String name){
		cardLayout.show(mainPanel, name);
	}
	

}
