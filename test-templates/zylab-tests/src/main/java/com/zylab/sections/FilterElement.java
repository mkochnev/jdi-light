package com.zylab.sections;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.NoCache;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.TextField;
import com.zylab.custom.FilterResults;
import com.zylab.entities.Book;
import org.openqa.selenium.Keys;

import static java.lang.String.*;

public class FilterElement extends Section {
	TextField filterInput;
	@UI("mat-list-item") @NoCache
	public FilterResults filterResults;

	public void filter(String value) {
		filterInput.sendKeys(value);
	}
	public void filter(Book book) {
		filter(book.bookTitle);
	}
	public void clear() {
		filterInput.input("t"+ Keys.BACK_SPACE);
	}

	public void filterAndCheckCount(String text, int count) {
		filter(text);
		filterResults.has().size(count);
		if (count > 0)
			filterResults.assertThat().each(b -> b.bookTitle.contains(text), format("contains text '%s'", text));
	}
}