package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;



public class HomeLoanEmiPage extends PageBaseClass {

	public HomeLoanEmiPage(WebDriver driver, ExtentTest logger) {
		// TODO Auto-generated constructor stub
		super(driver,logger);
	}

   @FindBy(xpath="//*[@id=\'homeprice\']")
   public WebElement homeValue_TxtBox;
   
   @FindBy(xpath="//*[@id=\'downpayment\']")
   public WebElement downPayment_TxtBox;
   
   @FindBy(xpath="//*[@id=\'homeloaninsuranceamount\']")
   public WebElement loanInsurance_TxtBox;
   
   @FindBy(xpath="//*[@id=\'homeloaninterest\']")
   public WebElement interestRate_TxtBox;
   
   @FindBy(xpath="//*[@id=\'homeloanterm\']")
   public WebElement loanTenure_TxtBox;
   
   @FindBy(xpath="//*[@id=\'onetimeexpenses\']")
   public WebElement oneTimeExpense_TxtBox;
   
   @FindBy(xpath="//*[@id=\'propertytaxes\']")
   public WebElement propertyTaxes_TxtBox;
   
   @FindBy(xpath="//*[@id=\'homeinsurance\']")
   public WebElement homeInsurance_TxtBox;
   
   @FindBy(xpath="//*[@id=\'maintenanceexpenses\']")
   public WebElement maintenanceExpenses_TxtBox;
   
   
   public void calculateHomeLoanEmi(String home_value, String down_payment, String loan_insurance,
			String interest_rate, String loan_tenure, String one_time_expenses, String property_taxes,
			String home_insurance, String maintenance_expenses) {
	   
	     logger.log(Status.INFO, "Entering Home Value");
		 clearField(homeValue_TxtBox);
		 addValues(homeValue_TxtBox, home_value);
		 logger.log(Status.PASS, "Home Value(HV) Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Dowm Payment");
		 clearField(downPayment_TxtBox);
		 addValues(downPayment_TxtBox, down_payment);
		 logger.log(Status.PASS, "Down Payment(Margin) Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Loan Insurance");
		 clearField(loanInsurance_TxtBox);
		 addValues(loanInsurance_TxtBox, loan_insurance);
		 logger.log(Status.PASS, "Loan Insurance(LI) Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Interest Rate");
		 clearField(interestRate_TxtBox);
		 addValues(interestRate_TxtBox, interest_rate);
		 logger.log(Status.PASS, "Interest Rate Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Loan Tenure");
		 clearField(loanTenure_TxtBox);
		 addValues(loanTenure_TxtBox, loan_tenure);
		 logger.log(Status.PASS, "Loan Tenure Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering One Time Expenses");
		 clearField(oneTimeExpense_TxtBox);
		 addValues(oneTimeExpense_TxtBox, one_time_expenses);
		 logger.log(Status.PASS, "One Time Expenses Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Property Taxes");
		 clearField(propertyTaxes_TxtBox);
		 addValues(propertyTaxes_TxtBox, property_taxes);
		 logger.log(Status.PASS, "Property Taxes Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Home Insurance");
		 clearField(homeInsurance_TxtBox);
		 addValues(homeInsurance_TxtBox, home_insurance);
		 logger.log(Status.PASS, "Home Insurance Entered Successfully");
		 
		 logger.log(Status.INFO, "Entering Maintenance Expences");
		 clearField(maintenanceExpenses_TxtBox);
		 addValues(maintenanceExpenses_TxtBox, maintenance_expenses);
		 logger.log(Status.PASS, "Maintenance Expences Entered Successfully");
		 
		 waitForPageLoad();
   
   
   }
    

}
