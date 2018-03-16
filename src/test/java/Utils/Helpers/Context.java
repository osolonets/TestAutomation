package Utils.Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class Context
{
    private static InheritableThreadLocal<Context> threadLocal = new InheritableThreadLocal<Context>()
    {
        @Override
        protected Context initialValue()
        {
            return new Context();
        }
    };

    private WebDriver driver;
    private WebDriverWait shortWait;
    private WebDriverWait longWait;
    private SoftAssert softAssert;
    private String baseURL;
    private String URLStart = "www";

    private Context(){}

    public static Context getInstance()
    {
        return threadLocal.get();
    }

    public static void init(String site, String browserType)
    {
        threadLocal.get().setContext(site, browserType);
    }

    private void setContext(String site, String browser)
    {
        baseURL = Helper.getURL(site);
        driver = new DriverInstantiator().getDriver(site, browser);
        configureDriver();
        setWait();
        setSoftAssert();
    }

    private void configureDriver()
    {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    private void setWait()
    {
        shortWait = new WebDriverWait(getDriver(), 2);
        longWait = new WebDriverWait(getDriver(), 30);
    }

    public WebDriverWait getShortWait()
    {
        return shortWait;
    }

    public WebDriverWait getLongWait()
    {
        return longWait;
    }

    public String getBaseURL()
    {
        return "https://"+URLStart+"."+baseURL;
    }

    public void setURLStart(String URLStart)
    {
        if (!this.URLStart.equals(URLStart) && !URLStart.isEmpty())
        this.URLStart = URLStart;
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public SoftAssert getSoftAssert()
    {
        return softAssert;
    }

    public void setSoftAssert()
    {
        softAssert = new SoftAssert();
    }

    public void tearDown()
    {
        getDriver().quit();
    }
}
