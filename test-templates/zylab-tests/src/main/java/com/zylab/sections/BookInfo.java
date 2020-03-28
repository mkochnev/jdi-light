package com.zylab.sections;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Text;

public class BookInfo extends Section {
	public Text bookIsNotSelected;
	@UI(".mat-card-title") public Text bookTitle;
	@UI(".mat-card") public BookDetails bookDetails;
}