package Lesson6;

public abstract class OneAndFour {

    public static boolean hasOneAndFour(int[] array) {

        //  можно было реализовать через Arrays.asList(array).indexOf(int), но это не интересно));
        boolean hasOne = false;
        boolean hasFour = false;

        for (int element : array) {
            if(!hasOne && (element == 1)) hasOne = true;
            if(!hasFour && (element == 4)) hasFour = true;
            if(hasOne && hasFour) return true;
        }
        if(hasOne || hasFour) {
            return false;
        } else {
            throw new RuntimeException("There is no 1 or 4 found in array");
        }
     }
}
