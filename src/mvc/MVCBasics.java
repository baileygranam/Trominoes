package mvc;
public class MVCBasics
{
    // Properties
    private Controller myController;
    
    // Methods
    public static void main(String[] args)
    {
        new MVCBasics();
    }
    
    public MVCBasics()
    {
        this.setController(new Controller());
    }

	public void setController(Controller myController) {
		this.myController = myController;
	}

	public Controller getController() {
		return myController;
	}
}