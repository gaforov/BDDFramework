package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static utils.CommonMethods.*;

public class EmployeeListPageElements {
    @FindBy(id = "empsearch_id")
    public WebElement employeeSearchById;

    @FindBy(id = "empsearch_employee_name_empName")
    public WebElement employeeSearchByName;

    @FindBy(id = "searchBtn")
    public WebElement searchButton;


    public EmployeeListPageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }

    public void searchEmployeeById(String empId) {
        sendText(employeeSearchById, empId);
    }

    public void searchEmployeeByName(String empName) {
        sendText(employeeSearchByName, empName);
    }

}
