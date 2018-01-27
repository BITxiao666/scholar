package com.eaample.listviewtest;

import android.app.Dialog;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Activity_1st extends AppCompatActivity {

    private List<Activity_1st_class>listActivity1stclass=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1st);
        initActivity_1st_class();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view_1st);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final Activity_1st_Adapter adapter=new Activity_1st_Adapter(listActivity1stclass);
        recyclerView.setAdapter(adapter);




        final Button button_delete=(Button) findViewById(R.id.button_1st_deleteitem);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });




        Button button_add=(Button) findViewById(R.id.button_1st_additem);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // final AlertDialog.Builder builder=new AlertDialog.Builder(Activity_1st.this);
                final View view=View.inflate(Activity_1st.this, R.layout.layout, null);
                final Dialog dialog=new Dialog(Activity_1st.this);
                final EditText edDate=(EditText) view.findViewById(R.id.editview_delog_1st_1);
                final EditText edScore=(EditText) view.findViewById(R.id.editview_delog_1st_2);
                final EditText edEvent=(EditText) view.findViewById(R.id.editview_delog_1st_3);
                Button button_delog_additem=(Button) view.findViewById(R.id.button_delog_1st_1);

                //对话框添加按钮
                button_delog_additem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Activity_1st_class classadd=new Activity_1st_class(edDate.getText().toString(), edEvent.getText().toString(),
                                edScore.getText().toString());
                        adapter.addData(listActivity1stclass.size(),classadd);
                        dialog.dismiss();

                    }
                });

                //对话框退出按钮
                Button button_delog_delitem=(Button) view.findViewById(R.id.button_delog_1st_2);
                button_delog_delitem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view);
                Point Outsize=new Point();
                getWindowManager().getDefaultDisplay().getSize(Outsize);
                dialog.getWindow().setLayout(Outsize.x,Outsize.y*3/5);
                dialog.show();

//                builder.setView(view);
//                builder.show();

            }

        });




    }

    private void initActivity_1st_class(){
        Activity_1st_class activity_1st_class_1st=new Activity_1st_class("2018-01", "10","班长职务");
        listActivity1stclass.add(activity_1st_class_1st);
        Activity_1st_class activity_1st_class_2st=new Activity_1st_class("2018-02","5","科协技术部部长");
        listActivity1stclass.add(activity_1st_class_2st);
        Activity_1st_class activity_1st_class_3st=new Activity_1st_class("2018-01", "8","班长职务");
        listActivity1stclass.add(activity_1st_class_3st);
        Activity_1st_class activity_1st_class_4st=new Activity_1st_class("2018-02","5","科协技术部部长");
        listActivity1stclass.add(activity_1st_class_4st);
    }
}
