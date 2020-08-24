package com.epam.jdi.light.elements.complex;

import com.epam.jdi.light.asserts.generic.HasAssert;
import com.epam.jdi.light.asserts.generic.UISelectAssert;
import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.common.JDILocator;
import com.epam.jdi.light.common.TextTypes;
import com.epam.jdi.light.elements.base.JDIBase;
import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.interfaces.base.HasUIList;
import com.epam.jdi.light.elements.interfaces.base.SetValue;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.MarkupLocator;
import com.epam.jdi.tools.CacheValue;
import com.epam.jdi.tools.LinqUtils;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.func.JFunc;
import com.epam.jdi.tools.func.JFunc1;
import com.epam.jdi.tools.map.MapArray;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.common.TextTypes.*;
import static com.epam.jdi.light.driver.WebDriverByUtils.shortBy;
import static com.epam.jdi.light.elements.init.UIFactory.*;
import static com.epam.jdi.light.elements.init.entities.collection.EntitiesCollection.getByType;
import static com.epam.jdi.light.logger.LogLevels.DEBUG;
import static com.epam.jdi.light.settings.JDISettings.ELEMENT;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.tools.EnumUtils.getEnumValue;
import static com.epam.jdi.tools.EnumUtils.getEnumValues;
import static com.epam.jdi.tools.LinqUtils.*;
import static com.epam.jdi.tools.LinqUtils.map;
import static com.epam.jdi.tools.LinqUtils.toList;
import static com.epam.jdi.tools.PrintUtils.print;
import static com.epam.jdi.tools.StringUtils.namesEqual;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.max;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public class WebList extends JDIBase implements IList<UIElement>, SetValue, ISelector, HasUIList,
        HasAssert<UISelectAssert<UISelectAssert<?,?>, WebList>> {
    protected int startIndex = ELEMENT.startIndex;
    public WebList indexFromZero() {
        return startIndex(0);
    }
    public WebList startIndex(int index) {
        startIndex = index;
        return this;
    }
    @Override
    public WebList list() { return this; }
    public UIElement core() {
        return new UIElement(base());
    }
    public WebList setup(JAction1<JDIBase> setup) {
        return setup(WebList.class, setup);
    }
    public List<WebElement> webElements() {
        if (isUseCache() && webElements.hasValue())
            return webElements.get();
        return elements(1).webElements();
    }
    @Override
    public WebList setCore(JDIBase base) {
        super.setCore(base);
        return this;
    }
    public WebList() {
        webElements.useCache(true);
        textType = SMART_LIST;
    }
    public WebList(@MarkupLocator By locator) { this(); setLocator(locator);}
    public WebList(List<WebElement> elements) {
        this(); setWebElements(elements);
    }
    public static WebList newList(List<UIElement> elements) {
        return new WebList(LinqUtils.map(elements, JDIBase::get));
    }
    public WebList(MapArray<String, UIElement> map) {
        this.map.set(map);
    }
    public WebList(List<String> header, List<UIElement> elements) {
        this(new MapArray<>(header, elements));
    }
    public WebList(JDIBase base) {
        super(base);
    }
    public WebList(JDIBase base, String locator, String name, Object parent) {
        super(base);
        setLocator(locator);
        setName(name);
        setParent(parent);
    }
    public WebList noValidation() {
        super.noValidation();
        return this;
    }
    @Override
    public WebList setName(String name) {
        super.setName(name);
        return this;
    }
    protected CacheValue<MapArray<String, UIElement>> map = new CacheValue<>();

    protected String nameFromIndex(int i) {
        return nameFromValue(i+1+"");
    }
    protected String nameFromValue(String value) {
        return format("%s[%s]", getName(), value);
    }

    @JDIAction(level = DEBUG)
    public WebList elements(int minAmount) {
        return new WebList(uiElements(minAmount));
    }
    protected List<WebElement> uiElements(int minAmount) {
        if (isUseCache() && webElements.hasValue() && isActual() && webElements.get().size() >= minAmount)
            return webElements.get();
        if (locator.isTemplate())
            throw exception("You call method that can't be used with template locator. " +
                    "Please correct %s locator to get List<WebElement> in order to use this method", shortBy(getLocator(), this));
        return getListElements(minAmount);
    }
    @Override
    public Iterator<UIElement> iterator() {
        return LinqUtils.map(uiElements(0), el -> $(el)).iterator();
    }
    protected List<WebElement> getListElements(int minAmount) {
        return getList(minAmount);
    }
    // TODO remove
    // protected MultiMap<String, UIElement> getListElements(int minAmount) {
    //     MultiMap<String, UIElement> result = new MultiMap<String, UIElement>().ignoreKeyCase();
    //     List<WebElement> we = getList(minAmount);
    //     int length = we.size();
    //     for (int i=0; i < length; i++) {
    //         int j = i;
    //         UIElement el = initElement(we.get(j), () -> getList(minAmount).get(j), j);
    //         result.add(getElementName(j, el), el);
    //     }
    //     return result;
    // }
    @Override
    public UIElement first() {
        return get(startIndex);
    }
    @Override
    public UIElement last() {
        return get(size() - startIndex + 1);
    }
    protected String getElementName(int i, UIElement element) {
        return nameIndex ? nameFromIndex(i) : getElementName(element);
    }
    protected String getElementName(UIElement element) {
        try {
            return (UIELEMENT_NAME != null
                ? UIELEMENT_NAME
                : textType.func).execute(element);
        } catch (Exception ex) {
            return "";
        }
    }
    protected boolean hasKey(String value) {
        return map.hasValue() && any(map.get().keys(), key -> namesEqual(key, value));
    }
    /**
     * @param value
     */
    @JDIAction(level = DEBUG) @Override
    public UIElement get(String value) {
        if (hasKey(value))
            return map.get().get(value);
        return getUIElement(value);
    }

    public UIElement getUIElement(String value) {
        if (locator.isTemplate())
            return new UIElement(base(), getLocator(value), nameFromValue(value));
        else {
            refresh();
            if (locator.isXPath())
                return new UIElement(base(), locator.addText(value), nameFromValue(value), parent);
            UIElement result = LinqUtils.first(elements(1), el -> namesEqual(el.getText(), value));
            if (result != null)
                return result;
            throw exception("Failed to get '%s' in list '%s'. No elements with this name found", value, getName());
        }
    }
    private JFunc1<UIElement, String> UIELEMENT_NAME;
    private boolean nameIndex = false;

    public WebList setUIElementName(JFunc1<UIElement, String> func) {
        UIELEMENT_NAME = func;
        return this;
    }
    public WebList setUIElementName(TextTypes type) {
        if (type.equals(INDEX)) {
            nameIndex = true;
            return this;
        }
        textType = type;
        return setUIElementName(type.func);
    }
    @Override
    public boolean isUseCache() {
        return webElements.isUseCache();
    }

    /**
     * @param index
     */
    @Override
    public UIElement get(int index) {
        if (index < startIndex)
            throw exception("Can't get element with index '%s'. Index should be %s or more", index, startIndex);
        return getByIndex(index);
    }

    @JDIAction(level = DEBUG)
    private UIElement getByIndex(int index) {
        if (index < startIndex)
            throw exception("Can't get element with index '%s'. Index should be %s or more", index, startIndex);
        int getIndex = index - startIndex;
        if (locator.isNull() && isUseCache() && webElements.get().size() > getIndex)
            return $(webElements.get().get(getIndex));
        UIElement element = locator.isTemplate()
                ? tryGetByIndex(index)
                : getElementByLocator(getIndex, index);
        return element.setName(nameFromIndex(index));
    }
    private UIElement getElementByLocator(int getIndex, int index) {
        return locator.isXPath()
            ? new UIElement(base(), locator.addIndex(index), index+"", parent)
            : initElement(() -> getList(getIndex + 1).get(getIndex));
    }
    protected UIElement tryGetByIndex(int index) {
        try {
            return new UIElement(base(), getLocator(index), nameFromIndex(index));
        } catch (Exception ex) {
            throw exception(ex, "Can't get element with index '%s' for template locator. " +
                            "Maybe locator is wrong or you need to get element by name", index);
        }
    }

////
    /**
     * Select the item by the value
     * @param value
     */
    @JDIAction("Select '{0}' for '{name}'")
    public void select(String value) {
        clickOnElement(get(value), value);
    }
    private void clickOnElement(UIElement element, String value) {
        if (element == null)
            throw exception("Can't get element '%s'", value);
        if (textType == LABEL) {
            if (element.isDisabled())
                throw exception("Can't perform click. Element is disabled");
            element.label().click();
        }
        else element.click();
    }

    /**
     * Select the items by the names
     * @param values
     */
    @JDIAction("Select ({0}) for '{name}'")
    public void select(String... values) {
        for (String value : values)
            select(value);
    }
    @JDIAction("Check all '{name}' unchecked options")
    public void checkAll() {
        for (UIElement checkbox : elements(1)) {
            if (checkbox.isEnabled() && !selected(checkbox)) {
                checkbox.click();
            }
        }
    }
    @JDIAction("Uncheck all '{name}' checked options")
    public void uncheckAll() {
        for (UIElement checkbox : elements(1)) {
            if (checkbox.isEnabled() && selected(checkbox)) {
                checkbox.click();
            }
        }
    }
    @JDIAction("Check only '{0}' in '{name}' list")
    public void check(String... names) {
        List<String> listNames = asList(names);
        for (UIElement value : elements(names.length)) {
            if (value.isDisabled()) continue;
            if (selected(value) && !listNames.contains(value.labelText().trim())
                    || !selected(value) && listNames.contains(value.labelText().trim()))
                value.click();
        }
    }
    @JDIAction("Uncheck '{0}' checkboxes in '{name}' checklist")
    public void uncheck(String... names) {
        List<String> listNames = asList(names);
        for (UIElement value : elements(names.length)) {
            if (value.isDisabled()) continue;
            if (selected(value) && listNames.contains(value.labelText().trim())
                    || !selected(value) && !listNames.contains(value.labelText().trim()))
                value.click();
        }
    }
    @JDIAction("Check '{0}' checkboxes in '{name}' checklist")
    public void check(int... indexes) {
        List<Integer> listIndexes = toList(indexes);
        int max = max(Ints.asList(indexes));
        List<UIElement> elements = elements(max - startIndex + 1);
        int i = startIndex;
        for (UIElement element : elements) {
            if (element.isDisabled()) continue;
            if (selected(element) && !listIndexes.contains(i)
                    || !selected(element) && listIndexes.contains(i))
                element.click();
            i++;
        }
    }
    @JDIAction("Uncheck '{0}' checkboxes in '{name}' checklist")
    public void uncheck(int... indexes) {
        List<Integer> listIndexes = toList(indexes);
        int max = max(Ints.asList(indexes));
        List<UIElement> elements = elements(max - startIndex + 1);
        int i = startIndex;
        for (UIElement element : elements) {
            if (element.isDisabled()) continue;
            if (selected(element) && listIndexes.contains(i)
                    || !selected(element) && !listIndexes.contains(i))
                element.click();
            i++;
        }
    }
    public <TEnum extends Enum<?>> void check(TEnum... values) {
        check(getEnumValues(values));
    }
    public <TEnum extends Enum<?>> void uncheck(TEnum... values) {
        uncheck(getEnumValues(values));
    }

    protected boolean selected(UIElement value) {
        return value.isSelected();
    }
    /**
     * Select the items by the values, hover and click on them
     * @param values
     */
    @JDIAction("Select ({0}) for '{name}'")
    public void hoverAndClick(String... values) {
        if (ArrayUtils.isEmpty(values))
            throw exception("Nothing to select in %s", getName());
        if (values.length < 2)
            throw exception("Hover and click method should have at list 2 parameters");
        int length = values.length;
        for (int i=0; i < length-1;i++) {
            get(values[i]).hover();
        }
        get(values[length-1]).click();
    }

    public <TEnum extends Enum<?>> void select(TEnum value) {
        select(getEnumValue(value));
    }

    /**
     * Select the items by the names
     * @param values
     */
    @JDIAction("Select ({0}) for '{name}'")
    public <TEnum extends Enum<?>> void select(TEnum... values) {
        for (TEnum value : values)
            select(value);
    }

    /**
     * Select the item by the index
     * @param index
     */
    @JDIAction("Select '{0}' for '{name}'")
    public void select(int index) {
        clickOnElement(get(index), index+"");
    }

    /**
     * Select the items by the indexes
     * @param indexes
     */
    @JDIAction("Select ({0}) for '{name}'")
    public void select(int... indexes) {
        for (int index : indexes)
            select(index);
    }

    /**
     * Get the selected element value
     * @return String
     */
    @JDIAction("Get '{name}' selected value")
    public String selected() {
        refresh();
        UIElement first = logger.logOff(() -> first(UIElement::isSelected) );
        return first != null ? getElementName(first) : "";
    }
    /**
     * Refresh the element
     */
    @JDIAction(level = DEBUG)
    public void refresh() {
        webElements.clear();
        map.clear();
    }

    /**
     * Clear the element
     */
    @JDIAction(level = DEBUG)
    public void clear() {
        refresh();
    }

    public void setValue(String value) {
        select(value);
    }

    public String getValue() {
        return print(values());
    }

    /**
     * Show all items
     */
    @JDIAction
    public void showAll() {
        int size;
        do {
            size = size();
            get(size-1).show();
            clear();
        } while (size < size());
    }
    @Override
    public int size() {
        try {
            return isUseCache() && webElements.hasValue() && webElements.get().size() > 0
                ? webElements.get().size()
                : IList.super.size();
        } catch (Exception ex) {
            return 0;
        }
    }

    @JDIAction("Check that '{option}' is selected in '{name}'")
    public boolean selected(String option) {
        return get(option).isSelected();
    }

    @JDIAction("Get '{name}' checked values")
    public List<String> checked() {
        return ifSelect(ui -> getByType(ui, CanBeSelected.class).isSelected(), this::getElementName);
    }
    @JDIAction("Get '{name}' values")
    public List<String> values() {
        refresh();
        WebList elements = noValidation(() -> elements(0));
        List<String> values = LinqUtils.map(elements, el -> el.noValidation(() -> el.text(base().textType)));
        HashSet<String> unique = new HashSet<>(values);
        if (unique.size() == values.size())
            map.set(new MapArray<>(values, elements));
        // else {
        //     map.set(solveUniqueness(unique, values, elements));
        // }
        return values;
    }
    private MapArray<String, UIElement> solveUniqueness(HashSet<String> unique, List<String> values, WebList elements) {
        MapArray<String, UIElement> result = new MapArray<>();
        for (String u : unique) {
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < values.size(); i ++) {
                if (values.get(i).equals(u))
                    indices.add(i);
            }
            if (indices.size() == 1)
                result.add(u, elements.get(indices.get(0)));
            else {
                for (int j : indices) {
                    try {
                        UIElement element = elements.get(j + startIndex);
                        element.text(base().textType);
                        result.add(u, element);
                        break;
                    } catch (Exception ignore) { }
                }
            }
        }
        return result;
    }
    @JDIAction("Get '{name}' values")
    public List<String> getValuesFast() {
        return getListFast().stream().map(WebElement::getText).collect(Collectors.toList());
    }
    @JDIAction("Get '{name}' values")
    public List<String> values(TextTypes type) {
        setUIElementName(type);
        return values();
    }

    @JDIAction("Get list of attributes for '{name}'")
    public List<String> attrs(String value) {
        return noValidation(() -> map(e -> e.attr(value)));
    }

    @JDIAction("Get list of enabled values for '{name}'")
    public List<String> listEnabled() {
        return noValidation(() -> ifSelect(UIElement::isEnabled, this::getElementName));
    }

    @JDIAction("Get list of disabled values for '{name}'")
    public List<String> listDisabled() {
        return noValidation(() -> ifSelect(UIElement::isDisabled, this::getElementName));
    }

    @JDIAction(value = "Check that '{name}' is displayed", timeout = 0)
    public boolean isDisplayed() {
        refresh();
        return isNotEmpty();
    }

    @JDIAction(value = "Check that '{name}' is hidden", timeout = 0)
    public boolean isHidden() {
        return !isDisplayed();
    }

    @JDIAction(value = "Check that '{name}' is enabled", timeout = 0)
    public boolean isEnabled() {
        return isNotEmpty() && first().isEnabled();
    }

    @JDIAction(value = "Check that '{name}' is disabled", timeout = 0)
    public boolean isDisabled() {
        return !isEnabled();
    }
////
    @JDIAction(level = DEBUG)
    public void highlight(String color) {
        foreach(el -> el.highlight(color));
    }
    @JDIAction(level = DEBUG)
    public void highlight() {
        foreach(UIElement::highlight);
    }
    @JDIAction(level = DEBUG)
    public void hover() {
        first().hover();
    }

    @JDIAction(level = DEBUG)
    public void show() {
        first().show();
    }
    public UISelectAssert<UISelectAssert<?,?>, WebList> is() {
        refresh();
        UISelectAssert<UISelectAssert<?,?>, WebList> is = new UISelectAssert<>();
        is.set(this);
        return is;
    }
    @JDIAction("Assert that {name} list meet condition")
    public UISelectAssert<UISelectAssert<?,?>, WebList> is(Matcher<? super List<UIElement>> condition) {
        MatcherAssert.assertThat(this, condition);
        return is();
    }
    public UISelectAssert<UISelectAssert<?,?>, WebList> assertThat(Matcher<? super List<UIElement>> condition) {
        return is(condition);
    }
    //endregion

    protected boolean isActual() {
        try {
            return isUseCache() && webElements.get().size() > 0 && isActual(webElements.get().get(0));
        } catch (Exception ex) { return false; }
    }

    protected boolean isActual(WebElement element) {
        try {
            return isNotBlank(element.getTagName());
        } catch (Exception ex) { return false; }
    }
    protected UIElement initElement(JFunc<WebElement> func) {
        return initElement(func.execute(), func);
    }
    protected UIElement initElement(WebElement el, JFunc<WebElement> func) {
        try {
            UIElement element = new UIElement(base(), el, func);
            element.locator = new JDILocator(element);
            return element;
        } catch (Exception ex) { throw exception(ex, "Can't init func new element for list"); }
    }// TODO to private
    public UIElement initElement(WebElement el, JFunc<WebElement> func, int i) {
        return initElement(el, func, nameFromIndex(i));
    }
    private UIElement initElement(WebElement el, JFunc<WebElement> func, String name) {
        return initElement(el, func).setName(name);
    }
    public boolean isEmpty() { return size() == 0; }
    public boolean isNotEmpty() { return size() > 0; }
    public Point getLocation() {
        return first().getLocation();
    }
    public Dimension getSize() {
        Point firstPoint = first().getLocation();
        Point lastPoint = last().getLocation();
        Dimension dLast = last().getSize();
        return new Dimension(lastPoint.x+dLast.width-firstPoint.x, lastPoint.y+dLast.height-firstPoint.y);
    }
    public void offCache() {
        super.offCache();
        map.useCache(false);
    }
    @Override
    public WebList finds(By locator) {
        List<WebElement> els = elements(1).selectMany(el -> el.finds(locator).webElements());
        return $$(els, context + ">" + locator);
    }
}