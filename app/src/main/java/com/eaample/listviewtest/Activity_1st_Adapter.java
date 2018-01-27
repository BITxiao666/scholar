package com.eaample.listviewtest;

import android.app.Dialog;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Liao on 2018/1/26.
 */

public  class Activity_1st_Adapter extends RecyclerView.Adapter<Activity_1st_Adapter.ViewHolder>{

    private List<Activity_1st_class> list_Activity_1st_class;


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_view_1st_date;
        TextView text_view_1st_score;
        TextView text_view_1st_event;
        View view_1st;
        public ViewHolder(View view){
            super (view);
            view_1st=view;
            text_view_1st_date=(TextView) view.findViewById(R.id.text_view_1st_data);
            text_view_1st_score=(TextView) view.findViewById(R.id.text_view_1st_score);
            text_view_1st_event=(TextView) view.findViewById(R.id.text_view_1st_event);
        }
    }

    public Activity_1st_Adapter(List<Activity_1st_class> listActivity1stclass){
        list_Activity_1st_class=listActivity1stclass;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_1st_item, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.view_1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position=holder.getAdapterPosition();
                final Activity_1st_class classselect=list_Activity_1st_class.get(position);

                //弹出对话框
                final View view=View.inflate(parent.getContext(), R.layout.layout2, null);
                final Dialog dialog=new Dialog(parent.getContext());
                final TextView textview_date=(TextView) view.findViewById(R.id.text_view_dialog_date);
                final TextView textView_event=(TextView) view.findViewById(R.id.text_view_dialog_event);
                final TextView textView_score=(TextView) view.findViewById(R.id.text_view_dialog_score);
                textview_date.setText(classselect.getDate().toString());
                textView_event.setText(classselect.getEvent().toString());
                textView_score.setText(classselect.getScore().toString());

                //退出当前对话框，不做任何操作
                Button button_eixt=(Button) view.findViewById(R.id.button_delog_exit);
                button_eixt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                //删除操作，并且退出对话框
                Button button_delete=(Button) view.findViewById(R.id.button_delog_delete);
                button_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list_Activity_1st_class.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view);
                Window dialogWindow=dialog.getWindow();
                WindowManager.LayoutParams lp=dialogWindow.getAttributes();
                lp.width=900;
                lp.height=800;
                dialogWindow.setAttributes(lp);
                dialog.show();


            }
        });
          return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Activity_1st_class activity_1st_class=list_Activity_1st_class.get(position);
        holder.text_view_1st_date.setText(activity_1st_class.getDate());
        holder.text_view_1st_score.setText("    "+activity_1st_class.getScore()+"     ");
        holder.text_view_1st_event.setText(activity_1st_class.getEvent());
    }

    @Override
    public int getItemCount() {
        return list_Activity_1st_class.size();
    }

    public void addData(int position, Activity_1st_class activityclass){
        list_Activity_1st_class.add(position, activityclass);
        notifyItemInserted(position);
    }

    public void deleteData(int position){
        list_Activity_1st_class.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
