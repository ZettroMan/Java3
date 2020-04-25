import org.junit.Assert;
import org.junit.Test;


import static Lesson6.ArrayTail.getArrayTail;

public class ArrayTailTest {

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[]{123, 12, 45}, getArrayTail(new int[]{54, 23, 86, 3, 4, 123, 12, 45}, 4));
    }

    @Test(expected = RuntimeException.class)
    public void test2() {
        Assert.assertArrayEquals(new int[]{123, 12, 45}, getArrayTail(new int[]{54, 23, 86, 3, 88, 123, 12, 45}, 4));
    }

    @Test    //Внимание! lastValue = 123  (не 4)
    public void test3() {
        Assert.assertArrayEquals(new int[]{12, 45}, getArrayTail(new int[]{54, 23, 86, 3, 4, 123, 12, 45}, 123));
    }

    @Test
    public void test4() {
        Assert.assertArrayEquals(new int[]{14, 48, 123, 12, 45}, getArrayTail(new int[]{54, 4, 23, 86, 3, 4, 14, 48, 123, 12, 45}, 4));
    }

    @Test  //А это провальный тест
    public void test5() {
        Assert.assertArrayEquals(new int[]{14, 48, 123, 12, 45}, getArrayTail(new int[]{54, 4, 23, 86, 4, 8, 14, 48, 123, 12, 45}, 4));
    }

}
