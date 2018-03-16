package PageObjects.Elite.CheckoutPages;

import PageObjects.Elite.Elite;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import Utils.Helpers.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public abstract class CheckoutPage extends Elite
{
    @FindBy(css = ".checkout-steps.clearfix")
    WebElement stepsBar_Type1;

    @FindBy(css = "[id^=progressBar]")
    WebElement stepsBar_Type2;

    protected CheckoutPage(String pageURL, String profession, String state, Boolean entryPoint)
    {
        super("/"+ Helper.getStateAbbreviation(state)+"/checkout"+pageURL, profession, entryPoint);
        verifyPageIsLoaded(this.getClass().getSimpleName());
    }

    private void verifyPageIsLoaded(String className)
    {
        boolean var = false;
        if (WaitUntil.visibilityOf(stepsBar_Type1))
        {
            switch (className)
            {
                case "LoginPage": var = verifyCheckoutStepsAreDisplayedCorrectly("active", "inactive", "inactive", "inactive");
                    break;
                case "ProfilePage": var = verifyCheckoutStepsAreDisplayedCorrectly("active", "active", "inactive", "inactive");
                    break;
                case "PaymentPage": var = verifyCheckoutStepsAreDisplayedCorrectly("active", "active", "active", "inactive");
                    break;
                case "ReceiptPage": var = verifyCheckoutStepsAreDisplayedCorrectly("active", "active", "active", "active");
                    break;
            }
        }
        else if (WaitUntil.visibilityOf(stepsBar_Type2))
        {
            switch (className)
            {
                case "LoginPage": var = verifyCheckoutStepsAreDisplayedCorrectly("One");
                    break;
                case "ProfilePage": var = verifyCheckoutStepsAreDisplayedCorrectly("Two");
                    break;
                case "PaymentPage": var = verifyCheckoutStepsAreDisplayedCorrectly("Three");
                    break;
                case "ReceiptPage": var = verifyCheckoutStepsAreDisplayedCorrectly("Four");
                    break;
            }
        }
        Action.softAssert(context.getSoftAssert(), var, "Verify that correct steps bar is being reflected during checkout flow");
    }

    private boolean verifyCheckoutStepsAreDisplayedCorrectly(String step_1, String step_2, String step_3, String step_4)
    {
        boolean var1, var2, var3, var4;
        String stepsBar = ".step-container.checkout-step-";

        var1 = WaitUntil.visibilityOfElementLocated(By.cssSelector(".step-register" + stepsBar + step_1));
        var2 = WaitUntil.visibilityOfElementLocated(By.cssSelector(".step-info" + stepsBar + step_2));
        var3 = WaitUntil.visibilityOfElementLocated(By.cssSelector(".step-payment" + stepsBar + step_3));
        var4 = WaitUntil.visibilityOfElementLocated(By.cssSelector(".step-review" + stepsBar + step_4));

        return var1&&var2&&var3&&var4;
    }

    private boolean verifyCheckoutStepsAreDisplayedCorrectly(String step)
    {
        return WaitUntil.visibilityOfElementLocated(By.id("progressBar" + step));
    }

    protected Map<String, String> getUserInfo()
    {
        return Helper.getUserInfo();
    }
}
