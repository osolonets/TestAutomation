package Utils.Listeners.TestNGListeners;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Context;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListenerElite implements IInvokedMethodListener
{
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult)
    {

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult)
    {
        if (iInvokedMethod.isTestMethod()) {
            try {
                Context.getInstance().getSoftAssert().assertAll();
            } catch (AssertionError e) {
                iTestResult.setStatus(2);
                iTestResult.setThrowable(e);
            } finally {
                if (iTestResult.getStatus() == 2) {
                    AllureReporter.attacheImage(Context.getInstance().getDriver().getCurrentUrl(), Action.takeScreenshot());
                }
                Context.getInstance().setSoftAssert();
            }
        }
    }
}