package TutorialsNinja.QAFox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	public String retrieveNoProductMessageText() {
		String displayStatus=noProductMessage.getText();
		return displayStatus;
	}

	public boolean displayStatusOfHPProduct() {
		boolean displayStatus=validHPProduct.isDisplayed();
		return displayStatus;
	}

}