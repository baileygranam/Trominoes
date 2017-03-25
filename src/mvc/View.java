package mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View
{
    
	private static JButton buttons[]; //create 9 buttons
	private Controller myController;

	/**
	 * Constructor to instantiate the class, used in Controller.java
	 * @param controller
	 */
    public View(Controller controller)
    {
    	Controller myController = controller;
    }
    
    /**
     * This method will ask the user for the size of the board. I.e a size of n will yield
     * a board n x n. 
     * 
     * @return user input
     */
    public String askBoardSize()
    {
    	return(JOptionPane.showInputDialog("What is the size of the board?"));	
    }

    /**
     * The main display of the application. 
     */
    public void display(int gridSize)
    {           	
    	// Title of the application
		JFrame frame = new JFrame("Trominoes Puzzle");
		
		// Buttons to apply to the grid
    	JButton buttons[] = new JButton[gridSize*gridSize];

    	// Characteristics of the window
        frame.setSize(800,500);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout (new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Grid panel
		JPanel panel = new JPanel(); 
		
		panel.setBorder (BorderFactory.createLineBorder (Color.gray, 3));
		panel.setBackground (Color.white);
		panel.setLayout (new GridLayout (gridSize,gridSize));
		
		// Adding buttons to grid
		for(int i=0; i<gridSize*gridSize; i++){ //placing the button onto the board
			buttons[i] = new JButton();
		    buttons[i].setPreferredSize(new Dimension(45, 45));
			panel.add(buttons[i]);			
		}

		// Add panel to frame
		frame.getContentPane().add (panel);
		frame.pack();

	}
}