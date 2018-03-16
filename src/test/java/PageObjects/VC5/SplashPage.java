package PageObjects.VC5;

import Utils.Helpers.Action;
import Utils.Helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SplashPage extends VC5Page
{
    private static final String SPLASH_PAGE = "/vc5/rex-splash-v4";

    @FindBy(id = "imageButtonOpen")
    WebElement openButton;

    @FindBy(id = "textboxSignature")
    WebElement signatureField;

    @FindBy(id = "buttonSplashSignature")
    WebElement signButton;

    @FindBy(xpath = "//h1[contains(text(),'Before you begin')]")
    WebElement header;

    public SplashPage()
    {
        super(SPLASH_PAGE, false);
        verifyPageIsLoaded(header);
    }

    private void clickOpenButton()
    {
        Action.clickOnElement(openButton, "'Open course' button");
    }

    public VC5CoursePage openCourseInVC5()
    {
        clickOpenButton();
        return new VC5CoursePage();
    }

    private void setSignatureField()
    {
        String name = Helper.getUserInfo().get("firstName");
        Action.sendKeysToElement(signatureField, name, "username: "+ name);
    }

    private void clickSignNameButton()
    {
        Action.clickOnElement(signButton, "'Sign name and continue' button");
    }

    public TermsAndConditionsPage signOrientation()
    {
        setSignatureField();
        clickSignNameButton();
        return new TermsAndConditionsPage();
    }
}
