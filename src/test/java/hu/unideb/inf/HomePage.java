package hu.unideb.inf;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage {

    private static final String PAGE_URL = "https://www.saucedemo.com/";

    private final WebDriver driver;

    @FindBy(css = "#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error")
    private WebElement checkoutErrorMessage;

    @FindBy(css = "#login_button_container > div > form > div.error-message-container.error > h3")
    private WebElement errorMessage;
    @FindBy(css = "#checkout_summary_container > div > div.summary_info > div.summary_total_label")
    private WebElement priceLabel;
    @FindBy(css = "#inventory_item_container > div > div > div.inventory_details_desc_container > div.inventory_details_price")
    private WebElement itemPriceLabel;
    @FindBy(css = ".product_sort_container")
    private WebElement sortingDropdown;
    @FindBy (css = ".shopping_cart_badge")
    private  WebElement shoppingBadge;



    private static final Map<String , By> itemLinks = Map.of(
            "Sauce Labs Backpack", By.id("item_4_title_link"),
            "Sauce Labs Bike Light", By.id("item_0_title_link"),
            "Sauce Labs Bolt T-Shirt", By.id("item_1_title_link"),
            "Sauce Labs Fleece Jacket", By.id("item_5_title_link"),
            "Sauce Labs Onesie", By.id("item_2_title_link"),
            "Test.allTheThings() T-Shirt (Red)", By.id("item_3_title_link")
    );

    private static final Map<String, By> textFields = Map.of(
       "Username", By.id("user-name"),
       "Password", By.id("password"),
       "First Name", By.id("first-name"),
       "Last Name", By.id("last-name"),
       "Zip Code", By.id("postal-code")
    );

    private static final Map<String, By> itemButtons = Map.of(
       "Sauce Labs Backpack", By.id("add-to-cart-sauce-labs-backpack"),
       "Sauce Labs Bike Light", By.id("add-to-cart-sauce-labs-bike-light"),
       "Sauce Labs Bolt T-Shirt", By.id("add-to-cart-sauce-labs-bolt-t-shirt"),
       "Sauce Labs Fleece Jacket", By.id("add-to-cart-sauce-labs-fleece-jacket"),
       "Sauce Labs Onesie", By.id("add-to-cart-sauce-labs-onesie"),
       "Test.allTheThings() T-Shirt (Red)", By.id("add-to-cart-test.allthethings()-t-shirt-(red)")

    );


    private static final Map<String, By> navigationButtons = Map.ofEntries(
            Map.entry("Login", By.id("login-button")),
            Map.entry("Cart", By.className("shopping_cart_link")),
            Map.entry("Checkout", By.id("checkout")),
            Map.entry("Continue", By.id("continue")),
            Map.entry("Finish", By.id("finish")),
            Map.entry("Remove", By.className("cart_button")),
            Map.entry("BurgerIcon", By.id("react-burger-menu-btn")),
            Map.entry("About", By.id("about_sidebar_link")),
            Map.entry("Logout", By.id("logout_sidebar_link")),
            Map.entry("Twitter", By.className("social_twitter")),
            Map.entry("Facebook", By.className("social_facebook")),
            Map.entry("LinkedIn", By.className("social_linkedin"))
    );



    Map<String, By> removeButtons = Map.of(
            "Sauce Labs Backpack", By.id("remove-sauce-labs-backpack"),
            "Sauce Labs Bike Light", By.id("remove-sauce-labs-bike-light"),
            "Sauce Labs Bolt T-Shirt", By.id("remove-sauce-labs-bolt-t-shirt"),
            "Sauce Labs Fleece Jacket", By.id("remove-sauce-labs-fleece-jacket"),
            "Sauce Labs Onesie", By.id("remove-sauce-labs-onesie"),
            "Test.allTheThings() T-Shirt (Red)", By.id("remove-test.allthethings()-t-shirt-(red)")
    );



    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void closePage() {
        driver.quit();
    }

    public void fillOutField(String field, String text) {
        driver.findElement(textFields.get(field)).sendKeys(text);
    }

    public void clickButton(String button) {
        driver.findElement(navigationButtons.get(button)).click();

    }

    public void clickItemButton(String button){
        driver.findElement(itemLinks.get(button)).click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getCheckOutErrorMessage() {
        return checkoutErrorMessage.getText();
    }

    public void addItemToCart(String item) {
        driver.findElement(itemButtons.get(item)).click();
//
    }

    public String getTotal() {
        return priceLabel.getText();
    }

    public String getPrice() {
        return itemPriceLabel.getText();
    }

    public void selectSortingOption(String sortingType) {
       sortingDropdown.click();

        String optionValue = "";
        if (sortingType.equalsIgnoreCase("Price (low to high)")) {
            optionValue = "lohi";
        } else if (sortingType.equalsIgnoreCase("Price (high to low)")) {
            optionValue = "hilo";
        }
        driver.findElement(By.cssSelector(".product_sort_container option[value='" + optionValue.toLowerCase().replace(" ", "-") + "']")).click();
    }

    public void selectSortingOrder(String order) {
        List<WebElement> prices = driver.findElements(By.cssSelector(".inventory_item_price"));

        // Create a list to store prices as integers
        List<Double> priceValues = new ArrayList<>();
        for (WebElement price : prices) {
            // Extract and parse the price value
            String priceText = price.getText().replace("$", "");
            priceValues.add(Double.parseDouble(priceText));
        }

        // Check if prices are sorted in the expected order
        if (order.equals("ascending")) {
            for (int i = 0; i < priceValues.size() - 1; i++) {
                assertTrue(priceValues.get(i) <= priceValues.get(i + 1));  // Ensure prices are in ascending order
            }
        } else if (order.equals("descending")) {
            for (int i = 0; i < priceValues.size() - 1; i++) {
                assertTrue(priceValues.get(i) >= priceValues.get(i + 1));  // Ensure prices are in descending order
            }
        }
    }

    public String getItemCount() {
        return shoppingBadge.getText();

    }

    public void removeItemFromCart(String item) {

        WebElement removeButton = driver.findElement(removeButtons.get(item));
        removeButton.click();
    }

    public void emptyCart() {
        try {
            if (shoppingBadge.getText().isEmpty()){
                System.out.println("Cart is Empty");
            }else {
                throw new AssertionError("Cart is not empty, badge text: " + shoppingBadge.getText());
            }
        } catch (NoSuchElementException e) {
            // If the shopping cart badge does not exist
            System.out.println("Cart is empty, no badge found.");
        }
        }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void userIsRedirected(String url) {

        // Store the current window handle
        String originalWindow = driver.getWindowHandle();

        // Wait for a new window or tab to open
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        // Switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Verify the URL of the new tab
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, url, "The redirected URL is incorrect");

        // Close the new tab and switch back to the original tab
        driver.close();
        driver.switchTo().window(originalWindow);
    }
}

