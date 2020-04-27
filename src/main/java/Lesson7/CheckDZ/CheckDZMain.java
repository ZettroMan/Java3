package Lesson7.CheckDZ;

public class CheckDZMain {
    public static void main(String[] args) {
        try {
            DZChecker.check("C:/_Study/DZ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
