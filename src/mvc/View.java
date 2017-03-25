package mvc;

import java.awt.*;
import java.lang.reflect.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class View extends JFrame
{
    
	/**
	 * Constructor to instantiate the class, used in Controller.java
	 * @param controller
	 */
    public View(Controller controller)
    {
    	
    }
    
    /**
     * This method will ask the user for the size of the board. I.e a size of n will yield
     * a board n x n. 
     * @return user input
     */
    public String getBoardSize()
    {
    	String str = JOptionPane.showInputDialog("What is the size of the board?");
		return str;
    }  
    
    /**
     * The main display of the application. 
     */
    public void display()
    {       
		JFrame frame = new JFrame("Trominoes Puzzle");
	       
        frame.setSize(800,500);
        frame.setLayout(new FlowLayout());      
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}