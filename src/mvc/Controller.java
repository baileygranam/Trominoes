package mvc;

public class Controller
{    
    private View myView;
    private Model myModel;
    
    /**
     * Controller constructor; view must be passed in since 
     * controller has responsibility to notify view when 
     * some event takes place.
     * 
     * The constructor creates a view and model. 
     * 
     * While the board size is not set (determined in Model.java) we will 
     * keep asking the user for a board size. Once a proper board size has
     * been given we will load the display. 
     */
    
    public Controller()
    {
        myModel = new Model();
        myView = new View(this);  
        
        while(myModel.isBoardSet() == false)
        {
        	getBoardSize();
        }
        
        myView.display();
    } 
    
    /**
     * Method that will update the view to ask the user for a board size. The size
     * given by the user will then be returned to the controller which will be 
     * passed to the model to attempt to set the board size.
     * 
     * Controller updates view, view sends data to controller, controller sends data
     * to model for processing. 
     */
    
    public void getBoardSize()
    {
    	myModel.setBoardSize(myView.getBoardSize());
    }
}