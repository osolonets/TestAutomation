package PageObjects.Mckissock;

import Mckissock.UtilMckissock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    public LoginPage(){}
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

        @FindBy(how = How.XPATH, using =  "//*[@id='menu-item-14']/a")
    public WebElement loginMainPage;

    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/header/div/div[2]/nav[1]/ul/li[4]/a")
    public WebElement logOutMainPage;

    @FindBy(how = How.ID, using = "ctl00_MasterContent_userNameTextBox")
    public WebElement userName;

	@FindBy(how= How.ID, using="ctl00_MasterContent_passWordTextBox")
    public WebElement password;

    @FindBy(how= How.ID, using="ctl00_MasterContent_signInButton")
    public WebElement btnLogin;

    @FindBy(how= How.ID, using="ctl00_MasterContent_registerNowButton")
    public WebElement registerNewUserBtn;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepOne_textboxFirstName")
    public WebElement firstName;


    public void login(WebDriver driver, LoginPage loginPage) throws InterruptedException {
        loginPage.loginMainPage.click();
        Thread.sleep(3000);
        loginPage.userName.clear();
        loginPage.userName.sendKeys(UtilMckissock.getUserName());
        loginPage.password.clear();
        loginPage.password.sendKeys(UtilMckissock.getPASSWD());
        loginPage.btnLogin.click();
    }
    public void login(WebDriver driver, LoginPage loginPage, String user, String passw) throws InterruptedException {
        loginPage.loginMainPage.click();
        Thread.sleep(3000);
        loginPage.userName.clear();
        loginPage.userName.sendKeys(user);
        loginPage.password.clear();
        loginPage.password.sendKeys(passw);
        loginPage.btnLogin.click();
    }
}
