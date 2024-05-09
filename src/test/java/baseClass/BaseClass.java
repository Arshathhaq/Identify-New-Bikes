package baseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Properties p;
	public static  Logger LOGGER;

	@BeforeClass(groups = {"smoke" , "regression"})
	@Parameters({ "browser" })
	public void setUpBrowser(@Optional String browser) throws IOException, InterruptedException {
		LOGGER = LogManager.getLogger(this.getClass());

		// Loading property file
		FileReader file = new FileReader("./src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);

		if (browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			LOGGER.debug("Driver set to Chrome");
			System.out.println("*** Testing in Chrome ***");
			System.out.println("");
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
			LOGGER.debug("Driver set to Edge");
			System.out.println("*** Testing in Edge ***");
			System.out.println("");
		}

		driver.get(p.getProperty("appURL"));
		LOGGER.debug("APP URL : " + driver.getCurrentUrl());

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LOGGER.debug("Implicit Wait : 10 secs");

	}

	// Quitting browser function
	@AfterClass(groups = {"smoke" , "regression"})
	public void tearDown() {

		driver.quit();
		LOGGER.debug("Driver quits * ALL WINDOW *");
	}

	// Screenshot method
	public void captureScreenshot(String name)

	{
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile = new File("./Screenshots/"+name+".png");
		LOGGER.debug("Screenshot Taken : "+name+".png");
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
