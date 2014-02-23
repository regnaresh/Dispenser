package beveragedispenser;
import java.io.*;
import java.util.*;
/**************************************************************************
 * Class: BaristaMatic
 * Author: Naresh
 * Functionality: Beverage Dispenser 
 * Class: BaristaMatic
 *************************************************************************/
//Note: Clas BaristaMatic can also be implemented as Singleton Class as the
//application does not require creating objects more than one. However, for
//simplicity it has been implemented as a regular class.

public class BaristaMatic {
	
	//default number of units for any ingredient
	private static final int DEFAULT_UNITS = 10;

	//price of an ingredient
	private static final float PRICE_COFFEE = 0.75f;
	private static final float PRICE_DECAF_COFFEE = 0.75f;
	private static final float PRICE_SUGAR = 0.25f;
	private static final float PRICE_CREAM = 0.25f;
	private static final float PRICE_STEAM_MILK = 0.35f;
	private static final float PRICE_FOAM_MILK = 0.35f;
	private static final float PRICE_ESPRESSO = 1.10f;
	private static final float PRICE_COCOA = 0.90f;
	private static final float PRICE_WHIP_CREAM = 1.00f;

	//Stores ingredients as keys and number of units as values
	private static TreeMap<String, Integer> ingredientMap = new TreeMap<String, Integer>();
	
	//Stores drinks as keys and cost of it as values
	private static TreeMap<String, Float> menuMap = new TreeMap<String, Float>();

	//Initializes the Dispenser with default stock and Menu structure
	public BaristaMatic(){
		Restock();
		InitializeDrinks();
	}
	
	//Display Main screen with inventory and menu
	public void MainScreen() {
		DisplayInventory();
		DisplayMenu();

	}

	//Re-stock all the ingredients with their default units
	public void Restock() {
		ingredientMap.put("Coffee", new Integer(DEFAULT_UNITS));
		ingredientMap.put("Decaf Coffee", new Integer(DEFAULT_UNITS));
		ingredientMap.put("Sugar", new Integer(DEFAULT_UNITS));
		ingredientMap.put("Cream", new Integer(DEFAULT_UNITS));
		ingredientMap.put("Steamed Milk", new Integer(DEFAULT_UNITS));
		ingredientMap.put("Foamed Milk", new Integer(DEFAULT_UNITS));
		ingredientMap.put("Espresso", new Integer(DEFAULT_UNITS));
		ingredientMap.put("Cocoa", new Integer(DEFAULT_UNITS));
		ingredientMap.put("Whipped Cream", new Integer(DEFAULT_UNITS));
	}

	//returns the stock for the given ingredient
	public Integer GetStock(String ingredient) throws ClassCastException , NullPointerException{
		return ingredientMap.get(ingredient);
	}
	
	//updates a stock for an ingredient
	public void UpdateStock(String ingredient, Integer stock) throws ClassCastException , NullPointerException{
		ingredientMap.put(ingredient, stock);
	}
	
	//Initialize drinks with their costs
	public void InitializeDrinks() {
		menuMap.put("Caffe Americano", GetCaffeAmericanoPrice());
		menuMap.put("Caffe Latte", GetCaffeLattePrice());
		menuMap.put("Coffee", GetCoffeePrice());
		menuMap.put("Decaf Coffee", GetDecafCoffeePrice());
		menuMap.put("Caffe Mocha", GetCaffeMochaPrice());
		menuMap.put("Cappuccino", GetCappuccinoPrice());

	}

	//return individual drink price
	public Float GetCaffeAmericanoPrice() {
		return new Float(3 * PRICE_ESPRESSO);
	}

	public Float GetCaffeLattePrice() {
		return new Float((2 * PRICE_ESPRESSO) + PRICE_STEAM_MILK);
	}

	public Float GetCoffeePrice() {
		return new Float((3 * PRICE_COFFEE) + PRICE_SUGAR + PRICE_CREAM);
	}

	public Float GetDecafCoffeePrice() {
		return new Float((3 * PRICE_DECAF_COFFEE) + PRICE_SUGAR + PRICE_CREAM);
	}

	public Float GetCaffeMochaPrice() {
		return new Float(PRICE_ESPRESSO + PRICE_COCOA + PRICE_STEAM_MILK
				+ PRICE_WHIP_CREAM);
	}

	public Float GetCappuccinoPrice() {
		return new Float((2 * PRICE_ESPRESSO) + PRICE_STEAM_MILK
				+ PRICE_FOAM_MILK);
	}

	//Displays Inventory list
	public void DisplayInventory() {
		System.out.println("Inventory:");
		for (String key : ingredientMap.keySet())
			System.out.println(key + "," + ingredientMap.get(key));

	}

	//Displays drinks menu list
	public void DisplayMenu() {
		int i = 1;
		System.out.println("Menu:");
		for (String key : menuMap.keySet()) {
			System.out.format("%d,%s,%.2f,%s %n", i, key, menuMap.get(key),CheckInventory(key));
			i++;
		}
	}
	
	//Checks Inventory before dispensing
	public boolean CheckInventory(String drink) throws ClassCastException , NullPointerException{
		boolean flag = false;
		if(drink.equals("Caffe Americano")){
			if(GetStock("Espresso") >= 3)
				flag = true;
		}
		else if(drink.equals("Caffe Latte")){
			if((GetStock("Espresso") >= 2) && (GetStock("Steamed Milk") >=1))
				flag = true;
		}
		else if(drink.equals("Caffe Mocha")){
			if((GetStock("Espresso") >= 1) && (GetStock("Cocoa") >=1) && 
					(GetStock("Steamed Milk") >=1) && (GetStock("Whipped Cream") >=1))
				flag = true;
		}
		else if(drink.equals("Cappuccino")){
			if((GetStock("Espresso") >= 2) && (GetStock("Steamed Milk") >=1)
					&& (GetStock("Foamed Milk") >=1))
				flag = true;
		}
		else if(drink.equals("Coffee")){
			if((GetStock("Coffee") >= 3) && (GetStock("Sugar") >=1) && 
					(GetStock("Cream") >=1))
				flag = true;
		}
		else if(drink.equals("Decaf Coffee")){
			if((GetStock("Decaf Coffee") >= 3) && (GetStock("Sugar") >=1) 
					&& (GetStock("Cream") >=1))
				flag = true;
		}
		else 
				flag = false;
		
		return flag;
	}

	//Dispenses Drinks as per stocks
	public void DispenseCaffeAmericano() {
		if(CheckInventory("Caffe Americano")){
			UpdateStock("Espresso", new Integer(GetStock("Espresso")-3));
			System.out.println("Dispensing: Caffe Americano");	
		}
		else
			System.out.println("Out of Stock: Caffe Americano");
		
	}

	public void DispenseCaffeLatte() {
		if(CheckInventory("Caffe Latte")){
			UpdateStock("Espresso", new Integer(GetStock("Espresso")-2));
			UpdateStock("Steamed Milk", new Integer(GetStock("Steamed Milk")-1));
			System.out.println("Dispensing: Caffe Latte");	
		}
		else
			System.out.println("Out of Stock: Caffe Latte");
		
	}

	public void DispenseCaffeMocha() {
		if(CheckInventory("Caffe Mocha")){
			UpdateStock("Espresso", new Integer(GetStock("Espresso")-1));
			UpdateStock("Cocoa", new Integer(GetStock("Cocoa")-1));
			UpdateStock("Steamed Milk", new Integer(GetStock("Steamed Milk")-1));
			UpdateStock("Whipped Cream", new Integer(GetStock("Whipped Cream")-1));
			System.out.println("Dispensing: Caffe Mocha");	
		}
		else
			System.out.println("Out of Stock: Caffe Mocha");
	}

	public void DispenseCappuccino() {
		if(CheckInventory("Cappuccino")){
			UpdateStock("Espresso", new Integer(GetStock("Espresso")-2));
			UpdateStock("Steamed Milk", new Integer(GetStock("Steamed Milk")-1));
			UpdateStock("Foamed Milk", new Integer(GetStock("Foamed Milk")-1));
			System.out.println("Dispensing: Cappuccino");	
		}
		else
			System.out.println("Out of Stock: Cappuccino");
	}

	public void DispenseCoffee() {
		if(CheckInventory("Coffee")){
			UpdateStock("Coffee", new Integer(GetStock("Coffee")-3));
			UpdateStock("Sugar", new Integer(GetStock("Sugar")-1));
			UpdateStock("Cream", new Integer(GetStock("Cream")-1));
			System.out.println("Dispensing: Coffee");	
		}
		else
			System.out.println("Out of Stock: Coffee");
		
	}

	public void DispenseDecafCoffee() {
		if(CheckInventory("Decaf Coffee")){
			ingredientMap.put("Decaf Coffee", new Integer(GetStock("Decaf Coffee")-3));
			ingredientMap.put("Sugar", new Integer(GetStock("Sugar")-1));
			ingredientMap.put("Cream", new Integer(GetStock("Cream")-1));
			System.out.println("Dispensing: Decaf Coffee");	
		}
		else
			System.out.println("Out of Stock: Decaf Coffee");
	}

	//Main 
	public static void main(String[] args) throws IOException {
		BaristaMatic objbm = new BaristaMatic();
		String input;
		Scanner scanInput;
		do {
			objbm.MainScreen();
			
			//Takes input from the user
			scanInput = new Scanner(System.in);
			input = scanInput.next();
			if (input.equals("1"))
				objbm.DispenseCaffeAmericano();
			else if (input.equals("2"))
				objbm.DispenseCaffeLatte();
			else if (input.equals("3"))
				objbm.DispenseCaffeMocha();
			else if (input.equals("4"))
				objbm.DispenseCappuccino();
			else if (input.equals("5"))
				objbm.DispenseCoffee();
			else if (input.equals("6"))
				objbm.DispenseDecafCoffee();
			else if (input.equals("r") || input.equals("R"))
				objbm.Restock();
			else if (input.equals("q") || input.equals("Q"))
				break;
			else
				System.out.println("Invalid selection: " + input);

		} while (!(input.equals("q") || input.equals("Q")));
	}
}
