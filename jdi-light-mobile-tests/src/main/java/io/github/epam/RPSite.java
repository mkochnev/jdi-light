package io.github.epam;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import io.github.epam.pages.*;

import static com.epam.jdi.light.common.CheckTypes.CONTAINS;

@JSite("https://dev.reportportal.io/ui")
public class RPSite {
    @Url("/#login")  @Title(value = "Report Portal", validate = CONTAINS)
    public static RPLoginPage loginPage;
    @Url("/#jdi-tests/dashboard") @Title("Report Portal")
    public static RPDashboardPage dashboardPage;
    @Url("/#jdi-tests/launches/all") @Title("Report Portal")
    public static RPLaunchesPage launchesPage;
//    @Url(value = "/metals-colors.html{0}", template = "metals-colors.html") @Title("Metal and Colors")
//    public static MetalAndColorsPage metalAndColorsPageParams;
//    @Url("/contacts.html") @Title(value = ".*tact \\w{4}", validate = MATCH)
//    public static ContactFormPage contactFormPage;
//    @Url("/performance.html") @Title("Performance page")
//    public static PerformancePage tablePage;
//    @Url("/search.html")
//    public static SearchPage searchPage;
//    @Url("/support") @Title("Support")
//    public static WebPage supportPage;
//
//    @Css("[ui=label]") public static List<WebElement> navigation;
//    @Css("[ui=label]") public static WebList navigationL;
//    @UI("//*[@ui='label']//*[contains(text(),'%s')]") public static WebList navigationS;
//    // TODO fix slow @UI("[ui=label][*'%s']") public static WebList navigationS;
//    @UI("//*[@ui='label']//*[contains(text(),'%s')]") public static UIElement menu;
//    // TODO fix slow @UI("[ui=label][*'%s']") public static UIElement menu;
//
//    @Css("header") public static Header header;
//    @Css("footer") public static Footer footer;
//
//    @Frame("#jdi-frame-site") public static HomePageFrame iframe;
//    @Frame("#first_frame") @UI("img")
//    public static UIElement wolverinFrame;
//    @Frame({"#second_frame", "#frame_in_frame"}) @UI("img")
//    public static UIElement spidermanElement;
//    @Frame({"#second_frame", "#frame_in_frame"})
//    public static FrameSpiderman frameSpiderman;
//
//    public static GithubPage githubPage;
}