package Utils.Helpers;

import Utils.ExpectedConditions.ElementContainsTextIgnoreCase;
import Utils.ExpectedConditions.ListIsNotEmpty;
import Utils.ExpectedConditions.TextPresentOnThePage;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class WaitUntil
{
    public static Boolean listOfElementsIsNotEmpty(List<WebElement> list)
    {
        try
        {
            Context.getInstance().getShortWait().until(new ListIsNotEmpty(list));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return true;
    }

    public static Boolean listContainsMoreElementsThan(WebDriverWait wait, By by, int number)
    {
        List<WebElement> var;

        try
        {
            var = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, number));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return var != null && var.size() > number;
    }

    public static Boolean invisibilityOfElementLocated(By by)
    {
        try
        {
            Context.getInstance().getLongWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return true;
    }

    public static Boolean invisibilityOfElements(List<WebElement> list)
    {
        try
        {
            Context.getInstance().getShortWait().until(ExpectedConditions.invisibilityOfAllElements(list));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return true;
    }

    static public Boolean visibilityOf(WebElement element)
    {
        WebElement var;

        try
        {
            var = Context.getInstance().getShortWait().until(ExpectedConditions.visibilityOf(element));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return var != null;
    }

    public static Boolean visibilityOfElementLocated(WebDriverWait wait, By by)
    {
        WebElement var;

        try
        {
            var = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return var != null;
    }

    public static Boolean visibilityOfElementLocated(By by)
    {
        WebElement var;

        try
        {
            var = Context.getInstance().getShortWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return var != null;
    }

    public static Boolean elementContainsTextIgnoreCase(WebElement element, String text)
    {
        Boolean var;

        try
        {
            var = Context.getInstance().getShortWait().until(new ElementContainsTextIgnoreCase(element, text));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return var;
    }

    public static Boolean visibilityOfNestedElementsLocated(WebElement element, By by)
    {
        List<WebElement> var;

        try
        {
            var = Context.getInstance().getShortWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, by));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return var != null && var.size() > 0;
    }

    public static Boolean pageContainsText(WebDriverWait wait, String s1, String s2)
    {
        try
        {
            if (s2.isEmpty())
            {
                wait.until(new TextPresentOnThePage(s1));
            }
            else wait.until(new TextPresentOnThePage(s1, s2));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return true;
    }

    public static Boolean elementIsAttachedToThePage(WebElement element)
    {
        try
        {
            element.getTagName();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
        return true;
    }

    public static Boolean elementToBeSelected(WebElement element)
    {
        try
        {
            Context.getInstance().getShortWait().until(ExpectedConditions.elementToBeSelected(element));
        }
        catch (TimeoutException e)
        {
            return false;
        }
        return true;
    }
}
