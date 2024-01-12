package javaproject2;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;


public class Page1 {
	 static WebDriver driver;
	
	 static By tableData = By.xpath("//*[text()='Table Data']");
	 static By inputBox = By.id("jsondata");
	 static By  refreshButton = By.id("refreshtable");
	 static By tableElement =   By.id("dynamictable");
	 static By tableHeading = By.tagName("tr");
	 static By tableRow = By.tagName("td");
	
	public Page1(WebDriver driver) {
        Page1.driver = driver;
    }
	public static String readjsonFile() throws FileNotFoundException, IOException {
			try (FileReader reader = new FileReader(".\\json_files\\testData.json")) {
			
			StringBuilder rawJsonData = new StringBuilder();
			
			 int data;
	            while ((data = reader.read()) != -1) {
	                rawJsonData.append((char) data);
	            }

	            // return the raw JSON string 
	          return  rawJsonData.toString();
		}
			
	}
			
	public static void fillvaluesinTAble() throws FileNotFoundException, IOException, Throwable {
		//click table data button
		driver.findElement(tableData).click();
		
		driver.findElement(inputBox).clear();
		driver.findElement(inputBox).sendKeys(readjsonFile());
		//click on refresh Button
		driver.findElement(refreshButton).click();
		
		}
	
	public static void VerifyTableAgainstJson() throws IOException {
		 
		        // Read JSON data from file
		        Path jsonFilePath = (Paths.get("json_files/testData.json"));
				String jsonContent = new String(Files.readAllBytes(jsonFilePath));
				
		        // Parse JSON content
		        JSONArray jsonArr = new JSONArray(jsonContent);
		        
		        // Access the HTML table
		        WebElement table = driver.findElement(tableElement);

			
		        for (int i = 0; i < jsonArr.length(); i++) {
		            JSONObject jsonObj = jsonArr.getJSONObject(i);

		            // Get row data from HTML table
		            WebElement row = table.findElements(tableHeading).get(i+1);
		            WebElement nameCell = row.findElements(tableRow).get(0);
		            WebElement ageCell = row.findElements(tableRow).get(1);
		            WebElement genderCell = row.findElements(tableRow).get(2);

		            // Extract text from HTML table
		            String name = nameCell.getText();
		            int age = Integer.parseInt(ageCell.getText());
		            String gender = genderCell.getText();

		            // Compare with JSON data
		            String jsonName = jsonObj.getString("name");
		            int jsonAge = jsonObj.getInt("age");
		            String jsonGender = jsonObj.getString("gender");
		            
		            //comparing both data
		            Assert.assertEquals(name, jsonName);
		            Assert.assertEquals(age, jsonAge);
		            Assert.assertEquals(gender, jsonGender);

		    }
		        System.out.println("Test passed");
	}
	
}
