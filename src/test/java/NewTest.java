import com.jaque.listener.RetryListener;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners(value = RetryListener.class)
public class NewTest {
    @Test(dataProvider = "dp")
    //@Test(retryAnalyzer = MyRetryAnalyzer.class,dataProvider = "dp")
    public void f(Integer n, String s) {
        System.out.println("ssssss:" + s);
        Assert.assertFalse(true);
    }

    @DataProvider
    public Object[][] dp() {
        return new Object[][] { new Object[] { 1, "a" }, new Object[] { 2, "b" },new Object[] { 3, "c" },new Object[] { 4, "d" } };
    }
}