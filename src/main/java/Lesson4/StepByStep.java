package Lesson4;

public class StepByStep {

    public static void main(String[] args) {

        WaitingThread threadA = new WaitingThread('A', 0);
        WaitingThread threadB = new WaitingThread('B', 1);
        WaitingThread threadC = new WaitingThread('C', 2);

        System.out.println("\nСтартуем три потока");
        threadA.start();
        threadB.start();
        threadC.start();

        //ожидаем завершения всех потоков
        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nВсе потоки завершились");
    }
}

