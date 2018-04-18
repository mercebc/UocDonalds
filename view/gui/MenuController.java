package edu.uoc.donalds.view.gui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import edu.uoc.donalds.model.Item;
import edu.uoc.donalds.model.OrderException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This class is the controller of the Menu View.
 * 
 * @author merce.bauza
 * @version 1.0
 *  
 */
public class MenuController extends Controller{

	@FXML
	private Pane menuTable;
	
	@FXML
	private ListView<Item> listOrder;
	
	@FXML
	private Label total;
	
	/**
	 * Default constructor.
	 */
	public MenuController(){
		super();		
	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	displayOrder();
    	goToScreen();    	
    }
	
    /**
     * Manages the different sub-screens that can be shown in the menu screen, i.e. when the user is managing her order.
     */
	private void goToScreen(){
		switch(Controller.guiApp.getCurrentView()){
			case MAINS:
				displayItemsOptionsByCategory(guiApp.getKiosk().getInventoryPerCategory("MAINCOURSE"));
				break;
			case SIDES:
				displayItemsOptionsByCategory(guiApp.getKiosk().getInventoryPerCategory("SIDE"));
				break;
			case BEVERAGES:    			
				displayItemsOptionsByCategory(guiApp.getKiosk().getInventoryPerCategory("BEVERAGE"));
				break;  
			case DESSERTS:				
				displayItemsOptionsByCategory(guiApp.getKiosk().getInventoryPerCategory("DESSERT"));
				break;
			case DINING_LOCATION:
				break;
			case MENU:
				try {
					Pane n = (Pane)FXMLLoader.load(getClass().getResource("CategoriesView.fxml"));
					for (Node component : n.getChildren()) {
						//Add a mouse released method to each component (i.e. category). 
						component.setOnMouseReleased(
							new EventHandler<MouseEvent>() {
					            @Override 
					            public void handle(MouseEvent event) {
					                event.consume();
					            	VBox option = (VBox) event.getSource();
					            	switch(option.getId()){
						    			case "mains":
						    					guiApp.setCurrentView(View.MAINS);			    					
						    					break;	
						    			case "beverages":
						    					guiApp.setCurrentView(View.BEVERAGES);
						    					break;
						    			case "sides":
						    					guiApp.setCurrentView(View.SIDES);
						    					break;
						    			case "desserts":
						    					guiApp.setCurrentView(View.DESSERTS);
						    					break;						    		
						    		}	
					            	
					            	goToScreen();
					            }
					        }
							
							);
					}
					menuTable.getChildren().clear();
					menuTable.getChildren().addAll(n);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;			
			default:
				break;
			}
	 }
	 
	/**
	 * Given a list of items (i.e. mains, beverages, etc.), this method creates a visual element for each item of the list. 
	 * In addition, it adds a mouse released method to each element.  
	 * @param currentList List of items whose items should be displayed.
	 */
	private void displayItemsOptionsByCategory(List<Item> currentList){
	 	ObservableList<Node> nodeList =  FXCollections.observableArrayList();
	    			
	   	for(Item itemInventoryList : currentList){    		
	   		try {
				Pane n = (Pane)FXMLLoader.load(getClass().getResource("ItemOption.fxml"));
				
				Image iv = new Image ("file:"+itemInventoryList.getImageSrc(),
		    			300, 300, false, false);
				
				((ImageView) n.getChildren().get(0)).setImage(iv);
				
				((Label)((HBox) n.getChildren().get(1)).getChildren().get(0)).setText(itemInventoryList.getName());
								
								
				if(itemInventoryList.isSoldOut()){//We paint the item in red and it is not clickable 
					((Label)((HBox) n.getChildren().get(1)).getChildren().get(1)).setText("Sold out");
					n.setStyle("-fx-background-color:#ff0000; -fx-opacity:0.5; -fx-border-color:#ff0000");
				}else{
					DecimalFormat df = new DecimalFormat("0.00");
					((Label)((HBox) n.getChildren().get(1)).getChildren().get(1)).setText(df.format(itemInventoryList.getGrossPrice())+" �");
					
					
					n.setOnMouseReleased(new EventHandler<MouseEvent>() {
			            @Override 
			            public void handle(MouseEvent event) {		            	
			            	try {
								guiApp.getKiosk().addItem2Order(itemInventoryList);
								displayOrder();
								goToScreen();
							} catch (OrderException e) {								
								e.printStackTrace();
							} event.consume();
			            }
			        });
				} 
				    			
				
				nodeList.add(n);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		
		}    	
	    	menuTable.getChildren().clear();
	    	menuTable.getChildren().addAll(nodeList);  	
	 }
	
	/**
	 * Manages the visual part in charge of the order, i.e. list of items and total gross cost. 
	 */
	private void displayOrder(){
		DecimalFormat df = new DecimalFormat("0.00");
		ObservableList<Item> linesOrder = FXCollections.observableArrayList();
		
		linesOrder.setAll(guiApp.getKiosk().getItemsOrder());
		listOrder.setItems(linesOrder);
		total.setText(df.format(guiApp.getKiosk().getOrderTotalGrossCost())+"�");		
	}
	
	 /**
	 * Called when the user clicks on the order/accept button.
	 */
	@FXML
	private void handleAcceptButton(){
		Alert alert;
		
		if(guiApp.getKiosk().getItemsOrder().size()==0){
			alert = new Alert(AlertType.ERROR, "Your order is empty!!", ButtonType.OK);
			alert.showAndWait();				
		}else{			
			alert = new Alert(AlertType.INFORMATION, "Is this order correct?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {			  			
				try {
					guiApp.getKiosk().commitOrder();
					guiApp.goToScene("ThanksView.fxml");
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}			
		}		
		
	}
	 
	 
 	/**
	 * Called when the user clicks on the cancel button.
	 */
	@FXML
	private void handleCancelButton(){
		Alert alert = new Alert(AlertType.WARNING, "Do you want to cancel the current order?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    //do stuff				
			guiApp.goToScene("WelcomeView.fxml");
		}		
		
	}
	
	/**
	 * Called when the user clicks on the back button.
	 */
	@FXML
	private void handleBackButton(){
		if(guiApp.getCurrentView()!=View.MENU){
			guiApp.setCurrentView(View.MENU);
			goToScreen();
		}else{
			guiApp.setCurrentView(View.DINING_LOCATION);
			guiApp.goToScene("DiningLocationView.fxml");
		}
	}
	
	/**
	 * Called when the user clicks on a row of the list view (i.e. an item from the order's list).
	 * @throws OrderException when there is a problem when removing the item from the order.
	 */
	@FXML
	private void handleItemSelectedFromListView() throws OrderException {
		if(listOrder.getSelectionModel().getSelectedItem() instanceof Item){				
			Alert alert = new Alert(AlertType.WARNING, "Do you want to remove "+listOrder.getSelectionModel().getSelectedItem().getName()+"?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {			    
				//listOrder.getSelectionModel().getSelectedItem().decrease1ExpectedPurchase();
				guiApp.getKiosk().removeItemFromOrder(listOrder.getSelectionModel().getSelectedIndex());
				displayOrder();
				goToScreen();
			}
		}
		        
		listOrder.getSelectionModel().clearSelection(listOrder.getSelectionModel().getSelectedIndex());		
    }	


	/**
	* Called when the user clicks on the lucky button.
	 * @throws OrderException 
	*/
	@FXML
	private void LuckyButton() throws OrderException{
		Alert alert;
		Item luckyItem;
		
		do {
			Random random = new Random();
			
			String[] categories = {"MAINCOURSE", "SIDE", "DESSERT", "BEVERAGE"};	
			int index = random.nextInt(categories.length);		
			String luckyCategory = categories[index];
			
			List<Item> inventory = guiApp.getKiosk().getInventoryPerCategory(luckyCategory);
			int pointer = random.nextInt(inventory.size());
			luckyItem = inventory.get(pointer);
		
		}while(luckyItem.isSoldOut());

		
		alert = new Alert(AlertType.INFORMATION, "Are you happy with it being added to your order?", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Lucky window");
		alert.setHeaderText("YUUUHU! Your lucky item is "+ luckyItem.getName());
		alert.showAndWait();
		
		if (alert.getResult() == ButtonType.YES) {	
			try {
				guiApp.getKiosk().addItem2Order(luckyItem);
				displayOrder();
				goToScreen();
			} catch (OrderException e) {								
				e.printStackTrace();
			} 
		}else {
			alert = new Alert(AlertType.INFORMATION, "But you can always try again if you wish!", ButtonType.OK);
			alert.setHeaderText("We are sorry to hear that");
			alert.show();
		}
				
	}

}
