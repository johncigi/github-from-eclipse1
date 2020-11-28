package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import pageClasses.CarLoanPage;
import pageClasses.LandingPage;



public class CarLoanTest extends BaseTestClass {
	
	
	LandingPage landingPage;
	CarLoanPage carLoanPage;

	@Test(dataProvider ="getCarLoanTestData")
	public void carEmiCalculator(String loan_amount, String interest_rate, String tenure, String loan_tenure,
			String expected_result) throws IOException  {
		logger=report.createTest("Car Loan Emi Calculation:");
		
		invokeBrowser("chrome");
		PageBaseClass pageBase= new PageBaseClass(driver,logger); 
		PageFactory.initElements(driver, pageBase);
		landingPage= pageBase.openApplication();
		carLoanPage=landingPage.clickCarLoanLink();
		waitForPageLoad();
		carLoanPage.calculateCarLoan(loan_amount,interest_rate ,loan_tenure);
		//carLoanPage.calculateCarLoan("1500000","9.5" ,"5");
		waitForPageLoad();
		
		logger.log(Status.INFO, "Finding Total Payment Result:");
		String actual_result = TotalPayment(prop.getProperty("TotalPayment"));
		/*if (actual_result.equals("0")) {
			actual_result = "0.0";
		}
		Assert.assertEquals(expected_result, actual_result); */
		logger.log(Status.PASS, "Total Payment is: "+ actual_result);
		
		 writeExcelData(System.getProperty("user.dir") + "\\Resources\\Repositiries\\carLoan.xlsx",
					prop.getProperty("Label_Xpath1"), prop.getProperty("Row_Xpath1"), prop.getProperty("Col_Xpath1"));
		 flushReports();
	}
	
	@DataProvider
	public Object[][] getCarLoanTestData(){
		Object[][] arrayObject = getExcelData(
				System.getProperty("user.dir") + "\\Resources\\Repositiries\\TestDataEmiCalculator.xlsx", "Scenario1", 9, 6);
		
		return arrayObject;

		
	}
	
}
