package com.example.bitshaw.dynamictable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.DiscretePathEffect;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.week)
    LinearLayout week;

    @BindView(R.id.segments)
    LinearLayout segments;

    @BindViews({R.id.weekPanel_1, R.id.weekPanel_2, R.id.weekPanel_3, R.id.weekPanel_4,
            R.id.weekPanel_5})
    List<LinearLayout> mcellViews;

    private int sectionHeight=50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        debugfun();
        initDayView();
        initSegmentView();
        initCell_1();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        LitePal.getDatabase();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this,
                        EditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onDestroy();
        onCreate(null);
    }

    private void debugfun()
    {
        List<Course> list = DataSupport.findAll(Course.class);
        if(list.size()==0) {
            new Course("Java", "金老师",
                    "信3010", 12, 19, 13).save();
            new Course("Java", "金老师",
                    "信3010", 12, 19, 41).save();
            new Course("计算机组成", "马老师",
                    "信3008", 4, 17, 14).save();
            new Course("计算机组成", "马老师",
                    "信3008", 4, 17, 42).save();
            new Course("操作系统", "刘老师",
                    "信3010", 4, 13, 21).save();
            new Course("操作系统", "刘老师",
                    "信3010", 4, 13, 45).save();
            new Course("", "",
                    "", 22, 22, 60).save();//不会显示出来
            WeekCount.init();
        }
    }

    public void showCouseDetails(final Course course) {
        AlertDialog.Builder builder = new Builder(this);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setContentView(R.layout.details_layout);

        DisplayMetrics dm=this.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.66);    //宽度设置为屏幕的0.66
        //p.height = (int) (displayHeight * 0.28);    //高度设置为屏幕的0.28
        dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
        dialog.getWindow().setAttributes(p);     //设置生效

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView textView = (TextView) dialog.findViewById(R.id.name);
        textView.setText(course.getCourse_name());
        textView = (TextView) dialog.findViewById(R.id.teacher);
        textView.setText(course.getTutor_name());
        textView = (TextView) dialog.findViewById(R.id.address);
        textView.setText(course.getSite());
        textView = (TextView) dialog.findViewById(R.id.week);
        textView.setText(course.getBegin_week()+" - "+course.getFinish_week());
        textView = (TextView)dialog.findViewById(R.id.edit_course);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        EditActivity.class);
                intent.putExtra("tag_course_id",course.getId());
                startActivity(intent);
            }
        });
    }

    public void initDayView() {
        List<String> str_week = new ArrayList<>();
        str_week.add("周一");
        str_week.add("周二");
        str_week.add("周三");
        str_week.add("周四");
        str_week.add("周五");
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK)-1;
        TextView one_day = new TextView(this);
        one_day.setText("");
        one_day.setTextColor(Color.parseColor("#4A4A4A"));
        LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        one_grid.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        one_grid.weight = 0.8f;
        one_day.setGravity(Gravity.CENTER_HORIZONTAL);
        one_day.setLayoutParams(one_grid);
        week.addView(one_day);
        int i;
        for (i = 0; i < 5; i++) {
            one_day = new TextView(this);
            one_day.setText(str_week.get(i));
            if(i+1==day_of_week){
                one_day.setTextColor(Color.parseColor("#EF6C00"));
            }
            else {
                one_day.setTextColor(Color.parseColor("#4A4A4A"));
            }
            //LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            one_grid.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            one_grid.weight = 1;
            one_day.setGravity(Gravity.CENTER_HORIZONTAL);
            one_day.setLayoutParams(one_grid);
            week.addView(one_day);
        }
    }

    private void initSegmentView() {
        List<String> str_segment = new ArrayList<>();
        str_segment.add("上午\n"+"1");
        str_segment.add("上午\n"+"2");
        str_segment.add("下午\n"+"1");
        str_segment.add("下午\n"+"2");
        str_segment.add("晚上");
        int i;
        TextView segment;
        LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        for (i = 0; i < 5; i++) {
            segment = new TextView(this);
            segment.setText(str_segment.get(i));
            segment.setTextColor(Color.parseColor("#4A4A4A"));
            //LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            one_grid.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            one_grid.weight = 1;
            segment.setGravity(Gravity.CENTER_HORIZONTAL);
            segment.setLayoutParams(one_grid);
            segments.addView(segment);
        }
    }

    public void initCell_1(){
        for (int i=1;i<=5;i++) {
            for (int j=1;j<=5;j++) {
                int status = Course.getStatus(10*i+j,WeekCount.getCurrentWeek());
                if (status>0) {
                    LinearLayout linearLayout = new LinearLayout(this);
                    LinearLayout.LayoutParams Lp = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    Lp.weight = 1;
                    Lp.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    linearLayout.setLayoutParams(Lp);
                    /**
                     *    原始代码
                     */
                    LinearLayout.LayoutParams one_cell = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    CellView textView;
                    final Course course;
                    if(status==1){
                        textView = new CellView(this,
                                GridColor.getCourseBgColor((i+j)%10),
                                dip2px(this,5));
                        course = Course.getCourseWithWeek(10*i+j,WeekCount.getCurrentWeek());
                        textView.setText(course.getCourse_name()+"\n@"+course.getSite());
                    }
                    else {
                        textView = new CellView(this,
                                GridColor.getCourseBgColor(15),
                                dip2px(this,5));
                        course = Course.getCourseNoWeek(10*i+j);
                        textView.setText(course.getCourse_name()+"\n@"+course.getSite());
                    }
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(course!=null) {
                                showCouseDetails(course);
                            }
                        }
                    });
                    //textView.setBackgroundColor(GridColor.getCourseBgColor(j));
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    one_cell.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLayoutParams(one_cell);
                    //mcellViews.get(i-1).addView(textView);
                    /**
                     *   end
                     */
                    linearLayout.setPadding(2, 2, 2, 2);
                    linearLayout.addView(textView);
                    mcellViews.get(i-1).addView(linearLayout);
                }
                else{
                    LinearLayout linearLayout = new LinearLayout(this);
                    LinearLayout.LayoutParams Lp = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    Lp.weight = 1;
                    Lp.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    linearLayout.setLayoutParams(Lp);
                    /**
                     *    原始代码
                     */
                    LinearLayout.LayoutParams one_cell = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    TextView textView = new TextView(this);
                    textView.setText("");
                    textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    one_cell.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLayoutParams(one_cell);
                    //mcellViews.get(i-1).addView(textView);
                    /**
                     *   end
                     */
                    linearLayout.addView(textView);
                    mcellViews.get(i-1).addView(linearLayout);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
