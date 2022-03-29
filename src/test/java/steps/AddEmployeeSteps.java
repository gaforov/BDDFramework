package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.ConfigsUtility;
import utils.Constants;
import utils.ExcelUtility;

import java.util.List;
import java.util.Map;

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

    @When("user enters new employee's details, clicks on save button and verify that employee added successfully")
    public void user_enters_new_employee_s_details_clicks_on_save_button_and_verify_that_employee_added_successfully(DataTable dataTable) {
        List<Map<String, String>> addEmployeeMapList = dataTable.asMaps();

        for (Map<String, String> map : addEmployeeMapList) {
            sendText(addEmployeePage.firstName, map.get("FirstName"));
            sendText(addEmployeePage.middleName, map.get("MiddleName"));
            sendText(addEmployeePage.lastName, map.get("LastName"));

//            System.out.println("map.get(\"FirstName\") = " + map.get("FirstName")); // get value by passing key
//            System.out.println("map.values() = "+ map.values());                    // get values only
//            System.out.println("map.keySet() = " + map.keySet());                   // get keys only
//            System.out.println("map.entrySet() = " + map.entrySet());               // get pairs of both key and value
//            System.out.println("------------------------------------------------");
            click_waitForClickability(addEmployeePage.saveButton);

            // Assertion starts here + take screenshot before clicking to next page
            String expectedFullName = map.get("FirstName") + " " + map.get("MiddleName") + " " + map.get("LastName");
            String actualFullName = personalDetailsPage.empFullName.getText();
            Assert.assertEquals("Added employee name doesn't match", expectedFullName, actualFullName);

            System.out.println(expectedFullName + " is added successfully");
            takeScreenshot(personalDetailsPage.empDetailsPageSection, "PASS/" + actualFullName);

            // get back to AddEmployeePge to add next employee
            pimPage.addEmployeeSubMenu.click();


        }
    }


    @When("user enters employee data from {string} sheet, then employee is added")
    public void userEntersEmployeeDataFromSheetThenEmployeeIsAdded(String sheetName) {
        List<Map<String, String>> mapList = ExcelUtility.excelIntoListOfMaps(Constants.TESTDATA_FILEPATH, "Employee");
        for (Map<String, String> map : mapList) {
                sendText(addEmployeePage.firstName, map.get("Firstname"));
                sendText(addEmployeePage.lastName, map.get("Lastname"));

                click_waitForClickability(addEmployeePage.saveButton);

                String expectedEmpName = map.get("Firstname") + " " + map.get("Lastname");
                Assert.assertEquals("Employee name doesn't match", expectedEmpName, personalDetailsPage.empFullName.getText());

                System.out.println(personalDetailsPage.empFullName.getText() + " was added successfully");
                click_waitForClickability(pimPage.addEmployeeSubMenu);
        }
    }
}