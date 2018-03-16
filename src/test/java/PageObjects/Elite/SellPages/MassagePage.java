package PageObjects.Elite.SellPages;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class MassagePage extends SellPage
{
    @FindBy(css = ".individual-courses")
    WebElement bigElementOnThePage;

    @FindBy(css = ".modal.fade")
    List<WebElement> popUp;

    @FindBy(css = ".close-button")
    WebElement closeBtn;

    private static int count = 0;

    protected MassagePage()
    {
        super();
        try
        {
            Assert.assertTrue(verifyPageIsLoaded());
        }
        catch (AssertionError e)
        {
            if (count > 0)
            {
                throw new RuntimeException("Failed to open "+
                        Helper.getEliteProperties().getEliteProfession()+
                        " sell page for "+Helper.getEliteProperties().getEliteState(), e);
            }
            AllureReporter.logStep("Resolve intermediate page of "+
                    Helper.getEliteProperties().getEliteProfession()+" "+
                    Helper.getEliteProperties().getEliteState());
            new IntermediatePage();
            new MassagePage();
        }
        finally
        {
            count = 0;
        }
    }

    private boolean verifyPageIsLoaded()
    {
        boolean var = WaitUntil.visibilityOf(bigElementOnThePage);
        if (var)
        {
            AllureReporter.logStep("Verify that " + this.getClass().getSimpleName() + " is loaded (specified element is present)");
        }
        return var;
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

    private class IntermediatePage extends SellPage
    {
        //This is hardcoded class for IL
        @FindBy(css = ".price-table > tfoot > tr > td:nth-child(2) > a")
        WebElement homeStudyBtn;

        @FindBy(css = ".pick-the-package.ng-scope")
        WebElement body;

        private IntermediatePage()
        {
            super();
            verifyPageIsLoaded(body, "Pick the CE that fits your needs");
            selectClassroomHours();
            count++;
        }

        public void selectClassroomHours()
        {
            Action.clickOnElement(homeStudyBtn, "'Select' button against home study");
        }

        protected String addItemToCart() {return "";}

        protected void closePopUp() {}
    }
}
