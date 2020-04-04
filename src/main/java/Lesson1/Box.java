package Lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> contents = new ArrayList<>();

    public void add(T obj) {
       if(!contents.isEmpty() && (!obj.getClass().equals(contents.get(0).getClass()))) {
           System.out.println("Тип фрукта не соответствует содержимому коробки");
            return;
       }
       contents.add(obj);
       System.out.println(obj.getName() + " добавлен(о)");
    }

    public float getWeight() {
        float totalWeight = 0f;
        for (T fr : contents) {
            totalWeight += fr.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(getWeight() - anotherBox.getWeight()) < 0.0001f;
    }

    public void relocateContents(Box<?> anotherBox) {
        if(anotherBox.contents.isEmpty()) return; //нечего перекладывать
        if(!contents.isEmpty() && (!anotherBox.contents.get(0).getClass().equals(contents.get(0).getClass()))) {
            System.out.println("Типы фруктов в коробках не совпадают");
            return;
        }
        //типы содержимого обоих коробок совпадают
        contents.addAll((ArrayList<T>)anotherBox.contents);
        anotherBox.contents.clear();
    }

    public int getNumberOfItems() {
        return contents.size();
    }

    public ArrayList<T> getContents() {
        return contents;
    }
}
