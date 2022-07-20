package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static base.BaseClass.driver;
import static base.PageInitializer.*;
import static utils.CommonMethods.*;

public class EmployeeSearchSteps {

    @Given("user navigates to employee list page")
    public void user_navigates_to_employee_list_page() {
        pimPage.navigateToEmployeeList();
    }

    @Given("user enters existing employee's id {string}")
    public void user_enters_existing_employee_s_id(String empId) {
        sendText(empListPage.employeeSearchFieldById, empId);
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        click_waitForClickability(empListPage.searchButton);
    }

    @Then("user see employee with Id {string} is displayed")
    public void user_see_employee_information_is_displayed(String empId) {
        String searchResultIdText = "";
        for (WebElement empSearchResultId : empListPage.searchResultEmpIds) {
            searchResultIdText = empSearchResultId.getText();
            // next arrow does not stop on the last page, keeps pressing, endlessly. below, replace && with || to see if it fixes this.
            while (searchResultIdText != null && !searchResultIdText.equals(empId)) {
                click_waitForClickability(empListPage.nextArrowButton);

//                List<WebElement> paginationNumbers = driver.findElements(By.xpath("//div[@class='bottom']/ul/li"));
//                for (int i = 0; i < paginationNumbers.size(); i++) {
//                    System.out.println(paginationNumbers.get(i).getText());
//                    while (!paginationNumbers.get(i + 1).getText().equals(empListPage.lastArrowButton.getText())) {
//                        click_waitForClickability(empListPage.nextArrowButton);
//                    }
//                }


            }
            click_waitForClickability(empSearchResultId);
            break;
        }
        Assert.assertEquals("Employee is NOT found", empId, searchResultIdText);
        System.out.println("Employee is found with ID: " + empId + "\nEmployee Full Name: "
                + personalDetailsPage.empFullName.getText());
    }

    @And("user enters existing employee's {string} and {string}")
    public void user_enters_existing_employee_s_firstname_and_lastname(String empFirstName, String empLastName) throws InterruptedException {
        Thread.sleep(2000);
        sendText(empListPage.employeeSearchFieldByName, empFirstName + " " + empLastName);
        Thread.sleep(2000);
    }
}
