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
     * Initializes Inventory object.
     *
     * @throws InventoryException if there was an error parsing the item
     *                         amount when add to inventory.
     */
    @Before
    public void setUp() throws InventoryException {
        inventory = new Inventory();
    }

    /**
     * Given a default inventory
     * When we try to add chocolate with negative number
     * Then we get an inventory exception.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddChocolateWithNegativeNumber() throws InventoryException {
        inventory.addChocolate("-1");
    }

    /**
     * Given a default inventory
     * When we try to add chocolate with non-numeric
     * Then we get an inventory exception.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddChocolateWithNonNumeric() throws InventoryException {
        inventory.addChocolate("ADD");
    }

    /**
     * Given a default inventory
     * When we try to add sugar with negative number
     * Then we get an inventory exception.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddSugarWithNegativeNumber() throws InventoryException {
        inventory.addSugar("-1");
    }

    /**
     * Given a default inventory
     * When we try to add sugar with neon-numeric
     * Then we get an inventory exception.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddSugarWithNonNumeric() throws InventoryException {
        inventory.addSugar("ADD");
    }

    /**
     * Given a default inventory
     * When we try to add milk with negative number
     * Then we get an inventory exception.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddMilkWithNegativeNumber() throws InventoryException {
        inventory.addMilk("-1");
    }

    /**
     * Given a default inventory
     * When we try to add milk with non-numeric
     * Then we get an inventory exception.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddMilkWithNonNumeric() throws InventoryException {
        inventory.addMilk("ADD");
    }

    /**
     * Given a default inventory
     * When we try to add coffee with negative number
     * Then we get an inventory exception.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddCoffeeWithNegativeNumber() throws InventoryException {
        inventory.addCoffee("-1");
    }

    /**
     * Given a default inventory
     * When we try to add coffee with non-numeric
     * Then we get an inventory exception.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddCoffeeWithNonNumeric() throws InventoryException {
        inventory.addCoffee("ADD");
    }

    /**
     * Given a default inventory
     * When we try to set an ingredients with value <= 0
     * Then value of each component doesn't change
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testSetInventoryWithNegativeNumber() throws InventoryException {
        inventory.setChocolate(-1);
        assertEquals(15, inventory.getChocolate());
        inventory.setSugar(-1);
        assertEquals(15, inventory.getSugar());
        inventory.setMilk(-1);
        assertEquals(15, inventory.getMilk());
        inventory.setCoffee(-1);
        assertEquals(15, inventory.getCoffee());
    }

    /**
     * Given a default inventory
     * When we try to set ingredients with value >= 0
     * Then value of each compenent will return a new amount.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testSetInventoryWithPositiveNumber() throws InventoryException {
        inventory.setChocolate(5);
        assertEquals(5, inventory.getChocolate());
        inventory.setSugar(10);
        assertEquals(10, inventory.getSugar());
        inventory.setMilk(15);
        assertEquals(15, inventory.getMilk());
        inventory.setCoffee(20);
        assertEquals(20, inventory.getCoffee());
    }

}