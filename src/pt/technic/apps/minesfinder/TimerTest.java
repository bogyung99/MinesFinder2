package pt.technic.apps.minesfinder;

import java.awt.*;

import javax.swing.*;

public class TimerTest extends JPanel {
	public JLabel timerLabel = null;
    public JLabel secondLabel = null;
    public TimerTest()
    {
        timerLabel = new JLabel("0 : ", JLabel.CENTER);
        timerLabel.setFont(new Font("Gothic",Font.ITALIC,40));
        
        secondLabel = new JLabel("0", JLabel.CENTER);
        secondLabel.setFont(new Font("Gothic",Font.ITALIC,40));
        
        this.add(timerLabel);
        this.add(secondLabel);
    }
    
    

}

