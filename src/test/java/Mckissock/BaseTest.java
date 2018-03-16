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

    @BeforeClass
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(UtilMckissock.getWaitTime(), TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() throws Exception {
        //driver.quit();
    }
}