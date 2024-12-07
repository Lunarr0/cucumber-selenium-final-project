package hu.unideb.inf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SocialsStepDefs extends AbstractStepDefs {


    public SocialsStepDefs(TestContext testContext) {
        super(testContext);

    }


    @Then("the user is redirected to {string}")
    public void theUserIsRedirectedToHttpsXComSaucelabs(String url) {
        String actualUrl = homePage.userIsRedirected();
        assertEquals(url, actualUrl);
    }
}