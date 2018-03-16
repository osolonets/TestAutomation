package Utils.ExpectedConditions;

import Utils.Helpers.Context;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class TextPresentOnThePage implements ExpectedCondition<Boolean>
{
    private String textToBeFound;
    private String firstString;
    private String secondString;

    public TextPresentOnThePage(String textToBeFound)
    {
        this.textToBeFound = textToBeFound;
        apply(Context.getInstance().getDriver());
    }

    public TextPresentOnThePage(String firstString, String secondString)
    {
        this.firstString = firstString;
        this.secondString = secondString;
        apply(Context.getInstance().getDriver());
    }

    public Boolean apply(WebDriver driver)
    {
        String source = driver.getPageSource().toUpperCase();

        if (textToBeFound != null && !textToBeFound.isEmpty())
        {
            return source.contains(textToBeFound.toUpperCase());
        }
        else
        {
            return source.contains(firstString.toUpperCase())
                    && source.contains(secondString.toUpperCase());
        }
    }
}