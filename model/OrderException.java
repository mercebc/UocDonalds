package edu.uoc.donalds.model;

/**
 * @author merce.bauza
 *
 */
public class OrderException extends Exception {

	/**
	 * Default constructor
	 */
	public OrderException() {
		super();
	}

	/**
	 * Constructor with 1 argument
	 * 
	 * @param msg Message we want to display.
	 */
	public OrderException(String msg) {
		super(msg);		
	}
	
}
