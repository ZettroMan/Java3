package Lesson3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FilePageReader {
    static final int PAGE_SIZE = 1800;

    public static void main(String[] args) {

        //Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
        //Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
        //Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.

        char[] buffer = new char[PAGE_SIZE];
        File file = new File("files/bigfile.txt");
        int count;
        long pageNo;
        long startTime;

        Scanner sc = new Scanner(System.in);
        if (!file.exists()) {
            System.out.println("Файла с таким именем не существует");
            return;
        }
        if (file.isDirectory()) {
            System.out.println("Этот файл является каталогом");
            return;
        }

        while (true) {
            System.out.println("Пожалуйста, введите номер страницы (0 - завершить работу):");
            pageNo = sc.nextLong();
            if (pageNo == 0L) break;

            startTime = System.currentTimeMillis();
            try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
                count = 0;
                //поскольку в кодировке UTF-8 символы могут быть переменной длины (в байтах) - считываем все последовательно
                for (long i = 0L; i < pageNo; i++) {
                    count = inputStreamReader.read(buffer);
                    if (count <= 1) break;
                }
                if (count <= 1) {
                    System.out.println("Вы ввели слишком большое значение, такой страницы не существует.\n");
                    continue;
                }
                System.out.println("Страница № " + pageNo);
                System.out.println(String.valueOf(buffer, 0, count));
                System.out.printf("\nВсего потрачено времени %d мсек\n\n", System.currentTimeMillis() - startTime);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
