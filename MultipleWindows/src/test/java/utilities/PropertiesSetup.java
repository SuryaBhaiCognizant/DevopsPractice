package utilities;

import java.io.FileInputStream;
import java.util.Properties;

import exception.MiniProjectException;

public class PropertiesSetup {

	public static Properties getProperty()  throws MiniProjectException{
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("driver.properties");
			prop.load(file);
			System.out.println("TC-01 || Success: Properties file loaded successfully!");
		}catch (Exception e) {
			throw new MiniProjectException("TC-01 || Error: occured while loading properties file!");
		}
		return prop;
	}
}
