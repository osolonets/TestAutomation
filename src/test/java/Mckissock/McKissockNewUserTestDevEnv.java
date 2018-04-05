package Mckissock;

import PageObjects.AdminTool.LoginPage;
import PageObjects.AdminTool.StudentsPage;
import PageObjects.Mckissock.MainPage;
import PageObjects.Mckissock.RegistrationPage;
import PageObjects.Mckissock.UserHomePage;
import Utils.Modules;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class McKissockNewUserTestDevEnv extends BaseTest {

    private RegistrationPage registrationPage;
    private PageObjects.Mckissock.LoginPage loginPage;
    private MainPage mainPage;

    String baseUrl = UtilMckissock.getBaseUrlDevEnv();
    String adminToolUrl = UtilMckissock.getAdminToolUrl();
    String targetURL = UtilMckissock.getBaseUrl();
    String userNameCreated = "TestAlex";
    String loginDevEnv = UtilMckissock.getLoginDevEnvUserName();
    String passwDevEnv = UtilMckissock.getLoginDevEnvUserPasswd();


    @Test(priority = 0, dataProvider = "RegistrationMcTestData")
    public void registrationTestCaseDevEnv(String firstName,
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
        driver.switchTo().alert().sendKeys(loginDevEnv + Keys.TAB + passwDevEnv);
        driver.switchTo().alert().accept();
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
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registrationPage.createAccountBtn);
        Thread.sleep(5000);
        registrationPage.createAccountBtn.click();
        Assert.assertEquals(registrationPage.confirmRegistrationText.getText(), "Your Registration Was Successful");
        System.out.println("**********************************************" + userNameCreated);


    }

    /*
    Changing Student account to testing account in Admin Tool
     */
    @Test(priority = 1, enabled = false)
    public void changeUserTypeToTestUserTestCase() throws InterruptedException {
        driver.get(adminToolUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(driver, loginPage);
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
        System.out.println(userNameCreated);
        //studentPage.SignOut.click();
    }

    @Test(priority = 2, enabled = false, dataProvider = "RegistrationMcTestData")
    public void checkUserInfoTestCase(String firstName,
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
        Thread.sleep(3000);
        driver.switchTo().alert().sendKeys(loginDevEnv + Keys.TAB + passwDevEnv);
        driver.switchTo().alert().accept();
        loginPage = new PageObjects.Mckissock.LoginPage(driver);
        loginPage.login(driver, loginPage, userNameCreated, passwordRegistrationPage);
        UserHomePage userHomePage = new UserHomePage(driver);
        Thread.sleep(3000);
        userHomePage.myProfileTab.click();
        Thread.sleep(3000);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userHomePage.myProfileTabAccountInfoFirstName.getText(), firstName);
        softAssert.assertEquals(userHomePage.myProfileTabAccountInfoLastName.getText(), lastName);
        softAssert.assertEquals(userHomePage.myProfileTabAccountInfoPhone.getText(), phone);
        softAssert.assertEquals(userHomePage.myProfileTabAccountInfoEmail.getText(), emailAdress);
        softAssert.assertEquals(userHomePage.myProfileTabAccountInfoAddress.getText(), adress);
        softAssert.assertEquals(userHomePage.myProfileTabAccountInfoCity.getText(), city);
        softAssert.assertEquals(userHomePage.myProfileTabAccountInfoState.getText(), state);
        softAssert.assertEquals(userHomePage.myProfileTabAccountInfoZipCode.getText(), zipCode);
        softAssert.assertAll();
    }

    @Test(priority = 0, dataProvider = "MainPageTestData")
    public void buyCourseTestCase(String typeOfEducation,
                                  String type,
                                  String state,
                                  String firstPageAssertion,
                                  String secondPageAssertion
                                                                ) throws InterruptedException {
        driver.get(baseUrl + "/" + typeOfEducation + "/" + type + "/");
        driver.switchTo().alert().sendKeys(loginDevEnv + Keys.TAB + passwDevEnv);
        driver.switchTo().alert().accept();
        mainPage = new MainPage(driver);
        Assert.assertEquals(mainPage.loadingPageBeforeSelectState.getText(), firstPageAssertion);
        mainPage.selectState.click();
        mainPage.clickOnState(driver, state);
        mainPage.selectStateContinueBtn.click();
        Thread.sleep(5000);
        Assert.assertEquals(mainPage.loadingPageAfterSelectState.getText(), state+secondPageAssertion);
        System.out.println(mainPage.loadingPageAfterSelectState.getText());
        System.out.println(driver.findElements(By.xpath("//div[@id='filtered-output']/div")).size());
        Thread.sleep(5000);
        String randCoursePrice = mainPage.buyRandCourse(driver);
        System.out.println(randCoursePrice);
        double  price = Double.valueOf(randCoursePrice.substring(1,randCoursePrice.length()));
        System.out.println(price);

    }
}