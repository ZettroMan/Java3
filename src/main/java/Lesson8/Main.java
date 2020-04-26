package Lesson8;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final int WIDTH = 7;
        final int HEIGHT = 10;

        int[][] array = new int[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++) {
            Arrays.fill(array[y], 0);
        }
        System.out.println("Recursive Fill:");
        recursiveFillArray(array, HEIGHT, WIDTH, 0, 0, 1);
        printArray(array);
        System.out.println();

        System.out.println("Directional Fill:");
        directionalFillArray(array, HEIGHT, WIDTH, 1);
        printArray(array);
    }

    private static void directionalFillArray(int[][] array, int height, int width, int startValue) {

        int currentX = 0;
        int currentY = 0;
        int nextY, nextX;
        Direction direction = new Direction();

        for (int y = 0; y < height; y++) {
            Arrays.fill(array[y], 0);
        }

        for (int i = 0; i < height * width; i++) {
            array[currentY][currentX] = startValue++;
            nextX = currentX + direction.getDeltaX();
            nextY = currentY + direction.getDeltaY();
            if ((nextX >= width) || (nextY >= height) || (nextX < 0) || (array[nextY][nextX] > 0)) {
                direction.rotate();
                nextX = currentX + direction.getDeltaX();
                nextY = currentY + direction.getDeltaY();
            }
            currentX = nextX;
            currentY = nextY;
        }
    }

    public static void recursiveFillArray(int[][] array, int height, int width, int startY, int startX, int startValue) {
        int xLen = width - (startX * 2);
        int yLen = height - (startY * 2);
        if ((xLen < 1) && (yLen < 1)) return;
        int currentX = startX;
        int currentY = startY;
        for (int i = 0; i < xLen; i++) {
            array[currentY][currentX++] = startValue++;
        }
        currentX--;
        currentY++;
        if (yLen > 1) {
            for (int i = 0; i < yLen - 1; i++) {
                array[currentY++][currentX] = startValue++;
            }
            currentX--;
            currentY--;

            if (xLen > 1) {
                for (int i = 0; i < xLen - 1; i++) {
                    array[currentY][currentX--] = startValue++;
                }
                currentX++;
                currentY--;

                if (yLen > 2) {
                    for (int i = 0; i < yLen - 2; i++) {
                        array[currentY--][currentX] = startValue++;
                    }

                    if (xLen > 2) {
                        recursiveFillArray(array, height, width, startY + 1, startX + 1, startValue);
                    }
                }
            }
        }
    }

    public static void printArray(int[][] array) {
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                System.out.printf("%4d", array[y][x]);
            }
            System.out.println();
        }
    }
}
