import PageObjects.REX.Helium.CourseRunnerPage;
import PageObjects.REX.Helium.DashboardPage;
import PageObjects.REX.Helium.LoginPage;
import PageObjects.VC5.VC5CoursePage;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Context;
import Utils.Helpers.Helper;
import Utils.Listeners.TestNGListeners.InvokedMethodListenerREX;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Title;

import static Utils.Helpers.AllureReporter.logStep;
import static Utils.Helpers.Action.AssertTrue;

import java.util.List;

public class REXExistingUsersTest
{
    private Context context;
    private WebDriver driver;

    private String userName;
    private String currentState;
    private String currentCourse;
    private String parentWindow;

    private DashboardPage dashboardPage;
    private CourseRunnerPage courseRunnerPage;
    private VC5CoursePage vc5CoursePage;

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browserType)
    {
        Context.init("REX", browserType);
        context = Context.getInstance();
        driver = context.getDriver();
        parentWindow = driver.getWindowHandle();
    }

    @DataProvider(name = "loginCredentials")
    public static Object[][] usersProvider()
    {
        return Helper.getUsersForRegression();
    }

    @Factory(dataProvider = "loginCredentials")
    public REXExistingUsersTest(String userName)
    {
        this.userName = userName;
        currentState = userName.substring(0,2);
    }

    @DataProvider(name = "courses")
    public Object[][] coursesProvider()
    {
        List<String> courseList = dashboardPage.getCourseList();
        Object [][] courses = new String[courseList.size()][1];

        for (int i = 0; i < courseList.size(); i++)
        {
            courses[i][0] = courseList.get(i);
        }
        return courses;
    }

    @Test()
    public void login()
    {
        dashboardPage = new LoginPage(true).loginAs(userName, Helper.getUserInfo().get("password"));
    }

    @Title("Test certificates")
    @Test(priority = 0, dependsOnMethods = "login", dataProvider = "courses")
    public void certificatesTest(String course)
    {
        dashboardPage.printCertificate(course);
        Action.switchWindow();
        try
        {
            AssertTrue(dashboardPage.verifyProperCertificateOpened(course, currentState),
                    "Verify that proper certificate opened");
        }
        catch (AssertionError e)
        {
            AllureReporter.attacheImage(driver.getCurrentUrl(), Action.takeScreenshot());
            InvokedMethodListenerREX.results.get().captured = true;
            throw e;
        }
        finally
        {
            if (driver.getWindowHandles().size() > 1)
            {
                logStep("Close certificate");
                driver.close();
            }
            driver.switchTo().window(parentWindow);
        }
    }

//    @Test(priority = 1, dependsOnMethods = "login")
//    public void goToRandomRunnerPage()
//    {
//        currentCourse = dashboardPage.getRandomCourse();
//        courseRunnerPage = dashboardPage.openRandomCourse(currentCourse);
//
//        AssertTrue(WaitUntil.elementContainsTextIgnoreCase(courseRunnerPage.getCourseProgress(), "100%"),
//                "Runner Page: Verify that course progress is 100%");
//    }
//
//    @Test(dependsOnMethods = "goToRandomRunnerPage")
//    public void openTheCourseInVC5()
//    {
//        vc5CoursePage = courseRunnerPage.openSplashPage().openCourseInVC5();
//
//        AssertTrue(WaitUntil.elementContainsTextIgnoreCase(vc5CoursePage.getCourseHeader(), currentCourse),
//                "VC5 Page: Verify that VC5 contains correct course title");
//    }
//
//    @Test(dependsOnMethods = "openTheCourseInVC5")
//    public void closeTheCourse()
//    {
//        courseRunnerPage = vc5CoursePage.closeCourse();
//
//        AssertTrue(WaitUntil.elementContainsTextIgnoreCase(courseRunnerPage.getCourseProgress(), "100%"),
//                "Runner Page: Verify course progress is 100%");
//    }

    @Test(dependsOnMethods = "certificatesTest")
    public void logout()
    {
//        if (getEnvironment().equalsIgnoreCase("Production"))
//        {
//            driver.manage().deleteCookieNamed("XSRF-TOKEN");
//            driver.navigate().refresh();
//        } else
        dashboardPage.logout();
    }

    @AfterClass
    public void tearDown()
    {
        context.tearDown();
    }
}
