package PageObjects.Elite.CheckoutPages;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CheckoutPage
{
    private static final String LOGIN_PAGE = "/login";

    @FindBy(css = "div > div > form[name='registerForm']")
    WebElement registerForm;

    @FindBy(css = "div > div > form[name='registerForm'] > div > input[name='email']")
    WebElement emailField;

    @FindBy(css = "div > div > form[name='registerForm'] > div > input[name='pass1']")
    WebElement passwordField;

    @FindBy(css = "div > div > form[name='registerForm'] > div > input[name='pass2']")
    WebElement confirmPasswordField;

    @FindBy(css = "div > div > form[name='registerForm'] > div > [type='submit']")
    WebElement registerBtn;

    public LoginPage(String profession, String state)
    {
        super(LOGIN_PAGE, profession, state, false);
        verifyPageIsLoaded(registerForm);
    }

    public ProfilePage registerANewUser(String profession, String state)
    {
        String userName = Helper.generateUserName(state, "_elite");

        setEmailField(userName);
        setPasswordField(getUserInfo().get("password"));
        setConfirmPasswordField(getUserInfo().get("password"));
        submitRegistrationForm();
//        Helper.rememberNewUser(userName);

        return new ProfilePage(profession, state);
    }

    private void setEmailField(String email)
    {
        Action.sendKeysToElement(emailField, email, "username: "+ email);
    }

    private void setPasswordField(String password)
    {
        Action.sendKeysToElement(passwordField, password, "password: "+password);
    }

    private void setConfirmPasswordField(String password)
    {
        AllureReporter.logStep("Confirm password: "+password);
        Action.sendKeysToElement(confirmPasswordField, password, "");
    }

    private void submitRegistrationForm()
    {
        Action.clickOnElement(registerBtn, "'Register' button");
    }
}