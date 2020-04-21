import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static Lesson6.ArrayTail.getArrayTail;

public class ArrayTailTest {

//    int arraySize;
//    Random random;
//    int[] array;
//    int lastFourIndex = -1;
//
//    @Before
//    public ArrayTailTest(int maxVal, int arraySize, int lastFourIndex) {
//        random = new Random(maxVal);
//        int[] array = new int[this.arraySize];
//        for (int i = 0; i < this.arraySize; i++) {
//            array[i] = random.nextInt();
//            if(array[i] == 4) lastFourIndex = i;
//        }
//    }

    @Test
    public void test1() {
       // System.out.println(Arrays.toString(getArrayTail(new int[]{54, 23, 86, 3, 4, 123, 12, 45}, 4)));
        Assert.assertArrayEquals(new int[]{123, 12, 45}, getArrayTail(new int[]{54, 23, 86, 3, 4, 123, 12, 45}, 4));
    }

    @Test(expected = RuntimeException.class)
    public void test2() {
        Assert.assertArrayEquals(new int[]{123, 12, 45}, getArrayTail(new int[]{54, 23, 86, 3, 88, 123, 12, 45}, 4));
    }

    @Test
    public void test3() {
        Assert.assertArrayEquals(new int[]{12, 45}, getArrayTail(new int[]{54, 23, 86, 3, 4, 123, 12, 45}, 123));
    }

    @Test
    public void test4() {
        Assert.assertArrayEquals(new int[]{14, 48, 123, 12, 45}, getArrayTail(new int[]{54, 4, 23, 86, 3, 4, 14, 48, 123, 12, 45}, 4));
    }


}
