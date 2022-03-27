package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigsUtility;

import static utils.CommonMethods.*;

public class AddEmployeePageElements {
    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(id = "middleName")
    public WebElement middleName;

    @FindBy(id = "employeeId")
    public WebElement employeeId;

    @FindBy(id = "photofile")
    public WebElement uploadPhoto;

    @FindBy(id = "chkLogin")
    public WebElement createCredentialsCheckbox;

    @FindBy(id = "user_name")
    public WebElement username;

    @FindBy(id = "user_password")
    public WebElement password;

    @FindBy(id = "re_password")
    public WebElement confirmPassword;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    public AddEmployeePageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }

    public void createNewEmployeeCredentials() {
        sendText(username, ConfigsUtility.getProperty("empUsername"));
        sendText(password, ConfigsUtility.getProperty("empPassword"));
        sendText(confirmPassword, ConfigsUtility.getProperty("empPassword"));
        click_waitForClickability(saveButton);
    }
}
