package Lesson4;

public class PrintingThread extends Thread {
    private final char letter;
    private final int turn;
    private final PrintOutManager printOutManager;

    public PrintingThread(char letter, int turn, PrintOutManager printOutManager) {
        this.letter = letter;
        this.turn = turn;
        this.printOutManager = printOutManager;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            printOutManager.printLetter(letter, turn);
        }
    }
}


