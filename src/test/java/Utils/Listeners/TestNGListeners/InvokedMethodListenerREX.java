package Utils.Listeners.TestNGListeners;

import Utils.Helpers.Action;
import Utils.Helpers.AllureReporter;
import Utils.Helpers.Context;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.util.ArrayList;
import java.util.List;

public class InvokedMethodListenerREX implements IInvokedMethodListener
{
    public static InheritableThreadLocal<ResultsCollector> results = new InheritableThreadLocal<ResultsCollector>()
    {
        @Override
        protected ResultsCollector initialValue()
        {
            return new ResultsCollector();
        }
    };

    public static class ResultsCollector
    {
        public boolean captured;
        String test;
        List<String> failedMethods;

        ResultsCollector()
        {
            captured = false;
            test = "";
            failedMethods = new ArrayList<>();
        }
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult)
    {
        if (iInvokedMethod.isTestMethod())
        {
            checkTestName(iInvokedMethod);
            String[] dependedUpon = iInvokedMethod.getTestMethod().getMethodsDependedUpon();
            if (dependedUpon.length > 0)
            {
                for (String method: dependedUpon)
                {
                    for (String failedMethod: results.get().failedMethods)
                    {
                        if (method.contains(failedMethod))
                        {
                            throw new SkipException("");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult)
    {
        if (iInvokedMethod.isTestMethod())
        {
            try {
                Context.getInstance().getSoftAssert().assertAll();
            } catch (AssertionError e) {
                iTestResult.setStatus(2);
                iTestResult.setThrowable(e);
            } finally {
                if (iTestResult.getStatus() == 2) {
                    if (!results.get().captured) {
                        AllureReporter.attacheImage(Context.getInstance().getDriver().getCurrentUrl(), Action.takeScreenshot());
                    }
                    results.get().captured = false;
                }
                if ((iTestResult.getStatus() == 2 && !iTestResult.getThrowable().getClass().getSimpleName().equals("AssertionError"))
                        || iTestResult.getStatus() == 3)
                    results.get().failedMethods.add(iInvokedMethod.getTestMethod().getMethodName());
                Context.getInstance().setSoftAssert();
            }
        }
    }

    private void checkTestName(IInvokedMethod method)
    {
        if (results.get().test.isEmpty() || !results.get().test.equals(method.getTestMethod().getXmlTest().getName()))
        {
            results.get().test = method.getTestMethod().getXmlTest().getName();
            results.get().failedMethods.clear();
        }
    }
}