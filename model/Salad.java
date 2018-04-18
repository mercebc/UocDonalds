package edu.uoc.donalds.model;

/**
 * @author merce.bauza
 *
 */
public class Salad extends MainCourse{
	
	private boolean isVegan;
	
	/**
	 * Default constructor
	 * Sets isVegan to false with setter
	 * Sets isHot to false with setter
	 * 
	 * @throws ItemException when the parent's constructor throws this exception.
	 */
	public Salad() throws ItemException{
		super();
		setIsVegan(false);
		setHot(false);
	}
	
	/**
	 * Constructor with parameters
	 * 
	 * @param name
	 * @param isVegan
	 * @param imageSrc
	 * @param netPrice
	 * @param tax
	 * @param kcal
	 * @param stock
	 * @throws ItemException
	 */
	public Salad(String name, boolean isVegan, String imageSrc, double netPrice, double tax, double kcal, int stock) throws ItemException{
		super(name, isVegan, imageSrc, netPrice, tax, kcal, stock);
	}
	
	/**
	 * isVegan getter
	 * 
	 * @return isVegan
	 */
	public boolean isVegan() {
		return isVegan;
	}
	
	/**
	 * isVegan setter
	 * 
	 * @param isVegan
	 */
	public void setIsVegan(boolean isVegan) {
		this.isVegan = isVegan;
	}
	
}
