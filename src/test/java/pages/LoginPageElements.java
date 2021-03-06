package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.CommonMethods.*;

public class LoginPageElements {

    @FindBy(id = "txtUsername")
    public WebElement username;

    @FindBy(name = "txtPassword")
    public WebElement password;

    @FindBy(id = "btnLogin")
    public WebElement loginButton;

    @FindBy(css = "div#divLogo img")
    public WebElement hrmLogo;

    @FindBy(id = "spanMessage")
    public WebElement errorMessage;

    @FindBy(id = "divLoginImage")
    public WebElement loginPanelSection;

    public LoginPageElements() {

        PageFactory.initElements(BaseClass.driver, this);  // this OR LoginWithPageFactory.class
    }

    /*
     if you want to use encapsulation, you can change access modifier of elements to private then call them from
     another public method as below example or simply auto-generate getters/setters for each private method.
     */
    public void setUsername(String userId) {
        sendText(username, userId);
    }

    public WebElement getUsername() {
        return username;
    }

    public void loginAndClick(String uid, String pswd) {
        sendText(username, uid);
        sendText(password, pswd);
        click_waitForClickability(loginButton);  // extra click removed. Do not add/repeat .click() at the end, it is part of method function.
    }


}
