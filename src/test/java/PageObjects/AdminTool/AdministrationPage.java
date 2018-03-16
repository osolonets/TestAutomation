package PageObjects.AdminTool;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdministrationPage {

    WebDriver driver;

    public AdministrationPage() {
    }

    public AdministrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_studentsButton")
    public WebElement students;

    @FindBy(how = How.ID, using = "ctl00_LinkButton1")
    public WebElement SignOut;




    public void login(WebDriver driver, AdministrationPage loginPage) throws InterruptedException {


    }

}
