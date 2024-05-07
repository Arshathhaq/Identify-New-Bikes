package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleLogin extends BasePage{

	public GoogleLogin(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//*[@id=\"forum_login_wrap_lg\"]")
	WebElement logIn;
	@FindBy(xpath="//*[@id=\"myModal3-modal-content\"]/div[1]/div/div[3]/div[6]/div")
	WebElement googleBtn;
	@FindBy(xpath="//*[@id=\"identifierId\"]")
	WebElement emailId;
	@FindBy(xpath="//*[@id=\"identifierNext\"]/div/button/div[3]")
	WebElement next;
	@FindBy(xpath="//div[@class='Ekjuhf Jj6Lae']")
	WebElement errorMsg;

	
	public void handleLogIn() {
		try {
		logIn.click();
		Thread.sleep(3000);
		googleBtn.click();
		Set<String> windowIDs  = driver.getWindowHandles(); // store 2 window id's
		 List<String> windowidList = new ArrayList<String>(windowIDs);
	        String childWindowID = windowidList.get(1);
	        driver.switchTo().window(childWindowID);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void putEmail(String email) throws InterruptedException {
		emailId.sendKeys(email);
		Thread.sleep(3000);
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", next);
		Thread.sleep(5000);
	}
	public String getErrorMsg() {
		//this won't work as the website stops this automated login
		return errorMsg.getText();
		
	}

}
