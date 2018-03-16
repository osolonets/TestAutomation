package PageObjects.Mckissock;

import Utils.Modules;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends LoginPage {

    WebDriver driver;
    public RegistrationPage(){}
    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how= How.ID, using="ctl00_MasterContent_registerNowButton")
    public WebElement registerNewUserBtn;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepOne_textboxFirstName")
    public WebElement firstName;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepOne_textboxMiddleInitial")
    public WebElement middleInitial;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepOne_textboxLastName")
    public WebElement lastName;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepOne_textboxDayPhone")
    public WebElement phone;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepOne_submitButton")
    public WebElement continueFirstRegistrationBtn;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepTwo_textboxUsername")
    public WebElement userNameRegistrationPage;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepTwo_textboxPassword")
    public WebElement passwordRegistrationPage;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepTwo_textboxConfirmPassword")
    public WebElement confirmPasswordRegistrationPage;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepTwo_submitButton")
    public WebElement continueSecondRegistrationBtn;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepThree_textboxBirthDate")
    public WebElement dateOfBirth;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepThree_textboxEmailAddress")
    public WebElement emailAdress;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepThree_textboxAddress")
    public WebElement adress;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepThree_textboxCity")
    public WebElement city;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepThree_dropDownListCountry")
    public WebElement country;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepThree_dropDownListState")
    public WebElement state;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepThree_textboxZipCode")
    public WebElement zipCode;

    @FindBy(how= How.ID, using="ctl00_MasterContent_ucStepThree_textboxConfirmPassword")
    public WebElement createAccountBtn;



    public String registerANewUser()    {
        String userName = Modules.generateUserName("MC");

       return userName;
    }
}
