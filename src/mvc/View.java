package mvc;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View
{
	private Controller myController;
	private ButtonListener[][] mySquareListener;
	private JLabel[][] mySquareLabels;
	private int mySize;

	/**
	 * Constructor to instantiate the class, used in Controller.java
	 * 
	 * @param controller
	 */
	public View(Controller controller) 
	{
		myController = controller;
	}

	/**
	 * This method will ask the user for the size of the board. I.e a size of n
	 * will yield a board n x n.
	 * 
	 * @return user input
	 */
	public String askBoardSize() 
	{
		return (JOptionPane.showInputDialog("What is the size of the board?"));
	}
	
	public void disableLabels()
	{
		for (int i = 0; i < mySize; i++) 
		{
			for (int j = 0; j < mySize; j++) 
			{
				mySquareLabels[i][j].removeMouseListener(mySquareListener[i][j]);
			}
		}		
	}
	
	public void placeMissingSquare(int x, int y)
	{
		mySquareLabels[x][y].setIcon(new ImageIcon("src/colors/5.png"));

	}

	/**
	 * The main display of the application.
	 */
	public void display(int size) 
	{
		// Set size of board
		mySize = size;
		
		// Title of the application
		JFrame frame = new JFrame("Trominoes Puzzle");

		// Properties of the window
		frame.setSize(800, 500);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Grid panel
		JPanel grid = new JPanel(new GridLayout(mySize, mySize));

		// Create an array of buttons
		mySquareLabels  = new JLabel[mySize][mySize];

		// Assign a button to [i][j] where i and j are 'coordinates.
		// then add it to the grid. 
		for (int i = 0; i < mySize; i++) 
		{
			for (int j = 0; j < mySize; j++) 
			{
				mySquareLabels[i][j] = new JLabel(i + " " + j);
				mySquareLabels[i][j].setPreferredSize(new Dimension(20,20));
				Random ran = new Random();
				int x = ran.nextInt(4);
				mySquareLabels[i][j].setIcon(new ImageIcon("src/colors/" + x +".png"));

				grid.add(mySquareLabels[i][j]);
			}
		}
		
		// Create an array of listeners
		mySquareListener = new ButtonListener[mySize][mySize];
		
		// Add panels
		frame.add(grid);
		
		// Set Visibility
		frame.setVisible(true);
		
        // Associate listeners
        this.associateListeners(myController);
	}

	/**
	 * Associates each component's listener with the controller and the correct
	 * method to invoke when triggered.
	 *
	 * <pre>
	 * pre:  the controller class has to be instantiated
	 * post: all listeners have been associated to the controller
	 *       and the method it must invoke
	 * </pre>
	 */
	public void associateListeners(Controller controller) 
	{
		Class<? extends Controller> controllerClass;
		Method setMissingSquareMethod;
		Class<?>[] classArgs;

		controllerClass = controller.getClass();

		setMissingSquareMethod = null;

		classArgs = new Class[1];

		try 
		{
			classArgs[0] = Class.forName("java.lang.String");
		} 
		catch (ClassNotFoundException e) 
		{
			String error;
			error = e.toString();
			System.out.println(error);
		}
		try 
		{
			setMissingSquareMethod = controllerClass.getMethod("setMissingSquare", classArgs);
		} 
		catch (NoSuchMethodException exception) 
		{
			String error;
			error = exception.toString();
			System.out.println(error);
		} 
		catch (SecurityException exception) 
		{
			String error;
			error = exception.toString();
			System.out.println(error);
		}

		int i, j;
		String[] args;

		for (i = 0; i < mySize; i++) 
		{
			for (j = 0; j < mySize; j++) 
			{
				args = new String[1];
				args[0] = new String(i+""+j);
				mySquareListener[i][j] = new ButtonListener(myController, setMissingSquareMethod, args);
				mySquareLabels[i][j].addMouseListener(mySquareListener[i][j]);
			}
		}
	}
}
