package PageObjects.Elite.SellPages;

import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChildAbusePage extends SellPage
{
    @FindBy(xpath = "//div[@class='pkg-course ng-scope course-content']/.//a[contains(text(),'Child Abuse Recognition')]")
    List<WebElement> courseContainers;

    @FindBy(css = ".premium-courses-hero-nursing")
    WebElement bigElementOnThePage;

    protected ChildAbusePage()
    {
        super();
        verifyPageIsLoaded(bigElementOnThePage);
    }

    protected String addItemToCart()
    {
        List<WebElement> container = new ArrayList<>();
        if (WaitUntil.listOfElementsIsNotEmpty(courseContainers))
        {
            container.add(courseContainers.get(new Random().nextInt(courseContainers.size())));
            do
            {
                container.set(0, container.get(0).findElement(By.xpath("..")));
            }
            while (!container.get(0).getAttribute("class").equalsIgnoreCase("pkg-course ng-scope course-content"));
            return parsePackageContainers(container);
        }
        else throw new RuntimeException("Child Abuse courses are not present at Nursing Page for "+ Helper.getEliteProperties().getEliteState());
    }

    protected void closePopUp() {}
}
