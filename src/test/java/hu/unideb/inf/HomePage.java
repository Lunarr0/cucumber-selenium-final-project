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
import java.util.Optional;

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
    @FindBy(css = ".inventory_item_name")
    private List<WebElement> itemNames;



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
            Map.entry("LinkedIn", By.className("social_linkedin")),
            Map.entry("Reset App State", By.id("reset_sidebar_link"))
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

    }

    public String getTotal() {
        return priceLabel.getText();
    }

    public String getPrice() {
        return itemPriceLabel.getText();
    }

    public void selectSortingOption(String sortingType) {
       sortingDropdown.click();

        String optionValue = switch (sortingType.toLowerCase()) {
            case "price (low to high)" -> "lohi";
            case "price (high to low)" -> "hilo";
            case "name (a to z)" -> "az";
            case "name (z to a)" -> "za";
            default -> throw new IllegalArgumentException("Invalid sorting option: " + sortingType);
        };
        driver.findElement(By.cssSelector(".product_sort_container option[value='" + optionValue.toLowerCase() + "']")).click();
    }


    public List<Double> selectSortingOrder() {
        List<WebElement> prices = driver.findElements(By.cssSelector(".inventory_item_price"));

        // Create a list to store prices as integers
        List<Double> priceValues = new ArrayList<>();
        for (WebElement price : prices) {
            String priceText = price.getText().replace("$", "");
            priceValues.add(Double.parseDouble(priceText));
        }

       return priceValues;
    }

    public String getItemCount() {
        return shoppingBadge.getText();

    }

    public void removeItemFromCart(String item) {

        WebElement removeButton = driver.findElement(removeButtons.get(item));
        removeButton.click();
    }



    public Optional<String> isCartEmpty() {
        try {
            // If the badge is present, return its text wrapped in an Optional
            return Optional.of(shoppingBadge.getText());
        } catch (NoSuchElementException e) {
            // If the badge is not found, return an empty Optional
            return Optional.empty();
        }
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String userIsRedirected() {

        // Store the current window handle
        String originalWindow = driver.getWindowHandle();

        // Wait for a new window or tab to open
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        // Switch to the new tab
        String redirectedUrl = "";
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                // Get the URL of the new tab
                redirectedUrl = driver.getCurrentUrl();
                // Close the new tab
                driver.close();
                break;
            }
        }



       // switch back to the original tab
        driver.switchTo().window(originalWindow);

        return redirectedUrl;

    }


    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : itemNames) {
            names.add(item.getText());
        }
        return names;
    }

    public boolean isItemInCart(String itemName) {
        try {
            driver.findElement(removeButtons.get(itemName));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getDisplayedItemName() {
        return driver.findElement(By.cssSelector(".inventory_details_name")).getText();
    }

    public void clearCart() {
        // Navigate to the cart page
        driver.get("https://www.saucedemo.com/cart.html");

        // Find and remove all items from the cart
        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".cart_item .btn_secondary"));
        for (WebElement button : removeButtons) {
            button.click();
        }


//        // Navigate to the cart page
//        driver.get("https://www.saucedemo.com/cart.html");
//
//        // Wait for the cart items to load
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(driver -> !driver.findElements(By.cssSelector(".cart_item")).isEmpty());
//
//        // Find and remove all items from the cart
//        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".cart_item .btn_secondary"));
//        for (WebElement button : removeButtons) {
//            button.click();
//            // Optionally, wait for the item to be removed
//            new WebDriverWait(driver, Duration.ofSeconds(2))
//                    .until(driver -> button.isDisplayed() == false); // Wait until button is no longer displayed
//        }
//
//        // Optionally, navigate back to the inventory page
//        driver.get("https://www.saucedemo.com/inventory.html");
//    }
    }
}

