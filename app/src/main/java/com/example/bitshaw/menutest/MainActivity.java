package com.example.bitshaw.menutest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bitshaw.menutest.R;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    @BindView(R.id.week_main)
    LinearLayout week;

    @BindView(R.id.segments_main)
    LinearLayout segments;

    @BindViews({R.id.weekPanel_1, R.id.weekPanel_2, R.id.weekPanel_3, R.id.weekPanel_4,
            R.id.weekPanel_5})
    List<LinearLayout> mcellViews;
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initMenuFragment();
        ButterKnife.bind(this);
        initDayView();
        initSegmentView();
        LitePal.getDatabase();
        debugfun();
        initCell_1();
        ClickFloatButton();
        //addFragment(new MainFragment(), true, R.id.container);
    }

    private void ClickFloatButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this,
                        ChangeWeekActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * 初始化一些用于测试的数据
     * 发布版本中可能会删除
     */
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

    /**
     * 创建课程的横栏（周一~周五）
     */
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
        one_day.setBackgroundColor(Color.parseColor("#FAFAFA"));
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

    /**
     * 创建课程表的竖栏（第一节到第五节）
     */
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

    /**
     * 动态加载每一节课
     */
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
                                showCourseDetails(course);
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

    /**
     * 初始化toolbar栏
     */
    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    /**
     * 函数来自开源框架
     * https://github.com/Yalantis/Context-Menu.Android
     * @return
     */
    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("添加课程");
        send.setResource(R.drawable.ic_add_course);

        MenuObject like = new MenuObject("转到下周");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_change_week);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("新建学期");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_term));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("获取源码");
        addFav.setResource(R.drawable.ic_github);

        /*MenuObject block = new MenuObject("Block user");
        block.setResource(R.drawable.icn_5);*/

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        //menuObjects.add(block);
        return menuObjects;
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        /*mToolbar.setNavigationIcon(R.drawable.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
        mToolBarTextView.setText("         "+String.valueOf(WeekCount.getCurrentWeek()));
    }

    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
            finish();
        } else {
            finish();
        }
    }

    @Override
    protected void onUserLeaveHint() {
        finish();
        super.onUserLeaveHint();
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        if(position == 1){
            Intent intent = new Intent(MainActivity.this,
                    EditActivity.class);
            startActivity(intent);
        }else if(position == 2){
            Intent intent = new Intent(MainActivity.this,
                    ChangeWeekActivity.class);
            startActivity(intent);
        }
        else if(position == 3){
            confirm_claer();
            //Course.courseClear();
        }
        //Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * 调用了onCreate方法，解决动态刷新问题
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        onDestroy();
        onCreate(null);
    }

    public void showCourseDetails(final Course course) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        TextView textView = (TextView) dialog.findViewById(R.id.name_detail);
        textView.setText(course.getCourse_name());
        textView = (TextView) dialog.findViewById(R.id.teacher_detail);
        textView.setText(course.getTutor_name());
        textView = (TextView) dialog.findViewById(R.id.address_detail);
        textView.setText(course.getSite());
        textView = (TextView) dialog.findViewById(R.id.week_detail);
        textView.setText(course.getBegin_week()+" - "+course.getFinish_week());
        textView = (TextView)dialog.findViewById(R.id.edit_course_detail);
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

    private void confirm_claer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setContentView(R.layout.clear_course_layout);
        DisplayMetrics dm=this.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.66);    //宽度设置为屏幕的0.66
        //p.height = (int) (displayHeight * 0.28);    //高度设置为屏幕的0.28
        dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
        dialog.getWindow().setAttributes(p);     //设置生效
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView esc = (TextView)dialog.findViewById(R.id.clear_course_esc);
        esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView confirm = (TextView)dialog.findViewById(R.id.clear_course_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course.courseClear();
                dialog.dismiss();
            }
        });
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
