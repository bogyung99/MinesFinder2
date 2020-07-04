package pt.technic.apps.minesfinder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

class TimerThread extends Thread 
{
    public static int min = 0;
    public static int sec = 1;
    JLabel timerLabel = null;
    JLabel secondLabel = null;
    
    public TimerThread(JLabel secondLabel ,JLabel timerLabel)
    {
        this.timerLabel = timerLabel;
        this.secondLabel = secondLabel;
 
    }
    public void run()
    {
    	min = 0;
    	sec = 1;
    	int t = 1000;
    		
        while(true)
        {
        	if(Minefield.isBUSTED == 1)
        		t = 750;

        	if(MinesFinder.level == 1) {	
        		if(min == 1)
        			t = 1500;
        		else if(min == 1 && sec == 20)
        			t = 1000;
        		
        		if(min == 2) {
        			JOptionPane.showMessageDialog(null, "Oh, Time is over",
                        "Time out!", JOptionPane.INFORMATION_MESSAGE);
        			System.exit(0);
        		}
        	}
        	else if(MinesFinder.level == 2) {	
        		if(min == 2)
        			t = 1500;
        		else if(min == 2 && sec == 20)
        			t = 1000;
        		
        		if(min == 4) { 
        			JOptionPane.showMessageDialog(null, "Oh, Time is over",
                        "Time out!", JOptionPane.INFORMATION_MESSAGE);
        			System.exit(0);
        		}
        	}
        	else if(MinesFinder.level == 3) {	
        		if(min == 2 || min == 4)
        			t = 1500;
        		else if((min == 2 && sec == 20) || (min == 4 && sec == 20))
        			t = 1000;
        		
        		if(min == 6) {
        			JOptionPane.showMessageDialog(null, "Oh, Time is over",
                        "Time out!", JOptionPane.INFORMATION_MESSAGE);
        			System.exit(0);
        		}
        	}
        	
        	if(sec == 60) {
        		sec = 0;
        		min++;
        	}
        	timerLabel.setText(""+ min + " : ");
            secondLabel.setText("" + sec);   
            
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            sec++;
        }
                
    }
    

}
