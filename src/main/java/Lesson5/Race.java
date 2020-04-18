package Lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    public final CountDownLatch startCdl;
    public final CountDownLatch finnishCdl;
    private final AtomicInteger carsCount;
    private final ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(int carsCount, Stage... stages) {
        this.carsCount = new AtomicInteger(carsCount);
        startCdl = new CountDownLatch(carsCount + 1);
        finnishCdl = new CountDownLatch(carsCount);
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public int getCarsCount() {
        return carsCount.intValue();
    }

    public void decreaseCarsCount() {
        carsCount.set(carsCount.intValue() - 1);
        finnishCdl.countDown();
    }
}
