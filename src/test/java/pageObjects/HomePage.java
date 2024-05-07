package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[normalize-space()='New Bikes']")
	WebElement newBikes;
	@FindBy(xpath = "//a[normalize-space()='Upcoming Bikes']")
	WebElement upcomingBikes;
	@FindBy(xpath = "//div[@id='forum_login_wrap_lg']")
	WebElement logIn;

	Actions actions = new Actions(driver);
	Logger LOGGER = LogManager.getLogger(this.getClass());

	public void hoverNewBikes() {
		try {
			actions.moveToElement(newBikes).build().perform();
			LOGGER.debug("*** Hovered on the New Bikes Section ***");

		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		}
	}

	public void selectUpcomingBikes() {
		try {
			upcomingBikes.click();
			LOGGER.debug("*** Clicked the Upcoming Bikes Section ***");
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		}
	}

	public void ClickOnLogin() {
		logIn.click();
	}

}
