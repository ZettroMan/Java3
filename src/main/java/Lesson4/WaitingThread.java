package Lesson4;

public class WaitingThread extends Thread {
    private char letter;
    private static Integer turn = 0;
    private final Integer threadNo;


    public WaitingThread(char letter, int threadNo) {
        this.letter = letter;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (turn) {  //проверяем, чья очередь
                if (threadNo.equals(turn)) {
                    System.out.print(letter);
                    try {
                        sleep(300);  //для наглядности
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    turn = (turn + 1) % 3;
                } else {
                    i--; // откатываем назад
                    continue;
                }
            }
         }
    }
}


