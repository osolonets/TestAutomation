package PageObjects.Elite.CheckoutPages;

import PageObjects.Elite.DashboardPage;
import Utils.Helpers.Action;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceiptPage extends CheckoutPage
{
    private static final String RECEIPT_PAGE = "/transaction";

    @FindBy(xpath = "//a[text()='Go to My Courses']")
    WebElement submitBtn;

    @FindBy(css = ".transaction-message > h3")
    WebElement transactionInfo_Type1;

    @FindBy(css = "article[ng-show='transaction && transaction.Approved']")
    WebElement transactionInfo_Type2;

    public ReceiptPage(String profession, String state)
    {
        super(RECEIPT_PAGE, profession, state, false);
        verifyPageIsLoaded();
    }

    private void verifyPageIsLoaded()
    {
        if (WaitUntil.elementIsAttachedToThePage(transactionInfo_Type1))
        {
            verifyPageIsLoaded(transactionInfo_Type1, "Thank you! Your payment was successful");
        }
        else
        {
            verifyPageIsLoaded(transactionInfo_Type2, "Your payment has been approved");
        }
    }

    public DashboardPage proceedToUserDashboard(String profession, String state)
    {
        Action.clickOnElement(submitBtn, "'Submit' button");
        return new DashboardPage(profession, state);
    }
}
