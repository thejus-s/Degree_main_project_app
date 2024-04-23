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

public class Custompost extends BaseAdapter {
    String[] id,p,d;
    private  Context context;
    public Custompost(Context applicationContext, String[] id, String[] p, String[] d) {
        this.id = id;
        this.p = p;
        this.d = d;
        this.context = applicationContext;
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
            gridView=inflator.inflate(R.layout.activity_custompost,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView comp=(TextView)gridView.findViewById(R.id.textView10);
        TextView de=(TextView)gridView.findViewById(R.id.textView12);
        Button b = gridView.findViewById(R.id.button4);
        Button b1 = gridView.findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("pid",id[i]);
                editor.commit();
                context.startActivity(new Intent(context,Viewnominee.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("pid",id[i]);
                editor.commit();
                context.startActivity(new Intent(context,View_rules.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        comp.setTextColor(Color.BLACK);
        de.setTextColor(Color.BLACK);
        comp.setText(p[i]);
        de.setText(d[i]);



        return gridView;
    }
}