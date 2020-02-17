package io.github.epam.sections;

import com.epam.jdi.light.elements.complex.Selector;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;
import io.github.epam.entities.User;
import org.openqa.selenium.WebElement;

public class ContactForm extends Form<User> {
	@UI("#passport") public WebElement passport;
	@UI("#name") public WebElement name;
	@UI("#last-name") public WebElement lastName;
	@UI("#position") public WebElement position;
	@UI("#passport-number") public WebElement passportNumber;
	@UI("#passport-seria") public WebElement passportSeria;

	@UI("#gender") public Selector gender;
	@UI("div[ui=combobox] input") public WebElement religion;

	@UI("#accept-conditions") public WebElement acceptConditions;
	@UI("textarea") public WebElement description;

	@XPath(".//button[@type='submit']") public WebElement submit;
}