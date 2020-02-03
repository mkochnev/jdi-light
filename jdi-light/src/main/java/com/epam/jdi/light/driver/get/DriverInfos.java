package com.epam.jdi.light.driver.get;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.epam.jdi.light.driver.get.DriverData.*;
import static com.epam.jdi.light.driver.get.DriverTypes.*;

/**
 * Created by Roman Iovlev on 26.09.2019
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public class DriverInfos {
    public static DriverInfo MOBILE_INFO = new DriverInfo()
            .set(d -> {
                        d.type = APPIUM;
                        d.initCapabilities = new DesiredCapabilities();
                        d.capabilities = c -> getCapabilities(c, cap -> MOBILE_OPTIONS.execute((DesiredCapabilities) cap));
                        d.getDriver = c -> new AppiumDriver((DesiredCapabilities) c);
                    }
            );

}
