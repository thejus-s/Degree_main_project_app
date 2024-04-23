package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_reply extends BaseAdapter {
    String[] id,Complaints,Complaint_date,Reply,Reply_date;
    private Context context;

    public Custom_reply(Context applicationContext, String[] id, String[] complaints, String[] complaint_date, String[] reply) {
        this.context=applicationContext;
        this.id=id;
        this.Complaints=complaints;
        this.Complaint_date=complaint_date;
        this.Reply=reply;

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
            gridView=inflator.inflate(R.layout.activity_custom_reply,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView comp=(TextView)gridView.findViewById(R.id.textView10);
        TextView compdate=(TextView)gridView.findViewById(R.id.textView12);
        TextView rep=(TextView)gridView.findViewById(R.id.textView14);

        comp.setTextColor(Color.BLACK);
        comp.setText(Complaints[i]);

        compdate.setTextColor(Color.BLACK);
        compdate.setText(Complaint_date[i]);


        rep.setTextColor(Color.BLACK);
        rep.setText(Reply[i]);

        return gridView;
    }
}