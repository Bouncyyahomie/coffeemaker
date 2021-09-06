package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for Inventory class.
 *
 * @author Jakkrathorn Srisawad
 */
public class InventoryTest{

    /**
     * The object under test.
     */
    private Inventory inventory;

    /**
     * Initializes Inventory object we wish to test.
     *
     * @throws InventoryException if there was an error parsing the item
     *                         amount when add to inventory.
     */
    @Before
    public void setUp() throws InventoryException {
        inventory = new Inventory();
    }

    @Test
    public void testAddChocolateWithNegativeNumber() throws InventoryException {
        inventory.addChocolate("-1");
    }

    @Test
    public void testAddChocolateWithNonNumber() throws InventoryException {
        inventory.addChocolate("ADD");
    }

    @Test
    public void testAddSugarWithNegativeNumber() throws InventoryException {
        inventory.addSugar("-1");
    }

    @Test
    public void testAddSugarWithNonNumber() throws InventoryException {
        inventory.addSugar("ADD");
    }

    @Test
    public void testAddMilkWithNegativeNumber() throws InventoryException {
        inventory.addMilk("-1");
    }

    @Test
    public void testAddMilkWithNonNumber() throws InventoryException {
        inventory.addMilk("ADD");
    }

    @Test
    public void testAddCoffeeWithNegativeNumber() throws InventoryException {
        inventory.addCoffee("-1");
    }

    @Test
    public void testAddCoffeeWithNonNumber() throws InventoryException {
        inventory.addCoffee("ADD");
    }

    @Test
    public void testSetInventoryWithNegativeNumber() throws InventoryException {
        inventory.setChocolate(-1);
        inventory.setSugar(-1);
        inventory.setMilk(-1);
        inventory.setCoffee(-1);
    }

}