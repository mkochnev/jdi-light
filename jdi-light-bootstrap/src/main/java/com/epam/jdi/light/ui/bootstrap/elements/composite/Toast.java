package com.epam.jdi.light.ui.bootstrap.elements.composite;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Text;

public class Toast extends Section {
    @UI(".toast-header .mr-auto") Text title;
    @UI(".toast-header .close") Button closeButton;
    @UI(".toast-body") Text text;

    public String getTitle() {
        return title.getText();
    }
    public String getMessage() {
        return text.getText();
    }
    public void close() {
        closeButton.click();
    }
}
