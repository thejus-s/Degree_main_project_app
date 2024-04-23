package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Customrule extends BaseAdapter {
    String[] id,p;
    private  Context context;
    public Customrule(Context applicationContext, String[] id, String[] r) {
        this.id = id;
        this.p = r;
        this.context = applicationContext;
    }


    @Override
    public int getCount() {
        return p.length;
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
            gridView=inflator.inflate(R.layout.activity_customrule,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView comp=(TextView)gridView.findViewById(R.id.textView10);
        comp.setTextColor(Color.BLACK);
        comp.setText(p[i]);



        return gridView;
    }
}