package utilities;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import exception.MiniProjectException;

public class DriverSetup {
	public static WebDriver getDriver(Properties prop) throws MiniProjectException{
		try {
            String driverName = prop.getProperty("driver_name");
	        // Initialize WebDriver based on specified browser type
	        if(driverName.equalsIgnoreCase("Chrome")) {
	        	System.out.println("TC-02 || Success: Driver opened successfully!");
	            return new ChromeDriver();
	        } else if(driverName.equalsIgnoreCase("Edge")){
	        	System.out.println("TC-02 || Success: Driver opened successfully!");
	            return new EdgeDriver();
	        } else {
	        	throw new Exception();
	        }		        
        } catch(Exception e) {
        	throw new MiniProjectException("TC-02 || Error: An error occured while opening driver ready!");
        }
	}
	
	 public static void closeDriver(WebDriver driver) throws Exception {
		 try {
			 driver.quit();
			 System.out.println("TC-11 || Success: Driver closed successfully!");
		 }catch(Exception e) {
			 throw new Exception("TC-11 || Error: An error occured while closing driver!");
		 }
	 }
}
