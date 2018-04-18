package edu.uoc.donalds.model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents an UocDonald's order.
 * 
 * @author merce.bauza
 * @version 1.0
 * 
 */

public class Order {
	
	private static int id = 0;
	private DiningLocation diningLocation;
	private List<Item> items;
	private boolean confirmed;
	
	/**
	  * Default constructor which sets id++, diningLocation equals to EATIN and set items with 15 positions.
	 *	   
	 */
	public Order() throws OrderException{
		setConfirmed(false);
		diningLocation = DiningLocation.EATIN;
		items = new LinkedList<Item>();
	}


	/**
	 * Returns the order's id.
	 * 
	 * @return Order's number/id.
	 */
	public int getId() {
		return id;
	}


	/**
	 * Sets a new value for the Order's id.
	 * 
	 * @param id New value for the private field "id".
	 */
	public void setId(int id) {
		Order.id = id;
	}


	/**
	 * Returns where the customer will eat her order.
	 * 
	 * @return Dining location's value.
	 */
	public DiningLocation getDiningLocation() {
		return diningLocation;
	}


	/**
	 * Sets where the customer will eat her order.
	 * 
	 * @param diningLocation Location/Place where the customer will eat her order.
	 */
	public void setDiningLocation(DiningLocation diningLocation) {
		this.diningLocation = diningLocation;
	}
	
	/**
	 * Returns if an order has been confirmed or not
	 * 
	 * @return order confirmation
	 */
	public boolean isConfirmed() {
		return confirmed;
	}


	/**
	 * Sets an order to confirmed or not
	 * 
	 * @param confirmed 
	 */
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	
	/**
	 * Increases 1 unit the value of the private field "id".
	 */
	private void increase1Id(){
		setId(getId()+1); //tambien valdria "id++", pero usando el getter y setter se mejora el mantenimiento ante posibles cambios.
	}
	
	/**
	 * Returns the whole list of items.
	 * 
	 * @return The private field "items".
	 */
	public List<Item> getItems() {
		return items;
	}


	/**
	 * Replaces the whole list of items.
	 *	 
	 * @param items New list of items.
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	/**
	 * Adds a new item to the end of the list "items".
	 * 
	 * @param item New item to add.
	 */
	public void addItem(Item item) throws OrderException{	
		if(!isConfirmed()) {
			if(!item.isSoldOut()) {
				items.add(item);	
				item.increase1ExpectedPurchase();
			}else throw new OrderException("The item is sold out. You cannot add it to your order.");
		}else throw new OrderException("The order has already been commited, you cannot add an item.");
	}
	
	/**
	 * Removes the item in the position "index" of the list "items".
	 * 
	 * @param index Position where the item we want to remove is.
	 */
	public void removeItem(int index) throws OrderException{	
		if(!isConfirmed()) {
				items.get(index).decrease1ExpectedPurchase();
				items.remove(index);
		}else throw new OrderException("The order has already been commited, you cannot remove the item.");
	}
	
	/**
	  * This method adds the gross price of all the items of the order together.
	 * 
	 * @return The sum in euros of the gross price of the order's items.  
	 */
	public double getTotalGrossCost(){
		double total = 0;
		
		for(Item item : items){
			//total += (item!=null)?item.getGrossPrice():0;
			total += item.getGrossPrice(); //quitamos la comprobacion de si es null, porque ahora no tiene sentido.
		}
		
		return total;
		
	}
	
	/**
	  * This method adds the net price of all the items of the order together.
	 * 
	 * @return The sum in euros of the net price of the order's items.  
	 */
	public double getTotalNetCost(){
		double total = 0;
		
		for(Item item : items){
			//total += (item!=null)?item.getNetPrice():0;
			total += item.getNetPrice(); //quitamos la comprobacion de si es null, porque ahora no tiene sentido.
		}
		
		return total;
		
	}

	/**
	 * This method adds the difference between the gross price and the net price of all the items of the order together.
	 * 
	 * @return The sum in euros of the taxes of the order's items.  
	 */
	public double getTotalTaxesCost(){
		double total = 0;
		
		for(Item item : items){
			//total += (item!=null)?(item.getGrossPrice()-item.getNetPrice()):0;
			total += item.getGrossPrice()-item.getNetPrice(); //quitamos la comprobacion de si es null, porque ahora no tiene sentido.
		}
		
		return total;
	}
	
	/**
	 * Commit the order
	 * 
	 * Set order confirmation to true if order is not empty. 
	 * 
	 * Decrease 1 unit the items' stock and the expected purchase
	 * Print receipt
	 * Increase ID for next order
	 * 
	 * @throws ItemException
	 * @throws OrderException
	 * @throws IOException
	 */
	public void commit() throws ItemException, OrderException, IOException {
		
		if(!this.isConfirmed()) {
			if(!this.getItems().isEmpty()) {
				this.setConfirmed(true);
							
				for(Item item: this.getItems()) {
					item.decrease1Stock();
					item.decrease1ExpectedPurchase();
				}
				printReceipt();
				this.increase1Id();
			}else throw new OrderException("Your order is empty, then you cannot commit it");
		}else throw new OrderException("The order has already been created");
		
	}
	
	/**
	 * Print Receipt
	 * 
	 * Print receipt in an .txt file using a writer
	 * 
	 * @throws IOException
	 */
	private void printReceipt() throws IOException {
		  File file = new File("output", "receipt_"+this.getId()+".txt");
	      
	      // creates the file
	      file.createNewFile();
	      if(file.exists()) {
		      // creates a FileWriter Object
		      FileWriter writer = new FileWriter(file); 
		      // Writes the content to the file
		      writer.write(this.toString()); 
		      writer.flush();
		      writer.close();
	      } else throw new IOException("The file already exists. Please delete 'receipt_"+this.getId()+".txt' and try again.");

	}
	
	/**
	 * This method overrides Object's toString.
	 * 
	 * @return String with the Order's id, the list of the items with the format "name......grosPrice �", and the total gross cost and total taxes cost.
	 */
	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("0.00");
		StringBuilder text = new StringBuilder();
		
		text.append("**UocDonald's**\n");

		text.append("\n||No. Order:"+getId()+"||\n");
			
		text.append("\nDining location: "+getDiningLocation()+"");
		text.append("\n__________________________\n");
		
		for(Item item : items){
			text.append("\n"+item); //aprovechamos el m�todo toString de Item que ya devuelve el String en el formato que necesitamos.
			//Adem�s, gracias al uso de una List, ya no hay que comprobar si es null o no
		}
		
		text.append("\n__________________________\n");
			
		text.append("\nTOTAL: "+df.format(getTotalGrossCost())+" �");
		text.append("\nTaxes: "+df.format(getTotalTaxesCost())+" �");
		text.append("\n__________________________\n");
		
		return text.toString();	
	}
}
