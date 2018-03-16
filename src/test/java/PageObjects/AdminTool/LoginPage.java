package PageObjects.AdminTool;

import Mckissock.UtilMckissock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage() {
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "userNameTextBox")
    public WebElement login;

    @FindBy(how = How.ID, using = "passWordTextBox")
    public WebElement password;

    @FindBy(how = How.ID, using = "signInButton")
    public WebElement signInBtn;


    public void login(WebDriver driver, LoginPage loginPage) throws InterruptedException {


        loginPage.login.clear();
        loginPage.login.sendKeys(UtilMckissock.getUserNameAdmTool());
        loginPage.password.clear();
        loginPage.password.sendKeys(UtilMckissock.getPasswdAdmTool());
        loginPage.signInBtn.click();
    }

}
