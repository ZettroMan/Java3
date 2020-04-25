package Lesson7.Test;

import Lesson7.Test.Annotation.AfterSuite;
import Lesson7.Test.Annotation.BeforeSuite;
import Lesson7.Test.Annotation.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tester {

    public static void start(Class testClass) {

        Method beforeMethod = null;
        Method afterMethod = null;

        // получаем все публичные методы тестируемого класса
        Method[] methods = testClass.getMethods();

        // добавляем в список все методы с аннотацией Test, прописываем методы beforeMethod и afterMethod
        List<Method> methodList = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                methodList.add(method);
            } else if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod != null) throw new RuntimeException("Duplicate before method!");
                beforeMethod = method;
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod != null) throw new RuntimeException("Duplicate after method!");
                afterMethod = method;
            }
        }
        if(beforeMethod == null) throw new RuntimeException("Before method is not found!");
        if(afterMethod == null) throw new RuntimeException("After method is not found!");

        // здесь мы имеем beforeMethod, afterMethod и НЕсортированный по приоритету список методов

        // производим сортировку методов по приоритету
        methodList.sort(new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                return m1.getAnnotation(Test.class).priotity() - m2.getAnnotation(Test.class).priotity();
            }
        });

        try {
            // создаем тестовый объект типа testClass
            Constructor constructor = testClass.getConstructor();
            Object testObject = constructor.newInstance();
            beforeMethod.invoke(testObject);
            for (int i = 0; i < methodList.size() ; i++) {
                methodList.get(i).invoke(testObject);
            }
            afterMethod.invoke(testObject);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }




    }
}
