package edu.uoc.donalds.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.uoc.donalds.model.Beverage;
import edu.uoc.donalds.model.Burger;
import edu.uoc.donalds.model.Dessert;
import edu.uoc.donalds.model.Item;
import edu.uoc.donalds.model.Salad;
import edu.uoc.donalds.model.Side;

/**
 * This class represents the inventory of the kiosk. It loads and manages inventory's data.
 * 
 * @author merce.bauza
 * @version 1.0
 *  
 */
public class Inventory {

	ArrayList<Item> inventory;
	
	/**
	 * Default constructor.
	 */
	public Inventory() {		
		this("files/inventory.txt");
	}
	
	/**
	 * Constructor with arguments.
	 * @param fileName Name of the file where is the inventory's items.
	 * @param fileName Name of the text file which contains inventory's data.
	 */
	public Inventory(String fileName){
		inventory = new ArrayList<Item>();
		loadInventory(fileName);
	}
	
	/**
	 * Manages the reading of the file and stores items in the inventory as a list.
	 */
	private void loadInventory(String fileName){		
		List<String> list = new ArrayList<String>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			//Convert it into a List
			list = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String itemList : list){
			String[] elements = itemList.split("--");
			
			try{
				switch(elements[0].toUpperCase()){
					case "BURGER":
							inventory.add(createBurger(elements));;							
							break;							
					case "SALAD":
							inventory.add(createSalad(elements));;
							break;
					case "BEVERAGE":							
							inventory.add(createBeverage(elements));;
							break;
					case "SIDE":						
							inventory.add(createSide(elements));;
							break;
					case "DESSERT":
							inventory.add(createDessert(elements));;
							break;				
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
		}		
	}
	
	/**
	 * 
	 * @param items Information of the item (i.e. burger) which must be instantiated.
	 * @return Burger object
	 * @throws Exception when the Burger's constructor detects a problem.
	 */	
	private Burger createBurger(String[] items) throws Exception{
		return new Burger(items[1],Boolean.parseBoolean(items[2]),items[3],
				Double.parseDouble(items[4]),Double.parseDouble(items[5]),Double.parseDouble(items[6]),Integer.parseInt(items[7]));
	}
	
	/**
	 * 
	 * @param items Information of the item (i.e. Salad) which must be instantiated.
	 * @return Salad object
	 * @throws Exception when the Salad's constructor detects a problem.
	 */
	private Salad createSalad(String[] items) throws Exception{
		return new Salad(items[1],Boolean.parseBoolean(items[2]),items[3],
				Double.parseDouble(items[4]),Double.parseDouble(items[5]),Double.parseDouble(items[6]),Integer.parseInt(items[7]));
	}
	
	/**
	 * 
	 * @param items Information of the item (i.e. beverage) which must be instantiated.
	 * @return Beverage object
	 * @throws Exception when the Beverage's constructor detects a problem.
	 */
	private Beverage createBeverage(String[] items) throws Exception{
		return new Beverage(items[1],Integer.parseInt(items[2]),Double.parseDouble(items[3]),Boolean.parseBoolean(items[4]),
				items[5],Double.parseDouble(items[6]),Double.parseDouble(items[7]),Double.parseDouble(items[8]),Integer.parseInt(items[9]));
	}
	
	/**
	 * 
	 * @param items Information of the item (i.e. side) which must be instantiated.
	 * @return Side object
	 * @throws Exception when the Side's constructor detects a problem.
	 */	
	private Side createSide(String[] items) throws Exception{
		return new Side(items[1],Integer.parseInt(items[2]),items[3],Double.parseDouble(items[4]),
				Double.parseDouble(items[5]),Double.parseDouble(items[6]),Integer.parseInt(items[7]));
	}
	
	/**
	 * 
	 * @param items Information of the item (i.e. dessert) which must be instantiated.
	 * @return Dessert object
	 * @throws Exception when the Dessert's constructor detects a problem.
	 */
	private Dessert createDessert(String[] items) throws Exception{
		return new Dessert(items[1],Boolean.parseBoolean(items[2]),items[3],
				Double.parseDouble(items[4]),Double.parseDouble(items[5]),Double.parseDouble(items[6]),Integer.parseInt(items[7]));
	}

	/**
	 * Returns the whole inventory.
	 * @return List with the inventory's items.
	 */
	public ArrayList<Item> getList() {
		return inventory;
	}

	/**
	 * Sets a list of items as a new inventory.
	 * @param inventory List of items that indicates what items the inventory has.
	 */
	public void setList(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
}
