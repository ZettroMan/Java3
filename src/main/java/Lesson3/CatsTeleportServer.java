package Lesson3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CatsTeleportServer {

    private static ArrayList<Cat> cats = new ArrayList<>();

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8189);
             Socket socket = server.accept()) {
            System.out.println("Портал открыт");
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("Жду новых котов)");
                try {
                    Cat cat = (Cat) in.readObject();
                    System.out.println(cat + " прибыл!");
                    out.writeUTF(cat + " прибыл!");
                    cats.add(cat);
                } catch (IOException e) {
                    System.out.println("Коты закончились");
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Всего прибыло " + cats.size() + " котов:");
        for(Cat cat: cats) {
            System.out.println(cat);
        }
    }
}
