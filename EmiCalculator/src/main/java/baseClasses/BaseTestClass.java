package baseClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtentReportManager;



public class BaseTestClass {

	public WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public Properties prop;
	
	/****************** Invoke Browser 
	 * @throws IOException ***********************/
	public void invokeBrowser(String browserName) throws IOException {

		try {

			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				//System.setProperty("webdriver.chrome.driver", "E:\\Chrome_driver\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				//System.setProperty("webdriver.gecko.driver", "E:\\Firefox_driver\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
		} catch (Exception e) {
			 reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		if(prop == null) {
			prop= new Properties();
			
			try {
				FileInputStream file= new FileInputStream(System.getProperty("user.dir")
						+ "\\Resources\\Repositiries\\config.properties");
				prop.load(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				reportFail(e.getMessage());
			}
			
		}
	}
	
	
	public void flushReports() {
	    report.flush();
		driver.quit();
	    //driver.close();
	}

	
	/***************** Wait Functions in Framework *****************/
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 /******************************************
	 * Writing values in excel
	 *****************************************************/

	public void writeExcelData(String fileName, String Label_Xpath, String Row_Xpath, String Col_Xpath) throws IOException{

		int Row_count = driver.findElements(By.xpath(Row_Xpath)).size();
		int Col_count = driver.findElements(By.xpath(Col_Xpath)).size();

		String first_part = Row_Xpath + "[";
		String second_part = "]/td[";
		String third_part = "]";

		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Data");

		Row row = sheet.createRow(0);
		for (int i = 1; i <= Col_count; i++) {
			row.createCell(i - 1).setCellValue(driver.findElement(By.xpath(Label_Xpath + "[" + i + "]")).getText());
		}

		for (int i = 1; i <= Row_count; i++) {
			row = sheet.createRow(i);
			for (int j = 1; j <= Col_count; j++) {
				String final_xpath = first_part + i + second_part + j + third_part;

				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				row.createCell(j - 1).setCellValue(Table_data);

			}

		}

		FileOutputStream fos = new FileOutputStream(new File(fileName));

		workbook.write(fos);

		fos.close();

		// print message
		System.err.println("");
		System.err.println("************************************************************");
		System.err.println("Data has been stored");
		System.err.println("************************************************************");

	}
	
	/******************************************
	 * Reading values from excel
	 *****************************************************/

	public String[][] getExcelData(String fileName, String sheetName, int totalNoOfRows, int totalNoOfCols) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sh = wb.getSheet(sheetName);

			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols - 1];

			for (int i = 1; i < totalNoOfRows; i++) {

				for (int j = 1; j < totalNoOfCols; j++) {
					arrayExcelData[i - 1][j - 1] = String.valueOf(sh.getRow(i).getCell(j));

				}

			}
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}

		return arrayExcelData;
	}
	
	/********** Total Payment *************/
	public String TotalPayment(String LocatorValue) {
		String Payment = null;
		try {
			WebElement element = driver.findElement(By.xpath(LocatorValue));
			Payment = element.getText();
			System.out.println("Total amount is : " + Payment);
			//reportPass("Total amount will be : " + Payment);

		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return Payment;

	}
	
	
	
	 public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		Assert.fail();
	}

  

}
