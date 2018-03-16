package Utils.Helpers;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class AllureReporter
{
    @Attachment(value = "Oops! {0}")
    public static byte[] attacheImage(String URL, byte[] attachment)
    {
        return attachment;
    }

    @Attachment(value = "ERROR")
    public static String attacheErrorMessage(String shortDescription)
    {
        return shortDescription;
    }

    @Step(value = "{0}")
    public static void logStep(String stepName)
    {

    }

    @Step(value = "ERROR {0}")
    public static void logError(String shortDescription)
    {

    }
}
