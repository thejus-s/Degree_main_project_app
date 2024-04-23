package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Customvotecand extends BaseAdapter {
    String[] id,name,photo,post;
    private Context context;
    public Customvotecand(Context applicationContext, String[] id, String[] name, String[] photo, String[] course) {
        this.context=applicationContext;
        this.id=id;
        this.name=name;
        this.photo=photo;
        this.post=course;
    }
    @Override
    public int getCount() {
        return post.length;
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
    public View getView(int i, View view, ViewGroup viewGroup){


        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_customvotecand,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView compdate=(TextView)gridView.findViewById(R.id.textView12);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);
        Button bb=(Button) gridView.findViewById(R.id.button9);




        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                RequestQueue queue = Volley.newRequestQueue(context);
                String  url = sh.getString("url", "") + "/vote";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
//                        Toast.makeText(context, "welcome"+response, Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject json = new JSONObject(response);


                            String res = json.getString("status");

                            if (res.equalsIgnoreCase("ok")) {

                                //////////

                                Toast.makeText(context, "Vote updated", Toast.LENGTH_SHORT).show();
                                int es = Integer.parseInt(sh.getString("pcount",""))+1;
                                SharedPreferences.Editor edp = sh.edit();
                                edp.putString("pcount", String.valueOf(es));
                                edp.commit();
                                context.startActivity(new Intent(context,Vote_candidates.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));


                            } else {

                                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, ""+e.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(context, "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("cid", id[i]);
                        params.put("lid", sh.getString("lid",""));

                        return params;
                    }
                };
                queue.add(stringRequest);

            }
        });


        compdate.setTextColor(Color.BLACK);
        compdate.setText(name[i]);
        String images= photo[i];
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("url","");
        String url=ip+images;
        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);//circle



        return gridView;

    }
}