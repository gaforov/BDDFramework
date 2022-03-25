package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ConfigsReader;

import java.util.List;

import static base.BaseClass.driver;
import static base.BaseClass.setUp;
import static base.PageInitializer.*;
import static utils.CommonMethods.click_waitForClickability;

public class EmployeeSearchSteps {
    String empId = "27335A";

    @Given("user is navigated to HRM homepage")
    public void user_is_navigated_to_hrm_homepage() {
        setUp();
    }

    @Given("user is logged with valid admin credentials")
    public void user_is_logged_with_valid_admin_credentials() {
        loginPage.loginAndClick(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
    }

    @Given("user navigates to employee list page")
    public void user_navigates_to_employee_list_page() {
        pimPage.navigateToEmployeeList();
    }

    @Given("user enters employee's valid id")
    public void user_enters_employee_s_valid_id() {
        empListPage.searchEmployeeById(empId);
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        click_waitForClickability(empListPage.searchButton);
    }

    @Then("user see employee information is displayed")
    public void user_see_employee_information_is_displayed() {
        List<WebElement> empListSearchResultIds = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr/td[2]"));
        for (WebElement empSearchResultId : empListSearchResultIds) {
            String searchResultIdText = empSearchResultId.getText();
            while (searchResultIdText != null && !searchResultIdText.equals(empId)) {
                driver.findElement(By.xpath("(//div[@class='bottom']/ul/li/a)[5]")).click();
            }
            Assert.assertEquals("Employee is NOT found",empId,searchResultIdText);
            System.out.println("Employee is found with ID: " + empId);
        }

        driver.close();
    }

    @Given("user enters employee's valid firstname and lastname")
    public void user_enters_employee_s_valid_firstname_and_lastname() {
    }
}
