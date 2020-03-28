package com.zylab;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.zylab.entities.Books.*;
import static com.zylab.pages.HomePage.*;

/**
 * Created by Roman Iovlev on 26.03.2020
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public class FilterTests implements TestsInit {
    @BeforeMethod
    public void before() {
        filterElement.clear();
    }

    @Test
    public void filterExactLongSentenceTest() {
        filterAndCheckCount(goodManBook.bookTitle, 1);
    }
    @Test
    public void filterFewResultsTest() {
        filterAndCheckCount("lo", 2);
    }
    @Test
    public void filterCaseSensitiveTest() {
        filterAndCheckCount("n", 4);
        filterElement.clear();
        filterAndCheckCount("N", 1);
    }
    @Test
    public void filterByApostropheTest() {
        filterAndCheckCount("\'", 2);
    }
    @Test
    public void filterNoResultsTest() {
        filterAndCheckCount("no results", 0);
    }
    @Test
    public void filterResultsOneByOneLetterTest() {
        filterAndCheckCount("i", 6);
        filterAndCheckCount("n", 3);
        filterAndCheckCount("f", 1);
        filterAndCheckCount("r", 0);
    }
    @Test
    public void filterAndClearTest() {
        filterAndCheckCount("lo", 2);
        filterElement.clear();
        filterElement.filterResults.has().size(7);
    }

}
