package com.epam.jdi.light.asserts.core;

import com.epam.jdi.light.asserts.generic.UISelectAssert;
import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.complex.DataList;
import com.epam.jdi.light.elements.interfaces.base.ICoreElement;
import com.epam.jdi.tools.LinqUtils;
import com.epam.jdi.tools.func.JFunc1;
import org.hamcrest.*;

import java.util.List;

import static com.epam.jdi.light.asserts.core.SoftAssert.*;
import static com.epam.jdi.light.common.Exceptions.*;
import static com.epam.jdi.tools.LinqUtils.*;
import static com.epam.jdi.tools.PrintUtils.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by Roman Iovlev on 26.09.2019
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public class DataListAssert<T extends ICoreElement, D>
        extends UISelectAssert<DataListAssert<T, D>, DataList<T, D>> {
    public List<D> data() {
        element.refresh();
        return element.asData();
    }

    /**
     * Check that all elements meet condition
     * @param condition to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that each of the '{name}' {1}")
    public DataListAssert<T, D> each(JFunc1<D, Boolean> condition, String nameCondition) {
        jdiAssert(LinqUtils.all(data(), condition::execute), Matchers.is(true));
        return this;
    }
    public DataListAssert<T, D> each(JFunc1<D, Boolean> condition) {
        return each(condition, "meet condition");
    }

    /**
     * Check that at least one element meets condition
     * @param condition to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that any of '{name}' {1}")
    public DataListAssert<T, D> any(JFunc1<D, Boolean> condition, String nameCondition) {
        jdiAssert(LinqUtils.any(data(), condition::execute), Matchers.is(true));
        return this;
    }
    public DataListAssert<T, D> any(JFunc1<D, Boolean> condition) {
        return any(condition, "meet condition");
    }

    /**
     * Check that only one of elements meets condition
     * @param condition to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that only one of the '{name}' {1}}")
    public DataListAssert<T, D> onlyOne(JFunc1<D, Boolean> condition, String nameCondition) {
        jdiAssert(single(data(), condition::execute), Matchers.is(notNullValue()));
        return this;
    }
    public DataListAssert<T, D> onlyOne(JFunc1<D, Boolean> condition) {
        return onlyOne(condition, "meet condition");
    }

    /**
     * Check that none of elements meets condition
     * @param condition to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that none of the '{name}' {1}")
    public DataListAssert<T, D> noOne(JFunc1<D, Boolean> condition, String nameCondition) {
        jdiAssert(first(data(), condition::execute), Matchers.is(nullValue()));
        return this;
    }
    public DataListAssert<T, D> noOne(JFunc1<D, Boolean> condition) {
        return noOne(condition, "meet condition");
    }

    /**
     * Check that element has the item
     * @param item to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' has {0}")
    public DataListAssert<T, D> value(D item) {
        return assertData(hasItem(item));
    }

    /**
     * Match passed value with elements texts
     * @param condition to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' text {0}")
    public DataListAssert<T, D> value(Matcher<String> condition) {
        jdiAssert(print(data(), Object::toString), condition);
        return this;
    }

    /**
     * Check that the element has the text
     * @param text to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' text {0}")
    public DataListAssert<T, D> value(String text) {
        jdiAssert(select(data(), Object::toString), hasItem(text));
        return this;
    }

    /**
     * Check that all elements are displayed
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' elements [{0}] are displayed")
    public DataListAssert<T, D> displayed(String... names) {
        for (String name : names)
            jdiAssert(element.get(name).isDisplayed() ? name + "displayed" : "hidden", Matchers.is(name + "displayed"));
        return this;
    }

    /**
     * Check that at least one element is displayed
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' has at least one displayed element")
    public DataListAssert<T, D> displayed() {
        jdiAssert(element.isDisplayed() ? "displayed" : "hidden", Matchers.is("displayed"));
        return this;
    }

    /**
     * Check that all elements are hidden
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' is hidden")
    public DataListAssert<T, D> hidden() {
        jdiAssert(map(element, this::isDisplayed), everyItem(Matchers.is(false)));
        return this;
    }
    private boolean isDisplayed(T element) {
        try {
            return element.core().isDisplayed();
        } catch (Exception ex) { throw exception(ex, "Is element Displayed failed. DataList element not a Section. Only Sections can be assert on isDisplayed"); }
    }

    /**
     * Check that the list is empty
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' is empty")
    public DataListAssert<T, D> empty() {
        jdiAssert(element.isEmpty() ? "list is empty" : "list is not empty", Matchers.is("list is empty"));
        return this;
    }

    /**
     * Check that the list isn't empty
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' is not empty")
    public DataListAssert<T, D> notEmpty() {
        jdiAssert(element.isEmpty() ? "list is empty" : "list is not empty", Matchers.is("list is not empty"));
        return this;
    }

    /**
     * Match passed value with element size
     * @param condition to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' size {0}")
    public DataListAssert<T, D> size(Matcher<Integer> condition) {
        return sizeAssert(condition);
    }
    /**
     * Check that the list size is given size
     * @param size to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' size {0}")
    public DataListAssert<T, D> size(int size) {
        return sizeAssert(equalTo(size));
    }
    private DataListAssert<T, D> sizeAssert(Matcher<Integer> condition) {
        jdiAssert(element.size(), condition);
        return this;
    }

    /**
     * Match passed value with elements data
     * @param condition to compare
     * @return DataListAssert
     */
    @JDIAction("Assert that '{name}' data {0}")
    public DataListAssert<T, D> and(Matcher<? super List<D>> condition) {
        return assertData(condition);
    }
    public DataListAssert<T, D> assertData(Matcher<? super List<D>> condition) {
        MatcherAssert.assertThat(data(), condition);
        return this;
    }
}
