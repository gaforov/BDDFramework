package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.ConfigsUtility;

import static base.BaseClass.*;
import static base.PageInitializer.*;
import static utils.CommonMethods.*;

public class LoginSteps {

    // Scenario: valid admin login

    @Given("user is logged with valid admin credentials")
    public void user_is_logged_with_valid_admin_credentials() {
        loginPage.loginAndClick(ConfigsUtility.getProperty("username"), ConfigsUtility.getProperty("password"));
    }

    @Given("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        sendText(loginPage.username, ConfigsUtility.getProperty("username"));
        sendText(loginPage.password, ConfigsUtility.getProperty("password"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click_waitForClickability(loginPage.loginButton);
    }

    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
        String expectedMsg = "Welcome Admin";
        String actualMsg = dashboardPage.welcome.getText();
        Assert.assertEquals("Welcome message is not correct", expectedMsg, actualMsg);
    }

    // Scenario: valid ess login

    @Given("user enters valid ess username and password")
    public void user_enters_valid_ess_username_and_password() {
        sendText(loginPage.username, ConfigsUtility.getProperty("essUsername"));
        sendText(loginPage.password, ConfigsUtility.getProperty("essPassword"));

    }

    @Then("ess user is successfully logged in")
    public void ess_user_is_successfully_logged_in() {
        String expectedMsg = "Welcome Mike";
        Assert.assertEquals("Welcome message is not correct", expectedMsg, dashboardPage.welcome.getText());
    }

    // Scenario: login with valid username and invalid password

    @Given("user enters valid username and invalid password")
    public void user_enters_valid_username_and_invalid_password() {
        String invalidPassword = "pass123";
        sendText(loginPage.username, ConfigsUtility.getProperty("username"));
        sendText(loginPage.password, invalidPassword);
    }

    @Then("user see invalid credentials text on login page")
    public void user_see_invalid_credentials_text_on_login_page() {
        String expectedErrorMsg = "Invalid credentials";
        Assert.assertEquals("Error message is not correct", expectedErrorMsg, loginPage.errorMessage.getText());
    }

    @When("user enters valid {string} and {string}")
    public void userEntersValidAnd(String username, String password) {
        sendText(loginPage.username, username);
        sendText(loginPage.password, password);
    }

    @Then("{string} is successfully logged in")
    public void isSuccessfullyLoggedIn(String firstname) {
        Assert.assertTrue("User name doesn't match", dashboardPage.welcome.getText().contains(firstname));
    }

    @When("user enters invalid {string} andOr {string}")
    public void userEntersInvalidAndOr(String username, String password) {
        sendText(loginPage.username, username);
        sendText(loginPage.password, password);
    }

    @Then("user should see correct {string} displayed")
    public void userShouldSeeCorrectDisplayed(String errorMessage) {
        Assert.assertEquals("Error message is not correct", errorMessage, loginPage.errorMessage.getText());
    }

}
