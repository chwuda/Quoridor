package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MenuPanel extends JPanel{

	
	public MenuPanel(){
		super();
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(-16, 74, 929, 165);
		
		JPanel cube = new JPanel();
		cube.setBounds(281, 159, 410, 561);
		cube.setLayout(new GridLayout(2,2,30,30));
		cube.setBorder(BorderFactory.createEmptyBorder(150, 10,10,10));
		CustomButton resumeButton = new CustomButton("Continuer",new ImageIcon("resources/icons/resume_white.png"),false);
		cube.add(resumeButton);
		CustomButton playButton = new CustomButton("Nouvelle Partie",new ImageIcon("resources/icons/play_white.png"));
		playButton.setTargetPanel("game");
		cube.add(playButton);
		CustomButton settingsButton = new CustomButton("Options",new ImageIcon("resources/icons/settings_white.png"));
		cube.add(settingsButton);
		CustomButton aboutButton = new CustomButton("À propos",new ImageIcon("resources/icons/info_white.png"));
		cube.add(aboutButton);
		setLayout(null);
		
		this.add(titlePanel);
		
		JLabel title = new JLabel("quoridor",SwingConstants.CENTER);
		titlePanel.add(title);
		title.setForeground(new Color(236, 240, 241));
		title.setFont(Settings.getTitleFont());
		title.setOpaque(true);
		title.setBackground(new Color(44, 62, 80));
		title.setPreferredSize(new Dimension(900,150));
		title.setBorder(new EmptyBorder(-10, 10, 10, 20));
		this.add(cube);
		
		this.setBackground(new Color(236, 240, 241));
	}

}
