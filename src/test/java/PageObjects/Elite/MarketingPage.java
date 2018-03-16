package PageObjects.Elite;

import PageObjects.Elite.SellPages.SellPage;
import Utils.Helpers.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MarketingPage extends Elite
{
    private static final String MARKET_PAGE = "";

    @FindBy(css = ".stateSelect.customSelect.ng-pristine.ng-invalid.ng-invalid-required")
    WebElement professionDropdown;

    @FindBy(css = ".stateSelect.customSelect.ng-pristine.ng-valid")
    WebElement stateDropdown;

    @FindBy(css = "input[value='Open my Profession']")
    WebElement openMyProfessionBtn;

    @FindBy(id = "blueHeader")
    WebElement header;

    public MarketingPage(boolean entryPoint)
    {
        super(MARKET_PAGE, "", entryPoint);
        verifyPageIsLoaded(header, "The most affordable CE you'll find on the web");
    }

    public void selectProfession(String profession)
    {
        List<WebElement> professions = professionDropdown.findElements(By.tagName("option"));
        for (WebElement p: professions)
        {
            String prof = p.getText();
            if (prof.contains(profession.equalsIgnoreCase("ChildAbuse")?"Nursing":parseProfession(profession)))
            {
                Action.selectItemByText(professionDropdown, prof, "profession: "+prof);
                return;
            }
        }
        throw new RuntimeException("Make sure profession is specified correctly");
    }

    private String parseProfession(String profession)
    {
        StringBuilder builder = new StringBuilder(profession);
        for(int i=1; i<profession.length(); i++)
        {
            if (Character.isUpperCase(profession.charAt(i)))
            {
                builder.insert(i, " ");
            }
        }
        return builder.toString();
    }

    public void selectState(String state)
    {
        Action.selectItemByText(stateDropdown, state, "state: "+state);
    }

    public SellPage proceedToSellPage(String profession)
    {
        Action.clickOnElement(openMyProfessionBtn, "'Open my profession' button");
        return SellPage.getSellPage(profession);
    }
}
