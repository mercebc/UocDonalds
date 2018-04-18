package edu.uoc.donalds.view.gui;

import java.io.IOException;
import java.util.Timer;

import edu.uoc.donalds.controller.Kiosk;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

/**
 * This class is the entry point of the Kiosk's graphical interface.
 * 
 * @author merce.bauza
 * @version 1.0
 */
public class GUIApp extends Application{
 
	private Stage primaryStage;
    private Region rootLayout;
    private Kiosk kiosk;
    private View currentView;
    private Timer timer = null;
	
    
    /**
     * Method that is called first when the app is launched.
     * @throws Exception when something wrong happens.
     */
    @Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
	    this.primaryStage.setTitle("UOCDonald's self-ordering kiosk");
	    this.primaryStage.setResizable(false);      
	    kiosk = new Kiosk();      
	    setCurrentView(View.WELCOME);
	    goToScene("WelcomeView.fxml");	  
	 }
       
    
    /**
     * Method that is called first when the app is closed/stopped.
     * @throws Exception when something wrong happens.
     */
    @Override
	public void stop() throws Exception {
		timer.cancel();
		timer.purge();
		super.stop();
	}


	/**
	 * Program entry point.
	 * 
	 * @param args arguments to the program (they are not needed).
	 */    
	 public static void main(String[] args) {
	    launch(args);
	 }
    
	 /**
	  * Returns the current Kiosk object
	  * @return Kiosk object that is currently used. 
	  */
	 public Kiosk getKiosk(){
		return kiosk;
	 }
	 
	 /**
	  * Sets the a new Kiosk object.
	  * @param kiosk New Kiosk object to save.
	  */
	 public void setKiosk(Kiosk kiosk){
		this.kiosk = kiosk;
	 }  
	  
	 /**
	  *  Returns the current view object.
	  * @return View Object that is currently shown to the user.
	  */
	 public View getCurrentView() {
		return currentView;
	 }
	
	 /**
	  * Sets the view that is being shown at this moment.
	  * @param currentView View object.
	  */
	 public void setCurrentView(View currentView) {
		this.currentView = currentView;
	 }
	 
	 
	 /**
	  *  Getter of the private field "timer" object.
	  * @return Timer object.
	  */
	public Timer getTimer() {
		return timer;
	}

	 /**
	  *  Setter of the private field "timer" object.
	  * @param timer The new timer object to set to the private field "timer".
	  */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * It sets the layout/scene that should be shown currently.
	 * @param layout Name of the FXML file, e.g. WelcomeView.fxml.
	 */	
	public void goToScene(String layout) {
      try {
          // Load root layout from fxml file.
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(GUIApp.class.getResource(layout));
          rootLayout = (Region) loader.load();
          // Give the controller access to the main app.
          Controller.guiApp = this;

          // Show the scene containing the root layout.
          Scene scene = new Scene(rootLayout);
          primaryStage.setScene(scene);
          primaryStage.show();
          
      }catch(IOException e) {
          e.printStackTrace();
      }
	}  
}