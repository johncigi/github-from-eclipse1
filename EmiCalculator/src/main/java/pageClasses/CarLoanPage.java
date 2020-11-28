package pageClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class CarLoanPage extends PageBaseClass {

	public CarLoanPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		
	}
	
	@FindBy(xpath= "//*[@id=\'loanamount\']")
	public WebElement carLoanAmount_TextBox;
	
	@FindBy(xpath= "//*[@id=\'loaninterest\']")
	public WebElement interestRate_TextBox;
	
	@FindBy(xpath= "//*[@id=\'loanterm\']")
	public WebElement loanTenure_TextBox;
	
	/************* Calculating Car Loan 
	 * @throws IOException ************/
	
	public void calculateCarLoan(String carLoanAmount, String interestRate, String loanTenure) throws IOException {
		 
		 logger.log(Status.INFO, "Entering Car Loan Amount");
		 clearField(carLoanAmount_TextBox);
		 addValues(carLoanAmount_TextBox, carLoanAmount);
		 logger.log(Status.PASS, "Car Loan Amount Entered Successfully");
		// waitForPageLoad();
		 
		 logger.log(Status.INFO, "Entering Interest Rate");
		 clearField(interestRate_TextBox);
		 addValues(interestRate_TextBox, interestRate);
		 logger.log(Status.PASS, "Interest Rate Entered Successfully");
		// waitForPageLoad();
		 
		 logger.log(Status.INFO, "Entering Loan Tenure");
		 clearField(loanTenure_TextBox);
		 addValues(loanTenure_TextBox, loanTenure);
		 logger.log(Status.PASS, "Loan Tenure Entered Successfully");
		 
		 waitForPageLoad();
		 
		
	}

}
