package TutorialsNinja.QAFox;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TutorialsNinja.QAFox.Base.Base;
import TutorialsNinja.QAFox.Utils.Utilities;
import TutorialsNinja.QAFox.pages.AccountSuccessPage;
import TutorialsNinja.QAFox.pages.HomePage;
import TutorialsNinja.QAFox.pages.RegisterPage;

public class RegisterTest extends Base {
	
	public RegisterTest() {
		super();
	}
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@BeforeMethod
	public void setup() {		
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage=homePage.selectRegisterOption();
	}
	@AfterMethod
	public void teadDown() {
		driver.quit();
	}
		
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(dataProp.getProperty("password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("password"));
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnContinueButton();
					
		String actualSuccessText=accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessText,dataProp.getProperty("accountCreatedMessage"),"Account is not created");		
	}
	
	
	@Test(priority=2)
	public void verifyRegisteringAnAccountWithAllFields() {
		
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(dataProp.getProperty("password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("password"));
		registerPage.clickOnNewsletterOption();	
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnContinueButton();
			
		String actualSuccessText=accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessText,dataProp.getProperty("accountCreatedMessage"),"Account is not created");	
		
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringAnAccountWithExixtingEmail() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephone"));
		registerPage.enterPassword(dataProp.getProperty("password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("password"));
		registerPage.clickOnNewsletterOption();	
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinueButton();
			
		String actualText=registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertEquals(actualText,dataProp.getProperty("duplicateEmailWarning"));		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {
		
		
		registerPage.clickOnContinueButton();
		
		String actualPrivacyPolicyWarning=registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyWarning")),"Privacy Policy Warning message is not displayed");
		
		String actualFirstNameWarning=registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning,dataProp.getProperty("firstNameWarning"),"First name warning message not displayed");
		
		String actualLasttNameWarning=registerPage.retrieveLastNameWarning();
		Assert.assertEquals(actualLasttNameWarning,dataProp.getProperty("lastNameWarning"),"Last name warning message not displayed");
		
		String actualEmailWarning=registerPage.retrieveEmailAddressWarning();
		Assert.assertEquals(actualEmailWarning,dataProp.getProperty("emailWarning"),"Email warning message not displayed");
		
		String actualPhoneWarning=registerPage.retrieveTelephoneWarning();
		Assert.assertEquals(actualPhoneWarning,dataProp.getProperty("telephoneWarning"),"Phone warning message not displayed");
		
		String actualPasswordWarning=registerPage.retrievePasswordWarning();
		Assert.assertEquals(actualPasswordWarning,dataProp.getProperty("passwordWarning"),"Password warning message not displayed");
			
	}
	
	
}

