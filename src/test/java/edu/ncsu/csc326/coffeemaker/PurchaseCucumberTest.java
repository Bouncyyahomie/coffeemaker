package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.junit.Assert.*;

public class PurchaseCucumberTest {

    private CoffeeMaker coffeeMaker;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    private int purchaseID;
    private int deposit;

    @Given("Open coffee shop")
    public void open_coffee_shop() throws RecipeException{
        coffeeMaker = new CoffeeMaker();

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

        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
    }

    @When("I want to purchase a {word}")
    public void i_want_to_purchase_a_beverage_recipe(String beverage) {
        switch (beverage){
            case "Coffee":
                purchaseID = 0;
                break;
            case "Mocha":
                purchaseID = 1;
                break;
            case "Latte":
                purchaseID = 2;
                break;
            default:
                purchaseID = 3;
        };
    }

    @When("I deposit a coffee maker with {int} baht")
    public void i_deposit_a_coffee_maker_with_baht(int money) {
        deposit = money;
    }

    @Then("I get change {int} baht")
    public void i_get_change_baht(int change) {
        assertEquals(change, coffeeMaker.makeCoffee(purchaseID, deposit));
    }

}