package Lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore tunnelSemaphore;

    public Tunnel(int length, int capacity) {
        this.length = length;
        this.description = "Тоннель " + length + " метров, вмещающий " + capacity + " машин";
        tunnelSemaphore = new Semaphore(capacity);
    }

    @Override
    public void go(Car c) throws InterruptedException {
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            tunnelSemaphore.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } finally {
            tunnelSemaphore.release();  //в случае InterruptedException машина также магическим образом извлекается из тоннеля
        }

    }
}
