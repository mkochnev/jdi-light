package com.zylab.entities;

import com.epam.jdi.tools.DataClass;

public class Book extends DataClass<Book> {
    public String bookTitle, author, publisher, year;
    public Book() {}
    public Book(String name) { bookTitle = name; }
}
