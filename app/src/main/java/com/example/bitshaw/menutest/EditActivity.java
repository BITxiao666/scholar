package com.example.bitshaw.menutest;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.bitshaw.menutest.R;

public class EditActivity extends AppCompatActivity {

    @BindView(R.id.course_name_edit)
    EditText course_name;

    @BindView(R.id.tutor_name_edit)
    EditText tutor_name;

    @BindView(R.id.begin_week_edit)
    EditText begin_week;

    @BindView(R.id.finish_week_edit)
    EditText finish_week;

    @BindView(R.id.day_edit)
    EditText which_day;

    @BindView(R.id.segment_edit)
    EditText which_segment;

    @BindView(R.id.site_edit)
    EditText site;

    private Course old_course = null;
    int course_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        Intent intent = getIntent();
        course_id = intent.getIntExtra("tag_course_id",-1);

        if(course_id != -1){
            List<Course> list = DataSupport.where("id = ? ",
                    String.valueOf(course_id)).find(Course.class);
            old_course = list.get(0);
        }
        ButterKnife.bind(this);
        init_course();


        TextView save_button = (TextView) findViewById(R.id.save_button_edit);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_course();
            }
        });

        TextView back_button = (TextView) findViewById(R.id.back_button_edit);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!allEditIsEmpty()) {
                    Intent intent_back = new Intent(EditActivity.this,
                            MainActivity.class);
                    startActivity(intent_back);
                    finish();
                }
                else {

                }
            }
        });
        TextView delete_button = (TextView) findViewById(R.id.delete_button_edit);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm_delete();
                //delete_course();
            }
        });
    }

    private boolean allEditIsEmpty(){
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent_back = new Intent(EditActivity.this,
                MainActivity.class);
        startActivity(intent_back);
        finish();
    }

    private void init_course(){

        if(course_id==-1){
            return;
        }
        course_name.setText(old_course.getCourse_name());
        tutor_name.setText(old_course.getTutor_name());
        site.setText(old_course.getSite());
        begin_week.setText(String.valueOf(old_course.getBegin_week()));
        finish_week.setText(String.valueOf(old_course.getFinish_week()));
        which_day.setText(String.valueOf(old_course.getTime()/10));
        which_segment.setText(String.valueOf(old_course.getTime()%10));
    }

    private void save_course(){
        int editstatus = editCheck();
        int inputstatus = 0;
        if(editstatus==1) {
            int time = Integer.valueOf(which_day.getText().toString()) * 10 +
                    Integer.valueOf(which_segment.getText().toString());

            Course course_for_check = new Course(course_id,course_name.getText().toString(),
                    tutor_name.getText().toString(), site.getText().toString(),
                    Integer.valueOf(begin_week.getText().toString()),
                    Integer.valueOf(finish_week.getText().toString()), time);
            inputstatus = course_for_check.checkInput();
            if (inputstatus == 1) {
                Toast.makeText(EditActivity.this, "与现有课程冲突", Toast.LENGTH_SHORT).show();
            }else {

                Course new_course = new Course(course_name.getText().toString(),
                        tutor_name.getText().toString(), site.getText().toString(),
                        Integer.valueOf(begin_week.getText().toString()),
                        Integer.valueOf(finish_week.getText().toString()), time);

                if(course_id>0){
                    DataSupport.delete(Course.class,course_id);
                    new_course.save();
                }
                else if(course_id == -1){
                    new_course.save();
                }
                Intent intent_back = new Intent(EditActivity.this,
                        MainActivity.class);
                startActivity(intent_back);
                finish();
            }
        }
    }

    private void delete_course(){
        if(course_id==-1){
            Toast.makeText(EditActivity.this,"仅能删除以保存的课程",Toast.LENGTH_SHORT).show();
            return;
        }
        DataSupport.delete(Course.class,course_id);
        Intent intent_back = new Intent(EditActivity.this,
                MainActivity.class);
        startActivity(intent_back);
        finish();
    }

    private void confirm_delete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setContentView(R.layout.confirm_delete_layout);

        DisplayMetrics dm=this.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.66);    //宽度设置为屏幕的0.66
        //p.height = (int) (displayHeight * 0.28);    //高度设置为屏幕的0.28
        dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
        dialog.getWindow().setAttributes(p);     //设置生效
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView esc = (TextView)dialog.findViewById(R.id.confirm_delete_esc);
        esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView confirm = (TextView)dialog.findViewById(R.id.confirm_delete_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_course();
            }
        });
    }

    private int editCheck() {
        if(course_name.getText().toString().isEmpty()){
            Toast.makeText(EditActivity.this,"请输入课程",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(tutor_name.getText().toString().isEmpty()){
            Toast.makeText(this,"请输入教师",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(site.getText().toString().isEmpty()){
            Toast.makeText(this,"请输入地点",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(begin_week.getText().toString().isEmpty()){
            Toast.makeText(this,"请输入开始周",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(finish_week.getText().toString().isEmpty()){
            Toast.makeText(this,"请输入结束周",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(which_day.getText().toString().isEmpty()){
            Toast.makeText(this,"请输入星期",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(which_segment.getText().toString().isEmpty()){
            Toast.makeText(this,"请输入时间",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(Integer.valueOf(begin_week.getText().toString())<1||
                Integer.valueOf(begin_week.getText().toString())>19){
            Toast.makeText(this,"开始周在1到19之间",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(Integer.valueOf(finish_week.getText().toString())<1||
                Integer.valueOf(finish_week.getText().toString())>19){
            Toast.makeText(this,"结束周在1到19之间",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(Integer.valueOf(which_segment.getText().toString())<1||
                Integer.valueOf(which_segment.getText().toString())>5){
            Toast.makeText(this,"节数在1到5之间",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(Integer.valueOf(which_day.getText().toString())<1||
                Integer.valueOf(which_day.getText().toString())>5){
            Toast.makeText(this,"天数在1到5之间",Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(Integer.valueOf(finish_week.getText().toString())<
                Integer.valueOf(begin_week.getText().toString())){
            Toast.makeText(this,"结束周应大于开始周",Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
}
