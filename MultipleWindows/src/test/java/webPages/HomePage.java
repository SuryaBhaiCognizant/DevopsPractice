package webPages;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import exception.MiniProjectException;
import utilities.DriverSetup;
import utilities.PropertiesSetup;

public class HomePage {
	
	//Global Variables
	WebDriver driver;
	Properties prop;
	
	//Constructor
	public HomePage() {
		prop = new Properties();
		try {
			this.prop = PropertiesSetup.getProperty();
			this.driver = DriverSetup.getDriver(prop);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		PageFactory.initElements(driver,this);
	}
	
	
	
	//Locators
	@FindBy(xpath="//a[normalize-space()='Create Account']") WebElement createBtn;

	public static void main(String[] args) {
		try {
			//Creating a HomePage Object, Launching the browser
			HomePage hp = new HomePage();
			
			//Opening the url in browser.
			hp.get();
			
			//Clicking on the "Create Account" link
			hp.clickOnLink();
			
			//Opening the next Class
			CreatePage.main(hp.driver);
		}catch(MiniProjectException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void get() throws MiniProjectException{
		try {
			driver.get(prop.getProperty("base_url"));	
			driver.manage().window().maximize();
			System.out.println("TC-03 || Success: URL opened successfully!");
		} catch (Exception e) {
			throw new MiniProjectException("TC-03 || Error: An error occured while opening the url!");
		}
			
	}
	
	private void clickOnLink() throws MiniProjectException{
		try {
			createBtn.click();
			System.out.println("TC-04 || Success: Create Page opened successfully!");
		}catch(Exception e) {
			throw new MiniProjectException("TC-04 || Error: An error occured while clicking the url");
		}
	}

}
