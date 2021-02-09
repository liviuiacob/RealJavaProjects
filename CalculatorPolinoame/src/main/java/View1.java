import javax.swing.*;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View1 extends JFrame{
	public static boolean isNumeric(String str) {
	    return str.matches("[+-]*\\d*\\.?\\d+");
	}
	private JButton b1 = new JButton("+");
	private JButton b2 = new JButton("-");
	private JButton b3 = new JButton("*");
	
	private JTextField tf = new JTextField(17);
	private JTextField tf1 = new JTextField(17);
	private JTextField tf2 = new JTextField(50);
	
	private JLabel l2 = new JLabel ("Result");
	private JLabel l1 = new JLabel ("P2");
	private JLabel l = new JLabel ("P1");
	
	
	
	public View1(){ 
	    setTitle("Calculator Polinoame");
	    setSize(700, 200);
	    setLocation(new Point(100, 200));
	    setResizable(false);
	    
	    initComponent();    
	    initEvent();    
	  }
	
	private void initComponent(){
		getContentPane().setLayout(null);
		b1.setBounds(239, 6, 42, 23);
		
		getContentPane().add(b1);
		b2.setBounds(281, 6, 40, 23);
		getContentPane().add(b2);
		b3.setBounds(321, 6, 40, 23);
		getContentPane().add(b3);
		tf.setBounds(79, 6, 152, 23);
		
		getContentPane().add(tf);
		tf1.setBounds(388, 6, 152, 23);
		getContentPane().add(tf1);
		tf2.setBounds(79, 43, 461, 23);
		getContentPane().add(tf2);
		l.setBounds(52, 10, 17, 23);
		
		getContentPane().add(l);
		l1.setBounds(371, 10, 17, 23);
		getContentPane().add(l1);
		l2.setBounds(29, 43, 40, 23);
		getContentPane().add(l2);
		
		
	}
	
	private void initEvent() {
	
		this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		       System.exit(1);
		      }
		    });
		
		b1.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) {
		        b1Plus(e);
		      }
		    });
		b2.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) {
		        b2Minus(e);
		      }
		    });
		b3.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) {
		        b3Multiply(e);
		      }
		    });

	}
	
	private void b1Plus(ActionEvent evt) {
		Rejex ba = new Rejex();
    	Polynomial a = new Polynomial();
    	a = ba.rez(tf.getText());
    	Polynomial b = new Polynomial();
    	b = ba.rez(tf1.getText());
    	tf2.setText(a.sum(b).toString());
	}
	private void b2Minus(ActionEvent evt){
		Rejex ba = new Rejex();
    	Polynomial a = new Polynomial();
    	a = ba.rez(tf.getText());
    	Polynomial b = new Polynomial();
    	b = ba.rez(tf1.getText());
    	tf2.setText(a.dif(b).toString());
	}
	private void b3Multiply(ActionEvent evt) {
		Rejex ba = new Rejex();
    	Polynomial a = new Polynomial();
    	a = ba.rez(tf.getText());
    	Polynomial b = new Polynomial();
    	b = ba.rez(tf1.getText());
    	tf2.setText(a.prod(b).toString());
	}
	

}
