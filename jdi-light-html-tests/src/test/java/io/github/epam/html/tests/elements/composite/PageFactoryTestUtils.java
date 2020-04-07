package io.github.epam.html.tests.elements.composite;

import com.epam.jdi.light.elements.composite.WebPage;
import edu.emory.mathcs.backport.java.util.Arrays;
import io.github.com.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.DataProvider;

import java.util.List;

import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static com.epam.jdi.light.elements.init.PageFactory.initElements;
import static io.github.com.StaticSite.*;
import static io.github.com.StaticSite.performancePage;
import static io.github.epam.html.tests.site.steps.States.shouldBeLoggedIn;
import static io.github.epam.html.tests.site.steps.States.shouldBeLoggedOut;
import static org.testng.Assert.assertEquals;

public class PageFactoryTestUtils {
    static List<Class> getListOfPages() {
        Class[] sites = {HomePage.class,
                MetalAndColorsPage.class,
                ContactFormPage.class,
                HtmlElementsPage.class,
                DatesPage.class,
                UsersPage.class,
                SimpleTablePage.class,
                PerformancePage.class};
        return Arrays.asList(sites);
    }

    static List<Object> getListOfPageObjectNames() {
        Object[] names = {
                homePage,
                metalAndColorsPage,
                contactFormPage,
                html5Page,
                datesPage,
                usersPage,
                tablePage,
                performancePage
        };
        return Arrays.asList(names);
    }

    static List<String> getListOfTitles() {
        String[] titles = {"Home Page",
                "Metal and Colors",
                "Contact Form",
                "HTML 5",
                "Dates",
                "User Table",
                "Simple Table",
                "Performance page"};
        return Arrays.asList(titles);
    }

    static List<String> getListOfUrls() {
        String[] Urls = {
                "https://jdi-testing.github.io/jdi-light/index.html",
                "https://jdi-testing.github.io/jdi-light/metals-colors.html",
                "https://jdi-testing.github.io/jdi-light/contacts.html",
                "https://jdi-testing.github.io/jdi-light/html5.html",
                "https://jdi-testing.github.io/jdi-light/dates.html",
                "https://jdi-testing.github.io/jdi-light/user-table.html",
                "https://jdi-testing.github.io/jdi-light/simple-table.html",
                "https://jdi-testing.github.io/jdi-light/performance.html"
        };
        return Arrays.asList(Urls);
    }

    static WebDriver getNewTab() {
        return getDriver().switchTo().newWindow(WindowType.TAB);
    }

    static WebDriver getNewWindow() {
        WebDriver window = getDriver().switchTo().newWindow(WindowType.WINDOW);
        window.manage().window().maximize();
        return window;
    }

    static int getPageIndex(Class<?> page) {
        return getListOfPages().indexOf(page);
    }

    static int getPageObjectIndex(Object pageObject) {
        return getListOfPageObjectNames().indexOf(pageObject);
    }

    static String matchUrlToPage(Class<?> page) {
        return getListOfUrls().get(getPageIndex(page));
    }

    static String matchUrlToPageObject(Object pageObject) {
        return getListOfUrls().get(getPageObjectIndex(pageObject));
    }

    static String matchTitleToPage(Class<?> page) {
        return getListOfTitles().get(getPageIndex(page));
    }

    static String matchTitleToPageObject(Object pageObject) {
        return getListOfTitles().get(getPageObjectIndex(pageObject));
    }

    static void getToUrl(String url, WebDriver window) {
        window.get(HomePage.getUrl());
        shouldBeLoggedIn();
        window.get(url);
    }

    static void initialiseAndAssertTitle(String url, WebDriver window, Class<?> page) {
        getToUrl(url, window);
        WebPage curInstance = (WebPage) initElements(window, page);
        assertEquals(
                WebPage.getTitle(),
                matchTitleToPage(page));
        shouldBeLoggedOut();
    }

    static void initialiseAndAssertTitle(String url, WebDriver window, Object pageObject) {
        getToUrl(url, window);
        initElements(window, pageObject);
        WebPage curInstance = (WebPage) pageObject;
        assertEquals(
                WebPage.getTitle(),
                matchTitleToPageObject(pageObject));
        shouldBeLoggedOut();
    }

    static void iterateOverInitialisedPagesAndAssertTitles(String url, WebDriver window, Class<?> page) {
        getToUrl(url, window);
        assertEquals(window.getTitle(), matchTitleToPage(page));
        shouldBeLoggedOut();
    }

    static void iterateOverInitialisedPagesAndAssertTitles(String url, WebDriver window, Object pageObject) {
        getToUrl(url, window);
        assertEquals(window.getTitle(), matchTitleToPageObject(pageObject));
        shouldBeLoggedOut();
    }

    private Object[][] listToMatrix(List<?> list) {
        Object[][] twoDimensionalArray = new Object[list.size()][2];
        list.forEach(
                element ->
                        twoDimensionalArray[list.indexOf(element)][0] = element
        );
        return twoDimensionalArray;
    }

    @DataProvider(name = "listOfPages")
    public Object[][] listOfPages() {
        return listToMatrix(getListOfPages());
    }

    @DataProvider(name = "listOfPageObjects")
    public Object[][] listOfPageObjects() {
        return listToMatrix(getListOfPageObjectNames());
    }
}
