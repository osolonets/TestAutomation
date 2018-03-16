package PageObjects.REX.Helium;

import PageObjects.VC5.SplashPage;
import Utils.Helpers.Action;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CourseRunnerPage extends Helium
{
    private static final String RUNNER_PAGE = "/runner";
    private By chapterRows = By.cssSelector("table.table.table-bordered > tbody > tr.ng-scope");

    @FindBy(css = "a.btn.btn-block.btn-success.green.ng-scope")
    WebElement openButton;

    @FindBy(css = "span.content.bg-success")
    WebElement courseProgress;

    @FindBy(css = ".courseProgressContainer")
    WebElement courseContainer;

    public CourseRunnerPage()
    {
        super(RUNNER_PAGE, false);
        verifyPageIsLoaded(courseContainer);
        verifyPageIsLoaded();
    }

    private void verifyPageIsLoaded()
    {
        lookForLoadingContainer();
        boolean var = WaitUntil.listContainsMoreElementsThan(new WebDriverWait(getDriver(), 3), chapterRows, 3);

        Action.softAssert(context.getSoftAssert(), var, "Runner Page: Verify that list of chapters is greater than 3");
    }

    protected void clickOpenButton()
    {
        Action.clickOnElement(openButton, "'Open' button");
    }

    public SplashPage openSplashPage()
    {
        clickOpenButton();
        return new SplashPage();
    }

    public WebElement getCourseProgress()
    {
        return courseProgress;
    }
}
