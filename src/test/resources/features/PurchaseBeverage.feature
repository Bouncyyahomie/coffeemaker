Feature: The user selects a beverage and inserts an amount of money. The money must be an integer.
  If the beverage is in the RecipeBook and the user paid enough money the beverage will be dispensed
  and any change will be returned. The user will not be able to purchase a beverage
  if they do not deposit enough money into the CoffeeMaker. A user's money will be returned
  if there is not enough inventory to make the beverage.
  Upon completion, the Coffee Maker displays a message about the purchase status and is returned to the main menu.

  Background:
    Given Open coffee shop

  Scenario: Purchase a beverage with enough money
    When I want to purchase a Coffee
    And  I deposit a coffee maker with 100 baht
    Then I get change 50 baht

  Scenario: Purchase a beverage with not enough money
    When I want to purchase a Latte
    And  I deposit a coffee maker with 40 baht
    Then I get change 40 baht

  Scenario: Purchase a beverage with not enough ingredients
    When I want to purchase a Mocha
    And  I deposit a coffee maker with 75 baht
    Then I get change 75 baht

  Scenario: Purchase a beverage that doesn't exist
    When I want to purchase a Tea
    And  I deposit a coffee maker with 200 baht
    Then I get change 200 baht
