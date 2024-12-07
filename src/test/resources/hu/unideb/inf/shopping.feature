Feature: Saucedemo Shopping

  Background:
    Given the home page is opened
    And the 'Username' field is filled with 'standard_user'
    And the 'Password' field is filled with 'secret_sauce'
    And the 'Login' button is clicked

    Scenario: Buying a backpack and t-shirt
      Given the 'Sauce Labs Backpack' is added to the cart
      And the 'Sauce Labs Bolt T-Shirt' is added to the cart
      And the 'Cart' button is clicked
      And the 'Checkout' button is clicked
      And the 'First Name' field is filled with 'testname_first'
      And the 'Last Name' field is filled with 'testname_last'
      And the 'Zip Code' field is filled with '1111'
      When the 'Continue' button is clicked
      Then the price should read 'Total: $49.66'
      Then the 'Finish' button is clicked


    Scenario Outline: Checking item price
        Given the '<item>' link is clicked
        Then the '<price>' is displayed
      Examples:
        | item | price |
        | Sauce Labs Backpack                 | $29.99|
        | Sauce Labs Bike Light               | $9.99 |
        | Sauce Labs Bolt T-Shirt             | $15.99|
        | Sauce Labs Fleece Jacket            | $49.99|
        | Sauce Labs Onesie                   | $7.99 |
        | Test.allTheThings() T-Shirt (Red)   | $15.99|


  Scenario Outline: Buying two items
    Given the '<item 1>' is added to the cart
    And the '<item 2>' is added to the cart
    And the 'Cart' button is clicked
    And the 'Checkout' button is clicked
    And the 'First Name' field is filled with '<firstname>'
    And the 'Last Name' field is filled with '<lastname>'
    And the 'Zip Code' field is filled with '<zipcode>'
    When the 'Continue' button is clicked
    Then the price should read '<total>'
    Then the 'Finish' button is clicked

    Examples:
      | item 1                  | item 2                            | firstname | lastname | zipcode | total         |
      | Sauce Labs Backpack     | Sauce Labs Bike Light             | first     | last     | 1234    | Total: $43.18 |
      | Sauce Labs Onesie       | Sauce Labs Fleece Jacket          | mr        | boy      | 2345    | Total: $62.62 |
      | Sauce Labs Bolt T-Shirt | Test.allTheThings() T-Shirt (Red) | mrs       | girl     | 2345    | Total: $34.54 |


  Scenario Outline: Verify Sorting by Price
    Given the user selects '<sortingType>' from the Sorting dropdown
    Then the item should be sorted by price in '<order>'
    Examples:
      | sortingType         | order           |
      | Price (low to high) | ascending       |
      | Price (high to low) | descending      |


  Scenario: Sort items by name
    When the user selects "Name (A to Z)" from the sorting options
    Then the items should be sorted in ascending order by name


  Scenario: Sort items by name
    When the user selects "Name (Z to A)" from the sorting options
    Then the items should be sorted in descending order by name


  Scenario: An item is removed from the cart
    Given the cart is empty
    And  the 'Sauce Labs Backpack' is added to the cart
    When the 'Cart' button is clicked
    And the user clicks the 'Remove' button for the 'Sauce Labs Backpack'
    Then the cart should be updated correctly

 Scenario:   Add an item to the cart and verify the cart count
   Given the 'Sauce Labs Backpack' is added to the cart
   Then the cart count should be "1"


  Scenario Outline: Incorrect checking out
    Given the '<item>' is added to the cart
    When the 'Cart' button is clicked
    And the 'Checkout' button is clicked
    And the 'First Name' field is filled with '<firstname>'
    And the 'Last Name' field is filled with '<lastname>'
    And the 'Zip Code' field is filled with '<zipcode>'
    When the 'Continue' button is clicked
    Then the '<checkoutErrorMessage>' message is shown for checkout
    Examples:
      | item                    | firstname | lastname | zipcode | checkoutErrorMessage |
      |  Sauce Labs Onesie     |           |          |         |  Error: First Name is required|
      |  Sauce Labs Fleece Jacket      | first     |          | 1234    | Error: Last Name is required|
      |  Sauce Labs Bolt T-Shirt    |           | last     | 1234    | Error: First Name is required|
      | Sauce Labs Bike Light     | first     | last     |         | Error: Postal Code is required|



  Scenario: User opens the about page
    Given the 'BurgerIcon' button is clicked
    And the 'About' button is clicked
    Then the user is directed to 'https://saucelabs.com/'



