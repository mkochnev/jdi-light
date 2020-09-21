package com.epam.jdi.light.angular.asserts;

import com.epam.jdi.light.angular.elements.complex.Stepper;
import com.epam.jdi.light.asserts.generic.ITextAssert;
import com.epam.jdi.light.asserts.generic.UIAssert;
import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.complex.WebList;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static com.epam.jdi.light.asserts.core.SoftAssert.jdiAssert;

public class StepperAssert extends UIAssert<StepperAssert, Stepper> implements ITextAssert<StepperAssert> {

    @JDIAction("Assert that '{name}' text {0}")
    @Override
    public StepperAssert text(Matcher<String> condition) {
        jdiAssert(element().getValue(), condition);
        return this;
    }

    @JDIAction("Assert that '{name} is mandatory")
    public StepperAssert isMandatoryStep(String tabName) {
        jdiAssert(element.isMandatoryStep(tabName), Matchers.is(true));
        return this;
    }

    @JDIAction("Assert that '{name} is not mandatory")
    public StepperAssert isNotMandatoryStep(String tabName) {
        jdiAssert(element.isMandatoryStep(tabName), Matchers.is(false));
        return this;
    }

    @JDIAction("Assert that '{name} liner mode is enable")
    public StepperAssert isLinerModeEnable() {
        jdiAssert(element.isLinerMode(), Matchers.is(true));
        return this;
    }

    @JDIAction("Assert that '{name} is liner mode is disable")
    public StepperAssert isLinerModeDisable() {
        jdiAssert(element.isLinerMode(), Matchers.is(false));
        return this;
    }

    @JDIAction("Assert that '{name} the step active")
    public StepperAssert isStepActive(String tabName) {
        jdiAssert(element.isStepActive(tabName), Matchers.is(true));
        return this;
    }

    @JDIAction("Assert that '{name} the element on step")
    public StepperAssert isElementWithTextPresentOnStepAssert(WebList elements, String text) {
        jdiAssert(element.isElementWithTextPresentOnStep(elements, text), Matchers.is(true));
        return this;
    }
}
