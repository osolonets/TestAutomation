package PageObjects.REX.Helium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends Helium
{
    private static final String PROFILE_PAGE = "/profile";

    @FindBy(xpath = "//h1[text()='My Profile']")
    WebElement header;

    public ProfilePage()
    {
        super(PROFILE_PAGE, false);
        verifyPageIsLoaded(header);
    }
}
