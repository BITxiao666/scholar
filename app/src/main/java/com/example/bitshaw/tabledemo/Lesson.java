package com.example.bitshaw.tabledemo;

/**
 * Created by BITshaw on 2018/1/24.
 */

public class Lesson {
    private Course course;
    private String classRoom="";
    private int what_day=1;
    private int which_segment=1;

    Lesson(String _course_name,String _tutor_name,int _what_day,int _which_segment) {
        course = new Course(_course_name,_tutor_name);
        what_day = _what_day;
        which_segment = _which_segment;
    }

    Lesson(Course _course,int _what_day,int _which_segment) {
        course = _course;
        what_day = _what_day;
        which_segment = _which_segment;
    }

    public String get_class_room() {
        return classRoom;
    }

    public String get_class_name(){
        return course.getCourseInf().get(0);
    }

    public int getWhat_day(){
        return what_day;
    }

    public int getWhich_segment(){
        return which_segment;
    }

}
