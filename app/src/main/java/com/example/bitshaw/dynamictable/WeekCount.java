package com.example.bitshaw.dynamictable;

import android.provider.ContactsContract;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by BITshaw on 2018/1/30.
 */

public class WeekCount {
    private static int first_week = 1;

    public static void init(){
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
        cal.setMinimalDaysInFirstWeek(7); // 设置每周最少为7天
        cal.setTime(new Date());
        int weeks=cal.get(Calendar.WEEK_OF_YEAR);
        first_week = weeks -12;
    }

    public static void reset(int current_week){
        Calendar cal = Calendar.getInstance();//这一句必须要设置，否则美国认为第一天是周日，而我国认为是周一，对计算当期日期是第几周会有错误
        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
        cal.setMinimalDaysInFirstWeek(7); // 设置每周最少为7天
        cal.setTime(new Date());
        int weeks=cal.get(Calendar.WEEK_OF_YEAR);
        first_week = weeks - current_week;

    }

    public static int getCurrentWeek(){
        Calendar cal = Calendar.getInstance();//这一句必须要设置，否则美国认为第一天是周日，而我国认为是周一，对计算当期日期是第几周会有错误
        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
        cal.setMinimalDaysInFirstWeek(7); // 设置每周最少为7天
        cal.setTime(new Date());
        int weeks=cal.get(Calendar.WEEK_OF_YEAR);
        return weeks - first_week;
    }

    public static int getCurrenDay(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }
}
