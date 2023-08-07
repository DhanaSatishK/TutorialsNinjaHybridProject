package TutorialsNinja.QAFox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	private WebElement emailAddress;
	

	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	
	public void enterEmailAddress(String emailText) {
		emailAddress.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText) {
		password.sendKeys(passwordText);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNotMatchingWarning() {
		String warningText= emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
}