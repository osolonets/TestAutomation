import PageObjects.REX.Helium.*;
import PageObjects.REX.MarketingSite.MarketingPage;
import PageObjects.REX.MarketingSite.SellPage;
import PageObjects.VC5.VC5CoursePage;

import static Utils.Helpers.Action.AssertTrue;
import static Utils.Helpers.Helper.getEnvironment;

import Utils.Helpers.Action;
import Utils.Helpers.Context;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.testng.annotations.*;

import java.util.*;

public class REXPurchaseTest
{
    private Context context;

    private String currentState;
    private String currentCourse;
    private String itemToPurchase = "";
    private String price = "$0.00";
    private String sku = "";

    private Map<String, Map<String, String>> products;   //key: state; values: packageName; sku; coursesQuantity; price
    private List<String> contentList;

    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private CheckoutPage checkoutPage;
    private ConfirmationPage confirmationPage;
    private ThankYouPage thankYouPage;
    private CourseRunnerPage courseRunnerPage;
    private VC5CoursePage vc5CoursePage;

    @Factory(dataProvider = "statesProvider")
    public REXPurchaseTest(String state)
    {
        currentState = state;
    }

    @DataProvider(name = "statesProvider")
    public static Object[][] statesProvider()
    {
        List<String> list = Helper.getStates();
        String[][] states = new String[list.size()][1];

        for (int i = 0; i < list.size(); i++)
        {
            states[i][0] = list.get(i);
        }
        return states;
    }

    private void setPurchaseDetails()
    {
        products = Helper.getItemsToPurchase();

        itemToPurchase = products.get(currentState).get("packageName");
        price = products.get(currentState).get("price");
        sku = products.get(currentState).get("sku");
    }

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browserType)
    {
        Context.init("REX", browserType);
        context = Context.getInstance();

        if (getEnvironment().equalsIgnoreCase("Staging"))
            setPurchaseDetails();
    }

    @Test()
    public void addItemToTheCart()
    {
        SellPage sellPage;

        if (getEnvironment().equalsIgnoreCase("Staging"))
        {
            registrationPage = new RegistrationPage(true);
            registrationPage.addToCartViaURL(sku);
        }
        else
        {
            sellPage =
                    new MarketingPage(true).
                            goToTheSellPage(currentState);
            registrationPage = sellPage.chooseItemToPurchase(itemToPurchase = sellPage.getItemToPurchase());
        }

        AssertTrue(WaitUntil.visibilityOf(registrationPage.getStepsImage()),
                "Registration Page: Verify 'checkout steps' image is displayed");
    }

    @Test(dependsOnMethods = "addItemToTheCart")
    public void registerANewUser()
    {
        checkoutPage = registrationPage.registerANewUser(currentState);
    }

    @Test(dependsOnMethods = "registerANewUser")
    public void checkItemToPurchaseTitle()
    {
        if (getEnvironment().equalsIgnoreCase("Production"))
        {
            checkoutPage.applyZeroDiscount();
        }
        Action.softAssert(context.getSoftAssert(), WaitUntil.elementContainsTextIgnoreCase(checkoutPage.getCartSummary(), itemToPurchase),
                "Checkout Page: Verify that cart reflects correct item to purchase");
        Action.softAssert(context.getSoftAssert(), WaitUntil.elementContainsTextIgnoreCase(checkoutPage.getGrandTotal(), price),
                "Checkout Page: Verify that cart total is correct");
    }

    @Test(dependsOnMethods = "checkItemToPurchaseTitle")
    public void checkout()
    {
        checkoutPage.selectCCAsPaymentMethod();
        checkoutPage.fillOutCCCredentials();
        confirmationPage = checkoutPage.proceedToOrderConfirmation();

        Action.softAssert(context.getSoftAssert(), WaitUntil.elementContainsTextIgnoreCase(confirmationPage.getCartSummary(), itemToPurchase),
                "Confirmation Page: Confirm that cart reflects correct item to purchase");
        Action.softAssert(context.getSoftAssert(), WaitUntil.elementContainsTextIgnoreCase(confirmationPage.getGrandTotal(), price),
                "Confirmation Page: Confirm that cart total is correct");
        Action.softAssert(context.getSoftAssert(), confirmationPage.verifyBillingAddress(Helper.getBillingAddress(currentState)),
                "Confirmation Page: Verify that billing address is correct");
    }

    @Test(dependsOnMethods = "checkout")
    public void placeOrder()
    {
        thankYouPage = confirmationPage.placeOrder();
        contentList = thankYouPage.getItems();

        Action.softAssert(context.getSoftAssert(), WaitUntil.elementContainsTextIgnoreCase(thankYouPage.getTotal(), price),
                "Receipt Page: Verify that receipt reflects correct total");
        Action.softAssert(context.getSoftAssert(), thankYouPage.verifyBillingAddress(Helper.getBillingAddress(currentState)),
                "Receipt Page: Verify that receipt reflects correct billing address");
    }

    @Test(dependsOnMethods = "placeOrder")
    public void verifyDashboardContainsPurchasedCourses()
    {
        dashboardPage = thankYouPage.goToDashboard();

        AssertTrue(dashboardPage.verifyDashboardContainsAllPurchasedItems(contentList, currentState),
                "Dashboard Page: Verify that items on dashboard correspond to items in receipt");
    }

    @Test(dependsOnMethods = "verifyDashboardContainsPurchasedCourses")
    public void startTheCourse()
    {
        courseRunnerPage = dashboardPage.startRandomCourse(
                currentCourse = dashboardPage.getNewRandomCourse());

        AssertTrue(WaitUntil.elementContainsTextIgnoreCase(courseRunnerPage.getCourseProgress(), "0%"),
                "Runner Page: Verify that course progress is 0%");
    }

    @Test(dependsOnMethods = "startTheCourse")
    public void openTheCourseInVC5()
    {
        vc5CoursePage =
                courseRunnerPage.
                        openSplashPage().
                        signOrientation().
                        signTermsAndConditions();

        AssertTrue(WaitUntil.elementContainsTextIgnoreCase(vc5CoursePage.getCourseHeader(), currentCourse),
                "VC5 Page: Verify that VC5 contains correct course title");
    }

    @Test(dependsOnMethods = "openTheCourseInVC5")
    public void goThroughTheCourse()
    {
        String firstPageTitle;

        firstPageTitle = vc5CoursePage.getCoursePageTitle().getText();
        vc5CoursePage.makeOnePercentProgressInVC5();

        AssertTrue(!firstPageTitle.equals(vc5CoursePage.getCoursePageTitle().getText()),
                "VC5 Page: Verify that page titles of the course are being changed during clicking next");
    }

    @Test(dependsOnMethods = "goThroughTheCourse")
    public void closeTheCourse()
    {
        courseRunnerPage =  vc5CoursePage.closeCourse();

        AssertTrue(WaitUntil.elementContainsTextIgnoreCase(courseRunnerPage.getCourseProgress(), "1%"),
                "Runner Page: Verify that course progress is 1%");
    }

    @Test(dependsOnMethods = "closeTheCourse")
    public void logout()
    {
//        if (getEnvironment().equalsIgnoreCase("Production"))    //Jira: RED-378
//        {
//            AllureReporter.logStep("Delete XSRF-TOKEN cookie");
//            driver.manage().deleteCookieNamed("XSRF-TOKEN");
//            driver.navigate().refresh();
//            new LoginPage();
//        }
//        else
            courseRunnerPage.logout();
    }

    @AfterClass
    public void tearDown()
    {
        context.tearDown();
    }
}