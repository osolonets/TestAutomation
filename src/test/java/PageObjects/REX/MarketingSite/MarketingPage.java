package PageObjects.REX.MarketingSite;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MarketingPage extends WordPressREX
{
    private static final String MARKET_PAGE = "";

    @FindBy(css = ".modal-inner")
    List<WebElement> welcomePopUp;

    @FindBy(xpath = "//h1[text()='Launch your real estate career']")
    WebElement header;

    @FindBy(xpath = "*//label[text()='Select State']/..//div[@class='dropdown-container']")
    WebElement stateList;

    @FindBy(xpath = "*//label[text()='Select Education']/..//div[@class='dropdown-container']")
    WebElement educationList;

    @FindBy(css = "input[value='Find Courses']")
    WebElement findCoursesBtn;

    public MarketingPage(boolean entryPoint)
    {
        super(MARKET_PAGE, entryPoint);
        verifyPageIsLoaded(header);
        handleWelcomePopUp();
    }

    public SellPage goToTheSellPage(String state)
    {
        selectState(state);
        selectEducationType();
        submitSelection();
        return new SellPage(state);
    }

    private void selectState(String state)
    {
        AllureReporter.logStep("Expend dropdown list of states");
        Action.clickOnElement(stateList, "");
        if (!WaitUntil.elementContainsTextIgnoreCase(stateList, state))
            throw new RuntimeException(state+" is not in the dropdown list");
        AllureReporter.logStep("Select state: " + state);
        Action.clickOnElementWithoutScrollingTheScreen(stateList.findElement(By.xpath(".//li/span[text()='"+state+"']")), "");
    }

    private void selectEducationType()
    {
        String education = "Get My License";

        AllureReporter.logStep("Expend dropdown list of educations");
        Action.clickOnElement(educationList, "");
        if (!WaitUntil.elementContainsTextIgnoreCase(educationList, education))
            throw new RuntimeException(education+" is not in the dropdown list");
        AllureReporter.logStep("Select '"+education+"'");
        Action.clickOnElementWithoutScrollingTheScreen(educationList.findElement(By.xpath(".//li/span[text()='"+education+"']")), "");
    }

    private void submitSelection()
    {
        Action.clickOnElement(findCoursesBtn, "'Find Courses'");
    }

    private void handleWelcomePopUp()
    {
        if (welcomePopUp.size() != 0)
        {
            AllureReporter.logStep("Close pop-up");
            Action.clickOnElement(welcomePopUp.get(0).findElement(By.cssSelector(".button-text")), "");
        }
    }
}
