package mvc;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View
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

		// Buttons to add to the grid
		JButton buttons[][] = new JButton[gridSize][gridSize];

		// Properties of the window
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
		for(int i=0; i<gridSize; i++){ 
			for(int j=0; j<gridSize; j++)
			{
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(45, 45));

				panel.add(buttons[i][j]);		
			}
		}

		// Message to player
		JLabel label = new JLabel();
		label.setText("Please select a missing tile...");

		// Add to frame
		frame.add(panel);
		frame.add(label);
		frame.pack();

	}

}