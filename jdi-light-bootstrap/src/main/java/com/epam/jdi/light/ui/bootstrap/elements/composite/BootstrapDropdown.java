package com.epam.jdi.light.ui.bootstrap.elements.composite;

import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;

/**
 * To see an example of Dropdown web element in bootstrap please visit https://getbootstrap.com/docs/4.3/components/dropdowns/#examples
 */

public class BootstrapDropdown extends Section {
    @UI(".dropdown-toggle")
    private Button dropdownToggle;
    @UI(".dropdown-menu")
    private UIElement dropdownMenu;


    @JDIAction("Expand dropdown '{name}' ")
    public void expand() {
        if (!isExpanded()) {
            dropdownToggle.click();
        }
        //waitFor().expanded();
    }

    @JDIAction("Collapse dropdown '{name}' ")
    public void collapse() {
        if (isExpanded()) {
            dropdownToggle.click();
        }
        //waitFor().collapsed();
    }

    @JDIAction("Check that '{name}' is expanded")
    public boolean isExpanded() {
        return this.dropdownMenu.isDisplayed();
    }

    @JDIAction("Check that '{name}' is collapsed")
    public boolean isCollapsed() {
        return !isExpanded();
    }

    //@Override
    //public BootstrapDropdownAssert<?,?> is() {
    //    return new BootstrapDropdownAssert<?,?>().set(this);
    //}

    public Button expander() {
        return dropdownToggle;
    }

    public UIElement menu() {
        return dropdownMenu;
    }
}
