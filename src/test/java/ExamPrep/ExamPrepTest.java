package ExamPrep;
import PageObjects.EXAMPREP.LoginPage;
import PageObjects.EXAMPREP.MainPage;
import PageObjects.EXAMPREP.SelectFinalExamPage;
import PageObjects.EXAMPREP.Series7Page;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static PageObjects.EXAMPREP.SelectFinalExamPage.quizzProceedExamPrep;

public class ExamPrepTest extends BaseTest {

    private LoginPage loginPage;
    private MainPage mainPage;
    private Series7Page series7Page;
    private SelectFinalExamPage selectFinalExamPage;
    String targetURL = UtilExamPrep.getBaseUrl();


    @Test()
    public void loginTestCase() throws InterruptedException {
        baseUrl = UtilExamPrep.getBaseUrl();
        driver.get(baseUrl);

        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
        loginPage.login(driver, loginPage);
    }

    @Test(dependsOnMethods = "loginTestCase")
    public void series7TestCase() throws InterruptedException {
        mainPage = new MainPage(driver);
        Thread.sleep(5000);
        mainPage.series7.click();
        mainPage.series7.click();
        Thread.sleep(3000);
        //mainPage.startQuizButton.click();
        series7Page = new Series7Page(driver);
        Thread.sleep(3000);
        series7Page.examSenter.click();
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        series7Page.finalExam.click();
        Thread.sleep(3000);
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }
/*
change URL to http://stage-vc5up.colibrigroup.com/
 */
    @Test(dependsOnMethods = "series7TestCase")
    public void checkVC5Page() throws InterruptedException {
        String currentURL = driver.getCurrentUrl();
        targetURL = UtilExamPrep.getTargetUrlStart() + currentURL.substring(30);
    }

    @Test(dependsOnMethods = "checkVC5Page")
    public void welcomeAndExam1() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage = new SelectFinalExamPage(driver);
        selectFinalExamPage.finalExam01.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "welcomeAndExam1")
    public void exam2() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam02.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam2")
    public void exam3() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam03.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam3")
    public void exam4() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam04.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam4")
    public void exam5() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam05.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam5")
    public void exam6() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam06.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam6")
    public void exam7() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam07.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam7")
    public void exam8() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam08.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);

    }
    @Test(dependsOnMethods = "exam8")
    public void exam9() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam09.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam9")
    public void exam10() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam10.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam10")
    public void exam11() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam11.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

    @Test(dependsOnMethods = "exam11")
    public void exam12() throws InterruptedException {
        driver.get(targetURL);
        selectFinalExamPage.finalExam12.click();
        quizzProceedExamPrep(driver, selectFinalExamPage);
    }

}