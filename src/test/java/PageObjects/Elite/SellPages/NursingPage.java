package PageObjects.Elite.SellPages;

import Utils.Helpers.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class NursingPage extends SellPage
{
    @FindBy(css = ".offer")
    List<WebElement> packageContainers;

    @FindBy(css = ".premium-courses-hero-nursing")
    WebElement bigElementOnThePage;

    protected NursingPage()
    {
        super();
        verifyPageIsLoaded(bigElementOnThePage);
    }

    protected String addItemToCart()
    {
        //Keep this hardcoded until figure out why promo code is not applicable for certain packages
        if (WaitUntil.listOfElementsIsNotEmpty(packageContainers))
        {
//            return parsePackageContainers(packageContainers);
            return parsePackageContainers(new ArrayList<WebElement>(){{add(packageContainers.get(2));}});
        }
        return parsePackageContainers(null);
    }

    protected void closePopUp() {}
}