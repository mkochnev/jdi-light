package com.zylab.entities;

public class Books {
    public static Book goodManBook = new Book().set(b -> {
        b.bookTitle = "A Good Man Is Hard to Find and Other Stories";
        b.author = "Vladimir Nabokov";
        b.publisher = "Hay House";
        b.year = "2007";
    });
    public static Book infiniteBook = new Book().set(b -> {
        b.bookTitle = "infinite jest";
        b.author = "James Baldwin";
        b.publisher = "O\'Reilly Media";
        b.year = "2013";
    });
}
