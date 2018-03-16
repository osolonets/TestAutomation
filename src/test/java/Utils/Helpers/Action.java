package Utils.Helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Action
{
    private static void click(WebElement element, String log, boolean scroll)
    {
        if (!log.isEmpty())
        {
            AllureReporter.logStep("Click "+log);
        }
        try
        {
            try
            {
                Context.getInstance().getShortWait().until(ExpectedConditions.elementToBeClickable(element));
                if (scroll) scrollToElement(element);
                element.click();
            }
            catch (WebDriverException e)
            {
                if (scroll) scrollToElement(element);
                Actions actions = new Actions(Context.getInstance().getDriver());
                actions.moveToElement(element).click().perform();
            }
        }
        catch (NullPointerException e)
        {
            throw new RuntimeException("Unable to locate element", e);
        }
    }

    public static void clickOnElement(WebElement element, String log)
    {
        click(element, log, true);
    }

    public static void clickOnElementWithoutScrollingTheScreen(WebElement element, String log)
    {
        click(element, log, false);
    }

    public static void scrollToElement(WebElement element)
    {
        Dimension elD = element.getSize();
        Point elP = element.getLocation();
        Dimension dimension = Context.getInstance().getDriver().manage().window().getSize();
        int screenX = dimension.getWidth();
        int screenY = dimension.getHeight();
        int X = elP.getX()-(screenX+elD.getWidth())/2;
        int Y = elP.getY()-(screenY+elD.getHeight())/2;

        ((JavascriptExecutor)Context.getInstance().getDriver()).executeScript("window.scrollTo("+X+","+Y+");");
    }

    public static void clickOnCheckbox(WebElement element, String log)
    {
        if (!element.isSelected())
        {
            if (!log.isEmpty())
            {
                AllureReporter.logStep("Click "+log);
            }
            Context.getInstance().getShortWait().until(ExpectedConditions.elementToBeClickable(element)).click();
            if (!WaitUntil.elementToBeSelected(element))
                throw new RuntimeException("Failed to select the checkbox");
        }
    }

    public static void sendKeysToElement(WebElement element, String keys, String log)
    {
        if (!log.isEmpty())
        {
            AllureReporter.logStep("Enter "+log);
        }
        Context.getInstance().getShortWait().until(ExpectedConditions.visibilityOf(element)).clear();
        scrollToElement(element);
        element.sendKeys(keys);
    }

    public static void selectItemByText(WebElement element, String key, String log)
    {
        if (!log.isEmpty())
        {
            AllureReporter.logStep("Select "+log);
        }
        try {
            Context.getInstance().getShortWait().until(ExpectedConditions.visibilityOf(element));
            scrollToElement(element);
            Context.getInstance().getShortWait().until(ExpectedConditions.textToBePresentInElement(element, key));
        }catch (WebDriverException e)
        {
            throw new RuntimeException("Failed to select element in the list", e);
        }
        new Select(element).selectByVisibleText(key);
    }

    public static void switchWindow()
    {
        WebDriver driver = Context.getInstance().getDriver();
        for (String window : driver.getWindowHandles())
        {
            driver.switchTo().window(window);
        }
    }

    public static void sleep(long timeOut)
    {
        try
        {
            Thread.sleep(timeOut);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void AssertTrue(boolean condition, String message)
    {
        AllureReporter.logStep(message.contains(":")?message.substring(message.indexOf(":")+2):message);
        Assert.assertTrue(condition, message);
    }

    public static void softAssert(SoftAssert softAssert, boolean condition, String message)
    {
        AllureReporter.logStep(message.contains(":")?message.substring(message.indexOf(":")+2):message);
        softAssert.assertTrue(condition, message);
    }

    public static byte[] takeScreenshot()
    {
        byte[] bytes = null;
        try
        {
            bytes = ((TakesScreenshot)Context.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
        }
        catch (Exception e)
        {
            AllureReporter.logError("there was a problem with screenshot capturing");
            e.printStackTrace();
        }
        return bytes;
    }
}
