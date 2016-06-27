package com.elevatorsimulator.processor;
/*
Core SWING Advanced Programming 
By Kim Topley
ISBN: 0 13 083292 8       
Publisher: Prentice Hall  
*/

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Main {
  public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception evt) {}
  
    JLabel l;
    JTextField t;
    JButton b;
    JFrame f = new JFrame("Text Accelerator Example");
    Container cp = f.getContentPane();
    cp.setLayout(new GridBagLayout());
    cp.setBackground(UIManager.getColor("control"));
    GridBagConstraints c = new GridBagConstraints();

    c.gridx = 0;
    c.gridy = GridBagConstraints.RELATIVE;
    c.gridwidth = 1;
    c.gridheight = 1;
    c.insets = new Insets(2, 2, 2, 2);
    c.anchor = GridBagConstraints.EAST;

    cp.add(l = new JLabel("Number of floors:", SwingConstants.RIGHT), c);
    l.setDisplayedMnemonic('n');
    cp.add(l = new JLabel("Number of Elevator:", SwingConstants.RIGHT), c);
    l.setDisplayedMnemonic('h');
   /* cp.add(l = new JLabel("City:", SwingConstants.RIGHT), c);
    l.setDisplayedMnemonic('c');
    cp.add(l = new JLabel("State/County:", SwingConstants.RIGHT), c);
    l.setDisplayedMnemonic('s');
    cp.add(l = new JLabel("Zip/Post code:", SwingConstants.RIGHT), c);
    l.setDisplayedMnemonic('z');
    cp.add(l = new JLabel("Telephone:", SwingConstants.RIGHT), c);
    l.setDisplayedMnemonic('t');*/
    cp.add(b = new JButton("Clear"), c);
    b.setMnemonic('l');

    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1.0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;

   final  IntPutText noOfFloorsIntPutText= new IntPutText();
    cp.add(noOfFloorsIntPutText, c);
    noOfFloorsIntPutText.setFocusAccelerator('n');
    c.gridx = 1;
    c.gridy = GridBagConstraints.RELATIVE;
    
    final IntPutText noOfElevatorIntPutText= new IntPutText();
    cp.add(noOfElevatorIntPutText, c);
    noOfElevatorIntPutText.setFocusAccelerator('h');
   /* cp.add(t = new IntPutText(), c);
    t.setFocusAccelerator('c');
    cp.add(t = new IntPutText(), c);
    t.setFocusAccelerator('s');
    cp.add(t = new IntPutText(), c);
    t.setFocusAccelerator('z');
  
    cp.add(t = new IntPutText(), c);
    t.setFocusAccelerator('t');*/
    c.weightx = 0.0;
    c.fill = GridBagConstraints.NONE;
    JButton jbutton=new JButton("OK"); 
    cp.add(jbutton, c);
    b.setMnemonic('o');
    
    jbutton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        System.out.println(" No of Floors "+noOfFloorsIntPutText.getText() + " No of Elevator " + noOfElevatorIntPutText.getText());
	        Simulator simulator=new Simulator();
	        simulator.init(noOfFloorsIntPutText.getText(), noOfElevatorIntPutText.getText());
	      }
	    });
    
   

    f.pack();
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        System.exit(0);
      }
    });
    f.setVisible(true);
  }
}





class IntPutText extends  JTextField{
	  public void processKeyEvent(KeyEvent ev) {
	    char c = ev.getKeyChar();
	    try {
	      // Ignore all non-printable characters. Just check the printable ones.
	      if (c > 31 && c < 127) {
	        Integer.parseInt(c + "");
	      }
	      super.processKeyEvent(ev);
	    }
	    catch (NumberFormatException nfe) {
	      // Do nothing. Character inputted is not a number, so ignore it.
	    }
	  }
	};