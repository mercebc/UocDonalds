package edu.uoc.donalds.model;

/**
 * This class represents a main course of the UocDonald's menu.
 * 
 * @author merce.bauza
 * @version 1.0
 *  
 */
public class MainCourse extends Item{

	private boolean isHot;
	
	/**
	 * Default constructor.
	 * Sets isHot to true.
	 * 
	 * @throws ItemException When the parent's constructor throws this exception.
	 */
	public MainCourse() throws ItemException{
		super();
		setHot(true);
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param name Main course's name.
	 * @param isHot It is true if the main course is hot, otherwise (i.e. cold) it is false.	
	 * @param imageSrc The source of the main course's image.
	 * @param netPrice Main course's net price.
	 * @param tax Main course's tax.
	 * @param kcal Main course's kcal.
	 * @param stock Number of main courses of this type that the restaurant has.
	 * @throws ItemException When some method throws this exception.
	 */
	public MainCourse(String name, boolean isHot, String imageSrc, double netPrice, double tax, double kcal, int stock) throws ItemException{
		super(name, imageSrc, netPrice, tax, kcal, stock);
		setHot(isHot);
	}
	
	/**
	 * Returns the current value of the private field "isHot".
	 * 
	 * @return true is hot, false is cold.
	 */
	public boolean isHot() {
		return isHot;
	}

	/**
	 * Replaces the current value of the private field "isHot".
	 * 
	 * @param isHot New value for the private field "isHot".
	 */
	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}
}
