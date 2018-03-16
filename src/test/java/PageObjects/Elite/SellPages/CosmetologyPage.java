package PageObjects.Elite.SellPages;


import Utils.Helpers.Action;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CosmetologyPage extends SellPage
{
    @FindBy(css = ".neck-container.ng-scope")
    WebElement bigElementOnThePage;

    @FindBy(css = ".modal.fade")
    List<WebElement> popUp;

    @FindBy(css = ".no-thanks")
    WebElement closeBtn;

    protected CosmetologyPage()
    {
        super();
        verifyPageIsLoaded(bigElementOnThePage, Helper.getEliteProperties().getEliteProfession());
    }

    protected String addItemToCart()
    {
        return parsePackageContainers(null);
    }

    protected void closePopUp()
    {
        if (WaitUntil.listOfElementsIsNotEmpty(popUp))
        {
            Action.clickOnElement(closeBtn, "");
        }
    }
}