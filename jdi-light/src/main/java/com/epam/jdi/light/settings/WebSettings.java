package com.epam.jdi.light.settings;

import com.epam.jdi.light.common.*;
import com.epam.jdi.light.driver.WebDriverFactory;
import com.epam.jdi.light.driver.get.DriverTypes;
import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.interfaces.base.IBaseElement;
import com.epam.jdi.light.logger.ILogger;
import com.epam.jdi.tools.PropertyReader;
import com.epam.jdi.tools.Safe;
import com.epam.jdi.tools.StringUtils;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.func.JFunc;
import com.epam.jdi.tools.func.JFunc1;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static com.epam.jdi.light.common.ElementArea.CENTER;
import static com.epam.jdi.light.common.ElementArea.SMART_CLICK;
import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.common.SetTextTypes.SET_TEXT;
import static com.epam.jdi.light.common.TextTypes.SMART_TEXT;
import static com.epam.jdi.light.driver.get.DriverData.CAPABILITIES_FOR_MOBILE;
import static com.epam.jdi.light.driver.get.DriverData.COMMON_CAPABILITIES;
import static com.epam.jdi.light.driver.get.RemoteDriver.*;
import static com.epam.jdi.light.driver.sauce.SauceSettings.sauceCapabilities;
import static com.epam.jdi.light.elements.init.PageFactory.preInit;
import static com.epam.jdi.light.elements.init.UIFactory.$;
import static com.epam.jdi.light.logger.AllureLoggerHelper.AttachmentStrategy;
import static com.epam.jdi.light.logger.JDILogger.instance;
import static com.epam.jdi.light.settings.TimeoutSettings.PAGE_TIMEOUT;
import static com.epam.jdi.light.settings.TimeoutSettings.TIMEOUT;
import static com.epam.jdi.tools.EnumUtils.getAllEnumValues;
import static com.epam.jdi.tools.LinqUtils.first;
import static com.epam.jdi.tools.PathUtils.mergePath;
import static com.epam.jdi.tools.PropertyReader.fillAction;
import static com.epam.jdi.tools.PropertyReader.getProperty;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openqa.selenium.PageLoadStrategy.*;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public class WebSettings {
    public static ILogger logger = instance("JDI");
    public static String DOMAIN;
    public static String getDomain() {
        if (DOMAIN != null)
            return DOMAIN;
        preInit();
        return "No Domain Found. Use test.properties or WebSettings.DOMAIN";
    }
    public static void setDomain(String domain) {
        DOMAIN = domain;
    }
    public static JFunc1<WebElement, Boolean> ANY_ELEMENT = Objects::nonNull;
    public static JFunc1<WebElement, Boolean> VISIBLE_ELEMENT = WebElement::isDisplayed;
    public static JFunc1<WebElement, Boolean> ENABLED_ELEMENT = el ->
        el != null && el.isDisplayed() && el.isEnabled();
    public static JFunc1<WebElement, Boolean> ELEMENT_IN_VIEW = el ->
        el != null && !el.isDisplayed() && $(el).isClickable();
    public static JFunc1<WebElement, Boolean> SEARCH_RULES = VISIBLE_ELEMENT;
    public static JAction1<UIElement> BEFORE_SEARCH = b -> {};
    public static void setSearchRule(JFunc1<WebElement, Boolean> rule) {
        SEARCH_RULES = rule;
    }
    public static void noValidation() {
        SEARCH_RULES = ANY_ELEMENT;
        CLICK_TYPE = CENTER;
    }
    public static void onlyVisible() {
        SEARCH_RULES = VISIBLE_ELEMENT;
    }
    public static void visibleEnabled() {
        SEARCH_RULES = ENABLED_ELEMENT;
    }
    public static void inView() {
        SEARCH_RULES = ELEMENT_IN_VIEW;
        BEFORE_SEARCH = UIElement::show;
    }

    public static ElementArea CLICK_TYPE = SMART_CLICK;
    public static TextTypes TEXT_TYPE = SMART_TEXT;
    public static SetTextTypes SET_TEXT_TYPE = SET_TEXT;
    public static VisualCheckAction VISUAL_ACTION_STRATEGY = VisualCheckAction.NONE;
    public static VisualCheckPage VISUAL_PAGE_STRATEGY = VisualCheckPage.NONE;
    public static boolean STRICT_SEARCH = true;
    public static boolean hasDomain() {
        preInit();
        return DOMAIN != null && DOMAIN.contains("://");
    }
    public static String TEST_GROUP = "";
    // TODO multi properties example
    public static String TEST_PROPERTIES_PATH = "test.properties";
    public static Safe<String> TEST_NAME = new Safe<>((String) null);
    public static String useDriver(JFunc<AppiumDriver> driver) {
        return WebDriverFactory.useDriver(driver);
    }
    public static String useDriver(String driverName) {
        return WebDriverFactory.useDriver(driverName);
    }
    public static String useDriver(DriverTypes driverType) {
        return WebDriverFactory.useDriver(driverType);
    }

    public static List<String> SMART_SEARCH_LOCATORS = new ArrayList<>();
    public static JFunc1<String, String> SMART_SEARCH_NAME = StringUtils::splitHyphen;
    public static JFunc1<IBaseElement, WebElement> SMART_SEARCH = el -> {
        String locatorName = SMART_SEARCH_NAME.execute(el.getName());
        return el.base().timer().getResult(() -> {
            for (String template : SMART_SEARCH_LOCATORS) {
                UIElement ui = (template.equals("#%s")
                    ? $(String.format(template, locatorName))
                    : $(String.format(template, locatorName), el.base().parent))
                        .setup(e -> e.setName(el.getName()).noWait());
                try {
                    return ui.getWebElement();
                } catch (Exception ignore) { }
            }
            throw exception("Element '%s' has no locator and Smart Search failed. Please add locator to element or be sure that element can be found using Smart Search", el.getName());
        });
    };

    public static synchronized void init() {
        getProperties(TEST_PROPERTIES_PATH);
        fillAction(p -> TIMEOUT = new Timeout(parseInt(p)), "timeout.wait.element");
        fillAction(p -> PAGE_TIMEOUT = new Timeout(parseInt(p)), "timeout.wait.page");
//
        loadCapabilities("appium.capabilities.path",
                p -> p.forEach((key, value) -> CAPABILITIES_FOR_MOBILE.put(key.toString(), value.toString())));

//        INIT_THREAD_ID = Thread.currentThread().getId();
//        if (SMART_SEARCH_LOCATORS.size() == 0)
//            SMART_SEARCH_LOCATORS.add("#%s"/*, "[ui=%s]", "[qa=%s]", "[name=%s]"*/);
    }


    private static void loadCapabilities(String property, JAction1<Properties> setCapabilities) {
        String path = "";
        try {
            path = System.getProperty(property, getProperty(property));
        } catch (Exception ignore) {
        }
        if (isNotEmpty(path)) {
            setCapabilities.execute(getProperties(path));
        }
    }

    private static void setSearchStrategy(String p) {
        p = p.toLowerCase();
        if (p.equals("soft"))
            p = "any, multiple";
        if (p.equals("strict"))
            p = "visible, single";
        if (p.split(",").length == 2) {
            List<String> params = asList(p.split(","));
            if (params.contains("visible") || params.contains("displayed"))
                onlyVisible();
            if (params.contains("any") || params.contains("all"))
                noValidation();
            if (params.contains("enabled"))
                visibleEnabled();
            if (params.contains("inview"))
                inView();
            if (params.contains("single"))
                STRICT_SEARCH = true;
            if (params.contains("multiple"))
                STRICT_SEARCH = false;
        }
    }

    private static PageLoadStrategy getPageLoadStrategy(String strategy) {
        switch (strategy.toLowerCase()) {
            case "normal": return NORMAL;
            case "none": return NONE;
            case "eager": return EAGER;
        }
        return NORMAL;
    }

    private static Properties getProperties(String path) {
        File propertyFile = new File(path);
        Properties properties;
        if (propertyFile.exists()) {
            properties = getCiProperties(path, propertyFile);
        } else {
            Properties pTest = PropertyReader.getProperties(mergePath(path));
            Properties pTarget = PropertyReader.getProperties(mergePath("/../../target/classes/" + path));
            if (pTarget.size() > 0)
                return pTarget;
            String propertiesPath = pTest.size() > 0
                    ? path
                    : mergePath("/../../target/classes/" + path);
            properties = PropertyReader.getProperties(propertiesPath);
        }
        return properties;
    }

    private static Properties getCiProperties(String path, File propertyFile){
        Properties properties = new Properties();
        try {
            System.out.println("Property file found: " + propertyFile.getAbsolutePath());
            properties.load(new FileInputStream(propertyFile));
        } catch (IOException ex) {
            throw exception("Couldn't load properties for CI Server" + path);
        }
        return properties;
    }
    private static AttachmentStrategy getAttachmentStrategy(String strategy) {
        return strategy.toLowerCase().equals("off")
            ? AttachmentStrategy.OFF
            : AttachmentStrategy.ON_FAIL;
    }
}
