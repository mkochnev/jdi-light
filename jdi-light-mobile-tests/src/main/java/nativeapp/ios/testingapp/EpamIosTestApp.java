package nativeapp.ios.testingapp;

import com.epam.jdi.light.mobile.elements.common.app.Button;
import com.epam.jdi.light.mobile.elements.pageobjects.annotations.JApp;
import com.epam.jdi.light.mobile.elements.pageobjects.annotations.MobileFindBy;

@JApp(app = "epam.com.EpamIOSTestAPP")
public class EpamIosTestApp {

    @MobileFindBy(xpath = "//XCUIElementTypeCell[@name='ToggleSwitch']/XCUIElementTypeOther[2]/XCUIElementTypeOther")
    public static Button toggleSwitch;
}
