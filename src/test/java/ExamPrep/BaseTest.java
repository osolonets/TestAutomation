package ExamPrep;

import ExamPrep.UtilExamPrep;
import Utils.FileParsers.ExcelUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public String baseUrl;


    @BeforeClass
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(UtilExamPrep.getWaitTime(), TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() throws Exception {
        //driver.quit();
    }
}