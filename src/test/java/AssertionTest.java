import com.jaque.Assertion;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AssertionTest {

    @Test
    public void test1(){
        Assertion.start().forTest();
        Assertion.end();
    }
    @Test
    public void test2(){
        Map<String, Boolean> flagMap = new HashMap<>();
        Boolean flag = flagMap.get("aa");
        flagMap.remove("faf");
        Assert.assertTrue(null == flag);
    }

}
