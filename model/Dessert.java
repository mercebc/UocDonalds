package edu.uoc.donalds.model;

/**
 * This class represents a dessert of the UocDonald's menu.
 * 
 * @author merce.bauza
 * @version 1.0
 *  
 */
public class Dessert extends Item{

	private boolean isDiabetic;
	
	/**
	 * Default constructor.
	 * Sets isDiabetic to false.
	 * 
	 * @throws ItemException When the parent's constructor throw this exception.
	 */
	public Dessert() throws ItemException{
		super();
		setDiabetic(false);
	}
	
	/**
	 * Constructor with arguments.
	 * 
	 * @param name Dessert's name.
	 * @param isDiabetic It is true if the dessert can be eaten by a diabetic person, otherwise it is false.	
	 * @param imageSrc The source of the dessert's image.
	 * @param netPrice Dessert's net price.
	 * @param tax Dessert's tax.
	 * @param kcal Dessert's kcal.
	 * @param stock Number of desserts of this type that the restaurant has.
	 * @throws ItemException When some method throws this exception.
	 */
	public Dessert(String name, boolean isDiabetic, String imageSrc, double netPrice, double tax, double kcal, int stock) throws ItemException{
		super(name, imageSrc, netPrice, tax, kcal, stock);
		setDiabetic(isDiabetic);
	}
	
	/**
	 * Returns the current status of the private field "isDiabetic".
	 * 
	 * @return It returns "true" if the dessert can be eaten by a diabetic person, otherwise it returns "false".
	 */
	public boolean isDiabetic() {
		return isDiabetic;
	}

	/**
	 * Replaces the current value of the private field "isDiabetic".
	 * 
	 * @param isDiabetic New value for the private field "isDiabetic".
	 */
	public void setDiabetic(boolean isDiabetic) {
		this.isDiabetic = isDiabetic;
	}
}
