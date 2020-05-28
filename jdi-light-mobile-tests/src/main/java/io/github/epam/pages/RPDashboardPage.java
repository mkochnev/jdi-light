package io.github.epam.pages;

import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;
import com.epam.jdi.light.mobile.elements.common.Button;
import com.epam.jdi.light.mobile.elements.common.Icon;
import com.epam.jdi.light.mobile.elements.common.Text;

public class RPDashboardPage extends BasePage {

    @XPath("//*[contains(text(), 'You have no dashboards')]")
    public Text noDashboardsText;
    @XPath("//*[contains(text(), 'Add your first dashboard to analyse statistics')]")
    public Text addDashboardText;
    @XPath("//*[@class='emptyDashboards__empty-dashboard-content--RZbNx']//button")
    public Button addDashboardButton;
    @XPath("//*[@class='mobileHeader__hamburger--95j4J']")
    public Button hamburgerMenuButton;
    @XPath("//*[contains(text(), 'Launches')]")
    public Button launchesButton;
    @XPath("//*[@class='mobileHeader__rp-logo--QWmol']")
    public Icon siteLogo;
    @XPath("//*[contains(text(), 'Cancel')]")
    public Button modalCancelButton;

}
