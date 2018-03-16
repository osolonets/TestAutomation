package PageObjects.REX.Helium;

import Utils.Helpers.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductsPage extends Helium
{
    private static final String PROD_PAGE = "/products";

    @FindBy(css = "tr.ng-scope")
    List<WebElement> products;

    @FindBy(xpath = "//img[@alt='Brand']")
    WebElement logo;

    public ProductsPage()
    {
        super(PROD_PAGE, false);
        verifyPageIsLoaded(logo);
        verifyPageIsLoaded();
    }

    public ProductsPage(Boolean entryPoint)
    {
        super(PROD_PAGE, entryPoint);
        verifyPageIsLoaded(logo);
        verifyPageIsLoaded();
    }

    protected void verifyPageIsLoaded()
    {
        try
        {
            new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfAllElements(products));
        }catch (TimeoutException e)
        {
            throw new TimeoutException("Failed to load a full products list", e);
        }
    }

    public RegistrationPage addItemToTheCartAsNonLoggedInUser(String sku)
    {
        Action.clickOnElement(getLinkToClick(sku), "");
        return new RegistrationPage(false);
    }

    private WebElement getCourse(String sku)
    {
        for (WebElement product : products)
        {
            if (product.getText().contains(sku))
            {
                return product;
            }
        }
        throw new NoSuchElementException("Product is not found");
    }

    private WebElement getLinkToClick(String sku)
    {
        return getCourse(sku).findElement(By.linkText("Add to the cart"));
    }
}
