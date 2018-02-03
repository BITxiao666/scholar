package com.example.javacourseexercise.GPAItem;

/**
 * Created by Liao on 2018/1/31.
 */

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.javacourseexercise.R;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Liao on 2018/1/26.
 */

public  class Activity_4th_Adapter extends RecyclerView.Adapter<Activity_4th_Adapter.ViewHolder>{

    private List<Activity_4th_class> mActivity_4th_classList;


    static class ViewHolder extends RecyclerView.ViewHolder{
        //TextView text_view_4th_date;
        //TextView text_view_4th_score;
        TextView text_view_4th_event;
        View view_4th;
        public ViewHolder(View view){
            super (view);
            view_4th=view;

            // text_view_4th_date=(TextView) view.findViewById(R.id.activity_4th_date);
            //text_view_4th_score=(TextView) view.findViewById(R.id.activity_4th_score);
            text_view_4th_event=(TextView) view.findViewById(R.id.activity_4th_event);
        }
    }

    public Activity_4th_Adapter(List<Activity_4th_class> activity_4th_classList){
        mActivity_4th_classList=activity_4th_classList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_4th_item, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.view_4th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position=holder.getAdapterPosition();
                final Activity_4th_class classselect=mActivity_4th_classList.get(position);

                //弹出对话框
                final View view=View.inflate(parent.getContext(), R.layout.dialog_delete, null);
                final Dialog dialog=new Dialog(parent.getContext());
                final TextView textview_date=(TextView) view.findViewById(R.id.dialog_delete_date);
                final TextView textView_event=(TextView) view.findViewById(R.id.dialog_delete_event);
                final TextView textView_score=(TextView) view.findViewById(R.id.dialog_delete_score);
                textview_date.setText(classselect.getDate().toString());
                textView_event.setText(classselect.getEvent().toString());
                textView_score.setText(classselect.getScore().toString());

                //退出当前对话框，不做任何操作
                TextView textView_exit =  (TextView) view.findViewById(R.id.dialog_delete_exit);
                textView_exit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialog.dismiss();
                    }
                });
//                Button button_eixt=(Button) view.findViewById(R.id.dialog_delete_exit);
//                button_eixt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });

                //删除操作，并且退出对话框
                TextView textView_delete = (TextView) view.findViewById(R.id.dialog_delete_delete);
                textView_delete.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Activity_4th_class classdelete=mActivity_4th_classList.get(position);
                        DataSupport.deleteAll(Activity_4th_tableclass.class, "event=?", classdelete.getEvent());
                        //   DataSupport.deleteAll(Activity_4th_table.class, "event=?", classdelete.getEvent());
                        mActivity_4th_classList.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });

//                Button button_delete=(Button) view.findViewById(R.id.dialog_delete_delete);
//                button_delete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Activity_4th_class classdelete=mActivity_4th_classList.get(position);
//                        DataSupport.deleteAll(Activity_4th_tableclass.class, "event=?", classdelete.getEvent());
//                     //   DataSupport.deleteAll(Activity_4th_table.class, "event=?", classdelete.getEvent());
//                        mActivity_4th_classList.remove(position);
//                        notifyItemRemoved(position);
//                        notifyDataSetChanged();
//                        dialog.dismiss();
//                    }
//                });

                //配置删除对话框
                dialog.setContentView(view);
                Window dialogWindow=dialog.getWindow();
                WindowManager.LayoutParams lp=dialogWindow.getAttributes();
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                lp.width=800;
                lp.height=580;
                dialog.show();


            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Activity_4th_class activity_4th_class=mActivity_4th_classList.get(position);
        //holder.text_view_4th_date.setText(activity_4th_class.getDate());
        // holder.text_view_4th_score.setText("    "+activity_4th_class.getScore()+"     ");
        holder.text_view_4th_event.setText(activity_4th_class.getEvent());
    }

    @Override
    public int getItemCount() {
        return mActivity_4th_classList.size();
    }

    public void addData(int position, Activity_4th_class activityclass){
        mActivity_4th_classList.add(position, activityclass);
        notifyItemInserted(position);
    }

    public void deleteData(int position){
        mActivity_4th_classList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
