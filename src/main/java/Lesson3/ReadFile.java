package Lesson3;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ReadFile {
    public static void main(String[] args) {

        //Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
        byte[] byteArray = new byte[50];
        int count;
        try (FileInputStream fis = new FileInputStream("files/smallfile.txt")) {
            count = fis.read(byteArray);
            System.out.printf("Из файла smallfile.txt прочитано %d байт:\n", count);
            System.out.println(Arrays.toString(byteArray));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
