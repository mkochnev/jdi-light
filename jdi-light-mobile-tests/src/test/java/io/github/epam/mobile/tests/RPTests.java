package io.github.epam.mobile.tests;

import com.epam.jdi.light.driver.WebDriverFactory;
import org.testng.annotations.Test;

import static io.github.epam.RPSite.dashboardPage;
import static io.github.epam.RPSite.launchesPage;
import static io.github.epam.RPSite.loginPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RPTests extends RPTestsInit {

    // TODO: Move this login test to BeforeSuite
    @Test(priority = 0)
    public void loginTest() throws InterruptedException {
        loginPage.loginField.sendKeys("test-user");
        loginPage.passwordField.sendKeys("Fqvq1s0S");
        // TODO: replace Thread.sleep with a WebDriverWait
        Thread.sleep(3000);
        loginPage.loginButton.click();
    }

    @Test(priority = 1)
    public void labelTest() {
        assertTrue(dashboardPage.noDashboardsText.getValue().contains("no dashboards"));
        assertTrue(dashboardPage.addDashboardText.getValue().contains("first dashboard"));
    }

    @Test(priority = 2)
    public void addDashboardModalTest() throws InterruptedException {
        dashboardPage.addDashboardButton.click();
        // TODO: replace w/Wait
        Thread.sleep(3000);
        dashboardPage.modalCancelButton.click();
        // TODO: replace w/Wait
        Thread.sleep(3000);
        assertTrue(dashboardPage.siteLogo.isDisplayed());
    }

    @Test(priority = 3)
    public void menuTest() {
        dashboardPage.hamburgerMenuButton.click();
        dashboardPage.launchesButton.click();
        assertEquals(launchesPage.noResultsFoundLabel.getValue(), "No results found");
    }


}
