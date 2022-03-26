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
    @Given("user enters new employee's firstname and lastname")
    public void user_enters_new_employee_s_firstname_and_lastname() {
        sendText(addEmployeePage.firstName, ConfigsUtility.getProperty("empFirstname"));
        sendText(addEmployeePage.lastName, ConfigsUtility.getProperty("empLastname"));
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click_waitForClickability(addEmployeePage.saveButton);
    }
    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        String expected = ConfigsUtility.getProperty("empFirstname") + " " + ConfigsUtility.getProperty("empLastname");
        String actual = personalDetailsPage.empFullName.getText();
        Assert.assertEquals("Employee name doesn't match", expected, actual);
        takeScreenshot(personalDetailsPage.empDetailsPageSection, "NewEmployeeAdded");
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
}