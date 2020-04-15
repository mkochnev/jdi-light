package com.epam.jdi.light.elements.init;

import com.epam.jdi.tools.map.MapArray;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static com.epam.jdi.light.elements.init.PageFactory.initElements;
import static com.epam.jdi.light.elements.init.PageFactoryUnitTestUtils.*;
import static com.epam.jdi.light.elements.init.entities.collection.EntitiesCollection.ELEMENTS;
import static com.epam.jdi.light.settings.JDISettings.DRIVER;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

public class PageFactoryUnitTest {

    @Test(dataProvider = "listOfPages", dataProviderClass = PageFactoryUnitTestUtils.class)
    public void testInitElementsUsingSiteInfo(Class<?> pageClass, Integer numberOfElements) {
        SiteInfo info = new SiteInfo(DRIVER.name, instantiatePageClass(pageClass.getName()));
        ELEMENTS = new MapArray<>();
        initElements(info);
        assertEquals(ELEMENTS.keys().size(), numberOfElements.intValue());
    }

    @Test(dataProvider = "listOfPageObjects", dataProviderClass = PageFactoryUnitTestUtils.class)
    public void testInitElementsUsingPageObjects(Object pageObject, Integer numberOfElements) {
        ELEMENTS = new MapArray<>();
        initElements(pageObject);
        assertEquals(ELEMENTS.keys().size(), numberOfElements.intValue());
    }

    @Test(dataProvider = "listOfPages", dataProviderClass = PageFactoryUnitTestUtils.class)
    public void testInitElementsUsingPageClass(Class<?> pageClass, Integer numberOfElements) {
        ELEMENTS = new MapArray<>();
        initElements(pageClass);
        assertEquals(ELEMENTS.keys().size(), numberOfElements.intValue());
    }

    @Test(dataProvider = "listOfPages", dataProviderClass = PageFactoryUnitTestUtils.class)
    public void testInitElementsUsingPageClassAndWebDriver(Class<?> pageClass, Integer numberOfElements) {
        ELEMENTS = new MapArray<>();
        WebDriver mockDriver = mock(WebDriver.class);
        initElements(mockDriver, pageClass);
        assertEquals(ELEMENTS.keys().size(), numberOfElements.intValue());
    }

    @Test(dataProvider = "listOfPageObjects", dataProviderClass = PageFactoryUnitTestUtils.class)
    public void testInitElementsUsingPageObjectAndWebDriver(Object pageObject, Integer numberOfElements) {
        ELEMENTS = new MapArray<>();
        WebDriver mockDriver = mock(WebDriver.class);
        initElements(mockDriver, pageObject);
        assertEquals(ELEMENTS.keys().size(), numberOfElements.intValue());
    }

    @Test
    public void testInitElementsUsingArrayOfPageClasses() {
        ELEMENTS = new MapArray<>();
        WebDriver mockDriver = mock(WebDriver.class);
        Class<?>[] listOfPageClasses = getListOfPages().toArray(new Class[getListOfPages().size()]);
        Integer expectedNumberOfElements = getNumberOfElementsForArrayOfPages(listOfPageClasses);
        initElements(mockDriver, listOfPageClasses);
        assertEquals(ELEMENTS.keys().size(), expectedNumberOfElements.intValue());
    }

    @Test
    public void testInitElementsUsingArrayOfPageObjects() {
        ELEMENTS = new MapArray<>();
        WebDriver mockDriver = mock(WebDriver.class);
        Object[] listOfPages = getListOfPageObjectNames().toArray();
        Integer expectedNumberOfElements = getNumberOfElementsForArrayOfPages(listOfPages);
        initElements(mockDriver, listOfPages);
        assertEquals(ELEMENTS.keys().size(), expectedNumberOfElements.intValue());
    }

    @Test
    public void testInitElementsInSeveralTabs() {
        ELEMENTS = new MapArray<>();
        for (Class<?> curPageClass : getListOfPages()) {
            WebDriver curTab = getNewTab();
            initElements(curTab, curPageClass);
        }
        assertEquals(ELEMENTS.keys().size(),
                getNumberOfElementsForArrayOfPages(getListOfPages()
                        .toArray(new Class[getListOfPages().size()]))
                        .intValue());
    }

    @Test
    public void testInitElementsInSeveralWindows() {
        ELEMENTS = new MapArray<>();
        for (Class<?> curPageClass : getListOfPages()) {
            WebDriver curWindow = getNewWindow();
            initElements(curWindow, curPageClass);
        }
        assertEquals(ELEMENTS.keys().size(),
                getNumberOfElementsForArrayOfPages(getListOfPages()
                        .toArray(new Class[getListOfPages().size()]))
                        .intValue());

    }

}