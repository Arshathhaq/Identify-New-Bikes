package testClass;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.GoogleLogin;
import pageObjects.HomePage;
import pageObjects.UpcomingBikes;
import pageObjects.UsedCars;

public class TestCases extends BaseClass {

	@Test(priority = 1, groups = { "smoke", "regression" })
	public void goToUpcomingBikes() throws InterruptedException, IOException {
		HomePage hp = new HomePage(driver);
		Thread.sleep(5000);
		hp.hoverNewBikes();
		captureScreenshot("goToUpcomingBikes");
		hp.selectUpcomingBikes();
	}

	@Test(priority = 2, groups = { "regression" })
	@Parameters({ "manufacturer" })
	public void selectHonda(String name) throws InterruptedException, IOException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		ub.selectManufacturer(name);
		captureScreenshot("selectHonda");
		Thread.sleep(4000);
	}

	@Test(priority = 3, groups = { "regression" })
	public void showBikes() throws InterruptedException, IOException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		ub.selectReadMore();
		ub.scrollDown();
		captureScreenshot("showBikes");
		Thread.sleep(4000);
	}

	@Test(priority = 4, groups = { "regression" })
	public void fetchBikeDetails() throws InterruptedException, IOException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		ArrayList<String[]> results = ub.captureData();
		captureScreenshot("fetchBikeDetails");
		Thread.sleep(2000);
		System.out.println("Name					|	Price		|	Expected Launch Date		");
		for (String[] result : results) {
			System.out.println(result[0].trim() + "				|	" + result[1] + "	|	" + result[2]);
		}
	}

	@Test(priority = 5, groups = { "regression" })
	public void moveToUsedCars() throws IOException, InterruptedException, AWTException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		ub.scrollUp();
		ub.navigateToUsedCars();
		captureScreenshot("moveToUsedCars");
	}

	@Test(priority = 6, groups = { "regression" })
	public void findTopCars() throws IOException, InterruptedException {
		UsedCars uc = new UsedCars(driver);
		uc.clickOnReadMore();
		uc.scrollDown();
		captureScreenshot("findTopCars");
		Thread.sleep(3000);
	}

	@Test(priority = 7, groups = { "regression" })
	public void fetchTopCars() throws IOException {
		UsedCars uc = new UsedCars(driver);
		ArrayList<String[]> topCars = uc.findPopularUsedCars();
		captureScreenshot("fetchTopCars");
		System.out.println("Model			|	Price in Chennai");
		for (String[] topCar : topCars) {
			System.out.println(topCar[0] + "			|	" + topCar[1]);
		}
	}

	@Test(priority = 8, groups = { "regression" })
	public void backToHomePage() throws IOException, InterruptedException {
		UsedCars uc = new UsedCars(driver);
		uc.scrollUptoTop();
		captureScreenshot("backToHomePage");
		Thread.sleep(2000);
		uc.navigateBackToMainPage();

	}

	@Test(priority = 9, groups = { "smoke" , "regression" })
	public void logIn() {
		GoogleLogin gl = new GoogleLogin(driver);
		gl.handleLogIn();
		captureScreenshot("logIn");
	}

	@Test(priority = 10, groups = { "smoke" ,"regression"})
	@Parameters({ "email" })
	public void invalidLogIn(String email) throws InterruptedException {
		GoogleLogin gl = new GoogleLogin(driver);
		gl.putEmail(email);
		Thread.sleep(3000);
		captureScreenshot("invalidLogIn");
		System.out.println("Error msg : " + gl.getErrorMsg());
	}

}
