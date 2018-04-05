package Mckissock;

import PageObjects.AdminTool.StudentsPage;
import PageObjects.Mckissock.CheckOutPage;
import PageObjects.Mckissock.MainPage;
import PageObjects.Mckissock.RegistrationPage;
import PageObjects.AdminTool.LoginPage;
import Utils.Modules;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class McKissockNewUserTest extends BaseTest {

    private RegistrationPage registrationPage;
    String baseUrl = UtilMckissock.getBaseUrl();
    String adminToolUrl = UtilMckissock.getAdminToolUrl();
    String targetURL = UtilMckissock.getBaseUrl();
    String userNameCreated = "TestAlex";
    private MainPage mainPage;



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
        Thread.sleep(1000);
        registrationPage.loginMainPage.click();
        registrationPage.registerNewUserBtn.click();
        registrationPage.firstName.sendKeys(firstName);
        registrationPage.lastName.sendKeys(lastName);
        registrationPage.phone.sendKeys(phone);
        Thread.sleep(1000);
        registrationPage.continueFirstRegistrationBtn.click();
        Thread.sleep(1000);
        //registrationPage.continueFirstRegistrationBtn.click();
        userNameCreated = Modules.generateUserName("mc");
        Thread.sleep(2000);
        registrationPage.userNameRegistrationPage.sendKeys(userNameCreated);
        registrationPage.passwordRegistrationPage.sendKeys(passwordRegistrationPage);
        registrationPage.confirmPasswordRegistrationPage.sendKeys(passwordRegistrationPage);
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registrationPage.continueSecondRegistrationBtn);
        Thread.sleep(1000);
        registrationPage.continueSecondRegistrationBtn.click();
        registrationPage.dateOfBirth.sendKeys(dateOfBirth);
        registrationPage.emailAdress.sendKeys(emailAdress);
        registrationPage.adress.sendKeys(adress);
        registrationPage.city.sendKeys(city);
        Select sel = new Select(registrationPage.country);
        sel.selectByValue(country);
        Thread.sleep(1000);
        sel = new Select(registrationPage.state);
        sel.selectByValue(state);
        registrationPage.zipCode.sendKeys(zipCode);
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registrationPage.createAccountBtn);
        Thread.sleep(5000);
        registrationPage.createAccountBtn.click();
        System.out.println(userNameCreated);
        driver.manage().deleteAllCookies();

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
        Thread.sleep(3000);
        studentPage.userInfoStudentTypeEditBTN.click();
        Thread.sleep(4000);
        Select sel = new Select(studentPage.userInfoStudentTypeDropDown);
        sel.selectByValue("Test Account");
        Thread.sleep(4000);
        studentPage.userInfoSaveStudentTypeBTN.click();
        Thread.sleep(4000);
        //studentPage.SignOut.click();
        Thread.sleep(1000);
        //driver.close();


    }
    @Test(priority = 2, dataProvider = "MainPageTestData")
    public void buyCourseTestCase(String typeOfEducation,
                                  String type,
                                  String state,
                                  String firstPageAssertion,
                                  String secondPageAssertion
                                                                        ) throws InterruptedException {
        driver.get(baseUrl + "/" + typeOfEducation + "/" + type + "/");
        mainPage = new MainPage(driver);
        Thread.sleep(1000);
        Assert.assertEquals(mainPage.loadingPageBeforeSelectState.getText(), firstPageAssertion);
        mainPage.selectState.click();
        mainPage.clickOnState(driver, state);
        mainPage.selectStateContinueBtn.click();
        Thread.sleep(1000);
        Assert.assertEquals(mainPage.loadingPageAfterSelectState.getText(), state+secondPageAssertion);
        System.out.println(mainPage.loadingPageAfterSelectState.getText());
        System.out.println(driver.findElements(By.xpath("//div[@id='filtered-output']/div")).size());
        Thread.sleep(1000);
        String randCoursePrice = mainPage.buyRandCourse(driver);
        System.out.println(randCoursePrice);
        double  price = Double.valueOf(randCoursePrice.substring(1,randCoursePrice.length()));
        System.out.println(price);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.promoCodeTextBox.sendKeys(UtilMckissock.getPromoCode100());
        checkOutPage.applyPromoCodeBTN.click();
        Thread.sleep(2000);
        Assert.assertTrue(checkOutPage.discountShowTextBox.getText().contains(Double.toString(price)));
        checkOutPage.checkOutBtn.click();
    }

    @Test(priority = 3, dataProvider = "CheckOutTestData")
    public void checkOutTestCase(String nameOnCard,
                                 String creditCardType,
                                 String cardNumber,
                                 String expirationDateMonth,
                                 String expirationDateYear,
                                 String securityCode
                                                                ) throws InterruptedException {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        //Login
        checkOutPage.checkOutLoginUserNameTextBox.sendKeys(userNameCreated);
        checkOutPage.checkOutLoginPasswordTextBox.sendKeys(UtilMckissock.getPASSWD());
        checkOutPage.signInCheckOutPageBtn.click();
        checkOutPage.paymentInfoNameOnCardTextBox.sendKeys(nameOnCard);
        checkOutPage.paymentInfoCreditCardNumberTextBox.sendKeys(cardNumber);
        checkOutPage.paymentInfoSecurityCodeTextBox.sendKeys(securityCode);
        checkOutPage.paymentInfoContinueBtn.click();
        System.out.println(checkOutPage.paymentInfoUserDetailsElements.getText());









    }
}