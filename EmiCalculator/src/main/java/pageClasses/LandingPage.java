package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class LandingPage extends PageBaseClass {
	
	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
	
	@FindBy(xpath= "//*[@id=\'car-loan\']/a")
	public WebElement carLoanLink;
	
	public CarLoanPage clickCarLoanLink() {
		logger.log(Status.INFO, "Clicking the Car Loan Tab");
		carLoanLink.click();
		logger.log(Status.PASS, "Clicked Car Loan Tab");
		CarLoanPage carLoanPage= new CarLoanPage(driver,logger);
		PageFactory.initElements(driver, carLoanPage);
		return carLoanPage;
	}
	
	
	@FindBy(xpath="//*[@id=\'menu-item-3294\']/a")
	public WebElement homeLoanEmiLink;
	
	public HomeLoanEmiPage clickHomeLoanEmiLink() {
		logger.log(Status.INFO, "Clicking the Home Loan Emi Calculator Tab");
		homeLoanEmiLink.click();
		logger.log(Status.PASS, "Clicked Successfully");
		HomeLoanEmiPage homeLoanEmiPage= new HomeLoanEmiPage(driver,logger);
		PageFactory.initElements(driver, homeLoanEmiPage);
		return homeLoanEmiPage;
	}
	
	@FindBy(xpath="//*[@id=\'menu-item-dropdown-2696\']")
	public WebElement dropDown;
	
	@FindBy(xpath="//header/div[1]/nav[1]/div[2]/div[1]/ul[1]/li[1]/ul[1]/li[2]/a[1]")
	public WebElement loanCalculatorBtn;
	
	public LoanCalculatorPage clickLoanCalculatorLink() {
		logger.log(Status.INFO, "Clicking Loan Calculator Tab");
		dropDown.click();
		waitForPageLoad();
		loanCalculatorBtn.click();
		logger.log(Status.PASS, "Clicked Successfully");
		LoanCalculatorPage loanCalculatorPage= new LoanCalculatorPage(driver,logger);
		PageFactory.initElements(driver, loanCalculatorPage);
		return loanCalculatorPage;
	}

}
