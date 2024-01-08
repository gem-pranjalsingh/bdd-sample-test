package com.qa.StepDefination;

import com.gemini.generic.bdd.GemJarCucumberBase;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.qa.Locators.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;

public class StepDefinition extends DriverAction{
    HashMap<String, Object> sharedData = new HashMap<>();

    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        String actualTitle = getTitle();
        String expectedTitle = "Swag Labs";
        STATUS titleStatus = actualTitle.equals("Swag Labs") ? STATUS.PASS : STATUS.FAIL;
        GemTestReporter.addTestStep("Verify page title", "Expected: " + expectedTitle + " Actual: " + actualTitle,
                titleStatus);
        sharedData.put("titleStatus", titleStatus);

    }

    @When("Enter valid credentials with username {string} and password {string}")
    public void enterValidCredentialsWithUsernameAndPassword(String username, String password) {
        if (sharedData.get("titleStatus").equals(STATUS.PASS)) {
            typeText(Locator.userName_txtBox, username, "Enter Username", "Username: " + username);
            typeText(Locator.password_txtBox, password, "Enter Password", "Password: ********");
        }
    }

    @And("Click on login button")
    public void clickOnLoginButton() {
        if (sharedData.get("titleStatus").equals(STATUS.PASS)) {
            click(Locator.login_BTN, "Login button");
        }
    }

    @Then("User should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        if (sharedData.get("titleStatus").equals(STATUS.PASS)) {
            String loginStatus = getElementText(Locator.verifyLogin);
            if (loginStatus.equals("Swag Labs")) {
                GemTestReporter.addTestStep("Verify Login", "Home page loaded successfully", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify Login", "Failed to load home page", STATUS.FAIL, takeSnapShot());
            }
        }
    }

    @Then("Verify an error message {string}")
    public void verifyAnErrorMessage(String message) {
        if (getElement(Locator.errorPopup).isDisplayed()) {
            GemTestReporter.addTestStep("Verify if error popup appeared", "Popup appeared", STATUS.PASS, takeSnapShot());
            String actualMessage = getElementText(Locator.errorPopup_MSG);
            if (!actualMessage.contains(message)) {
                GemTestReporter.addTestStep("Verify if error message is equal to <strong>'"+message+"'</strong>",
                        "Validated successfully", STATUS.PASS, takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify if error message is equal to <strong>'"+message+"'</strong>",
                        "Validation Failed", STATUS.FAIL, takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Verify if error popup appeared", "Popup didn't appear", STATUS.FAIL, takeSnapShot());
        }
    }
}