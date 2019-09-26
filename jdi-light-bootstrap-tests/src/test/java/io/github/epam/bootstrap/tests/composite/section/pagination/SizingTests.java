package io.github.epam.bootstrap.tests.composite.section.pagination;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.bsPage;
import static io.github.com.pages.BootstrapPage.*;
import static io.github.epam.states.States.shouldBeLoggedIn;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class SizingTests extends TestsInit {

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        bsPage.shouldBeOpened();
    }

    String one = "1";
    String two = "2";
    String three = "3";

    @DataProvider
    public Object[][] listData() {
        return new Object[][]{
                {1, one}, {2, two}, {3, three}
        };
    }

    @Test
    public void isValidationTests() {
        paginationSizeBig.paginationItems.is().size(3);
        paginationSizeSmall.paginationItems.is().size(3);
    }

    @Test (dataProvider = "listData")
    public void linkTextTests(int index, String linkText) {
        paginationSizeBig.paginationItems.get(index)
                .is().displayed().enabled()
                .css("font-size", is("14px"))
                .hasClass("page-item")
                .text(is(containsString(linkText)));
    }
}
