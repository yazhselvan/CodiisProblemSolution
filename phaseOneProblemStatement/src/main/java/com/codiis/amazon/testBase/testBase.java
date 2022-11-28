package com.codiis.amazon.testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.codiis.amazon.util.*;

public class testBase {
	
	public static Properties prop;
	public static WebDriver driver;

	public testBase() {
		try {
			 prop = new Properties();
			 
			 String ConfigPath = System.getProperty("user.dir")+"/src/main/java/com/codiis/amazon/config/config.properties";
			 FileInputStream ip = new FileInputStream(ConfigPath);
			 prop.load(ip);
			}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(testUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testUtil.IMPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
	}
	
}
