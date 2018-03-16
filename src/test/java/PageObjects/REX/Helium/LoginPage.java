package PageObjects.REX.Helium;

import Utils.Helpers.Action;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Helium
{
    private static final String LOGIN_PAGE = "/login";

    @FindBy(name = "login")
    WebElement userName;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(css = "button[type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//strong[text()='Customer Login']")
    WebElement header;

    public LoginPage(boolean entryPoint)
    {
        super(LOGIN_PAGE, entryPoint);
        verifyPageIsLoaded(header);
    }

    private void setUserName(String name)
    {
        Action.sendKeysToElement(userName, name, "username: "+name);
    }

    private void setPassword(String psw)
    {
        Action.sendKeysToElement(password, psw, "password: "+psw);
    }

    private void clickSubmitButton()
    {
        Action.clickOnElement(submitButton, "'Log in' button");
    }

    public DashboardPage loginAs(String name, String psw)
    {
        setUserName(name);
        setPassword(psw);
        clickSubmitButton();
        return new DashboardPage();
    }
}