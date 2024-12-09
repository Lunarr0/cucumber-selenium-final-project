Feature:Shopping functionality

  Background:
    Given the home page is opened
    And the 'Username' field is filled with 'problem_user'
    And the 'Password' field is filled with 'secret_sauce'
    And the 'Login' button is clicked


  Scenario Outline: incorrect item name
    Given the '<item 1>' link is clicked
    Then the incorrect '<item 2>' is displayed
    Examples:
      | item 1               | item 2               |
      | Sauce Labs Backpack  | Sauce Labs Fleece Jacket |
      | Sauce Labs Bike Light| Sauce Labs Bolt T-Shirt |
      | Sauce Labs Bolt T-Shirt| Sauce Labs Onesie   |
      |Sauce Labs Fleece Jacket | ITEM NOT FOUND   |
      |Sauce Labs Onesie   | Test.allTheThings() T-Shirt (Red)  |
      |Test.allTheThings() T-Shirt (Red) | Sauce Labs Backpack     |


Scenario Outline: Unsuccessful checkout
Given the '<item>' is added to the cart
And the 'Cart' button is clicked
And the 'Checkout' button is clicked
And the 'First Name' field is filled with 'John'
And the 'Last Name' field is filled with 'Doe'
Then the 'First Name' field is filled with 'Doe'
And the 'Last Name' field is filled with ' '
And the 'Zip Code' field is filled with '12345'
When the 'Continue' button is clicked
Then the '<checkoutErrorMessage>' message is shown for checkout
  And the cart should be cleared
Examples:
| item                    | checkoutErrorMessage |
| Sauce Labs Onesie         |  Error: Last Name is required|
| Sauce Labs Fleece Jacket |  Error: Last Name is required|



  Scenario: User opens the about page
    Given the 'BurgerIcon' button is clicked
    And the 'About' button is clicked
    Then the user is directed to 'https://saucelabs.com/error/404'