package Mckissock;

import Utils.FileParsers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public String baseUrl;


    @DataProvider(name = "RegistrationMcTestData")
    public Object[][] registrationMcTestData() throws Exception {
        ExcelUtility.setExcelFile(UtilMckissock.getTestDataFilePath(), "RegistrationTest");
        Object[][] testData = ExcelUtility.getTestData("RegistrationTestData");
        return testData;
    }
    @DataProvider(name = "CheckOutTestData")
    public Object[][] checkOutTestData() throws Exception {
        ExcelUtility.setExcelFile(UtilMckissock.getTestDataFilePath(), "CheckOutTest");
        Object[][] testData = ExcelUtility.getTestData("CheckoutTestData");
        return testData;
    }
    @DataProvider(name = "MainPageTestData")
    public Object[][] mainPageTestData() throws Exception {
        ExcelUtility.setExcelFile(UtilMckissock.getTestDataFilePath(), "MainPageTest");
        Object[][] testData = ExcelUtility.getTestData("MainPageTestData");
        return testData;
    }
    @BeforeSuite
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(UtilMckissock.getWaitTime(), TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() throws Exception {
        //driver.quit();
    }
}