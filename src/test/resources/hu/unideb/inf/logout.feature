Feature: Saucedemo LogOut

  Background:
    Given the home page is opened
    And the 'Username' field is filled with 'standard_user'
    And the 'Password' field is filled with 'secret_sauce'
    And the 'Login' button is clicked


  Scenario: User logs out
    Given the 'BurgerIcon' button is clicked
    And the 'Logout' button is clicked
    Then the user is directed to 'https://www.saucedemo.com/'