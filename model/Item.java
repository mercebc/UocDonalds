package edu.uoc.donalds.model;
import java.text.DecimalFormat;

/**
 * This class represents an item of the UocDonald's menu.
 * 
 * @author merce.bauza
 * @version 1.0
 * 
 */

public abstract class Item {

	private String name, imageSrc;
	private double netPrice, tax, kcal;
	private int stock;
	private int expectedPurchase;
		
	/**
	 * Default constructor. It sets the private fields as follows:
	 * 
	 * name = "Dummy";
	 * imageSrc = "./";
	 * netPrice = 0.1;
	 * tax = 0;
	 * kcal = 0;
	 * stock = 0
	 * 
	 * @throws ItemException is thrown according to setName, setNetPrice, setTax, setKcal and setStock which are used in.	
	 */
	public Item() throws ItemException{
		this("Dummy", "./",0.1,0,1,0);
		this.setExpectedPurchase(0);
	}
	
	/**
	 * Constructor with arguments.
	 * 
	 * @param name Item's name. 
	 * @param imageSrc Location of the image in the project.
	 * @param netPrice Net price of the item (i.e. without taxes).
	 * @param tax Tax that must be applied.
	 * @param kcal Number of kcalories that the item has
	 * @param stock Number of the units of the item.
	 * 
	 * @throws ItemException is thrown according to setName, setNetPrice, setTax, setKcal and setStock which are used in.
	 */
	public Item(String name, String imageSrc, double netPrice, double tax, double kcal, int stock) throws ItemException{
		setName(name);
		setImageSrc(imageSrc);
		setNetPrice(netPrice);
		setTax(tax);
		setKcal(kcal);
		setStock(stock);	
		setExpectedPurchase(0);
	}
	
	/**
	 * Name's getter
	 * 
	 * @return Value of the private field "name".
	 */
	public String getName() {
		return name;
	}


	/**
	 * Name's setter
	 * 
	 * @param name New value for the private field "name".
	 * @throws ItemException is thrown when the length of the new name is longer than 15 characters.
	 */
	public void setName(String name) throws ItemException{
		if(name.length()>15){
			throw new ItemException("The name cannot be longer than 15 characters!!");
		}
		this.name = name;

	}

	/**
	 * imageSrc's getter
	 * 
	 * @return Value of the private field "imageSrc".
	 */
	public String getImageSrc() {
		return imageSrc;
	}

	/**
	 * imageSrc's setter
	 * 
	 * @param imageSrc New value for the private field "imageSrc".	
	 */
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	/**
	 * netPrice's getter
	 * 
	 * @return Value of the private field "netPrice".
	 */
	public double getNetPrice() {
		return netPrice;
	}

	
	/**
	 * netPrice's setter
	 * 
	 * @param netPrice New value for the private field "netPrice".
	 * @throws ItemException is thrown when the value of the new netPrice is negative.
	 */
	public void setNetPrice(double netPrice) throws ItemException{
		if(netPrice<0){
			throw new ItemException("Net price cannot be a negative value!!");
		}
		this.netPrice = netPrice;
	}

	/**
	 * tax's getter
	 * 
	 * @return Value of the private field "tax".
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * tax's setter
	 * 
	 * @param tax New value for the private field "tax".
	 * @throws ItemException is thrown when the value of the new tax is negative.
	 */
	public void setTax(double tax) throws ItemException{
		if(tax<0){
			throw new ItemException("Tax cannot be a negative value!!");
		}
		this.tax = tax;
	}

	/**
	 * kcal's getter
	 * 
	 * @return Value of the private field "kcal".
	 */
	public double getKcal() {
		return kcal;
	}

	/**
	 * kcal's setter
	 * 
	 * @param kcal New value for the private field "kcal".
	 * @throws ItemException is thrown when the value of the new kcal is negative.
	 */
	public void setKcal(double kcal) throws ItemException{
		if(kcal<0){
			throw new ItemException("Kcal cannot be a negative value!!");
		}
		this.kcal = kcal;
	}

	/**
	 * stock's getter
	 * 
	 * @return Value of the private field "stock".
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * stock's setter
	 * 
	 * @param stock New value for the private field "stock".
	 * @throws ItemException is thrown when the value of the new netPrice is negative.
	 */
	public void setStock(int stock) throws ItemException{
		if(stock<0){
			throw new ItemException("Stock cannot be a negative value!!");
		}else{ //Este else se podr�a quitar, porque si no se entrara en el if se ejecutar�a lo que va despu�s.
			this.stock = stock;			
		}
	}	
	
	/**
	 * expectedPurchase getter
	 * 
	 * @return expectedPurchase
	 */
	public int getExpectedPurchase() {
		return expectedPurchase;
	}

	/**
	 * expectedPurchase setter
	 * 
	 * @param expectedPurchase
	 */
	public void setExpectedPurchase(int expectedPurchase) {
		this.expectedPurchase = expectedPurchase;
	}
	
	/**
	 * Increase expectedPurchase in 1 unit
	 */
	public void increase1ExpectedPurchase() {
		setExpectedPurchase(getExpectedPurchase()+1);
	}
	
	/**
	 * Decrease expectedPurchase 1 unit
	 * 
	 */
	public void decrease1ExpectedPurchase() {
		setExpectedPurchase(getExpectedPurchase()-1);
		//ItemException is not needed because this method is never called when Expected Purchase is 0.
	}
	
	/**
	 * This method decreases 1 unit the value of the private field "stock".
	 * 
	 * @throws ItemException is thrown if the current value of stock less 1 is equal to a negative value (i.e. -1).
	 */
	public void decrease1Stock() throws ItemException{
		try{
			setStock(getStock()-1);
		}catch(ItemException e){	
			//Si setStock lanza una excepci�n, quiere decir en este caso que actualmente el valor de stock es cero.
			//As� pues, recogemos la excepci�n y lanzamos otra con un mensaje m�s apropiado para decrease1Stock. 
			throw new ItemException("This item is sold out!! You cannot decrease 1 unit.");
		}
	}
	
	/**
	 * This method calculates an returns item's gross price, i.e. (net price * tax)+(net price). If tax = 0, then gross price = net price.
	 * 
	 * @return Item's gross price.
	 */
	public double getGrossPrice() {
		double tax = getTax();
		if(tax==0)
			return getNetPrice();
		else
			return (getNetPrice()*tax)+getNetPrice();
	}
	
	/**
	 * isSouldOut method shows if an item is sold out
	 * there are two ways the item could mean is sold out 
	 * if stock equals to 0 
	 * if the expectedPurchase is the same as the stock, there aren't more items to be purchased. 
	 * 
	 * @return true or false if its sold out or not
	 */
	public boolean isSoldOut() {
		if( this.getStock()==0 || (this.getExpectedPurchase()==this.getStock())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method overrides Object's toString.
	 * 
	 * @return String with the format: "name......grosPrice �"
	 */
	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("0.00");
		return getName()+"......"+df.format(getGrossPrice())+" �";
	}
	
}