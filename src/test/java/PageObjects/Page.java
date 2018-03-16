package PageObjects;

import PageObjects.REX.Helium.Helium;
import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Context;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page
{
    protected Context context;
    private String currentPage;

    protected void lookForLoadingContainer() {};

    protected Page(String currentPage, String URLStart, boolean entryPoint)
    {
        context = Context.getInstance();
        if (this instanceof Helium && !entryPoint)
            this.lookForLoadingContainer();
        init(currentPage, URLStart, entryPoint);
    }

    private void init(String currentPage, String URLStart, boolean entryPoint)
    {
        String page = this.getClass().getSimpleName();
        AllureReporter.logStep("Open "+page.substring(0, page.indexOf("Page"))+" "+page.substring(page.indexOf("Page")));
        context.setURLStart(URLStart);
        this.currentPage = currentPage;
        if (entryPoint)
            context.getDriver().navigate().to(getCurrentPage());
        verifyURL(getCurrentPage());
        parse();
    }

    private void verifyURL(String URLToVerify)
    {
        try
        {
            new WebDriverWait(context.getDriver(), 10).until(ExpectedConditions.urlContains(URLToVerify));
        }
        catch (TimeoutException e)
        {
            throw new TimeoutException("Failed to load "+this.getClass().getSimpleName(), e);
        }
    }

    protected void verifyPageIsLoaded(WebElement element)
    {
        Action.softAssert(context.getSoftAssert(), WaitUntil.visibilityOf(element),
                "Verify that " + this.getClass().getSimpleName() + " is loaded (specified element is present)");
    }

    protected void verifyPageIsLoaded(WebElement element, String text)
    {
        Action.softAssert(context.getSoftAssert(), WaitUntil.elementContainsTextIgnoreCase(element, text),
                "Verify that "+this.getClass().getSimpleName()+" is loaded (specified text is present)");
    }

    private void parse()
    {
        PageFactory.initElements(context.getDriver(), this);
    }

    protected String getCurrentPage() {
        return context.getBaseURL() + currentPage;
    }

    protected WebDriver getDriver()
    {
        return context.getDriver();
    }
}
