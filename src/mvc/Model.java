package mvc;

public class Model 
{

	
	private int[][] myGrid;
    private int 	myCurrentNumber,
					myMissingX,
					myMissingY,
					myBoardSize;

	/**
	 * This is the constructor for the Model class. It does not necessarily
	 * perform any direct actions upon an instance being created.
	 */
	public Model() 
	{

	}

	/**
	 * Pre: myBoardSize must be a perfect power of 2 and greater than 1.
	 * Post: Creates an empty tromino object with dimensions myBoardSize x myBoardSize.
	 */
	public void tromino() {
		
		// Create a grid
		myGrid = new int[myBoardSize][myBoardSize];
		
		myCurrentNumber = 1;

		// Fill the grid with empty points to begin
		for (int i=0; i<myBoardSize; i++) 
		{
			for (int j=0; j<myBoardSize; j++) 
			{
				myGrid[i][j] = 0;
			}
		}

		// This represents the point on the grid where the square is missing.
		myGrid[myMissingX][myMissingY] = -1;
	}
	
	/**
	 * This method is the wrapper call for the recusrive method for trominoes.
	 */
	public void tile() {
		tileRec(myGrid.length, 0, 0);
	}

	
	/**
	 * Method that tiles the grid.
	 * 
	 * @param myBoardSize
	 * @param originX
	 * @param originY
	 */
	private void tileRec(int myBoardSize, int originX, int originY) {

		if (myBoardSize == 2) 
		{
			for (int i=0; i<myBoardSize; i++) 
			{
				for (int j=0; j<myBoardSize; j++)
				{
					if (myGrid[originX+i][originY+j] == 0)
					{
						myGrid[originX+i][originY+j] = myCurrentNumber;
					}
				}
			}
			nextTrominoe();
			//myFinalNumbIterations++;
		}
		else 
		{
			int defX = originX, defY = originY;
			for (int x = originX; x < originX+myBoardSize; x++) 
			{
				for (int y = originY; y < originY+myBoardSize; y++)
				{
					if (myGrid[x][y] != 0) 
					{
						defX = x;
						defY = y;
					}
				}
			}
				
			if (defX < originX + myBoardSize/2 && defY < originY + myBoardSize/2) 
			{
				tileRec(myBoardSize/2, originX, originY);
				
				myGrid[originX+myBoardSize/2][originY+myBoardSize/2-1] = myCurrentNumber;
				myGrid[originX+myBoardSize/2][originY+myBoardSize/2] = myCurrentNumber;
				myGrid[originX+myBoardSize/2-1][originY+myBoardSize/2] = myCurrentNumber;
				
				nextTrominoe();
				
				tileRec(myBoardSize/2, originX, originY+myBoardSize/2);
				tileRec(myBoardSize/2, originX+myBoardSize/2, originY);
				tileRec(myBoardSize/2, originX+myBoardSize/2, originY+myBoardSize/2);	
			}
			else if (defX < originX + myBoardSize/2 && defY >= originY + myBoardSize/2) 
			{
				tileRec(myBoardSize/2, originX, originY+myBoardSize/2);
				
				myGrid[originX+myBoardSize/2][originY+myBoardSize/2-1] = myCurrentNumber;
				myGrid[originX+myBoardSize/2][originY+myBoardSize/2] = myCurrentNumber;
				myGrid[originX+myBoardSize/2-1][originY+myBoardSize/2-1] = myCurrentNumber;
				
				nextTrominoe();
				
				tileRec(myBoardSize/2, originX, originY);
				tileRec(myBoardSize/2, originX+myBoardSize/2, originY);
				tileRec(myBoardSize/2, originX+myBoardSize/2, originY+myBoardSize/2);
			}
			else if (defX >= originX + myBoardSize/2 && defY < originY + myBoardSize/2) 
			{
				tileRec(myBoardSize/2, originX+myBoardSize/2, originY);
				
				myGrid[originX+myBoardSize/2-1][originY+myBoardSize/2] = myCurrentNumber;
				myGrid[originX+myBoardSize/2][originY+myBoardSize/2] = myCurrentNumber;
				myGrid[originX+myBoardSize/2-1][originY+myBoardSize/2-1] = myCurrentNumber;
				
				nextTrominoe();
				
				tileRec(myBoardSize/2, originX, originY);
				tileRec(myBoardSize/2, originX, originY+myBoardSize/2);
				tileRec(myBoardSize/2, originX+myBoardSize/2, originY+myBoardSize/2);
			}
			else 
			{
				tileRec(myBoardSize/2, originX+myBoardSize/2, originY+myBoardSize/2);
				
				myGrid[originX+myBoardSize/2-1][originY+myBoardSize/2] = myCurrentNumber;
				myGrid[originX+myBoardSize/2][originY+myBoardSize/2-1] = myCurrentNumber;
				myGrid[originX+myBoardSize/2-1][originY+myBoardSize/2-1] = myCurrentNumber;
				
				nextTrominoe();
				
				tileRec(myBoardSize/2, originX+myBoardSize/2, originY);
				tileRec(myBoardSize/2, originX, originY+myBoardSize/2);
				tileRec(myBoardSize/2, originX, originY);
			}
		} 
	} 
	
	/**
	 * Increment the number
	 */
	public void nextTrominoe()
	{
		myCurrentNumber++;
	}

	/**
	 * Set the missing square of the board via its coordinaties
	 * @param x
	 * @param y
	 */
	public void setMissingSquare(int x, int y)
	{
		myMissingX = x;
		myMissingY = y;
	}

	/**
	 * Set the size of the board.
	 * @param size
	 */
	public boolean setBoardSize(int mySize)
	{
		if((Math.log(mySize)/Math.log(2))%1 != 0 || mySize < 2)
		{
			return false;
		}
		else
		{
			myBoardSize = mySize;
			
			return true;
		}
	}

	/**
	 * Return the entire grid of values
	 * @return 2D Array of values
	 */
	public int[][] getGrid()
	{
		return myGrid;
	}
	
	/**
	 * Method to reset the missing square.
	 */
	public void reset()
	{
		setMissingSquare(0,0);
		tromino();
	}
}