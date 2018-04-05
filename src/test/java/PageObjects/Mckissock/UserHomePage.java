package PageObjects.Mckissock;

import Mckissock.UtilMckissock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UserHomePage {

    WebDriver driver;
    public UserHomePage(){}
    public UserHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/nav/ul/li[6]/a")
    public WebElement myProfileTab;

    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/div/div[3]/div[1]/div[1]/form/div[1]/div[1]/div[1]/input")
    public WebElement myProfileTabAccountInfoFirstName;
    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/div/div[3]/div[1]/div[1]/form/div[1]/div[1]/div[2]/input")
    public WebElement myProfileTabAccountInfoLastName;
    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/div/div[3]/div[1]/div[1]/form/div[1]/div[2]/input")
    public WebElement myProfileTabAccountInfoPhone;
    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/div/div[3]/div[1]/div[1]/form/div[1]/div[3]/input")
    public WebElement myProfileTabAccountInfoEmail;
    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/div/div[3]/div[1]/div[1]/form/div[2]/div[1]/input")
    public WebElement myProfileTabAccountInfoAddress;
    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/div/div[3]/div[1]/div[1]/form/div[2]/div[3]/input")
    public WebElement myProfileTabAccountInfoCity;
    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/div/div[3]/div[1]/div[1]/form/div[2]/div[4]/div[1]/select")
    public WebElement myProfileTabAccountInfoState;
    @FindBy(how = How.XPATH, using =  "//*[@id='ng-app']/body/section/div/div/div[3]/div[1]/div[1]/form/div[2]/div[4]/div[2]/input")
    public WebElement myProfileTabAccountInfoZipCode;


}
