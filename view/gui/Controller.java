package edu.uoc.donalds.view.gui;

/**
 * This class is the parent of all the controllers.
 * 
 * @author merce.bauza
 * @version 1.0
 */
public abstract class Controller {

	// Reference to the main application.
	protected static GUIApp guiApp;
	
	/**
	 * Default constructor.
	 */
	public Controller() {
		// TODO Auto-generated constructor stub
	}
	
	 /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param guiApp Object related to the main app.
     */
    public void setGUIApp(GUIApp guiApp) {
        this.guiApp = guiApp;     
    }

}
