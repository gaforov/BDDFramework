package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static utils.CommonMethods.*;

import static base.BaseClass.driver;
import static base.PageInitializer.empListPage;
import static base.PageInitializer.pimPage;
import static utils.CommonMethods.click_waitForClickability;

public class EmployeeSearchSteps {
    String empId = "27335A";
    String empFirstname = "Ellen";
    String empLastname = "Ali";

    @Given("user navigates to employee list page")
    public void user_navigates_to_employee_list_page() {
        pimPage.navigateToEmployeeList();
    }

    @Given("user enters existing employee's id")
    public void user_enters_existing_employee_s_id() {
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

    @Given("user enters existing employee's firstname and lastname")
    public void user_enters_existing_employee_s_firstname_and_lastname() {
        sendText(empListPage.employeeSearchByName, empFirstname + " " + empLastname);
    }
}
