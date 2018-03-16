package PageObjects.AdminTool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class StudentsPage extends AdministrationPage {

    WebDriver driver;

    public StudentsPage() {
    }

    public StudentsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_AdminContent_ctl01_userNameTextBox")
    public WebElement searchStudentsUsernameTextBox;

    @FindBy(how = How.ID, using = "ctl00_AdminContent_ctl01_searchButton")
    public WebElement searchStudentsSearchBTN;

    @FindBy(how = How.XPATH, using = "//*[@id='ctl00_AdminContent_searchResultsGrid']/tbody/tr[2]/td[1]/a")
    public WebElement searchresultStudentPage;

    @FindBy(how = How.ID, using = "ctl00_AdminContent_ctl08_editSaveSudentTypeButton")
    public WebElement userInfoStudentTypeEditBTN;

    @FindBy(how = How.ID, using = "ctl00_AdminContent_ctl08_studentTypeDropDown")
    public WebElement userInfoStudentTypeDropDown;

    @FindBy(how = How.ID, using = "ctl00_AdminContent_ctl08_editSaveSudentTypeButton")
    public WebElement userInfoSaveStudentTypeBTN;



    public void login(WebDriver driver, StudentsPage loginPage) throws InterruptedException {


    }

}
