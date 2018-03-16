package PageObjects.VC5;

import Utils.Helpers.Action;
import Utils.Helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TermsAndConditionsPage extends VC5Page
{
    private static final String TC_PAGE = "/VC5/rex-terms-conditions";

    @FindBy(id = "textboxSignature")
    WebElement signatureField;

    @FindBy(id = "buttonSplashSignature")
    WebElement signButton;

    @FindBy(xpath = "//h1[contains(text(),'Terms and Conditions')]")
    WebElement header;

    public TermsAndConditionsPage()
    {
        super(TC_PAGE, false);
        verifyPageIsLoaded(header);
    }

    private void setSignatureField()
    {
        String name = Helper.getUserInfo().get("firstName");
        Action.sendKeysToElement(signatureField, name, "username: "+name);
    }

    private void clickSignButton()
    {
        Action.clickOnElement(signButton, "'Sign name and continue' button");
    }

    public VC5CoursePage signTermsAndConditions()
    {
        setSignatureField();
        clickSignButton();
        return new VC5CoursePage();
    }
}
