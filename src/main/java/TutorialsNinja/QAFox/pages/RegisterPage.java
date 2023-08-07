package TutorialsNinja.QAFox.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TutorialsNinja.QAFox.Utils.Utilities;

public class RegisterPage {
	
	
WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'] [@value='1']")
	private WebElement newsletterField;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}
	
	public void clickOnPrivacyPolicy() {
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void clickOnNewsletterOption() {
		newsletterField.click();
	}
	
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText=duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText=privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveFirstNameWarning() {
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retrieveLastNameWarning() {
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retrieveEmailAddressWarning() {
		String emailWarningText=emailWarning.getText();
		return emailWarningText;
	}
	
	public String retrieveTelephoneWarning() {
		String telephoneWarningText=telephoneWarning.getText();
		return telephoneWarningText;
	}
	
	public String retrievePasswordWarning() {
		String passwordWarningText=passwordWarning.getText();
		return passwordWarningText;
	}

}
