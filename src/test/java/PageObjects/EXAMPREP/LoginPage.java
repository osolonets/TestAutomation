package PageObjects.EXAMPREP;

import ExamPrep.UtilExamPrep;
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

    @FindBy(how = How.XPATH, using =
            "html/body/ion-app/ng-component/ion-nav/page-login/ion-content/div[2]/ion-grid/ion-row/ion-col[1]/form/ion-row[2]/ion-col/ion-input/input")
    public WebElement userName;

	@FindBy(how= How.XPATH, using="html/body/ion-app/ng-component/ion-nav/page-login/ion-content/div[2]/ion-grid/ion-row/ion-col[1]/form/ion-row[4]/ion-col/ion-input/input")
    public WebElement password;

    @FindBy(how= How.CSS, using=".button.button-md.button-default.button-default-md.button-block.button-block-md")
    public WebElement btnLogin;

    public void login(WebDriver driver, LoginPage loginPage) throws InterruptedException {
        Thread.sleep(3000);
        loginPage.userName.clear();
        loginPage.userName.sendKeys(UtilExamPrep.getUserName());
        loginPage.password.clear();
        loginPage.password.sendKeys(UtilExamPrep.getPASSWD());
        loginPage.btnLogin.click();
    }


}
