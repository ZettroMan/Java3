package Lesson8;

public class Direction {
    private int deltaX = 1;
    private int deltaY = 0;

    public void rotate() {
        int temp = deltaX;
        deltaX = -deltaY;
        deltaY = temp;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
