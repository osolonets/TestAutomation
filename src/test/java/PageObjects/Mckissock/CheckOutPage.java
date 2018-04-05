package PageObjects.Mckissock;

import Utils.Modules;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends MainPage {

    WebDriver driver;
    public CheckOutPage(){}
    public CheckOutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_discountTextBox")
    public WebElement promoCodeTextBox;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_applyLink")
    public WebElement applyPromoCodeBTN;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_discountLabel")
    public WebElement discountShowTextBox;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_checkOutButton")
    public WebElement checkOutBtn;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_userNameTextBox")
    public WebElement checkOutLoginUserNameTextBox;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_passWordTextBox")
    public WebElement checkOutLoginPasswordTextBox;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_loginBtn")
    public WebElement signInCheckOutPageBtn;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_cardNameTextBox")
    public WebElement paymentInfoNameOnCardTextBox;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_cardTypeDropDown")
    public WebElement paymentInfoCreditCardTypeSelect;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_cardNumberTextBox")
    public WebElement paymentInfoCreditCardNumberTextBox;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_monthDropDown")
    public WebElement paymentInfoExpirationDateMonthDropDown;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_monthDropDown")
    public WebElement paymentInfoExpirationDateYearDropDown;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_txtSecurityCode")
    public WebElement paymentInfoSecurityCodeTextBox;

    @FindBy(how= How.ID, using="ctl00_ctl00_MasterContent_MasterContent_checkOutButtonRight")
    public WebElement paymentInfoContinueBtn;

    @FindBy(how= How.ID, using="genericBackground")
    public WebElement paymentInfoUserDetailsElements;






}
