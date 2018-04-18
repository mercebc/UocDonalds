package edu.uoc.donalds.view.cmd;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import edu.uoc.donalds.controller.Kiosk;
import edu.uoc.donalds.model.DiningLocation;
import edu.uoc.donalds.model.Item;
import edu.uoc.donalds.model.OrderException;

/**
 * CmdApp function of the Kiosk JAVA Practice
 * 
 * @author merce.bauza
 * @version 1.0
 */
public class CmdApp {

	private Scanner in;
	Kiosk kiosk = null;

	/**
	 * Default constructor. It creates an instance of Kiosk.
	 */
	public CmdApp() {
		// not necessary if extending Object.
		super();
		kiosk = new Kiosk();
		in = new Scanner(System.in);		
	}

	/**
	 * Manages the Welcome screen.
	 * @throws OrderException 
	 */
	public void manageWelcome() throws OrderException{		
		System.out.println("\n*********Welcome to UOCDonald's***********");
		System.out.println("\n\tPress any character and enter to start your order!!!");
		System.out.println("*****************************");
		in.next();
		kiosk.createOrder();
	}
	
	/**
	 * Manages the DiningLocation selection screen. 
	 * @throws OrderException 
	 */	
	public void manageDiningLocation() throws OrderException{
		
		do{
			System.out.println("\n*********Choose your dining location***********");
			System.out.println("\n\t1. Eat in.");
			System.out.println("\n\t2. Take away.");
			System.out.println("\n\n\t3. Go back.");
			System.out.println("*****************************");
			try{
				int choice = in.nextInt();
				if(choice==1){
					kiosk.setDiningLocation(DiningLocation.EATIN);
					break;
				}else if(choice==2){
					kiosk.setDiningLocation(DiningLocation.TAKEAWAY);
					break;
				}else if(choice==3){
					manageWelcome();
				}else{
					System.err.println("[ERROR] Your option is incorrect!! Try again!!");
				}
			}catch(InputMismatchException e) {
				System.err.println("[ERROR] You must type a number!!!");
				in.next();// To avoid infinite loop
			}			
		}while(true);
	}
	
	/**
	 * Manages the actions performed in the current order. 
	 * @throws OrderException 
	 */	
	public void manageOrder() throws OrderException{
		List<Item> list = null;
		
		do{
			System.out.println("\n*********Choose a category********* *********Other options***********");
			System.out.println("\n\t1. Mains.\t\t\t5. Show your order.");
			System.out.println("\n\t2. Sides.\t\t\t6. Delete an item from your order.");
			System.out.println("\n\t3. Beverages.\t\t\t7. Commit your order.");
			System.out.println("\n\t4. Desserts.\t\t\t8. Go back.");		
			System.out.println("***********************************");
			try{
				int choice = in.nextInt();
			
				if(choice==1){
					list = kiosk.getInventoryPerCategory("MAINCOURSE");
					manageAddItem2Order(list);
				}else if(choice==2){
					list = kiosk.getInventoryPerCategory("SIDE");
					manageAddItem2Order(list);
				}else if(choice==3){
					list = kiosk.getInventoryPerCategory("BEVERAGE");
					manageAddItem2Order(list);
				}else if(choice==4){
					list = kiosk.getInventoryPerCategory("DESSERT");
					manageAddItem2Order(list);
				}else if(choice==5){				
					System.out.println(kiosk.showOrder());				
				}else if(choice==6){				
					manageRemoveItemFromOrder();
				}else if(choice==7){
					try {
						kiosk.commitOrder();
						System.out.println("TAKE THE RECEIPT AND ENJOY YOUR MEAL!!!");
						break;
					} catch (Exception e) {					
						System.out.println(e.getMessage());
					}
				}else if(choice==8){
					manageDiningLocation();						
				}else{
					System.err.println("[ERROR] Your option is incorrect!! Try again!!");
				}			
				
			}catch(InputMismatchException e) {
				System.err.println("[ERROR] You must type a number!!!");
				in.next();// To avoid infinite loop
			}
		}while(true);		
	}
	
	/**
	 * Manages the addition of a new item in the current order.
	 * @param list List of items to display, so that the user can choose one of them.
	 */
	private void manageAddItem2Order(List<Item> list){
		if(!list.isEmpty()){
			do{
				AtomicInteger index = new AtomicInteger();
				list.forEach((item) -> System.out.println(index.incrementAndGet()+". "+item));
				System.out.println("Choose the number of the item you want to add ('0' means go back):");
				try{
					int choice = in.nextInt();
					choice--;
					if(choice>=0 && choice<list.size()){ 
						try{
							kiosk.addItem2Order(list.get(choice));
							System.out.println("You have just added "+list.get(choice).getName()+" to your order!!!");
						}catch(Exception e){
							System.out.println(e.getMessage());
						}
						break;
					}else if(choice!=-1){
						System.err.println("[ERROR] Your option is incorrect!! Try again!!");
					}else{
						break;
					}
				}catch(InputMismatchException e) {
					System.err.println("[ERROR] You must type a number!!!");
					in.next();// To avoid infinite loop
				}
			}while(true);
			
		}else{
			System.out.println("There are no items for this category!!!");
		}
	}
	
	/**
	 * Manages the deletion of an item from the current order. It shows the Order's item, so that the user can choose one.
	 */
	private void manageRemoveItemFromOrder(){
		List<Item> list = kiosk.getItemsOrder();
		
		if(!list.isEmpty()){
			do{
				AtomicInteger index = new AtomicInteger();
				list.forEach((item) -> System.out.println(index.incrementAndGet()+". "+item));
				System.out.println("Choose the number of the item you want to remove ('0' means go back):");
				try{
					int choice = in.nextInt();
					choice--;
					if(choice>=0 && choice<list.size()){ 
						try{
							String itemName = list.get(choice).getName();
							kiosk.removeItemFromOrder(choice);
							System.out.println("You have just removed "+itemName+" from your order!!!");
						}catch(Exception e){
							System.out.println(e.getMessage());
						}
						break;
					}else if(choice!=-1){
						System.err.println("[ERROR] Your option is incorrect!! Try again!!");
					}else{
						break;
					}
				}catch(InputMismatchException e) {
					System.err.println("[ERROR] You must type a number!!!");
					in.next();// To avoid infinite loop
				}
			}while(true);
			
		}else{
			System.out.println("There are no items for this category!!!");
		}
	}
	/**
	 * Program entry point.
	 * 
	 * @param args arguments to the program (they are not needed).
	 * @throws OrderException 
	 */
	public static void main(String[] args) throws OrderException {
		CmdApp program = new CmdApp();
		
		do{
			program.manageWelcome();
			program.manageDiningLocation();
			program.manageOrder();
		}while(true);
	}	
}