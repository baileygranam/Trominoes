package model;

/**
 * This program tiles with right trominoes using an (n x n) board
 * with one square missing, assuming that n is a power of 2.
 * 
 * @author Bailey Granam 
 * @author Mimi Shimkovska 
 *
 */

public class TrominoesModel 
{
	
	private int myX; // X Coordinate of board
	private int myY; // Y Coordinate of board
	private int myXMissing; // X Coordinate of missing square
	private int myYMissing; // Y Coordinate of missing square
	private int myBoardSize; // Size of board
	
	public TrominoesModel()
	{
		myX = 0;
		myY = 0;
		
	}
	
	public void setBoardSize(int boardSize) 
	{
		myBoardSize = (int) java.lang.Math.pow(2,boardSize);
	}
	
	public void setMissingSquare(int x, int y)
	{
		myXMissing = x;
		myYMissing = y;
	}
	
	
	public void tileBoard()
	{
		
	}
	
	
}
