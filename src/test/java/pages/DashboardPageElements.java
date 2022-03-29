package pages;


import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPageElements {

    @FindBy(id = "welcome")
    public WebElement welcome;

    @FindBy(css = "div#branding img")
    public WebElement logo;

    @FindBy(xpath = "//div[@class='menu']/ul/li")
    public List<WebElement> mainMenu;

    public DashboardPageElements() {
        PageFactory.initElements(BaseClass.driver, this);
    }
}
