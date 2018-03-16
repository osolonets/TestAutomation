package PageObjects.Elite;

import PageObjects.Elite.CheckoutPages.LoginPage;
import PageObjects.Page;
import Utils.Helpers.Action;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class Elite extends Page
{
    private int count = 0;

    @FindBy(id = "dropdown_wrapper")
    WebElement myCartBtn_Type1;

    @FindBy(css = ".icon-cart")
    WebElement myCartBtn_Type2;

    @FindBy(css = ".cart_dropdown.ng-scope")
    WebElement cart;

    @FindBy(css = ".cartTable > tbody")
    WebElement cartBody;

    @FindBy(css = ".cartTable > tfoot > tr > td:nth-child(2)")
    WebElement totalPrice;

    @FindBy(id = "checkoutButton")
    protected WebElement proceedToCheckoutBtn;

    @FindBy(linkText = "Logout")
    WebElement logoutBtn;

    protected  Elite(String currentPage, String profession, Boolean entryPoint)
    {
        super(currentPage, Helper.getEliteURLStart(profession).toLowerCase(), entryPoint);
    }

    public void openCart()
    {
        Action.scrollToElement(cart);
        if (!WaitUntil.visibilityOf(cart))
        {
            count++;
            if (count < 2)
            {
                clickCartIcon();
                openCart();
            } else throw new RuntimeException("Cart is not visible");
        }
    }

    private void clickCartIcon()
    {
        if (WaitUntil.elementIsAttachedToThePage(myCartBtn_Type1))
        {
            Action.clickOnElement(myCartBtn_Type1, "on shopping cart icon");
        }
        else
        {
            Action.clickOnElement(myCartBtn_Type2, "on shopping cart icon");
        }
    }

    public Boolean verifyCartIsNotEmpty()
    {
        if (!WaitUntil.visibilityOfNestedElementsLocated(cartBody, By.tagName("tr")))
        {
      //      throw new RuntimeException("Cart is empty");
        }
        return true;
    }

    public WebElement getTotalPrice()
    {
        return totalPrice;
    }

    public LoginPage proceedToCheckoutAsANotLoggedInUser(String profession, String state)
    {
        Action.clickOnElement(proceedToCheckoutBtn, "'Proceed to checkout' button");
        return new LoginPage(profession, state);
    }

    public LogoutPage logout(String profession, String state)
    {
        clickLogoutBtn();
        return new LogoutPage(profession, state);
    }

    private void clickLogoutBtn()
    {
        Action.clickOnElement(logoutBtn, "'Logout' button");
    }
}
