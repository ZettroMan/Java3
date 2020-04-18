package Lesson5;

public class Car implements Runnable {

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, String name) {
        this.race = race;
        this.speed = speed;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(1500 + (int) (Math.random() * 5000));
            System.out.println(this.name + " к старту готов");
            race.startCdl.countDown();
            race.startCdl.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            synchronized (race.finnishCdl) {
                if (race.finnishCdl.getCount() == race.getCarsCount()) {
                    System.out.printf("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Приветствуем ПОБЕДИТЕЛЯ: %s !!!\n", name);
                } else {
                    System.out.printf("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> %s занял %d место!!!\n", name,
                                        race.getCarsCount() - race.finnishCdl.getCount() + 1);
                }
                race.finnishCdl.countDown();
            }
        } catch (Exception e) {
            race.decreaseCarsCount();  //чтобы правильно считались места, занимаемые участниками
            System.out.println(name + " сошел с дистанции");
            e.printStackTrace();
        }

    }
}
