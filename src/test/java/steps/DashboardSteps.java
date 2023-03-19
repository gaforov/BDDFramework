package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static base.PageInitializer.dashboardPage;

/**
 * Then user should see dashboard menu displayed
 * | Admin | PIM | Leave | Time | Recruitment | Performance | Dashboard | Directory |
 * <p>
 * This above step with one row and many horizontal cells will work as this:
 * List<List<String>> expectedAdminMenu = menu.asLists();
 */
public class DashboardSteps {
    @Then("user should see dashboard menu displayed")
    public void user_should_see_dashboard_menu_displayed(DataTable menu) {
        List<String> expectedAdminMenu = menu.asList(); // doesn't work
        List<String> actualAdminMenu = new ArrayList<>();
//        int menuSize = dashboardPage.mainMenu.size();
//        for (int i = 0; i < menuSize; i++) {
//            String menuText = dashboardPage.mainMenu.get(i).getText();
//            actualAdminMenu.add(menuText);
//        }
        for (WebElement element : dashboardPage.mainMenu) {  // <-- enhanced loop is shorter than for loop
            actualAdminMenu.add(element.getText());
        }
        System.out.println(expectedAdminMenu.toString());
        System.out.println(actualAdminMenu);
        Assert.assertEquals("Admin menu doesn't match", expectedAdminMenu, actualAdminMenu);
        /**
         * As of Cucumber 7.0 and later asList(), asLists(), and asMaps() have changed:
         * Replace DataTable.asList() with -> DataTable.values()
         * Replace DataTable.asLists() with -> DataTable.cells()
         * Replace DataTable.asMaps() with -> DataTable.entries()
         */
    }

    @Then("user should see dashboard menu displayed2")
    public void user_should_see_dashboard_menu_displayed2(DataTable dataTable) {
        List<List<String>> adminMenu = dataTable.asLists();
        List<String> expectedAdminMenu = new ArrayList<>();
        ListIterator<List<String>> iterator = adminMenu.listIterator();
        while (iterator.hasNext()) {
            List<String> next = iterator.next();
            expectedAdminMenu.addAll(next);
        }
        List<String> actualAdminMenu = new ArrayList<>();
        for (WebElement element : dashboardPage.mainMenu) {  // <-- enhanced loop is shorter than for loop
            actualAdminMenu.add(element.getText());
        }
        System.out.println(expectedAdminMenu);
        System.out.println(actualAdminMenu);
        Assert.assertEquals("Admin menu doesn't match", expectedAdminMenu, actualAdminMenu);
    }
}
