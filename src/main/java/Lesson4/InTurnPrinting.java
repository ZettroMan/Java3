package Lesson4;

public class InTurnPrinting {

    public static void main(String[] args) {
        // менеджер печати, выступает в роли объекта синхронизации потоков
        PrintOutManager printOutManager = new PrintOutManager(0,2);

        // Все потоки используют один и тот же менеджер печати.
        // Первым печатает тот у которого turn == startTurn, а дальше - по кругу

        PrintingThread threadA = new PrintingThread('A', 0, printOutManager);
        PrintingThread threadB = new PrintingThread('B', 1, printOutManager);
        PrintingThread threadC = new PrintingThread('C', 2, printOutManager);

        System.out.println("\nСтартуем три потока");

        // поскольку в printOutManager'e мы указали startTurn = 0, то первым всегда печатает поток threadA
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

//      другой вариант ожидания завершения потоков:
//        do {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } while (threadA.isAlive() || threadB.isAlive() || threadC.isAlive());

        System.out.println("\nВсе потоки завершились");
    }
}

