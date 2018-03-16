package PageObjects.Elite;

import Utils.Helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends Elite
{
    private static final String DASH_PAGE = "/student/courses";

    @FindBy(css = "table > tbody > tr > td > div.title")
    List<WebElement> courses;

    public DashboardPage(String profession, String state)
    {
        super("/"+ Helper.getStateAbbreviation(state)+DASH_PAGE, profession, false);
    }

    public boolean verifyDashboardContainsOneOrMoreCourses()
    {
        return courses.size() >= 1;
    }
}
