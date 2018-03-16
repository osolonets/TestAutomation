package PageObjects.REX.Helium;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends Helium
{
    private static final String CHECK_PAGE = "/checkout";

    By CCNumber = By.id("cardNumber");
    By CVC = By.id("cardCvc");
    By orderedItemTitle = By.cssSelector("strong.ng-binding");

    @FindBy(id = "checkoutPayment")
    WebElement paymentMethodSelect;

    @FindBy(css = "button.btn.btn-success.btn-block")
    WebElement continueButton;

    @FindBy (css = ".col-xs-12.total")
    WebElement grandTotal;

    @FindBy(css = "div.col-lg-12.item-box")
    WebElement cartSummary;

    @FindBy(xpath = "//span[text()='Payment Information']")
    WebElement header;

    @FindBy(css = "")
    WebElement billingAddress;

    public CheckoutPage()
    {
        super(CHECK_PAGE, false);
        verifyPageIsLoaded(header);
    }

    public void selectCCAsPaymentMethod()
    {
        Action.selectItemByText(paymentMethodSelect, "Credit Card", "credit card as a payment method");
    }

    public void fillOutCCCredentials()
    {
        setCCNumber();
        setSecurityCode();
    }

    public ConfirmationPage proceedToOrderConfirmation()
    {
        clickContinueButton();
        return new ConfirmationPage();
    }

    private void setCCNumber()
    {
        String cc = Helper.getUserInfo().get("cc");
        Action.sendKeysToElement(getDriver().findElement(CCNumber), cc, "CC number: "+cc);
    }

    private void setSecurityCode()
    {
        String cvc = Helper.getUserInfo().get("cvc");
        Action.sendKeysToElement(getDriver().findElement(CVC), cvc, "security code: "+cvc);
    }

    private void clickContinueButton()
    {
        Action.clickOnElement(continueButton, "'Continue' button");
    }

    public WebElement getGrandTotal()
    {
        return grandTotal;
    }

    public WebElement getCartSummary()
    {
        return cartSummary;
    }

    public WebElement getBillingAddress()
    {
        return billingAddress;
    }

    public void applyZeroDiscount()
    {
        AllureReporter.logStep("Apply 100% discount");
        getDriver().get(context.getBaseURL() + "/addtocart/33c45d8f-4071-4ff5-bdd2-40df72ba8b5e");
    }
}