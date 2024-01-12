package javaproject2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Script1 {
	private WebDriver driver;
	@BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html"); 
	        new Page1(driver);
	        driver.manage().window().maximize();
	    }
	 
	    @Test
	    public void Script() throws Throwable {
	    	Page1.fillvaluesinTAble();
	    	Page1.VerifyTableAgainstJson();
	        
	    }
	    
	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }



}
