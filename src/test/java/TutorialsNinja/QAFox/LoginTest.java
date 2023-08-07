package TutorialsNinja.QAFox;
import TutorialsNinja.QAFox.pages.HomePage;
import TutorialsNinja.QAFox.pages.LoginPage;
import TutorialsNinja.QAFox.pages.AccountPage;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TutorialsNinja.QAFox.Base.Base;
import TutorialsNinja.QAFox.Utils.Utilities;

public class LoginTest extends Base {
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	@BeforeMethod
	public void setup() {
		
		
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
	}
	
	@AfterMethod
	public void teadDown() {
		driver.quit();
	}
	
	//dataProvider using dataprovider name attribute
	@Test(priority=1,dataProvider="validCredentialsSupply")
	public void verifyLoginWithValidCredentials(String email,String password) {
		
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		accountPage=loginPage.clickOnLoginButton();
		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"edit your accountpage info is not displayed");
				
	}
	/*//using dataProvide method name
	 * @Test(priority=1,dataProvider="supplyTestData")
	public void verifyLoginWithValidCredentials(String email,String password) {
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());				
	}*/
	
	/*
	//get email and password using excelsheet
	 * @DataProvider(name="validCredentialsSupply")
	public Object[][] supplyTestData() {
		
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}*/
	
	
	@DataProvider(name="validCredentialsSupply")
	public Object[][] supplyTestData() {
		
		Object[][] data= {{"dhanasatish.27@gmail.com","Dhana@123"}};
		return data;
	}
	
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
			
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();		
		String actualMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		String expectedMessage=loginPage.retrieveEmailPasswordNotMatchingWarning();
		Assert.assertEquals(actualMessage, expectedMessage);
			
	}
	
	@Test(priority=3)
	public void verifyLoginWithNoCredentials() {
		
			
		loginPage.clickOnLoginButton();		
		String actualMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		String expectedMessage=loginPage.retrieveEmailPasswordNotMatchingWarning();
		Assert.assertEquals(actualMessage, expectedMessage);
			
	}
	
	@Test(priority=4)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();		
		String actualMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		String expectedMessage=loginPage.retrieveEmailPasswordNotMatchingWarning();
		Assert.assertEquals(actualMessage, expectedMessage);
				
	}
	
	@Test(priority=5)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
			
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();		
		String actualMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		String expectedMessage=loginPage.retrieveEmailPasswordNotMatchingWarning();
		Assert.assertEquals(actualMessage, expectedMessage);
			
	}	
}
