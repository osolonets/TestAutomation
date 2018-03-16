package Utils.Helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverInstantiator
{
    private WebDriver driver;

    protected WebDriver getDriver(String site, String browser)
    {
        setDriver(site, browser);
        return driver;
    }

    private void setDriver(String site, String browser)
    {
        DesiredCapabilities capabilities = setCapabilities(site, browser);

        if (browser.equalsIgnoreCase("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver","src/main/resources/ChromeDriver/chromedriver.exe");
            driver = new ChromeDriver(capabilities);
        }
        else if (browser.equalsIgnoreCase("FireFox"))
        {
            System.setProperty("webdriver.gecko.driver","src/main/resources/GeckoDriver/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("InternetExplorer"))
        {
            System.setProperty("webdriver.ie.driver","src/main/resources/IEDriver/IEDriverServer.exe");
            driver = new InternetExplorerDriver(capabilities);
        }
        else if (browser.equalsIgnoreCase("Edge"))
        {
            System.setProperty("webdriver.edge.driver","src/main/resources/EdgeDriver/MicrosoftWebDriver.exe");
            driver = new EdgeDriver(capabilities);
        }

        else throw new RuntimeException("Browser type must be specified");
    }

    private DesiredCapabilities setCapabilities(String site, String browser)
    {
        if (browser.equalsIgnoreCase("Chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            //options = setChromeExtensions(options, site);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return capabilities;
        }
        else if (browser.equalsIgnoreCase("InternetExplorer"))
        {
            return DesiredCapabilities.internetExplorer();
        }
        else if (browser.equalsIgnoreCase("Edge"))
        {
            return DesiredCapabilities.edge();
        }
        throw new RuntimeException("Browser type must be specified");
    }

    private ChromeOptions setChromeExtensions(ChromeOptions options, String site)
    {
        File pdfReader = new File("src/main/resources/ChromeDriver/extension_1_5_294.crx");

        if (site.equalsIgnoreCase("REX"))
        {
            options.addExtensions(pdfReader);
            return options;
        }
        else options.addArguments("--disable-extensions");
        return options;
    }

    private void setSauceLabs()
    {
        DesiredCapabilities capabilities;
        String username = "George4638";
        String password = "6452faeb-82a5-423d-b098-2f6c07d55809";
        String URL = "https://"+username+":"+password+"@ondemand.saucelabs.com:443/wd/hub";

        capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("version", "50.0");

        try
        {
            driver = new RemoteWebDriver(new URL(URL), capabilities);
        } catch (MalformedURLException e)
        {
            throw new RuntimeException("Fail to instantiate remote web driver", e);
        }
    }

    private WebDriver setUpGrid(String browserType)
    {
        ChromeOptions options;
        WebDriver driver;
        DesiredCapabilities capabilities;

        if (browserType.equalsIgnoreCase("Chrome"))
        {
            options = new ChromeOptions();
            options.addExtensions(new File("src/main/resources/ChromeDriver/extension_1_5_294.crx"));
            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            try
            {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                return driver;
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        else if (browserType.equalsIgnoreCase("InternetExplorer"))
        {
            try
            {
                capabilities = DesiredCapabilities.internetExplorer();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                return driver;
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        else if (browserType.equalsIgnoreCase("Edge"))
        {
            try
            {
                capabilities = DesiredCapabilities.edge();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                return driver;
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Browser type is not specified");
    }

    private AppiumDriver<WebElement> setAppiumDriver(DesiredCapabilities capabilities)
    {
        AppiumDriver<WebElement> driver;
        try
        {
            driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            return driver;
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        throw new RuntimeException("Browser type must be specified");
    }
}
