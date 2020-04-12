package Lesson3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CatsTeleportClient {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Барсик", 4, 2);
        Cat cat2 = new Cat("Мурзик", 5, 3);
        Cat cat3 = new Cat("Васька", 7, 6);

        try (Socket s = new Socket("127.0.0.1", 8189)) {
            System.out.println("Портал открыт");
            String serverResponse;
            DataInputStream in = new DataInputStream(s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            System.out.println("Все готово к телепортации");
            //телепортируем на сервер трех котов
            System.out.println("Телепортируем на сервер трех котов");
            System.out.println(cat1 + "отправляется");
            out.writeObject(cat1);
            serverResponse = in.readUTF();
            System.out.println(serverResponse);
            System.out.println(cat2 + "отправляется");
            out.writeObject(cat2);
            serverResponse = in.readUTF();
            System.out.println(serverResponse);
            System.out.println(cat3 + "отправляется");
            out.writeObject(cat3);
            serverResponse = in.readUTF();
            System.out.println(serverResponse);
            System.out.println("Все коты телепортированы))");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
