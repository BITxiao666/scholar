package com.example.bitshaw.tabledemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BITshaw on 2018/1/24.
 */

public class Course {
    private String course_name="";
    private String tutor_name="";
    private int credict=0;
    private int course_type=0;                  //必修课为0，选修课为1，校公选课为2
    private int start_week=1;
    private int finish_week=19;

    Course(String _course_name,String _tutor_name)
    {
        course_name =_course_name;
        tutor_name =_tutor_name;
    }

    public List<String> getCourseInf()
    {
        List<String> result=new ArrayList();
        result.add(course_name);
        result.add(tutor_name);
        return result;
    }
}
