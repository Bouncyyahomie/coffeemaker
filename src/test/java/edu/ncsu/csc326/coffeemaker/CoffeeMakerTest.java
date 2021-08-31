/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.Assert.*;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Jakkrathorn Srisawad
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
	}
	
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
		coffeeMaker.addInventory( "5", "5", "5", "5");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

	/**
	 * Given a coffee maker with one valid recipe.
	 */
	@Test
	public void testAddARecipe(){
		assertTrue(coffeeMaker.addRecipe(recipe1));
	}

	/**
	 * From the requirements only three recipes may be added to the CoffeeMaker
	 * When we add more than three recipe to coffee maker won't able to add it
	 * Then it will be return false.
	 * */
	@Test
	public void testAddOnlyThreeRecipe() {
		assertTrue(coffeeMaker.addRecipe(recipe1));
		assertTrue(coffeeMaker.addRecipe(recipe2));
		assertTrue(coffeeMaker.addRecipe(recipe3));
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}

	/**
	 * Given two same name recipe
	 * When we add duplicate name recipe to coffee maker won't able to add it because the name of recipe must be unique
	 * Then it will be return false.
	 */
	@Test
	public void testAddDuplicate(){
		assertTrue(coffeeMaker.addRecipe(recipe1));
		assertFalse(coffeeMaker.addRecipe(recipe1));
	}

	/**
	 * Given a one valid recipe
	 * When we try to delete a recipe
	 * Then deleted recipe must be not show on recipe list.
	 */
	@Test
	public void testDeleteRecipe() {
		assertTrue(coffeeMaker.addRecipe(recipe1));
		assertEquals(recipe1.getName(), coffeeMaker.deleteRecipe(0));
		assertNull(coffeeMaker.getRecipes()[0]);
	}

	/**
	 * Given a one valid recipe
	 * When we already delete a recipe but want to delete it again
	 * Then coffee maker won't able to delete a deleted recipe, so it will be return false.
	 */
	@Test
	public void testRemoveDeletedRecipe(){
		assertTrue(coffeeMaker.addRecipe(recipe1));
		assertEquals(recipe1.getName(), coffeeMaker.deleteRecipe(0));
		assertNotEquals(recipe1.getName(), coffeeMaker.deleteRecipe(0));
	}

	/**
	 * Given a one valid recipe
	 * When we edit recipe1 to recipe2
	 * Then recipe will be a recipe2.
	 */
	@Test
	public void  testEditRecipe(){
		assertTrue(coffeeMaker.addRecipe(recipe1));
		coffeeMaker.editRecipe(0, recipe2);
		assertEquals(coffeeMaker.getRecipes()[0], recipe2);
	}

	/**
	 * When we add items to inventory with well-formed quantities
	 * Then inventory should be update quantities.
	 */
	@Test
	public void testCheckInventory() throws InventoryException {
		assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n",coffeeMaker.checkInventory());
		coffeeMaker.addInventory("5","5","5","5");
		assertEquals("Coffee: 20\nMilk: 20\nSugar: 20\nChocolate: 20\n",coffeeMaker.checkInventory());
	}

	/**
	 * Given two valid recipe
	 * When user try to paid a beverage with enough money
	 * Then coffee maker will be dispensed and any change will be returned.
	 */
	@Test
	public void testPurchaseBeverage() {
		assertTrue(coffeeMaker.addRecipe(recipe1));
		assertTrue(coffeeMaker.addRecipe(recipe3));
		assertEquals(50, coffeeMaker.makeCoffee(0, 100));
		assertEquals(0, coffeeMaker.makeCoffee(1, 100));
	}

	/**
	 * Given a one valid recipe
	 * When user try to paid a beverage but inventory not enough
	 * Then coffee maker won't be dispensed and user's money will be returned.
	 */
	@Test
	public void testMakeWithNotEnoughInventory() {
		assertTrue(coffeeMaker.addRecipe(recipe2));
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}

	/**
	 * Given a one valid recipe
	 * When user try to paid a beverage with not enough money
	 * Then coffee maker won't be dispensed and user's money will be returned.
	 */
	@Test
	public void testMakeWithNotEnoughMoney(){
		assertTrue(coffeeMaker.addRecipe(recipe3));
		assertEquals(50, coffeeMaker.makeCoffee(0, 50));
	}

}
