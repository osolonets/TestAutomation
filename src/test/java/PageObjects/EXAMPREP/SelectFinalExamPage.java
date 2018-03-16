package PageObjects.EXAMPREP;

import Utils.Modules;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SelectFinalExamPage extends MainPage{

    WebDriver driver;

    public SelectFinalExamPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[1]/div[2]/a")
    public WebElement welcomeToSeries7;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[2]/div[2]/a")
    public WebElement finalExam01;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[3]/div[2]/a")
    public WebElement finalExam02;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[4]/div[2]/a")
    public WebElement finalExam03;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[5]/div[2]/a")
    public WebElement finalExam04;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[6]/div[2]/a")
    public WebElement finalExam05;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[7]/div[2]/a")
    public WebElement finalExam06;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[8]/div[2]/a")
    public WebElement finalExam07;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[9]/div[2]/a")
    public WebElement finalExam08;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[10]/div[2]/a")
    public WebElement finalExam09;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[11]/div[2]/a")
    public WebElement finalExam10;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[12]/div[2]/a")
    public WebElement finalExam11;

    @FindBy(how = How.XPATH, using = "//*[@id='courseContent']/div[2]/div[2]/div[13]/div[2]/a")
    public WebElement finalExam12;

    @FindBy(how = How.ID, using = "nextButton")
    public WebElement nextBTN;

    @FindBy(how = How.XPATH, using = "//*[@id='divActualQuestion']/span[1]")
    public WebElement numberOfAttempts;

    @FindBy(how = How.XPATH, using = "//*[@id='courseFooter']/div[3]/input")
    public WebElement nextBTNinExam;

    @FindBy(how = How.XPATH,  using = "//*[@id='StartQuizButton']")
    public WebElement startQuizButton;

    public static void quizzProceedExamPrep(WebDriver driver,
                                            SelectFinalExamPage selectFinalExamPage
    ) throws InterruptedException, NoSuchElementException {
        try{
            selectFinalExamPage.startQuizButton.click();
            System.out.println("New course start");
        }
        catch (Exception e){
            System.out.println("It's not new course" + e);
        }
        try {
            int numberOfQuestionOn = Modules.numberOfQuestionOn(selectFinalExamPage.numberOfAttempts.getText());
            int totalNumberOfQuestions = Modules.totalNumberOfQuestions(selectFinalExamPage.numberOfAttempts.getText());
            for (int i = numberOfQuestionOn; i <= totalNumberOfQuestions; i++) {
                String xPATH = "//*[@id='q" + i + "']/div[5]";
                driver.findElement(By.xpath(xPATH)).click();
                Thread.sleep(2000);
                selectFinalExamPage.nextBTNinExam.click();
                Thread.sleep(500);
                selectFinalExamPage.nextBTNinExam.click();
            }
        } catch (Exception e) {
            System.out.println("This course might be completed." + e);
        }
    }
}
