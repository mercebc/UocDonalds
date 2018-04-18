package edu.uoc.donalds.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for class Item
 * 
 * @author merce.bauza
 *
 */
public class ItemTest {

	/**
	 * Tests setStock method
	 * 
	 * @throws ItemException
	 */
	@Test
	public void set2UnitsToItemStock() throws ItemException {
		Item coleslaw = new Salad(); //create new Salad coleslaw
		int stock = 2;
		
		coleslaw.setStock(stock);//set coleslaw's stock to 2
		assertEquals(2, coleslaw.getStock());//check coleslaw stock and test that equals to 2
	}

}
