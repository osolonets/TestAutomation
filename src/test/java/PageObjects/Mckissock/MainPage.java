package PageObjects.Mckissock;

import Utils.Modules;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.sql.Array;

public class MainPage extends LoginPage {

    WebDriver driver;
    public MainPage(){}
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how= How.XPATH, using="//*[@id='mega-menu-item-14996']/a")
    public WebElement appraisalTab;

    @FindBy(how= How.XPATH, using="//*[@id='mega-menu-item-16569']/a")
    public WebElement appraisalTabContinuingEducation;

    @FindBy(how= How.XPATH, using="//form[@id='courseform032684']/div/div/div/div/div/div")
    public WebElement selectState;

    @FindBy(how= How.XPATH, using="//*[@id='courseform032684']/div/div/div/div/div/div/ul/li[contains(@data-value, 'Alabama')]")
    public WebElement selectStateAlabama;

    @FindBy(how= How.XPATH, using="//form[@id='courseform032684']/input")
    public WebElement selectStateContinueBtn;

    @FindBy(how= How.XPATH, using="//div[@id='content']/div[3]/header/div/div[1]/h1")
    public WebElement loadingPageAfterSelectState;

    @FindBy(how= How.XPATH, using="//div[@id='cba76a31f5b23caf2905d9b90322b451']/div/div/section/div[1]/header/h1")
    public WebElement loadingPageBeforeSelectState;

    @FindBy(how= How.XPATH, using="//div[@id='filtered-output']/div")
    public WebElement allAvailableCourses;

    public String buyRandCourse(WebDriver driver) {
        int numbrerOfCoursesAvailable = driver.findElements(By.xpath("//div[@id='filtered-output']/div")).size();
        int randomCourseNumber = Modules.generateRamdom(1,numbrerOfCoursesAvailable);
        String addToCartpart1 = "//div[@id='filtered-output']/div[";
        String addToCartpart3 = "]/div[2]/div[3]/div[2]/div[2]/button";
        String pricePart1 = addToCartpart1;
        String pricePart3 = "]/div[2]/div[3]/div[2]/div[1]";
        String price = driver.findElement(By.xpath(pricePart1+randomCourseNumber+pricePart3)).getText();
        driver.findElement(By.xpath(addToCartpart1+randomCourseNumber+addToCartpart3)).click();
        String proceedToCheckOutPart1 = addToCartpart1;
        String proceedToCheckOutPart2 = "]/div[2]/div[3]/div[2]/div[2]/a";
        driver.findElement(By.xpath(proceedToCheckOutPart1+randomCourseNumber+proceedToCheckOutPart2)).click();
        return price;
    }
    public void clickOnState (WebDriver driver, String state){
        String selectStatePart1 = "//form[@id='courseform032684']/div/div/div/div/div/div/ul/li[contains(@data-value, '";
        String selectStatePart3 = "')]";
        driver.findElement(By.xpath(selectStatePart1+state + selectStatePart3)).click();
    }
}
