package testCases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import pageClasses.LandingPage;
import pageClasses.LoanCalculatorPage;

public class LoanCalculatorTest extends BaseTestClass {
	
	LandingPage landingPage;
	LoanCalculatorPage loanCalculatorPage;
	
	@Test(dataProvider = "loancalculator")
	public void loanCalculator(String loan_amount, String interest_rate, String loan_tenure,
			String fees_charges, String expected_result) throws IOException {
		
		logger=report.createTest("Loan Calculator Test:");
		
		invokeBrowser("chrome");
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
