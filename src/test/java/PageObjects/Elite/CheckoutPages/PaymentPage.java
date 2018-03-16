package PageObjects.Elite.CheckoutPages;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.WaitUntil;
import Utils.Helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends CheckoutPage
{
    private static final String PAYMENT_PAGE = "/card";
    private WebElement paymentForm;

    @FindBy(name = "referral_code")
    WebElement discountField;

    @FindBy(xpath = "//*[text()='Apply Code']")
    WebElement applyDiscountBtn;

    @FindBy(xpath = "//button[text()='Continue']")
    WebElement continueBtn;

    @FindBy(css = ".checkout-card-wrapper")
    WebElement paymentForm_Type1;

    @FindBy(css = ".checkout-cardinfo")
    WebElement paymentForm_Type2;

    @FindBy(css = ".order-container")
    WebElement orderInfo_Type1;

    @FindBy(css = ".checkout-courselist")
    WebElement orderInfo_Type2;

    public PaymentPage(String profession, String state)
    {
        super(PAYMENT_PAGE, profession, state, false);
        verifyPageIsLoaded();
        verifyCartStillHasItems();
    }

    private void verifyPageIsLoaded()
    {
        if (WaitUntil.elementIsAttachedToThePage(paymentForm_Type1))
        {
            verifyPageIsLoaded(paymentForm_Type1);
            paymentForm = paymentForm_Type1;
        }
        else
        {
            verifyPageIsLoaded(paymentForm_Type2);
            paymentForm = paymentForm_Type2;
        }
    }

    public void applyZeroDiscount(String profession, String state)
    {
        AllureReporter.logStep("Apply 100% discount");
        setDiscountField();
        applyDiscount();
        verifyDiscountWasApplied(profession, state);
    }

    private void setDiscountField()
    {
        Action.sendKeysToElement(discountField, Helper.getUserInfo().get("discountElite"), "");
    }

    private void applyDiscount()
    {
        Action.clickOnElement(applyDiscountBtn, "");
    }

    private void verifyDiscountWasApplied(String profession, String state)
    {
        if (WaitUntil.elementContainsTextIgnoreCase(paymentForm, "Referral code is not valid or has already been applied"))
        {
            throw new RuntimeException("Failed to apply discount code for "+profession+" "+state);
        }
    }

    public boolean verifyCartTotalAfterZeroDiscountWasApplied()
    {
        if (WaitUntil.visibilityOf(orderInfo_Type1))
        {
            return WaitUntil.elementContainsTextIgnoreCase(orderInfo_Type1, "$0.00");
        }
        else
        {
            return WaitUntil.elementContainsTextIgnoreCase(orderInfo_Type2, "$0.00");
        }
    }

    public ReceiptPage completeCheckout(String profession, String state)
    {
        clickContinueBtn();
        return new ReceiptPage(profession, state);
    }

    private void clickContinueBtn()
    {
        Action.clickOnElement(continueBtn, "'Continue' button");
    }

    private void verifyCartStillHasItems()
    {
        if (paymentForm.getText().contains("There are no items in your cart"))
            throw new RuntimeException("Checkout failed");
    }
}