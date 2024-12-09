package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingStepDefs extends AbstractStepDefs {


    public ShoppingStepDefs(TestContext testContext) {
        super(testContext);
    }

    @Given("the {string} is added to the cart")
    public void somethingIsAddedToTheCart(String item) {
        homePage.addItemToCart(item);
    }

    @Then("the price should read {string}")
    public void thePriceShouldRead(String total) {
        assertEquals(total, homePage.getTotal());
    }

    @Given("the {string} link is clicked")
    public void theItemLinkIsClicked(String item) {
        homePage.clickItemButton(item);
    }

    @Then("the {string} is displayed")
    public void thePriceIsDisplayed(String price) {
        assertEquals(price, homePage.getPrice());
    }


    @Given("the user selects {string} from the Sorting dropdown")
    public void theUserSelectsSortingTypeFromTheSortingDropdown(String sorting) {
        homePage.selectSortingOption(sorting);
    }

    @Then("the item should be sorted by price in {string}")
    public void theItemShouldBeSortedByPriceInOrder(String order) {

        List<Double> prices = homePage.selectSortingOrder();
        List<Double> sortedPrices = new ArrayList<>(prices);

        // Check if prices are sorted in the expected order
        if (order.equals("ascending")) {
           Collections.sort(sortedPrices);
        } else if (order.equals("descending")) {
           sortedPrices.sort(Collections.reverseOrder());
        }
        assertEquals(sortedPrices, prices);
    }

    @Given("the user is on the homepage")
    public void theUserIsOnTheHomepage() {
        homePage.openPage();
    }

    @Given("the user adds an {string} to the cart")
    public void theUserAddsAnItemToTheCart(String item) {
        homePage.addItemToCart(item);
    }

    @Then("the cart count should be {string}")
    public void theCartCountShouldBe(String count) {
        assertEquals(count, homePage.getItemCount());
    }

    @Given("the user adds the {string} to the cart")
    public void theUserAddsTheSauceLabsBackpackToTheCart(String item) {
        homePage.addItemToCart(item);
    }

    @And("the user clicks the {string} button for the {string}")
    public void theUserClicksTheRemoveButtonForTheSauceLabsBackpack(String button, String item) {
        homePage.removeItemFromCart(item);
    }

    @Then("the cart count should be updated correctly")
    public void theCartCountShouldBeUpdatedCorrectly() {
        homePage.getItemCount();
    }


    @Then("the cart should be updated correctly")
    public void theCartShouldBeUpdatedCorrectly() {
        Optional<String> badgeText = homePage.isCartEmpty();

        if (badgeText.isPresent()) {
            // If the Optional contains a value, the cart is not empty
            throw new AssertionError("Cart is not empty, badge text: " + badgeText.get());
        } else {
            // If the Optional is empty, the cart is empty
            System.out.println("Cart is empty, no badge found.");
        }
    }

    @Given("the cart is empty")
    public void theCartIsEmpty() {

        Optional<String> badgeText = homePage.isCartEmpty();

        if (badgeText.isPresent()) {
            throw new AssertionError("Cart is not empty. Badge text: " + badgeText.get());
        }
        System.out.println("Cart is confirmed to be empty.");
    }

    @Then("the {string} message is shown for checkout")
    public void theCheckoutErrorMessageMessageIsShown(String checkoutErrorMessage) {
        assertEquals(checkoutErrorMessage,  homePage.getCheckOutErrorMessage() );


    }


    @Then("the user is directed to {string}")
    public void theUserIsDirectedToHttpsSaucelabsCom(String url) {
        assertEquals(url, homePage.getPageUrl());
    }


    @When("the user selects {string} from the sorting options")
    public void theUserSelectsFromTheSortingOptions(String options) {
        homePage.selectSortingOption(options);
    }

    @Then("the items should be sorted in ascending order by name")
    public void theItemsShouldBeSortedInAscendingOrderByName() {
        //first get the list of items
        List<String> itemNames = homePage.getItemNames();

        //make a copy of the list and compare
        List<String> sortedItemNames = new ArrayList<>(itemNames);
        Collections.sort(sortedItemNames);

        assertEquals(sortedItemNames, itemNames);
    }

    @Then("the items should be sorted in descending order by name")
    public void theItemsShouldBeSortedInDescendingOrderByName() {

        List<String> itemNames = homePage.getItemNames();


        List<String> sortedItemNames = new ArrayList<>(itemNames);
        sortedItemNames.sort(Collections.reverseOrder());

        assertEquals(sortedItemNames, itemNames, "The items are not sorted in descending order by name");


    }

    @Then("the {string} should remain in the cart")
    public void theSauceLabsBackpackShouldRemainInTheCart(String itemName) {
        assertTrue(homePage.isItemInCart(itemName), "The 'Sauce Labs Backpack' should remain in the cart.");
        System.out.println(itemName);

    }

    @Then("the incorrect {string} is displayed")
    public void theIncorrectItemIsDisplayed(String itemName) {
        assertEquals(itemName, homePage.getDisplayedItemName());
    }


    @And("the cart should be cleared")
    public void theCartShouldBeCleared() {
        homePage.clearCart();
    }
}
