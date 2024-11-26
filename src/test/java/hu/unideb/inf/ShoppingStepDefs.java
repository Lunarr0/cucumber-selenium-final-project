package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void theItemShouldBeSortedByPriceInOrder(String price) {
        homePage.selectSortingOrder(price);
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
        homePage.emptyCart();
    }

    @Given("the cart is empty")
    public void theCartIsEmpty() {
        homePage.emptyCart();
    }

    @Then("the {string} message is shown for checkout")
    public void theCheckoutErrorMessageMessageIsShown(String checkoutErrorMessage) {
        assertEquals(checkoutErrorMessage,  homePage.getCheckOutErrorMessage() );


    }


    @Then("the user is directed to {string}")
    public void theUserIsDirectedToHttpsSaucelabsCom(String url) {
        assertEquals(url, homePage.getPageUrl());
    }


}
