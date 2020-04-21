import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static Lesson6.OneAndFour.hasOneAndFour;

@RunWith(Parameterized.class)
public class OneAndFourTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 1, 1, 4, 1, 1, 1, 1, 4, 1}, true},
                {new int[]{1, 4, 1, 4, 1, 4}, true},
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1}, false},
                {new int[]{2, 3, 5, 4, 6, 7, 8, 1, 9, 0}, true},
                {new int[]{4, 2, 7, 6, 1, 8, 1, 1, 13}, false},   //это - провальный тест, для примера (massOneAndFourTest[4])
                {new int[]{4, 4, 4, 4}, false},
                {new int[]{2, 3, 5, 6, 7, 8, 4, 9}, false},
                {new int[]{1, 1, 1, 4, 1, 1, 1, 1, 4, 1}, true}
        });
    }

    private int[] array;
    private boolean expectedValue;

    public OneAndFourTest(int[] array, boolean expectedValue) {
        this.array = array;
        this.expectedValue = expectedValue;
    }

    @Test
    public void massOneAndFourTest() {
        Assert.assertEquals(expectedValue, hasOneAndFour(array));
    }

}

