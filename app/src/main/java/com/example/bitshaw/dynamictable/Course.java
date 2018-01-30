package com.example.bitshaw.dynamictable;

import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BITshaw on 2018/1/28.
 */

public class Course extends DataSupport{
    private int id;
    private String course_name;
    private String tutor_name;
    private int begin_week;
    private int finish_week;
    private String site;
    private int time;

    public int checkInput() {
        List<Course> list = DataSupport.where("time = ?  ",
                String.valueOf(time)).find(Course.class);
        int i;
        for (i=0;i<list.size();i++){
            if(id!=list.get(i).getId()) {
                if (!((begin_week < list.get(i).begin_week && finish_week < list.get(i).finish_week) ||
                        (begin_week > list.get(i).begin_week && finish_week > list.get(i).finish_week))) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static int getStatus(int time,int week){
        List<Course> list = DataSupport.where("time = ?  ",
                String.valueOf(time)).find(Course.class);
        if(list.size()<1){
            return 0;
        }
        int i;
        for (i=0;i<list.size();i++){
            if(week>=list.get(i).getBegin_week()&&week<=list.get(i).getFinish_week()){
                return 1;
            }
        }
        return 2;
    }

    public static Course getCourseWithWeek(int time,int week){
        List<Course> list = DataSupport.where("time = ?  ",
                String.valueOf(time)).find(Course.class);
        int i;
        for (i=0;i<list.size();i++){
            if(week>=list.get(i).getBegin_week()&&week<=list.get(i).getFinish_week()){
                return list.get(i);
            }
        }
        return null;
    }

    public static Course getCourseNoWeek(int time){
        List<Course> list = DataSupport.where("time = ?  ",
                String.valueOf(time)).find(Course.class);
        return list.get(0);
    }

    public Course(String _course_name,String _tutor_name,String _site,
                       int _begin_week,int _finish_week,int _time) {
        course_name = _course_name;
        tutor_name = _tutor_name;
        site = _site;
        begin_week = _begin_week;
        finish_week = _finish_week;
        time = _time;
    }

    public Course(int _id,String _course_name,String _tutor_name,String _site,
                  int _begin_week,int _finish_week,int _time) {
        id = _id;
        course_name = _course_name;
        tutor_name = _tutor_name;
        site = _site;
        begin_week = _begin_week;
        finish_week = _finish_week;
        time = _time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getBegin_week() {
        return begin_week;
    }

    public int getFinish_week() {
        return finish_week;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setBegin_week(int begin_week) {
        this.begin_week = begin_week;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setFinish_week(int finish_week) {
        this.finish_week = finish_week;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
