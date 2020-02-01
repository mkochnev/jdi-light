package io.github.epam.bootstrap.tests.common;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.light.elements.common.Alerts.validateAlert;
import static io.github.com.StaticSite.bsPage;
import static io.github.com.pages.BootstrapPage.*;
import static io.github.epam.bootstrap.tests.BaseValidationsUtils.baseValidation;
import static io.github.epam.states.States.shouldBeLoggedIn;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class CollapseTests extends TestsInit {
    final String text = "Collapse text shown";

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        bsPage.shouldBeOpened();
        expandLink.show();
    }

    @Test
    public void expandCollapseTest() {
        hiddenText.is().hidden();
        assertFalse(hiddenText.isDisplayed());
        expandLink.click();
        hiddenText.is().shown();
        assertTrue(hiddenText.isDisplayed());
        expandLink.click();
        hiddenText.is().hidden();
        assertFalse(hiddenText.isDisplayed());
    }
}
