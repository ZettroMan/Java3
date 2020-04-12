package Lesson3;

import java.io.*;
import java.util.Vector;

public class MergeFiles {
    public static void main(String[] args) {

        //Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
        // Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>();
        // ... Enumeration<InputStream> e = Collections.enumeration(al);
       Vector<FileInputStream> files = new Vector<>();
       byte[] buffer = new byte[200];
       try {
           for (int i = 1; i < 6; i++) {
               files.add(new FileInputStream("files/part" + i + ".txt"));
           }
       }  catch (FileNotFoundException e) {
           e.printStackTrace();
       }

        try(SequenceInputStream seq = new SequenceInputStream(files.elements());
            FileOutputStream fos = new FileOutputStream("files/merged.txt")) {
            int count;
            while((count = seq.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
