package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static utils.CommonMethods.*;

public class EmployeeListPageElements {

    @FindBy(id = "empsearch_id")
    public WebElement employeeSearchFieldById;

    @FindBy(id = "empsearch_employee_name_empName")
    public WebElement employeeSearchFieldByName;

    @FindBy(id = "searchBtn")
    public WebElement searchButton;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr/td[2]")
    public List<WebElement> searchResultEmpIds;

    @FindBy(linkText = "Next")
    public WebElement nextArrowButton;

    @FindBy(linkText = "Last")
    public WebElement lastArrowButton;


    public EmployeeListPageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }


    public void searchEmployeeById(String empId) {
        sendText(employeeSearchFieldById, empId);
    }

    public void searchEmployeeByName(String empName) {
        sendText(employeeSearchFieldByName, empName);
    }

}
