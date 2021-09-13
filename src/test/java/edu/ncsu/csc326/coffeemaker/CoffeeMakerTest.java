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

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    private CoffeeMaker mockCoffeeMaker;
    private RecipeBook  recipeBook;
    private Inventory   inventory;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    private Recipe[] recipesList;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();
        recipeBook = mock(RecipeBook.class);
        inventory = new Inventory();
        mockCoffeeMaker = new CoffeeMaker(recipeBook, inventory);

        //Coffe
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Mocha
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Latte
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Hot Chocolate
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");

        recipesList = new Recipe[]{recipe1, recipe2, recipe3, recipe4};
    }


    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with well-formed quantities
     * Then we do not get an exception trying to read the inventory quantities.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "7", "0", "9");
        coffeeMaker.addInventory("5", "5", "5", "5");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryException() throws InventoryException {
        coffeeMaker.addInventory("4", "-1", "asdf", "3");
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the valid recipe and paying more than
     * the coffee costs
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
    public void testAddARecipe() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
    }

    /**
     * From the requirements only three recipes may be added to the CoffeeMaker
     * When we add four different recipes to coffee maker won't able to add it
     * Then it will be return false.
     */
    @Test
    public void testAddOnlyThreeRecipe() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertTrue(coffeeMaker.addRecipe(recipe2));
        assertTrue(coffeeMaker.addRecipe(recipe3));
        assertFalse(coffeeMaker.addRecipe(recipe4));
    }

    /**
     * Given two same name recipe
     * When we add duplicate name of recipes to coffee maker won't able to add it because the name of recipe must be unique
     * Then it will be return false.
     */
    @Test
    public void testAddDuplicate() {
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
    public void testRemoveDeletedRecipe() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertEquals(recipe1.getName(), coffeeMaker.deleteRecipe(0));
        assertNotEquals(recipe1.getName(), coffeeMaker.deleteRecipe(0));
    }

    /**
     * When we try to delete a recipe but doesn't have any recipe in coffee maker
     * Then coffee maker will return null.
     */
    @Test
    public void  testDeleteNothing() {
        assertNull(coffeeMaker.deleteRecipe(0));
    }

    /**
     * Given a one valid recipe
     * When we edit recipe1 to recipe2
     * Then recipe will be a recipe2.
     */
    @Test
    public void testEditRecipe() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        coffeeMaker.editRecipe(0, recipe2);
        assertEquals(coffeeMaker.getRecipes()[0], recipe2);
    }

    /**
     * When we try to edit a recipe that not in a list
     * Then we will get a null.
     */
    @Test
    public void testEditNotInList() {
        assertNull(coffeeMaker.editRecipe(0, recipe2));
    }

    /**
     * When we add items to inventory with well-formed quantities
     * Then inventory should be update quantities.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testCheckInventory() throws InventoryException {
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", coffeeMaker.checkInventory());
        coffeeMaker.addInventory("5", "5", "5", "5");
        assertEquals("Coffee: 20\nMilk: 20\nSugar: 20\nChocolate: 20\n", coffeeMaker.checkInventory());
    }

    /**
     * Given a one valid recipe
     * When user try to paid a beverage but not enough chocolate
     * Then coffee maker won't be dispensed and user's money will be returned.
     */
    @Test
    public void testMakeWithNotEnoughChocolate() {
        assertTrue(coffeeMaker.addRecipe(recipe2));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));
    }

    /**
     * Given a one valid recipe
     * When user try to paid a beverage but not enough coffee
     * Then coffee maker won't be dispensed and user's money will be returned.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Test
    public void testMakeWithNotEnoughCoffee() throws RecipeException {
        recipe1.setAmtCoffee("20");
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));
    }

    /**
     * Given a one valid recipe
     * When user try to paid a beverage but not enough sugar
     * Then coffee maker won't be dispensed and user's money will be returned.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Test
    public void testMakeWithNotEnoughSugar() throws RecipeException {
        recipe3.setAmtSugar("20");
        assertTrue(coffeeMaker.addRecipe(recipe3));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));
    }

    /**
     * Given a one valid recipe
     * When user try to paid a beverage but not enough milk
     * Then coffee maker won't be dispensed and user's money will be returned.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                           amount when setting up the recipe.
     */
    @Test
    public void testMakeWithNotEnoughMilk() throws RecipeException {
        recipe4.setAmtMilk("20");
        assertTrue(coffeeMaker.addRecipe(recipe4));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));
    }

    /**
     * Given a one valid recipe
     * When user try to paid a beverage with not enough money
     * Then coffee maker won't be dispensed and user's money will be returned.
     */
    @Test
    public void testMakeWithNotEnoughMoney() {
        assertTrue(coffeeMaker.addRecipe(recipe3));
        assertEquals(50, coffeeMaker.makeCoffee(0, 50));
    }

    /**
     * When user try to paid a beverage but doesn't have recipe in coffeemaker
     * Then coffee maker won't be dispensed and user's money will be returned.
     */
    @Test
    public void testMakeWithNull() {
        assertEquals(50, coffeeMaker.makeCoffee(0, 50));
    }

    /**
     * Test the amount of change when paid a beverage from coffee maker
     * Then we get the correct change back and
     * mockito verify how many times getRecipes was invoked.
     */
    @Test
    public void mockTestMakeCoffee() {
        when(recipeBook.getRecipes()).thenReturn(recipesList);
        assertEquals(25, mockCoffeeMaker.makeCoffee(0, 75));
        verify(recipeBook, times(4)).getRecipes();
    }


    /**
     * When user try to paid a beverage but not enough milk
     * Then coffee maker won't be dispensed and user's money will be returned and
     * mockito verify how many times getRecipes was invoked.
     *
     * @throws RecipeException
     */
    @Test
    public void mockTestMakeWithNotEnoughIngredients() throws RecipeException {
        recipe4.setAmtMilk("20");
        when(recipeBook.getRecipes()).thenReturn(recipesList);
        assertEquals(200, mockCoffeeMaker.makeCoffee(3, 200));
        verify(recipeBook, times(3)).getRecipes();
    }

    /**
     * When user try to paid a beverage with not enough money
     * Then coffee maker won't be dispensed and user's money will be returned and
     * mockito verify how many times getRecipes was invoked.
     */
    @Test
    public void mockTestMakeWithNotEnoughMoney() {
        when(recipeBook.getRecipes()).thenReturn(recipesList);
        assertEquals(50, mockCoffeeMaker.makeCoffee(1, 50));
        verify(recipeBook, times(2)).getRecipes();
    }

    /**
     * Test to paid a beverage but doesn't have recipe in coffeemaker
     * Then coffee maker won't be dispensed and user's money will be returned and
     * mockito verify how many times getRecipes was invoked.
     */
    @Test
    public void mockTestMakeWithNull() {
        when(recipeBook.getRecipes()).thenReturn(new Recipe[]{null});
        assertEquals(50, mockCoffeeMaker.makeCoffee(0, 50));
        verify(recipeBook).getRecipes();
    }

}
