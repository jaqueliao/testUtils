import com.jaque.Assertion;
import org.testng.annotations.Test;

public class AssertionTest {

    @Test
    public void test1(){
        Assertion.forTest();
        Assertion.end();
    }
}
