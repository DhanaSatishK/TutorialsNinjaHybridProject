package TutorialsNinja.QAFox.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TutorialsNinja.QAFox.Utils.ExtentReporter;
import TutorialsNinja.QAFox.Utils.Utilities;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
	
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" Started executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.PASS,result.getName()+" Got successfuly executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		WebDriver driver=null;
		
	try {	
	driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch(Throwable e) {
		e.printStackTrace();
	}
	
	String destinationScreenshotPath=Utilities.captureScreenshot(driver, result.getName());
	
	extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
	extentTest.log(Status.INFO, result.getThrowable());
	extentTest.log(Status.FAIL, result.getName()+" got failed");
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		//after finishing extent report will open automatically with below code
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentRport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentRport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
