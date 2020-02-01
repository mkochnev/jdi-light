package io.github.epam.bootstrap.tests.complex;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.tools.Timer;
import io.github.com.entities.SlideInfo;
import io.github.com.sections.Slide;
import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.github.com.StaticSite.bsPage;
import static io.github.com.pages.BootstrapPage.*;
import static io.github.com.pages.BootstrapPage.progressbar;
import static io.github.epam.bootstrap.tests.BaseValidationsUtils.baseValidation;
import static io.github.epam.states.States.shouldBeLoggedIn;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class CarouselTests extends TestsInit {
	final String firstSlideText = "FIRST SLIDE[Wolverin:Nulla vitae elit libero, a pharetra augue mollis interdum.]";
	final String secondSlideText = "SECOND SLIDE[Cyclope:Lorem ipsum dolor sit amet, consectetur adipiscing elit.]";
	final String thirdSlideText = "THIRD SLIDE[Punisher:Praesent commodo cursus magna, vel scelerisque nisl consectetur.]";

	@BeforeMethod
	public void before() {
		shouldBeLoggedIn();
		bsPage.shouldBeOpened();
		WebPage.reload();
		carousel.show();
	}

	@Test
	public void getSlideTest() {
		Slide slide = carousel.activeSlide();
		slide.has().text(firstSlideText);
	}

	@Test
	public void nextSlideTest() {
		carousel.next();
		Slide slide = carousel.activeSlide();
		slide.has().text(secondSlideText);
	}

	@Test
	public void prevSlideTest() {
		carousel.previous();
		Slide slide = carousel.activeSlide();
		slide.has().text(thirdSlideText);
	}

	@Test
	public void selectSlideTest() {
		carousel.openSlide(3);
		Slide slide = carousel.activeSlide();
		slide.has().text(thirdSlideText);
		slide.image.find("img").has().attr("src", containsString("punisher.jpg"));
	}

	//@Test
	//public void selectSlideByNameTest() {
	//	carousel.openSlide("SECOND SLIDE");
	//	Slide slide = carousel.activeSlide();
	//	slide.has().text(secondSlideText);
	//	slide.image.find("img").has().attr("src", containsString("cyclope.jpg"));
	//}
	@Test
	public void getSlideDataTest() {
		SlideInfo info = carousel.activeSlideData();
		assertEquals(info.toString(), firstSlideText);
	}

	//@Test
	//public void getSlideDataByIndexTest() {
	//	SlideInfo info = carousel.slides.getData(2);
	//	assertEquals(info.toString(), secondSlideText);
	//}
	//@Test
	//public void getSlideDataByTextTest() {
	//	SlideInfo info = carousel.slides.getData("THIRD SLIDE LABEL");
	//	assertEquals(info.toString(), thirdSlideText);
	//}
	//@Test
	//public void getSlideByIndexTest() {
	//	Slide info = carousel.slides.get(2);
	//	info.image.find("img").has().attr("src", containsString("cyclope.jpg"));
	//}
	//@Test
	//public void getSlideByTextTest() {
	//	Slide info = carousel.slides.get("THIRD SLIDE LABEL");
	//	info.image.find("img").has().attr("src", containsString("punisher.jpg"));
	//}

	@Test
	public void isValidationTest() {
		carousel.is().displayed().enabled();
		carousel.has().text(firstSlideText);
	}
	@Test
	public void baseValidationTest() {
		baseValidation(carousel);
	}
}
