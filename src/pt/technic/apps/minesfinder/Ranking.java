package pt.technic.apps.minesfinder;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ranking extends javax.swing.JFrame{
	
	public static JLabel Easy1 = new JLabel();
	public static JLabel Easy2= new JLabel();
	public static JLabel Easy3= new JLabel();
	public static JLabel Easy1Points= new JLabel();
	public static JLabel Easy2Points= new JLabel();
	public static JLabel Easy3Points= new JLabel();
	
	public static JLabel Medium1= new JLabel();
	public static JLabel Medium2= new JLabel();
	public static JLabel Medium3= new JLabel();
	public static JLabel Medium1Points= new JLabel();
	public static JLabel Medium2Points= new JLabel();
	public static JLabel Medium3Points= new JLabel();
	
	public static JLabel Hard1= new JLabel();
	public static JLabel Hard2= new JLabel();
	public static JLabel Hard3= new JLabel();
	public static JLabel Hard1Points= new JLabel();
	public static JLabel Hard2Points= new JLabel();
	public static JLabel Hard3Points= new JLabel();
	
	public Ranking() {
		
		SaveRankI s = new SaveRankI();
		
		JFrame frame = new JFrame("Ranking");
		frame.setPreferredSize(new Dimension(600, 400));
		
		Container contentPane = frame.getContentPane();
		JLabel label = new JLabel("Ranking Chart");
		label.setBackground(new java.awt.Color(250, 180, 120));
		label.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
		label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		label.setOpaque(true);
		
		contentPane.add(label, BorderLayout.NORTH);
		
		JPanel Case = new JPanel();
		Case.setBackground(new java.awt.Color(250, 245, 120)); 
		
		JLabel labelEasy = new JLabel("Easy Mode");
		labelEasy.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        labelEasy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        
        if(s.EasyN.get(0) == null)
        	Easy1.setText("Player");
        Easy1.setText(s.EasyN.get(0).toString());       
        if(s.EasyL.get(0) == 0)
        	Easy1Points.setText("9999");
        Easy1Points.setText("" + s.EasyL.get(0).toString());
        
        if(s.EasyN.get(1) == null)
        	Easy2.setText("Player");
        Easy2.setText(s.EasyN.get(1).toString());
        if(s.EasyL.get(1) == 0)
        	Easy2Points.setText("9999");
        Easy2Points.setText("" + s.EasyL.get(1).toString());
        
        if(s.EasyN.get(2) == null)
        	Easy3.setText("Player");
        Easy3.setText(s.EasyN.get(2).toString());
        if(s.EasyL.get(2) == 0)
        	Easy3Points.setText("9999");
        Easy3Points.setText("" + s.EasyL.get(2).toString());
        
        JLabel labelMedium = new JLabel("Medium Mode");
        labelMedium.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        labelMedium.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        if(s.MediumN.get(0) == null)
        	Medium1.setText("Player");
        Medium1.setText(s.MediumN.get(0).toString());       
        if(s.MediumL.get(0) == 0)
        	Medium1Points.setText("9999");
        Medium1Points.setText("" + s.MediumL.get(0).toString());
        
        if(s.MediumN.get(1) == null)
        	Medium2.setText("Player");
        Medium2.setText(s.MediumN.get(1).toString());       
        if(s.MediumL.get(1) == 0)
        	Medium2Points.setText("9999");
        Medium2Points.setText("" + s.MediumL.get(1).toString());
        
        if(s.MediumN.get(2) == null)
        	Medium3.setText("Player");
        Medium3.setText(s.MediumN.get(2).toString());       
        if(s.MediumL.get(2) == 0)
        	Medium3Points.setText("9999");
        Medium3Points.setText("" + s.MediumL.get(2).toString());


        JLabel labelHard = new JLabel("Hard Mode");
        labelHard.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        labelHard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        if(s.HardN.get(0) == null)
        	Hard1.setText("Player");
        Hard1.setText(s.MediumN.get(0).toString());       
        if(s.HardL.get(0) == 0)
        	Hard1Points.setText("9999");
        Hard1Points.setText("" + s.HardL.get(0).toString());
        
        if(s.HardN.get(1) == null)
        	Hard2.setText("Player");
        Hard2.setText(s.HardN.get(1).toString());       
        if(s.HardL.get(1) == 0)
        	Hard2Points.setText("9999");
        Hard2Points.setText("" + s.HardL.get(1).toString());
        
        if(s.HardN.get(2) == null)
        	Hard3.setText("Player");
        Hard3.setText(s.HardN.get(2).toString());       
        if(s.HardL.get(2) == 0)
        	Hard3Points.setText("9999");
        Hard3Points.setText("" + s.HardL.get(2).toString());
		
        javax.swing.GroupLayout Layout = new javax.swing.GroupLayout(Case);
        Case.setLayout(Layout);    
        Layout.setHorizontalGroup(
            Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelEasy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Layout.createSequentialGroup()
                        .addComponent(Easy1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                        .addComponent(Easy1Points))
                        .addGroup(Layout.createSequentialGroup()
                        .addComponent(Easy2)
                    	.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                    	.addComponent(Easy2Points))
                        .addGroup(Layout.createSequentialGroup()
                    	.addComponent(Easy3)
                		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                		.addComponent(Easy3Points))
                    .addComponent(labelMedium, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Layout.createSequentialGroup()
                    	.addComponent(Medium1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                        .addComponent(Medium1Points))
                        .addGroup(Layout.createSequentialGroup()
                        .addComponent(Medium2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                        .addComponent(Medium2Points))
                        .addGroup(Layout.createSequentialGroup()
                    	.addComponent(Medium3)
                    	.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                    	.addComponent(Medium3Points))
                    .addComponent(labelHard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Layout.createSequentialGroup()
                    		.addComponent(Hard1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                            .addComponent(Hard1Points))
                            .addGroup(Layout.createSequentialGroup()
                            .addComponent(Hard2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                            .addComponent(Hard2Points))
                            .addGroup(Layout.createSequentialGroup()
                        	.addComponent(Hard3)
                        	.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, 15)
                        	.addComponent(Hard3Points)))
                .addContainerGap())
        );
        Layout.setVerticalGroup(
            Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Layout.createSequentialGroup()
                .addComponent(label)
                .addGap(10, 10, 10) //18
                .addComponent(labelEasy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Easy1Points)
                    .addComponent(Easy1))
                    .addGap(5, 5, 5)
                    .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)                
                	.addComponent(Easy2Points)
                	.addComponent(Easy2))
                	.addGap(5, 5, 5)
                	.addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            		.addComponent(Easy3Points)
            		.addComponent(Easy3))
                .addGap(10, 10, 10)
                .addComponent(labelMedium)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                	.addComponent(Medium1Points)
                    .addComponent(Medium1))
                    .addGap(5, 5, 5)
                    .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Medium2Points)
                    .addComponent(Medium2))
                    .addGap(5, 5, 5)
                    .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                	.addComponent(Medium3Points)
                	.addComponent(Medium3))
                .addGap(10, 10, 10)
                .addComponent(labelHard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                	.addComponent(Hard1Points)
                    .addComponent(Hard1))
                    .addGap(5, 5, 5)
                    .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Hard2Points)
                    .addComponent(Hard2))
                    .addGap(5, 5, 5)
                    .addGroup(Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                	.addComponent(Hard3Points)
                	.addComponent(Hard3))
                .addGap(0, 169, Short.MAX_VALUE))
        );
        
        contentPane.add(Case, BorderLayout.CENTER);
        
        
        
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}	
}


