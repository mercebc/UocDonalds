package edu.uoc.donalds.model;
import java.text.DecimalFormat;

/**
 * This class represents a beverage of the UocDonald's menu.
 * 
 * @author merce.bauza
 * @version 1.0
 *  
 */
public class Beverage extends Item{

	private int centiliters;
	private double alcoholVolume;
	private boolean isFizzy;
	
	/**
	 * Default constructor.
	 * Sets centiliters to 33, alcoholVolume to 0 and isFizzy to true.
	 * 
	 * @throws ItemException When the parent's constructor or setCentiliters or setAlcoholVolume throw this exception.
	 */
	public Beverage() throws ItemException{
		super();
		setCentiliters(33);
		setAlcoholVolume(0);
		setFizzy(true);
	}
	
	/**
	 * Constructor with arguments.
	 * 
	 * @param name Beverage's name.
	 * @param centiliters Size of the drink in centiliters.
	 * @param alcoholVolume Drink's alcohol by volume. 
	 * @param isFizzy It is true if the drink is fizzy, otherwise it is false.
	 * @param imageSrc The source of the drink's image.
	 * @param netPrice Drink's net price.
	 * @param tax Drink's tax.
	 * @param kcal Drink's kcal.
	 * @param stock Number of drinks of this type that the restaurant has.
	 * @throws ItemException When some method throws this exception.
	 */
	public Beverage(String name, int centiliters, double alcoholVolume, boolean isFizzy, String imageSrc, double netPrice, double tax, double kcal, int stock) throws ItemException{
		super(name, imageSrc, netPrice, tax, kcal, stock);
		setCentiliters(centiliters);
		setAlcoholVolume(alcoholVolume);
		setFizzy(isFizzy);
	}

	/**
	 * Returns the current value of the private field "centiliters".
	 * 
	 * @return Value of the private field "centiliters".
	 */
	public int getCentiliters() {
		return centiliters;
	}
	
	/**
	 * * Replaces the current value of the private field "centiliters".
	 * 
	 * @param centiliters New value for the private field "centiliters". 
	 * @throws ItemException When the argument is less or equal to 0.
	 */
	public void setCentiliters(int centiliters) throws ItemException{
		if(centiliters<=0){
			throw new ItemException("Centiliters cannot be either zero or negative!!");
		}else{
			this.centiliters = centiliters;
		}
	}

	/**
	 * Returns the current value of the private field "alcoholVolume".
	 * 
	 * @return Value of the private field "alcoholVolume".
	 */
	public double getAlcoholVolume() {
		return alcoholVolume;
	}

	/**
	 * Replaces the current value of the private field "alcoholVolume".
	 * 
	 * @param alcoholVolume New value for the private field "alcoholVolume".
	 * @throws ItemException When the argument is negative.
	 */
	public void setAlcoholVolume(double alcoholVolume) throws ItemException{
		if(alcoholVolume<0){
			throw new ItemException("Alcohol by volume cannot be a negative value!!");
		}else{
			this.alcoholVolume = alcoholVolume;
		}
	}

	/**
	 * Returns Beverage's "isFizzy" field.
	 * 
	 * 
	 * @return Boolean that indicates if the drink is fizzy (true) or not (false).
	 */
	public boolean isFizzy() {
		return isFizzy;
	}

	/**
	 * Replaces the current value of the private field "isFizzy".
	 * 
	 * @param isFizzy New value for the private field "isFizzy".
	 */
	public void setFizzy(boolean isFizzy) {
		this.isFizzy = isFizzy;
	}
	
	/**
	 * Overrides the Item's toString.
	 *  
	 * @return String with the drink's name, centiliters and gross price in euros.
	 */
	@Override	
	public String toString(){
		DecimalFormat df = new DecimalFormat("0.00");
		return getName()+" ("+getCentiliters()+" cl)......"+df.format(getGrossPrice())+" �";		
	}
}
