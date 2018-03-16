package PageObjects.REX.Helium;

import Utils.Helpers.Action;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ConfirmationPage extends Helium
{
    private static final String CONFIRM_PAGE = "/checkout";

    @FindBy(css = "button.btn.btn-success.btn-block")
    WebElement continueButton;

    @FindBy (css = ".col-xs-12.total")
    WebElement grandTotal;

    @FindBy(css = "div.col-lg-12.item-box")
    WebElement cartSummary;

    @FindBy(xpath = "//span[text()='Confirm Order']")
    WebElement header;

    @FindBy(css = ".col-md-6.noPaddingLeft.column")
    WebElement billingAddress;

    public ConfirmationPage()
    {
        super(CONFIRM_PAGE, false);
        verifyPageIsLoaded(header);
    }

    public ThankYouPage placeOrder()
    {
        clickContinueButton();
        return new ThankYouPage();
    }

    private void clickContinueButton()
    {
        Action.clickOnElement(continueButton, "'Complete checkout' button");
    }

    public WebElement getCartSummary()
    {
        return cartSummary;
    }

    public WebElement getGrandTotal()
    {
        return grandTotal;
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
