package edu.uoc.donalds.view.gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * This class is the controller of the Thanks View.
 * 
 * @author merce.bauza
 * @version 1.0
 */
public class ThanksController extends Controller{

	public ThanksController() {		
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
    	    }, 3000);    	

    }
	
}