package com.example.bitshaw.tabledemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BITshaw on 2018/1/24.
 */

public class TestCourse {
    public static Course course1 = new Course("Java基础","金旭亮");
    public static Course course2 = new Course("计算机组成原理","马忠梅");
    public static List<Lesson> lessons = new ArrayList<>();
    static void fun()
    {
        lessons.add(new Lesson(course1,1,1));
        lessons.add(new Lesson(course2,1,2));
    }
}
