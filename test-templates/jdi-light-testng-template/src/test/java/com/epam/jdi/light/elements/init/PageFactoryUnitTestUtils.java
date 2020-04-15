package com.epam.jdi.light.elements.init;

import com.epam.jdi.light.elements.composite.WebPage;
import io.github.com.pages.*;
import org.mytests.uiobjects.example.site.pages.HomePage;
import org.mytests.uiobjects.example.site.pages.Html5Page;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Field;
import java.util.List;

import static com.epam.jdi.light.elements.init.InitActions.isJDIField;
import static com.epam.jdi.light.elements.init.InitActions.isPageObject;
import static com.epam.jdi.light.elements.init.PageFactory.STOP_INIT_CLASSES;
import static com.epam.jdi.tools.LinqUtils.filter;
import static com.epam.jdi.tools.ReflectionUtils.recursion;
import static edu.emory.mathcs.backport.java.util.Arrays.asList;
import static org.mockito.Mockito.*;
import static org.openqa.selenium.WindowType.TAB;
import static org.openqa.selenium.WindowType.WINDOW;

public class PageFactoryUnitTestUtils {
    static List<Class<?>> getListOfPages() {
        Class<?>[] sites = {HomePage.class,
                MetalAndColorsPage.class,
                ContactFormPage.class,
                HtmlElementsPage.class,
                DatesPage.class,
                UsersPage.class,
                SimpleTablePage.class,
                PerformancePage.class};
        return java.util.Arrays.asList(sites);
    }

    static Integer getNumberOfElements(Class<?> pageClass) {
        List<Field> allFieldsList = recursion(pageClass,
                t -> !STOP_INIT_CLASSES.contains(t),
                t -> asList(t.getDeclaredFields()));
        List<Field> fields = filter(allFieldsList, f -> isJDIField(f) || isPageObject(f.getType()));
        return fields.size();
    }

    static Integer getNumberOfElements(String pageObjectName) {
        List<Field> allFieldsList = recursion(instantiatePageClass(pageObjectName).getClass(),
                t -> !STOP_INIT_CLASSES.contains(t),
                t -> asList(t.getDeclaredFields()));
        List<Field> fields = filter(allFieldsList, f -> isJDIField(f) || isPageObject(f.getType()));
        return fields.size();
    }

    static Integer getNumberOfElementsForArrayOfPages(Class<?>[] pageClasses) {
        Integer totalNumberOfElements = 0;
        for (Class<?> curPage : pageClasses) {
            Integer curNumberOfElements = getNumberOfElements(curPage);
            totalNumberOfElements += curNumberOfElements;
        }
        return totalNumberOfElements;
    }

    static Integer getNumberOfElementsForArrayOfPages(Object[] pageObjects) {
        Integer totalNumberOfElements = 0;
        for (Object curPage : pageObjects) {
            Integer curNumberOfElements = getNumberOfElements(curPage.getClass());
            totalNumberOfElements += curNumberOfElements;
        }
        return totalNumberOfElements;
    }

    static List<Object> getListOfPageObjectNames() {
        Object[] names = {
                new HomePage(),
                new MetalAndColorsPage(),
                new ContactFormPage(),
                new Html5Page(),
                new DatesPage(),
                new UsersPage(),
                new SimpleTablePage(),
                new PerformancePage()
        };
        return java.util.Arrays.asList(names);
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
        return java.util.Arrays.asList(titles);
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
        return asList(Urls);
    }

    public static WebPage instantiatePageClass(String className) {
        WebPage pageInstance = new WebPage();
        try {
            Class curClass = Class.forName(className);
            pageInstance = (WebPage) curClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInstance;
    }

    static WebDriver getNewTab() {
        WebDriver mockDriver = mock(WebDriver.class);
        WebDriver.TargetLocator target = mock(WebDriver.TargetLocator.class, CALLS_REAL_METHODS);
        when(mockDriver.switchTo()).thenReturn(target);
        WebDriver newTab = target.newWindow(TAB);
        when(mockDriver.switchTo().newWindow(TAB)).thenReturn(newTab);
        return mockDriver.switchTo().newWindow(TAB);
    }

    static WebDriver getNewWindow() {
        WebDriver mockDriver = mock(WebDriver.class);
        WebDriver.TargetLocator target = mock(WebDriver.TargetLocator.class, CALLS_REAL_METHODS);
        when(mockDriver.switchTo()).thenReturn(target);
        WebDriver newTab = target.newWindow(WINDOW);
        when(mockDriver.switchTo().newWindow(WINDOW)).thenReturn(newTab);
        return mockDriver.switchTo().newWindow(WINDOW);
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

    private Object[][] listToMatrixForClasses(List<Class<?>> list) {
        Object[][] twoDimensionalArray = new Object[list.size()][2];
        list.forEach(
                element -> {
                    twoDimensionalArray[list.indexOf(element)][0] = element;
                    twoDimensionalArray[list.indexOf(element)][1] = getNumberOfElements(element);
                }
        );
        return twoDimensionalArray;
    }

    private Object[][] listToMatrixForObjects(List<Object> list) {
        Object[][] twoDimensionalArray = new Object[list.size()][2];
        list.forEach(
                element -> {
                    twoDimensionalArray[list.indexOf(element)][0] = element;
                    twoDimensionalArray[list.indexOf(element)][1] = getNumberOfElements(element.getClass().getName());
                }
        );
        return twoDimensionalArray;
    }


    @DataProvider(name = "listOfPages")
    public Object[][] listOfPages() {
        return listToMatrixForClasses(getListOfPages());
    }

    @DataProvider(name = "listOfPageObjects")
    public Object[][] listOfPageObjects() {
        return listToMatrixForObjects(getListOfPageObjectNames());
    }

}