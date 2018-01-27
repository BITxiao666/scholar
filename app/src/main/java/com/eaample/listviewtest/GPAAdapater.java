package com.eaample.listviewtest;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Liao on 2018/1/24.
 */

public class GPAAdapater extends ArrayAdapter<GPA> {
    private int resourceId;
    public GPAAdapater(Context context, int textViewResourceId, List<GPA> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        GPA gpa = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.gpaImage = (ImageView) view.findViewById(R.id.gpa_image);
            viewHolder.gpaName = (TextView) view.findViewById(R.id.gpa_name);
            view.setTag(viewHolder);
        }
        else
        {
            view =convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.gpaImage.setImageResource(gpa.getImageId());
        viewHolder.gpaName.setText(gpa.getName());
        return view;
        }

class ViewHolder{
    ImageView gpaImage;
    TextView gpaName;
 }
}