package io.github.epam.html.tests.elements.composite;

import io.github.com.pages.*;
import io.github.epam.TestsInit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.github.epam.html.tests.elements.composite.PageFactoryTestUtils.*;

import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.elements.init.PageFactory.initElements;
import static com.epam.jdi.light.elements.init.PageFactory.initSite;

public class PageFactoryTest implements TestsInit {
    @BeforeTest
    public void testSetUp() {
        initSite(HomePage.class, getDriver().getCurrentUrl());
    }

    @Test(dataProvider = "listOfPages", dataProviderClass = PageFactoryTestUtils.class)
    public void initElementsInTheWebPage(Class<?> page, String nix) {
        initialiseAndAssertTitle(
                matchUrlToPage(page),
                getDriver(),
                page);
    }

    @Test(dataProvider = "listOfPageObjects", dataProviderClass = PageFactoryTestUtils.class)
    public void initElementsInTheSite(Object pageObject, String nix) {
        initialiseAndAssertTitle(
                matchUrlToPageObject(pageObject),
                getDriver(),
                pageObject
        );
    }

    @Test
    public void initElementsInTheListOfWebPages() {
        initElements(getListOfPages().toArray());
        for (Class<?> page : getListOfPages()) {
            iterateOverInitialisedPagesAndAssertTitles(
                    matchUrlToPage(page),
                    getDriver(),
                    page);
        }
    }

    @Test
    public void initElementInTheListOfPageObjects() {
        initElements(getListOfPageObjectNames().toArray());
        for (Object pageObject : getListOfPageObjectNames()) {
            iterateOverInitialisedPagesAndAssertTitles(
                    matchUrlToPageObject(pageObject),
                    getDriver(),
                    pageObject
            );
        }
    }

    @Test
    public void initElementsInSeveralTabs() {
        getListOfPages().forEach(page ->
                initialiseAndAssertTitle(
                        matchUrlToPage(page),
                        getNewTab(),
                        page));
    }

    @Test
    public void initElementsInSeveralWindows() {
        getListOfPages().forEach(page ->
                initialiseAndAssertTitle(
                        matchUrlToPage(page),
                        getNewWindow(),
                        page));
    }

    @Test
    public void initElementsUsingPageObjectsInSeveralTabs() {
        getListOfPageObjectNames().forEach(pageObject ->
                initialiseAndAssertTitle(
                        matchUrlToPageObject(pageObject),
                        getNewTab(),
                        pageObject)
        );
    }

    @Test
    public void initElementsUsingPageObjectsInSeveralWindows() {
        getListOfPageObjectNames().forEach(pageObject ->
                initialiseAndAssertTitle(
                        matchUrlToPageObject(pageObject),
                        getNewWindow(),
                        pageObject)
        );
    }

    @AfterTest
    public void tearDown() {
        killAllSeleniumDrivers();
    }
}
