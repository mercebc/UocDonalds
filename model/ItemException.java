package edu.uoc.donalds.model;
/**
 * This class represents an item exception.
 * 
 * @author merce.bauza
 * @version 1.0
 * 
 */
public class ItemException extends Exception {

	/**
	 * Default constructor.
	 */
	public ItemException() {
		super();
	}

	/**
	 * Constructor with 1 argument.
	 * 
	 * @param msg Message we want to display.
	 */
	public ItemException(String msg) {
		super(msg);		
	}
}