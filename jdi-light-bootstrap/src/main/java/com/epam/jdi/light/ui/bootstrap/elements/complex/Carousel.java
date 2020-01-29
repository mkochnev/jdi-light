package com.epam.jdi.light.ui.bootstrap.elements.complex;

import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.DataList;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.interfaces.base.HasValue;
import com.epam.jdi.light.elements.interfaces.base.ICoreElement;
import com.epam.jdi.light.elements.interfaces.common.IsText;
import com.epam.jdi.light.elements.pageobjects.annotations.VisualCheck;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;

import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.common.UIUtils.asEntity;
import static com.epam.jdi.light.logger.LogLevels.DEBUG;
import static com.epam.jdi.tools.ReflectionUtils.isClass;

/**
 * To see an example of Carousel web element please visit https://getbootstrap.com/docs/4.3/components/carousel/#example
 */

//UPDATED
public class Carousel<T extends ICoreElement, D> extends DataList<T, D> {
	@Override
	public WebList list() {
		if (list == null) {
			list = new WebList() .setUIElementName(this::elementTitle)
				.setName(getName());
			list.noValidation();
			list.setLocator(".carousel-item").setParent(this);
		}
		return list;
	}
	@UI(".carousel-item") @VisualCheck UIElement activeSlide;

	@Override
	public String selected() {
		return elementTitle(activeSlide);
	}
	public T activeSlide() {
		return toT(activeSlide);
	}
	public D activeSlideData() {
		return asEntity(activeSlide(), dataType);
	}
	protected String nextLocator = ".carousel-control-next";
	protected String prevLocator = ".carousel-control-prev";
	protected String indicatorsLocator = "li[data-slide-to]";

	@JDIAction("Open next slide '{name}'")
	public void next() {
		linked(nextLocator, "next").click();
	}
	@JDIAction("Open previous slide '{name}'")
	public void previous() {
		linked(prevLocator, "previous").click();
	}
	private WebList indicators() {
		return linkedList(indicatorsLocator, "indicators");
	}
	public void openSlide(int index) {
		if (index < 1)
			throw exception("Can't select element with index '%s'. Index should be 1 or more", index);
		indicators().select(index-1);
	}
	@Override @JDIAction(level = DEBUG)
	public void show() {
		activeSlide.show();
	}
	@Override
	public String getText() {
		T slide = activeSlide();
		if (isClass(dataType, IsText.class))
			return ((IsText)slide).getText();
		if (isClass(dataType, HasValue.class))
			return ((HasValue)slide).getValue();
		return slide.core().getText();
	}
	@Override
	public String getValue() {
		return getText();
	}
}
