/*
Existing promo code is not working for this profession
 */
package PageObjects.Elite.SellPages;

import Utils.Helpers.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SocialWorkPage extends SellPage
{
    @FindBy(css = ".premium-courses-hero.ng-scope")
    WebElement bigElementOnThePage;

    protected SocialWorkPage()
    {
        super();
        verifyPageIsLoaded(bigElementOnThePage);
    }

    protected String addItemToCart()
    {
        return parsePackageContainers(null);
    }

    protected void waitForCourseListToBeLoaded()
    {
        if (!WaitUntil.listContainsMoreElementsThan(new WebDriverWait(getDriver(), 10), addToCart, 50))
            throw new RuntimeException("Not all courses are displayed at the sell page");
    }

    protected void closePopUp() {}
}
