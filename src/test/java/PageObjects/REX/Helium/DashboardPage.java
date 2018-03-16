package PageObjects.REX.Helium;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DashboardPage extends Helium
{
    private static final String DASH_PAGE = "/dashboard";

    private List<WebElement> allContainers;
    private List<WebElement> courseContainers;
    private List<String> courseNames;

    private By courseContainerHeader = By.cssSelector(".courseProgressHeader > div > h2");
    private By ebooks = By.cssSelector("div > div > .bookEnrollmentRow > div > h2");
    private By startButton = By.cssSelector("button[ng-click='startEnrollmentAndOpen(enrollment)']");
    private By openButton = By.cssSelector("a[ng-show='enrollment.statementsSummary.started.count']");
    private By certificateButton = By.xpath(".//button[text()='VIEW CERTIFICATE']");

    @FindBy(css = "div.courseProgressContainer.green")
    List<WebElement> preLicenseContainers;

    @FindBy(css = "div.courseProgressContainer.blue")
    List<WebElement> postLicenseContainers;

    @FindBy(css = "div.courseProgressContainer.yellow")
    List<WebElement> brokerContainers;

    @FindBy(css = "div.courseProgressContainer.purple")
    List<WebElement> EPMContainers;

    @FindBy(css = "div.courseProgressContainer.brown")
    List<WebElement> eBooksContainers;

    @FindBy(xpath = "//strong[text()='My Home Page']")
    WebElement header;

    public DashboardPage()
    {
        super(DASH_PAGE, false);
        verifyPageIsLoaded(header);
        groupAllCourses();
    }

    private void groupAllCourses()
    {
        courseContainers = new ArrayList<>();
        if (!preLicenseContainers.isEmpty() && preLicenseContainers != null)
        {
            courseContainers.addAll(preLicenseContainers);
        }
        if (!postLicenseContainers.isEmpty() && postLicenseContainers != null)
        {
            courseContainers.addAll(postLicenseContainers);
        }
        if (!brokerContainers.isEmpty() && brokerContainers != null)
        {
            courseContainers.addAll(brokerContainers);
        }
    }

    private void groupAllContainers()
    {
        allContainers = new ArrayList<>();
        allContainers.addAll(courseContainers);
        if (!EPMContainers.isEmpty() && EPMContainers != null)
        {
            allContainers.addAll(EPMContainers);
        }
        if (!eBooksContainers.isEmpty() && eBooksContainers != null)
        {
            allContainers.addAll(eBooksContainers);
        }
    }

    private void parseCourseNames()
    {
        courseNames = new LinkedList<>();
        for(WebElement container : courseContainers)
        {
            courseNames.add(container.findElement(courseContainerHeader).getText());
        }
    }

    public List<String> getCourseList()
    {
        parseCourseNames();
        return courseNames;
    }

    public void printCertificate(String courseName)
    {
        clickViewCertificate(courseName);
        Assert.assertTrue(getWindowsNumberIsGreaterThanOne(), "Verify that click on 'View Certificate' button opens certificate");
    }

    private void clickViewCertificate(String courseName)
    {
        for(WebElement container : courseContainers)
        {
            if (container.findElement(courseContainerHeader).getText().equals(courseName))
                Action.clickOnElement(container.findElement(certificateButton), "'View certificate' button");
        }
    }

    private Boolean getWindowsNumberIsGreaterThanOne()
    {
        return getDriver().getWindowHandles().size() > 1;
    }

    public Boolean verifyProperCertificateOpened(String courseName, String state)
    {
        if (state.equalsIgnoreCase("MO"))
        {
            return WaitUntil.pageContainsText(new WebDriverWait(getDriver(), 10),
                            Helper.getUserInfo().get("firstName"),
                            Helper.getUserInfo().get("lastName"));
        }
        else return WaitUntil.pageContainsText(new WebDriverWait(getDriver(), 10), courseName, "");
    }

    public String getNewRandomCourse()
    {
        for (WebElement container : courseContainers)
        {
            if (container.findElements(startButton).size() > 0){
                return container.findElement(courseContainerHeader).getText();
            }
        }
        return null;
    }

    public String getRandomCourse()
    {
        AllureReporter.logStep("Select a random course to open");
        for (WebElement container : courseContainers)
        {
            if (container.findElements(openButton).size() > 0){
                return container.findElement(courseContainerHeader).getText();
            }
        }
        return null;
    }

    public CourseRunnerPage startRandomCourse(String courseName)
    {
        clickStartCourseButton(courseName);
        return new CourseRunnerPage();
    }

    private void clickStartCourseButton(String courseName)
    {
        for (WebElement container : courseContainers)
        {
            if (container.findElement(courseContainerHeader).getText().equalsIgnoreCase(courseName))
            {
                Action.clickOnElement(container.findElement(startButton), "'Start' button against "+courseName);
                return;
            }
        }
    }

    public CourseRunnerPage openRandomCourse(String courseName)
    {
        clickOpenCourseButton(courseName);
        return new CourseRunnerPage();
    }

    private void clickOpenCourseButton(String courseName)
    {
        for (WebElement container : courseContainers)
        {
            if (container.findElement(courseContainerHeader).getText().equalsIgnoreCase(courseName))
            {
                Action.clickOnElement(container.findElement(openButton), "'Open' button");
                return;
            }
        }
        throw new RuntimeException("No course was selected to open");
    }

    public Boolean verifyDashboardContainsAllPurchasedItems(List<String> listOfContent, String state)
    {
        groupAllContainers();
        List<String> coursesOnDashboard = new ArrayList<>();

        for (WebElement container : allContainers)
        {
            if (container.findElement(courseContainerHeader).getText().equalsIgnoreCase("E-Books"))
            {
                for (WebElement b : container.findElements(ebooks))
                {
                    coursesOnDashboard.add(b.getText().toLowerCase());
                }
            }
            //TODO once REXMAINT-266 is fixed, remove this condition and state parameter
            else if (!state.equalsIgnoreCase("Missouri") && !state.equalsIgnoreCase("Florida") && !state.equalsIgnoreCase("Texas"))
            {
                coursesOnDashboard.add(container.findElement(courseContainerHeader).getText().toLowerCase());
            }
        }
        coursesOnDashboard.removeAll(listOfContent);
        return coursesOnDashboard.size() == 0;
    }
}