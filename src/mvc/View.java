package mvc;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class View
{
	private Border 			   myBorder;

	private ButtonListener[][] mySquareListener;
	
	private ButtonListener     mySolveListener,
							   myResetListener;

	private Color 			   myRandomColor;

	private Color[]            myColors;

	private Controller         myController;

	private JFrame 			   myFrame;

	private int 			   myBoardSize;

	private JButton            mySolveButton,
						       myResetButton;

	private JLabel[][]         mySquareLabels;

	private JLabel             myInformationLabel;

	private JPanel             myBoardPanel,
							   myNavigationPanel;

	private Random 			   myRandom;

	/**
	 * Constructor to instantiate the class, used in Controller.java
	 * 
	 * @param controller
	 */
	public View(Controller controller) 
	{
		// Establish the controller
		myController = controller;

		// Declare the JmyFrame with a title of "Trominoes Puzzle".
		myFrame = new JFrame("Trominoes Puzzle");

		// Establish Panels
		myBoardPanel       = new JPanel();
		myNavigationPanel  = new JPanel();

		// Establish Buttons
		mySolveButton 	   = new JButton("Solve");
		myResetButton      = new JButton("Reset");

		// Establish Labels
		myInformationLabel = new JLabel();

		// Associate listeners
		this.associateListeners(myController);
	}

	/**
	 * The main display of the application.
	 */
	public void display(int size) 
	{
		// Set size of board
		myBoardSize = size;

		// Establish JLabel array that will act as the board grid for the myBoard Panel
		mySquareLabels  = new JLabel[myBoardSize][myBoardSize];

		// Establish an array of listeners used in selecting a missing square.
		mySquareListener = new ButtonListener[myBoardSize][myBoardSize];
		
		// Properties of the JFrame
		myFrame.setSize(900, 500);
		myFrame.setLayout(new FlowLayout());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Properties of the myBoard Panel
		myBoardPanel.setLayout(new GridLayout(myBoardSize, myBoardSize));

		// Method that generates the board. See method newBoard() for more information.
		newBoard();

		// Add listeners to buttons
		mySolveButton.addMouseListener(mySolveListener);
		myResetButton.addMouseListener(myResetListener);

		// Add items to myNavigationPanel
		myNavigationPanel.add(mySolveButton); 
		myNavigationPanel.add(myResetButton);
		myNavigationPanel.add(myInformationLabel);
		
		// Set the array of colors
	    setColorArray(); 

		// Add panels to the frame
		myFrame.add(myBoardPanel);
		myFrame.add(myNavigationPanel);

		// Set Visibility
		myFrame.setVisible(true);

		// Frame Sizing/Positioning/Compacting
		myFrame.setLocationRelativeTo(null);
		
		// Set buttons to disabled as default. 
		// (We need to choose a missing square before being able to solve the puzzle)
		mySolveButton.setVisible(false);
		myResetButton.setVisible(false);

		// Associate listeners
		this.associateListeners(myController);
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
	 *   I. This method is called when a user selects a label on the myBoard as 
	 *      the missing piece.
	 *      
	 *  II. Once a missing piece is set we no longer need action listeners on
	 *      any of the labels. So we remove all of them through this method.
	 *      
	 */
	public void disableBoard()
	{
		for (int i = 0; i < myBoardSize; i++) 
		{
			for (int j = 0; j < myBoardSize; j++) 
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
		mySquareLabels[x][y].setBackground(Color.WHITE);

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
	 * The purpose of this method is to generate an array of randomized colors
	 * to be used in tiling the board.
	 */
	public void setColorArray()
	{
		myColors = new Color[100];

		for(int i = 1; i <100; i++)
		{
			myColors[i] = newRandomColor();
		}
	}

	/**
	 * This method is called from the Controller when the 'solve' button is engaged. 
	 * 
	 * @param x
	 * @param y
	 */
	public void setTiles(int x, int y)
	{
		for(int i = 1; i < (myBoardSize*myBoardSize); i++)
		{
			if(myController.getGrid()[x][y] == i)
			{
				mySquareLabels[x][y].setBackground(myColors[i]);
				return;
			}
		}
	}

	/**
	 *  This method is used in creating a new game board. It loops through the
	 *  size of the board and creates a square in the form of a JLabel. The output
	 *  is then displayed as a grid.
	 */
	public void newBoard()
	{
		for (int i = 0; i < myBoardSize; i++) 
		{
			for (int j = 0; j < myBoardSize; j++) 
			{
				mySquareLabels[i][j] = new JLabel();
				mySquareLabels[i][j].setPreferredSize(new Dimension(30,30));
				myBorder = LineBorder.createBlackLineBorder();
				mySquareLabels[i][j].setBackground(Color.GRAY);
				mySquareLabels[i][j].setBorder(myBorder);
				mySquareLabels[i][j].setOpaque(true);
				myBoardPanel.add(mySquareLabels[i][j]);
			}
		}
	}
	
	/**
	 * This method is used to reset the board so that we may choose a new missing square.
	 */
	public void resetBoard()
	{
		for (int i = 0; i < myBoardSize; i++) 
		{
			for (int j = 0; j < myBoardSize; j++) 
			{
				myBorder = LineBorder.createBlackLineBorder();
				mySquareLabels[i][j].setBackground(Color.GRAY);
				mySquareLabels[i][j].setOpaque(true);
			}
		}	
	}
	
	/**
	 * Method to toggle buttons between reset and selecting a missing square.
	 * I.e we shouldn't be able to solve or reset an empty grid. Additionally
	 * we shouldn't be able to solve a grid without a missing square.
	 */
	public void toggleButtons()
	{
		mySolveButton.setVisible(!mySolveButton.isVisible());
		myResetButton.setVisible(!myResetButton.isVisible());
	}
	
	public void setInformationLabel(String message)
	{
		myInformationLabel.setText(message);
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
		Method setMissingSquareMethod,
		solveMethod,
		resetMethod;
		Class<?>[] classArgs;

		controllerClass = controller.getClass();

		setMissingSquareMethod = null;
		solveMethod			   = null;
		resetMethod 		   = null;

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
			solveMethod    = controllerClass.getMethod("solve",(Class<?>[])null);
			resetMethod    = controllerClass.getMethod("reset",(Class<?>[])null);
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

		mySolveListener = new ButtonListener(controller, solveMethod, null);
		myResetListener = new ButtonListener(controller, resetMethod, null);

		int i, j;
		String[] args;

		for (i = 0; i < myBoardSize; i++) 
		{
			for (j = 0; j < myBoardSize; j++) 
			{
				args = new String[1];
				args[0] = new String(i+""+j);
				mySquareListener[i][j] = new ButtonListener(myController, setMissingSquareMethod, args);
				mySquareLabels[i][j].addMouseListener(mySquareListener[i][j]);
			}
		}
	}
}
