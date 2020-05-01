package Lesson8;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final int WIDTH = 7;
        final int HEIGHT = 10;

        int[][] array = new int[HEIGHT][WIDTH];

        System.out.println("Recursive Fill:");
        recursiveFillArray(array, HEIGHT, WIDTH, 0, 0, 1);
        printArray(array);

        System.out.println("\nDirectional Fill:");
        directionalFillArray(array, HEIGHT, WIDTH, 1);
        printArray(array);
    }

    // метод заполнения массива последовательными числами по спирали
    // с применением изменяемого направления обхода
    private static void directionalFillArray(int[][] array, int height, int width, int startValue) {

        int currentX = 0;
        int currentY = 0;
        int nextY, nextX;
        Direction direction = new Direction();

        // инициализируем массив нулями
        for (int y = 0; y < height; y++) {
            Arrays.fill(array[y], 0);
        }

        // проходим по массиву по спирали с заполнением последовательными числами
        for (int i = 0; i < height * width; i++) {
            array[currentY][currentX] = startValue++;
            nextX = currentX + direction.getDeltaX();
            nextY = currentY + direction.getDeltaY();
            if ((nextX >= width) || (nextY >= height) || (nextX < 0) || (array[nextY][nextX] > 0)) {
                direction.rotate();   // поворачиваем направо когда дошли до края массива или до уже заполненной ячейки
                nextX = currentX + direction.getDeltaX();
                nextY = currentY + direction.getDeltaY();
            }
            currentX = nextX;
            currentY = nextY;
        }
    }

    // метод рекурсивного заполнения массива последовательными числами по спирали
    public static void recursiveFillArray(int[][] array, int height, int width, int startY, int startX, int startValue) {

        // основная часть кода осуществляет заполнение последовательными числами массива
        // по периметру, а затем рекурсивно вызывает заполнение оставшейся внутренней
        // части массива
        int xLen = width - (startX * 2);
        int yLen = height - (startY * 2);
        if ((xLen < 1) && (yLen < 1)) return;
        int currentX = startX;
        int currentY = startY;
        // заполняем верхнюю границу
        for (int i = 0; i < xLen; i++) {
            array[currentY][currentX++] = startValue++;
        }
        currentX--;
        currentY++;
        // заполняем правую границу
        if (yLen > 1) {
            for (int i = 0; i < yLen - 1; i++) {
                array[currentY++][currentX] = startValue++;
            }
            currentX--;
            currentY--;

            // заполняем нижнюю границу
            if (xLen > 1) {
                for (int i = 0; i < xLen - 1; i++) {
                    array[currentY][currentX--] = startValue++;
                }
                currentX++;
                currentY--;

                // заполняем левую границу
                if (yLen > 2) {
                    for (int i = 0; i < yLen - 2; i++) {
                        array[currentY--][currentX] = startValue++;
                    }
                    // если после заполнения массива по периметру еще осталась внутренняя,
                    // незаполненная часть -  заполняем ее
                    if (xLen > 2) {
                        recursiveFillArray(array, height, width, startY + 1, startX + 1, startValue);
                    }
                }
            }
        }
    }

    // метод вывода массива в консоль
    public static void printArray(int[][] array) {
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                System.out.printf("%4d", array[y][x]);
            }
            System.out.println();
        }
    }
}
