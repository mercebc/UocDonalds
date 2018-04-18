package edu.uoc.donalds.view.gui;


import edu.uoc.donalds.model.OrderException;
import javafx.fxml.FXML;

/**
 * This class is the controller of the Welcome View.
 * 
 * @author merce.bauza
 * @version 1.0
 */
public class WelcomeController extends Controller{

	public WelcomeController() {		
		super();
	}

	/**
	 * Called when the user clicks on the screen.
	 * @throws OrderException 
	 */
	@FXML
	private void handleStartButton() throws OrderException{
		guiApp.getKiosk().createOrder();
		guiApp.goToScene("DiningLocationView.fxml");		
	}	
}
