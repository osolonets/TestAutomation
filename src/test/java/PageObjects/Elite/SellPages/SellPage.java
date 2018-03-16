package PageObjects.Elite.SellPages;

import PageObjects.Elite.*;
import PageObjects.Elite.CheckoutPages.LoginPage;
import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class SellPage extends Elite
{
    private By price = By.xpath(".//*[starts-with(.,'$')]");
    protected By addToCart = By.xpath("//*[text()='Add to Cart']");

    @FindBy(xpath = "//*[text()='Add to Cart']")
    private List<WebElement> addToCartBtn;

    protected SellPage()
    {
        super("/" + Helper.getStateAbbreviation(Helper.getEliteProperties().getEliteState()),
                Helper.getEliteProperties().getEliteProfession(), false);
        handlePopUp();
    }

    protected abstract String addItemToCart();

    protected abstract void closePopUp();

    public String addToCart()
    {
        return this.addItemToCart();
    }

    public static SellPage getSellPage(String profession)
    {
        try
        {
            return (SellPage)Class.forName("PageObjects.Elite.SellPages."+profession+"Page").newInstance();
        } catch (ReflectiveOperationException e)
        {
            throw new RuntimeException("Make sure profession is specified correctly", e);
        }
    }

    protected String parsePackageContainers(List<WebElement> containers)
    {
        if (containers != null)
        {
            AllureReporter.logStep("Add random package to the shopping cart");
            for (WebElement container : containers)
            {
                if (container.findElements(By.xpath(".//*[text()='Add to Cart']")).size() > 0)
                {
                    Action.clickOnElement(container.findElement(By.xpath(".//*[text()='Add to Cart']")), "");
                    return container.findElement(price).getText();
                }
            }
            this.waitForCourseListToBeLoaded();
            return selectRandomCourse();
        }
        else
        {
            this.waitForCourseListToBeLoaded();
            return selectRandomCourse();
        }
    }

    protected void waitForCourseListToBeLoaded()
    {
        if (!WaitUntil.listOfElementsIsNotEmpty(addToCartBtn))
        {
            throw new RuntimeException("There is no courses displayed at the sell page");
        }
    }

    private String selectRandomCourse()
    {
        List<WebElement> listOfValidBtn = new ArrayList<>();
        WebElement addToCart_Btn;

        listOfValidBtn.addAll(addToCartBtn);
        AllureReporter.logStep("Add random course to the shopping cart");
        for (Iterator<WebElement> iterator = listOfValidBtn.iterator(); iterator.hasNext();)
        {
            WebElement e = iterator.next();
            if (e.getAttribute("class").contains("ng-hide")
                    || e.findElement(By.xpath("./../..")).getAttribute("class").contains("pc-square ng-scope")
                    || (e.getSize().getWidth() == 0 && e.getSize().getHeight() == 0))
                iterator.remove();
        }
        addToCart_Btn = listOfValidBtn.get(new Random().nextInt(listOfValidBtn.size()));
        WebElement courseContainer = addToCart_Btn;

        do
        {
            courseContainer = courseContainer.findElement(By.xpath(".."));
        }
        while (courseContainer.findElements(price).size() == 0);
        Action.clickOnElement(addToCart_Btn, "");
        return courseContainer.findElement(price).getText();
    }

    @Override
    public LoginPage proceedToCheckoutAsANotLoggedInUser(String profession, String state)
    {
        if (this instanceof CosmetologyPage)
        {
            Action.clickOnElement(proceedToCheckoutBtn, "'Proceed to checkout' button");
            handlePopUp();
            return new LoginPage(profession, state);
        }
        else return super.proceedToCheckoutAsANotLoggedInUser(profession, state);
    }

    private void handlePopUp()
    {
        this.closePopUp();
    }
}