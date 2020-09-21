package com.epam.jdi.light.angular.elements.complex;

import com.epam.jdi.light.angular.asserts.StepperAssert;
import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.base.UIBaseElement;
import com.epam.jdi.light.elements.common.Label;
import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.interfaces.base.HasLabel;
import com.epam.jdi.light.elements.interfaces.base.HasPlaceholder;
import com.epam.jdi.light.elements.interfaces.common.IsButton;
import com.epam.jdi.light.elements.interfaces.common.IsInput;
import com.epam.jdi.light.elements.interfaces.common.IsText;

public class Stepper extends UIBaseElement<StepperAssert> implements IsButton, HasLabel, HasPlaceholder, IsInput, IsText {

    private String stepperTabsLocator = "//*[@role='tab']";
    private String stepperTabByNameLocator = "//*[contains(text(),'%s')]/ancestor::mat-step-header";
    private String stepperButtonByNameLocator = "//*[contains(text(),'%s')]/ancestor::button";
    private String stepperInputByNameLocator = "//*[contains(text(),'%s')]/ancestor::div/input";
    private String stepperLabelByNameLocator = "//*[contains(text(),'%s')]/..label";
    private boolean validation = false;

    public Stepper() {
    }

    public Stepper(boolean validation) {
        this.validation = validation;
    }

    public WebList getStepperTabs() {
        return this.finds(stepperTabsLocator);
    }

    public UIElement getStepTabByName(String tabName) {
        return this.find(String.format(stepperTabByNameLocator, tabName));
    }

    public Stepper pressTabFormButton(String buttonName) {
        if (isStepValid()) {
            getStepperTabButtonByName(buttonName).click();
            return this;
        }
        throw new UnsupportedOperationException("Please fill mandatory fields or switch off validation");

    }

    public UIElement getStepperTabButtonByName(String button) {
        return this.find(String.format(stepperButtonByNameLocator, button)).core();
    }

    public UIElement getStepperTabInputByName(String input) {
        return this.find(String.format(stepperInputByNameLocator, input));
    }

    public Label getStepperTabLabelByName(String label) {
        return this.find(String.format(stepperLabelByNameLocator, label)).core().label();
    }

    public WebList getStepperTabButtons() {
        return this.finds("//button");
    }

    public WebList getStepperTabInputs() {
        return this.finds("//input");
    }

    public WebList getStepperTabLabels() {
        return this.finds("//label");
    }

    public WebList getPLabels() {
        return this.finds("//p");
    }

    public UIElement getPlLabelByValue(String labelName) {
        return this.find(String.format("//*[contains(text(),'%s')]//p..", labelName));
    }

    public Stepper fillTabInputByName(String input, String text) {
        getStepperTabInputByName(input).sendKeys(text);
        return this;
    }

    public Stepper nextStep() {
        if (isStepValid()) {
            this.find("//button[@matsteppernext]").click();
            return this;
        }
        throw new UnsupportedOperationException("Please fill mandatory fields or switch off validation");
    }

    public Stepper previousStep() {
        this.find("//button[@matstepperprevious]").click();
        return this;
    }

    public UIElement getActiveStep() {
        for (UIElement elem : getStepperTabs()) {
            if (elem.getAttribute("aria-selected").equals("true")) {
                return elem;
            }
        }
        throw new UnsupportedOperationException("is not find out active steps");
    }

    public boolean isEmptyMandatoryInputOnActiveStep() {
        for (UIElement elem : this.getStepperTabInputs()) {
            if (elem.getAttribute("aria-required").equals("true")
                    && elem.text().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean isStepValid() {
        if (!validation) {
            return true;
        }
        boolean mandatoryStepCondition = isMandatoryStep(getActiveStep().find("//div[contains(@class, 'mat-step-label')]").getText().replaceAll("[\\n\\r\\t].+", ""));
        boolean linerModeCondition;

        if (!this.attr("ng-reflect-linear").isEmpty()) {
            linerModeCondition = isLinerMode();
        } else {
            linerModeCondition = true;
        }
        if (linerModeCondition && mandatoryStepCondition) {
            return !isEmptyMandatoryInputOnActiveStep();
        }
        return true;
    }

    public Stepper waitForNextStepAppear(int sec) {
        return (Stepper) this.waitSec(sec);
    }

    @JDIAction("Is '{name} liner mode enable")
    public Boolean isLinerMode() {
        return "true".equals(this.attr("ng-reflect-linear"));
    }

    @JDIAction("Is '{name}' mandatory field")
    public Boolean isMandatoryInput(String input) {
        return "true".equals(getStepperTabInputByName(input).attr("aria-required"));
    }

    @JDIAction("Is '{name}' tab '{0}' mandatory step")
    public Boolean isMandatoryStep(String tabName) {
        return "false".equals(getStepTabByName(tabName).getAttribute("ng-reflect-optional"));
    }

    @JDIAction("Is '{name} disabled")
    @Override
    public boolean isDisabled() {
        return core().hasAttribute("disabled");
    }

    @Override
    public StepperAssert is() {
        return new StepperAssert().set(this);
    }

    public boolean isStepActive(String tabName) {
        return "true".equals(getStepTabByName(tabName).getAttribute("ng-reflect-active"))
                || "true".equals(getStepTabByName(tabName).getAttribute("ng-reflect-selected"));
    }

    public boolean isElementWithTextPresentOnStep(WebList elements, String text) {
        for (UIElement elem : elements) {
            if (elem.text().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public void setStepperTabsLocator(String stepperTabsLocator) {
        this.stepperTabsLocator = stepperTabsLocator;
    }

    public void setStepperTabByNameLocator(String stepperTabByNameLocator) {
        this.stepperTabByNameLocator = stepperTabByNameLocator;
    }

    public void setStepperButtonByNameLocator(String stepperButtonByNameLocator) {
        this.stepperButtonByNameLocator = stepperButtonByNameLocator;
    }

    public void setStepperInputByNameLocator(String stepperInputByNameLocator) {
        this.stepperInputByNameLocator = stepperInputByNameLocator;
    }

    public void setStepperLabelByNameLocator(String stepperLabelByNameLocator) {
        this.stepperLabelByNameLocator = stepperLabelByNameLocator;
    }
}
