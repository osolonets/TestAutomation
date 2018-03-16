package Utils.ExpectedConditions;

import Utils.Helpers.Context;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementContainsTextIgnoreCase implements ExpectedCondition<Boolean>
{
    private WebElement element;
    private String string;

    public ElementContainsTextIgnoreCase(WebElement element, String string)
    {
        this.element = element;
        this.string = string;
        apply(Context.getInstance().getDriver());
    }

    public Boolean apply(WebDriver driver) {
        try {
            return element.getText().toUpperCase().contains(string.toUpperCase());
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return false;
        }
    }

/*    public static ExpectedCondition<Boolean> textToBe(final By locator, final String value) {
        return new ExpectedCondition() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                try {
                    this.currentValue = driver.findElement(locator).getText();
                    return Boolean.valueOf(this.currentValue.equals(value));
                } catch (Exception var3) {
                    return Boolean.valueOf(false);
                }
            }

            public String toString() {
                return String.format("text to be \"%s\". Current text: \"%s\"", new Object[]{value, this.currentValue});
            }
        };
    }*/
}