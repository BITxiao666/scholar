package com.eaample.listviewtest;

import java.util.Date;

/**
 * Created by Liao on 2018/1/26.
 */

public class Activity_1st_class {
    private String date;
    private String score;
    private String event;
    public Activity_1st_class(String date, String score,String event){
        this.date=date;
        this.event=event;
        this.score=score;
    }
//    public  Activity_1st_class(String date, String event){
//        this.date=date;
//        this.event=event;
//        this.score= 0;
//    }

    public String getDate(){return date;}
    public String getEvent(){return event;}
    public String  getScore(){return score;}
}
