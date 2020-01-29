package io.github.com.pages;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.bootstrap.elements.common.*;
import com.epam.jdi.light.ui.bootstrap.elements.complex.Carousel;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Checkbox;
import com.epam.jdi.light.ui.html.elements.common.Range;
import io.github.com.RangeControl;
import io.github.com.custom.ButtonWithTooltip;
import io.github.com.entities.SlideInfo;
import io.github.com.sections.DismissAlert;
import io.github.com.sections.Slide;

public class BootstrapPage extends WebPage {
    public static UIElement inputGroupWrap, inputGroupNowrap;
    public static Badge badge;
    public static Button redButton, doubleButton, disabledButton, increaseProgress,
        decreaseProgress, resetProgress;
    public static Progress progressbar, multiProgressbar;
    public static Breadcrumb breadcrumb;
    public static Switch switcher, disabledSwitcher;
    public static ButtonWithTooltip buttonWithTooltip;
    public static Button simpleToastButton;
    public static Checkbox acceptCheckbox;

    //region Alerts
    public static Alert simpleAlert;
    public static DismissAlert dismissibleAlert;
    //endregion

    //region Ranges
    public static Range defaultRange;
    public static Range minMaxRange;
    public static Range fractionalRange;
    public static Range disabledRange;
    @UI("#default-range")
    public static RangeControl rangeControl;
    //endregion

    //region Ranges
    public static Button startButton;
    public static Spinner spinnerLoading;
    public static Loading spinnerRotate;
    //endregion

    public static Carousel<Slide, SlideInfo> carousel;
    @UI("#list-example>a") public static WebList scrollSpy;

}
