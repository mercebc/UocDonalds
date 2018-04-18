package edu.uoc.donalds.model;

/**
 * @author merce.bauza
 *
 */
public class Burger extends MainCourse{
	
	private boolean isGlutenFree;
	
	/**
	 * Default constructor
	 * Sets isGlutenFree to false
	 * Sets isHot to true
	 * 
	 * @throws ItemException
	 */
	public Burger() throws ItemException{
		super();
		setIsGlutenFree(false);
		setHot(true);
	}
	
	/**
	 * Constructor with arguments
	 * 
	 * @param name
	 * @param isGlutenFree
	 * @param imageSrc
	 * @param netPrice
	 * @param tax
	 * @param kcal
	 * @param stock
	 * @throws ItemException
	 */
	public Burger(String name, boolean isGlutenFree, String imageSrc, double netPrice, double tax, double kcal, int stock) throws ItemException{
		super(name, isGlutenFree, imageSrc, netPrice, tax, kcal, stock);
	}

	/**
	 * isGlutenFree getter
	 * 
	 * @return isGlutenFree
	 */
	public boolean isGlutenFree() {
		return isGlutenFree;
	}

	/**
	 * isGlutenFree setter
	 * 
	 * @param isGlutenFree
	 */
	public void setIsGlutenFree(boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}

}
