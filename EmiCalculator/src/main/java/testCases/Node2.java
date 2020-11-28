package testCases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import pageClasses.CarLoanPage;
import pageClasses.HomeLoanEmiPage;
import pageClasses.LandingPage;
import pageClasses.LoanCalculatorPage;


public class Node2 extends BaseTestClass {
	
	LandingPage landingPage;
	CarLoanPage carLoanPage;
	//LandingPage landingPage;
	HomeLoanEmiPage homeLoanEmiPage;
	LoanCalculatorPage loanCalculatorPage;
	
	@Test(priority=0)
	  public void f() {
			
		WebDriver driver;
		String nodeUrl;
			
			 try {
				nodeUrl ="http://192.168.1.17:5566/wd/hub";
				
				 DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				 
				 capabilities.setBrowserName("firefox");
				 capabilities.setPlatform(Platform.WINDOWS);
				 driver=new RemoteWebDriver(new URL(nodeUrl), capabilities);
				 
				 
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	@Test(dataProvider ="getCarLoanTestData")
	public void carEmiCalculator(String loan_amount, String interest_rate, String tenure, String loan_tenure,
			String expected_result) throws IOException  {
		logger=report.createTest("Car Loan Emi Calculation:");
		
		invokeBrowser("firefox");
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
	
	@Test(dataProvider = "homeloanemi")
	public void homeLoanEmiCalculator(String home_value, String down_payment, String loan_insurance,
			String interest_rate, String loan_tenure, String one_time_expenses, String property_taxes,
			String home_insurance, String maintenance_expenses, String expected_result) throws IOException {
		
		logger=report.createTest("Home Loan Emi Calculation Test:");
		
		invokeBrowser("firefox");
		PageBaseClass pageBase= new PageBaseClass(driver,logger); 
		PageFactory.initElements(driver, pageBase);
		landingPage= pageBase.openApplication();
		homeLoanEmiPage= landingPage.clickHomeLoanEmiLink();
		//homeLoanEmiPage.calculateHomeLoanEmi("100000","0","10000","9.5","20","10","0.25","0.05","-2");
		homeLoanEmiPage.calculateHomeLoanEmi(home_value,down_payment,loan_insurance, interest_rate, loan_tenure, one_time_expenses, property_taxes, home_insurance, maintenance_expenses);
		
		logger.log(Status.INFO, "Finding Total Payment Result:");
		String actual_result = TotalPayment(prop.getProperty("TotalPayment_2"));
		//Assert.assertEquals(expected_result, actual_result);
		logger.log(Status.PASS, "Total Payment is: "+ actual_result);
		
		writeExcelData(System.getProperty("user.dir") + "\\Resources\\Repositiries\\homeLoanEmi.xlsx",
				prop.getProperty("Label_Xpath2"), prop.getProperty("Row_Xpath2"), prop.getProperty("Col_Xpath2"));
		
		flushReports();
		
	}
	
	@DataProvider(name = "homeloanemi")
	public Object[][] homeloanemiData() {
		Object[][] arrayObject = getExcelData(
				System.getProperty("user.dir") + "\\Resources\\Repositiries\\TestDataEmiCalculator.xlsx", "Scenario2", 11, 11);
		return arrayObject;
	}
	
	@Test(dataProvider = "loancalculator")
	public void loanCalculator(String loan_amount, String interest_rate, String loan_tenure,
			String fees_charges, String expected_result) throws IOException {
		
		logger=report.createTest("Loan Calculator Test:");
		
		invokeBrowser("firefox");
		PageBaseClass pageBase= new PageBaseClass(driver,logger); 
		PageFactory.initElements(driver, pageBase);
		landingPage= pageBase.openApplication();
		loanCalculatorPage=landingPage.clickLoanCalculatorLink();
		//loanCalculatorPage.calculateLoan("1000000","9.5","6","0");
		loanCalculatorPage.calculateLoan(loan_amount, interest_rate, loan_tenure, fees_charges);
		
		logger.log(Status.INFO, "Finding Total Payment Result:");
		String actual_result = TotalPayment(prop.getProperty("TotalPayment_3"));
		AssertJUnit.assertEquals(expected_result, actual_result);
		logger.log(Status.PASS, "Total Payment is: "+ actual_result);
		
		
		writeExcelData(System.getProperty("user.dir") + "\\Resources\\Repositiries\\loanCalculator.xlsx",
				prop.getProperty("Label_Xpath3"), prop.getProperty("Row_Xpath3"), prop.getProperty("Col_Xpath3"));
		
		flushReports();
	}
	
	
	@DataProvider(name = "loancalculator")
	public Object[][] loancalculatorData() {
		Object[][] arrayObject = getExcelData(
				System.getProperty("user.dir") + "\\Resources\\Repositiries\\TestDataEmiCalculator.xlsx", "Scenario3", 4, 6);
		return arrayObject;
	}
	
}

