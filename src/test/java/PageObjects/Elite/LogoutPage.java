package PageObjects.Elite;

import Utils.Helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends Elite
{
    private static final String LOGOUT_PAGE = "/student/logout";

    @FindBy(css = ".view.clearfix.ng-scope")
    WebElement messageContainer;

    public LogoutPage(String profession, String state)
    {
        super("/"+ Helper.getStateAbbreviation(state)+LOGOUT_PAGE, profession, false);
        verifyPageIsLoaded(messageContainer, "Good bye");
    }
}
