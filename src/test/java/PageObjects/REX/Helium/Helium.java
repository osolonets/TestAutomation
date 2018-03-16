package PageObjects.REX.Helium;

import PageObjects.Page;
import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Helium extends Page
{
    private static final String URLStart = Helper.getREX_URLStart(Helium.class);

    @FindBy(css = ".dropdown-toggle")
    WebElement dropdown;

    @FindBy(linkText = "My Home Page")
    WebElement homePage;

    @FindBy(linkText = "Account Information")
    WebElement accountInfo;

    @FindBy(linkText = "Invoices")
    WebElement invoices;

    @FindBy(linkText = "Log Out")
    WebElement log_out;

    protected Helium(String currentPage, boolean entryPoint)
    {
        super(currentPage, URLStart, entryPoint);
        lookForLoadingContainer();
    }

    protected void lookForLoadingContainer()
    {
        try
        {
            if (WaitUntil.visibilityOfElementLocated(new WebDriverWait(getDriver(), 3), By.cssSelector(".loaderContainer")))
            {
                AllureReporter.logStep("Wait for loading completion");
                if (!WaitUntil.invisibilityOfElementLocated(By.cssSelector(".loaderContainer")))
                    throw new RuntimeException("Helium loading stuck");
            }
        }
        catch (StaleElementReferenceException | NoSuchElementException e)
        {
            e.printStackTrace();
        }
    }

    public LoginPage logout()
    {
        clickDropdownMenu();
        clickLogoutBtn();
        return new LoginPage(false);
    }

    private void clickDropdownMenu()
    {
        Action.clickOnElement(dropdown, "dropdown menu");
    }

    private void clickLogoutBtn()
    {
        Action.clickOnElement(log_out, "'Logout' button");
    }
}
