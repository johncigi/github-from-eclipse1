package testCases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import pageClasses.HomeLoanEmiPage;
import pageClasses.LandingPage;

public class HomeLoanEmiTest extends BaseTestClass {
	
	LandingPage landingPage;
	HomeLoanEmiPage homeLoanEmiPage;
	
	@Test(dataProvider = "homeloanemi")
	public void homeLoanEmiCalculator(String home_value, String down_payment, String loan_insurance,
			String interest_rate, String loan_tenure, String one_time_expenses, String property_taxes,
			String home_insurance, String maintenance_expenses, String expected_result) throws IOException {
		
		logger=report.createTest("Home Loan Emi Calculation Test:");
		
		invokeBrowser("chrome");
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

}
