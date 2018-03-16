package Mckissock;

import PageObjects.AdminTool.StudentsPage;
import PageObjects.Mckissock.RegistrationPage;
import PageObjects.AdminTool.LoginPage;
import Utils.Modules;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class McKissockNewUserTest extends BaseTest {

    private RegistrationPage registrationPage;
    String baseUrl = UtilMckissock.getBaseUrl();
    String adminToolUrl = UtilMckissock.getAdminToolUrl();
    String targetURL = UtilMckissock.getBaseUrl();
    String userNameCreated = "TestAlex";


    @Test(priority = 0, dataProvider = "RegistrationMcTestData")
    public void registrationTestCase(String firstName,
                                     String lastName,
                                     String phone,
                                     String passwordRegistrationPage,
                                     String dateOfBirth,
                                     String emailAdress,
                                     String adress,
                                     String city,
                                     String country,
                                     String state,
                                     String zipCode) throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(UtilMckissock.getWaitTime(), TimeUnit.SECONDS);
        registrationPage = new RegistrationPage(driver);
        registrationPage.loginMainPage.click();
        registrationPage.registerNewUserBtn.click();
        registrationPage.firstName.sendKeys(firstName);
        registrationPage.lastName.sendKeys(lastName);
        registrationPage.phone.sendKeys(phone);
        registrationPage.continueFirstRegistrationBtn.click();
        userNameCreated = Modules.generateUserName("mc");
        registrationPage.userNameRegistrationPage.sendKeys(userNameCreated);
        registrationPage.passwordRegistrationPage.sendKeys(passwordRegistrationPage);
        registrationPage.confirmPasswordRegistrationPage.sendKeys(passwordRegistrationPage);
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registrationPage.continueSecondRegistrationBtn);
        Thread.sleep(5000);
        registrationPage.continueSecondRegistrationBtn.click();
        registrationPage.dateOfBirth.sendKeys(dateOfBirth);
        registrationPage.emailAdress.sendKeys(emailAdress);
        registrationPage.adress.sendKeys(adress);
        registrationPage.city.sendKeys(city);
        Select sel = new Select(registrationPage.country);
        sel.selectByValue(country);
        Thread.sleep(5000);
        sel = new Select(registrationPage.state);
        sel.selectByValue(state);
        registrationPage.zipCode.sendKeys(zipCode);
//        Thread.sleep(5000);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registrationPage.createAccountBtn);
//        Thread.sleep(5000);
//        registrationPage.createAccountBtn.click();

    }
    /*
    Changing Student account to testing account in Admin Tool
     */
    @Test(priority = 1)
    public void changeUserTypeToTestUserTestCase() throws InterruptedException {
        driver.get(adminToolUrl);
        LoginPage loginAdmTool = new LoginPage(driver);
        loginAdmTool.login(driver,loginAdmTool);
        StudentsPage studentPage = new StudentsPage(driver);
        studentPage.students.click();
        studentPage.searchStudentsUsernameTextBox.sendKeys(userNameCreated);
        studentPage.searchStudentsSearchBTN.click();
        studentPage.searchresultStudentPage.click();
        Thread.sleep(2000);
        studentPage.userInfoStudentTypeEditBTN.click();
        Thread.sleep(3000);
        Select sel = new Select(studentPage.userInfoStudentTypeDropDown);
        sel.selectByValue("Test Account");
        studentPage.userInfoSaveStudentTypeBTN.click();
        //studentPage.SignOut.click();


    }
}