import PageObjects.Elite.*;
import PageObjects.Elite.CheckoutPages.LoginPage;
import PageObjects.Elite.CheckoutPages.PaymentPage;
import PageObjects.Elite.CheckoutPages.ProfilePage;
import PageObjects.Elite.CheckoutPages.ReceiptPage;
import PageObjects.Elite.SellPages.SellPage;
import Utils.Helpers.Action;
import Utils.Helpers.Context;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.testng.annotations.*;

import static Utils.Helpers.Action.AssertTrue;

import java.util.Map;

public class ElitePurchaseTest
{
    private Context context;

    private String profession;
    private String state;

    private SellPage sellPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private PaymentPage paymentPage;
    private ReceiptPage receiptPage;
    private DashboardPage dashboardPage;

    @DataProvider(name = "stateProfessionProvider")
    public static Object[][] stateProfessionProvider()
    {
        int count = 0;
        Map<String, String> map = Helper.getProfessionAndState();
        String[][] data = new String[map.size()][2];

        for (String key : map.keySet())
        {
            data[count][0] = key;          //profession
            data[count][1] = map.get(key); //state
            count++;
        }
        System.out.print(data);
        return data;

    }

    @Factory(dataProvider = "stateProfessionProvider")
    public ElitePurchaseTest(String profession, String state)
    {
        this.profession = profession;
        this.state = state;
    }

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browserType)

    {
        Context.init("Elite", browserType);
        context = Context.getInstance();
        Helper.setEliteProperties(state, profession);
    }

    @Test()
    public void selectProfessionAndState()
    {
        MarketingPage marketingPage = new MarketingPage(true);
        marketingPage.selectProfession(profession);
        marketingPage.selectState(state);
        sellPage = marketingPage.proceedToSellPage(profession);
    }

    @Test(dependsOnMethods = "selectProfessionAndState")
    public void addToCart()
    {
        String price = sellPage.addToCart();
        sellPage.openCart();

        Action.softAssert(context.getSoftAssert(), sellPage.verifyCartIsNotEmpty(),
                "Sell Page: Verify cart is not empty");
        Action.softAssert(context.getSoftAssert(), WaitUntil.elementContainsTextIgnoreCase(sellPage.getTotalPrice(),price),
                "Sell Page: Verify cart total is correct. Expected: "+price+", actual: "+sellPage.getTotalPrice().getText());
    }

    @Test(dependsOnMethods = "addToCart")
    public void proceedToCheckout()
    {
        loginPage = sellPage.proceedToCheckoutAsANotLoggedInUser(profession, state);
    }

    @Test(dependsOnMethods = "proceedToCheckout")
    public void registerANewUser()
    {
        profilePage = loginPage.registerANewUser(profession, state);
     //   loginPage.registerANewUser(profession, state);
    }

    @Test(dependsOnMethods = "registerANewUser")
    public void fillOutProfileForm()
    {
        paymentPage = profilePage.fillOutUserInfo(profession, state);
    }

    @Test(dependsOnMethods = "fillOutProfileForm")
    public void makePayment()
    {
        paymentPage.applyZeroDiscount(profession, state);

        AssertTrue(paymentPage.verifyCartTotalAfterZeroDiscountWasApplied(),
                "Payment Page: Verify cart total after 100% discount was applied");

        receiptPage = paymentPage.completeCheckout(profession, state);
    }

    @Test(dependsOnMethods = "makePayment")
    public void proceedToUserDashboard()
    {
        dashboardPage = receiptPage.proceedToUserDashboard(profession, state);

        AssertTrue(dashboardPage.verifyDashboardContainsOneOrMoreCourses(),
                "Dashboard Page: Verify dashboard contains one or more courses");
    }

    @Test(dependsOnMethods = "proceedToUserDashboard")
    public void logout()
    {
        dashboardPage.logout(profession, state);
    }

    @AfterClass
    public void tearDown()    {
        //context.tearDown();
    }
}
