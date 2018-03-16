package PageObjects.Elite.SellPages;

import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class DentalPage extends SellPage
{
    @FindBy(css = ".pkg-body")
    List<WebElement> packageContainers;

    @FindBy(css = ".neck-container.ng-scope")
    WebElement bigElementOnThePage;

    protected DentalPage()
    {
        super();
        verifyPageIsLoaded(bigElementOnThePage , Helper.getEliteProperties().getEliteProfession());
    }

    protected String addItemToCart()
    {
        if (WaitUntil.listOfElementsIsNotEmpty(packageContainers))
        {
            return parsePackageContainers(packageContainers);
        }
        return parsePackageContainers(null);
    }

    protected void closePopUp() {}
}
