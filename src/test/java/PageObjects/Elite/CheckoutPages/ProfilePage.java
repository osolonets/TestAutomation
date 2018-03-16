package PageObjects.Elite.CheckoutPages;

import Utils.Helpers.Action;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class ProfilePage extends CheckoutPage
{
    private static final String PROFILE_PAGE = "/profile";

    @FindBy(name = "profileForm")
    WebElement infoForm;

    @FindBy(name = "first_name")
    WebElement firstNameField;

    @FindBy(name = "last_name")
    WebElement lastNameField;

    @FindBy(name = "address1")
    WebElement addressField;

    @FindBy(name = "city")
    WebElement cityField;

    @FindBy(name = "profileState")
    WebElement stateDropdown;

    @FindBy(name = "zip")
    WebElement zipField;

    @FindBy(name = "phone")
    WebElement phoneField;

    @FindBy(name = "ssnlast4")
    WebElement SSNField;

    @FindBy(name = "birthDate")
    WebElement birthDateField;

    @FindBy(css = "input[type='checkbox']")
    WebElement licenseCheckbox;

    @FindBy(id = "agree_tos")
    WebElement agreeCheckbox;

    @FindBy(xpath = "//*[contains(text(),'Save & Continue') or contains(@value,'Save & Continue')]")
    WebElement submitBtn;

    public ProfilePage(String profession, String state)
    {
        super(PROFILE_PAGE, profession, state, false);
        verifyPageIsLoaded(infoForm);
    }

    public PaymentPage fillOutUserInfo(String profession, String state)
    {
        populateUserInfoFields(Helper.getUserInfo(), state);
        submitUserInfo();
        return new PaymentPage(profession, state);
    }

    private void populateUserInfoFields(Map<String, String> map, String state)
    {
        Action.sleep(1000); //For some reason sometimes it fails to fill firstName field in quick pace
        setFirstNameField(map.get("firstName"));
        setLastNameField(map.get("lastName"));
        setAddressField(map.get("address"));
        setCityField(map.get("city"));
        setStateDropdown(state);
        setZipField(map.get("zip"));
        setPhoneField(map.get("phoneElite"));
        setSSNField(map.get("eliteSSN"));
        setBirthDateField(map.get("birthDate"));
        setLicenseCheckbox();
        setAgreeCheckbox();
    }

    private void setFirstNameField(String firstName)
    {
        Action.sendKeysToElement(firstNameField, firstName, "first name: "+firstName);
    }

    private void setLastNameField(String lastName)
    {
        Action.sendKeysToElement(lastNameField, lastName, "last name: "+lastName);
    }

    private void setAddressField(String address)
    {
        Action.sendKeysToElement(addressField, address, "address: "+address);
    }

    private void setCityField(String city)
    {
        Action.sendKeysToElement(cityField, city, "city: "+city);
    }

    private void setStateDropdown(String state)
    {
        Action.selectItemByText(stateDropdown, state, "state: "+state);
    }

    private void setZipField(String zip)
    {
        Action.sendKeysToElement(zipField, zip, "zip code: "+zip);
    }

    private void setPhoneField(String phone)
    {
        Action.sendKeysToElement(phoneField, phone, "phone number: "+phone);
    }

    private void setSSNField(String SSN)
    {
        if (SSNField.isDisplayed())
        {
            Action.sendKeysToElement(SSNField, SSN, "SSN: "+SSN);
        }
    }

    private void setBirthDateField(String birthDate)
    {
        if (birthDateField.isDisplayed())
        {
            Action.sendKeysToElement(birthDateField, birthDate, "date of birth: "+birthDate);
        }
    }

    private void setLicenseCheckbox()
    {
        Action.clickOnCheckbox(licenseCheckbox, "'License' checkbox");
    }

    private void setAgreeCheckbox()
    {
        if (WaitUntil.elementIsAttachedToThePage(agreeCheckbox))
        {
            Action.clickOnCheckbox(agreeCheckbox, "'Agree' checkbox");
        }
    }

    private void submitUserInfo()
    {
        Action.clickOnElement(submitBtn, "'Submit' button");
    }
}
