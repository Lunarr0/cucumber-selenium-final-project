package hu.unideb.inf;

import io.cucumber.java.en.Then;

public class SocialsStepDefs extends AbstractStepDefs {
    public SocialsStepDefs(TestContext testContext) {
        super(testContext);
    }

    @Then("the user is redirected to {string}")
    public void theUserIsRedirectedToHttpsXComSaucelabs(String url) {
        homePage.userIsRedirected(url);
    }
}
