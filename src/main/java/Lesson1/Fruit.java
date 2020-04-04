package Lesson1;

public abstract class Fruit {
    private float weight;
    private String name;

    public Fruit(float weight, String name)
    {
        this.weight = weight;
        this.name = name;
    }

    public Fruit(float weight) {
        this.weight = weight;
        this.name = "Фрукт";
    }

    public float getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
