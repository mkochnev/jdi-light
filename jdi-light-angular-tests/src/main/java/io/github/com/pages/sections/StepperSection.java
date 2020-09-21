package io.github.com.pages.sections;

import com.epam.jdi.light.angular.elements.complex.Stepper;
import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;

public class StepperSection extends Section {

    @Css("#optional-steps-stepper")
    public static Stepper optionalStepper;

    @Css("#optional-steps-stepper")
    public static Stepper optionalStepperValidation = new Stepper(true);

    @UI("#linear-stepper")
    public static Stepper lineStepper;

    @UI("#linear-stepper")
    public static Stepper lineStepperValidation = new Stepper(true);

    @UI("#linear-mode-button")
    public static Button linerModeButton;

    @UI("#optional-step-button")
    public static Button optionalModeButton;
}
