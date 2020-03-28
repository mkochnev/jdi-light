package com.zylab;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.settings.WebSettings;
import com.zylab.entities.Book;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.light.driver.WebDriverFactory.*;
import static com.zylab.entities.Books.*;
import static com.zylab.pages.HomePage.*;

/**
 * Created by Roman Iovlev on 26.03.2020
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public class RemoveBookTests implements TestsInit {
    @BeforeMethod
    public void before() {
        WebPage.refresh();
    }

    @Test
    public void removeOtherBookTest() {
        selectBook(goodManBook);
        removeBook(infiniteBook);

        filterElement.filterResults.has().size(6);
        bookInfo.bookDetails.is().shown();
        bookInfo.bookTitle.has().text(goodManBook.bookTitle);
    }

    @Test
    public void removeSelectedBookTest() {
        selectBook(goodManBook);
        removeBook(goodManBook);

        filterElement.filterResults.has().size(6);
        bookInfo.bookDetails.is().hidden();
        bookInfo.bookIsNotSelected.is().shown();
    }

    @Test
    public void removeAllBooksTest() {
        for (String bookTitle : filterElement.filterResults.values())
            removeBook(bookTitle);

        filterElement.filterResults.is().empty();
        bookInfo.bookDetails.is().hidden();
        bookInfo.bookIsNotSelected.is().shown();
    }
}
