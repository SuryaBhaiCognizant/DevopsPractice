package webPages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import exception.MiniProjectException;

public class TermsPage {
	//Global Variables
	WebDriver driver;
	
	//Constructor
	public TermsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators
	@FindBy(xpath="//div[@class='floatR']//input[@value='OK']") WebElement okBtn;

	public static void main(WebDriver driver, String parentHandle) throws MiniProjectException{
		//Creating object of TermsPage
		TermsPage tp = new TermsPage(driver);
		
		// Validating the childWindow()
		tp.validateChildWindow();
		
		//Getting the title of childWindow
		System.out.println(tp.getTitle());
		
		//Validating the terms and conditions
		tp.clickOk(parentHandle);
	}
	
	public void validateChildWindow() throws MiniProjectException {
		FluentWait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(MiniProjectException.class);
		wait.until(driverInstance->{
			return driver.getTitle().equals("Rediffmail: Terms and Conditions");
		});
		if(driver.getTitle().equals("Rediffmail: Terms and Conditions")) {
			System.out.println("TC-08 || Success: Validated the create account page!");
		}
		else {
			throw new MiniProjectException("TC-08 || Error: An error occured while validating the page");
		}
	}
	
	public String getTitle() throws MiniProjectException{
		if(driver.getTitle().equals("Rediffmail: Terms and Conditions")) {
			System.out.println("TC-09 || Success: Got the page title!");
			return driver.getTitle();
		}
		else {
			throw new MiniProjectException("TC-09 || Error: Unable to get the page title!");
		}
	}
	
	public void clickOk(String parentHandle) throws MiniProjectException{
		try {
			okBtn.click();
			driver.switchTo().window(parentHandle);			
			System.out.println("TC-10 || Success: Accepted the terms and conditions!");
		}catch(Exception e) {
			e.printStackTrace();
			throw new MiniProjectException("TC-10 || Error: Unable to accept the terms and conditions!");
		}
	}
}
