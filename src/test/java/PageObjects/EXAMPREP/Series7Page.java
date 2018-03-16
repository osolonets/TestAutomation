package PageObjects.EXAMPREP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Series7Page extends MainPage{

    WebDriver driver;

    public Series7Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID,using = "icon-edit-property")
    public WebElement examSenter;

    @FindBy(how = How.XPATH, using = "html/body/ion-app/ion-modal/div/exam-center-series-modal/ion-content/div[2]/ion-grid[2]/ion-row/ion-col[1]/ion-grid/ion-row[2]/ion-col")
    public WebElement finalExam;

	}
