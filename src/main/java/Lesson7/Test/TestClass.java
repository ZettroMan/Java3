package Lesson7.Test;

import Lesson7.Test.Annotation.AfterSuite;
import Lesson7.Test.Annotation.BeforeSuite;
import Lesson7.Test.Annotation.Test;

public class TestClass {

    @Test(priotity = 3)
    public void thirdPriorityMethod(){
        System.out.println("Third method is called (priority == 3)");
    }

    @Test
    public void defaultPriorityMethod(){
        System.out.println("Default priority method is called (priority == 3)");
    }

    @BeforeSuite
    public void beforeMethod(){
        System.out.println("Before method is called");
    }

    @Test(priotity = 2)
    public void secondPriorityMethod(){
        System.out.println("Second method is called (priority == 2)");
    }

    @Test(priotity = 4)
    public void fourthPriorityMethod(){
        System.out.println("Fourth method is called (priority == 4)");
    }

    @Test(priotity = 1)
    public void firstPriorityMethod(){
        System.out.println("First method is called (priority == 1)");
    }

    @AfterSuite
    public void afterMethod(){
        System.out.println("After method is called");
    }
}
