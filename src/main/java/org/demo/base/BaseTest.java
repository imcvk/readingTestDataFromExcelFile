package org.demo.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.demo.utility.ExcelUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {
  public static WebDriver driver;
  public static Properties properties;
  public static ExtentReports extentReports;
  public static ExtentTest extentTest;

  public BaseTest () {
    try {
      FileInputStream inputStream = new FileInputStream ("src/main/resources/testProperties.properties");
      properties = new Properties ();
      properties.load (inputStream);
    } catch (Exception e) {
      e.getCause ();
    }
  }

//  @DataProvider(name = "excelData")
//  public Object[][] getData() throws IOException {
//    ExcelUtils.setExcelFile("path/to/excel.xlsx", "Sheet1");
//    int rowCount = ExcelUtils.sheet.getPhysicalNumberOfRows();
//    int colCount = ExcelUtils.sheet.getRow(0).getPhysicalNumberOfCells();
//
//    Object[][] data = new Object[rowCount - 1][colCount];
//    for (int i = 1; i < rowCount; i++) {
//      for (int j = 0; j < colCount; j++) {
//        data[i - 1][j] = ExcelUtils.getCellData(i, j);
//      }
//    }
//    return data;
//  }

  @Parameters("browserName")
  @BeforeTest
  public void setup (ITestContext context, @Optional("chrome") String browserName) {
    switch(browserName.toLowerCase ()) {
      case "chrome":
        driver = new ChromeDriver ();
        break;
      case "edge":

        driver = new EdgeDriver ();
        break;
      case "firefox":

        driver = new FirefoxDriver ();
        break;
      default:
        System.out.println ("Browser is invalid");
        break;
    }
    driver.manage ().window ().maximize ();
    Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities ();
    String device = capabilities.getBrowserName () + " " + capabilities.getBrowserVersion ().substring (0, capabilities.getBrowserVersion ().indexOf ("."));
    extentTest = extentReports.createTest (context.getName ());
    extentTest.assignDevice (device);
  }

  @AfterTest
  public void teardown () {
    driver.quit ();
  }

  @BeforeSuite
  public void initReports () {
    ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter ("./target/reports/alltestcases.html");
    extentSparkReporter.config ().setReportName ("All test report");
    ExtentSparkReporter reportForFailedTestCases = new ExtentSparkReporter ("./target/reports/failed.html");
    reportForFailedTestCases.filter ().statusFilter ().as (new Status[]{Status.FAIL}).apply ();
    reportForFailedTestCases.config ().setReportName ("Failed test cases");
    extentReports = new ExtentReports ();
    extentReports.attachReporter (extentSparkReporter, reportForFailedTestCases);
    extentReports.setSystemInfo ("OS", System.getProperty ("os.name"));
    extentReports.setSystemInfo ("Java Version", System.getProperty ("java.version"));
  }

  @AfterSuite
  public void generateReports () {
    extentReports.flush ();
  }

  @AfterMethod
  public void checkStatus (ITestResult result, ITestContext context, Method method) {
    if (result.getStatus () == ITestResult.FAILURE) {
      String screenShotPath = captureScreenshot (method.getName ());
      extentTest.addScreenCaptureFromPath (screenShotPath);
      extentTest.fail (result.getThrowable ());
    } else {
      extentTest.pass (method.getName () + " is passed");
    }
  }

  public String captureScreenshot (String fileName) {
    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    File sourceFile = takesScreenshot.getScreenshotAs (OutputType.FILE);
    File destFile = new File ("target/Screenshots/" + fileName + ".jpg");
    try {
      FileUtils.copyFile (sourceFile, destFile);
    } catch (IOException e) {
      e.getCause ();
    }
    System.out.println ("Screenshot saved successfully");
    String path = destFile.getAbsolutePath ();
    System.out.println (path);
    return path;
  }
}
