package io.github.epam.bootstrap.tests.complex;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.bsPage;
import static io.github.com.pages.BootstrapPage.numbers;
import static io.github.epam.states.States.shouldBeLoggedIn;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ChecklistTests implements TestsInit {

    //@BeforeMethod
    //public void before() {
    //    shouldBeLoggedIn();
    //    bsPage.shouldBeOpened();
    //    numbers.check(text);
    //}
    //String text = "Hot option";
//
    //@Test
    //public void getValueTest() {
    //    assertEquals(numbers.getValue(), text);
    //}
//
    //@Test
    //public void checkTest() {
    //    numbers.check("Cold");
    //    assertEquals(numbers.checked(), asList("Cold"));
    //}
    //@Test
    //public void checkTwoTest() {
    //    numbers.check("Cold", "Hot option");
    //    assertEquals(numbers.checked(), asList("Hot option", "Cold"));
    //}
    //@Test
    //public void uncheckTest() {
    //    numbers.check("Rainy day", "Sunny");
    //    numbers.uncheck("Rainy day");
    //    numbers.is().checked(hasSize(3));
    //    numbers.is().checked(hasItems("Hot option", "Cold", "Sunny"));
    //}
    //@Test
    //public void uncheckTwoTest() {
    //    numbers.check("Rainy day", "Sunny");
    //    numbers.uncheck("Rainy day", "Sunny");
    //    numbers.is().checked(hasSize(2));
    //    numbers.is().checked(hasItems("Hot option", "Cold"));
    //}
    //@Test
    //public void selectTest() {
    //    numbers.select("Cold");
    //    assertEquals(numbers.checked(), asList("Hot option", "Cold"));
    //}
    //@Test
    //public void selectTwoTest() {
    //    numbers.select("Cold", "Hot option");
    //    assertEquals(numbers.checked(), asList("Cold"));
    //}
    //@Test
    //public void uncheckEnumTest() {
    //    numbers.check("Rainy day", "Sunny");
    //    numbers.uncheck("Rainy day");
    //    numbers.is().checked(hasSize(3));
    //    numbers.is().checked(hasItems("Hot option", "Cold", "Sunny"));
    //}
    //@Test
    //public void uncheckEnumTwoTest() {
    //    numbers.check("Rainy day", "Sunny");
    //    numbers.uncheck("Rainy day", "Sunny");
    //    numbers.is().checked(hasSize(2));
    //    numbers.is().checked(hasItems("Hot option", "Cold"));
    //}
    //@Test
    //public void checkNumTest() {
    //    numbers.check(4);
    //    assertEquals(numbers.checked(), asList("Sunny"));
    //}
    //@Test
    //public void checkNumTwoTest() {
    //    numbers.check(1, 4);
    //    assertEquals(numbers.checked(), asList("Hot option", "Sunny"));
    //}
    //@Test
    //public void uncheckNumTest() {
    //    numbers.checkAll();
    //    numbers.uncheck(1);
    //    numbers.is().checked(hasSize(3));
    //    numbers.is().checked(hasItems("Cold", "Rainy day", "Sunny"));
    //}
    //@Test
    //public void uncheckNumTwoTest() {
    //    numbers.checkAll();
    //    numbers.uncheck(1, 4);
    //    numbers.is().checked(hasSize(2));
    //    numbers.is().checked(hasItems("Cold", "Rainy day"));
    //}
    //@Test
    //public void selectNumTest() {
    //    numbers.select(4);
    //    assertEquals(numbers.checked(), asList("Hot option", "Sunny"));
    //}
    //@Test
    //public void selectNumTwoTest() {
    //    numbers.select(1, 4);
    //    assertEquals(numbers.checked(), asList("Sunny"));
    //}
//
    //@Test
    //public void selectedTest() {
    //    assertEquals(numbers.selected(), text);
    //}
//
    //@Test
    //public void disabledTest() {
    //    try {
    //        numbers.select("Disabled");
    //        fail("Click on disabled element should throw exception");
    //    } catch (Exception ex) {
    //        assertThat(ex.getMessage(), containsString("Can't perform click. Element is disabled"));
    //    }
    //    assertEquals(numbers.selected(), text);
    //}
//
    //@Test
    //public void isValidationTest() {
    //    numbers.is().displayed().selected("Hot option");
    //    numbers.assertThat().values(hasItem("Sunny"))
    //        .disabled(hasItem("Disabled"))
    //        .enabled(not(hasItem("Disabled")))
    //        .enabled(hasItems("Cold", "Sunny"));
    //}
//
    //@Test
    //public void assertValidationTest() {
    //    numbers.assertThat().values(containsInAnyOrder(
    //    "Hot option", "Cold", "Rainy day", "Sunny", "Disabled"));
    //}
//
    //@Test
    //public void uncheckAllTest() {
    //    numbers.check("Rainy day", "Sunny");
    //    numbers.uncheckAll();
    //    numbers.is().checked(hasSize(0));
    //}
    //@Test
    //public void checkAllTest() {
    //    numbers.checkAll();
    //    numbers.is().checked(hasSize(4));
    //    numbers.is().checked(hasItems("Hot option", "Cold", "Rainy day", "Sunny"));
    //}
}
