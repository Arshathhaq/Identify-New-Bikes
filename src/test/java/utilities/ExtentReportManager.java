package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
 
public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;  // UI of the report
	public ExtentReports extent;  //populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test methods
	public void onStart(ITestContext context) {
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "\\Reports\\myReport.html");//specify location of the report
		sparkReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
		sparkReporter.config().setReportName("Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Computer Name","localhost");
		extent.setSystemInfo("Environment","QA:SDET");
		extent.setSystemInfo("Testers:","Arshathul Mohamed Haq");
		extent.setSystemInfo("OS","Windows11");
		extent.setSystemInfo("Browser name", context.getCurrentXmlTest().getParameter("browser"));
		
		Map<String, String> parameters = context.getCurrentXmlTest().getAllParameters();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String env = System.getenv(parameter.getKey());
            if (env != null && !env.trim().isEmpty()) {
                parameter.setValue(env);
            }
        }
	}
 
 
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new enty in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s
		test.addScreenCaptureFromPath(System.getProperty("user.dir")+"/Screenshots/"+result.getName()+".png");
	}
 
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable()); 
	}
 
	public void onTestSkipped(ITestResult result) {
 
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
	}
 
	
	public void onFinish(ITestContext context) {
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\Reports\\myReport.html";
		File extentReport = new File(pathOfExtentReport);
		try 
		{
			// Open the extent report in the default browser
			Desktop.getDesktop().browse(extentReport.toURI());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}