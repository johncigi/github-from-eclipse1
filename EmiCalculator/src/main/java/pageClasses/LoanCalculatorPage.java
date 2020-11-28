package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class LoanCalculatorPage extends PageBaseClass {

	public LoanCalculatorPage(WebDriver driver, ExtentTest logger) {
		
		super(driver, logger);
	}
	
	@FindBy(xpath="//*[@id=\'loanamount\']")
	public WebElement loanAmount_TxtBox;
	
	@FindBy(xpath="//*[@id=\'loaninterest\']")
	public WebElement interestRate_TxtBox;
	
	@FindBy(xpath="//*[@id=\'loanterm\']")
	public WebElement loanTenure_TxtBox;
	
	@FindBy(xpath="//*[@id=\'loanfees\']")
	public WebElement loanFees_TxtBox;
	
	
	public void calculateLoan(String loan_amount, String interest_rate, String loan_tenure,
			String fees_charges) {
		
		 logger.log(Status.INFO, "Entering Loan Amount");
		 clearField(loanAmount_TxtBox);
		 addValues(loanAmount_TxtBox, loan_amount);
		 logger.log(Status.PASS, "Loan Amount Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Interest Rate");
		 clearField(interestRate_TxtBox);
		 addValues(interestRate_TxtBox, interest_rate);
		 logger.log(Status.PASS, "Interest Rate Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Loan Tenure");
		 clearField(loanTenure_TxtBox);
		 addValues(loanTenure_TxtBox, loan_tenure);
		 logger.log(Status.PASS, "Loan Tenure Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Loan Tenure");
		 clearField(loanFees_TxtBox);
		 addValues(loanFees_TxtBox, fees_charges);
		 logger.log(Status.PASS, "Loan Tenure Entered Successfully");
		 
		 waitForPageLoad();
		
	}

}
