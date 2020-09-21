package io.github.epam.angular.tests.elements.complex;

import io.github.epam.TestsInit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.com.pages.sections.StepperSection.*;

public class StepperTests extends TestsInit {

    private static final String CLASS_FOR_TEST = "mat-stepper-horizontal";
    private static final String NAME_STEP = "Fill out your name";
    private static final String NAME_INPUT = "Name";
    private static final String ADDRESS_STEP = "Fill out your address";
    private static final String ADDRESS_INPUT = "Address";
    private static final String ADDRESS = "Stroiteley 11 a";
    private static final String NAME = "jdi light";

    @BeforeMethod
    public void before() {
        optionalStepper.show();
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        if ("Disable optional steps".equals(optionalModeButton.text())) {
            optionalModeButton.click();
        }
        if ("Disable linear mode".equals(linerModeButton.text())) {
            linerModeButton.click();
        }
        if (!NAME_STEP.equals(lineStepper.getActiveStep().find("//div[contains(@class, 'mat-step-label')]").getText())) {
            lineStepper.getStepTabByName(NAME_STEP).click();
        }
        if (!NAME_STEP.equals(optionalStepper.getActiveStep().find("//div[contains(@class, 'mat-step-label')]").getText())) {
            optionalStepper.getStepTabByName(NAME_STEP).click();
        }
    }

    @Test
    public void basicStepperTest() {
        lineStepper.is().hasClass(CLASS_FOR_TEST);
        lineStepper.is().displayed();
    }

    @Test
    public void fillStepperTest() {
        lineStepper.fillTabInputByName(NAME_INPUT, NAME)
                .nextStep()
                .fillTabInputByName(ADDRESS_INPUT, ADDRESS)
                .nextStep();
        lineStepper.is().isElementWithTextPresentOnStepAssert(lineStepper.getPLabels(), "You are now done.");
        lineStepper.pressTabFormButton("Reset");
    }

    @Test
    public void checkStepsValuesTest() throws InterruptedException {
        lineStepper.fillTabInputByName(NAME_INPUT, NAME)
                .nextStep()
                .fillTabInputByName(ADDRESS_INPUT, ADDRESS)
                .nextStep();
        lineStepper.previousStep().getStepperTabInputByName(ADDRESS_INPUT).is().text(ADDRESS);
        lineStepper.waitFor(5);
        lineStepper.previousStep().getStepperTabInputByName(NAME_INPUT).assertThat().text(NAME);
        lineStepper.getStepTabByName("Done").click();
        lineStepper.pressTabFormButton("Reset");
    }

    @Test
    public void linerModeEnableTest() {
        lineStepper.assertThat().isLinerModeDisable();
        linerModeButton.click();
        lineStepper.assertThat().isLinerModeEnable();
        linerModeButton.click();
    }

    @Test
    public void fieldAndResetTest() {
        lineStepper.fillTabInputByName(NAME_INPUT, NAME)
                .nextStep()
                .fillTabInputByName(ADDRESS_INPUT, ADDRESS)
                .nextStep()
                .pressTabFormButton("Reset");
        lineStepper.getStepperTabInputByName(NAME_INPUT).assertThat().text("");
    }

    @Test
    public void activeStepTest() {
        lineStepper.getStepTabByName(NAME_STEP).click();
        lineStepper.assertThat().isStepActive(NAME_STEP);
        lineStepper.getStepTabByName(ADDRESS_STEP).click();
        lineStepper.assertThat().isStepActive(ADDRESS_STEP);
        lineStepper.getStepTabByName("Done").click();
        lineStepper.assertThat().isStepActive("Done");
        lineStepper.pressTabFormButton("Reset");
    }

    @Test
    public void optionalStepTest() {
        optionalStepper.assertThat().isMandatoryStep(ADDRESS_STEP);
        optionalModeButton.click();
        optionalStepper.assertThat().isNotMandatoryStep(ADDRESS_STEP);
        optionalModeButton.click();
    }

    @Test
    public void optionalStepSkipTest() {
        optionalModeButton.click();
        optionalStepper.fillTabInputByName(NAME_INPUT, NAME)
                .nextStep()
                .waitForNextStepAppear(3)
                .nextStep();
        optionalStepper.is().isElementWithTextPresentOnStepAssert(optionalStepper.getPLabels(), "You are now done.");
        optionalStepper.pressTabFormButton("Reset");
        optionalModeButton.click();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void lineStepperWithValidationExcTest() {
        lineStepperValidation.nextStep();
        linerModeButton.click();
        lineStepperValidation.nextStep();
    }

    @Test
    public void lineStepperWithValidationTest() {
        linerModeButton.click();
        lineStepperValidation.fillTabInputByName(NAME_INPUT, NAME)
                .nextStep()
                .fillTabInputByName(ADDRESS_INPUT, ADDRESS)
                .nextStep();
        lineStepperValidation.is().isElementWithTextPresentOnStepAssert(lineStepper.getPLabels(), "You are now done.");
        lineStepperValidation.pressTabFormButton("Reset");
        linerModeButton.click();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void optionalStepperWithValidationExcTest() {
        optionalStepperValidation.nextStep();
    }

    @Test
    public void optionalStepperWithValidationTest() {
        optionalStepperValidation.fillTabInputByName(NAME_INPUT, NAME)
                .nextStep()
                .fillTabInputByName(ADDRESS_INPUT, ADDRESS)
                .nextStep();
        optionalStepperValidation.is().isElementWithTextPresentOnStepAssert(optionalStepperValidation.getPLabels(), "You are now done.");
        optionalStepperValidation.pressTabFormButton("Reset");
    }

    @Test
    public void optionalStepperWithValidationEnableOptionTest() {
        optionalModeButton.click();
        optionalStepperValidation.fillTabInputByName(NAME_INPUT, NAME).nextStep();
        optionalStepperValidation.nextStep();
        optionalStepperValidation.is().isElementWithTextPresentOnStepAssert(optionalStepperValidation.getPLabels(), "You are now done.");
        optionalStepperValidation.pressTabFormButton("Reset");
        optionalModeButton.click();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class, priority = 10)
    public void optionalStepperWithValidationDisableOptionTest() {
        optionalStepperValidation.fillTabInputByName(NAME_INPUT, NAME)
                .nextStep()
                .nextStep();
    }

    public void qq(int a){
        System.out.println(a);
    }
    public void qq(byte a){
        System.out.println(a+a);
    }

    public static void main(String[] args) {
        StepperTests tests = new StepperTests();
        tests.qq(3);
    }
}
