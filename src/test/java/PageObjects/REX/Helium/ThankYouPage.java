package PageObjects.REX.Helium;

import Utils.Helpers.Action;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ThankYouPage extends Helium
{
    private static final String CHECK_PAGE = "/thankyou";

    private By items = By.cssSelector("div.row.noMargin.ng-scope");
    private By nestedPurchasedItems = By.cssSelector("div.col-sm-11");

    @FindBy(css = "a.btn.btn-success.btn-block")
    WebElement goToMyHomePageButton;

    @FindBy(css = "td.receipt-border.right")
    WebElement purchasedItems;

    @FindBy(css = "td.receipt-border.left")
    WebElement billingAddress;

    @FindBy(css = ".col-xs-6.thankyou-total")
    WebElement total;

    @FindBy(xpath = "//h1[contains(text(),'Receipt')]")
    WebElement header;

    public ThankYouPage()
    {
        super(CHECK_PAGE, false);
        verifyPageIsLoaded(header);
    }

    public DashboardPage goToDashboard()
    {
        clickGoToMyHomePageButton();
        return new DashboardPage();
    }

    private void clickGoToMyHomePageButton()
    {
        Action.clickOnElement(goToMyHomePageButton, "'Go to my homepage' button");
    }

    public List<String> getItems()
    {
        List<String> list = new ArrayList<>();
        List<WebElement> nestedCourses;
        for (WebElement element : purchasedItems.findElements(items))
        {
            if ((nestedCourses = element.findElements(nestedPurchasedItems)).size() > 0)
            {
                for (WebElement nested : nestedCourses)
                {
                    list.add(nested.getText().replace(".", "").toLowerCase());
                }
            }
            else
            {
                if (!element.findElement(By.cssSelector("div.col-xs-12 > strong"))
                        .getText().equalsIgnoreCase("Test Promo Code 100% Discount."))
                {
                    list.add(element.findElement(By.cssSelector("div.col-xs-12 > strong")).getText().replace(".", "").toLowerCase());
                }
            }
        }
        return list;
    }

    public WebElement getTotal()
    {
        return total;
    }

    public Boolean verifyBillingAddress(List<String> address)
    {
        for (String line : address)
        {
            if (!WaitUntil.elementContainsTextIgnoreCase(billingAddress, line))
                return false;
        }
        return true;
    }
}
