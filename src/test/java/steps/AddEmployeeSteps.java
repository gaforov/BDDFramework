package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.ConfigsUtility;

import static utils.CommonMethods.*;
import static base.PageInitializer.*;

public class AddEmployeeSteps {

    @Given("user navigates to AddEmployeePage")
    public void user_navigates_to_add_employee_page() {
        pimPage.navigateToAddEmployee();
    }
    @Given("user enters new employee's {string} and {string}")
    public void user_enters_new_employee_s_firstname_and_lastname(String firstName, String lastName) {
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.lastName, lastName);
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click_waitForClickability(addEmployeePage.saveButton);
    }
    @Then("{string} is added successfully")
    public void employee_is_added_successfully(String expectedName) {
        String actualName = personalDetailsPage.empFullName.getText();
        Assert.assertEquals("Employee name doesn't match", expectedName, actualName);
        takeScreenshot(personalDetailsPage.empDetailsPageSection, "NewEmployeeAdded");
        System.out.println(expectedName + " was added successfully");
    }

    @And("user deletes employee id")
    public void userDeletesEmployeeId() {
        addEmployeePage.employeeId.clear();
    }

    @And("user clicks on create login checkbox")
    public void userClicksOnCreateLoginCheckbox() {
        addEmployeePage.createCredentialsCheckbox.click();
    }

    @And("user enters new employee's login credentials")
    public void userEntersNewEmployeeSLoginCredentials() {
        addEmployeePage.createNewEmployeeCredentials();
    }

    @And("user enters new employee's {string}, {string}, and {string}")
    public void userEntersNewEmployeeSAnd(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }
}