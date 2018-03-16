package PageObjects.REX.MarketingSite;

import PageObjects.REX.Helium.RegistrationPage;
import Utils.Helpers.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SellPage extends WordPressREX
{
    private static final String SELL_PAGE = "/real-estate-license";

    By selectButton = By.cssSelector(".button.color-aqua");
    By columnTitle = By.cssSelector(".column-title");
    By mostPopular = By.cssSelector(".popular-banner");

    @FindBy(css = ".pricing-column-content")
    List<WebElement> columns;

    @FindBy(css = ".columns-container")
    WebElement columnsContainer;

    public SellPage(String state)
    {
        super(SELL_PAGE +"/"+(state.contains(" ")?state.replace(" ", "-").toLowerCase():state.toLowerCase()), false);
        verifyPageIsLoaded(columnsContainer);
    }

    private void selectItemToPurchase(String itemToPurchase)
    {
        for (WebElement element: columns)
        {
            if (element.findElement(columnTitle).getText().contains(itemToPurchase))
            {
                Action.clickOnElement(element.findElement(selectButton), "'Select' button against "+itemToPurchase);
                return;
            }
        }
    }

    public RegistrationPage chooseItemToPurchase(String itemToPurchase)
    {
        selectItemToPurchase(itemToPurchase);
        return new RegistrationPage(false);
    }

    public String getItemToPurchase()
    {
        for (WebElement element: columns)
        {
            if (element.findElements(mostPopular).size() != 0)
            {
                return element.findElement(columnTitle).getText();
            }
        }
        return columns.get(new Random().nextInt(columns.size())).findElement(columnTitle).getText();
    }
}
