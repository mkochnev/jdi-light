package com.zylab;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.light.driver.WebDriverUtils.*;
import static com.epam.jdi.light.elements.init.PageFactory.*;
import static com.epam.jdi.light.settings.JDISettings.*;
import static com.epam.jdi.light.settings.WebSettings.*;
import static com.zylab.ZyLabSite.*;

public interface TestsInit {
    @BeforeSuite(alwaysRun = true)
    default void setUp() {
        killAllSeleniumDrivers();
        initSite(ZyLabSite.class);
        ELEMENT.beforeSearch = el -> {};
        homePage.open();
        logger.toLog("Run Tests");
    }

    @AfterSuite(alwaysRun = true)
    default void tearDown() {
        killAllSeleniumDrivers();
    }
}
