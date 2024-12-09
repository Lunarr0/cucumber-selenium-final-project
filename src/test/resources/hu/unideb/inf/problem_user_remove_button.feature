Feature: Remove button functionality

  Background:
    Given the home page is opened

Scenario: invalid remove btn functionality

  Given the 'Username' field is filled with 'problem_user'
  And the 'Password' field is filled with 'secret_sauce'
  And the 'Login' button is clicked
  And the user is directed to 'https://www.saucedemo.com/inventory.html'
  And the 'Sauce Labs Backpack' is added to the cart
  And the user clicks the 'Remove' button for the 'Sauce Labs Backpack'
  Then the 'Sauce Labs Backpack' should remain in the cart




