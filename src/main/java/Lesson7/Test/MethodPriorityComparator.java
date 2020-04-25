package Lesson7.Test;

import Lesson7.Test.Annotation.Test;

import java.lang.reflect.Method;
import java.util.Comparator;

public class MethodPriorityComparator implements Comparator<Method> {
    @Override
    public int compare(Method m1, Method m2) {
        return m1.getAnnotation(Test.class).priotity() - m2.getAnnotation(Test.class).priotity();
    }
}
