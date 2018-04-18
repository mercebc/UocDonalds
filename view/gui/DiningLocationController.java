package edu.uoc.donalds.view.gui;

import java.util.Timer;
import java.util.TimerTask;

import edu.uoc.donalds.model.DiningLocation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * This class is the controller of the Dining Location View.
 * 
 * @author merce.bauza
 * @version 1.0
 */
public class DiningLocationController extends Controller{
	
	/**
	 * Default constructor.
	 */
	public DiningLocationController() {		
		super();
	}

	
	 /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	guiApp.setTimer(new Timer());
    	guiApp.getTimer().schedule(new TimerTask() {
    	        @Override
    	        public void run() {
    	            Platform.runLater(new Runnable() {
    	                @Override
    	                public void run() {
    	                	guiApp.setCurrentView(View.WELCOME);		
    	            		guiApp.goToScene("WelcomeView.fxml");
    	                }
    	            });

    	        }
    	    }, 5000);    	

    }
	
	/**
	 * Called when the user clicks on one of the dining location options.
	 * @param e MouseEvent that includes information about the event and the element that was clicked.
	 */
	@FXML
	private void handleDiningLocationSelection(MouseEvent e){
		VBox option = (VBox) e.getSource();
		if(option.getId().compareTo("eatin")==0){
			guiApp.getKiosk().setDiningLocation(DiningLocation.EATIN);
		}else{
			guiApp.getKiosk().setDiningLocation(DiningLocation.TAKEAWAY);
		}
		
		guiApp.getTimer().cancel();
		guiApp.getTimer().purge();
		
		guiApp.setCurrentView(View.MENU);		
		guiApp.goToScene("MenuView.fxml");		
	}	
}