package edu.uoc.donalds.controller;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import edu.uoc.donalds.model.*;
import edu.uoc.donalds.utils.Inventory;

/**
 * This class represents a kiosk (entry point class).
 * 
 * @author merce.bauza
 * @version 1.0
 *  
 */
public class Kiosk {
	
	private Order order;
	private Inventory inventory;
	
	/**
	 * Constructor.
	 */
	public Kiosk() {
		this("");
	}
	
	
	/**
	 * Constructor with one argument.
	 * @param fileNameInventory Path to the file that contains inventory's data.
	 */
	public Kiosk(String fileNameInventory){
		if(fileNameInventory.isEmpty()){
			inventory = new Inventory(); //We cannot use "this();", because a constructor call must be the first statement in a constructor
		}else{
			inventory = new Inventory(fileNameInventory);
		}
	}
	
	
	/**
	 * Creates a new order object.
	 * @throws OrderException 
	 */
	public void createOrder() throws OrderException{
		order = new Order();
	}
	
	/**
	 * Sets the dining location for the current order.
	 * @param diningLocation an enum with the value "EATIN" or "TAKEAWAY"
	 */
	public void setDiningLocation(DiningLocation diningLocation){
		order.setDiningLocation(diningLocation);
	}

	/**
	 * Sets a new item
	 * @param item New item to add to the order.
	 * @throws OrderException is thrown when the item to add is sold out or the order is committed.
	 */
	public void addItem2Order(Item item) throws OrderException{
		order.addItem(item);
	}
	
	/**
	 * Removes the item in the position "index" from the order.
	 * @param index Position of the item to remove.
	 * @throws OrderException When the index is out of the bounds of the list or the order is committed.
	 */
	public void removeItemFromOrder(int index) throws OrderException{
		order.removeItem(index);	
	}
	
	/**
	 * Returns a list which includes the items added to the current order
	 * @return List with Order's items.
	 */
	public List<Item> getItemsOrder(){
		return order.getItems();	
	}
	
	/**
	 * Returns a list which includes the items added to the current order
	 * @return List with Order's items.
	 */
	public String showOrder(){
		StringBuilder text = new StringBuilder();
		text.append("\n*********This is your order***********\n");
		text.append(order);
		return text.toString();
	}
	
	/**
	 * Returns the total gross cost of the current order
	 * @return Gross cost of the order.
	 */
	public double getOrderTotalGrossCost(){
		return order.getTotalGrossCost();
	}
	
	/**
	 * Commit the current order
	 * @throws Exception When the order has already been committed or decrease1Stock throws an ItemException.
	 */
	public void commitOrder() throws Exception{
		order.commit();
	}
	
	
	/**
	 * Returns a List sorted by item's name (first numbers, after characters, etc.) with those items from the inventory that match the category indicated.
	 * If the category does not match with "MAINCOURSE", "SIDE", "BEVERAGE" or "DESSERT", then this method returns the whole inventory list sorted by name.  
	 *
	 * Note: We recommend using Java 8's lambda expressions to do this method. However, using lamda expressions is not mandatory.
	 * See: https://dzone.com/articles/using-lambda-expression-sort
	 *
	 *@param category Item's category. The available categories are: "MAINCOURSE", "SIDE", "BEVERAGE" and "DESSERT".
	 *@return List with Order's items sorted by name and filtered by category. 
	 */
	public List<Item> getInventoryPerCategory(String category){

		List<Item> result = inventory.getList();

		switch(category) { 
		
		case "MAINCOURSE":
		
			result = inventory.getList().stream() //convert list to stream. Using stream() makes it easier to filter        
	                .filter(it -> (it instanceof MainCourse)) //variable 'it' recurses the list inventory and only shows the elements that are instances of MainCourse.
	                											//Salad and Burger are instances of MainCourse, by inheritance. 
	                .collect(Collectors.toList()); //convert to list again to be able to do the sorting
			break;
			
		case "SIDE":
			
			result = inventory.getList().stream()              
	                .filter(it -> (it instanceof Side))
	                .collect(Collectors.toList());
			break;
			
		case "BEVERAGE":
			
			result = inventory.getList().stream()              
	                .filter(it -> (it instanceof Beverage))
	                .collect(Collectors.toList());
			break;
		
		case "DESSERT":
			
			result = inventory.getList().stream()              
	                .filter(it -> (it instanceof Dessert))
	                .collect(Collectors.toList());
			break;	
			
		default:
			result = inventory.getList(); //If the category isn't any of the above, returns the whole inventory list
			
			break;
		}
		
		Collections.sort(result, (i1, i2) -> i1.getName().compareTo(i2.getName()));//'result' is the filtered list (or whole list in case of default) 
																				//Having two items,it compares their names and sorts them. 
	
		return result;
	}	
}
