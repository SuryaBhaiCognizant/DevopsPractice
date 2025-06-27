package webPages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.MiniProjectException;
import utilities.DriverSetup;

public class CreatePage {
	//Global Variable
	WebDriver driver;
	String parentHandle ;
	//Constructor
	public CreatePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		parentHandle = driver.getWindowHandle();
	}
	
	//Locators
	@FindBy(xpath="//a[normalize-space()='terms and conditions']") WebElement termsBtn;
	@FindBy(tagName = "a") List<WebElement> list;
	
	public static void main(WebDriver driver) throws MiniProjectException{
		try {
			//Creating object of CreatePage
			CreatePage cp = new CreatePage(driver);
			
			//Validating the CreatePage
			cp.validate();
			
			// Counting the total numbers of links in Page
			System.out.println(cp.countLinks() + " is the total numbers of links in page.");
			
			//Clicking on terms and conditions link
			cp.openingTermsAndConditions();
			
			//Switching the window to Terms
			cp.switchToTermsPage();
			
			//Close the browser
			DriverSetup.closeDriver(driver);
		} catch (Exception e) {
			throw new MiniProjectException(e.getMessage());
		}
		
	}
	
	public void validate() throws MiniProjectException {
		if(driver.getTitle().equals("Rediffmail Free Unlimited Storage")) {
			System.out.println("TC-05 || Success: Validated the create account page!");
		}
		else {
			throw new MiniProjectException("TC-05 || Error: An error occured while validating the page");
		}
	}
	
	public int countLinks() throws MiniProjectException{
		if(list.isEmpty()) {
			throw new MiniProjectException("TC-06 || Error: An error occured while counting the total number of links");
		}
		else { 
			System.out.println("TC-06 || Success: Count was return successfully!");
			return list.size();		
		}
	}
	
	public void openingTermsAndConditions() throws MiniProjectException{
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(termsBtn).click().perform();
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			System.out.println("TC-07 || Success: Clicked the terms and conditions button!");
		}catch (Exception e) {
			e.printStackTrace();
			throw new MiniProjectException("TC-07 || Error: An error occured while clicking the terms and conditions button!");
		}
		
	}
	
	
	
	public void switchToTermsPage() throws MiniProjectException{
		for(String handle : driver.getWindowHandles()) {
			if(!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		try {
			TermsPage.main(this.driver,parentHandle);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
