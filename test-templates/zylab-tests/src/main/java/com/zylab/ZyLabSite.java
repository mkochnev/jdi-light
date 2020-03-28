package com.zylab;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.zylab.pages.HomePage;

@JSite("http://localhost:4200/")
public class ZyLabSite {
	@Url("/#/books") public static HomePage homePage;

}