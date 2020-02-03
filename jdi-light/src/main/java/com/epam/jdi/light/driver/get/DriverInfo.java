package com.epam.jdi.light.driver.get;

import com.epam.jdi.tools.DataClass;
import com.epam.jdi.tools.func.JFunc1;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.driver.WebDriverFactory.isMobile;
import static com.epam.jdi.light.driver.get.DownloadDriverManager.wdm;
import static java.lang.Integer.parseInt;

/**
 * Created by Roman Iovlev on 26.09.2019
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public class DriverInfo extends DataClass<DriverInfo> {
    public DriverTypes type;
    public MutableCapabilities initCapabilities;
    public JFunc1<MutableCapabilities, Capabilities> capabilities;
    public JFunc1<Object, AppiumDriver> getDriver;

    public AppiumDriver getDriver() {
        if(isMobile()) {
            return setupMobile();
        } else {
            return null;
        }
    }

    private AppiumDriver setupMobile() {
        try {
            return new AppiumDriver(new URL(MobileDriver.DRIVER_MOBILE_URL), capabilities.execute(initCapabilities));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getBelowVersion() {
        String currentMajor = wdm.getDownloadedVersion().split("\\.")[0];
        List<String> allVersions = wdm.getVersions();
        for (int i = allVersions.size()-1; i>=0; i--)
             if (parseInt(currentMajor) > parseInt(allVersions.get(i).split("\\.")[0]))
                 return allVersions.get(i);
         throw exception("Can't find version below current(" + wdm.getDownloadedVersion()+")");
    }
}
