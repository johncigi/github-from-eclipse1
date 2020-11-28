package baseClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageClasses.LandingPage;

public class PageBaseClass extends BaseTestClass {
 
	  public ExtentTest logger;
	  
	  public PageBaseClass(WebDriver driver, ExtentTest logger) {
		  this.driver= driver;
		  this.logger= logger;
	  }
	  
	  /*********** Opening Application **********/
	  public LandingPage openApplication() {
		  logger.log(Status.INFO, "Opening the EmiCalculator Website");
		  driver.get("https://emicalculator.net/");
		  logger.log(Status.PASS, "Successfully Opened the emicalculator.net Site");
		  LandingPage landingPage= new LandingPage(driver,logger);
		  PageFactory.initElements(driver, landingPage);
		  return landingPage;
		}		 
	  
	  
	  /********** Clearing the Input Fields *********/
	  public void clearField(WebElement element) {
		  
		  element.sendKeys(Keys.CONTROL + "a");
		  element.sendKeys(Keys.DELETE);		  
	  }
	  
	  /********* Entering Data in Input fields *********/   
	  public void addValues(WebElement element, String value) {

		  try {
			  
			  element.sendKeys(value);
			  element.sendKeys(Keys.ENTER);
			  
		  }
		  catch (Exception e) {
			  e.printStackTrace();
			  reportFail(e.getMessage());    
		  }
	  }
	  
	 
	   
	  
}
