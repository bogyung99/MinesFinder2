package pt.technic.apps.minesfinder;


import java.awt.event.ActionListener;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;

public class Option extends javax.swing.JFrame {
	JLabel lbl,la1,la2,la3;
    JTextField l1, l2, l3;
    JPanel la1Panel,la2Panel,la3Panel,bPanel;
    JButton b;
    JTextArea content;
    
    static int n1=-1;
    static int n2=-1;
    static int n3=-1;
    
	public Option() {
        
        setLayout( new FlowLayout() );
        
        EtchedBorder eborder =  new EtchedBorder();
           
        lbl = new JLabel( "Setting your Option" );
        
        lbl.setBorder(eborder);
        
        add( lbl );
        
        la1Panel = new JPanel();
        la2Panel = new JPanel();
        la3Panel = new JPanel();
        la1 = new JLabel("맵 높이");
        la2 = new JLabel("맵 넓이");
        la3 = new JLabel("지뢰 개수");
        
        l1 = new JTextField(10);
        l2 = new JTextField(10);
        l3 = new JTextField(10);
        la1Panel.add(la1);
        la1Panel.add(l1);
        la2Panel.add( la2 );
        la2Panel.add( l2 );
        la3Panel.add( la3 );
        la3Panel.add( l3 );
        
        bPanel = new JPanel();
        b = new JButton("확인");
        bPanel.add( b );
        add(la1Panel);
        add(la2Panel);
        add(la3Panel);
        add(bPanel);

        JScrollPane s= new JScrollPane(content);
        add(s);
        
        b.addActionListener(new Listener());

        setSize( 250, 350 );
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
	}
	
	class Listener implements ActionListener{

		        @Override		      
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		            //버튼을 누르면 이쪽으로 제어가 이동
		        	String a1 = l1.getText().toString();
		        	String a2 = l2.getText().toString();
		        	String a3 = l3.getText().toString();		
		        	
		        	n1 = Integer.parseInt(a1);
		        	n2 = Integer.parseInt(a2);
		        	n3 = Integer.parseInt(a3);	
		        	
		        	JOptionPane.showMessageDialog(null, "입력 되었습니다.",
                            "Setting!", JOptionPane.INFORMATION_MESSAGE);
		        	
		        	 GameWindow gameWindow = new GameWindow(new Minefield(n1, n2, n3), null ); // 창크기, 지뢰개수
		             gameWindow.setVisible(true);
		        	
		            setVisible(false);
		        }
		    }	

}
