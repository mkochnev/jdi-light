package com.zylab.sections;

import com.epam.jdi.light.elements.common.Label;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.Name;
import com.epam.jdi.light.ui.html.elements.common.*;
import com.zylab.entities.Book;

public class BookDetails extends Form<Book> {
    public Label bookDetailsTitle;
    @Name("Book Title") public TextField bookDetailsTitleInput;
    @Name("Author") public TextField bookDetailsAuthorInput;
    @Name("Publisher") public TextField bookDetailsPublisherInput;
    @Name("Year") public TextField bookDetailsYearInput;
    public Text bookDetailsTitleError, bookDetailsAuthorError, bookDetailsPublisherError, bookDetailsYearError;

    public Button bookDetailsSaveButton, bookDetailsCancelButton;
}
