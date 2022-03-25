package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.CommonMethods.*;

public class PIMPageElements {
    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimMenu;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployee;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement employeeList;


    public PIMPageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }

    public void navigateToAddEmployee() {
        click_waitForClickability(pimMenu);
        //jsClick(PIM); // if regular(HTML) click doesn't work, use JS click.
        click_waitForClickability(addEmployee);
    }

    public void navigateToEmployeeList() {
        click_waitForClickability(pimMenu);
        click_waitForClickability(employeeList);
    }
}
