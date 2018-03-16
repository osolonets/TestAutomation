package PageObjects.REX.Helium;

import Utils.Helpers.Action;
import Utils.Helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

import static Utils.Helpers.AllureReporter.logStep;

public class RegistrationPage extends Helium
{
    private static final String REG_PAGE = "/register";

    @FindBy(id = "registerEmail")
    WebElement emailField;

    @FindBy(id = "registerEmailConfirm")
    WebElement confirmEmailField;

    @FindBy(id = "registerPwdHidden")
    WebElement passwordField;

    @FindBy(id = "registerFirstName")
    WebElement firstNameField;

    @FindBy(id = "registerLastName")
    WebElement lastNameField;

    @FindBy(id = "registerAddress")
    WebElement streetAddressField;

    @FindBy(id = "registerCity")
    WebElement cityField;

    @FindBy(id = "registerState")
    WebElement stateField;

    @FindBy(id = "registerZip")
    WebElement zipCodeField;

    @FindBy(id = "registerTelephone")
    WebElement phoneNumberField;

    @FindBy(id = "registerSubmit")
    WebElement createAccountBtn;

    @FindBy(css = ".row.stepsImgArea")
    WebElement stepsImage;

    @FindBy(css = ".row.sectionTitle")
    WebElement header;

    public RegistrationPage(boolean entryPoint)
    {
        super(REG_PAGE, entryPoint);
        verifyPageIsLoaded(header, "New Customer Registration");
    }

    private void setEmailField(String email)
    {
        Action.sendKeysToElement(emailField, email, "email: "+email);
    }

    private void setConfirmEmailField(String confirmEmail)
    {
        Action.sendKeysToElement(confirmEmailField, confirmEmail, "email: "+confirmEmail);
    }

    private void setPasswordField(String password)
    {
        Action.sendKeysToElement(passwordField, password, "password: "+password);
    }

    private void setFirstNameField(String firstName)
    {
        Action.sendKeysToElement(firstNameField, firstName, "first name: "+firstName);
    }

    private void setLastNameField(String lastName)
    {
        Action.sendKeysToElement(lastNameField, lastName, "last name: "+lastName);
    }

    private void setStreetAddressField(String streetAddress)
    {
        Action.sendKeysToElement(streetAddressField, streetAddress, "street address: "+streetAddress);
    }

    private void setCityField(String city)
    {
        Action.sendKeysToElement(cityField, city, "city: "+city);
    }

    private void setState(String state)
    {
        Action.selectItemByText(stateField, state, "state: "+state);
    }

    private void setZipCodeField(String zipCode)
    {
       Action.sendKeysToElement(zipCodeField, zipCode, "zip code: "+zipCode);
    }

    private void setPhoneNumberField(String phoneNumber)
    {
       Action.sendKeysToElement(phoneNumberField, phoneNumber, "phone number: "+phoneNumber.substring(1));
    }

    private void submitRegistrationForm()
    {
        Action.clickOnElement(createAccountBtn, "'Create account' button");
    }

    public CheckoutPage registerANewUser(String state)
    {
        String userName = Helper.generateUserName(state, "_rex");

        populateRegForm(Helper.getUserInfo(), userName, state);
        submitRegistrationForm();
//        Helper.rememberNewUser(userName);

        return new CheckoutPage();
    }

    private void populateRegForm(Map<String, String> map, String userName, String state)
    {
        setEmailField(userName);
        setConfirmEmailField(userName);
        setPasswordField(map.get("password"));
        setFirstNameField(map.get("firstName"));
        setLastNameField(map.get("lastName"));
        setStreetAddressField(map.get("address"));
        setCityField(map.get("city"));
        setZipCodeField(map.get("zip"));
        setPhoneNumberField(map.get("phoneRex"));
        setState(state);
    }

    public void addToCartViaURL(String sku)
    {
        logStep("Add to cart, sku: "+sku);
        getDriver().get(context.getBaseURL()+"/addtocart/"+sku);
    }

    public WebElement getStepsImage()
    {
        return stepsImage;
    }
}
