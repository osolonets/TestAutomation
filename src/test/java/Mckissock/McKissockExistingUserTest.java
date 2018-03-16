package Mckissock;

import PageObjects.Mckissock.LoginPage;


import org.testng.annotations.Test;

public class McKissockExistingUserTest extends BaseTest {

    private LoginPage loginPage;
    String baseUrl = UtilMckissock.getBaseUrl();
    String targetURL = UtilMckissock.getBaseUrl();


    @Test()
    public void loginTestCase() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
        loginPage.login(driver, loginPage);
    }

    @Test(dependsOnMethods = "loginTestCase")
    public void logOutTestCase() throws InterruptedException {
    loginPage.logOutMainPage.click();
    }
    @Test(dependsOnMethods = "logOutTestCase")
    public void newUserRegistratioTestCase() throws InterruptedException {
    loginPage.loginMainPage.click();
    }
}