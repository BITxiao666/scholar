package com.example.bitshaw.tabledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        fun();
        initDayView();
        initSegmentView();
        initCell_1();
    }

    public void initDayView() {
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
            one_day.setText("周一");
            one_day.setTextColor(Color.parseColor("#4A4A4A"));
            //LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            one_grid.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            one_grid.weight = 1;
            one_day.setGravity(Gravity.CENTER_HORIZONTAL);
            one_day.setLayoutParams(one_grid);
            week.addView(one_day);
        }
    }

    private void initSegmentView() {
        int i;
        TextView segment;
        LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        for (i = 0; i < 5; i++) {
            segment = new TextView(this);
            segment.setText("1");
            segment.setTextColor(Color.parseColor("#4A4A4A"));
            //LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            one_grid.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            one_grid.weight = 1;
            segment.setGravity(Gravity.CENTER_HORIZONTAL);
            segment.setLayoutParams(one_grid);
            segments.addView(segment);
        }
    }

    /**
     * 临时测试用
     */
    public static Course course1 = new Course("Java基础","金旭亮");
    public static Course course2 = new Course("计算机组成原理","马忠梅");
    public static List<Lesson> lessons = new ArrayList<>();
    static void fun() {
        lessons.add(new Lesson(course1,1,1));
        lessons.add(new Lesson(course2,1,2));
    }

    /**
     * end
     */

    public void initCell() {
        int lesson_count=0;
        Lesson lesson = lessons.get(0);
        for (int i=1;i<=1;i++) {
            for (int j=1;j<=5;j++) {
                if(lesson.getWhat_day()==i && lesson.getWhich_segment()==j) {
                    LinearLayout.LayoutParams one_cell = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    CellView textView = new CellView(this,
                            GridColor.getCourseBgColor(j),
                            dip2px(this,5));
                    textView.setText(lesson.get_class_name());
                    //textView.setBackgroundColor(GridColor.getCourseBgColor(j));
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    textView.setTextSize(12);
                    one_cell.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    one_cell.weight = 1;
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLayoutParams(one_cell);
                    mcellViews.get(i-1).addView(textView);
                    lesson_count++;
                    if(lesson_count<lessons.size()) {
                        lesson = lessons.get(lesson_count);
                    }
                }
                else {
                    LinearLayout.LayoutParams one_cell = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    TextView textView = new TextView(this);
                    textView.setText("");
                    textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    one_cell.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    one_cell.weight = 1;
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLayoutParams(one_cell);
                    mcellViews.get(i-1).addView(textView);
                }
            }
        }
    }

    public void initCell_1(){
        int lesson_count=0;
        Lesson lesson = lessons.get(0);
        for (int i=1;i<=1;i++) {
            for (int j=1;j<=5;j++) {
                if (lesson.getWhat_day() == i && lesson.getWhich_segment() == j) {
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
                    textView.setText(lesson.get_class_name());
                    textView.setBackgroundColor(GridColor.getCourseBgColor(j));
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    one_cell.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLayoutParams(one_cell);
                    //mcellViews.get(i-1).addView(textView);
                    lesson_count++;
                    if(lesson_count<lessons.size()) {
                        lesson = lessons.get(lesson_count);
                    }
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

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}