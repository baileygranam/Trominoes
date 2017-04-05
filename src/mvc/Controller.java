package mvc;

public class Controller
{    
	private View  myView;
	private Model myModel;
	private int   mySize;

	/**
	 *    I. Controller constructor; view must be passed in since 
	 *       controller has responsibility to notify view when 
	 *       some event takes place.
	 * 
	 *   II. The constructor creates a view and model. 
	 * 
	 *  III. Finally we call the newBoard() method within this Controller.java class.
	 *       See newBoard() method for further information.
	 */
	public Controller()
	{  
		myView = new View(this);
		myModel = new Model();    
		newBoard();
	}

	/** 
	 *    I. This method takes in a string of coordinates from the label that the user
	 *       had pressed to choose a missing square. This string is then split into
	 *       its respective x any y values and turned into numeric values instead
	 *       of chars. Next we pass these numerical x and y values to the model
	 *       to set the missing square.
	 * 
	 *   II. Additionally, we disable the labels as we no longer need the user to
	 *       choose a missing square. 
	 * 
	 *  III. Finally we place the missing square on the board through the view
	 *       via the x and y coordinates. 
	 * 
	 * @param coordinates
	 */
	public void setMissingSquare(String coordinates)
	{
		int x = Character.getNumericValue(coordinates.charAt(0));
		int y = Character.getNumericValue(coordinates.charAt(1));
		myModel.setMissingSquare(x, y);
		myView.placeMissingSquare(x, y);
		myView.toggleButtons();
		myView.setInformationLabel("Click 'solve' to complete the puzzle!");

	}

	/**
	 *    I. To create a new board we must get the size of the board from the user.
	 *       We do this by calling the getBoardSize() method within this Controller.java
	 *       class. See getBoardSize() for further information.
	 *      
	 *   II. After the user has entered a board size we will set it 
	 *       through myModel.setBoardSize() .
	 *      
	 * 
	 *  III. Finally we will display the board in the view by calling the 
	 *       myView.display(mySize) with mySize being the parameter. 
	 */
	public void newBoard()
	{
		mySize = getBoardSize();
		
		while(!myModel.setBoardSize(mySize))
		{
			mySize = getBoardSize();
		}
		
		myView.display(mySize);
		myView.setInformationLabel("Please choose a missing square...");
	
	}

	/**
	 * A simple method that will call a method in the view that will ask for the
	 * size of the board. The string the user enters will be converted to an
	 * int and returned. 
	 */
	public int getBoardSize()
	{
		return Integer.parseInt(myView.askBoardSize());
	}

	/**
	 * Method to tile the board.
	 */
	public void solve()
	{
		myModel.tromino();
		myModel.tile();
		myView.setColorArray(); 

		for(int i = 0; i < mySize; i++)
		{
			for(int j = 0; j < mySize; j++)
			{
				if(myModel.getGrid()[i][j] > 0)
				{
					myView.setTiles(i, j);
				}
			}
		}
		
		myView.setInformationLabel("Puzzle Solved!");

	}

	/**
	 * Method that resets the missing point in the model and clears the board
	 * in the view for the user to select a new missing square.
	 */
	public void reset()
	{
		myView.toggleButtons();
		myView.resetBoard();
		myModel.reset();
		
		myView.setInformationLabel("Please choose a missing square...");

	}

	/**
	 * Retrieve the 2D Grid Array to be used in the view 
	 * for tiling the board.
	 * @return 2D Grid Array
	 */
	public int[][] getGrid()
	{
		return myModel.getGrid();
	}
}