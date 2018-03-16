package PageObjects.VC5;

import PageObjects.REX.Helium.CourseRunnerPage;
import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Helper;
import Utils.Helpers.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VC5CoursePage extends VC5Page
{
    private static final String VC5_PAGE = "/VC5/Default";
    private static final By savingPopUp = By.id("messageBox");

    @FindBy(css = ".header > h1")
    WebElement courseHeader;

    @FindBy(id = "closeCourse")
    WebElement closeButton;

    @FindBy(css = "div.messaging")
    List<WebElement> popUp;

    @FindBy(id = "buttonMessageProctoredExam")
    List<WebElement> okProctoredExam;

    @FindBy(id = "textMessageSSN")
    List<WebElement> SSNField;

    @FindBy(id = "buttonMessageSSN")
    List<WebElement> saveSSNButton;

    @FindBy(id = "textSignatureValue")
    List<WebElement> signatureField;

    @FindBy(id = "buttonSaveSignatureValue")
    List<WebElement> saveSignatureButton;

    @FindBy(id = "nextButton")
    WebElement nextButton;

    @FindBy(id = "progressNumber")
    WebElement progress;

    @FindBy(css = ".page:nth-child(3)")
    WebElement coursePageTitle;

    @FindBy(css = "div.chapter")
    WebElement chapterBar;

    public VC5CoursePage()
    {
        super(VC5_PAGE, false);
        verifyPageIsLoaded(chapterBar);
        isPopUpsPresent();
    }

    private void clickCloseButton()
    {
        Action.clickOnElement(closeButton, "'Close course' button");
    }

    public WebElement getCourseHeader()
    {
        return courseHeader;
    }

    public CourseRunnerPage closeCourse()
    {
        clickCloseButton();
        waitForCourseProgressSaving();
        return new CourseRunnerPage();
    }

    private void waitForCourseProgressSaving()
    {
        AllureReporter.logStep("Wait for the course progress saving");
        WaitUntil.visibilityOfElementLocated(savingPopUp);
        if (!WaitUntil.invisibilityOfElementLocated(savingPopUp))
            throw new RuntimeException("VC5 closing. Saving  of course progress stuck");
    }

    private void setSSNField()
    {
        String SSN = Helper.getUserInfo().get("rexSSN");
        Action.sendKeysToElement(SSNField.get(0), SSN, "SSN: "+SSN);
    }

    private void clickSaveSSNButton()
    {
        Action.clickOnElement(saveSSNButton.get(0), "'Save' button on SSN pop-up");
    }

    private void confirmSSNSaved()
    {
        WebElement okBtn;
        okBtn = context.getShortWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSSNSaved")));
        Action.clickOnElement(okBtn, "'Ok' button on SSN pop-up");
    }

    private void setSignatureField()
    {
        String name = Helper.getUserInfo().get("firstName");
        Action.sendKeysToElement(signatureField.get(0), name, "username: "+name);
    }

    private void clickSaveSignatureButton()
    {
        Action.clickOnElement(saveSignatureButton.get(0), "'Save' button on signature pop-up");
    }

    private void confirmSignatureSaved()
    {
        WebElement okBtn;
        okBtn = context.getShortWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonElectronicSigCorrect")));
        Action.clickOnElement(okBtn, "'Ok' button on signature pop-up");
    }

    private void handlePopUps()
    {
        if (okProctoredExam.size() != 0)
        {
            Action.clickOnElement(okProctoredExam.get(0), "'Ok' button on the proctor pop-up");
        }
        if (SSNField .size() != 0)
        {
            setSSNField();
            clickSaveSSNButton();
            Action.sleep(1000);
            confirmSSNSaved();
        }
        if (signatureField .size() != 0)
        {
            setSignatureField();
            clickSaveSignatureButton();
            Action.sleep(1000);
            confirmSignatureSaved();
        }
        WaitUntil.invisibilityOfElements(popUp);
    }

    private void isPopUpsPresent()
    {
        if (WaitUntil.listOfElementsIsNotEmpty(popUp))
        {
            handlePopUps();
        }
    }

    public void makeOnePercentProgressInVC5()
    {
        String log = "'Next' button until course progress becomes 1%";
        do
        {
            Action.sleep(500);
            Action.clickOnElement(nextButton, log);
            log = "";
        }
        while (progress.getText().equals("0%"));
    }

    public WebElement getCoursePageTitle()
    {
        return coursePageTitle;
    }
}
