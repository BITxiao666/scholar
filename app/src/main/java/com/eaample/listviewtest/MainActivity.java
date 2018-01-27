package com.eaample.listviewtest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<GPA> gpaList =new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }
        initGPA();
        GPAAdapater adapter =new GPAAdapater(MainActivity.this, R.layout.gpa_layout, gpaList);
        ListView listView =(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GPA gpa=gpaList.get(position);
                Intent intent;
                switch (gpa.getName()){
                    case "学生干部与团干部":
                        intent=new Intent(MainActivity.this, Activity_1st.class);
                        startActivity(intent);
                        break;
                    case "志愿活动":
                        intent=new Intent(MainActivity.this, Activity_2ed.class);
                        startActivity(intent);
                        break;
                    case "创新与竞赛":
                        intent=new Intent(MainActivity.this, Activity_3th.class);
                        startActivity(intent);
                        break;
                    case "体育":
                        intent=new Intent(MainActivity.this, Activity_4th.class);
                        startActivity(intent);
                        break;
                    case "艺术与修养":
                        intent=new Intent(MainActivity.this, Activity_5th.class);
                        startActivity(intent);
                        break;
                    case "学术与创新":
                        intent=new Intent(MainActivity.this, Activity_6h.class);
                        startActivity(intent);
                        break;
                        default:
                            break;
                }

            }
        });
    }

    private void initGPA() {
        for(int i=0; i<1; i++){
            GPA item_1st=new GPA("学生干部与团干部", R.drawable.w1);
            gpaList.add(item_1st);
            GPA item_2ed=new GPA("志愿活动",R.drawable.w2);
            gpaList.add(item_2ed);
            GPA item_3th=new GPA("创新与竞赛", R.drawable.w3);
            gpaList.add(item_3th);
            GPA item_4th=new GPA("体育", R.drawable.w4);
            gpaList.add(item_4th);
            GPA item_5th=new GPA("艺术与修养",R.drawable.w5);
            gpaList.add(item_5th);
            GPA item_6th=new GPA("学术与创新", R.drawable.w6);
            gpaList.add(item_6th);
        }
    }
}

