package Utils.ExpectedConditions;

import Utils.Helpers.Context;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class ListIsNotEmpty implements ExpectedCondition<Boolean>
{
    private List<WebElement> list;

    public ListIsNotEmpty(List<WebElement> list)
    {
        this.list = list;
        apply(Context.getInstance().getDriver());
    }

    public Boolean apply(WebDriver driver){
        return list.size() > 0;
    }
}
