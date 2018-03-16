package Utils.Listeners.TestNGListeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer
{
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method)
    {
        String[] s = iTestAnnotation.getDependsOnMethods();

        if (s.length > 0)
        {
            iTestAnnotation.setAlwaysRun(true);
            System.out.println();
        }
    }
}