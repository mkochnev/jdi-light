package io.github.epam.html.tests.elements;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.htmlElementsPage;
import static io.github.com.pages.HtmlElementsPage.*;
import static io.github.epam.html.tests.elements.BaseValidations.baseValidation;
import static io.github.epam.html.tests.site.steps.Preconditions.shouldBeLoggedIn;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class RangeTests extends TestsInit {

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        htmlElementsPage.shouldBeOpened();
        volume.setVolume(90);
    }

    @Test
    public void getLabelTextTest() {
        assertEquals(volume.labelText(), "Volume");
    }

    @Test
    public void getValueTest() {
        assertEquals(disabledRange.volume(), 50);
    }
    @Test
    public void minTest() {
        assertEquals(volume.min(), "10");
    }
    @Test
    public void maxTest() {
        assertEquals(volume.max(), "100");
    }
    @Test
    public void stepTest() {
        assertEquals(volume.step(), "5");
    }

    @Test
    public void setVolumeTest() {
        volume.setVolume(10);
        assertEquals(volume.volume(), 10);
    }

    @Test
    public void isValidationTest() {
        volume.is().minVolume(10);
        volume.is().maxVolume(100);
        volume.is().step(5);
        volume.is().volume(greaterThanOrEqualTo(10));
        volume.is().volume(lessThanOrEqualTo(100));
        volume.is().volume(is(90));
    }

    @Test
    public void labelTest() {
        assertEquals(volume.label().getText(), "Volume");
        volume.label().is().text(containsString("lume"));
        volume.label().assertThat().text(equalToIgnoringCase("volume"));
    }

    @Test
    public void assertValidationTest() {
        volume.assertThat().volume(greaterThan(0));
        volume.assertThat().volume(lessThan(200));
        disabledRange.assertThat().volume(is(50));
    }

    @Test
    public void baseValidationTest() {
        baseValidation(colorPicker);
    }
}
