package mvc;

public class Model 
{
	
	private int myX; // X Coordinate of board
	private int myY; // Y Coordinate of board
	private int myXMissing; // X Coordinate of missing square
	private int myYMissing; // Y Coordinate of missing square
	private boolean myBoardSet; // Used in determining if the board has been set
	protected int myBoardSize; // Size of board

	/**
	 * The purpose of this constructor is to set the default coordinates of the board
	 * to (0,0). The default location of the missing piece is (0,0).
	 */
	public Model()
	{
		myX = 0;
		myY = 0;
		myXMissing = 0;
		myYMissing = 0;
		myBoardSet = false;
	}

	/**
	 * The purpose of this method is to overall set the size of the board indicated
	 * by the user's input received from the origin of view.getBoardSize().  
	 * 
	 * 1. We are going to initialize mySize. We will use this variable as a temporary
	 * 	  holder of the user's inputed board size. 
	 * 
	 * 2. We are going to try to convert the string entered by the user to an integer. If
	 * 	  the string contains anything other than a numerical value then we will catch this
	 *    exception and exit the method. Likewise, if the value entered by the user is less
	 *    than 2 OR the log base 2 of mySize modulo 1 is anything other than 0 we will exit.
	 *    
	 * 3. By exiting the method the user will then again
	 *    be asked to enter a value. [See controller constructor].
	 *    
	 * 4. Finally we will calculate/set the board size by 
	 *    taking n * n.
	 *    
	 *    I.e if a user enters a board size of 2 then it will be calculated
	 *    2 x 2.
	 * 
	 * @param str
	 */
	public void setBoardSize(String str) 
	{
		int mySize = 0;

		try
		{
			mySize = Integer.parseInt(str);
		}
		catch (NumberFormatException e)
		{
			return;
		}
		if((Math.log(mySize)/Math.log(2))%1 != 0 || mySize < 2)
		{
			return;
		}

		myBoardSize = mySize;
		myBoardSet = true;
	}

	/**
	 * Method to set the x and y coordinates of the missing square. 
	 * 
	 * [Still needs more implementation].
	 * 
	 * @param x
	 * @param y
	 */
	public void setMissingSquare(int x, int y)
	{
		myXMissing = x;
		myYMissing = y;
	}

	/**
	 * Method to tile the board.
	 * 
	 * [Incomplete]
	 * @param myBoardSize
	 */
	public void tileBoard(int myBoardSize)
	{

	}

	/**
	 * Method returns the size of the board.
	 * @return
	 */
	public int getBoardSize() {
		return myBoardSize;
	}

	public boolean isBoardSet() {
		return myBoardSet;
	}
}