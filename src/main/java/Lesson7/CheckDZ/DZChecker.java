package Lesson7.CheckDZ;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.*;
import java.util.Random;

public class DZChecker {
    public static void check(String path) throws Exception {

        final int N_TESTS = 50;

        File file = new File(path);
        String[] str = file.list();

        for (String o : str) {
            String[] mass = o.split("\\.");
            if (!mass[1].equalsIgnoreCase("class")) {
                continue;
            }

            System.out.printf("Testing %s ...\n", o);
            try {
                Class testClass = URLClassLoader.newInstance(new URL[]{file.toURL()}).loadClass(mass[0]);

                Method calculateInt = testClass.getDeclaredMethod("calculate", int.class, int.class, int.class, int.class);
                Method calculateFloat = testClass.getDeclaredMethod("calculate", float.class, float.class, float.class, float.class);
                Method checkTwoNumbers = testClass.getDeclaredMethod("checkTwoNumbers", int.class, int.class);
                Method isNegative = testClass.getDeclaredMethod("isNegative", int.class);
                Method isLeapYear = testClass.getDeclaredMethod("isLeapYear", int.class);

                calculateInt.setAccessible(true);
                calculateFloat.setAccessible(true);
                checkTwoNumbers.setAccessible(true);
                isNegative.setAccessible(true);
                isLeapYear.setAccessible(true);

                // создаем тестовый объект типа testClass (хотя для статичных методов
                // можно было бы возмользоваться method.invoke(null), но тут мы достоверно не знаем,
                // как студент реализовал метод - статичным или нет)
                Constructor constructor = testClass.getConstructor();
                Object testObject = constructor.newInstance();
                Random random = new Random(System.currentTimeMillis());

                // тестируем calculateInt
                boolean testPassed = true;
                for (int i = 0; i < N_TESTS; i++) {
                    int a = random.nextInt(1000) - 500;
                    int b = random.nextInt(1000) - 500;
                    int c = random.nextInt(1000) - 500;
                    int d;
                    do {
                        d = random.nextInt(1000) - 500;
                    } while (d == 0);
                    if (!calculateInt.invoke(testObject, a, b, c, d).equals(calculateIntRight(a, b, c, d))) {
                        testPassed = false;
                        break;
                    }
                }
                if (testPassed) {
                    System.out.println("Calculate Int Test passed.");
                } else {
                    System.out.println("Calculate Int Test FAILED!!!");
                }

                // тестируем calculateFloat
                testPassed = true;
                for (int i = 0; i < N_TESTS; i++) {
                    float a = random.nextFloat();
                    float b = random.nextFloat();
                    float c = random.nextFloat();
                    float d;
                    do {
                        d = random.nextFloat();
                    } while (d == 0f);
                    if (!calculateFloat.invoke(testObject, a, b, c, d).equals(calculateFloatRight(a, b, c, d))) {
                        testPassed = false;
                        break;
                    }
                }
                if (testPassed) {
                    System.out.println("Calculate Float Test passed.");
                } else {
                    System.out.println("Calculate Float Test FAILED!!!");
                }

                // тестируем checkTwoNumbers
                testPassed = true;
                for (int i = 0; i < N_TESTS; i++) {
                    int a = random.nextInt(1000) - 500;
                    int b = random.nextInt(1000) - 500;
                    if (!checkTwoNumbers.invoke(testObject, a, b).equals(checkTwoNumbersRight(a, b))) {
                        testPassed = false;
                        break;
                    }
                }
                if (testPassed) {
                    System.out.println("CheckTwoNumbers Test passed.");
                } else {
                    System.out.println("CheckTwoNumbers Test FAILED!!!");
                }

                // тестируем isNegative
                testPassed = true;
                for (int i = 0; i < N_TESTS; i++) {
                    int a = random.nextInt(1000) - 500;
                    if (!isNegative.invoke(testObject, a).equals(isNegativeRight(a))) {
                        testPassed = false;
                        break;
                    }
                }
                if (testPassed) {
                    System.out.println("IsNegative Test passed.");
                } else {
                    System.out.println("IsNegative Test FAILED!!!");
                }

                // тестируем isLeapYear
                //TODO Нужен более строгий тест, на случайных числах не всегда выявляется ошибка
                testPassed = true;
                for (int i = 0; i < N_TESTS; i++) {
                    int a = random.nextInt(5000) + 1;  // проверяем только года нашей эры
                    if (!isLeapYear.invoke(testObject, a).equals(isLeapYearRight(a))) {
                        testPassed = false;
                        break;
                    }
                }
                if (testPassed) {
                    System.out.println("IsLeapYear Test passed.");
                } else {
                    System.out.println("IsLeapYear Test FAILED!!!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int calculateIntRight(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    private static float calculateFloatRight(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    private static boolean checkTwoNumbersRight(int first, int second) {
        int sum = first + second;
        return (sum >= 10) && (sum <= 20);
    }

    private static boolean isNegativeRight(int variable) {
        return (variable < 0);
    }

    private static boolean isLeapYearRight(int year) {
        return (year % 100 != 0) && (year % 4 == 0) || (year % 400 == 0);
    }

}
