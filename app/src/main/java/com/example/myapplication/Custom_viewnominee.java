package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_viewnominee extends BaseAdapter {
    String[] id,Complaints,Complaint_date,Reply,Reply_date;
    private Context context;

    public Custom_viewnominee(Context applicationContext, String[] id, String[] name, String[] photo, String[] course) {
        this.context=applicationContext;
        this.id=id;
        this.Complaints=name;
        this.Complaint_date=photo;
        this.Reply=course;

    }


    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_viewnominee,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView compdate=(TextView)gridView.findViewById(R.id.textView12);
        TextView rep=(TextView)gridView.findViewById(R.id.textView14);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);

        compdate.setTextColor(Color.BLACK);
        compdate.setText(Complaints[i]);


        rep.setTextColor(Color.BLACK);
        rep.setText(Reply[i]);
        String images= Complaint_date[i];
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("url","");
        String url=ip+images;
        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);//circle



        return gridView;
    }
}