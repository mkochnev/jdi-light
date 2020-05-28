package io.github.epam.pages;

import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;
import com.epam.jdi.light.mobile.elements.common.Text;

public class RPLaunchesPage extends BasePage {

    @XPath("//*[@class='noItemMessage__message--2ydYP']")
    public Text noResultsFoundLabel;

}
