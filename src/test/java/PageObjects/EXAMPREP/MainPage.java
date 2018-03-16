package PageObjects.EXAMPREP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends LoginPage{

    WebDriver driver;
    public MainPage(){}
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH,
            using = "html/body/ion-app/ng-component/ion-nav/select-enrollment/ion-content/div[2]/div/ion-grid/ion-row/ion-col[1]/ion-list/div/list-enrollment[1]/course-list-row/ion-item/div[1]")
    public WebElement series7;

    @FindBy(how = How.XPATH,
            using = "html/body/ion-app/ng-component/ion-nav/select-enrollment/ion-content/div[2]/div/ion-grid/ion-row/ion-col[1]/ion-list/div/list-enrollment[2]/course-list-row/ion-item/div[1]")
    public WebElement series3;

    //*[@id='StartQuizButton']

	}
