package com.zylab.custom;

import com.epam.jdi.light.elements.complex.DataList;
import com.zylab.entities.Book;

import static com.epam.jdi.light.elements.init.UIFactory.*;

public class FilterResults extends DataList<Result, Book> {
    public FilterResults() {
        initClass = Result.class;
        dataType = Book.class;
    }
    @Override public Result get(String value) {
        return toT($("//mat-list-item[.//*[@data-test-book-title][text()=\""+value+"\"]]"));
    }
    public void remove(String value) {
        $("//mat-list-item[.//*[@data-test-book-title][text()=\""+value+"\"]]//*[@data-test-book-delete-icon]")
            .setName("Remove Book '"+value+"'")
            .click();
    }
}
