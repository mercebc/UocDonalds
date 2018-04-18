package edu.uoc.donalds.model;
/**
 * This class represents a side of the UocDonald's menu.
 * 
 * @author merce.bauza
 * @version 1.0
 *  
 */
public class Side extends Item{

	private int units;
	
	/**
	 * Default constructor.
	 * Sets units to 6.
	 * 
	 * @throws ItemException When the parent's constructor or setUnits throw this exception.
	 */
	public Side() throws ItemException{
		super();
		setUnits(6);
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param name Side's name.
	 * @param units Number of units that the side has, e.g. 9 chicken balls, 50 fries, etc.	
	 * @param imageSrc The source of the side's image.
	 * @param netPrice Side's net price.
	 * @param tax Side's tax.
	 * @param kcal Side's kcal.
	 * @param stock Number of sides of this type that the restaurant has.
	 * @throws ItemException When some method throws this exception.
	 */
	public Side(String name, int units, String imageSrc, double netPrice, double tax, double kcal, int stock) throws ItemException{
		super(name, imageSrc, netPrice, tax, kcal, stock);
		setUnits(units);
	}
	
	/**
	 * Return the current value of the private field "units".
	 * 
	 * @return Current value of the private field "units".
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * Replaces the current value of the private field "units".
	 * 
	 * @param units New value for the private field "units".
	 * @throws ItemException When the argument is less than 1.
	 */
	public void setUnits(int units) throws ItemException{
		if(units<=0){
			throw new ItemException("Units cannot be either zero or negative!!");
		}else{
			this.units = units;
		}
	}
}
