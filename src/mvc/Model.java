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

	private void tileRec(int myBoardSize, int topx, int topy) {

		// No recursive case needed here, just fill in your one tromino...
		if (myBoardSize == 2) {

			// Fill in the one necessary tromino. The hole is identified by a
			// non-zero number, so don't fill in that one square.	
			for (int i=0; i<myBoardSize; i++)
			{
				for (int j=0; j<myBoardSize; j++)
				{
					if (myGrid[topx+i][topy+j] == 0)
					{
						myGrid[topx+i][topy+j] = myCurrentNumber;
					}
				}
			}

			// Advance to the next tromino.
			myCurrentNumber++;
		}

		// Recursive case...
		else {

			// Find coordinates of missing hole
			int savex=topx, savey=topy;

			for (int x=topx; x<topx+myBoardSize; x++) 
			{
				for (int y=topy; y<topy+myBoardSize; y++)
				{
					if (myGrid[myMissingX][myMissingY] != 0) 
					{
						savex = x;
						savey = y;
					}
				}
			}

			// Hole in upper left quadrant.		
			if (savex < topx + myBoardSize/2 && savey < topy + myBoardSize/2) 
			{

				// Recursively tile upper left quadrant.
				tileRec(myBoardSize/2, topx, topy);

				// Fill in middle tromino
				myGrid[topx+myBoardSize/2][topy+myBoardSize/2-1] = myCurrentNumber;
				myGrid[topx+myBoardSize/2][topy+myBoardSize/2] = myCurrentNumber;
				myGrid[topx+myBoardSize/2-1][topy+myBoardSize/2] = myCurrentNumber;

				// Advance to the next tromino
				myCurrentNumber++;

				// Now we can make our three other recursive calls.
				tileRec(myBoardSize/2, topx, topy+myBoardSize/2);
				tileRec(myBoardSize/2, topx+myBoardSize/2, topy);
				tileRec(myBoardSize/2, topx+myBoardSize/2, topy+myBoardSize/2);

			}

			// Hole in upper right quadrant
			else if (savex < topx + myBoardSize/2 && savey >= topy + myBoardSize/2) 
			{

				// Recursively tile upper right quadrant.
				tileRec(myBoardSize/2, topx, topy+myBoardSize/2);

				// Fill in middle tromino
				myGrid[topx+myBoardSize/2][topy+myBoardSize/2-1] = myCurrentNumber;
				myGrid[topx+myBoardSize/2][topy+myBoardSize/2] = myCurrentNumber;
				myGrid[topx+myBoardSize/2-1][topy+myBoardSize/2-1] = myCurrentNumber;

				// Advance to the next tromino
				myCurrentNumber++;

				// Now we can make our three other recursive calls.
				tileRec(myBoardSize/2, topx, topy);
				tileRec(myBoardSize/2, topx+myBoardSize/2, topy);
				tileRec(myBoardSize/2, topx+myBoardSize/2, topy+myBoardSize/2);

			}

			// Hole in bottom left quadrant
			else if (savex >= topx + myBoardSize/2 && savey < topy + myBoardSize/2) 
			{

				// Recursively tile bottom left quadrant.
				tileRec(myBoardSize/2, topx+myBoardSize/2, topy);

				// Fill in middle tromino
				myGrid[topx+myBoardSize/2-1][topy+myBoardSize/2] = myCurrentNumber;
				myGrid[topx+myBoardSize/2][topy+myBoardSize/2] = myCurrentNumber;
				myGrid[topx+myBoardSize/2-1][topy+myBoardSize/2-1] = myCurrentNumber;

				// Advance to the next tromino
				myCurrentNumber++;

				// Now we can make our three other recursive calls.
				tileRec(myBoardSize/2, topx, topy);
				tileRec(myBoardSize/2, topx, topy+myBoardSize/2);
				tileRec(myBoardSize/2, topx+myBoardSize/2, topy+myBoardSize/2);
			}
			else 
			{

				// Recursively tile bottom right quadrant.
				tileRec(myBoardSize/2, topx+myBoardSize/2, topy+myBoardSize/2);

				// Fill in middle tromino
				myGrid[topx+myBoardSize/2-1][topy+myBoardSize/2] = myCurrentNumber;
				myGrid[topx+myBoardSize/2][topy+myBoardSize/2-1] = myCurrentNumber;
				myGrid[topx+myBoardSize/2-1][topy+myBoardSize/2-1] = myCurrentNumber;

				// Advance to the next tromino
				myCurrentNumber++;

				// Now we can make our three other recursive calls.
				tileRec(myBoardSize/2, topx+myBoardSize/2, topy);
				tileRec(myBoardSize/2, topx, topy+myBoardSize/2);
				tileRec(myBoardSize/2, topx, topy);
			}
		} 
	} 


	/**
	 * Method to show us the trominoes in the console. 
	 * 
	 * [REMOVE BEFORE SUBMITION]
	 */
	public void print() {

		for (int i=0; i<myGrid.length; i++) {
			for (int j=0; j<myGrid[i].length; j++)
				System.out.print(myGrid[i][j] + "\t");
			System.out.println();
		}
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
	public void setBoardSize(int size)
	{
		myBoardSize = size;
	}

	/**
	 * Return the entire grid of values
	 * @return 2D Array of values
	 */
	public int[][] getGrid()
	{
		return myGrid;
	}
}