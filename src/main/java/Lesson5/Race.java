package Lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    public final CountDownLatch startCdl;
    public final CountDownLatch finnishCdl;
    private int carsCount;
    private final ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(int carsCount, Stage... stages) {
        this.carsCount = carsCount;
        startCdl = new CountDownLatch(carsCount + 1);
        finnishCdl = new CountDownLatch(carsCount);
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public synchronized int getCarsCount() {
        return carsCount;
    }

    public synchronized void decreaseCarsCount() {
        carsCount--;
        finnishCdl.countDown();
    }
}
