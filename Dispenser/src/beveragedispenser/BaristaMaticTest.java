package beveragedispenser;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaristaMaticTest {

	@Test
	public void testGetStock() {
		BaristaMatic bm = new BaristaMatic();
		assertEquals("Result: ", new Integer(10), bm.GetStock("Coffee"));
		//assertEquals("Result: ", new Integer(10), bm.GetStock("invalid_ingredient"));
		//assertEquals("Result: ", null, bm.GetStock(null));
		
	}

	@Test
	public void testCheckInventory() {
		BaristaMatic bm = new BaristaMatic();
		bm.UpdateStock("Coffee", 2);
		assertEquals("Result: ", true, bm.CheckInventory("Coffee"));
		//test case2: assertEquals("Result: ", true, bm.CheckInventory("Coffee"));
		//test case3: assertEquals("Result: ", false, bm.CheckInventory("invalid_drink"));
	}
}

/***************************************************************
 * Task: Maunal Testing 
 * 
 * Test Case1: Invalid input => '10'
 * OUTPUT: Invalid selection: 10
 * RESULT: PASS
 * 
 * Test Case2: Valid input => 3
 * OUTPUT: Dispensing: Caffe Mocha
 * RESULT: PASS
 * 
 * Test Case3: Selection of out of stock drink 
 * Ingredient : Espresso = > 2
 * Input: 1 (Cafe Americano)
 * OUTPUT: 	Inventory:
			Cocoa,8
			Coffee,10
			Cream,10
			Decaf Coffee,10
			Espresso,2
			Foamed Milk,10
			Steamed Milk,8
			Sugar,10
			Whipped Cream,8
			Menu:
			1,Caffe Americano,3.30,false 
			2,Caffe Latte,2.55,true 
			3,Caffe Mocha,3.35,true 
			4,Cappuccino,2.90,true 
			5,Coffee,2.75,true 
			6,Decaf Coffee,2.75,true
			1
			Out of Stock: Caffe Americano
 * RESULT: PASS 
 * 
 * Test Case4 : Re stock ingredients
 * Input : r or R
 * OUTPUT: 	r
			Inventory:
			Cocoa,10
			Coffee,10
			Cream,10
			Decaf Coffee,10
			Espresso,10
			Foamed Milk,10
			Steamed Milk,10
			Sugar,10
			Whipped Cream,10
 *
 *RESULT: PASS
 *
 *Test Case5: Quit
 *INPUT : q or Q
 *OUTPUT: program exited
 *RESULT: PASS
 **************************************************************/