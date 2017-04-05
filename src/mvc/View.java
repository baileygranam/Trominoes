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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class View
{
	private Controller myController;
	private ButtonListener[][] mySquareListener;
	private JLabel[][] mySquareLabels;
	private int mySize;
	private Random myRandom;
	private Color myRandomColor;
	private Border myBorder;

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
	
	
	/**
	 *   I. This method is called when a user selects a label on the grid as 
	 *      the missing piece.
	 *      
	 *  II. Once a missing piece is set we no longer need action listeners on
	 *      any of the labels. So we remove all of them through this method.
	 *      
	 */
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
	
	/**
	 * The purpose of this method is to place the missing square on the graph that
	 * will be represented as a label with a white background.
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void placeMissingSquare(int x, int y)
	{
		mySquareLabels[x][y].setIcon(new ImageIcon("src/colors/5.png"));

	}
	
	/** 
	 *  I. The purpose of this method is to generate a random color for the use
	 *     in tiling a distinct trominoe.
	 *     
	 * II. The way this method works is that it utilizes RGB colors as well as the
	 *     'Random' component. Three float values are generated through random 
	 *     numbers then plugged in to create a new color. 
	 *      
	 * @return generated color
	 */
	public Color newRandomColor()
	{
		myRandom = new Random();
		
		float r = myRandom.nextFloat();
		float g = myRandom.nextFloat();
		float b = myRandom.nextFloat();
		
		myRandomColor = new Color(r, g, b);

		return myRandomColor;
		
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

		// Create an array of labels that will be added to visualize the grid
		mySquareLabels  = new JLabel[mySize][mySize];

		// Assign a label to [i][j] where i and j are coordinates x and y,
		// then add it to the grid.
		for (int i = 0; i < mySize; i++) 
		{
			for (int j = 0; j < mySize; j++) 
			{
				mySquareLabels[i][j] = new JLabel();
				mySquareLabels[i][j].setPreferredSize(new Dimension(50,50));
			    myBorder = LineBorder.createBlackLineBorder();
				mySquareLabels[i][j].setBackground(Color.GRAY);
				mySquareLabels[i][j].setBorder(myBorder);
				mySquareLabels[i][j].setOpaque(true);
				grid.add(mySquareLabels[i][j]);
			}
		}
		
		// Create an array of listeners used in selecting a missing square.
		mySquareListener = new ButtonListener[mySize][mySize];
		
		// Create label
		JLabel myInformationLabel = new JLabel("Select a missing square..");
		
		JPanel myInformationPanel = new JPanel();
		
		myInformationPanel.add(myInformationLabel);

		
		// Add panels
		frame.add(grid);
		frame.add(myInformationPanel);
		
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
