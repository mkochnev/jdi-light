package io.github.epam.angular.tests.elements.complex.select;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static io.github.com.pages.sections.SelectSection.formMatSelect;
import static io.github.com.pages.sections.SelectSection.formMatSelectConfirmation;
import static org.hamcrest.Matchers.hasItems;

public class FormMatSelectTests extends TestsSelectBase {
    @BeforeMethod(alwaysRun = true)
    public void before() {
        formMatSelect.show();
    }

    @Test
    public void checkLabelValue() {
        formMatSelect.label().has().value("Favorite food");
    }

    @Test
    public void checkOptionCanBeSelectedByIndexAndConfirmMessageWillAppear() {
        formMatSelect.select(3);
        formMatSelect.is().selected(TACOS);
        formMatSelectConfirmation.assertThat().text("Selected food: tacos-2");
    }

    @Test
    public void checkListDisabledOptions() {
        formMatSelect.has().listDisabled(Collections.emptyList());
    }

    @Test
    public void checkListEnabledOptions() {
        formMatSelect.has().listEnabled(Arrays.asList(STEAK, PIZZA, TACOS));
    }

    @Test
    public void checkAvailableOptions() {
        formMatSelect.assertThat().values(hasItems(STEAK, TACOS, PIZZA));
    }
}
