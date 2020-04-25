import org.junit.Assert;
import org.junit.Test;

import static Lesson6.OneAndFour.hasOneAndFour;

public class OneAndFourExceptionTest {

    @Test(expected = RuntimeException.class)
    public void exceptionTest1() {
        Assert.assertFalse(hasOneAndFour(new int[]{}));
    }

    @Test(expected = RuntimeException.class)
    public void exceptionTest2() {
        Assert.assertFalse(hasOneAndFour(new int[]{2, 3, 5, 6, 7, 8, 9}));
    }

    @Test(expected = RuntimeException.class)
    public void exceptionTest3() {
        Assert.assertFalse(hasOneAndFour(new int[]{7, 100, 14, 48}));
    }

}
