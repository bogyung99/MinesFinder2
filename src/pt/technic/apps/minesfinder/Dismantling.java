package pt.technic.apps.minesfinder;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Dismantling extends javax.swing.JFrame {
    public static int isGameWin = 0;
	
	int mix;
	String success = "";
	
	public Dismantling() {
		Timer timer = new Timer();
		
		JFrame frame = new JFrame("Dismantling Mines");
		frame.setPreferredSize(new Dimension(700, 500));
		
		Container contentPane = frame.getContentPane();
		JLabel label = new JLabel("Choose lucky Line(just one time!)");
		label.setBackground(new java.awt.Color(136, 135, 217));
		label.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
		label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		label.setOpaque(true);
		
		JPanel Case = new JPanel();
		Case.setLayout(new java.awt.GridLayout(0, 3));
		
		JButton button1 = new JButton("Line 1");
		JButton button2 = new JButton("Line 2");
		JButton button3 = new JButton("Line 3");
		
		contentPane.add(label, BorderLayout.NORTH);
		
		button1.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/¼±1.png"))); // NOI18
		button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JButton button1 = (JButton) evt.getSource();                    
            	button1.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/Àß¸° ¼±1.png")));
            
            	
            	TimerTask task = new TimerTask() {
        			public void run() {
        				startMix();
                    	if(success == "win") {
                    		button1.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/¾ÈÀü.png")));
                    		JOptionPane.showMessageDialog(null, "WoW you are safe, keep going!",
                                    "Lucky!", JOptionPane.INFORMATION_MESSAGE);
                    		isGameWin = 0;
                    		frame.setVisible(false);
                    	}
                    	else {
                    		button1.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/ÆøÅº.png")));
                    	
                                Sound bomb = new Sound();
                           try {
                              bomb.bombPlay();

                           } catch(Exception ex) {
                           }
                           
                    		JOptionPane.showMessageDialog(null, "Oh, a mine broke",
                                    "Lost!", JOptionPane.INFORMATION_MESSAGE);
                    		isGameWin = 1;
                    		//dispose();
                    		System.exit(0);
                    	}
                    	timer.cancel();
        			}
        		};
            	timer.schedule(task, 1000);
	
            }
        });
		
		button2.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/¼±2.png"))); // NOI18
		button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JButton button2 = (JButton) evt.getSource();
            	button2.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/Àß¸° ¼±2.png")));
            	
            	TimerTask task = new TimerTask() {
        			public void run() {
        				startMix();
                    	
                    	if(success == "win") {
                    		button2.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/¾ÈÀü.png")));
                    		JOptionPane.showMessageDialog(null, "WoW you are safe, keep going!",
                                    "Lucky!", JOptionPane.INFORMATION_MESSAGE);
                    		isGameWin = 0;
                    		setVisible(false);
                    	}
                    	else {
                    		button2.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/ÆøÅº.png")));
                    		JOptionPane.showMessageDialog(null, "Oh, a mine broke",
                                    "Lost!", JOptionPane.INFORMATION_MESSAGE);
                    		isGameWin = 1;
                    		System.exit(0);
                    	}
                    	timer.cancel();
        			}
        		};
            	timer.schedule(task, 1000);
            }
        });
		
		button3.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/¼±3.png"))); // NOI18
		button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JButton button3 = (JButton) evt.getSource();
            	button3.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/Àß¸° ¼±3.png")));
            	
            	TimerTask task = new TimerTask() {
        			public void run() {
        				startMix();
                    	
                    	if(success == "win") {
                    		button3.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/¾ÈÀü.png")));
                    		JOptionPane.showMessageDialog(null, "WoW you are safe, keep going!",
                                    "Lucky!", JOptionPane.INFORMATION_MESSAGE);
                    		isGameWin = 0;
                    		frame.setVisible(false);
                    	}
                    	else {
                    		GameWindow gameWindow = new GameWindow();
                    		button3.setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/ÆøÅº.png")));
                    		JOptionPane.showMessageDialog(null, "Oh, a mine broke",
                                    "Lost!", JOptionPane.INFORMATION_MESSAGE);
                    		isGameWin = 1;
                    		System.exit(0);
                    	}
                    	timer.cancel();
        			}
        		};
            	timer.schedule(task, 1000);
            }
        });
		
		Case.add(button1);
        Case.add(button2);
        Case.add(button3);
        
        contentPane.add(Case, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void startMix(){
		  mix = (int)(Math.random()*3); //È®·ü 33ÆÛ
		  switch(mix){
		  case 0 : success = "win"; break;
		  case 1 : success = "win"; break;
		  default : success = "win"; break;
		  }
	}

}