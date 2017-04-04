package mvc;

public class Controller
{    
    private View myView;
    private Model myModel;
    private int mySize;
    
    /**
     * Controller constructor; view must be passed in since 
     * controller has responsibility to notify view when 
     * some event takes place.
     * 
     * The constructor creates a view and model. 
     * 
     * While the board size is not set determined in Model.java we will 
     * keep asking the user for a board size. Once a proper board size has
     * been given we will load the display. 
     */
    public Controller()
    {  
        myView = new View(this);
        mySize = Integer.parseInt(myView.askBoardSize());
        myView.display(mySize);
        myModel = new Model();     
    }
    
    /** 
     * This method takes in a string of coordinates from the button that the user
     * had press to choose a missing square. This string is then split into
     * its respective x any y values and turned into numeric values instead
     * of chars. Finally we pass these numerical x and y values to the model
     * to set the missing square.
     * 
     * @param coordinates
     */
    public void setMissingSquare(String coordinates)
    {
    	int x = Character.getNumericValue(coordinates.charAt(0));
    	int y = Character.getNumericValue(coordinates.charAt(0));
    	myModel.setMissingSquare(x, y);
    	myView.disableLabels();
    	myView.placeMissingSquare(x, y);
    }
    
    /**
     * Method to set the size of the board.
     * 
     * @param size
     */
    public void setBoardSize(int size)
    {
    	myModel.setBoardSize(size);
    }
    
    /**
     * Method to tile the board.
     */
    public void tileBoard()
    {
    	myModel.tile();
    }
}