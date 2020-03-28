package com.zylab.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.zylab.entities.Book;
import com.zylab.sections.BookInfo;
import com.zylab.sections.FilterElement;

public class HomePage extends WebPage {
	@UI(".list") public static FilterElement filterElement;
	@UI("app-book-details") public static BookInfo bookInfo;

	public static void filterAndCheckCount(String text, int count) {
		filterElement.filterAndCheckCount(text, count);
	}
	public static void selectBook(Book book) {
		filterElement.filterResults.select(book.bookTitle);
	}
	public static void removeBook(Book book) {
		removeBook(book.bookTitle);
	}
	public static void removeBook(String bookTitle) {
		filterElement.filterResults.remove(bookTitle);
	}
}