package io.github.epam.mobile.tests;

import io.github.epam.RPSite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.mobile.elements.init.PageFactory.initElements;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static io.github.epam.RPSite.loginPage;

public class RPTestsInit {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        initElements(RPSite.class);
        loginPage.open();
        logger.toLog("Run Tests");
    }

    @AfterSuite(alwaysRun = true)
    public static void tearDown() {
        killAllSeleniumDrivers();
    }
}
