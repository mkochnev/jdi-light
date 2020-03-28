package com.zylab;

import com.epam.jdi.light.elements.composite.WebPage;
import com.zylab.entities.Book;
import com.zylab.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.zylab.ZyLabSite.*;
import static com.zylab.entities.Books.*;
import static com.zylab.pages.HomePage.*;

/**
 * Created by Roman Iovlev on 26.03.2020
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public class ModifyAndFilterTests implements TestsInit {
    @BeforeMethod
    public void before() {
        WebPage.refresh();
    }

    @Test
    public void changeTitleToMeetFilterTest() {
        selectBook(goodManBook);
        filterElement.filter("No results");
        filterElement.filterResults.is().empty();
        bookInfo.bookDetails.save(new Book("No results"));
        filterElement.filterResults.has().size(1);
    }

    @Test
    public void changeTitleToRemoveFromFilterTest() {
        selectBook(goodManBook);
        filterElement.filter(goodManBook.bookTitle);
        filterElement.filterResults.has().size(1);
        bookInfo.bookDetails.save(new Book("No results"));
        filterElement.filterResults.is().empty();
    }
}
