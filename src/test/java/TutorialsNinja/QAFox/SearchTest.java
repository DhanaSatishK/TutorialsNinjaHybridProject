package TutorialsNinja.QAFox;
import TutorialsNinja.QAFox.pages.HomePage;
import TutorialsNinja.QAFox.pages.SearchPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TutorialsNinja.QAFox.Base.Base;

public class SearchTest extends Base{
	
	SearchPage searchPage;
	public SearchTest() {
		super();
	}


	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
	}
	@AfterMethod
	public void teadDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		HomePage homePage=new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		searchPage=homePage.clickOnSerachButton();
	
		searchPage.displayStatusOfHPProduct();	
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		HomePage homePage=new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("inValidProduct"));
		searchPage=homePage.clickOnSerachButton();
		
		String actualSerachMessage=searchPage.retrieveNoProductMessageText();
		//Assert.assertEquals(actualSerachMessage, dataProp.getProperty("inValidProductWarning"),"No product in search results");
		Assert.assertEquals(actualSerachMessage,"abcde","No product in search results");
	}
	
	@Test(priority=3, dependsOnMethods={"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		HomePage homePage=new HomePage(driver);
		searchPage=homePage.clickOnSerachButton();
		
		String actualSerachMessage=searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSerachMessage, dataProp.getProperty("inValidProductWarning"),"No product in search results");		
	}

}
